package com.kovecmedia.redseat.service;

import java.util.List;
import org.springframework.transaction.annotation.Transactional;

import com.kovecmedia.redseat.entity.User;
import com.kovecmedia.redseat.model.UserPackages;
import com.kovecmedia.redseat.model.UserRegistration;

public interface UserService   {

	public User getUser(long id);

	public List<User> getAllUsers();
	
   public UserPackages getUserPaakages(long id);
	
	@Transactional
	void save(UserRegistration userRegistration);



}
