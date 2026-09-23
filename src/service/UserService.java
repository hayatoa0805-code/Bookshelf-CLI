package service;

import entity.UserEntity;
import repository.UserRepository;
import util.PasswordHasher;

public class UserService {
	private UserRepository userRepository;
	private PasswordHasher passwordHasher;

	// コンストラクタでUserRepositoryを受け取る
	public UserService(
			UserRepository userRepository,
			PasswordHasher passwordHasher) {
		this.userRepository = userRepository;
		this.passwordHasher = passwordHasher;
	}

	// ユーザーの登録
	public void register(UserEntity user) {

		// ユーザーネームの入力制御
		String userName = user.getName();

		if (userName == null || userName.isBlank()) {
			throw new IllegalArgumentException(
					"ユーザーネームを入力してください。");
		}

		if (userName.length() < 4 || userName.length() >= 20) {
			throw new IllegalArgumentException(
					"ユーザーネームは4文字以上、20文字未満で入力してください。");
		}

		// 入力されたメールアドレスが登録されているか判定
		UserEntity existingUser = userRepository.findByEmail(user.getEmail());

		if (existingUser != null) {
			throw new IllegalArgumentException(
					"このメールアドレスはすでに登録されています。");
		}

		// パスワードをハッシュ化
		String hashedPassword = passwordHasher.hash(user.getPasswordHash());
		user.setPasswordHash(hashedPassword);
		userRepository.register(user);
	}

	// ログイン処理
	public UserEntity login(String email, String password) {

		UserEntity user = userRepository.findByEmail(email);

		if (user == null) {
			throw new IllegalArgumentException(
					"メールアドレスまたはパスワードが間違っています。");
		}

		// アカウントが無効か確認
		if (!user.isActive()) {
			throw new IllegalArgumentException(
					"このアカウントは現在ログインできません。");
		}

		if (!passwordHasher.verify(password, user.getPasswordHash())) {
			throw new IllegalArgumentException(
					"メールアドレスまたはパスワードが間違っています。");
		}

		return user;
	}

	// メールアドレスからユーザーを取得
	public UserEntity findByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	// ユーザーの削除
	public void delete(int userId) {
		userRepository.delete(userId);
	}

}
