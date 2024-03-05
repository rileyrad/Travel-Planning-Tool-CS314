package com.tco.misc;

public abstract class CalculatorFactory {

    abstract public DistanceCalculator create();
    static public DistanceCalculator get(String formula){
        return null;
    }

}
