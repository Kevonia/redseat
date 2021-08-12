package com.kovecmedia.redseat.service;

import java.util.List;

import com.kovecmedia.redseat.entity.DutyList;
import com.kovecmedia.redseat.model.CustomCost;



public interface CalculatorService {
	CustomCost calculate(long dutyID,long wegith,double cost );
	
	List<DutyList> GetAllDutyfees();
}
