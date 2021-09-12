package com.gopro.service;

import static com.gopro.constant.SearchGridConstant.DEFAULT_USER_MODULE_PAGE_SIZE;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Stream;

import javax.persistence.NoResultException;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.gopro.AuthendicationFacade.AuthendicationFacade;
import com.gopro.bene.Product;
import com.gopro.bene.SearchCredentialDTO;
import com.gopro.bene.Shop;
import com.gopro.bene.User;
import com.gopro.constant.ExceptionMessage;
import com.gopro.repository.ShopRepository;
import com.gopro.repository.UserRepository;

@Service
public class ShopServiceImpl implements ShopService {
	
	private ShopRepository shopRepository;
	private UserService userService;
	AuthendicationFacade authenticationFacade;
	
	@Autowired
	public ShopServiceImpl(ShopRepository shopRepository,@Lazy AuthendicationFacade authenticationFacade,@Lazy UserService userService) {		
		this.shopRepository = shopRepository;
		this.authenticationFacade = authenticationFacade;
		this.userService  = userService;
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
	public Page<Shop> getAllShopByParentUserId(SearchCredentialDTO searchCredentialDTO) {		
		User loginUser = authenticationFacade.getCurrentUserDetails();
		System.out.println(searchCredentialDTO);
		Long parentUserId = 0L;
		if(loginUser.getParentUserId() == 0)		
			parentUserId = loginUser.getId(); 
		else
			parentUserId = loginUser.getParentUserId();
		
		
		Date todayWithTime = new Date();
		Date todayDate = new Date();
		try {
			DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
			todayDate = formatter.parse(formatter.format(todayWithTime));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		Pageable pageable = null;
		
		if(searchCredentialDTO.getSize() == 0)
		{			
			searchCredentialDTO.setSize(DEFAULT_USER_MODULE_PAGE_SIZE);
		}
		
	
		pageable = PageRequest.of(searchCredentialDTO.getPage(), searchCredentialDTO.getSize());
		
		if (StringUtils.isEmpty(searchCredentialDTO.getSearchKeyWord())) {
			searchCredentialDTO.setSearchKeyWord("");
		}
		searchCredentialDTO.setSearchKeyWord("%" + searchCredentialDTO.getSearchKeyWord() + "%");		
		
		try {
			Page<Shop> list =  shopRepository.findAllShopByParentUserId(parentUserId,					
					searchCredentialDTO.getSearchKeyWord(), pageable);
			for (Shop shop : list) {
				Long todaySale = shopRepository.findTodaySale(shop.getShopId(),todayDate);
				if( todaySale == null)
					shop.setTodaySale(0L);
				else
					shop.setTodaySale(todaySale);
				
				if(shop.getTotalSale()==null)
					shop.setTotalSale(0L);
			}
			return list;
		} catch (Exception ex) {
			System.out.println("Exception" + ex);
			return null;
		}
		
	}
	
	@Override
	public List<Shop> getAllShopNameByParentUserId() {	
		
		User loginUser = authenticationFacade.getCurrentUserDetails();		
		Long parentUserId = 0L;
		if(loginUser.getParentUserId() == 0)		
			parentUserId = loginUser.getId(); 
		else
			parentUserId = loginUser.getParentUserId();
		
		List<Shop> list =  shopRepository.findAllShopByParentUserId(parentUserId);
		
		return list;
	}
	
	@Override
	public List<Shop> getAllShopByParentUserId(Long id) {
		return shopRepository.getAllShopByParentUserId(id);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public Shop save(Shop shop) {
		try {
			User loginUser = authenticationFacade.getCurrentUserDetails();
			Long parentUserId = 0L;
			if(loginUser.getParentUserId() == 0)		
				parentUserId = loginUser.getId(); 
			else
				parentUserId = loginUser.getParentUserId();
			
			shop.setUserId(parentUserId);
			Shop shopSaved = shopRepository.save(shop);
			List<User> userList = userService.getAdminANDManagerUser(parentUserId);
			for (User user : userList) {				
				int count = shopRepository.addShopUserMapping(user.getId(),shopSaved.getShopId());
			}
			return shopSaved;
		}
		catch(Exception ex)
		{
			throw new NoResultException(ExceptionMessage.TECNICAL_ERROR_OCCURED);			
		}
	}
	
	@Override
	public Shop saveWithOurUserId(Shop shop) {		
		return shopRepository.save(shop);
	}

	@Override
	public boolean updateUserId(Long shopId, Long id) {
		shopRepository.updateUserId(shopId,id);
		return true;
	}

	@Override
	public Shop findById(Long shopId) {			
		return shopRepository.findShopByShopId(shopId);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public Shop update(Shop shop) {
		
		User loginUser = authenticationFacade.getCurrentUserDetails();
		Long parentUserId = 0L;
		if (loginUser.getParentUserId() == 0)
			parentUserId = loginUser.getId();
		else
			parentUserId = loginUser.getParentUserId();

		int update = shopRepository.updateShopDetail(shop.getShopName(), shop.getPhone(), shop.getEmail(),
				shop.getAddressLineOne(), shop.getShopId(), parentUserId);
		return shop;
	}



}
