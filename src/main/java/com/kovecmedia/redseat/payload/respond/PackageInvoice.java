package com.kovecmedia.redseat.payload.respond;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.kovecmedia.redseat.entity.Billing;
import com.kovecmedia.redseat.entity.Invoice;
import com.kovecmedia.redseat.entity.Package;

public class PackageInvoice {

	
	private Package  packageItem;
	
	private List<Invoice>  invoices;
	
	private Billing billing;

	public Package getPackageItem() {
		return packageItem;
	}

	public void setPackageItem(Package packageItem) {
		this.packageItem = packageItem;
	}

	public List<Invoice> getInvoices() {
		return invoices;
	}

	public void setInvoices(List<Invoice> invoices) {
		this.invoices = invoices;
	}

	public Billing getBilling() {
		return billing;
	}

	public void setBilling(Billing billing) {
		this.billing = billing;
	}

	

	
	
}
