package com.tco.misc;

public interface DistanceCalculator {

    public Long between(
        GeographicCoordinate from,
        GeographicCoordinate to,
        double earthRadius);
    
}
