package com.gopro.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gopro.bene.Menu;
import com.gopro.repository.MenuRepo;

@Service
public class MenuServiceImpl implements MenuService{
	
	MenuRepo renurepo;
	
	
	@Autowired
	public MenuServiceImpl(MenuRepo renurepo) {		
		this.renurepo = renurepo;
	}



	@Override
	public List<Menu> findMenuByUserId(Long userId) {
		return renurepo.findMenuByUserId(userId);
	}

}
