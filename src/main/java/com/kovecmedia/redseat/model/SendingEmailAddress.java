package com.kovecmedia.redseat.model;

public enum SendingEmailAddress {

	NO_REPY("no-repy@rscja.com"), DELIVERY("delivery@rscja.com"), SHIP("ship@rscja.com"),BILLING("Billing@rscja.com"),SHOP("SHOP@rscja.com");

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
