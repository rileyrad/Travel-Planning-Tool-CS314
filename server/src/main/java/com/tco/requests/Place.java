package com.tco.requests;

import com.tco.misc.GeographicCoordinate;

import java.util.HashMap;
import java.util.Map;
import static java.lang.Math.toRadians;


class Place extends HashMap<String,String> implements GeographicCoordinate, Map<String, String> {

    public Place() {}
    
    public Place(String latitude, String longitude) {
        this.put("latitude" , latitude);
        this.put("longitude", longitude);
    }
    
    public Double lonRadians() { 
        return stringToRadian(this.get("longitude")); 
    }
    
    public Double latRadians() { 
        return stringToRadian(this.get("latitude"));
    }

    private Double stringToRadian(String latLon){
        Double toDouble = Double.parseDouble(latLon);
        return toRadians(toDouble);
    }
}
