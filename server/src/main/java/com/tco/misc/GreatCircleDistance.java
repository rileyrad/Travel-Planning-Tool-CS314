package com.tco.misc;

public interface GreatCircleDistance extends GeographicCoordinate {
    public Long between(GeographicCoordinate from, GeographicCoordinate to, double earthRadius);
}
