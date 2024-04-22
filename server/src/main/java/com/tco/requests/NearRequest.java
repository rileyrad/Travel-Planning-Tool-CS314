package com.tco.requests;

import com.tco.misc.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NearRequest extends Request {
    private static final transient Logger log = LoggerFactory.getLogger(NearRequest.class);
    private Place place;
    private Long distance;
    private Long earthRadius;
    private Integer limit;
    private Places places;
    private Distances distances;
    
    @Override
    public void buildResponse() throws BadRequestException {
        GeographicLocations geoLocations = new GeographicLocations();
        try{
        places = geoLocations.near(place, distance, earthRadius, limit);
        distances = geoLocations.distances(place, places, earthRadius);
        }catch(Exception e) {
            throw new BadRequestException();
        }
        log.trace("buildResponse -> {}", this);
    }
}
