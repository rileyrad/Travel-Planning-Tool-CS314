package com.tco.requests;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestPlaceMethods {
    private final Double doubleComparisonPrecision = 0.0000001;

    @Test
    @DisplayName("greeff: Test latRadians Conversion")
    public void testLatRadians(){

        Place place = new Place("40.1", "0.0");
        Double idealRadians = 0.699877;
        Double convertedRadians = place.latRadians();
        
        assertTrue(Math.abs(idealRadians - convertedRadians) <= doubleComparisonPrecision);
    }
}
