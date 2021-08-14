package com.kovecmedia.redseat.controller;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import javax.imageio.ImageIO;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.kovecmedia.redseat.doa.PackageRepository;
import com.kovecmedia.redseat.doa.UserRepository;
import com.kovecmedia.redseat.entity.Package;
import com.kovecmedia.redseat.payload.respond.MessageResponse;
import com.kovecmedia.redseat.payload.respond.PackageInvoice;
import com.kovecmedia.redseat.service.PackegeService;


@RestController
@RequestMapping("package")
public class PackageController {

	private Logger logger = Logger.getLogger(this.getClass());
	
	@Autowired
	PackageRepository packageRepository;
	

	@Autowired
	PackegeService packegeService;

	@GetMapping(value = "/{id}", produces = "application/json")
	public PackageInvoice getbyId(@PathVariable long id) {
				
		PackageInvoice packages = new PackageInvoice();
		packages = packegeService.getPacketByid(id);
		logger.info(packages.toString());
		return packages;
	}
	
	@PostMapping(value = "/create", produces = "application/json")
	public ResponseEntity<MessageResponse> getbyId(@Valid @RequestBody  Package package1) {
                
		if (packageRepository.existsByTrackingNumber(package1.getTrackingNumber())) {
			return ResponseEntity.badRequest().body(new MessageResponse("TrackingNumber is already in exits!"));
		}
		packegeService.add(package1);
		
		
		return  ResponseEntity.ok(new MessageResponse("Saved"));
	}
	@PostMapping(value = "/userPoint", produces = "application/json")
	public ResponseEntity<MessageResponse> userPoint(@Valid @RequestBody  Package package1,long points) {
                
		try {
			packegeService.userPoint(package1, points);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return  ResponseEntity.ok(new MessageResponse("Saved"));
	}
	
	
	

}
