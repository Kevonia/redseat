package com.kovecmedia.redseat.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;

import com.kovecmedia.redseat.entity.User;
import com.kovecmedia.redseat.payload.request.UserRegistration;
import com.kovecmedia.redseat.payload.respond.UserPackages;

public interface UserService {

	public User getUser(long id);

	public List<User> getAllUsers();

	public UserPackages getUserPaakages(long id);

	UserDetails loadUserByEmail(String email) throws UsernameNotFoundException;

	@Transactional
	void Adduser(UserRegistration userRegistration);

}
