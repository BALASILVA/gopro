package com.gopro.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.gopro.bene.Shop;

public interface ShopRepository extends JpaRepository<Shop, Long> {

	Shop findShopByShopId(Long shopId);

	@Query(value="select * from shop shp inner join user_shop_list map on map.shop_list_shopid = shp.shopid WHERE map.user_id=:userid",nativeQuery = true)
	List<Shop> getAllShopByParentUserId(@Param(value = "userid")Long id);

}
