
package com.tco.misc;

public class VincentyCreator extends CalculatorFactory {
    
    public DistanceCalculator create(){
        DistanceCalculator placeholder = new VincentyDistance();
        return placeholder;
    }
    
}
