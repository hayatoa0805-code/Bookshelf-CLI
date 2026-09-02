package entity;

import java.time.OffsetDateTime;

public class UserEntity {
	private static int nextId = 1;
	private int id;
	private String name;
	private String email;
	private String passwordHash;
	private OffsetDateTime created_at;
	private OffsetDateTime deleted_at;
	private boolean isActive;

	public UserEntity(String name, String email, String passwordHash) {
		this.id = nextId++;
		this.name = name;
		this.email = email;
		this.passwordHash = passwordHash;
		this.isActive = true;
	}

	public static int getNextId() {
		return nextId;
	}

	public int getId() {
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

}