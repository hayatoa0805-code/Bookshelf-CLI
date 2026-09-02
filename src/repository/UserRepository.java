package repository;

import java.util.ArrayList;
import java.util.List;

import entity.UserEntity;

public class UserRepository {

	private List<UserEntity> users = new ArrayList<UserEntity>();

	// ユーザー登録
	public void save(UserEntity user) {
		users.add(user);
	}

	// 　メールアドレスからユーザーを登録
	public UserEntity findByEmail(String email) {
		for (UserEntity user : users) {
			if (user.getEmail().equals(email)) {
				return user;
			}
		}

		return null;
	}

	// ユーザー削除
	public void delete(UserEntity user) {
		users.remove(user);
	}

}