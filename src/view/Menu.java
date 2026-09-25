package view;

import util.InputUtil;

public class Menu {
	private InputUtil inputUtil;

	public Menu(
			InputUtil inputUtil) {
		this.inputUtil = inputUtil;
	}

	// アプリ起動して、最初に表示するメニュー
	String[] mainItems = {
			"ユーザー管理",
			"本の管理",
	};

	public int mainMenu() {

		while (true) {
			System.out.println("===メインメニュー===");
			for (int i = 0; i < mainItems.length; i++) {
				System.out.println((i + 1) + ". " + mainItems[i]);
			}
			System.out.println();
			System.out.println("0. アプリ終了");
			System.out.print("選択してください：");

			String num = inputUtil.inputMenuChoice();

			int choice = inputUtil.inputCheck(num);

			if (0 <= choice && choice <= mainItems.length) {
				return choice;
			}

		}
	}
}