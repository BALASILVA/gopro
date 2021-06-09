package com.gopro.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gopro.bene.Shop;
import com.gopro.service.ShopService;

@RestController
@RequestMapping(path = { "/", "/shop" })
public class ShopController {

	private ShopService shopService;

	@Autowired
	public ShopController(ShopService shopService) {
		this.shopService = shopService;
	}

	public List<Shop> getAllShopByParentUserId(Long id)
	{
		shopService.getAllShopByParentUserId(id);
		return null;
	}
}
