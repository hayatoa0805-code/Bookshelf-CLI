package entity;

import java.time.OffsetDateTime;

public class UserEntity {
	private int id;
	private String name;
	private String email;
	private String passwordHash;
	private OffsetDateTime created_at;
	private OffsetDateTime deleted_at;
	private boolean isActive;

	public UserEntity() {
		// TODO 自動生成されたコンストラクター・スタブ
	}

	public UserEntity(String name, String email, String passwordHash) {

		this.name = name;
		this.email = email;
		this.passwordHash = passwordHash;
		this.isActive = true;
	}

	public int getUserId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getEmail() {
		return email;
	}

	public String getPasswordHash() {
		return passwordHash;
	}

	public OffsetDateTime getCreated_at() {
		return created_at;
	}

	public OffsetDateTime getDeleted_at() {
		return deleted_at;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setUserId(int userId) {
		this.id = userId;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setPasswordHash(String passwordHash) {
		this.passwordHash = passwordHash;
	}

	public void setCreated_at(OffsetDateTime created_at) {
		this.created_at = created_at;
	}

	public void setDeleted_at(OffsetDateTime deleted_at) {
		this.deleted_at = deleted_at;
	}

	public void setActive(boolean active) {
		isActive = active;
	}
}