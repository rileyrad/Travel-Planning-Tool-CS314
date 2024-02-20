package com.tco.misc;

public class CosinesDistance implements GreatCircleDistance {
    public CosinesDistance(){};

    public Long between(GeographicCoordinate from, GeographicCoordinate to, double earthRadius) {
        double from_lon = from.lonRadians();
        double from_lat = from.latRadians();
        double to_lon = to.lonRadians();
        double to_lat = to.latRadians();

        Long distance = (long) (earthRadius * Math.acos(Math.sin(from_lat) * Math.sin(to_lat) + 
                Math.cos(from_lat) * Math.cos(to_lat) * Math.cos(from_lon - to_lon)));
                
        return distance;
    }


}