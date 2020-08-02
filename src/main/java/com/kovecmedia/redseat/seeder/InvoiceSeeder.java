package com.kovecmedia.redseat.seeder;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.kovecmedia.redseat.doa.InvoiceRepository;
import com.kovecmedia.redseat.doa.PackageRepository;
import com.kovecmedia.redseat.entity.Invoice;
import com.kovecmedia.redseat.model.UpdateBy;
@Component
public class InvoiceSeeder {
	private Logger logger = Logger.getLogger(PackageSeeder.class);
	@Autowired
	private InvoiceRepository invoiceRepository;
	@Autowired
	private PackageRepository packageRepository;

	public void run() {
		try {			
			List<Invoice> list = new ArrayList<Invoice>();
			NumberFormat formatter = new DecimalFormat("#0.00000");
			long start = System.currentTimeMillis();
			for (int i = 1; i <= 200; i++) {
				Invoice  invoice = new Invoice();
				invoice.setUpdate_by(UpdateBy.System.name());
				invoice.setPackageId(packageRepository.getOne((long) i));
				list.add(invoice);
			}
			long end = System.currentTimeMillis();
			invoiceRepository.saveAll(list);
			logger.info("Package Seeder ran in " + formatter.format((end - start) / 1000d) + " seconds");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
