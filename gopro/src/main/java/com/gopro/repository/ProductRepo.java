package com.gopro.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.gopro.bene.Product;

public interface ProductRepo extends PagingAndSortingRepository<Product, Long> {

	@Query("SELECT p FROM Product p where CONCAT(p.productId,p.availableStock,p.sellingprice,p.productName,p.totalSale) LIKE %?1% AND p.shop.shopId=?2")
	public Page<Product> findAll(String searchKey, Long shopId, Pageable pageable);

	@Query(value = "SELECT * FROM   product P WHERE  P.productid = Ifnull(:productId, P.productid) AND Upper(P.productname) = Upper(Ifnull(:productName, P.productname)) AND P.sellingprice BETWEEN Ifnull(:startPrice, P.sellingprice) AND Ifnull(:endPrice, P.sellingprice) AND P.availablestock BETWEEN Ifnull(:startAvailableStock, P.availablestock) AND Ifnull(:endAvailableStock, P.availablestock) AND CONCAT_WS(P.productid,P.productname,P.sellingprice,P.totalsale,P.availablestock) LIKE :searchKeyWord AND P.shopid=:shopId", nativeQuery = true)
	public Page<Product> findAllPagination(@Param("productId") Long productId, @Param("productName") String productName,
			@Param("startPrice") Double startPrice, @Param("endPrice") Double endPrice,
			@Param("startAvailableStock") Long startAvailableStock, @Param("endAvailableStock") Long endAvailableStock,
			@Param("searchKeyWord") String searchKeyWord, @Param("shopId") Long shopId, Pageable pageable);

    @Query(value="SELECT * FROM   product WHERE shopid = :shopId", nativeQuery=true)
	public List<Product> getAllProductsByShopId(@Param("shopId")Long shopId);

    @Query(value="SELECT * FROM product WHERE shopId = :shopId AND productid <> :productId AND barcode = :barCode LIMIT 1", nativeQuery=true)
	public Product findBarCodeAvailable(@Param("shopId")Long shopId,@Param("productId") Long productId,@Param("barCode") String barCode);

    @Query(value="SELECT * FROM product WHERE shopId = :shopId AND barcode = :barCode LIMIT 1", nativeQuery=true)
	public Product findBarCodeAvailable(@Param("shopId")Long defaultShopId,@Param("barCode") String barCode);

	
}
