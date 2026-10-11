package service;

import java.util.List;

import entity.WishListEntity;
import repository.WishListRepository;

public class WishListService {
	WishListRepository wishListRepository;

	// コンストラクタでWishListRepositoryを受け取る
	public WishListService(WishListRepository wishListRepository) {
		this.wishListRepository = wishListRepository;
	}

	// 欲しい本リストに本の登録
	public void registerBook(int userId, WishListEntity book) {
		wishListRepository.registerBook(userId, book);
	}

	// ユーザー自身の登録してる欲しい本リストの本の一覧を取得
	public List<WishListEntity> findByUserId(int userId) {
		return wishListRepository.findByUserId(userId);
	}

	// 削除
	public void deleteBook(int userId, int wishId) {
		wishListRepository.deleteBook(userId, wishId);
	}
}
