package com.gopro.service;

import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.gopro.AuthendicationFacade.AuthendicationFacade;
import com.gopro.bene.Product;
import com.gopro.bene.SearchCredentialDTO;
import com.gopro.bene.Shop;
import com.gopro.bene.User;
import com.gopro.repository.ProductRepo;

@Service
public class ProducServiceImpl implements ProductService {

	ProductRepo productRepo;
	AuthendicationFacade authenticationFacade;

	@Autowired
	public ProducServiceImpl(ProductRepo productRepo, @Lazy AuthendicationFacade authenticationFacade) {
		super();
		this.productRepo = productRepo;
		this.authenticationFacade = authenticationFacade;
	}

	@Override
	public List<Product> getProductList() {
		return null;
	}

	@Override
	public Page<Product> getProductPaginationAndSearching(SearchCredentialDTO searchCredentialDTO) {

		Sort sort = null;
		if (StringUtils.isEmpty(searchCredentialDTO.getShortBy())) {
			searchCredentialDTO.setShortBy("productid");
		}

		sort = Sort.by(searchCredentialDTO.getShortBy());
		if (StringUtils.isNotEmpty(searchCredentialDTO.getShortOrderAscOrDsc())) {
			if (searchCredentialDTO.getShortOrderAscOrDsc().equalsIgnoreCase("DESC")) {
				sort = sort.descending();
			} else if (searchCredentialDTO.getShortOrderAscOrDsc().equalsIgnoreCase("ASC")) {
				sort = sort.ascending();
			} else {
				sort = sort.ascending();
			}
		}

		Pageable pageable = null;

		if (sort != null) {
			pageable = PageRequest.of(searchCredentialDTO.getPage(), searchCredentialDTO.getSize(), sort);
		} else {
			pageable = PageRequest.of(searchCredentialDTO.getPage(), searchCredentialDTO.getSize());
		}
		if (StringUtils.isEmpty(searchCredentialDTO.getSearchKeyWord())) {
			searchCredentialDTO.setSearchKeyWord("");
		}
		searchCredentialDTO.setSearchKeyWord("%" + searchCredentialDTO.getSearchKeyWord() + "%");
		System.out.println(searchCredentialDTO);
		try {
			return productRepo.findAllPagination(searchCredentialDTO.getProductId(),
					searchCredentialDTO.getProductName(), searchCredentialDTO.getStartPrice(),
					searchCredentialDTO.getEndPrice(), searchCredentialDTO.getStartAvailableStock(),
					searchCredentialDTO.getEndAvailableStock(), searchCredentialDTO.getSearchKeyWord(),
					searchCredentialDTO.getShopId(), pageable);
		} catch (Exception ex) {
			System.out.println("Exception" + ex);
			return null;
		}
	}

	public Product getProductById(Long productId) {
		Optional<Product> result = productRepo.findById(productId);
		return result.orElse(null);
	}

	public Product addNewProduct(Product product) {
		User user = authenticationFacade.getCurrentUserDetails();
		Shop shop = new Shop();
		shop.setShopId(user.getDefaultShopId());
		product.setShop(shop);

		// Default Product Settings
		product.setTotalSale((long) 0);
		
		Product savedProduct = productRepo.save(product);
		return savedProduct;
	}

	@Override
	public Product updateProduct(Product product) {
		// Get The product details from data base by id
		Product productCopy = getProductById(product.getProductId());

		// update only required parameter and save
		productCopy.setProductName(product.getProductName());
		productCopy.setPrice(product.getPrice());
		productCopy.setAvailableStock(product.getAvailableStock());
		productCopy.setRemarks(product.getRemarks());

		return productRepo.save(productCopy);
	}
}
