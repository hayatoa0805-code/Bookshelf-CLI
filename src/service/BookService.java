package service;

import java.util.List;

import entity.BookEntity;
import repository.BookRepository;

public class BookService {
	private BookRepository bookRepository;

	// コンストラクタでBookRepositoryを受け取る
	public BookService(BookRepository bookRepository) {
		this.bookRepository = bookRepository;
	}

	// 本の登録
	public void registerBook(int userId, BookEntity book) {
		bookRepository.regiserBook(userId, book);
	}

	// 本の詳細情報を取得
	public BookEntity findByUserIdAndBookId(int userId, int bookId) {
		return bookRepository.findByUserIdAndBookId(userId, bookId);
	}

	// ユーザー自身が登録してる本のタイトルを編集
	public void editTitle(int userId, int bookId, String title) {
		bookRepository.editTitle(userId, bookId, title);
	}

	// ユーザー自身が登録してる本の巻数を編集
	public void editVolume(int userId, int bookId, String volume) {
		bookRepository.editVolume(userId, bookId, volume);
	}

	// ユーザー自身が登録してる本の出版社を編集
	public void editPublisher(int userId, int bookId, String publisher) {
		bookRepository.editPublisher(userId, bookId, publisher);
	}

	// ユーザー自身が登録してる本の著者を編集
	public void editAuthor(int userId, int bookId, String author) {
		bookRepository.editAuthor(userId, bookId, author);
	}

	// ユーザー自身の本の一覧を取得
	public List<BookEntity> findByUserId(int userId) {
		return bookRepository.findByUserId(userId);
	}

	// タイトル検索
	public List<BookEntity> searchByTitle(int userId, String title) {
		return bookRepository.searchByTitle(userId, title);
	}

	// 出版社検索
	public List<BookEntity> searchByPublisher(int userId, String publisher) {
		return bookRepository.searchByPublisher(userId, publisher);
	}

	// 著者検索
	public List<BookEntity> searchByAuthor(int userId, String author) {
		return bookRepository.searchByAuthor(userId, author);
	}

	// 本の削除
	public void deleteBook(int userId, int bookId) {
		bookRepository.delete(userId, bookId);
	}
}
