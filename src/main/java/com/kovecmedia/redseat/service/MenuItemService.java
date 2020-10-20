package com.kovecmedia.redseat.service;

import java.util.List;

import com.kovecmedia.redseat.entity.MenuItems;

public interface MenuItemService{

	public MenuItems getByid(Long id);
	
	public List<MenuItems> getByMenu(Long id);
}
