package com.tco.misc;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import com.tco.requests.Place;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestSelect {
    
    @Test
    @DisplayName("bodorol: Match returns correct query")
    public void testMatchWithPlace(){
        String match = Select.match(GeographicLocations.COLUMNS, "airport", 10);
        String query = "SELECT world.id, world.name, world.latitude, world.longitude, world.altitude, world.type, country.name AS country"
        + " FROM world"
        + " INNER JOIN continent ON world.continent = continent.id"
        + " INNER JOIN country ON world.iso_country = country.id"
        + " INNER JOIN region ON world.iso_region = region.id"
        + " WHERE (world.name LIKE '%airport%'" 
        + " OR world.id LIKE '%airport%'"
        + " OR region.name LIKE '%airport%'"
        + " OR world.municipality LIKE '%airport%'"
        + " OR country.name LIKE '%airport%')"
        + " LIMIT 10"
        + " ;";

        assertEquals(query, match);
    }

    @Test
    @DisplayName("bodorol: Match returns query with random places")
    public void testMatchWithEmptyPlace(){
        String match = Select.match(GeographicLocations.COLUMNS, "", 10);
        String query = "SELECT world.id, world.name, world.latitude, world.longitude, world.altitude, world.type, country.name AS country"
        + " FROM world"
        + " INNER JOIN continent ON world.continent = continent.id"
        + " INNER JOIN country ON world.iso_country = country.id"
        + " INNER JOIN region ON world.iso_region = region.id"
        + " ORDER BY RAND()"
        + " LIMIT 10"
        + " ;";

        assertEquals(query, match);
    }

    @Test
    @DisplayName("bodorol: Match returns query with limit of 100")
    public void testMatchWithNoLimit(){
        String match = Select.match(GeographicLocations.COLUMNS, "airport", 0);
        String query = "SELECT world.id, world.name, world.latitude, world.longitude, world.altitude, world.type, country.name AS country"
        + " FROM world"
        + " INNER JOIN continent ON world.continent = continent.id"
        + " INNER JOIN country ON world.iso_country = country.id"
        + " INNER JOIN region ON world.iso_region = region.id"
        + " WHERE (world.name LIKE '%airport%'" 
        + " OR world.id LIKE '%airport%'"
        + " OR region.name LIKE '%airport%'"
        + " OR world.municipality LIKE '%airport%'"
        + " OR country.name LIKE '%airport%')"
        + " LIMIT 100"
        + " ;";

        assertEquals(query, match);
    }

    @Test
    @DisplayName("bodorol: Find returns query with limit of 100")
    public void testFindWithNoLimit(){
        Place origin = new Place("0.0", "0.0");
        Place offset = new Place("0.5", "0.5");
        String match = Select.near(GeographicLocations.COLUMNS, origin, offset, 10);
        String query = "SELECT world.id, world.name, world.latitude, world.longitude, world.altitude, world.type, country.name AS country"
        + " FROM world"
        + " INNER JOIN continent ON world.continent = continent.id"
        + " INNER JOIN country ON world.iso_country = country.id"
        + " INNER JOIN region ON world.iso_region = region.id"
        + " WHERE latitude BETWEEN -0.5 AND 0.5 AND longitude BETWEEN -0.5 AND 0.5"
        + " LIMIT 10"
        + " ;";

        assertEquals(query, match);
    }

    @Test
    @DisplayName("bodorol: Find returns correct query")
    public void testFindWithPlace(){
        Place origin = new Place("0.0", "0.0");
        Place offset = new Place("0.5", "0.5");
        String match = Select.near(GeographicLocations.COLUMNS, origin, offset, 0);
        String query = "SELECT world.id, world.name, world.latitude, world.longitude, world.altitude, world.type, country.name AS country"
        + " FROM world"
        + " INNER JOIN continent ON world.continent = continent.id"
        + " INNER JOIN country ON world.iso_country = country.id"
        + " INNER JOIN region ON world.iso_region = region.id"
        + " WHERE latitude BETWEEN -0.5 AND 0.5 AND longitude BETWEEN -0.5 AND 0.5"
        + " LIMIT 100"
        + " ;";

        assertEquals(query, match);
    }
}
