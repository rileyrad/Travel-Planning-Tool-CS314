package com.tco.misc;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestCosinesDistance {
    private List<GeographicCoordinate> testLocations;
    private List<Long> expectedDistances;
    private double earthRadius;
    private CosinesDistance cosinesCalculator;

    @BeforeEach
    public void setup() {
        testLocations = Arrays.asList(new Geo(-90, 0), new Geo(-90, -180), new Geo(90, 0), new Geo(25.314, 25.314));
        expectedDistances = Arrays.asList(0L, 20038L, 7201L);
        earthRadius = 6378.14;
        cosinesCalculator = new CosinesDistance();
    }

    @Test
    @DisplayName("Test distance between same location with different longitude values")
    public void testDistanceBetweenFirstAndSecondLocation() {
        long calculatedDistance = cosinesCalculator.between(testLocations.get(0), testLocations.get(1), earthRadius);
        assertEquals(expectedDistances.get(0), calculatedDistance);
    }

    @Test
    @DisplayName("Test distance calculation between North Pole and South Pole")
    public void testDistanceBetweenSecondAndThirdLocation() {
        long calculatedDistance = cosinesCalculator.between(testLocations.get(1), testLocations.get(2), earthRadius);
        assertEquals(expectedDistances.get(1), calculatedDistance);
    }

    @Test
    @DisplayName("Test distance calculation with decimal degrees")
    public void testDistanceBetweenThirdAndFourthLocation() {
        long calculatedDistance = cosinesCalculator.between(testLocations.get(2), testLocations.get(3), earthRadius);
        assertEquals(expectedDistances.get(2), calculatedDistance);
    }
    
    private class Geo implements GeographicCoordinate {
        private double lat;
        private double lon;

        private Geo(double lat, double lon) {
            this.lat = lat;
            this.lon = lon;
        }

        public Double latRadians() {
            return Math.toRadians(this.lat);
        }

        public Double lonRadians() {
            return Math.toRadians(this.lon);
        }
    }
}
