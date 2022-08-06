package com.kovecmedia.redseat.model;

import java.util.Random;

public enum Valuetype {

	Value, Percent;

	public static Valuetype getRandomValuetype() {
		Random random = new Random();
		return values()[random.nextInt(values().length)];
	}
}
