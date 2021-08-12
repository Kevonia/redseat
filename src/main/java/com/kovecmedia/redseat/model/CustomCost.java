package com.kovecmedia.redseat.model;

import java.text.DecimalFormat;



public class CustomCost {

	static final  double NO_OF_DECIMAL=100;
	
	private double Duty;
	private  double SCF;
	private  double ENVL;
	private double CAF;
	private double GCT;
	private double Stamp;
	private double ICD;
	private double Freight;
	private double Customs;
	
	public double getDuty() {
		return Duty;
	}
	public void setDuty(double duty) {
		Duty = duty;
	}
	public double getSCF() {


		return Math.round(SCF*NO_OF_DECIMAL)/NO_OF_DECIMAL;
	}
	public void setSCF(double sCF) {
		SCF = sCF;
	}
	public double getENVL() {
	return Math.round(ENVL*NO_OF_DECIMAL)/NO_OF_DECIMAL;
	}
	public void setENVL(double eNVL) {
		ENVL = eNVL;
	}
	public double getCAF() {
	
		return Math.round(CAF*NO_OF_DECIMAL)/NO_OF_DECIMAL;
	}
	public void setCAF(double cAF) {
		CAF = cAF;
	}
	public double getGCT() {
		
		return Math.round(GCT*NO_OF_DECIMAL)/NO_OF_DECIMAL;
	}
	public void setGCT(double gCT) {
		GCT = gCT;
	}
	public double getStamp() {
		return Stamp;
	}
	public void setStamp(double stamp) {
		Stamp = stamp;
	}
	public double getICD() {
	
		return Math.round(ICD*NO_OF_DECIMAL)/NO_OF_DECIMAL;
		
	}
	public void setICD(double iCD) {
		ICD = iCD;
	}
	public double getFreight() {
		
		return Math.round(Freight*NO_OF_DECIMAL)/NO_OF_DECIMAL;
	}
	public void setFreight(double freight) {
		Freight = freight;
	}
	public double getCustoms() {
	
		return Math.round(Customs*NO_OF_DECIMAL)/NO_OF_DECIMAL;
	}
	public void setCustoms(double customs) {
		Customs = customs;
	}
	public CustomCost() {

		Duty = 0.00;
		SCF =  0.00;
		ENVL =  0.00;
		CAF =  0.00;
		GCT =  0.00;
		Stamp =  0.00;
		ICD =  0.00;
		Freight =  0.00;
		Customs =  0.00;
	}
	
	
	
}
