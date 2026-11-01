package controller;

import java.util.List;

import entity.WishListEntity;
import service.WishListService;

public class WishListController {
	private WishListService wishListService;

	WishListController(WishListService wishListService) {
		this.wishListService = wishListService;
	}

	public void registerBook(int userId, WishListEntity book) {
		wishListService.registerBook(userId, book);
	}

	public List<WishListEntity> findByUserId(int userId) {
		return wishListService.findByUserId(userId);
	}

	public void deleteBook(int userId, int wishId) {
		wishListService.deleteBook(userId, wishId);
	}
}
