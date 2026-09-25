package view;

import java.util.Scanner;

import controller.UserController;
import entity.UserEntity;
import util.InputUtil;

public class UserView {
	boolean isLogin = false;

	// MenuからScanner,UserControllerを設定
	private UserController userController;
	private Menu menu;
	private InputUtil inputUtil;

	// 現在ログインしてるユーザー
	private UserEntity loginUser;

	// ログイン方法
	String[] loginItems = {
			"新規登録",
			"ログイン"
	};

	// user機能のメニュー
	String[] userItems = {
			"ログアウト",
			"削除",
	};

	// コンストラクタ
	public UserView(
			Scanner scan,
			Menu menu,
			UserController userController,
			InputUtil inputUtil) {
		this.menu = menu;
		this.userController = userController;
		this.inputUtil = inputUtil;
	}

	// ログインに関する表示
	public int loginMenu() {
		System.out.println("==ログインメニュー==");
		for (int i = 0; i < loginItems.length; i++) {
			System.out.println((i + 1) + ". " + loginItems[i]);
		}
		System.out.println();
		System.out.print("ログイン方法を選んでください：");
		String input = inputUtil.inputMenuChoice();
		return inputUtil.inputCheck(input);
	}

	// ユーザーに関する表示
	public int userMenu() {
		System.out.println("===ユーザーメニュー===");
		for (int i = 0; i < userItems.length; i++) {
			System.out.println((i + 1) + ". " + userItems[i]);
		}
		System.out.println();
		System.out.println("0. 戻る");
		System.out.print("選択してください：");
		String input = inputUtil.inputMenuChoice();

		return inputUtil.inputCheck(input);
	}

	// ログインしてるユーザーの情報を取得
	public UserEntity getLoginUser() {
		return loginUser;
	}

	// ユーザーの登録に必要な情報を入力
	public UserEntity registerUser() {
		System.out.println("==ユーザーの登録==");
		System.out.println();

		String userName = inputUtil.inputUserName();
		String email = inputUtil.inputEmail();
		String password = inputUtil.inputPassword();

		System.out.println();
		System.out.println("================");

		return new UserEntity(
				userName,
				email,
				password);
	}

	// ログイン処理
	public UserEntity login() {
		System.out.println("===ログイン===");
		System.out.println();

		String email = inputUtil.inputEmail();
		String password = inputUtil.inputPassword();

		System.out.println();
		System.out.println("=============");

		return userController.login(email, password);
	}

	// ログインのCLI操作
	public void showLoginMenu() {

		while (!isLogin) {
			int input = loginMenu();
			switch (input) {
			// ユーザー登録
			case 1:
				UserEntity registerUser = registerUser();
				try {
					userController.register(registerUser);
					loginUser = registerUser;
					isLogin = true;

					System.out.println("ユーザー登録が完了しました。");

				} catch (IllegalArgumentException e) {

					System.out.println("登録に失敗しました。");
					System.out.println(e.getMessage());
				}

				System.out.println();
				return;

			// ログイン処理
			case 2:
				if (loginUser != null) {
					System.out.println("ログイン済みです");
					System.out.println();
					break;
				}

				try {
					loginUser = login();
					isLogin = true;

					System.out.println("ログインしました");

				} catch (IllegalArgumentException e) {

					System.out.println("ログインに失敗しました");
					System.out.println(e.getMessage());

				}
				System.out.println();

				break;

			default:
				System.out.println("正しい番号を入力してください。");
			}
		}
	}

	// User機能のCLI操作
	public void showUserMenu() {
		while (true) {

			int input = userMenu();

			switch (input) {

			// ログアウト
			case 1:
				if (loginUser == null) {
					System.out.println("ログインしていません");
					System.out.println();
					break;
				}

				loginUser = null;
				isLogin = false;
				System.out.println("ログアウトしました");
				System.out.println();
				return;

			// アカウント削除
			case 2:
				if (loginUser == null) {
					System.out.println("ログインしてください。");
					System.out.println();
					return;
				}

				int userId = inputUtil.inputDeleteUser(loginUser.getUserId());

				if (userId != -1) {
					userController.delete(userId);
					loginUser = null;
					isLogin = false;
					System.out.println("アカウントを削除しました。");
					System.out.println();
					return;
				}
				return;

			case 0:
				return;

			default:
				System.out.println("正しい番号を入力してください。");
			}
		}
	}
}