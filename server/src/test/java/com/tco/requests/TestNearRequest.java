package com.tco.requests;

import com.tco.misc.BadRequestException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class TestNearRequest {

    private NearRequest near;


    @BeforeEach
    public void createNear() {
        near = new NearRequest();
        try {
            near.buildResponse();
        } catch (BadRequestException e) {
            e.printStackTrace();
        }
    }

    @Test
    @DisplayName("zjp32324: Request type is \"near\"")
    public void testType() {
        String type = near.getRequestType();
        assertEquals("near", type);
    }

    @Test
    @DisplayName("zjp32324: Near request with valid parameters")
    public void testValidNearRequest() {
        Place place = new Place("40.7128", "-74.0060");

        near.setPlace(place);
        near.setDistance(100L);
        near.setEarthRadius(3959L);
        near.setLimit(10);
        
        Places places = near.getPlaces();
        
        assertTrue(!places.isEmpty());
    }

    @Test
    @DisplayName("zjp32324: Near request with invalid distance")
    public void testInvalidDistance() {
        Place place = new Place("34.0549", "-118.2426");
        near.setPlace(place);
        near.setDistance(-100L);
        near.setEarthRadius(3959L);
        near.setLimit(10);
        Exception exception = assertThrows(BadRequestException.class, () -> {
            near.buildResponse();
        });
    }

    @Test
    @DisplayName("zjp32324: Near request with null place")
    public void testNullPlace() {
        near.setDistance(100L);
        near.setEarthRadius(3959L);
        near.setLimit(10);
        Exception exception = assertThrows(BadRequestException.class, () -> {
            near.buildResponse();
        });
    }
}
