package com.gopro.service;

import java.util.List;

import com.gopro.bene.Menu;

public interface MenuService {

	List<Menu> findMenuByUserId(Long id);

}
