package com.tco.misc;

import java.lang.Exception;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.DriverManager;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

import com.tco.requests.Place;
import com.tco.requests.Places;
import com.tco.requests.Distances;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestGeographicLocations {
    private GeographicLocations geoLocations;
    
    @BeforeEach
    public void setup() {
        geoLocations = new GeographicLocations();
    }

    @Test
    @DisplayName("rileyr3: test correct URL.")
    public void testURL() {
        String url = "jdbc:mariadb://127.0.0.1:56247/cs314";
        assertEquals(geoLocations.url(), url);
    }

    @Test
    @DisplayName("rileyr3: test found().")
    public void testFound() throws Exception {
        ResultSet resultSetMock = mock(ResultSet.class);
        when(resultSetMock.next()).thenReturn(true);
        when(resultSetMock.getInt("count")).thenReturn(1);

        Statement statementMock = mock(Statement.class);
        when(statementMock.executeQuery(anyString())).thenReturn(resultSetMock);

        Connection connectionMock = mock(Connection.class);
        when(connectionMock.createStatement()).thenReturn(statementMock);

        mockStatic(DriverManager.class);
        when(DriverManager.getConnection(anyString(), anyString(), anyString())).thenReturn(connectionMock);

        int result = geoLocations.found("match");
        assertEquals(1, result);
    }

    @Test
    @DisplayName("rileyr3: test count().")
    public void testCount() throws Exception {
        ResultSet resultSetMock = mock(ResultSet.class);
        when(resultSetMock.next()).thenReturn(true);
        when(resultSetMock.getInt("count")).thenReturn(1);

        int result = geoLocations.count(resultSetMock);
        assertEquals(1, result);
    }

    @Test
    @DisplayName("bodorol: Distances returns correct distances")
    public void testDistances() throws Exception {
        Place northPole = new Place("90", "0");
        Place southPole = new Place("-90", "0");
        Place equatorMiddle = new Place("0", "0");
        Place equatorWest = new Place("0", "-90");
        Place equatorEast = new Place("0", "90");
        Places places = new Places();
        places.add(southPole);
        places.add(equatorMiddle);
        places.add(equatorWest);
        places.add(equatorEast);

        Distances distances = geoLocations.distances(northPole, places, 1);

        assertEquals(3, distances.get(0));
    }

    @Test
    @DisplayName("bodorol: Distances returns correct size")
    public void testDistancesSize() throws Exception {
        Place northPole = new Place("90", "0");
        Place southPole = new Place("-90", "0");
        Place equatorMiddle = new Place("0", "0");
        Place equatorWest = new Place("0", "-90");
        Place equatorEast = new Place("0", "90");
        Places places = new Places();
        places.add(southPole);
        places.add(equatorMiddle);
        places.add(equatorWest);
        places.add(equatorEast);

        Distances distances = geoLocations.distances(northPole, places, 1);

        assertEquals(4, distances.size());
    }

    @Test
    @DisplayName("bodorol: Distances returns empty list")
    public void testDistancesNoPlaces() throws Exception {
        Places places = new Places();
        Place northPole = new Place("90", "0");

        Distances distances = geoLocations.distances(northPole, places, 1);

        assertTrue(distances.isEmpty());
    }
}
