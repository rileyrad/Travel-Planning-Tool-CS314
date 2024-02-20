package com.tco.misc;
import com.tco.requests.Place;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestDistanceFormulas {
    HaversineDistance haversine = new HaversineDistance();

    @Test
    @DisplayName("greeff: Test Haversine Formula")
    public void testHaversineFormula(){
        Double earthRadius = 100.0;
        Place from = new Place("40.1","40.1");
        Place to = new Place("80.2","80.2");
        assertTrue(74 == haversine.between(from,to,earthRadius));
    }
}
