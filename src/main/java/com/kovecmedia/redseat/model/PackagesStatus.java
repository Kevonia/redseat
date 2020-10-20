package com.kovecmedia.redseat.model;

import java.util.Random;

public enum PackagesStatus {

	NEW,RECEIVED,COLLECTED,PROCESSING,INTRANSIT,DELIVERED;
	
	public static PackagesStatus getRandomPackagesStatus() {
        Random random = new Random();
        return values()[random.nextInt(values().length)];
    }


}
