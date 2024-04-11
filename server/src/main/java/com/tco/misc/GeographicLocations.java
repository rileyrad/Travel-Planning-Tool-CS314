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
    final static String URL = "jdbc:mariadb://127.0.0.1:56247/cs314";
    final static String COLUMNS = "world.id, world.name, world.latitude, world.longitude, world.altitude, world.type, country.name AS country";
    private String formula;

    public GeographicLocations() {
    }

    public GeographicLocations(String formula) {
        this.formula = formula;
    }


    public Places find(String match, List<String> type, List<String> where, Integer limit) {
        return null;
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
                place.put(col, results.getString(col));
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
