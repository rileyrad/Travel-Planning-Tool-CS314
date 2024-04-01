package com.tco.requests;

import com.tco.misc.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NearRequest extends Request {
    private static final transient Logger log = LoggerFactory.getLogger(NearRequest.class);
    private Place place;
    private Integer distance;
    private Double earthRadius;
    private Integer limit;
    private Places places;
    private Distances distances;
    
    @Override
    public void buildResponse() throws BadRequestException {

    }
}
