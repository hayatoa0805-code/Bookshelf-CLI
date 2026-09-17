package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.OffsetDateTime;

import db.DatabaseConnection;
import entity.UserEntity;

public class UserRepository {

	// ユーザー登録
	public void register(UserEntity user) {

		String sql = """
				INSERT INTO users (name, email, password_hash)
				VALUES( ?, ?, ?)
				""";

		try (
				Connection connection = DatabaseConnection.getConnection();
				PreparedStatement statement = connection.prepareStatement(
						sql,
						java.sql.Statement.RETURN_GENERATED_KEYS)) {

			statement.setString(1, user.getName());
			statement.setString(2, user.getEmail());
			statement.setString(3, user.getPasswordHash());

			statement.executeUpdate();

			try (java.sql.ResultSet resultSet = statement.getGeneratedKeys()) {

				if (resultSet.next()) {
					int userId = resultSet.getInt(1);
					user.setUserId(userId);
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// 　メールアドレスからユーザーを取得
	public UserEntity findByEmail(String email) {

		String sql = """
				SELECT id, name, email, password_hash, created_at, deleted_at, is_active
				FROM users
				WHERE email = ?
				""";

		try (
				Connection connection = DatabaseConnection.getConnection();
				PreparedStatement statement = connection.prepareStatement(sql)) {

			statement.setString(1, email);

			try (java.sql.ResultSet resultSet = statement.executeQuery()) {

				if (resultSet.next()) {
					UserEntity user = new UserEntity();

					user.setUserId(resultSet.getInt("id"));
					user.setName(resultSet.getString("name"));
					user.setEmail(resultSet.getString("email"));
					user.setPasswordHash(resultSet.getString("password_hash"));
					user.setCreated_at(
							resultSet.getObject("created_at", OffsetDateTime.class));
					user.setDeleted_at(
							resultSet.getObject("deleted_at", OffsetDateTime.class));
					user.setActive(resultSet.getBoolean("is_active"));

					return user;

				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	// ユーザー削除
	public void delete(int userId) {

		String sql = """
				DELETE FROM users
				WHERE id = ?
				""";

		try (
				Connection connection = DatabaseConnection.getConnection();
				PreparedStatement statement = connection.prepareStatement(sql)) {

			statement.setInt(1, userId);

			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}