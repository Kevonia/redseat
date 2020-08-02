package com.kovecmedia.redseat.model;
import java.util.List;

import com.kovecmedia.redseat.entity.Invoice;
import com.kovecmedia.redseat.entity.Package;

public class PackageInvoice {

	
	private Package  packeage;
	
	private List<Invoice>  invoices;

	public Package getPackeage() {
		return packeage;
	}

	public void setPackeage(Package packeage) {
		this.packeage = packeage;
	}

	public List<Invoice> getInvoices() {
		return invoices;
	}

	public void setInvoices(List<Invoice> invoices) {
		this.invoices = invoices;
	}
	
	
}
