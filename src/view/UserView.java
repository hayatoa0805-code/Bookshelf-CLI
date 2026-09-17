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
	String[] loginMenu = {
			"新規登録",
			"ログイン"
	};

	// user機能のメニュー
	String[] userMenu = {
			"ログアウト",
			"削除",
	};

	// コンストラクタ
	public UserView(Scanner scan, Menu menu, UserController userController) {
		this.scan = scan;
		this.menu = menu;
		this.userController = userController;
	}

	// ログインに関する表示
	public int loginMenu() {
		System.out.println("==ログインメニュー==");
		for (int i = 0; i < loginMenu.length; i++) {
			System.out.println((i + 1) + ". " + loginMenu[i]);
		}
		System.out.println();
		System.out.print("ログイン方法を選んでください：");
		String input = scan.nextLine();
		return menu.inputCheck(input);
	}

	// ユーザーに関する表示
	public int userMenu() {
		System.out.println("===ユーザーメニュー===");
		for (int i = 0; i < userMenu.length; i++) {
			System.out.println((i + 1) + ". " + userMenu[i]);
		}

		String input = menu.returnMenu();

		return menu.inputCheck(input);
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

	// ログインしてるユーザーの情報を取得
	public UserEntity getLoginUser() {
		return loginUser;
	}

	// メールアドレス検索
	public String searchEmail() {
		System.out.print("メールアドレスを入力してください：");
		String email = scan.nextLine();
		return email;
	}

	// 本の削除
	public int inputDeleteUser(int userId) {
		System.out.println("アカウントを削除しますか？[y/n]");
		String input = scan.nextLine();
		if ("y".equals(input)) {
			return userId;
		}
		return -1;
	}

	// ユーザーIdを入力
	public int inputUserId() {
		System.out.print("ユーザーIDを入力してください：");
		int userId = scan.nextInt();
		scan.nextLine();
		return userId;
	}

	// ユーザーネームを入力
	public String inputUserName() {
		System.out.print("ユーザーネームを入力してください：");
		String userName = scan.nextLine();
		return userName;
	}

	// メールアドレスを入力
	public String inputEmail() {
		System.out.print("メールアドレスを入力してください：");
		String email = scan.nextLine();
		return email;
	}

	// パスワードを入力
	public String inputPassword() {
		System.out.print("パスワードを入力してください：");
		String password = scan.nextLine();
		return password;
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
			}
		}
	}

	// User希望のCLi操作
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

				int userId = inputDeleteUser(loginUser.getUserId());

				if (userId != -1) {
					userController.delete(userId);
					loginUser = null;
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