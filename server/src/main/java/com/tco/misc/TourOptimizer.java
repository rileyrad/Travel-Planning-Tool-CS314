package com.tco.misc;

import com.tco.requests.Place;
import com.tco.requests.Places;

public abstract class TourOptimizer {

    private Places places;
    private double radius;
    private String formula;
    private int[] tour;
    private long[][] distances;

    public Places construct(Places places, double radius, String formula, double response) throws BadRequestException {
        return null;
    }

    public void improve() {}

    private void initializeTour() {
        tour = new int[places.size()];

        for (int i = 0; i < places.size(); i++) {
            tour[i] = i;
        }
    }

    private void initializeDistances() throws BadRequestException {
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

    private void nearestNeighbor() {		
        for (int unusedIndex = 1; unusedIndex < places.size(); unusedIndex++) {
            int nearestNeighborIndex = getNearestNeighborIndex(unusedIndex - 1);
            swapElements(unusedIndex, nearestNeighborIndex);
        }
    }

    private int getNearestNeighborIndex(int startIndex) {
        int nearestNeighborIndex = startIndex + 1;
        long shortestDistance = getDistance(startIndex, nearestNeighborIndex);

        for (int nextIndex = nearestNeighborIndex + 1; nextIndex < tour.length; nextIndex++) {

            long nextDistance = getDistance(startIndex, nextIndex);

            if (nextDistance < shortestDistance) {
                shortestDistance = nextDistance;
                nearestNeighborIndex = nextIndex;
            }
        }

        return nearestNeighborIndex;
    }

    private void swapElements(int index1, int index2) {
        int temp = tour[index1];
        tour[index1] = tour[index2];
        tour[index2] = temp;
    }
	
	private long getDistance(int index1, int index2) {
		int firstCity = tour[index1];
		int nextCity = tour[index2];
		
		return distances[firstCity][nextCity];
	}

}
