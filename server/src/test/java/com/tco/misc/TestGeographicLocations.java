package com.tco.misc;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestGeographicLocations {
    private GeographicLocations geoLocations;
    
    @BeforeEach
    public void setup() {
        geoLocations = new GeographicLocations();
    }

    @Test
    @DisplayName("rileyr3: test correct URL")
    public void testURL() {
        String url = "jdbc:mariadb://127.0.0.1:56247/cs314";
        assertEquals(geoLocations.url(), url);
    }

    @Test
    @DisplayName("rileyr3: test count()")
    public void testCount() {
        ResultSet resultSetMock = mock(ResultSet.class);
        when(resultSetMock.next()).thenReturn(true);
        when(resultSetMock.getInt("count")).thenReturn(1);

        int result = geoLocations.database.count(resultSetMock);
        assertEquals(1, result);
    }
}
