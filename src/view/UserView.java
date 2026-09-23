package view;

import java.util.Scanner;

import controller.UserController;
import entity.UserEntity;

public class UserView {
	private Scanner scan;
	boolean isLogin = false;

	// MenuからScanner,UserControllerを設定
	private UserController userController;
	private Menu menu;

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
			UserController userController) {
		this.scan = scan;
		this.menu = menu;
		this.userController = userController;
	}

	// ログインに関する表示
	public int loginMenu() {
		System.out.println("==ログインメニュー==");
		for (int i = 0; i < loginItems.length; i++) {
			System.out.println((i + 1) + ". " + loginItems[i]);
		}
		System.out.println();
		System.out.print("ログイン方法を選んでください：");
		String input = scan.nextLine();
		return menu.inputCheck(input);
	}

	// ユーザーに関する表示
	public int userMenu() {
		System.out.println("===ユーザーメニュー===");
		for (int i = 0; i < userItems.length; i++) {
			System.out.println((i + 1) + ". " + userItems[i]);
		}

		String input = menu.inputMenu();

		return menu.inputCheck(input);
	}

	// ユーザーIdを入力
	public int inputUserId() {
		String input = "";
		int userId = -1;

		while (userId == -1) {
			System.out.print("ユーザーIDを入力してください：");
			input = scan.nextLine();
			userId = menu.inputCheck(input);
		}

		return userId;
	}

	// ユーザーネームを入力
	public String inputUserName() {
		String userName = "";

		while (true) {
			System.out.print("ユーザーネームを入力してください：");
			userName = scan.nextLine();

			if (userName.isBlank()) {
				System.out.println(
						"ユーザーネームを入力してください。");

				continue;
			}

			if (userName.length() < 4 || userName.length() >= 20) {
				System.out.println(
						"ユーザーネームは4文字以上、20文字未満で入力してください。");
				continue;
			}
			break;
		}
		return userName;
	}

	// メールアドレスを入力
	public String inputEmail() {

		while (true) {

			System.out.print("メールアドレスを入力してください：");
			String email = scan.nextLine();

			if (email.isBlank()) {
				System.out.println(
						"メールアドレスを入力してください。");
				continue;
			}

			if (!email.matches("^[^@\\s]+@[^@\\s]+\\.[^@\\s]+$")) {
				System.out.println(
						"正しいメールアドレスを入力してください。");
				continue;
			}

			return email;
		}
	}

	// パスワードを入力
	public String inputPassword() {
		String password = "";
		while (password.isBlank()) {
			System.out.print("パスワードを入力してください：");
			password = scan.nextLine();
		}
		return password;
	}

	// ユーザーの削除
	public int inputDeleteUserId(int userId) {

		while (true) {

			System.out.print("アカウントを削除しますか？[y/n]:");
			String input = scan.nextLine().trim().toLowerCase();

			if ("y".equals(input)) {
				return userId;
			}

			if ("n".equals(input)) {
				return -1;
			}

			System.out.println("yまたはnを入力してください。");
		}
	}

	// ログインしてるユーザーの情報を取得
	public UserEntity getLoginUser() {
		return loginUser;
	}

	// ユーザーの登録に必要な情報を入力
	public UserEntity registerUser() {
		System.out.println("==ユーザーの登録==");
		System.out.println();

		String userName = inputUserName();
		String email = inputEmail();
		String password = inputPassword();

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

		String email = inputEmail();
		String password = inputPassword();

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

				int userId = inputDeleteUserId(loginUser.getUserId());

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