package com.tco.misc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class OptimizerFactory extends TourConstruction {

    private static final transient Logger log = LoggerFactory.getLogger(OptimizerFactory.class);

    public TourConstruction get(int N, Double response) {
        TourConstruction optimizer;
        if (n >= 500 || n == 0 || response == 0) {
            optimizer = new NoOptimizer();
            log.info("NoOptimizer was selected.");
        } else {
            optimizer = new OneOptimizer();
            log.info("OneOptimizer was selected.");
        }
        return optimizer;
    }
}
