package com.kovecmedia.redseat.service;

import java.util.List;
import java.util.Set;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;

import com.kovecmedia.redseat.entity.User;
import com.kovecmedia.redseat.payload.request.ResetRequest;
import com.kovecmedia.redseat.payload.request.UserRegistration;
import com.kovecmedia.redseat.payload.respond.UserPackages;
import com.kovecmedia.redseat.entity.Role;

public interface UserService {

	public User getUser(long id);

	public List<User> getAllUsers();

	public UserPackages getUserPakages(long id);

	UserDetails loadUserByEmail(String email) throws UsernameNotFoundException;

	Boolean existsByEmail(String email);

	@Transactional
	void addUser(UserRegistration userRegistration, Set<Role> roles,String papiKey) throws Exception;

	@Transactional
	void resetPassword(String email) throws UsernameNotFoundException;

	boolean checkToken(String token);
	
	void UpdatePassword(ResetRequest request);
	
	User UpdateProfile(User user);

}
