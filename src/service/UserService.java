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

		UserEntity existingUser = userRepository.findByEmail(user.getEmail());

		// 入力されたメールアドレスが登録されてるか判定
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
			throw new IllegalArgumentException("メールアドレスまたはパスワードが間違っています。");
		}

		if (!passwordHasher.verify(password, user.getPasswordHash())) {
			throw new IllegalArgumentException("メールアドレスまたはパスワードが間違っています。");
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
