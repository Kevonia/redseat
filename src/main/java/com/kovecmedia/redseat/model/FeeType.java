package com.kovecmedia.redseat.model;

import java.util.Random;

public enum FeeType {
	Shipping,Processing,Customs,GCT;
	
	public static FeeType getRandomFeeType() {
	    Random random = new Random();
	    return values()[random.nextInt(values().length)];
	}
}
