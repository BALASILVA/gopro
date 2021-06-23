package com.gopro.service;

import java.text.Collator;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.gopro.AuthendicationFacade.AuthendicationFacade;
import com.gopro.bene.Customer;
import com.gopro.bene.Invoice;
import com.gopro.bene.InvoiceProductMap;
import com.gopro.bene.SearchCredentialDTO;
import com.gopro.bene.User;
import com.gopro.exception.domain.UserNotFoundException;
import com.gopro.repository.InvoiceRepo;

@Service
public class InvoiceServiceImpl implements InvoiceService {

	private InvoiceRepo invoiceRepo;
	private AuthendicationFacade authendicationFacade;
	private UserService userService;
	private InvoiceProductService invoiceProductService;
	private CustomerService customerService;
	@PersistenceContext
  	private EntityManager em;
	  
	@Autowired
	public InvoiceServiceImpl(InvoiceRepo invoiceRepo, @Lazy AuthendicationFacade authendicationFacade,
			UserService userService, InvoiceProductService invoiceProductService,CustomerService customerService) {
		this.invoiceRepo = invoiceRepo;
		this.authendicationFacade = authendicationFacade;
		this.userService = userService;
		this.invoiceProductService = invoiceProductService;
		this.customerService = customerService;
	}

	@Override
	public Invoice add(Invoice invoice) {
		 User user = authendicationFacade.getCurrentUserDetails();
		/*User user = new User();
		try {
			user = userService.findUserById((long) 1);
		} catch (UserNotFoundException e) {
		}
		 */
		if (invoice.getCustomer().getCustomerMobileNo() == 0) {
			invoice.setCustomer(null);
		}
		
		invoice.getInvoiceProductMap().parallelStream().forEach(
				(obj -> obj.setTotalPriceOfProduct( obj.getNoOfProduct() * obj.getPricePerItem() ))
				);
		
		Double totalPrice = invoice.getInvoiceProductMap().parallelStream()
				  .map(x -> x.getTotalPriceOfProduct())
				  .reduce( 0D, Double::sum);
		
        invoice.setTotalPrice(totalPrice);
		
		if(invoice.getCustomer()!=null) {
			Customer customer = customerService.findByCustomerMobileNo(invoice.getCustomer().getCustomerMobileNo());
			if(customer!=null)
				invoice.setCustomer(customer);
			else
				invoice.getCustomer().setCustomerId(null);
			
			invoice.setCustomerMobileNo(invoice.getCustomer().getCustomerMobileNo());
		}

		invoice.setShopId(user.getDefaultShopId());
		invoice.setUserName(user.getFirstName());
		invoice.setUserId(user.getId());
		invoice.setDate(new Date());
		
		return invoiceRepo.save(invoice);

	}

	@Override
	public Invoice getInvoiceDetailById(Invoice invoice) {
		List<InvoiceProductMap> productList = invoiceProductService.getInvoiceDetailByInvoiceId(invoice.getInvoiceId());
		invoice.setInvoiceProductMap(productList);
		return invoice;
	}

	@Override
	public Page<Invoice> getAllInvoicePaginationAndSorting(SearchCredentialDTO searchCredentialDTO) {
		// User logedInUser = authendicationFacade.getCurrentUserDetails();
		User logedInUser = new User();
		try {
			logedInUser = userService.findUserById((long) 1);
		} catch (UserNotFoundException e) {
		}

		searchCredentialDTO.setShopId(logedInUser.getDefaultShopId());

		Sort sort = null;
		if (StringUtils.isEmpty(searchCredentialDTO.getShortBy())) {
			searchCredentialDTO.setShortBy("customerMobileNo");
		}

		sort = Sort.by(searchCredentialDTO.getShortBy());
		if (StringUtils.isNotEmpty(searchCredentialDTO.getShortOrderAscOrDsc())) {
			if (searchCredentialDTO.getShortOrderAscOrDsc().equalsIgnoreCase("DESC")) {
				sort = sort.descending();
			} else if (searchCredentialDTO.getShortOrderAscOrDsc().equalsIgnoreCase("ASC")) {
				sort = sort.ascending();
			}
			else {
				sort = sort.descending();
			}
		}
		else {
			sort = sort.descending();
		}
		Pageable pageable = null;


		if (sort != null) {
			System.out.println("inside");
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
			return invoiceRepo.findAllOfInvoice(
					searchCredentialDTO.getInvoiceId(),
					searchCredentialDTO.getCustomerMobileNo(),
					searchCredentialDTO.getNoOfProduct(),
					searchCredentialDTO.getStartPrice(),
					searchCredentialDTO.getEndPrice(),
					searchCredentialDTO.getFromDate(),
					searchCredentialDTO.getToDate(),
					searchCredentialDTO.getPaymentType(), 
					searchCredentialDTO.getUsername(),
					searchCredentialDTO.getSearchKeyWord(), 
					searchCredentialDTO.getShopId() == 0 ? null : searchCredentialDTO.getShopId(),
					 pageable); 
					
		} catch (Exception ex) {
			System.out.println("Exception" + ex);
			return null;
		}

	}

}
