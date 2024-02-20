package com.tco.requests;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestDistances {
    
    private Distances dist;

    @BeforeEach
    public void createObjectForTestCases() {
        dist = new Distances();
    }

    @Test
    @DisplayName("rileyr3: Total() returns correct value using multiple places")
    public void testMultiplePlaces() {
        dist.add(372L);
        dist.add(99000L);
        dist.add(2833L);
        dist.add(21L);

        Long idealTotal = 102226L;

        assertEquals(idealTotal, dist.total());
    }

    @Test
    @DisplayName("rileyr3: Total() returns correct value using no places")
    public void testNoPlaces() {
        Long idealTotal = 0L;

        assertEquals(idealTotal, dist.total());
    }

}
