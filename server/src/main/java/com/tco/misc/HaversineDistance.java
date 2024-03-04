package com.tco.misc;
import java.lang.Math;

public class HaversineDistance implements DistanceCalculator {
    
    public HaversineDistance() {}

    public Long between(GeographicCoordinate from, GeographicCoordinate to, double earthRadius) {
        Double fromLatitude = from.latRadians();
        Double fromLongitude = from.lonRadians();
        Double toLatitude = to.latRadians();
        Double toLongitude = to.lonRadians();
        Double centralAngleCalculation = cosSquaredOfSumHalved(fromLatitude,toLatitude) - sinSquaredOfDifferenceHalved(fromLatitude,toLatitude);
        centralAngleCalculation = centralAngleCalculation * sinSquaredOfDifferenceHalved(fromLongitude,toLongitude);
        centralAngleCalculation = centralAngleCalculation + sinSquaredOfDifferenceHalved(fromLatitude,toLatitude);
        centralAngleCalculation = 2 * Math.asin(Math.sqrt(centralAngleCalculation));
        double distanceBetween = Math.round(centralAngleCalculation * earthRadius);
        return (long) distanceBetween;
    }

    private Double sinSquaredOfDifferenceHalved(Double subtractFrom, Double subtract){
        Double absoluteDifference = Math.abs(subtractFrom - subtract);
        Double differenceHalved = absoluteDifference/2;
        return Math.sin(differenceHalved) * Math.sin(differenceHalved);
    }

    private Double cosSquaredOfSumHalved(Double val1, Double val2){
        Double sum = val1 + val2;
        Double sumHalved = sum/2;
        return Math.cos(sumHalved) * Math.cos(sumHalved);
    }
}
