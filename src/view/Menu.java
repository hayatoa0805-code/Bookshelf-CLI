package view;

import java.util.Scanner;

public class Menu {
	private Scanner scan;

	public Menu(Scanner scan) {
		this.scan = scan;
	}

	// アプリ起動して、最初に表示するメニュー
	String[] mainItems = {
			"ユーザー管理",
			"本の管理",
	};

	public int inputCheck(String input) {
		try {
			return Integer.parseInt(input);
		} catch (NumberFormatException e) {
			return -1;
		}
	}

	public String inputMenu() {
		System.out.println();
		System.out.println("0. 戻る");
		System.out.print("選択してください：");
		String input = scan.nextLine();

		return input;
	}

	public int mainMenu() {

		while (true) {
			System.out.println("===メインメニュー===");
			for (int i = 0; i < mainItems.length; i++) {
				System.out.println((i + 1) + ". " + mainItems[i]);
			}
			System.out.println();
			System.out.println("0. アプリ終了");
			System.out.print("選択してください：");

			String num = scan.nextLine();

			int choice = inputCheck(num);

			if (0 <= choice && choice <= mainItems.length) {
				return choice;
			}

		}
	}
}