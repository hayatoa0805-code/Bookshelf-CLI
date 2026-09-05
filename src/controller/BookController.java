package controller;

import java.util.List;

import entity.BookEntity;
import service.BookService;

public class BookController {
	private BookService bookService;

	// コンストラクタ
	public BookController(BookService bookService) {
		this.bookService = bookService;
	}

	// 本の登録
	public void registerBook(int userId, BookEntity book) {
		bookService.registerBook(userId, book);
	}

	// 本の編集
	public void update(int userId, int bookId, String title, String volume, String publisher, String author) {
		bookService.update(userId, bookId, title, volume, publisher, author);
	}

	// ユーザー自身の登録してる本の一覧を取得
	public List<BookEntity> getBooks(int userId) {
		return bookService.getBooks(userId);
	}

	// ユーザー自身が登録してる本をタイトルで検索
	public List<BookEntity> searchByTitle(int userId, String title) {
		return bookService.searchByTitle(userId, title);
	}

	// ユーザー自身が登録してる本を出版社で検索
	public List<BookEntity> searchByPublisher(int userId, String publisher) {
		return bookService.searchByPublisher(userId, publisher);
	}

	// ユーザー自身が登録している本を著者で検索
	public List<BookEntity> searchByAuthor(int userId, String author) {
		return bookService.searchByAuthor(userId, author);
	}

	// 本の削除
	public void deleteBook(int userId, int bookId) {
		bookService.deleteBook(userId, bookId);
	}
}