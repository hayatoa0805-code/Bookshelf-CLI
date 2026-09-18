package view;

import java.util.List;
import java.util.Scanner;

import controller.BookController;
import entity.BookEntity;
import repository.BookRepository;

public class BookView {
	private Scanner scan;

	private int userId;
	private BookController bookController;
	private BookRepository bookRepository;
	private Menu menu;

	// book機能のメニュー
	String[] bookMenu = {
			"一覧表示",
			"検索",
			"登録",
			"編集",
			"削除"
	};

	//検索できる項目のリスト
	static String[] searchMenu = {
			"タイトル",
			"出版社",
			"著者",
	};

	// 編集できる項目のリスト
	static String[] editMenu = {
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
			BookRepository bookRepository,
			int userId) {

		this.scan = scan;
		this.menu = menu;
		this.bookController = bookController;
		this.bookRepository = bookRepository;
		this.userId = userId;
	}

	// Bookに関する表示
	public int bookMenu() {
		System.out.println("===ブックメニュー===");
		for (int i = 0; i < bookMenu.length; i++) {
			System.out.println((i + 1) + ". " + bookMenu[i]);
		}

		String input = menu.returnMenu();
		return menu.inputCheck(input);
	}

	// 検索する項目表示
	public int searchBookView() {
		for (int i = 0; i < searchMenu.length; i++) {
			System.out.println((i + 1) + ". " + searchMenu[i]);
		}
		System.out.print("検索する項目を選択してください：");
		int num = Integer.parseInt(scan.nextLine());
		return num;
	}

	// 編集する項目表示
	public int editBookView() {
		for (int i = 0; i < editMenu.length; i++) {
			System.out.println((i + 1) + ". " + editMenu[i]);
		}

		String bookId = menu.returnMenu();

		return menu.inputCheck(bookId);
	}

	public void showBookMenu() {

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
			int searchItem = searchBookView();
			switch (searchItem) {

			// タイトル検索
			case 1: {
				String title = inputTitle();
				List<BookEntity> titleBooks = bookController.searchByTitle(userId, title);
				displayBooks(titleBooks);

				menu.returnMenu();
				break;
			}

			// 出版社検索
			case 2: {
				String publisher = inputPublisher();
				List<BookEntity> publisherBooks = bookController.searchByPublisher(userId, publisher);
				displayBooks(publisherBooks);

				menu.returnMenu();
				break;
			}

			// 著者検索
			case 3: {
				String author = inputAuthor();
				List<BookEntity> authorBooks = bookController.searchByAuthor(userId, author);
				displayBooks(authorBooks);

				menu.returnMenu();
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
			BookEntity editBook = bookController.findByUserIdAndBookId(userId, editBookId);

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

			break;

		//　本の削除
		case 5:
			int deleteBookId = inputDeleteBookId();

			bookController.deleteBook(userId, deleteBookId);

			System.out.println("本を削除しました。");
			break;

		// 終了
		case 0:
			System.out.println("アプリを終了します。");
			break;

		default:
			System.out.println("正しい番号を入力してください。");
		}

	}

	// 本が登録されているか判定
	public boolean isNull(BookEntity book) {
		return book == null;
	}

	// 検索結果が0件か判断
	public boolean isEmpty(List<BookEntity> books) {
		return books.isEmpty();
	}

	// 本が登録されていなかった場合の出力
	public void nullDisplay() {
		System.out.println();
		System.out.println("指定された本はありません。");
		System.out.println();
	}

	// 本の一覧表示
	public void displayBooks(List<BookEntity> books) {

		System.out.println("========本の一覧========");
		// 本が登録されていなかった場合
		if (isEmpty((books))) {
			nullDisplay();
			System.out.println("=======================");
			return;
		}

		// 本が登録されている場合
		System.out.println();
		for (BookEntity book : books) {
			System.out.println("ID：" + book.getId() + "　タイトル：" + book.getTitle());
		}
		System.out.println();
		System.out.println("=======================");
	}

	// 本の詳細表示
	public void detailBook(BookEntity book) {

		// 本がない場合
		if (isNull(book)) {
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
				title,
				volume,
				publisher,
				author);
	}

	// 編集する本のIDを入力
	public int inputEditBookId() {
		System.out.print("編集する本のIDを入力してください：");
		String input = scan.nextLine();
		int bookId = menu.inputCheck(input);
		return bookId;
	}

	// タイトル入力
	public String inputTitle() {
		System.out.print("タイトルを入力してください：");
		String title = scan.nextLine();
		return title;
	}

	// 本の巻数を入力
	public String inputVolume() {
		System.out.print("巻数を入力してください：");
		String volume = scan.nextLine();
		return volume;
	}

	// 出版社入力
	public String inputPublisher() {
		System.out.print("出版社を入力してください：");
		String publisher = scan.nextLine();
		return publisher;
	}

	// 著者を入力
	public String inputAuthor() {
		System.out.print("著者を入力してください");
		String author = scan.nextLine();
		return author;
	}

	// 削除する本のIDを入力
	public int inputDeleteBookId() {
		System.out.print("削除する本のIDを入力してください：");
		int bookId = Integer.parseInt(scan.nextLine());
		return bookId;
	}
}
