import java.util.Scanner;

import controller.BookController;
import controller.UserController;
import repository.BookRepository;
import repository.UserRepository;
import service.BookService;
import service.UserService;
import util.PasswordHasher;
import view.BookView;
import view.Menu;
import view.UserView;

public class Main {

	public static void main(String[] args) {

		boolean isBoot = true;

		Scanner scan = new Scanner(System.in);
		PasswordHasher passwordhasher = new PasswordHasher();

		// User
		UserRepository userRepository = new UserRepository();
		UserService userService = new UserService(userRepository, passwordhasher);
		UserController userController = new UserController(userService);

		// Book
		BookRepository bookRepository = new BookRepository();
		BookService bookService = new BookService(bookRepository);
		BookController bookController = new BookController(bookService);

		// Menu
		Menu menu = new Menu(scan);
		UserView userView = new UserView(scan, menu, userController);

		while (isBoot) {

			// ログイン、新規登録
			userView.showLoginMenu();

			// ログイン後
			while (userView.getLoginUser() != null) {

				int userId = userView.getLoginUser().getUserId();

				BookView bookView = new BookView(
						scan,
						menu,
						bookController,
						userId);

				int choice = menu.mainMenu();
				switch (choice) {

				// User
				case 1:
					userView.showUserMenu();
					break;

				// Book
				case 2:
					bookView.showBookMenu();
					break;

				// アプリ終了
				case 0:
					System.out.println("アプリを終了します。");
					isBoot = false;
					return;

				// 他の値が入力された場合
				default:
					System.out.print("正しい数字を入力してください。");
					System.out.println();
				}
			}
		}
	}
}