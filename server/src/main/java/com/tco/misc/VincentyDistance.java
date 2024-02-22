package com.tco.misc;

import java.lang.Math;

public class VincentyDistance implements GreatCircleDistance{
    
    public VincentyDistance() {}
    
    public Long between(GeographicCoordinate from, GeographicCoordinate to, double earthRadius){
        double fromLatitude = from.latRadians();
        double toLatitude = to.latRadians();
        double longitudeDifference = from.lonRadians() - to.lonRadians();

        double numerator = computeNumerator(fromLatitude,toLatitude,longitudeDifference);
        double denominator = computeDenominator(fromLatitude,toLatitude,longitudeDifference);
        double distance = Math.round(earthRadius * Math.atan2(numerator,denominator));

        return (long) distance;
    }

    private double computeNumerator(double fromLatitude, double toLatitude, double longitudeDifference) {
        double firstTerm = Math.cos(toLatitude) * Math.sin(longitudeDifference);
        double secondTerm = Math.cos(fromLatitude) * Math.sin(toLatitude) - Math.sin(fromLatitude) * Math.cos(toLatitude) * Math.cos(longitudeDifference);
        double sumOfSquaredTerms = Math.pow(firstTerm, 2) + Math.pow(secondTerm, 2);
        return Math.sqrt(sumOfSquaredTerms);
    }

    private double computeDenominator(double fromLatitude, double toLatitude, double longitudeDifference) {
        double sinProduct = Math.sin(fromLatitude) * Math.sin(toLatitude);
        double cosProduct = Math.cos(fromLatitude) * Math.cos(toLatitude) * Math.cos(longitudeDifference);
        return sinProduct + cosProduct;
    }
}
