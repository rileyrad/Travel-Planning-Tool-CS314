package com.tco.requests;

import com.tco.misc.GreatCircleDistance;
import com.tco.misc.VincentyDistance;
import com.tco.misc.CosinesDistance;
import com.tco.misc.HaversineDistance;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DistancesRequest extends Request {
    
    private static final transient Logger log = LoggerFactory.getLogger
        (DistancesRequest.class);

    private Places places;
    private Double earthRadius;
    private String formula;
    private GreatCircleDistance calculator;
    private Distances distances;
    
    @Override
    public void buildResponse() {
        
    }

    private Distances buildDistanceList() {
        Distances distances = new Distances();

        // TODO

        return distances;
    }

    private void setDistanceCalculator() {
        if ("havesine".equals(formula)) {
            calculator = new HaversineDistance();
        } else if ("cosines".equals(formula)) {
            calculator = new CosinesDistance();
        } else {
            calculator = new VincentyDistance();
        }
    }

}