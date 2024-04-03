package com.tco.misc;

public class Select {
    private static final String COLUMNS = "world.name";

    static String match(String match, int limit) {
        match = "%" + match + "%";
        String where = " WHERE world.name LIKE " + match
                    + " OR world.id LIKE " + match
                    + " OR region.name LIKE " + match
                    + " OR world.municipality LIKE " + match
                    + " OR country.name LIKE " + match;
        return statement(where, " LIMIT " + limit);
    }

    static String statement(String where, String limit) {
        return "SELECT " + COLUMNS
                + " FROM world "
                + " INNER JOIN continent ON world.continent = continent.id"
                + " INNER JOIN country ON world.iso_country = country.id"
                + " INNER JOIN region ON world.iso_region = region.id"
                + where
                + limit
                + " ;";
    }
}
