package com.tco.misc;

public class HaversineCreator extends CalculatorFactory {
    
    public DistanceCalculator create(){
        HaversineDistance haversine = new HaversineDistance();
        return haversine;
    }
}
