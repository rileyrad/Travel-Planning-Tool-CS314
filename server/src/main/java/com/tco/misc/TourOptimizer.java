package com.tco.misc;

import com.tco.requests.Place;
import com.tco.requests.Places;

public abstract class TourOptimizer {

    private Places places;
    private double radius;
    private String formula;
    private int[] tour;
    private long[][] distances;
    private int unusedIndex;

    public Places construct(Places places, double radius, String formula, double response) {
        return null;
    }

    public void improve() {}

    private void initializeTour() {
        tour = new int[places.size()];

        for (int i = 0; i < places.size(); i++) {
            tour[i] = i;
        }
    }

    private void initializeDistances() {
        distances = new long[places.size()][places.size()];

        DistanceCalculator calculator = CalculatorFactory.get(formula);

        for (int fromIndex = 0; fromIndex < places.size() - 1; fromIndex++) {
            for (int toIndex = fromIndex + 1; toIndex < places.size(); toIndex++) {
                Place fromPlace = places.get(fromIndex);
                Place toPlace = places.get(toIndex);

                long distance = calculator.between(fromPlace, toPlace, radius);

                distances[fromIndex][toIndex] = distance;
                distances[toIndex][fromIndex] = distance;
            }
        }
    }

    private int getNearestNeighborIndex(int startIndex) {
        int startCity = tour[startIndex];
        int nextCity = tour[unusedIndex];

        int nearestNeighborIndex = unusedIndex;
        long shortestDistance = distances[startCity][nextCity];

        for (int nextIndex = unusedIndex + 1; nextIndex < tour.length; nextIndex++) {
            nextCity = tour[nextIndex];
            long nextDistance = distances[startCity][nextCity];

            if (nextDistance < shortestDistance) {
                shortestDistance = nextDistance;
                nearestNeighborIndex = nextIndex;
            }
        }

        return nearestNeighborIndex;
    }
    
}
