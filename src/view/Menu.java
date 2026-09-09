package view;

import java.util.Scanner;

public class Menu {
	static Scanner scan = new Scanner(System.in);
	String[] mainMenu = {
			"本の管理",
			"ユーザー管理",
	};

	String[] bookMenu = {
			"本の一覧表示",
			"本の検索",
			"本を登録",
			"本の編集",
			"本を削除",
	};

	public int mainMenu() {
		for (int i = 0; i < 2; i++) {
			System.out.println((i + 1) + ". " + new Menu().mainMenu[i]);
		}

		System.out.println();
		while (true) {
			System.out.print("選択してください：");
			int num = scan.nextInt();
			return num;
		}
	}

	public int bookMenu() {
		for (int i = 0; i < 5; i++) {
			System.out.println((i + 1) + ". " + new Menu().bookMenu[i]);
		}
		System.out.println();
		System.out.println("0. メニューに戻る");
		System.out.print("選択してください：");
		int num = scan.nextInt();
		return num;
	}

}