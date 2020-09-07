package com.kovecmedia.redseat.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kovecmedia.redseat.doa.MenuItemRepository;
import com.kovecmedia.redseat.doa.MenuRepository;
import com.kovecmedia.redseat.entity.MenuItems;
@Service
public class MenuItemServiceServiceImpl  implements MenuItemService  {
	
	@Autowired
	 MenuItemRepository  menuItemRepository;
	
	
	@Autowired
	MenuRepository  menuRepository;


	@Override
	public MenuItems getByid(Long id) {
		// TODO Auto-generated method stub
		return menuItemRepository.findById(id).get();
	}

	@Override
	public List<MenuItems> getByMenu(Long id) {
		// TODO Auto-generated method stub
		return menuItemRepository.findByMenu(menuRepository.findById(id).get());
	}

}
