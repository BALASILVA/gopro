package com.gopro.service;

import java.util.Date;
import java.util.InputMismatchException;
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
public class ProductServiceImpl implements ProductService {

	ProductRepo productRepo;
	AuthendicationFacade authenticationFacade;

	@Autowired
	public ProductServiceImpl(ProductRepo productRepo, @Lazy AuthendicationFacade authenticationFacade) {
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
		
		User logedInUser = authenticationFacade.getCurrentUserDetails();
		searchCredentialDTO.setShopId(logedInUser.getDefaultShopId());
		
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
		
		Product productValidateBarcode = productRepo.findBarCodeAvailable(user.getDefaultShopId(),product.getBarCode());
		
		if(productValidateBarcode != null)
		{
			throw new InputMismatchException("Bar Code Value Already Registerd For "+productValidateBarcode.getProductName());
		}
		
		
		Shop shop = new Shop();
		shop.setShopId(user.getDefaultShopId());
		product.setShop(shop);

		// Default Product Settings
		product.setTotalSale((long) 0);
		
		if(StringUtils.isNotEmpty(product.getBarCode()))
		{
			product.setIsSysGenBarCode("N");
		}
		else
		{
			String barCodeId = "PRODUCT"+new Date().getTime();
			product.setBarCode(barCodeId);
			product.setSysGenBarCodeBakeUp(barCodeId);
			product.setIsSysGenBarCode("Y");
			
		}
		
		Product savedProduct = productRepo.save(product);
		return savedProduct;
	}

	@Override
	public Product updateProduct(Product product) {				
		
		// Get The product details from data base by id		
		Product productCopy = getProductById(product.getProductId());

		if(productCopy == null)
			throw new InputMismatchException("Failed To Update, Product Not Found");
		
		//Check Bar Code is Valid -- Validate duplicate
		Product productValidateBarcode = productRepo.findBarCodeAvailable(productCopy.getShop().getShopId(),productCopy.getProductId(),product.getBarCode());
		if(productValidateBarcode != null)
		{
			throw new InputMismatchException("Bar Code Value Already Registerd For "+productValidateBarcode.getProductName());
		}

		if(StringUtils.isBlank(product.getBarCode()))
		{
			if(StringUtils.isNotBlank(productCopy.getSysGenBarCodeBakeUp())) {
				productCopy.setBarCode(productCopy.getSysGenBarCodeBakeUp());			
				productCopy.setIsSysGenBarCode("Y");
				productCopy.setSysGenBarCodeBakeUp(productCopy.getSysGenBarCodeBakeUp());
			}
			else
			{
				String barCodeId = "PRODUCT"+new Date().getTime();
				productCopy.setBarCode(barCodeId);
				productCopy.setSysGenBarCodeBakeUp(barCodeId);
				productCopy.setIsSysGenBarCode("Y");
			}
		}
		else {
			//Security reason
			productCopy.setBarCode(product.getBarCode());
			productCopy.setSysGenBarCodeBakeUp(productCopy.getSysGenBarCodeBakeUp());			
		}
		
		// update only required parameter and save
		productCopy.setProductName(product.getProductName());		
		productCopy.setMrpPrice(product.getMrpPrice());
		productCopy.setSellingprice(product.getSellingprice());
		productCopy.setAvailableStock(product.getAvailableStock());
		productCopy.setRemarks(product.getRemarks());
				
		return productRepo.save(productCopy);
	}

	@Override
	public List<Product> getAllProductsByDefaultShopId() {
		User logedInUser = authenticationFacade.getCurrentUserDetails();
		Long shopId = logedInUser.getDefaultShopId();
		System.out.println(shopId);
		return productRepo.getAllProductsByShopId(shopId);
	}
}
