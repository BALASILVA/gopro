package com.gopro.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.gopro.bene.Product;
import com.gopro.bene.SearchCredentialDTO;
import com.gopro.bene.Shop;

public interface ShopService {
	
	Shop save(Shop shop);
	 
	Shop saveWithOurUserId(Shop shop);

	List<Shop> findAllShopById(List<Shop> shopList);
	
	List<Shop> getAllShopByParentUserId(Long id);

	Page<Shop> getAllShopByParentUserId(SearchCredentialDTO searchCredentialDTO);

	boolean updateUserId(Long shopId, Long id);

	Shop findById(Long shopId);

	Shop update(Shop shop);

	List<Shop> getAllShopNameByParentUserId();

}
