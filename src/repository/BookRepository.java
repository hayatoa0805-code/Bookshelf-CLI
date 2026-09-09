package repository;

import java.util.ArrayList;
import java.util.List;

import entity.BookEntity;

public class BookRepository {

	private List<BookEntity> books = new ArrayList<>();

	// 本の登録
	public void save(int userId, BookEntity book) {
		book.setUserId(userId);
		books.add(book);
	}

	// ユーザー自身が登録してる本を取得
	public BookEntity getBook(int userId, int bookId) {
		for (BookEntity book : books) {
			if (book.getUserId() == userId &&
					book.getId() == bookId) {
				return book;
			}
		}
		return null;
	}

	// ユーザー自身の登録してる本の一覧を取得
	public List<BookEntity> findByUserId(int userId) {
		List<BookEntity> result = new ArrayList<>();

		for (BookEntity book : books) {
			if (book.getUserId() == userId) {
				result.add(book);
			}
		}

		return result;
	}

	// ユーザー自身が登録してる本の詳細情報を取得
	public BookEntity findByUserIdAndBookId(int userId, int bookId) {
		for (BookEntity book : books) {
			if (book.getUserId() == userId &&
					book.getId() == bookId) {
				return book;
			}
		}
		return null;
	}

	// ユーザー自身が登録してる本の情報を編集
	public void edit(int userId, int bookId, String title, String volume, String publisher, String aurhor) {
		for (BookEntity book : books) {
			if (book.getUserId() == userId &&
					book.getId() == bookId) {

				book.setTitle(title);
				book.setVolume(volume);
				book.setPublisher(publisher);
				book.setAuthor(aurhor);

				return;
			}
		}
	}

	// ユーザー自身が登録してる本をタイトルで検索
	public List<BookEntity> findByTitle(int userId, String title) {
		List<BookEntity> result = new ArrayList<>();

		for (BookEntity book : books) {
			if (book.getUserId() == userId
					&& book.getTitle().contains(title)) {
				result.add(book);
			}
		}
		return result;
	}

	// ユーザー自身が登録してる本を出版社で検索
	public List<BookEntity> findByPublisher(int userId, String publisher) {
		List<BookEntity> result = new ArrayList<>();

		for (BookEntity book : books) {
			if (book.getUserId() == userId
					&& book.getPublisher().contains(publisher)) {
				result.add(book);
			}
		}

		return result;
	}

	// ユーザー自身が登録してる本を著者で検索
	public List<BookEntity> findByAuthor(int userId, String author) {
		List<BookEntity> result = new ArrayList<>();

		for (BookEntity book : books) {
			if (book.getUserId() == userId
					&& book.getAuthor().contains(author)) {
				result.add(book);
			}
		}

		return result;
	}

	//　ユーザー自身が登録してる本を削除
	public void delete(int userId, int id) {
		books.removeIf(book -> book.getUserId() == userId &&
				book.getId() == id);
	}
}