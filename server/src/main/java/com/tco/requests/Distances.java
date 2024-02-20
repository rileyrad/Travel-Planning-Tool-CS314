package com.tco.requests;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Distances extends ArrayList<Long> {

    private static final transient Logger log = LoggerFactory.getLogger(Distances.class);

    public Long total() {
        long total = 0;
        for (Long distance : this) {
            total += distance;
        }
        log.info("Total distance: " + total);
        return total;
    }
}
