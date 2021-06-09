package com.gopro.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.gopro.bene.Product;
import com.gopro.bene.SearchCredentialDTO;

public interface ProductService {

	List<Product> getProductList();

	Page<Product> getProductPaginationAndSearching(SearchCredentialDTO searchCredentialDTO);
	
	Product addNewProduct(Product product);

	Product updateProduct(Product product);
	
}
