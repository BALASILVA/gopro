package com.gopro.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gopro.bene.Shop;
import com.gopro.repository.ShopRepository;

@Service
public class ShopServiceImpl implements ShopService {
	private ShopRepository shopRepository;

	@Autowired
	public ShopServiceImpl(ShopRepository shopRepository) {
		super();
		this.shopRepository = shopRepository;
	}

	@Override
	public List<Shop> findAllShopById(List<Shop> shopList) {
		// TODO Auto-generated method stub
		List<Shop> retunList = new ArrayList<Shop>();
		for (Shop shop : shopList) {
			retunList.add(shopRepository.findShopByShopId(shop.getShopId()));
		}
		return retunList;
	}

	@Override
	public List<Shop> getAllShopByParentUserId(Long id) {
		return shopRepository.getAllShopByParentUserId(id);
	}

	@Override
	public Shop save(Shop shop) {
		return shopRepository.save(shop);
	}

}
