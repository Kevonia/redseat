package com.kovecmedia.redseat.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.kovecmedia.redseat.payload.request.InvoiceRequest;
import com.kovecmedia.redseat.payload.respond.MessageResponse;
import com.kovecmedia.redseat.service.InvoiceService;

@RestController
@RequestMapping("api/invoice")
public class InvoiceController {

	Logger logger = LoggerFactory.getLogger(CountryController.class);
	@Autowired
	InvoiceService invoiceService;
	
	@PostMapping(value = "/add", produces = "application/json")
	public InvoiceRequest addInvoice(@Valid @RequestBody InvoiceRequest invoiceRequest) {	
		 invoiceService.saveInvoice(invoiceRequest);		  
		 return invoiceRequest;
		
	}
	
	@DeleteMapping(value = "/remove/{id}", produces = "application/json")
	public ResponseEntity<?> removeInvoice(@PathVariable long id) {	
		 invoiceService.removeInvoice(id);	 
		 return ResponseEntity.badRequest().body(new MessageResponse("Success: Invoice Removed"));
		
		
	}
	
}

