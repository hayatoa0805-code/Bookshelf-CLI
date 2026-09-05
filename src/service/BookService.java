package service;

import java.util.List;

import entity.BookEntity;
import repository.BookRepository;

public class BookService {
	private BookRepository bookRepository;

	public BookService(BookRepository bookRepository) {
		this.bookRepository = bookRepository;
	}

	// 本の登録
	public void registerBook(int userId, BookEntity book) {
		bookRepository.save(userId, book);
	}

	public void update(int userId, int bookId, String title, String volume, String publisher, String author) {
		bookRepository.update(userId, bookId, title, volume, publisher, author);
	}

	// ユーザー自身の本の一覧を取得
	public List<BookEntity> getBooks(int userId) {
		return bookRepository.findByUserId(userId);
	}

	// タイトル検索
	public List<BookEntity> searchByTitle(int userId, String title) {
		return bookRepository.findByTitle(userId, title);
	}

	// 出版社検索
	public List<BookEntity> searchByPublisher(int userId, String publisher) {
		return bookRepository.findByPublisher(userId, publisher);
	}

	// 著者検索
	public List<BookEntity> searchByAuthor(int userId, String author) {
		return bookRepository.findByAuthor(userId, author);
	}

	// 本の削除
	public void deleteBook(int userId, int bookId) {
		bookRepository.delete(userId, bookId);
	}
}
