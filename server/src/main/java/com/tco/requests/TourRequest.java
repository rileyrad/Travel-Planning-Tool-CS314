package com.tco.requests;

import com.tco.misc.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TourRequest extends Request {
    private static final transient Logger log = LoggerFactory.getLogger
            (TourRequest.class);
    private Places places;
    private Double earthRadius;
    private String formula;
    private Double response;

    @Override
    public void buildResponse() throws BadRequestException {
        TourConstruction optimizer = new OneOptimizer();
        places = optimizer.construct(places, earthRadius, formula, response);
        log.trace("buildResponse -> {}", this);
    }

    /* The following methods exist only for testing purposes and are not used
     during normal execution, including the constructor. */
     public TourRequest (Places placeList){
        this.requestType = "tour";
        this.places = placeList;
        earthRadius = 3959.0; 
        this.response = 1.0;

     }

     public Places getPlaces(){
        return this.places;
     }
}
