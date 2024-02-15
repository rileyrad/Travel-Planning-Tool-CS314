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
        setDistanceCalculator();
        distances = buildDistanceList();
        log.trace("buildResponse -> {}", this);
    }

    private Distances buildDistanceList() {
        Distances distances = new Distances();

        for (int fromIndex = 0; fromIndex < places.size(); fromIndex++) {
            int toIndex = (fromIndex + 1) % places.size();

            Place fromPlace = places.get(fromIndex);
            Place toPlace = places.get(toIndex);

            Long distance = calculator.between(fromPlace, toPlace, earthRadius);
            
            distances.add(distance);
        }

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

    /* The following methods exist only for testing purposes and are not used
     during normal execution, including the constructor. */

     public DistancesRequest() {
        this.requestType = "distances";
        places = new Places();
     }

     public GreatCircleDistance getDistanceCalculator() {
        return calculator;
     }

     public Distances getDistances() {
        return distances;
     }

}