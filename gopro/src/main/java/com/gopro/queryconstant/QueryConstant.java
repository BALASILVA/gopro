package com.gopro.queryconstant;

public interface QueryConstant {

	public static final String INVOICE_SEARCH_QUERY = "SELECT inv.invoiceId,inv.customerMobileNo,inv.noOfProduct,inv.totalPrice, inv.paymentType, inv.date,inv.shopId,inv.userId,inv.userName,inv.remarks FROM Invoice inv WHERE inv.invoiceId  = Ifnull(:invoiceId, inv.invoiceId) AND Upper(inv.customerMobileNo) = Upper(Ifnull(:customerMobileNo, inv.customerMobileNo)) AND Upper(inv.noOfProduct)= Upper(Ifnull(:noOfProduct, inv.noOfProduct)) AND inv.totalPrice BETWEEN Ifnull(:startPrice, inv.totalPrice) AND Ifnull(:endPrice, inv.totalPrice) AND ((date >= :fromDate OR :fromDate IS NULL) AND (date <= :toDate OR :toDate IS NULL) ) AND Upper(inv.paymentType) = Upper(Ifnull(:paymentType, inv.paymentType)) AND Upper(inv.userName) = Upper(Ifnull(:username, inv.userName)) AND Concat_ws(inv.invoiceId,inv.customerMobileNo,inv.noOfProduct, inv.totalPrice, inv.date, inv.paymentType, inv.userName) LIKE :searchKeyWord AND inv.shopId = :shopId";
}
