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

	// ViewのdetailBook
	public BookEntity findByUserIdAndBookId(int userId, int bookId) {
		return bookService.findByUserIdAndBookId(userId, bookId);
	}

	// 本の登録
	public void registerBook(int userId, BookEntity book) {
		bookService.registerBook(userId, book);
	}

	// ユーザー自身が登録してる本のタイトルを編集
	public void editTitle(int userId, int bookId, String title) {
		bookService.editTitle(userId, bookId, title);
	}

	// ユーザー自身が登録してる本の巻数を編集
	public void editVolume(int userId, int bookId, String volume) {
		bookService.editVolume(userId, bookId, volume);
	}

	// ユーザー自身が登録してる本の出版社を編集
	public void editPublisher(int userId, int bookId, String publisher) {
		bookService.editPublisher(userId, bookId, publisher);
	}

	// ユーザー自身が登録してる本の著者を編集
	public void editAuthor(int userId, int bookId, String author) {
		bookService.editAuthor(userId, bookId, author);
	}

	// ユーザー自身の登録してる本の一覧を取得
	public List<BookEntity> findByUserId(int userId) {
		return bookService.findByUserId(userId);
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