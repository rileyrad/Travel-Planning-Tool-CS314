package com.tco.misc;

public abstract class CalculatorFactory{

    abstract public DistanceCalculator create();
    
    static public DistanceCalculator get(String formula){
        if("haversine".equals(formula)){
            HaversineCreator haversine = new HaversineCreator();
            return haversine.create();
        } else if("cosines".equals(formula)){
            CosinesCreator cosines = new CosinesCreator();
            return cosines.create();
        } else{
            VincentyCreator vincenty = new VincentyCreator();
            return vincenty.create();
        }
    }

}
