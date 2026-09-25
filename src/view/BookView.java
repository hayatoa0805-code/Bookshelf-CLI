package view;

import java.util.List;
import java.util.Scanner;

import controller.BookController;
import entity.BookEntity;
import util.InputUtil;

public class BookView {
	private Scanner scan;

	private int userId;
	private BookController bookController;
	private InputUtil inputUtil;

	// book機能のメニュー
	String[] bookItems = {
			"一覧表示",
			"検索",
			"登録",
			"編集",
			"削除"
	};

	//検索できる項目のリスト
	static String[] searchItems = {
			"タイトル",
			"出版社",
			"著者",
	};

	// 編集できる項目のリスト
	static String[] editItems = {
			"タイトル",
			"巻数",
			"出版社",
			"著者",
	};

	// コンストラクタ
	public BookView(
			Scanner scan,
			Menu menu,
			BookController bookController,
			InputUtil inputUtil,
			int userId) {

		this.scan = scan;
		this.bookController = bookController;
		this.inputUtil = inputUtil;
		this.userId = userId;
	}

	// Bookに関する表示
	public int bookMenu() {
		System.out.println("===ブックメニュー===");
		for (int i = 0; i < bookItems.length; i++) {
			System.out.println((i + 1) + ". " + bookItems[i]);
		}
		System.out.println();
		System.out.println("0. 戻る");
		System.out.print("選択してください：");
		String input = inputUtil.inputMenuChoice();
		return inputUtil.inputCheck(input);
	}

	// 検索する項目表示
	public int searchMenu() {
		for (int i = 0; i < searchItems.length; i++) {
			System.out.println((i + 1) + ". " + searchItems[i]);
		}
		System.out.println();
		System.out.println("0. 戻る");
		System.out.print("検索する項目をえらんでください：");
		String input = inputUtil.inputMenuChoice();

		return inputUtil.inputCheck(input);
	}

	// 編集する項目表示
	public int editBookView() {
		for (int i = 0; i < editItems.length; i++) {
			System.out.println((i + 1) + ". " + editItems[i]);
		}

		System.out.println();
		System.out.println("0. 戻る");
		System.out.print("編集する項目をえらんでください：");
		String bookId = inputUtil.inputMenuChoice();

		return inputUtil.inputCheck(bookId);
	}

	// 本が登録されていなかった場合の出力
	public void nullDisplay() {
		System.out.println();
		System.out.println("指定された本はありません。");
		System.out.println();
	}

	public void searchBook() {

		// 検索する項目を選択
		int searchItem = searchMenu();

		while (true) {
			switch (searchItem) {

			// タイトル検索
			case 1: {
				String title = inputUtil.inputTitle();
				List<BookEntity> titleBooks = bookController.searchByTitle(userId, title);
				displayBooks(titleBooks);
				break;
			}

			// 出版社検索
			case 2: {
				String publisher = inputUtil.inputPublisher();
				List<BookEntity> publisherBooks = bookController.searchByPublisher(userId, publisher);
				displayBooks(publisherBooks);

				break;
			}

			// 著者検索
			case 3: {
				String author = inputUtil.inputAuthor();
				List<BookEntity> authorBooks = bookController.searchByAuthor(userId, author);
				displayBooks(authorBooks);

				break;
			}

			default:
				System.out.println("正しい番号を入力してください：");
				break;
			}
			break;
		}
	}

	// 本の編集処理
	private void editBook(int editBookId) {

		while (true) {

			// 編集する項目を選択
			int editItem = editBookView();

			switch (editItem) {

			// タイトル
			case 1: {
				String title = inputUtil.inputTitle();
				bookController.editTitle(userId, editBookId, title);
				break;
			}

			// 巻数
			case 2: {
				String volume = inputUtil.inputVolume();
				bookController.editVolume(userId, editBookId, volume);
				break;
			}

			// 出版社
			case 3: {
				String publisher = inputUtil.inputPublisher();
				bookController.editPublisher(userId, editBookId, publisher);
				break;
			}

			// 著者
			case 4: {
				String author = inputUtil.inputAuthor();
				bookController.editAuthor(userId, editBookId, author);
				break;
			}

			case 0:
				return;

			default:
				System.out.println("正しい番号を入力してください。");
				break;
			}
		}
	}

