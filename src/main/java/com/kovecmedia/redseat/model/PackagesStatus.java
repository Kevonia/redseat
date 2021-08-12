package com.kovecmedia.redseat.model;

import java.util.Random;

public enum PackagesStatus {

	    Pending,
	    Received_AT_Warehouse,
	    In_transit_To_Jamaica,
	    Processing_by_customs,
	    Processing_RSC_Office,
	    Ready_For_Pickup_Delivery,
	    PickedUp,
	    Delivered,
	    Collected,
	    Delayed;
	
	
	
	public static PackagesStatus getRandomPackagesStatus() {
        Random random = new Random();
        return values()[random.nextInt(values().length)];
    }


}
