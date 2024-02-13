package com.tco.requests;

import com.tco.misc.GeographicCoordinate;

import java.util.LinkedHashMap;

class Place extends LinkedHashMap<String,String> implements GeographicCoordinate {
    public Place(double longitude, double latitude) {
        this.longitude = longitude;
        this.latitude = latitude;
    }
    
    public Double lonRadians() { return 0.0; }
    public Double latRadians() { return 0.0; }
}
