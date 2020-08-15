package com.kovecmedia.redseat.model;

import java.util.Random;

public enum BillingStatus {
UNPAID,PAID;

public static BillingStatus getRandomBillingStatus() {
    Random random = new Random();
    return values()[random.nextInt(values().length)];
}
}
