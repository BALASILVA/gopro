package com.gopro.service;

import java.util.List;

import com.gopro.bene.Shop;

public interface ShopService {
	
	Shop save(Shop shop);

	List<Shop> findAllShopById(List<Shop> shopList);
	
	List<Shop> getAllShopByParentUserId(Long id);

}
