package com.kovecmedia.redseat.service;

import com.kovecmedia.redseat.payload.request.InvoiceRequest;



public interface InvoiceService  {

	void saveInvoice(InvoiceRequest invoiceRequest);
	void removeInvoice(long id);
	
}
