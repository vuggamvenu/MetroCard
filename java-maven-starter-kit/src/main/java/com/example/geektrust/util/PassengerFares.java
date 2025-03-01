package com.example.geektrust.util;

import java.util.HashMap;
import java.util.Map;

public class PassengerFares {

	private static final Map<String, Integer> PASSENGER_FARES = new HashMap<>();

    static {
        PASSENGER_FARES.put("ADULT", 200);
        PASSENGER_FARES.put("SENIOR_CITIZEN", 100);
        PASSENGER_FARES.put("KID", 50);
    }

    public static int getFare(String passengerType) {
        return PASSENGER_FARES.getOrDefault(passengerType.toUpperCase(), 0);
    }
}
