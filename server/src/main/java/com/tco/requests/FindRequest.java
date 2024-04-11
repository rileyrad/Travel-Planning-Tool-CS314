package com.tco.requests;

import com.tco.misc.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;

public class FindRequest extends Request {
    private static final transient Logger log = LoggerFactory.getLogger(FindRequest.class);
    private String match;
    private List<String> type;
    private List<String> where;
    private Integer limit;
    private Integer found;
    private Places places;

    @Override
    public void buildResponse() throws BadRequestException {
        GeographicLocations geoLocations = new GeographicLocations();
        try {
            places = geoLocations.find(match, type, where, limit);
            found = geoLocations.found(match);
        } catch (Exception e) {
            throw new BadRequestException();
        }
        log.trace("buildResponse -> {}", this);
    }
}
