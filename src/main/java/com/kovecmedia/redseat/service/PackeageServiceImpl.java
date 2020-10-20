package com.kovecmedia.redseat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kovecmedia.redseat.doa.BillingRepository;
import com.kovecmedia.redseat.doa.InvoiceRepository;
import com.kovecmedia.redseat.doa.PackageRepository;
import com.kovecmedia.redseat.entity.Billing;
import com.kovecmedia.redseat.entity.Package;
import com.kovecmedia.redseat.payload.respond.PackageInvoice;

@Service
public class PackeageServiceImpl implements PackegeService {

	@Autowired
	PackageRepository packageRepository;
	@Autowired
	InvoiceRepository  invoiceRepository;
	
	@Autowired
	BillingRepository  billingRepository;
	
	@Override
	public PackageInvoice getPacketByid(long id) {

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
	
	

}
