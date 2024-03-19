package com.tco.misc;

public class CalculatorFactory {
    
    static public DistanceCalculator get(String formula) throws BadRequestException {
        if("haversine".equals(formula)) {
            HaversineDistance haversine = new HaversineDistance();
            return haversine;
        } else if("cosines".equals(formula)) {
            CosinesDistance cosines = new CosinesDistance();
            return cosines;
        } else if(("vincenty".equals(formula)) || null == formula) {
            VincentyDistance vincenty = new VincentyDistance();
            return vincenty;
        } else {
            throw new BadRequestException();
        }
    }

}
