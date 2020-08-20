package com.kovecmedia.redseat.doa;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kovecmedia.redseat.entity.MenuItems;

public interface MenuItemRepository extends JpaRepository<MenuItems, Long>  { 

}
