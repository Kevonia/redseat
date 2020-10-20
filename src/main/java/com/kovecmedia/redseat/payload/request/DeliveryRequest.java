package com.kovecmedia.redseat.payload.request;

import java.sql.Date;
import java.util.List;

import com.kovecmedia.redseat.entity.Package;
public class DeliveryRequest {

	private String instructions;
	private String peroid;
	private Date date;
	private String addressLine1;
	private String addressLine2;
	private String zipCode;
	private List<Package> packages;
	private float charge;
	private long Userid;
	
	
	public String getInstructions() {
		return instructions;
	}
	public void setInstructions(String instructions) {
		this.instructions = instructions;
	}
	public String getPeroid() {
		return peroid;
	}
	public void setPeroid(String peroid) {
		this.peroid = peroid;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getAddressLine1() {
		return addressLine1;
	}
	public void setAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
	}
	public String getAddressLine2() {
		return addressLine2;
	}
	public void setAddressLine2(String addressLine2) {
		this.addressLine2 = addressLine2;
	}
	public String getZipCode() {
		return zipCode;
	}
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public float getCharge() {
		return charge;
	}
	public void setCharge(float charge) {
		this.charge = charge;
	}
	public long getUserid() {
		return Userid;
	}
	public void setUserid(long userid) {
		Userid = userid;
	}
	public List<Package> getPackages() {
		return packages;
	}
	public void setPackages(List<Package> packages) {
		this.packages = packages;
	}
	
	
	
	
}
