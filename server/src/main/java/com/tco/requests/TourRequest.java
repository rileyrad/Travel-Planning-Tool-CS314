package com.tco.requests;

import com.tco.misc.OptimizerFactory;
import com.tco.misc.NoOptimizer;
import com.tco.misc.OneOptimizer;
import com.tco.misc.TwoOptimizer;
import com.tco.misc.ThreeOptimizer;
import com.tco.misc.TourOptimizer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TourRequest extends Request {
    
    private Places places;
    private Double earthRadius;
    private String formula;
    private Double response;

    @Override
    public void buildResponse() {
    }
}
