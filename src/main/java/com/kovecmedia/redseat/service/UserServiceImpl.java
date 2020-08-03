package com.kovecmedia.redseat.service;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kovecmedia.redseat.doa.AddressRepository;
import com.kovecmedia.redseat.doa.ContactNumberRepository;
import com.kovecmedia.redseat.doa.CountryRepository;
import com.kovecmedia.redseat.doa.PackageRepository;
import com.kovecmedia.redseat.doa.UserRepository;
import com.kovecmedia.redseat.entity.Address;
import com.kovecmedia.redseat.entity.ContactNumber;
import com.kovecmedia.redseat.entity.Role;
import com.kovecmedia.redseat.entity.User;
import com.kovecmedia.redseat.model.Mail;
import com.kovecmedia.redseat.model.PhoneType;
import com.kovecmedia.redseat.payload.request.UserRegistration;
import com.kovecmedia.redseat.payload.respond.UserPackages;
import com.kovecmedia.redseat.security.services.UserDetailsImpl;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private AddressRepository addressRepository;

	@Autowired
	private ContactNumberRepository contactnumberepository;

	@Autowired
	private CountryRepository countryrepository;

	@Autowired
	private PackageRepository packageRepository;

	private User user = new User();

	private Address address = new Address();

	private Mail mail = new Mail();

	private ContactNumber contactNumber = new ContactNumber();

	private Set<Address> addresslist = new HashSet<Address>();

	private Set<ContactNumber> contactlist = new HashSet<ContactNumber>();

	private Set<Role> rolelist = new HashSet<Role>();

	@Override
	@Transactional
	public void Adduser(UserRegistration userRegistration, Set<Role> roles) throws Exception {

		try {
			this.address.setAddressline1(userRegistration.getAddressLine1());
			this.address.setAddressline2(userRegistration.getAddressLine2());
			this.address.setZipcode(userRegistration.getZipCode());
			this.address.setCountry(countryrepository.getOne((long) 111));

			this.addresslist.add(address);

			this.contactNumber.setIsprimary(true);
			this.contactNumber.setNumber(userRegistration.getPhone());
			this.contactNumber.setType(PhoneType.CELL);

			this.contactlist.add(contactNumber);

			this.rolelist.addAll(roles);

			this.user.setAddress(addresslist);
			this.user.setPhone(contactlist);
			this.user.setRoles(rolelist);

			this.user.setPassword(userRegistration.getPassword());
			this.user.setName(userRegistration.getName());
			this.user.setEmail(userRegistration.getEmail());
			this.addressRepository.save(address);
			this.contactnumberepository.save(contactNumber);

			this.userRepository.save(user);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new Exception("Oops, Something Went Wrong");

		}
	}

	@Override
	public User getUser(long id) {
		this.user = userRepository.getOne(id);
		return user;
	}

	@Override
	public List<User> getAllUsers() {
		// TODO Auto-generated method stub
		return userRepository.findAll();
	}

	@Override
	public UserPackages getUserPaakages(long id) {

		User tempuser = new User();
		tempuser = userRepository.getOne(id);
		UserPackages userPackages = new UserPackages();
		userPackages.setId(id);
		userPackages.setName(tempuser.getName());
		userPackages.setPacklist(packageRepository.findByUserId(userRepository.getOne((long) 3)));
		// TODO Auto-generated method stub

		return userPackages;
	}

	@Override
	@Transactional
	public UserDetails loadUserByEmail(String email) throws UsernameNotFoundException {
		User user = userRepository.findByEmail(email)
				.orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + email));

		return UserDetailsImpl.build(user);
	}

}
