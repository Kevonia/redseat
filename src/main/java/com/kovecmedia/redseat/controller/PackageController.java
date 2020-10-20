package com.kovecmedia.redseat.controller;

import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kovecmedia.redseat.entity.Package;
import com.kovecmedia.redseat.payload.respond.PackageInvoice;
import com.kovecmedia.redseat.service.PackegeService;

@RestController
@RequestMapping("package")
public class PackageController {

	private Logger logger = Logger.getLogger(this.getClass());

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
	public Package getbyId(@Valid @RequestBody  Package package1) {

		return packegeService.add(package1);
	}
}
