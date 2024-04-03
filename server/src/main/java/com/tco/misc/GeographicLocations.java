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
    public Places find(String match, List<String> type, List<String> where, Integer limit, Integer found, Places places) {
        return null;
    }
    
    public Places near(Place place, Long distance, Long earthRadius, Long limit){
        return null;
    }

    public Distances distances(){
        return null;
    }
}
