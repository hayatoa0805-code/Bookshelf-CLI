package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import db.DatabaseConnection;
import entity.WishListEntity;

public class WishListRepository {

	// 欲しい本リストに本の登録
	public void registerBook(int userId, WishListEntity book) {

		String sql = """
				INSERT INTO wish_lists (user_id, wish_text)
				VALUES(?, ?)
				RETURNING id, wish_id
				""";

		try (
				Connection connection = DatabaseConnection.getConnection();
				PreparedStatement statement = connection.prepareStatement(
						sql)) {
			statement.setInt(1, userId);
			statement.setString(2, book.getWishText());

			try (ResultSet resultSet = statement.executeQuery()) {

				if (resultSet.next()) {

					int id = resultSet.getInt("id");
					int wishId = resultSet.getInt("wish_id");

					book.setId(id);
					book.setWishId(wishId);
				}

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// ユーザー自身の登録してる欲しい本リストの本の一覧を取得
	public List<WishListEntity> findByUserId(int userId) {
		List<WishListEntity> result = new ArrayList<>();

		String sql = """
				SELECT wish_id, wish_text
				FROM wish_lists
				WHERE user_id = ?
				""";

		try (
				Connection connection = DatabaseConnection.getConnection();
				PreparedStatement statement = connection.prepareStatement(sql)) {

			statement.setInt(1, userId);

			try (ResultSet resultSet = statement.executeQuery()) {

				while (resultSet.next()) {

					WishListEntity book = new WishListEntity();

					book.setWishId(resultSet.getInt("wish_id"));
					book.setWishText(resultSet.getString("wish_text"));

					result.add(book);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;
	}

	// 削除
	public void deleteBook(int userId, int wishId) {

		String sql = """
				DELETE FROM wish_lists
				WHERE user_id = ?
				AND wish_id = ?
				""";

		try (
				Connection connection = DatabaseConnection.getConnection();
				PreparedStatement statement = connection.prepareStatement(sql)) {

			statement.setInt(1, userId);
			statement.setInt(2, wishId);

			statement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}