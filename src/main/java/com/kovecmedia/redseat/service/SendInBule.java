package com.kovecmedia.redseat.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.kovecmedia.redseat.entity.ContactNumber;
import com.kovecmedia.redseat.entity.User;

import sendinblue.ApiClient;
import sendinblue.ApiException;
import sendinblue.Configuration;
import sendinblue.auth.ApiKeyAuth;
import sibApi.ContactsApi;
import sibModel.CreateContact;
import sibModel.CreateUpdateContactModel;

@Component
public class SendInBule {
	

	
	
	
	
	public void addContact(User user,String papiKey) {
		ApiClient defaultClient = Configuration.getDefaultApiClient();
	      
		
		
	       ApiKeyAuth apiKey = (ApiKeyAuth) defaultClient.getAuthentication("api-key");
	       apiKey.setApiKey(papiKey);
	       ContactsApi api = new ContactsApi();
	       try {
	    	   
	    	   String[] fname= user.getName().split(" ");
	    	   
	    	   ContactNumber sms=  new ContactNumber();
	    	   
	    	   for (ContactNumber phone : user.getPhone()) { 
	    		   
	    		  if(phone.getIsprimary()==true) {
	    			  sms =phone;
	    		  }
	    	   }

	    	   
	    	   
	    	   CreateContact createContact = new CreateContact();
	            createContact.setEmail(user.getEmail());
	            Properties attributes = new Properties();
	            attributes.setProperty("FIRSTNAME",fname[0]);
	            attributes.setProperty("LASTNAME", fname[1]);
	            attributes.setProperty("SMS", sms.getNumber());
	            attributes.setProperty("RSC_CUSTOMER_NUMBER", "RSC"+ user.getId());
	            createContact.setAttributes(attributes);
	            List<Long> listIds = new ArrayList<Long>();
	            listIds.add((long) 4);
	            createContact.setListIds(listIds);
	            createContact.setEmailBlacklisted(false);
	            createContact.setSmsBlacklisted(false);
	            createContact.setUpdateEnabled(false);
//	            List<String> smtpBlacklistedSender = new ArrayList<String>();
//	            smtpBlacklistedSender.add(user.getEmail());
//	            createContact.setListIds(listIds);
//	            createContact.setSmtpBlacklistSender(smtpBlacklistedSender);
	            CreateUpdateContactModel response = api.createContact(createContact);
	            System.out.println(response.toString());
	        
	       } catch (ApiException  e) {
	    	 
	    	   System.out.println(  e.getResponseHeaders());
	    	   System.out.println(  e.getCode() );
	           System.out.println("Exception occurred:- " + e.getResponseBody());
	       }
	}
	   
};
