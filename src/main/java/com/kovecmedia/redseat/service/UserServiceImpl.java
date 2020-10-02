package com.kovecmedia.redseat.service;

import java.sql.Timestamp;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kovecmedia.redseat.doa.AddressRepository;
import com.kovecmedia.redseat.doa.ContactNumberRepository;
import com.kovecmedia.redseat.doa.CountryRepository;
import com.kovecmedia.redseat.doa.ForgetPasswordRepository;
import com.kovecmedia.redseat.doa.MessageQueueRepository;
import com.kovecmedia.redseat.doa.PackageRepository;
import com.kovecmedia.redseat.doa.ScheduledJobRepository;
import com.kovecmedia.redseat.doa.UserRepository;
import com.kovecmedia.redseat.entity.Address;
import com.kovecmedia.redseat.entity.ContactNumber;
import com.kovecmedia.redseat.entity.ForgetPassword;
import com.kovecmedia.redseat.entity.MessageQueue;
import com.kovecmedia.redseat.entity.Role;
import com.kovecmedia.redseat.entity.User;
import com.kovecmedia.redseat.model.MessageStatus;
import com.kovecmedia.redseat.model.PhoneType;
import com.kovecmedia.redseat.payload.request.ResetRequest;
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

	@Autowired
	private ForgetPasswordRepository forgetPasswordRepository;

	private MessageQueue messageQueue = new MessageQueue();

	@Autowired
	private ScheduledJobRepository scheduledJobRepository;

	static final Long duration = (long) (((60 * 60) * 24) * 1000);// 24 hours

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
			address.setCountry(countryrepository.getOne((long) 1));

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
			user.setPoints((long) 0);
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
		userPackages.setPoints(tempuser.getPoints());
		userPackages.setPacklist(packageRepository.findByUserIdAndPreAlert((userRepository.getOne((long) id)), false));

		return userPackages;
	}

	@Override
	@Transactional
	public UserDetails loadUserByEmail(String email) throws UsernameNotFoundException {
		User user = userRepository.findByEmail(email)
				.orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + email));

		return UserDetailsImpl.build(user);
	}

	@Override
	public Boolean existsByEmail(String email) {
		return userRepository.existsByEmail(email);
	}

	@Override
	@Transactional
	public void resetPassword(String email) throws UsernameNotFoundException {
		ForgetPassword forgetPassword = new ForgetPassword();
		Timestamp time = new Timestamp(System.currentTimeMillis());
		try {

			time.setTime(time.getTime() + duration);

			User user = userRepository.findByEmail(email)
					.orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + email));

			if (forgetPasswordRepository.findByUsedAndUser(false, user) == null) {
				forgetPassword.setUsed(false);
				forgetPassword.setUser(user);
				forgetPassword.setToken(generateType1UUID().toString());
				forgetPassword.setExpired_date(time);
				forgetPasswordRepository.save(forgetPassword);
			}

			messageQueue.setStatus(MessageStatus.NOTSENT);
			messageQueue.setScheduledId(scheduledJobRepository.getOne((long) 3));
			messageQueue.setEmail(user.getEmail());
			messageQueueRepository.save(messageQueue);
		} catch (Exception e) {
			e.printStackTrace();
			throw new UsernameNotFoundException("User Not Found with username: " + email);

		}
	}

	private static long get64LeastSignificantBitsForVersion1() {
		Random random = new Random();
		long random63BitLong = random.nextLong() & 0x3FFFFFFFFFFFFFFFL;
		long variant3BitFlag = 0x8000000000000000L;
		return random63BitLong + variant3BitFlag;
	}

	private static long get64MostSignificantBitsForVersion1() {
		LocalDateTime start = LocalDateTime.of(1582, 10, 15, 0, 0, 0);
		Duration duration = Duration.between(start, LocalDateTime.now());
		long seconds = duration.getSeconds();
		long nanos = duration.getNano();
		long timeForUuidIn100Nanos = seconds * 10000000 + nanos * 100;
		long least12SignificatBitOfTime = (timeForUuidIn100Nanos & 0x000000000000FFFFL) >> 4;
		long version = 1 << 12;
		return (timeForUuidIn100Nanos & 0xFFFFFFFFFFFF0000L) + version + least12SignificatBitOfTime;
	}

	public static UUID generateType1UUID() {

		long most64SigBits = get64MostSignificantBitsForVersion1();
		long least64SigBits = get64LeastSignificantBitsForVersion1();

		return new UUID(most64SigBits, least64SigBits);
	}

	@Override
	public boolean checkToken(String token) {

		if (forgetPasswordRepository.existsByToken(token)) {
			ForgetPassword forgetPassword = forgetPasswordRepository.findByToken(token);
			return !forgetPassword.isUsed();
		} else {
			return false;
		}

	}

	@Override
	public void UpdatePassword(ResetRequest request) {
		ForgetPassword forgetPassword = forgetPasswordRepository.findByToken(request.getToken());
		User user = userRepository.findById(forgetPassword.getUser().getId()).orElseThrow(
				() -> new UsernameNotFoundException("User Not Found with user with token: " + request.getToken()));

		if (request.getNewPassword().equals(request.getConfrimNewPassword())) {
			user.setPassword(request.getNewPassword());
			forgetPassword.setUsed(true);
			userRepository.save(user);
			forgetPasswordRepository.save(forgetPassword);

		}

	}

	@Override
	public User UpdateProfile(User user) {
		// TODO Auto-generated method stub
		User user2 = userRepository.findById(user.getId()).get();
		
		user2.setName(user.getName());
		
		user2.setEmail(user.getEmail());
		
		
		userRepository.save(user2);
         return user;
	}

}
