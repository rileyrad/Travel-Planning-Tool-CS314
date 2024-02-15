package com.tco.requests;

import com.tco.misc.GeographicCoordinate;

import java.util.LinkedHashMap;
import static java.lang.Math.toRadians;


class Place extends LinkedHashMap<String,String> implements GeographicCoordinate {
    
    public Place(String latitude, String longitude) {
        this.put("latitude" , latitude);
        this.put("longitude", longitude);
    }
    
    public Double lonRadians() { return 0.0; }
    
    public Double latRadians() { 
        String latitudeStr = this.get("latitude");
        Double latitudeDouble = Double.parseDouble(latitudeStr);
        return toRadians(latitudeDouble); 
    }
}
