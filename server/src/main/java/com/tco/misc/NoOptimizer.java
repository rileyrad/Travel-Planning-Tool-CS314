package com.tco.misc;

import com.tco.requests.Places;

public class NoOptimizer extends TourConstruction {
    @Override
    public Places construct(Places places, double radius, String formula, double response) throws BadRequestException {
        return places;
    }
}
