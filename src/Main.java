import java.sql.Connection;
import java.util.List;
import java.util.Scanner;

import controller.BookController;
import db.DatabaseConnection;
import entity.BookEntity;
import repository.BookRepository;
import service.BookService;
import view.BookView;
import view.Menu;

public class Main {

	public static void main(String[] args) {
		try (Connection connection = DatabaseConnection.getConnection()) {

			System.out.println("PostgreSQLへの接続成功");

		} catch (Exception e) {
			e.printStackTrace();
		}

		Scanner scan = new Scanner(System.in);

		BookEntity bookEntity = new BookEntity();
		BookRepository bookRepository = new BookRepository();
		BookService bookService = new BookService(bookRepository);
		BookController bookController = new BookController(bookService);
		BookView bookView = new BookView();
		Menu menu = new Menu();

		// 仮のユーザーID
		int userId = 1;
		// 仮の本ID
		BookEntity book = new BookEntity(userId, "test", "1", "publisher", "author");
		bookController.registerBook(userId, book);

		while (true) {

			int choice = menu.bookMenu();

			switch (choice) {

			// 一覧表示
			case 1:
				List<BookEntity> books = bookController.getBooks(userId);

				bookView.displayBooks(books);
				System.out.print("詳細を見たい本のIDまたはyを入力してください：");
				String input = scan.nextLine();

				// 機能メニューに戻る
				if ("y".equals(input)) {
					break;
				}
				// 本の詳細表示
				else {
					int bookId = Integer.parseInt(input);
					BookEntity detailbook = bookController.getBook(userId, bookId);
					bookView.detailBook(detailbook);
				}
				break;

			// 本の検索
			case 2: {
				while (true) {
					// 検索する項目を選択
					int searchItem = bookView.searchBookView();
					switch (searchItem) {

					// タイトル検索
					case 1: {
						String title = bookView.inputTitle();
						List<BookEntity> titleBooks = bookController.searchByTitle(userId, title);
						bookView.displayBooks(titleBooks);

						bookView.returnMenu();
						break;
					}

					// 出版社検索
					case 2: {
						String publisher = bookView.inputPublisher();
						List<BookEntity> publisherBooks = bookController.searchByPublisher(userId, publisher);
						bookView.displayBooks(publisherBooks);

						bookView.returnMenu();
						break;
					}

					// 著者検索
					case 3: {
						String author = bookView.inputAuthor();
						List<BookEntity> authorBooks = bookController.searchByAuthor(userId, author);
						bookView.displayBooks(authorBooks);

						bookView.returnMenu();
						break;
					}

					default:
						System.out.println("正しい番号を入力してください：");
					}
					break;
				}
				break;
			}

			// 本の登録
			case 3:
				BookEntity addBook = bookView.saveBook(userId);
				bookController.registerBook(userId, addBook);
				System.out.println("本を登録しました。");
				break;

			// 本の編集
			case 4:
				int editBookId = bookView.inputEditBookId();
				BookEntity editBook = bookService.findByUserIdAndBookId(userId, editBookId);

				// 編集する項目を選択
				int editItem = bookView.editBookView();

				switch (editItem) {
				// タイトル
				case 1: {
					String title = bookView.inputTitle();
					editBook.setTitle(title);
					break;
				}
				// 巻数
				case 2: {
					String volume = bookView.inputVolume();
					editBook.setVolume(volume);
					break;
				}
				// 出版社
				case 3: {

					break;
				}
				// 著者
				case 4: {

					break;
				}

				default:
					System.out.println("正しい番号を入力してください。");
				}

				break;

			//　本の削除
			case 5:
				int deleteBookId = bookView.inputDeleteBookId();

				bookController.deleteBook(userId, deleteBookId);

				System.out.println("本を削除しました。");
				break;

			// 終了
			case 0:
				System.out.println("アプリを終了します。");
				scan.close();
				return;

			default:
				System.out.println("正しい番号を入力してください。");
			}
		}
	}
}