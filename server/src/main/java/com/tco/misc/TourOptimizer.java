package com.tco.misc;

import com.tco.requests.Place;
import com.tco.requests.Places;

public abstract class TourOptimizer {

    public Places construct(Places places, double radius, String formula, double response) {
        return null;
    }

    public void improve() {}

    private int[] initializeTour(Places places) {
        int[] tour = new int[places.size()];

        for (int i = 0; i < places.size(); i++) {
            tour[i] = i;
        }

        return tour;
    }

    private long[][] initializeDistances(Places places, String formula, double radius) {
        long[][] distances = new long[places.size()][places.size()];

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

        return distances;
    }

    private int getNearestNeighborIndex(int[] tour, int[][] distances, int startIndex, int unusedIndex) {
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
