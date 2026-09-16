package repository;

import java.util.ArrayList;
import java.util.List;

import entity.UserEntity;

public class UserRepository {

	private List<UserEntity> users = new ArrayList<UserEntity>();

	// ユーザー登録
	public void register(UserEntity user) {
		user.setId(users.size() + 1); // IDを自動で設定
		users.add(user);
	}

	// 　メールアドレスからユーザーを取得
	public UserEntity findByEmail(String email) {
		for (UserEntity user : users) {
			if (user.getEmail().equals(email)) {
				return user;
			}
		}

		return null;
	}

	// ユーザー削除
	public void delete(int userId) {
		users.removeIf(user -> user.getId() == userId);
	}

}