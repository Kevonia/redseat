package com.kovecmedia.redseat.model;

import java.util.Random;

public enum CURRENCY {
	JMD, USD;

	public static CURRENCY getRandomCurrency() {
		Random random = new Random();
		return values()[random.nextInt(values().length)];
	}

}
