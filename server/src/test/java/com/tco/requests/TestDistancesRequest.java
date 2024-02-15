package com.tco.requests;

import com.tco.misc.GreatCircleDistance;
import com.tco.misc.VincentyDistance;
import com.tco.misc.CosinesDistance;
import com.tco.misc.HaversineDistance;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestDistancesRequest {

    private DistancesRequest dist;

    @BeforeEach
    public void createDistancesForTestCases() {
        dist = new DistancesRequest();
        dist.buildResponse();
    }

    @Test
    @DisplayName("bodorol: Request type is \"distances\"")
    public void testType() {
        String type = dist.getRequestType();
        assertEquals("distances", type);
    }

    @Test
    @DisplayName("bodorol: Distance calculator type is \"vincenty\"")
    public void testFormula() {
        GreatCircleDistance distanceCalculator = dist.getDistanceCalculator();
        assertTrue(distanceCalculator instanceof VincentyDistance);
    }

    @Test
    @DisplayName("bodorol: Distances object is empty")
    public void testDistances() {
        Distances distances = dist.getDistances();
        assertTrue(distances.isEmpty());
    }
}