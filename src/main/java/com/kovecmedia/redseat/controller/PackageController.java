package com.kovecmedia.redseat.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kovecmedia.redseat.service.PackegeService;
import com.kovecmedia.redseat.model.PackageInvoice;

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
}
