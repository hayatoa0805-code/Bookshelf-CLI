package view;

import java.util.Scanner;

public class Menu {
	private Scanner scan;

	public Menu(Scanner scan) {
		this.scan = scan;
	}

	// アプリ起動して、最初に表示するメニュー
	String[] mainMenu = {
			"ユーザー管理",
			"本の管理",
	};

	// book機能のメニュー
	String[] bookMenu = {
			"本の一覧表示",
			"本の検索",
			"本を登録",
			"本の編集",
			"本を削除",
	};

	public int mainMenu() {

		while (true) {
			System.out.println("===メインメニュー===");
			for (int i = 0; i < 2; i++) {
				System.out.println((i + 1) + ". " + mainMenu[i]);
			}
			System.out.println();
			System.out.println("0. アプリ終了");
			System.out.print("選択してください：");

			String num = scan.nextLine();

			int choice = inputCheck(num);

			if (0 <= choice && choice <= mainMenu.length) {
				return choice;
			}

		}
	}

	public String bookMenu() {
		for (int i = 0; i < 5; i++) {
			System.out.println((i + 1) + ". " + bookMenu[i]);
		}
		return returnMenu();
	}

	public int inputCheck(String input) {
		try {
			return Integer.parseInt(input);
		} catch (NumberFormatException e) {
			return -1;
		}
	}

	public String returnMenu() {
		System.out.println();
		System.out.println("0. 戻る");
		System.out.print("選択してください：");
		String input = scan.nextLine();

		return input;
	}

}