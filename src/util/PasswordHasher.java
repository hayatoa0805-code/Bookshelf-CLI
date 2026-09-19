package util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

public class PasswordHasher {

	private static final int SALT_LENGTH = 16;
	private static final int ITERATIONS = 600_000;
	private static final int KEY_LENGTH = 256;

	// パスワードをハッシュ化
	public String hash(String password) {

		byte[] salt = new byte[SALT_LENGTH];
		new SecureRandom().nextBytes(salt);

		PBEKeySpec spec = new PBEKeySpec(
				password.toCharArray(),
				salt,
				ITERATIONS,
				KEY_LENGTH);

		try {
			SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");

			byte[] hash = factory.generateSecret(spec).getEncoded();

			return Base64.getEncoder().encodeToString(salt)
					+ ":"
					+ Base64.getEncoder().encodeToString(hash);
		} catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
			throw new IllegalStateException("パスワードのハッシュ化に失敗しました。");
		} finally {
			spec.clearPassword();
		}
	}

	public boolean verify(String password, String storedHash) {

		// 保存されてる「ソルト:ハッシュ値」を分離
		String[] parts = storedHash.split(":");

		if (parts.length != 2) {
			return false;
		}

		byte[] salt = Base64.getDecoder().decode(parts[0]);
		byte[] expectedHash = Base64.getDecoder().decode(parts[1]);

		PBEKeySpec spec = new PBEKeySpec(
				password.toCharArray(),
				salt,
				ITERATIONS,
				KEY_LENGTH);

		try {
			SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");

			byte[] actualHash = factory.generateSecret(spec).getEncoded();

			return MessageDigest.isEqual(actualHash, expectedHash);
		} catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
			throw new IllegalStateException("パスワードの検証に失敗しました。", e);
		} finally {
			spec.clearPassword();
		}

	}
}
