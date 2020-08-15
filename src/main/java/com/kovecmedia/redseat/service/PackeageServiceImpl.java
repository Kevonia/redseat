package com.kovecmedia.redseat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kovecmedia.redseat.doa.InvoiceRepository;
import com.kovecmedia.redseat.doa.PackageRepository;
import com.kovecmedia.redseat.entity.Package;
import com.kovecmedia.redseat.model.PackageInvoice;

@Service
public class PackeageServiceImpl implements PackegeService {

	@Autowired
	PackageRepository packageRepository;
	@Autowired
	InvoiceRepository  invoiceRepository;
	@Override
	public PackageInvoice getPacketByid(long id) {

		PackageInvoice packageInvoice = new PackageInvoice();
		Package package1 =packageRepository.getOne(id);
		
		packageInvoice.setPackeage(package1);
		packageInvoice.setInvoices(invoiceRepository.findByPackageId(package1));
		return  packageInvoice;
	}

}
