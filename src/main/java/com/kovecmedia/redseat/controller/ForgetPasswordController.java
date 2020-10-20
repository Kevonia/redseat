package com.kovecmedia.redseat.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kovecmedia.redseat.payload.request.ResetRequest;
import com.kovecmedia.redseat.payload.respond.MessageResponse;
import com.kovecmedia.redseat.service.UserService;


@RestController
@RequestMapping("/api/password")
public class ForgetPasswordController<T> {

	Logger logger = LoggerFactory.getLogger(ForgetPasswordController.class);
	@Autowired
	UserService userservice;
	
	@SuppressWarnings("unchecked")
	@GetMapping(value = "/forget/{email}/", produces = "application/json")
	public ResponseEntity<?>  forgetPassword(@PathVariable String email) {	
		
		try {
			userservice.resetPassword(email);
			
			return ResponseEntity.ok(new MessageResponse("successfully!"));	
		}
		catch(UsernameNotFoundException ex){
			return ResponseEntity.badRequest().body((T) new MessageResponse(ex.getMessage()));
		} 
	}
	
	@GetMapping(value = "/checktoken/{token}", produces = "application/json")
	public boolean checkToken(@PathVariable String token) {	
		
	  return userservice.checkToken(token);
	}
			
	@PostMapping(value = "/reset/", produces = "application/json")
	public ResponseEntity<?>  resetPassword(@Valid @RequestBody  ResetRequest resetRequest) {	
		
		try {
			userservice.UpdatePassword(resetRequest);
			
			return ResponseEntity.ok(new MessageResponse("successfully!"));	
		}
		catch(UsernameNotFoundException ex){
			return ResponseEntity.badRequest().body((T) new MessageResponse(ex.getMessage()));
		}
			  
		
	}

	
}
