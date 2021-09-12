package com.gopro.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.gopro.bene.Shop;

public interface ShopRepository extends JpaRepository<Shop, Long> {

	Shop findShopByShopId(Long shopId);

	@Query(value = "select * from shop shp inner join user_shop_list map on map.shop_list_shopid = shp.shopid WHERE map.user_id=:userid", nativeQuery = true)
	List<Shop> getAllShopByParentUserId(@Param(value = "userid") Long userid);

	@Query(value = "select * from shop shp inner join user_shop_list map on map.shop_list_shopid = shp.shopid WHERE map.user_id=:userid", nativeQuery = true)
	List<Shop> findAllShopByParentUserId(@Param(value = "userid") Long userid);
	
	//@Query( name = "findAllShop",nativeQuery = true)
	@Query(value = "SELECT * FROM shop WHERE userid = :parentUserId AND Concat_ws(shopid, shopname, totalsale, phone) LIKE :searchKeyWord",nativeQuery = true)
	Page<Shop> findAllShopByParentUserId(@Param(value = "parentUserId") Long parentUserId,
			@Param(value = "searchKeyWord") String searchKeyWord,Pageable pageable);
	
	@Modifying
	@Query(value = "update shop set userid=:userId where shopId = :shopId", nativeQuery = true)
	int updateUserId(@Param("shopId")Long shopId,@Param("userId") Long id);

	@Modifying
	@Query(value = "INSERT into user_shop_list (user_id,shop_list_shopid)VALUES(:userId,:shopId)", nativeQuery = true)
	int addShopUserMapping(@Param(value = "userId")Long id,@Param(value = "shopId") Long shopId);

	@Query(value = "select SUM(totalprice) FROM   INVOICE where shopid=:shopId AND date >= :date", nativeQuery = true)
	Long findTodaySale(@Param(value = "shopId")Long shopId,@Param(value = "date") Date todayDate);

	@Modifying
	@Query(value = "update shop set shopName=:shopName,phone=:phone,email=:email ,addressLineOne=:addressLineOne where shopId = :shopId AND userId = :parentUserId", nativeQuery = true)
	int updateShopDetail(@Param(value = "shopName")String shopName,@Param(value = "phone") String phone,@Param(value = "email") String email,@Param(value = "addressLineOne") String addressLineOne,@Param(value="shopId")Long shopId,@Param(value="parentUserId") Long parentUserId );
	
	
	
}
