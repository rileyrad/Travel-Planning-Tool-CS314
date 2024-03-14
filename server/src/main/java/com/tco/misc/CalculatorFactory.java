package com.tco.misc;

public abstract class CalculatorFactory {

    abstract public DistanceCalculator create();
    
    static public DistanceCalculator get(String formula) throws BadRequestException{
        if("haversine".equals(formula)){
            HaversineCreator haversine = new HaversineCreator();
            return haversine.create();
        } else if("cosines".equals(formula)){
            CosinesCreator cosines = new CosinesCreator();
            return cosines.create();
        } else if(("vincenty".equals(formula)) || null == formula){
            VincentyCreator vincenty = new VincentyCreator();
            return vincenty.create();
        } else{
            throw new BadRequestException();
        }
    }

}
