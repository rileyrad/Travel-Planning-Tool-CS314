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

    /* The following methods exist only for testing purposes and are not used
     during normal execution, including the constructor. */
    
    public void setPlace(Place place) {
        places.add(place);
    }

    public void setEarthRadius(Long earthRadius) {
        this.earthRadius = earthRadius;
    }

    public void setDistance(Long dist){
        this.distance = dist;
    }

    public void setLimit(Integer limit){
        this.limit = limit;
    }

    public Places getPlaces(){
        return places;
    }

    public NearRequest(){
        this.requestType = "near";
        places = new Places();
        earthRadius = 3959L;
    }
}