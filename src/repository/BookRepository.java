package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import db.DatabaseConnection;
import entity.BookEntity;

public class BookRepository {

	// 本の登録
	public void registerBook(int userId, BookEntity book) {

		String sql = """
				INSERT INTO books(user_id, book_id, title, volume, publisher, author)
				VALUES(?, ?, ?, ?, ?, ?)
				""";

		try (
				Connection connection = DatabaseConnection.getConnection();
				PreparedStatement statement = connection.prepareStatement(
						sql,
						java.sql.Statement.RETURN_GENERATED_KEYS)) {

			statement.setInt(1, userId);
			statement.setInt(2, book.getBookId());
			statement.setString(3, book.getTitle());
			statement.setString(4, book.getVolume());
			statement.setString(5, book.getPublisher());
			statement.setString(6, book.getAuthor());

			statement.executeUpdate();

			try (java.sql.ResultSet resultSet = statement.getGeneratedKeys()) {
				if (resultSet.next()) {
					int id = resultSet.getInt(1);
					book.setId(id);
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// ユーザー自身の登録してる本の一覧を取得
	public List<BookEntity> findByUserId(int userId) {
		List<BookEntity> result = new ArrayList<>();

		String sql = """
				SELECT book_id, title
				FROM books
				WHERE user_id = ?
				""";

		try (
				Connection connection = DatabaseConnection.getConnection();
				PreparedStatement statement = connection.prepareStatement(sql)) {

			statement.setInt(1, userId);

			try (ResultSet resultSet = statement.executeQuery()) {

				while (resultSet.next()) {

					BookEntity book = new BookEntity();

					book.setBookId(resultSet.getInt("book_id"));
					book.setTitle(resultSet.getString("title"));

					result.add(book);
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;
	}

	// ユーザー自身が登録してる本の詳細情報を取得
	public BookEntity findByUserIdAndBookId(int userId, int bookId) {
		String sql = """
				SELECT id, user_id, book_id, title, volume, publisher, author
				FROM books
				WHERE user_id = ?
				AND book_id = ?
				""";

		try (
				Connection connection = DatabaseConnection.getConnection();
				PreparedStatement statement = connection.prepareStatement(sql)) {

			statement.setInt(1, userId);
			statement.setInt(2, bookId);

			try (ResultSet resultSet = statement.executeQuery()) {

				if (resultSet.next()) {

					BookEntity book = new BookEntity();

					book.setId(resultSet.getInt("id"));
					book.setUserId(resultSet.getInt("user_id"));
					book.setBookId(resultSet.getInt("book_id"));
					book.setTitle(resultSet.getString("title"));
					book.setVolume(resultSet.getString("volume"));
					book.setPublisher(resultSet.getString("publisher"));
					book.setAuthor(resultSet.getString("author"));

					return book;

				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	// ユーザー自身が登録してる本のタイトルを編集
	public void editTitle(int userId, int bookId, String title) {

		String sql = """
				UPDATE books
				SET
					title = ?,
					updated_at = now()
				WHERE user_id = ?
				AND book_id = ?
				""";

		try (
				Connection connection = DatabaseConnection.getConnection();
				PreparedStatement statement = connection.prepareStatement(sql)) {

			statement.setString(1, title);
			statement.setInt(2, userId);
			statement.setInt(3, bookId);

			statement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	// ユーザー自身が登録してる本の巻数を編集
	public void editVolume(int userId, int bookId, String volume) {

		String sql = """
				UPDATE books
				SET
					volume = ?,
					updated_at = now()
				WHERE user_id = ?
				AND book_id = ?
				""";

		try (
				Connection connection = DatabaseConnection.getConnection();
				PreparedStatement statement = connection.prepareStatement(sql)) {

			statement.setString(1, volume);
			statement.setInt(2, userId);
			statement.setInt(3, bookId);

			statement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// ユーザー自身が登録してる本の出版社を編集
	public void editPublisher(int userId, int bookId, String publisher) {

		String sql = """
				UPDATE books
				SET
					publisher = ?,
					updated_at = now()
				WHERE user_id = ?
				AND book_id = ?
				""";

		try (
				Connection connection = DatabaseConnection.getConnection();
				PreparedStatement statement = connection.prepareStatement(sql)) {

			statement.setString(1, publisher);
			statement.setInt(2, userId);
			statement.setInt(3, bookId);

			statement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// ユーザー自身が登録してる本の著者を編集
	public void editAuthor(int userId, int bookId, String author) {

		String sql = """
				UPDATE books
				SET
					author = ?,
					updated_at = now()
				WHERE user_id = ?
				AND book_id = ?
				""";

		try (
				Connection connection = DatabaseConnection.getConnection();
				PreparedStatement statement = connection.prepareStatement(sql)) {

			statement.setString(1, author);
			statement.setInt(2, userId);
			statement.setInt(3, bookId);

			statement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// ユーザー自身が登録してる本をタイトルで検索
	public List<BookEntity> searchByTitle(int userId, String title) {

		List<BookEntity> books = new ArrayList<>();

		String sql = """
				SELECT id, user_id, book_id, title, volume, publisher, author
				FROM books
				WHERE user_id = ?
				AND title LIKE ?
				""";

		try (
				Connection connection = DatabaseConnection.getConnection();
				PreparedStatement statement = connection.prepareStatement(sql)) {

			statement.setInt(1, userId);
			statement.setString(2, "%" + title + "%");

			try (ResultSet resultSet = statement.executeQuery()) {

				while (resultSet.next()) {

					BookEntity book = new BookEntity(
							resultSet.getInt("id"),
							resultSet.getInt("user_id"),
							resultSet.getInt("book_id"),
							resultSet.getString("title"),
							resultSet.getString("volume"),
							resultSet.getString("publisher"),
							resultSet.getString("author"));

					books.add(book);
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return books;
	}

	// ユーザー自身が登録してる本を出版社で検索
	public List<BookEntity> searchByPublisher(int userId, String publisher) {

		List<BookEntity> books = new ArrayList<>();

		String sql = """
				SELECT id, user_id, book_id, title, volume, publisher, author
				FROM books
				WHERE user_id = ?
				AND publisher LIKE ?
				""";

		try (
				Connection connection = DatabaseConnection.getConnection();
				PreparedStatement statement = connection.prepareStatement(sql)) {

			statement.setInt(1, userId);
			statement.setString(2, "%" + publisher + "%");

			try (ResultSet resultSet = statement.executeQuery()) {

				while (resultSet.next()) {

					BookEntity book = new BookEntity(
							resultSet.getInt("id"),
							resultSet.getInt("user_id"),
							resultSet.getInt("book_id"),
							resultSet.getString("title"),
							resultSet.getString("volume"),
							resultSet.getString("publisher"),
							resultSet.getString("author"));

					books.add(book);
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return books;
	}

	// ユーザー自身が登録してる本を著者で検索
	public List<BookEntity> searchByAuthor(int userId, String author) {

		List<BookEntity> books = new ArrayList<>();

		String sql = """
				SELECT id, user_id, book_id, title, volume, publisher, author
				FROM books
				WHERE user_id = ?
				AND author LIKE ?
				""";

		try (
				Connection connection = DatabaseConnection.getConnection();
				PreparedStatement statement = connection.prepareStatement(sql)) {

			statement.setInt(1, userId);
			statement.setString(2, "%" + author + "%");

			try (ResultSet resultSet = statement.executeQuery()) {

				while (resultSet.next()) {

					BookEntity book = new BookEntity(
							resultSet.getInt("id"),
							resultSet.getInt("user_id"),
							resultSet.getInt("book_id"),
							resultSet.getString("title"),
							resultSet.getString("volume"),
							resultSet.getString("publisher"),
							resultSet.getString("author"));

					books.add(book);
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return books;
	}

	//　ユーザー自身が登録してる本を物理削除
	public void delete(int userId, int bookId) {

		String sql = """
				DELETE FROM books
				WHERE user_id = ?
				AND book_id = ?
				""";

		try (
				Connection connection = DatabaseConnection.getConnection();
				PreparedStatement statement = connection.prepareStatement(sql)) {

			statement.setInt(1, userId);
			statement.setInt(2, bookId);

			statement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}