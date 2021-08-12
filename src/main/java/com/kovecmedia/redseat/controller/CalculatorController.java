package com.kovecmedia.redseat.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kovecmedia.redseat.entity.DutyList;
import com.kovecmedia.redseat.model.CustomCost;
import com.kovecmedia.redseat.payload.request.CalculatorRequest;
import com.kovecmedia.redseat.service.CalculatorService;

@RestController
@RequestMapping("api/calculator")
public class CalculatorController {

	@Autowired
	CalculatorService calculatorService;
	
	@PostMapping(value = "/", produces = "application/json")
	public @Valid CustomCost calculator(@Valid @RequestBody CalculatorRequest calculatorRequest) {
		
		CustomCost customCost = new CustomCost();
		try {
			customCost = calculatorService.calculate(calculatorRequest.getDutyID(), calculatorRequest.getWegith(), calculatorRequest.getCost());

		} catch (Exception ex) {
			ex.printStackTrace();

		}

		return customCost;
	}
	
	@GetMapping(value = "/duty", produces = "application/json")
	public @Valid List<DutyList> dutyList() {
		
		List<DutyList> dutyList = new ArrayList<DutyList>();
		
		try {
			dutyList = calculatorService.GetAllDutyfees();

		} catch (Exception ex) {
			ex.printStackTrace();

		}

		return dutyList;
	}
}