	// 指定の本のIDがあるか判定
	public BookEntity isBook(int bookId) {
		// 入力が正の整数ではなかった場合
		if (bookId < 0) {
			System.out.println("正しい本のIDを入力してください。");
			return null;
		}
		BookEntity detailBook = bookController.findByUserIdAndBookId(userId, bookId);

		// 指定の本が見つからなかった場合
		if (detailBook == null) {
			System.out.println("指定した本が見つかりません。");
			return null;
		}

		return detailBook;
	}

	// 本の一覧表示
	public void displayBooks(List<BookEntity> books) {

		System.out.println("========本の一覧========");

		// 本が登録されていなかった場合
		if (books.isEmpty()) {
			System.out.println();
			System.out.println("本が登録されていません。");
			System.out.println();
			System.out.println("=======================");
			return;
		}

		// 本が登録されている場合
		System.out.println();
		for (BookEntity book : books) {
			System.out.println("ID：" + book.getBookId() + "　タイトル：" + book.getTitle());
		}
		System.out.println();
		System.out.println("=======================");

	}

	// 本の詳細表示
	public void detailBook(BookEntity book) {

		if (book == null) {

			// 本がない場合
			nullDisplay();
		} else {

			// 本がある場合
			System.out.println("-------詳細------");
			System.out.println("　I　D　：" + book.getId());
			System.out.println("タイトル：" + book.getTitle());
			System.out.println("巻　　数：" + book.getVolume());
			System.out.println("出 版 社：" + book.getPublisher());
			System.out.println("著　　者：" + book.getAuthor());
			System.out.println("----------------");
		}

		boolean back = false;
		while (!back) {
			System.out.print("yを入力するとメニューに戻ります：");
			String returnMenu = scan.nextLine();
			if ("y".equals(returnMenu)) {
				back = true;
				break;
			}
		}
	}

	// 本の登録に必要な情報を入力
	public BookEntity registerBook(int userId) {
		System.out.println("==本の登録==");
		System.out.println();

		System.out.print("タイトル：");
		String title = scan.nextLine();

		System.out.print("巻数：");
		String volume = scan.nextLine();

		System.out.print("出版社：");
		String publisher = scan.nextLine();

		System.out.print("著者：");
		String author = scan.nextLine();

		return new BookEntity(
				userId,
				userId, userId, title,
				volume,
				publisher,
				author);
	}

	public void showBookMenu() {

		while (true) {
			int input = bookMenu();

			switch (input) {

			// 一覧表示
			case 1:
				List<BookEntity> books = bookController.findByUserId(userId);
				int bookId = -1;

				displayBooks(books);
				while (bookId <= 0) {
					System.out.print("詳細を見たい本のIDまたはyを入力してください：");
					String seachBookId = scan.nextLine();

					// 機能メニューに戻る
					if ("y".equals(seachBookId)) {
						break;
					}
					// 本の詳細表示
					else {
						// 入力を整数に変換
						bookId = inputUtil.inputCheck(seachBookId);
						BookEntity book = isBook(bookId);

						if (book != null) {
							detailBook(book);
						}
					}
				}

				break;

			// 本の検索
			case 2: {
				searchBook();
				break;
			}

			// 本の登録
			case 3:
				BookEntity addBook = registerBook(userId);
				bookController.registerBook(userId, addBook);
				System.out.println("本を登録しました。");
				break;

			// 本の編集
			case 4:
				int editBookId = inputUtil.inputBookId();
				editBook(editBookId);
				break;

			// 本の削除
			case 5:
				int deleteBookId = inputUtil.inputBookId();

				bookController.deleteBook(userId, deleteBookId);

				System.out.println("本を削除しました。");
				break;

			// 終了
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