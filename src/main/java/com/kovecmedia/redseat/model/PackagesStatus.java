package com.kovecmedia.redseat.model;

import java.util.Random;

public enum PackagesStatus {

	RECEIVED, COLLECTED,PROCESSING,INTRANSIT,DELIVERED;
	
	public static PackagesStatus getRandomPackagesStatus() {
        Random random = new Random();
        return values()[random.nextInt(values().length)];
    }


}
