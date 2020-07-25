package com.kovecmedia.redseat.model;

import java.util.Random;

public enum PhoneType {
	CELL, HOME, WORK;
	
	public static PhoneType getRandomPhoneType() {
        Random random = new Random();
        return values()[random.nextInt(values().length)];
    }
	
}
