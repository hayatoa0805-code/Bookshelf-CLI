package view;

import java.util.List;
import java.util.Scanner;

import controller.BookController;
import entity.BookEntity;

public class BookView {
	private Scanner scan;

	private int userId;
	private BookController bookController;
	private Menu menu;

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
			int userId) {

		this.scan = scan;
		this.menu = menu;
		this.bookController = bookController;
		this.userId = userId;
	}

	// Bookに関する表示
	public int bookMenu() {
		System.out.println("===ブックメニュー===");
		for (int i = 0; i < bookItems.length; i++) {
			System.out.println((i + 1) + ". " + bookItems[i]);
		}

		String input = menu.inputMenu();
		return menu.inputCheck(input);
	}

	// 検索する項目表示
	public int searchMenu() {
		for (int i = 0; i < searchItems.length; i++) {
			System.out.println((i + 1) + ". " + searchItems[i]);
		}
		String input = menu.inputMenu();

		return menu.inputCheck(input);
	}

	// 編集する項目表示
	public int editBookView() {
		for (int i = 0; i < editItems.length; i++) {
			System.out.println((i + 1) + ". " + editItems[i]);
		}

		String bookId = menu.inputMenu();

		return menu.inputCheck(bookId);
	}

	// 編集する本のIDを入力
	public int inputEditBookId() {
		int bookId = -1;
		while (bookId <= 0) {
			System.out.print("編集する本のIDを入力してください：");
			String input = scan.nextLine();
			bookId = menu.inputCheck(input);
		}
		return bookId;
	}

	// タイトル入力
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

	// 出版社入力
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

	// 削除する本のIDを入力
	public int inputDeleteBookId() {
		int bookId = -1;
		while (bookId <= 0) {
			System.out.print("削除する本のIDを入力してください：");
			String input = scan.nextLine();
			bookId = menu.inputCheck(input);
		}
		return bookId;
	}

	// 本が登録されていなかった場合の出力
	public void nullDisplay() {
		System.out.println();
		System.out.println("指定された本はありません。");
		System.out.println();
	}

	// 本の編集処理
	private void editBook(int editBookId) {

		while (true) {

			// 編集する項目を選択
			int editItem = editBookView();

			switch (editItem) {

			// タイトル
			case 1: {
				String title = inputTitle();
				bookController.editTitle(userId, editBookId, title);
				break;
			}

			// 巻数
			case 2: {
				String volume = inputVolume();
				bookController.editVolume(userId, editBookId, volume);
				break;
			}

			// 出版社
			case 3: {
				String publisher = inputPublisher();
				bookController.editPublisher(userId, editBookId, publisher);
				break;
			}

			// 著者
			case 4: {
				String author = inputAuthor();
				bookController.editAuthor(userId, editBookId, author);
				break;
			}

			case 0:
				return;

			default:
				System.out.println("正しい番号を入力してください。");
			}
		}
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

		// 本がない場合
		if (book == null) {
			nullDisplay();
			return;
		}

		// 本がある場合
		System.out.println("-------詳細------");
		System.out.println("　I　D　：" + book.getId());
		System.out.println("タイトル：" + book.getTitle());
		System.out.println("巻　　数：" + book.getVolume());
		System.out.println("出 版 社：" + book.getPublisher());
		System.out.println("著　　者：" + book.getAuthor());
		System.out.println("----------------");
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

				displayBooks(books);
				System.out.print("詳細を見たい本のIDまたはyを入力してください：");
				String seachBookId = scan.nextLine();

				// 機能メニューに戻る
				if ("y".equals(seachBookId)) {
					break;
				}
				// 本の詳細表示
				else {
					int bookId = Integer.parseInt(seachBookId);
					BookEntity detailbook = bookController.findByUserIdAndBookId(userId, bookId);
					detailBook(detailbook);
				}

				break;

			// 本の検索
			case 2: {
				// 検索する項目を選択
				int searchItem = searchMenu();
				switch (searchItem) {

				// タイトル検索
				case 1: {
					String title = inputTitle();
					List<BookEntity> titleBooks = bookController.searchByTitle(userId, title);
					displayBooks(titleBooks);

					menu.inputMenu();
					break;
				}

				// 出版社検索
				case 2: {
					String publisher = inputPublisher();
					List<BookEntity> publisherBooks = bookController.searchByPublisher(userId, publisher);
					displayBooks(publisherBooks);

					menu.inputMenu();
					break;
				}

				// 著者検索
				case 3: {
					String author = inputAuthor();
					List<BookEntity> authorBooks = bookController.searchByAuthor(userId, author);
					displayBooks(authorBooks);

					menu.inputMenu();
					break;
				}

				default:
					System.out.println("正しい番号を入力してください：");
				}
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
				int editBookId = inputEditBookId();
				editBook(editBookId);
				break;

			// 本の削除
			case 5:
				int deleteBookId = inputDeleteBookId();

				bookController.deleteBook(userId, deleteBookId);

				System.out.println("本を削除しました。");
				break;

			// 終了
			case 0:
				System.out.println("アプリを終了します。");
				return;

			default:
				System.out.println("正しい番号を入力してください。");
			}
		}
	}
}