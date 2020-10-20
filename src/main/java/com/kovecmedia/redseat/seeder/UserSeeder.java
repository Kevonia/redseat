package com.kovecmedia.redseat.seeder;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.github.javafaker.Faker;
import com.kovecmedia.redseat.doa.AddressRepository;
import com.kovecmedia.redseat.doa.ContactNumberRepository;
import com.kovecmedia.redseat.doa.RoleRepository;
import com.kovecmedia.redseat.doa.UserRepository;
import com.kovecmedia.redseat.entity.Address;
import com.kovecmedia.redseat.entity.ContactNumber;
import com.kovecmedia.redseat.entity.Role;
import com.kovecmedia.redseat.entity.User;
import com.kovecmedia.redseat.model.UpdateBy;

@Component
public class UserSeeder {
	private Logger logger = Logger.getLogger(UserSeeder.class);
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	private AddressRepository addressRepository;
	@Autowired
	private ContactNumberRepository contactNumberRepository;

	public void run() {

		try {
			List<User> list = new ArrayList<User>();
			Faker faker = new Faker();
			NumberFormat formatter = new DecimalFormat("#0.00000");
			
			User user = new User();
			user.setId((long) 777);
			user.setName("Mikhail Ramsay");
			user.setEmail("mikhailramsay@gmail.com");
			user.setPassword("testpassword");
	
			user.setUpdate_by(UpdateBy.System.name());
			
//			
//			long start = System.currentTimeMillis();
//			for (int i = 1; i <= 10; i++) {
//				User user = new User();
//				Random rand = new Random();
//				Set<Address> addresslist = new HashSet<Address>();
//				Set<ContactNumber> contactlist = new HashSet<ContactNumber>();
//				Set<Role> rolelist = new HashSet<Role>();
//				
//				contactlist.add(contactNumberRepository.getOne((long) i));
//				addresslist.add(addressRepository.getOne((long) i));
//				rolelist.add(roleRepository.getOne((long) (rand.nextInt(3 - 1 + 1) + 1)));
//				
//				user.setName(faker.name().fullName());
//				user.setEmail(faker.internet().safeEmailAddress());
//				user.setPassword("testpassword");
//				user.setAddress(addresslist);
//				user.setRoles(rolelist);
//				user.setPhone(contactlist);
//				user.setUpdate_by(UpdateBy.System.name());
//				list.add(user);
//			}
//			
//			long end = System.currentTimeMillis();
//			userRepository.saveAll(list);
			//logger.info("User  Seeder ran in " + formatter.format((end - start) / 1000d) + " seconds");

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
