package com.tco.misc;

public class VincentyCreator extends CalculatorFactory {
    
    public DistanceCalculator create(){
        VincentyDistance vincenty = new VincentyDistance();
        return vincenty;
    }
}