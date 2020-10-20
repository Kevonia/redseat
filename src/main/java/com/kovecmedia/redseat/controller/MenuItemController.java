package com.kovecmedia.redseat.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kovecmedia.redseat.entity.MenuItems;
import com.kovecmedia.redseat.service.MenuItemService;

@RestController
@RequestMapping("api/menuitem")
public class MenuItemController {

	
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private MenuItemService menuItemService;

	@GetMapping(value = "/{id}", produces = "application/json")
	public List<MenuItems> getById(@PathVariable long id) {

		List<MenuItems> menulist = new ArrayList<>();

		try {
			menulist = menuItemService.getByMenu(id);
		} catch (Exception ex) {
			ex.printStackTrace();

		}

		return menulist;
	}

}
