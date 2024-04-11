package com.tco.misc;

import com.tco.requests.Places;
import com.tco.requests.Place;
import com.tco.requests.Distances;
import java.util.List;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.DriverManager;
import java.lang.Exception;

public class GeographicLocations {
    // shared user with read-only access
    final static String USER = "cs314-db";
    final static String PASSWORD = "eiK5liet1uej";
    // connection information when using port forwarding from localhost
    final static String URL = "jdbc:mariadb://faure.cs.colostate.edu/cs314";
    final static String COLUMNS = "world.id,world.name,world.latitude,world.longitude,world.altitude,world.type";
    private String formula;

    public GeographicLocations() {
    }

    public GeographicLocations(String formula) {
        this.formula = formula;
    }

    private String getLimit(Integer limit) {
        if (limit == null || limit == 0) {
            limit = 100;
        }
        return " LIMIT " + limit;
    }

    public Places find(String match, List<String> type, List<String> where, Integer limit) throws Exception {
        String whereClause = buildWhereClause(match, type, where);
        String sql = Select.statement(COLUMNS, whereClause, getLimit(limit));
        return places(sql);
    }

    private String buildWhereClause(String match, List<String> type, List<String> where) {
        StringBuilder whereBuilder = new StringBuilder();
    
        if (!match.isEmpty()) {
            whereBuilder.append(" AND (world.name LIKE '%").append(match).append("%'")
                    .append(" OR world.id LIKE '%").append(match).append("%'")
                    .append(" OR region.name LIKE '%").append(match).append("%'")
                    .append(" OR world.municipality LIKE '%").append(match).append("%'")
                    .append(" OR country.name LIKE '%").append(match).append("%')");
        } else {
            whereBuilder.append(" ORDER BY RAND()");
        }
    
        if (type != null && !type.isEmpty()) {
            whereBuilder.append(" AND world.type IN (");
            for (String t : type) {
                whereBuilder.append("'").append(t).append("', ");
            }
            whereBuilder.deleteCharAt(whereBuilder.length() - 1); // Remove the last comma
            whereBuilder.deleteCharAt(whereBuilder.length() - 1); // Remove the extra space
            whereBuilder.append(")");
        }
    
        if (where != null && !where.isEmpty()) {
            for (String condition : where) {
                whereBuilder.append(" AND ").append(condition);
            }
        }
    
        return whereBuilder.toString();
    }
    
    public Places near(Place place, Long distance, Long earthRadius, Long limit) {
        return null;
    }

    public Distances distances(Place place, Places places, long earthRadius) throws BadRequestException {
        Distances distances = new Distances();
        DistanceCalculator calculator = CalculatorFactory.get(formula);

        for (int i = 0; i < places.size(); i++) {
            long distance = calculator.between(place, places.get(i), earthRadius);
            distances.add(distance);
        }

        return distances;
    }

    public Integer found(String match) throws Exception {
        String sql = Select.found(match);
		    ResultSet results = performQuery(sql);
        return count(results);
    }

    private ResultSet performQuery (String sql) throws Exception {
        try (
              Connection conn = DriverManager.getConnection(url(), USER, PASSWORD);
              Statement query = conn.createStatement();
        ) {
            ResultSet results = query.executeQuery(sql);
            return results;
        } catch (Exception e) {
            throw e;
        }
    }

    private Places places(String sql) throws Exception {
        ResultSet results = performQuery(sql);
        String columns = COLUMNS;
        int count = 0;
        String[] cols = columns.split(",");
        Places places = new Places();
        while (results.next()) {
            Place place = new Place();
            for (String col : cols) {
                place.put(col.substring(col.lastIndexOf(".") + 1), results.getString(col));
            }
            place.put("index", String.format("%d", ++count));
            places.add(place);
        }
        return places;
    }

    private double getLongitudeOffset(Place place, double distance, long earthRadius) {
        try {
            DistanceCalculator calculator = CalculatorFactory.get("vincenty");
            double offsetLon = Double.parseDouble(place.get("longitude")) + 1;
            Place offsetPlace = new Place(place.get("latitude"), "" + offsetLon);

            long offsetDistance = calculator.between(place, offsetPlace, earthRadius);

            return distance / offsetDistance;
        } catch (Exception e) {
            return 0;
        }
    }

    public static Integer count(ResultSet results) throws Exception {
        if (results.next()) {
            return results.getInt("count");
        }
        throw new Exception("No count results in found query.");
    }

    static String url() {
        return URL;
    }
}
