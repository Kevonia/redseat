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
import org.springframework.web.bind.annotation.PutMapping;
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
	
	@GetMapping(value = "/value/{id}", produces = "application/json")
	public long getValue(@PathVariable long id) {
				
		logger.info("Here");
		long value = packegeService.getValueByUser(id);
		logger.info(value);
		return value;
	}
	
	@PostMapping(value = "/create", produces = "application/json")
	public Package create(@Valid @RequestBody  Package package1) throws Exception {
                
		if (packageRepository.existsByTrackingNumber(package1.getTrackingNumber())) {
			throw new Exception("TrackingNumber is already in exits!");
		}
		
		return packegeService.add(package1);
	}
	
	
	@PutMapping(value = "/edit", produces = "application/json")
	public Package edit(@Valid @RequestBody  Package package1) throws Exception {
                
		return packegeService.add(package1);
	}
	
	@PostMapping(value = "/userPoint", produces = "application/json")
	public ResponseEntity<MessageResponse> userPoint(long packageID,long points) {
                
		try {
			packegeService.userPoint(packageID, points);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return  ResponseEntity.ok(new MessageResponse("Saved"));
	}
	
	
	

}
