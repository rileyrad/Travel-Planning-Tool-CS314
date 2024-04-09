package com.tco.misc;

import com.tco.requests.Places;
import com.tco.requests.Place;
import com.tco.requests.Distances;
import java.util.List;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.DriverManager;

public class GeographicLocations {
    // shared user with read-only access
    final static String USER = "cs314-db";
    final static String PASSWORD = "eiK5liet1uej";
    // connection information when using port forwarding from localhost
    final static String URL = "jdbc:mariadb://127.0.0.1:56247/cs314";


    public Places find(String match, List<String> type, List<String> where, Integer limit, Integer found, Places places) {
        return null;
    }
    
    public Places near(Place place, Long distance, Long earthRadius, Long limit){
        return null;
    }

    public Distances distances(){
        return null;
    }
    public Integer found(){
        return null;
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

    private static Integer count(ResultSet results) throws Exception {
        if (results.next()) {
            return results.getInt("count");
        }
        throw new Exception("No count results in found query.");
    }

    static String url() {
        return URL;
    }
}
