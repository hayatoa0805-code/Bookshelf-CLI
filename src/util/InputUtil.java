package util;

import java.util.Scanner;

public class InputUtil {
	private Scanner scan;

	// コンストラクタ
	public InputUtil(
			Scanner scan) {
		this.scan = scan;
	}

	// 共通処理s
	// 受け取った入力を整数に変換できるか確認
	public int inputCheck(String input) {
		try {
			// 受け取った入力を String から int に変換
			return Integer.parseInt(input);
		} catch (NumberFormatException e) {
			// 整数に変換できない場合、-1を返す
			return -1;
		}
	}

	// Menu

	// メニュー選択時の入力受け取り
	public String inputMenuChoice() {
		String input = scan.nextLine();
		return input;
	}

	// User

	// ユーザーIdを入力
	public int inputUserId() {
		String input = "";
		int userId = -1;

		while (userId == -1) {
			System.out.print("ユーザーIDを入力してください：");
			input = scan.nextLine();
			userId = inputCheck(input);
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
	public int inputDeleteUser(int userId) {

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

	// Book

	// タイトルを入力
	public String inputTitle() {

		String title = "";

		while (title.isBlank()) {
			System.out.print("タイトルを入力してください：");
			title = scan.nextLine();
		}

		return title;
	}

	// 本の巻数を入力
	public String inputVolume() {

		String volume = "";

		while (volume.isBlank()) {
			System.out.print("巻数を入力してください：");
			volume = scan.nextLine();
		}

		return volume;
	}

	// 出版社を入力
	public String inputPublisher() {

		String publisher = "";

		while (publisher.isBlank()) {
			System.out.print("出版社を入力してください：");
			publisher = scan.nextLine();
		}

		return publisher;
	}

	// 著者を入力
	public String inputAuthor() {

		String author = "";

		while (author.isBlank()) {
			System.out.print("著者を入力してください：");
			author = scan.nextLine();
		}

		return author;
	}

	// 本のIDを入力
	public int inputBookId() {

		int bookId = -1;

		while (bookId <= 0) {
			System.out.print("操作する本のIDを入力してください：");
			String input = scan.nextLine();
			bookId = inputCheck(input);
		}
		return bookId;
	}
}
