package controller;

import entity.UserEntity;
import service.UserService;

public class UserController {
	private UserService userService;

	// コンストラクタ
	public UserController(UserService userService) {
		this.userService = userService;
	}

	// ユーザーの登録
	public void register(UserEntity user) {
		userService.register(user);
	}

	// ログイン処理
	public UserEntity login(String email, String password) {
		return userService.login(email, password);
	}

	// メールアドレスからユーザーを取得
	public UserEntity findByEmail(String email) {
		return userService.findByEmail(email);
	}

	// ユーザーの削除
	public void delete(int userId) {
		userService.delete(userId);
	}
}