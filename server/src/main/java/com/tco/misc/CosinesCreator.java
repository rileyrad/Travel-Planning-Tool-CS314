package com.tco.misc;

public class CosinesCreator extends CalculatorFactory {
    
    public DistanceCalculator create(){
        CosinesDistance cosines = new CosinesDistance();
        return cosines;
    }
}
