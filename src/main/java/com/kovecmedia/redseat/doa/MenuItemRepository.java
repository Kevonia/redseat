package com.kovecmedia.redseat.doa;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kovecmedia.redseat.entity.Menu;
import com.kovecmedia.redseat.entity.MenuItems;

public interface MenuItemRepository extends JpaRepository<MenuItems, Long>  { 

	
	List<MenuItems> findByMenu(Menu menu);
}
