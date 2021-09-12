package com.gopro.controller;

import static org.springframework.http.HttpStatus.OK;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gopro.bene.Notification;
import com.gopro.bene.Product;
import com.gopro.bene.SearchCredentialDTO;
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
	
    @PostMapping(value="/addnew")
    public Shop addNew(@RequestBody Shop shop)
    {
    	return  shopService.save(shop);
    }
    
    @PostMapping(value="/update")
    public Shop update(@RequestBody Shop shop)
    {
    	return  shopService.update(shop);
    }
    
	@PostMapping
	public Page<Shop> getAllShop(@RequestBody SearchCredentialDTO searchCredentialDTO)
	{
		return shopService.getAllShopByParentUserId(searchCredentialDTO);		
	}
	
	@GetMapping(value = "/name")
	public List<Shop> getAllShopName()
	{
		return shopService.getAllShopNameByParentUserId();		
	}
	
	@PostMapping(value="/findById")
	public ResponseEntity<Shop> findById(@RequestBody Long shopId) {
		Shop shop =  shopService.findById(shopId);
		return new ResponseEntity<>(shop,OK);
	}
}
