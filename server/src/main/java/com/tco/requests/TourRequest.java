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
        TourConstruction optimizer = new NoOptimizer();
        places = optimizer.construct(places, earthRadius, formula, response);
        log.trace("buildResponse -> {}", this);
    }
}
