package entity;

import java.time.OffsetDateTime;

public class WishListEntity {
	private int id;
	private int userId;
	private int wishId;
	private String wishText;
	private OffsetDateTime createdAt;

	public WishListEntity() {
	}

	public WishListEntity(
			int userId,
			String wishText) {
		this.userId = userId;
		this.wishText = wishText;

	}

	public WishListEntity(
			int id,
			int userId,
			int wishId,
			String wishText) {
		this.id = id;
		this.userId = userId;
		this.wishId = wishId;
		this.wishText = wishText;
	}

	public int getId() {
		return id;
	}

	public int getUserId() {
		return userId;
	}

	public int getWishId() {
		return wishId;
	}

	public String getWishText() {
		return wishText;
	}

	public OffsetDateTime getCreatedAt() {
		return createdAt;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setWishId(int wishId) {
		this.wishId = wishId;
	}

	public void setWishText(String text) {
		this.wishText = text;
	}

}