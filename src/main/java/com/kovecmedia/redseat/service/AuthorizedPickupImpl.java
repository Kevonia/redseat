package com.kovecmedia.redseat.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kovecmedia.redseat.doa.AuthorizedPickupRepository;
import com.kovecmedia.redseat.doa.IDTYPERepository;
import com.kovecmedia.redseat.doa.UserRepository;
import com.kovecmedia.redseat.entity.AuthorizedPickup;
import com.kovecmedia.redseat.entity.User;
import com.kovecmedia.redseat.payload.request.authorizedUsersRequest;

@Service
public class AuthorizedPickupImpl implements AuthorizedPickupService {
	@Autowired
	private AuthorizedPickupRepository authorizedPickupRepository;

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private IDTYPERepository idtyperepository;

	@Override
	public void addUPickupUser(authorizedUsersRequest authorizedPickup) throws Exception {
		// TODO Auto-generated method stub
		AuthorizedPickup authorizedPickup1 = new AuthorizedPickup();

		User user = userRepository.getOne((long) authorizedPickup.getUserid());

		authorizedPickup1.setName(authorizedPickup.getName());
		authorizedPickup1.setIdnumber(authorizedPickup.getIdnumber());
		authorizedPickup1.setIdtype(idtyperepository.getOne((long) authorizedPickup.getIdtype()));

		authorizedPickup1.setUser(user);

		authorizedPickup1.setUpdate_by(user.getName());

		authorizedPickupRepository.save(authorizedPickup1);
	}

	@Override
	public List<AuthorizedPickup> getAllByUser(User user) throws Exception {
		// TODO Auto-generated method stub
		return authorizedPickupRepository.findByUser(user);
	}

	@Override
	public List<AuthorizedPickup> removdById(long id, User user) throws Exception {
		// TODO Auto-generated method stub
		authorizedPickupRepository.deleteById((long) id);
		return authorizedPickupRepository.findByUser(user);
	}

}
