package com.tco.requests;

import com.tco.misc.GeographicCoordinate;

import java.util.LinkedHashMap;
import static java.lang.Math.toRadians;


class Place extends LinkedHashMap<String,String> implements GeographicCoordinate {
    
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
