package com.kovecmedia.redseat.seeder;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.kovecmedia.redseat.doa.MenuItemRepository;
import com.kovecmedia.redseat.doa.MenuRepository;
import com.kovecmedia.redseat.entity.MenuItems;

@Component
public class MenuItemSeeder {
	
	private Logger logger = Logger.getLogger(this.getClass());
	@Autowired
	private MenuRepository menuRepository;

	@Autowired
	private MenuItemRepository menuItemRepository;
	
	public void run() {
		try {
			List<MenuItems> list = new ArrayList<>();

			NumberFormat formatter = new DecimalFormat("#0.00000");
			long start = System.currentTimeMillis();

			MenuItems menuItems = new MenuItems();
			MenuItems menuItems1 = new MenuItems();
			MenuItems menuItems2 = new MenuItems();

			menuItems.setName("home");
			menuItems.setParentId(0);
			menuItems.setIcon("fas fa-tachometer-alt");
			menuItems.setPath("/home");
			menuItems.setMenu(menuRepository.getOne((long) 1));
			
			menuItems1.setName("home");
			menuItems1.setParentId(0);
			menuItems1.setIcon("fas fa-tachometer-alt");
			menuItems1.setPath("/staff/home");
			menuItems1.setMenu(menuRepository.getOne((long) 3));
			
			
			menuItems2.setName("home");
			menuItems2.setParentId(0);
			menuItems2.setIcon("fas fa-tachometer-alt");
			menuItems2.setPath("/admin/home");
			menuItems2.setMenu(menuRepository.getOne((long) 2));
			
			list.add(menuItems);
			list.add(menuItems1);
			list.add(menuItems2);

			long end = System.currentTimeMillis();
			menuItemRepository.saveAll(list);
			logger.info("Menu Item Seeder ran in " + formatter.format((end - start) / 1000d) + " seconds");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

}
