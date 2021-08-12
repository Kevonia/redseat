package com.kovecmedia.redseat.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kovecmedia.redseat.doa.BillingRepository;
import com.kovecmedia.redseat.doa.InvoiceRepository;
import com.kovecmedia.redseat.doa.PackageRepository;
import com.kovecmedia.redseat.doa.UserRepository;
import com.kovecmedia.redseat.entity.Billing;
import com.kovecmedia.redseat.entity.Package;
import com.kovecmedia.redseat.entity.User;
import com.kovecmedia.redseat.payload.respond.PackageInvoice;

@Service
public class PackeageServiceImpl implements PackegeService {

	@Autowired
	PackageRepository packageRepository;
	@Autowired
	InvoiceRepository  invoiceRepository;
	
	@Autowired
	BillingRepository  billingRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Override
	public PackageInvoice getPacketByid(long id) {
        
		System.out.println(id);
		
		PackageInvoice packageInvoice = new PackageInvoice();
		Package package1 =packageRepository.findById(id).orElse(null);
		
		 packageInvoice.setPackageItem(package1);
		 packageInvoice.setInvoices(invoiceRepository.findByPackageId(package1));
		 packageInvoice.setBilling(billingRepository.findBypackageId(package1));
		
		
		return  packageInvoice;
	}

	@Override
	public Package add(Package package1) {
		// TODO Auto-generated method stub
		packageRepository.save(package1);
		
		return package1;
	}

	@Override
	@Transactional
	public void userPoint(Package package1, long point) throws Exception {	
		try {
			long userPoint=0;
			long pointVlaue=(point*10);
			
			 User user =userRepository.findById(package1.getUserid().getId()).get();
			 
			 Billing billing= (billingRepository.findBypackageId(package1).get(0));
			 long newfee =(long) (billing.getValue() - pointVlaue);
			
			userPoint =user.getPoints()  - point;
			
			userRepository.save(user);
			billingRepository.save(billing);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Oops, Something Went Wrong");

		}
		
	}


	

}
