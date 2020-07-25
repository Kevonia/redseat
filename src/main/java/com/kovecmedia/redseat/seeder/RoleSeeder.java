package com.kovecmedia.redseat.seeder;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.kovecmedia.redseat.doa.RoleRepository;
import com.kovecmedia.redseat.entity.Role;
import com.kovecmedia.redseat.model.Roles;
import com.kovecmedia.redseat.model.UpdateBy;

@Component
public class RoleSeeder {
	private Logger logger = Logger.getLogger(RoleSeeder.class);
	@Autowired
	private RoleRepository roleRepository;

	public void run() {
		try {
			List<Role> list = new ArrayList<Role>();
		
			NumberFormat formatter = new DecimalFormat("#0.00000");
			
			String update_by =UpdateBy.System.name();

			long start = System.currentTimeMillis();

			Role role1 = new Role();
			Role role2 = new Role();
			Role role3 = new Role();

			role1.setName(Roles.Admin.name());
			role1.setUpdate_by(update_by);
			role2.setName(Roles.User.name());
			role2.setUpdate_by(update_by);
			role3.setName(Roles.Guest.name());
			role3.setUpdate_by(update_by);
			
			list.add(role1);
			list.add(role2);
			list.add(role3);

			long end = System.currentTimeMillis();
			roleRepository.saveAll(list);
			logger.info("Role Seeder ran in " + formatter.format((end - start) / 1000d) + " seconds");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
