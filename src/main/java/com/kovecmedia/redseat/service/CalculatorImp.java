package com.kovecmedia.redseat.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kovecmedia.redseat.doa.DutylistRepository;
import com.kovecmedia.redseat.doa.ExrateRepository;
import com.kovecmedia.redseat.doa.FeesRepository;
import com.kovecmedia.redseat.entity.DutyList;
import com.kovecmedia.redseat.entity.EXRate;
import com.kovecmedia.redseat.entity.Fee;
import com.kovecmedia.redseat.model.CustomCost;

@Service
public class CalculatorImp implements CalculatorService {

	@Autowired
	private DutylistRepository dutylistRepository;
	@Autowired
	private FeesRepository feesRepository;
	@Autowired
	private ExrateRepository exrateRepository;
	@Override
	public CustomCost calculate(long dutyID, long wegith, double cost) {
		
		DutyList  dutyList  = dutylistRepository.findById(dutyID).get();

		Fee  freightFee  = feesRepository.getFreightFee(wegith);
		
		Fee  ProcessingFee  = feesRepository.getProcessingFee(wegith);
		
		EXRate rate = exrateRepository.findTopByOrderByIdDesc();
		
		CustomCost finalcost =new CustomCost();
	   if(cost>50.00) {
			final double ItemPrice =  ((cost *  rate.getExrate()) + ( (double)freightFee.getValueJmd()  + (double)ProcessingFee.getValueJmd()));
			
			
			finalcost.setDuty(ItemPrice*dutyList.getDuty());
			finalcost.setENVL(ItemPrice* 0.005);
			finalcost.setSCF(ItemPrice* 0.003);
			finalcost.setCAF((double)2500);
			finalcost.setStamp((double)100);
			
		
			final double totalTax= (finalcost.getCAF() + finalcost.getENVL() +  finalcost.getDuty() +finalcost.getENVL()  +finalcost.getStamp());
					 
			finalcost.setDuty(ItemPrice* dutyList.getDuty());
			  
			finalcost.setGCT( ItemPrice * dutyList.getGCT());
			
			final double itemTotal =(finalcost.getGCT() + totalTax ) ;	
			
			finalcost.setCustoms(itemTotal );
	   }


		finalcost.setFreight(freightFee.getValueJmd());
		// TODO Auto-generated method stub
		return finalcost;
	}
	@Override
	public List<DutyList> GetAllDutyfees() {
		// TODO Auto-generated method stub
		return dutylistRepository.findAll();
	}

}
