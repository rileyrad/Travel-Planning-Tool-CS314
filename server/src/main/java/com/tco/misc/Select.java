package com.tco.misc;

import com.tco.requests.Place;

public class Select {
    private static final String COLUMNS = "world.id, world.name, world.latitude, world.longitude, world.altitude, world.type, country.name AS country";

    static String match(String match, int limit) {
        String where;
        if ("".equals(match)) {
            where = " ORDER BY RAND()";
        } else {
            match = "'%" + match + "%'";
            where = " WHERE (world.name LIKE " + match
                    + " OR world.id LIKE " + match
                    + " OR region.name LIKE " + match
                    + " OR world.municipality LIKE " + match
                    + " OR country.name LIKE " + match + ")";
        }

        
        return statement(where, getLimit(limit));
    }
  
    static String near(Place center, double latOffset, double lonOffset, int limit) {
        double latLowerBound = center.latDegrees() - latOffset;
        double latUpperBound = center.latDegrees() + latOffset;
        double lonLowerBound = center.lonDegrees() - lonOffset;
        double lonUpperBound = center.lonDegrees() + lonOffset;

        String where =  " WHERE" 
        + " latitude BETWEEN " + latLowerBound + " AND " + latUpperBound 
        + " AND longitude BETWEEN " + lonLowerBound + " AND " + lonUpperBound;

        return statement(where, getLimit(limit));
    }

    static String found(String match) {
        return statement(match, "");
    }

    static String statement(String where, String limit) {
        return "SELECT " + COLUMNS
                + " FROM world"
                + " INNER JOIN continent ON world.continent = continent.id"
                + " INNER JOIN country ON world.iso_country = country.id"
                + " INNER JOIN region ON world.iso_region = region.id"
                + where
                + limit
                + " ;";
    }

    private static String getLimit(int limit) {
        if (limit == 0) {
            limit = 100;
        }

        return " LIMIT " + limit;
    }
}
