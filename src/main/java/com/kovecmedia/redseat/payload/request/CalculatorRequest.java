package com.kovecmedia.redseat.payload.request;

import javax.validation.constraints.NotBlank;

public class CalculatorRequest {

	private long dutyID;
	private long wegith;
	private double cost;

	public long getDutyID() {
		return dutyID;
	}

	public void setDutyID(long dutyID) {
		this.dutyID = dutyID;
	}

	public long getWegith() {
		return wegith;
	}

	public void setWegith(long wegith) {
		this.wegith = wegith;
	}

	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}

}