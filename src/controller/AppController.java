package controller;

import java.util.Scanner;

import repository.BookRepository;
import repository.UserRepository;
import service.BookService;
import service.UserService;
import util.InputUtil;
import util.PasswordHasher;
import view.BookView;
import view.Menu;
import view.UserView;

public class AppController {

	private Scanner scan;
	private Menu menu;
	private UserView userView;
	private BookController bookController;
	private InputUtil inputUtil;

	public AppController() {

		scan = new Scanner(System.in);

		PasswordHasher passwordHasher = new PasswordHasher();
		this.inputUtil = new InputUtil(scan, menu);

		// User
		UserRepository userRepository = new UserRepository();
		UserService userService = new UserService(userRepository, passwordHasher);
		UserController userController = new UserController(userService);

		// Book
		BookRepository bookRepository = new BookRepository();
		BookService bookService = new BookService(bookRepository);
		bookController = new BookController(bookService);

		// Menu
		menu = new Menu(inputUtil);
		userView = new UserView(
				scan,
				menu,
				userController,
				inputUtil);
	}

	public void start() {

		boolean isBoot = true;

		while (isBoot) {

			userView.showLoginMenu();

			while (userView.getLoginUser() != null) {

				int userId = userView.getLoginUser().getUserId();

				BookView bookView = new BookView(
						scan,
						menu,
						bookController,
						inputUtil,
						userId);

				int choice = menu.mainMenu();

				switch (choice) {

				case 1:
					userView.showUserMenu();
					break;

				case 2:
					bookView.showBookMenu();
					break;

				case 0:
					System.out.println("アプリを終了します。");
					isBoot = false;
					break;

				default:
					System.out.println("正しい数字を入力してください。");
				}
			}
		}
	}
}