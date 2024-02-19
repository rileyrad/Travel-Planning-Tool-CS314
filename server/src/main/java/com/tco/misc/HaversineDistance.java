package com.tco.misc;
import java.lang.Math;

public class HaversineDistance implements GreatCircleDistance {
    
    public Long between(GeographicCoordinate from, GeographicCoordinate to, double earthRadius) {
            return 0L;
    }

    private Double sinSquaredOfDifferenceHalved(Double subtractFrom, Double subtract){
        Double difference = subtractFrom - subtract;
        Double differenceHalved = difference/2;
        return Math.sin(differenceHalved) * Math.sin(differenceHalved);
    }

    private Double cosSquaredOfSumHalved(Double val1, Double val2){
        Double sum = val1 + val2;
        Double sumHalved = sum/2;
        return Math.cos(sumHalved) + Math.cos(sumHalved);
    }
}
