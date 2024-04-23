package com.tco.misc;

import com.tco.requests.Place;
import com.tco.requests.Places;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class TestTwoOptimizer {
    private TwoOptimizer twoOpt;

    private Place northPole = new Place("90", "0");
    private Place southPole = new Place("-90", "0");
    private Place equatorMiddle = new Place("0", "0");
    private Place equatorWest = new Place("0", "-90");
    private Place equatorEast = new Place("0", "90");
    private Places places;

    @BeforeEach
    public void createTwoOptimizerForTestCases() {
        twoOpt = new TwoOptimizer();
        places = new Places();
        places.add(northPole);
        places.add(southPole);
        places.add(equatorMiddle);
        places.add(equatorWest);
        places.add(equatorEast);

        twoOpt.setRadius(1);
        twoOpt.setPlaces(places);
        twoOpt.initializeTourTest();
        twoOpt.initializeDistancesTest();
    }

    @Test
    @DisplayName("gknudt: twoOpt should not be null after initialization")
    public void testTwoOptimizerNotNull() {
        assertNotNull(twoOpt);
    }

    @Test
    @DisplayName("gknudt: Test that improve() reduces the tour length for a simple tour")
    public void testImproveReducesTourLength() {
        long originalLength = twoOpt.getTourDistanceTest();
        twoOpt.improve();
        long improvedLength = twoOpt.getTourDistanceTest();
        assertTrue(improvedLength < originalLength);
    }
    
    @Test
    @DisplayName("gknudt: Test that swapImproves() accurately identifies beneficial swaps")
    public void testSwapImproves() {
        boolean shouldImprove = twoOpt.testSwapImproves(0, 2);
        assertTrue(shouldImprove);
    }
}
