package com.kovecmedia.redseat.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kovecmedia.redseat.doa.AddressRepository;
import com.kovecmedia.redseat.doa.ContactNumberRepository;
import com.kovecmedia.redseat.doa.CountryRepository;
import com.kovecmedia.redseat.doa.MessageQueueRepository;
import com.kovecmedia.redseat.doa.PackageRepository;
import com.kovecmedia.redseat.doa.ScheduledJobRepository;
import com.kovecmedia.redseat.doa.UserRepository;
import com.kovecmedia.redseat.entity.Address;
import com.kovecmedia.redseat.entity.ContactNumber;
import com.kovecmedia.redseat.entity.MessageQueue;
import com.kovecmedia.redseat.entity.Role;
import com.kovecmedia.redseat.entity.User;
import com.kovecmedia.redseat.model.MessageStatus;
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

	@Autowired
	private MessageQueueRepository messageQueueRepository;



	private MessageQueue messageQueue = new MessageQueue();

	@Autowired
	private ScheduledJobRepository scheduledJobRepository;

	@Override
	@Transactional
	public void addUser(UserRegistration userRegistration, Set<Role> roles) throws Exception {
		User user = new User();
		Address address = new Address();
		ContactNumber contactNumber = new ContactNumber();
		Set<Address> addresslist = new HashSet<>();

		Set<ContactNumber> contactlist = new HashSet<>();

		Set<Role> rolelist = new HashSet<>();
		try {
			address.setAddressline1(userRegistration.getAddressLine1());
			address.setAddressline2(userRegistration.getAddressLine2());
			address.setZipcode(userRegistration.getZipCode());
			address.setCountry(countryrepository.getOne((long) 111));

			addresslist.add(address);

			contactNumber.setIsprimary(true);
			contactNumber.setNumber(userRegistration.getPhone());
			contactNumber.setType(PhoneType.CELL);

			contactlist.add(contactNumber);

			rolelist.addAll(roles);

			user.setAddress(addresslist);
			user.setPhone(contactlist);
			user.setRoles(rolelist);

			user.setPassword(userRegistration.getPassword());
			user.setName(userRegistration.getName());
			user.setEmail(userRegistration.getEmail());
			addressRepository.save(address);
			contactnumberepository.save(contactNumber);

			userRepository.save(user);

			messageQueue.setStatus(MessageStatus.NOTSENT);
			messageQueue.setScheduledId(scheduledJobRepository.getOne((long) 1));
			messageQueue.setEmail(userRegistration.getEmail());
			messageQueueRepository.save(messageQueue);

		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Oops, Something Went Wrong");

		}
	}

	@Override
	public User getUser(long id) {
		User user = userRepository.findById(id).get();
		return user;
	}

	@Override
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

	@Override
	public UserPackages getUserPaakages(long id) {

		User tempuser = new User();
		tempuser = userRepository.getOne(id);
		UserPackages userPackages = new UserPackages();
		userPackages.setId(id);
		userPackages.setName(tempuser.getName());
		userPackages.setPacklist(packageRepository.findByUserId(userRepository.getOne((long) id)));

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
