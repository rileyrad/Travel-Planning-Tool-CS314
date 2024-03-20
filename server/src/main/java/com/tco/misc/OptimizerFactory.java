package com.tco.misc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class OptimizerFactory extends TourConstruction {

    private static final transient Logger log = LoggerFactory.getLogger(OptimizerFactory.class);

    public TourConstruction get(int N, Double response) {
        TourConstruction optimizer = new NoOptimizer();
        return optimizer;
    }
}
