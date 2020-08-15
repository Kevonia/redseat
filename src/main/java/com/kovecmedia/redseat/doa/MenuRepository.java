package com.kovecmedia.redseat.doa;

import org.springframework.data.jpa.repository.JpaRepository;


import com.kovecmedia.redseat.entity.Menu;

public interface MenuRepository  extends JpaRepository<Menu, Long> { 

}
