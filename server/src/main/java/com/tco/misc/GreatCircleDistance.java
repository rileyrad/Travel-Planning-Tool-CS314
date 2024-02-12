package com.tco.misc;

public interface GreatCircleDistance extends GeographicCoordinate {
    public double between(GeographicCoordinate from, GeographicCoordinate to, double earthRadius);
}
