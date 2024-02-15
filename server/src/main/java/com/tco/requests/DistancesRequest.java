package com.tco.requests;

import com.tco.misc.GreatCircleDistance;
import com.tco.misc.VincentyCalculator;
import com.tco.misc.CosineCalculator;
import com.tco.misc.HaversineCalculator;

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

}