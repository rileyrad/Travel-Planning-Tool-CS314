package com.tco.misc;

import com.tco.requests.Place;
import com.tco.requests.Places;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TestOneOptimizer {

    private OneOptimizer oneOpt;

    private Place northPole = new Place("90", "0");
    private Place southPole = new Place("-90", "0");
    private Place equatorMiddle = new Place("0", "0");
    private Place equatorWest = new Place("0", "-90");
    private Place equatorEast = new Place("0", "90");

    @BeforeEach
    public void createOneOptimizerForTestCases() {
        oneOpt = new OneOptimizer();
        oneOpt.setRadius(1);
    }

    @Test
    @DisplayName("bodorol: Nearest neighbor works with 4 places")
    public void testNearestNeighborFourPlaces() {
        Places places = new Places();
        places.add(northPole);
        places.add(equatorEast);
        places.add(equatorWest);
        places.add(southPole);

        oneOpt.setPlaces(places);
        oneOpt.initializeTourTest();
        oneOpt.initializeDistancesTest();
        oneOpt.nearestNeighborTest();

        Place thirdPlace = oneOpt.getPlaceFromTour(2);
        assertEquals(southPole, thirdPlace);
    }

    @Test
    @DisplayName("bodorol: Swap correctly swaps two places in tour")
    public void testSwap() {
        Places places = new Places();
        places.add(northPole);
        places.add(southPole);

        oneOpt.setPlaces(places);
        oneOpt.initializeTourTest();

        oneOpt.swapElementsTest(0, 1);
        Place firstPlace = oneOpt.getPlaceFromTour(0);

        assertEquals(southPole, firstPlace);
    }

    @Test
    @DisplayName("bodorol: Tour is initialized with correct length")
    public void testTourInitializationLength() {
        Places places = new Places();
        places.add(northPole);
        places.add(equatorEast);
        places.add(equatorMiddle);
        places.add(equatorWest);
        places.add(southPole);

        oneOpt.setPlaces(places);
        oneOpt.initializeTourTest();

        int[] tour = oneOpt.getTour();

        assertEquals(places.size(), tour.length);
    }

    @Test
    @DisplayName("bodorol: Tour is initialized with correct indices")
    public void testTourInitializationIndices() {
        Places places = new Places();
        places.add(northPole);
        places.add(equatorEast);
        places.add(equatorMiddle);
        places.add(equatorWest);
        places.add(southPole);

        oneOpt.setPlaces(places);
        oneOpt.initializeTourTest();

        Place fourthPlace = oneOpt.getPlaceFromTour(3);

        assertEquals(equatorWest, fourthPlace);
    }

    @Test
    @DisplayName("bodorol: Distances is initialized with correct length")
    public void testDistancesInitializationLength() {
        Places places = new Places();
        places.add(northPole);
        places.add(equatorEast);
        places.add(equatorMiddle);

        oneOpt.setPlaces(places);
        oneOpt.initializeDistancesTest();

        long[][] distance = oneOpt.getDistances();

        assertEquals(places.size(), distance.length);
    }

    @Test
    @DisplayName("bodorol: Distances is initialized with correct distances for same place")
    public void testDistancesInitializationSamePlace() {
        Places places = new Places();
        places.add(northPole);
        places.add(equatorEast);
        places.add(equatorMiddle);

        oneOpt.setPlaces(places);
        oneOpt.initializeTourTest();
        oneOpt.initializeDistancesTest();

        long selfDistance = oneOpt.getDistanceTest(1, 1);

        assertEquals(0, selfDistance);
    }

    @Test
    @DisplayName("bodorol: Distances is initialized with correct distances for different places")
    public void testDistancesInitializationDifferentPlaces() {
        Places places = new Places();
        places.add(northPole);
        places.add(equatorEast);
        places.add(equatorMiddle);

        oneOpt.setPlaces(places);
        oneOpt.initializeTourTest();
        oneOpt.initializeDistancesTest();

        long distance = oneOpt.getDistanceTest(0, 1);

        assertEquals(2, distance);
    }

    @Test
    @DisplayName("bodorol: Index of nearest neighbor is returned")
    public void testGetNearestNeighborIndex() {
        Places places = new Places();
        places.add(northPole);
        places.add(equatorEast);
        places.add(equatorMiddle);
        places.add(northPole);
        places.add(equatorWest);
        places.add(southPole);

        oneOpt.setPlaces(places);
        oneOpt.initializeTourTest();
        oneOpt.initializeDistancesTest();
        
        int nearestToNorthPole = oneOpt.getNearestNeighborIndexTest(0);

        assertEquals(3, nearestToNorthPole);
    }

    @Test
    @DisplayName("bodorol: Index of nearest neighbor is returned, ignoring the used places")
    public void testGetNearestNeighborIndexUnused() {
        Places places = new Places();
        places.add(northPole);
        places.add(equatorEast);
        places.add(equatorMiddle);
        places.add(northPole);
        places.add(equatorWest);
        places.add(southPole);

        oneOpt.setPlaces(places);
        oneOpt.initializeTourTest();
        oneOpt.initializeDistancesTest();
        
        int nearestToNorthPole = oneOpt.getNearestNeighborIndexTest(3);

        assertEquals(4, nearestToNorthPole);
    }    
}