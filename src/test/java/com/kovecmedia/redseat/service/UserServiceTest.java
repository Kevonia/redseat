package com.kovecmedia.redseat.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.kovecmedia.redseat.doa.UserRepository;
import com.kovecmedia.redseat.entity.User;

@SpringBootTest
class UserServiceTest {

	@Autowired
	UserRepository userRepository;

	@Test
	public void myTest() throws Exception {

		User user = new User("Kevonia", "Kevonia123@gmail.com", "Kevonia", null, null, null);

		UserService US = new UserServiceImpl();
	

	}

}
