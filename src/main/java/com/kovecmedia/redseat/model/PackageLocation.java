package com.kovecmedia.redseat.model;

import java.util.Random;

public enum PackageLocation {

	PENDING,AIRPORT,JAMAICA,WAREHOUSE;
	
	public static PackageLocation getRandomPackageLocation() {
        Random random = new Random();
        return values()[random.nextInt(values().length)];
    }
	
}
