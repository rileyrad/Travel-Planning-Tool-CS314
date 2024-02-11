package com.tco.requests;

import com.tco.misc.GeographicCoordinate;

import java.util.LinkedHashMap;

class Place extends LinkedHashMap<String,String> implements GeographicCoordinate {
    
    public Double lonRadians() { return 0.0; }
    public Double latRadians() { return 0.0; }
}
