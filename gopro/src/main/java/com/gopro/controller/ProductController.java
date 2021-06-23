package com.gopro.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gopro.AuthendicationFacade.AuthendicationFacade;
import com.gopro.bene.Product;
import com.gopro.bene.SearchCredentialDTO;
import com.gopro.bene.User;
import com.gopro.bene.UserPrincipal;
import com.gopro.service.ProductService;

@RestController
@RequestMapping(path = { "/", "/product" })
public class ProductController {
	
	private ProductService productService; 
    
    
	@Autowired
	public ProductController(ProductService productService) {
		super();
		this.productService = productService;
	}


    @PostMapping
	public Page<Product>  getAllProducts(@RequestBody SearchCredentialDTO searchCredentialDTO)
	{
		return productService.getProductPaginationAndSearching(searchCredentialDTO);
	}
    
    @GetMapping
	public List<Product>  getAllProducts()
	{
		return productService.getAllProductsByDefaultShopId();
	}
    
    @PostMapping(value="/addnew")
    public Product addNewProduct(@RequestBody Product product)
    {
    	return  productService.addNewProduct(product);
    }
    
    @PostMapping(value="/update")
    public Product updateProduct(@RequestBody Product product)
    {
    	return  productService.updateProduct(product);
    }

}
