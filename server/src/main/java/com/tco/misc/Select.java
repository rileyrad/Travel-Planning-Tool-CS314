package com.tco.misc;

public class Select {
    private static final String COLUMNS = "world.id, world.name, world.latitude, world.longitude, world.altitude, world.type, country.name AS country";

    static String match(String match, int limit) {
        String where;
        if ("".equals(match)) {
            where = " ORDER BY RAND()";
        } else {
            match = "%" + match + "%";
            where = " WHERE world.name LIKE " + match
                    + " OR world.id LIKE " + match
                    + " OR region.name LIKE " + match
                    + " OR world.municipality LIKE " + match
                    + " OR country.name LIKE " + match;
        }

        
        return statement(where, getLimit(limit));
    }

    public static String statement(String where, String limit) {
        return "SELECT " + COLUMNS
                + " FROM world "
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
