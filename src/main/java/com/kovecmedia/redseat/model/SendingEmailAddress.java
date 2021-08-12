package com.kovecmedia.redseat.model;

public enum SendingEmailAddress {

	NO_REPY("no-reply@rscja.com"), DELIVERY("delivery@rscja.com"),
	SHIP("ship@rscja.com"), BILLING("billing@rscja.com"),
	SHOP("shop@rscja.com");

	private final String text;

	/**
	 * @param text
	 */
	SendingEmailAddress(final String text) {
		this.text = text;
	}

	@Override
	public String toString() {
		return text;
	}
}
