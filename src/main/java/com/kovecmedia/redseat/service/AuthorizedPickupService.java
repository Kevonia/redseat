package com.kovecmedia.redseat.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.kovecmedia.redseat.entity.AuthorizedPickup;
import com.kovecmedia.redseat.entity.User;
import com.kovecmedia.redseat.payload.request.authorizedUsersRequest;


public interface AuthorizedPickupService {
	@Transactional
	void addUPickupUser(authorizedUsersRequest authorizedPickup) throws Exception;
	
	
	List<AuthorizedPickup> getAllByUser(User user) throws Exception;
	
	List<AuthorizedPickup> removdById(long id,User user) throws Exception;
}
