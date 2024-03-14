package com.tco.requests;

import com.tco.misc.DistanceCalculator;
import com.tco.misc.VincentyDistance;
import com.tco.misc.CosinesDistance;
import com.tco.misc.HaversineDistance;
import com.tco.misc.CalculatorFactory;
import com.tco.misc.BadRequestException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DistancesRequest extends Request {
    
    private static final transient Logger log = LoggerFactory.getLogger
        (DistancesRequest.class);

    private transient DistanceCalculator calculator;
    private Places places;
    private Double earthRadius;
    private String formula;
    private Distances distances;
    
    @Override
    public void buildResponse() throws BadRequestException{
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

    private void setDistanceCalculator() throws BadRequestException{
        calculator = CalculatorFactory.get(formula);
    }

    /* The following methods exist only for testing purposes and are not used
     during normal execution, including the constructor. */

     public DistancesRequest() {
        this.requestType = "distances";
        places = new Places();
        earthRadius = 3959.0;
     }

     public DistanceCalculator getDistanceCalculator() {
        return calculator;
     }

     public Distances getDistances() {
        return distances;
     }

     public void addPlace(Place place) {
        places.add(place);
     }

     public void setFormula(String formula) {
        this.formula = formula;
     }

     public Double getRadius() {
        return earthRadius;
     }

     public void setRadius(Double earthRadius) {
        this.earthRadius = earthRadius;
     }
}