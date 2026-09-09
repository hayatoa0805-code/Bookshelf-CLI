package view;

import java.util.List;
import java.util.Scanner;

import entity.BookEntity;

public class BookView {

	static Scanner scan = new Scanner(System.in);

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
		System.out.print("編集する項目を選択してください：");
		int bookId = Integer.parseInt(scan.nextLine());
		return bookId;
	}

	// メニューに戻る導線
	public void returnMenu() {
		while (true) {
			System.out.print("メニューに戻りますか？[y]：");
			String input = scan.nextLine();
			if ("y".equals(input)) {
				break;
			}
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
	public BookEntity saveBook(int userId) {
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
		int bookId = Integer.parseInt(scan.nextLine());
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
