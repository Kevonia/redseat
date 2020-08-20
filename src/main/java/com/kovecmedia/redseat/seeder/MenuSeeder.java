package com.kovecmedia.redseat.seeder;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.kovecmedia.redseat.doa.MenuRepository;
import com.kovecmedia.redseat.doa.RoleRepository;
import com.kovecmedia.redseat.entity.Menu;

@Component
public class MenuSeeder {
	private Logger logger = Logger.getLogger(this.getClass());
	@Autowired
	private MenuRepository menuRepository;

	@Autowired
	private RoleRepository roleRepository;

	public void run() {
		try {
			List<Menu> list = new ArrayList<>();

			NumberFormat formatter = new DecimalFormat("#0.00000");
			long start = System.currentTimeMillis();

			Menu menu = new Menu();
			Menu menu1 = new Menu();
			Menu menu2 = new Menu();

			menu.setName("user-nav");
			menu.setRole(roleRepository.getOne((long) 1));
			menu1.setName("admin-nav");
			menu1.setRole(roleRepository.getOne((long) 3));
			menu2.setName("staff-nav");
			menu2.setRole(roleRepository.getOne((long) 2));

			list.add(menu);
			list.add(menu1);
			list.add(menu2);

			long end = System.currentTimeMillis();
			menuRepository.saveAll(list);
			logger.info("Menu  Seeder ran in " + formatter.format((end - start) / 1000d) + " seconds");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
