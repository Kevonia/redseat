package com.kovecmedia.redseat.model;

import java.util.Random;

public enum AddressStatus {
	DELIVERY, SHIPPING;

	public static AddressStatus getRandomAddressType() {
            Random random = new Random();
            return values()[random.nextInt(values().length)];
        }
}
