package com.kovecmedia.redseat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Base64;

import com.kovecmedia.redseat.doa.InvoiceRepository;
import com.kovecmedia.redseat.doa.PackageRepository;
import com.kovecmedia.redseat.entity.Invoice;
import com.kovecmedia.redseat.payload.request.InvoiceRequest;

@Service
public class InvoiceServiceImpl implements InvoiceService {

	@Autowired
	InvoiceRepository invoiceRepository;
	@Autowired
	PackageRepository packageRepository;

	@Override
	public void saveInvoice(InvoiceRequest invoiceRequest) {

		try {
			byte[] decodedByte = Base64.getDecoder().decode(invoiceRequest.getBase64());

			Invoice invoice = new Invoice();
			invoice.setName(invoiceRequest.getName());
			invoice.setUpdate_by(invoiceRequest.getUsername());
			invoice.setData(decodedByte);
			invoice.setType(invoiceRequest.getType());
			invoice.setPackageId(packageRepository.getOne(invoiceRequest.getPackageId()));
             System.out.println(invoiceRequest.getPackageId());
			invoiceRepository.save(invoice);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public void removeInvoice(long id) {
		try {
			invoiceRepository.deleteById(id);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
