package com.gopro.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gopro.bene.Shop;

public interface ShopRepository extends JpaRepository<Shop, Long> {

	Shop findShopByShopId(Long shopId);

}
