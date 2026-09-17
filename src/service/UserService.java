package service;

import entity.UserEntity;
import repository.UserRepository;

public class UserService {
	private UserRepository userRepository;

	// コンストラクタでUserRepositoryを受け取る
	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	// ユーザーの登録
	public void register(UserEntity user) {

		UserEntity existingUser = userRepository.findByEmail(user.getEmail());

		// 入力されたメールアドレスが登録されてるか判定
		if (existingUser != null) {
			throw new IllegalArgumentException(
					"このメールアドレスはすでに登録されています。");
		}

		userRepository.register(user);
	}

	// ログイン処理
	public UserEntity login(String email, String password) {
		UserEntity user = userRepository.findByEmail(email);

		if (user == null) {
			throw new IllegalArgumentException("メールアドレスまたはパスワードが間違っています。");
		}

		if (!user.getPasswordHash().equals(password)) {
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
