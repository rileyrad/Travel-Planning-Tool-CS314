package com.tco.misc;

import com.tco.requests.Place;

public class Select {

    static String match(String data, String match, int limit) {
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

        
        return statement(data, where, getLimit(limit));
    }
  
    static String near(String data, Place center, Place offset, int limit) {
        double latLowerBound = center.latDegrees() - offset.latDegrees();
        double latUpperBound = center.latDegrees() + offset.latDegrees();
        double lonLowerBound = center.lonDegrees() - offset.lonDegrees();
        double lonUpperBound = center.lonDegrees() + offset.lonDegrees();

        String where =  " WHERE" 
        + " latitude BETWEEN " + latLowerBound + " AND " + latUpperBound 
        + " AND longitude BETWEEN " + lonLowerBound + " AND " + lonUpperBound;

        return statement(data, where, getLimit(limit));
    }

    public static String statement(String data, String where, String limit) {
        return "SELECT " + data
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
