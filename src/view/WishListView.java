package view;

import java.util.List;
import java.util.Scanner;

import controller.BookController;
import controller.WishListController;
import entity.BookEntity;
import entity.WishListEntity;
import util.InputUtil;

public class WishListView {
	private Scanner scan;

	private int userId;
	private WishListController wishListController;
	private BookView bookView;
	private BookController bookController;
	private InputUtil inputUtil;

	String[] wishListItems = {
			"一覧表示",
			"本を追加",
			"購入済みに登録",
			"削除"
	};

	public WishListView(
			Scanner scan,
			WishListController wishListController,
			BookView bookView,
			BookController bookController,
			InputUtil inputUtil,
			int userId) {
		this.scan = scan;
		this.wishListController = wishListController;
		this.bookView = bookView;
		this.bookController = bookController;
		this.inputUtil = inputUtil;
		this.userId = userId;
	}

	public int wishListMenu() {
		System.out.println("===欲しい本リストメニュー===");

		for (int i = 0; i < wishListItems.length; i++) {
			System.out.println((i + 1) + ". " + wishListItems[i]);
		}

		System.out.println();
		System.out.println("0. 戻る");
		System.out.print("選択してください：");
		String input = inputUtil.inputMenuChoice();
		return inputUtil.inputCheck(input);
	}

	public void displayWishList(List<WishListEntity> wishList) {
		System.out.println("===欲しい本リスト一覧===");

		// 本が登録されていなかった場合
		if (wishList.isEmpty()) {
			System.out.println();
			System.out.println("本が登録されていません。");
			System.out.println();
			System.out.println("=======================");
			return;
		}

		// 本が登録されている場合
		System.out.println();
		for (WishListEntity wishBook : wishList) {
			System.out.println("ID: " + wishBook.getWishId() + ", " + wishBook.getWishText());
		}
	}

	public WishListEntity registerBook(int userId) {
		System.out.println("===登録===");
		System.out.println();

		System.out.print("テキスト：");
		String wishText = scan.nextLine();

		return new WishListEntity(
				userId,
				wishText);
	}

	public void showWishListMenu() {
		while (true) {
			int input = wishListMenu();

			switch (input) {

			// 一覧表示
			case 1:
				List<WishListEntity> wishList = wishListController.findByUserId(userId);
				displayWishList(wishList);

				while (true) {
					System.out.print("yを入力するとメニューに戻ります：");
					String backInput = scan.nextLine();

					if ("y".equalsIgnoreCase(backInput)) {
						break;
					}
				}
				break;

			// 本の追加
			case 2:
				WishListEntity addBook = registerBook(userId);
				wishListController.registerBook(userId, addBook);
				break;

			// 本棚に登録
			case 3:
				int bookId = inputUtil.inputBookId();
				wishListController.deleteBook(userId, bookId);
				BookEntity registerBook = bookView.registerBook(userId);
				bookController.registerBook(userId, registerBook);
				break;

			//本の削除
			case 4:
				int deleteBookId = inputUtil.inputBookId();
				wishListController.deleteBook(userId, deleteBookId);
				break;

			case 0:
				System.out.println("アプリを終了します。");
				return;

			default:
				System.out.println("正しい番号を入力してください。");
				break;
			}

		}

	}
}
