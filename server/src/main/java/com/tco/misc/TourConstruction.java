package com.tco.misc;

import com.tco.requests.Place;
import com.tco.requests.Places;

public abstract class TourConstruction {

    private Places places;
    private double radius;
    private String formula;
    private int[] tour;
    private long[][] distances;

    public Places construct(Places places, double radius, String formula, double response) throws BadRequestException {
		this.places = places;
        this.radius = radius;
        this.formula = formula;

        initializeTour();
        initializeDistances();

        long shortestDistance = getTourDistance();
        int[] shortestTour = tour.clone();

        for (int i = 0; i < places.size(); i++) {
            setTourStart(i);
            nearestNeighbor();
            improve();

            long tourDistance = getTourDistance();

            if (tourDistance < shortestDistance) {
                shortestDistance = tourDistance;
                shortestTour = tour.clone();
            }
        }

        tour = shortestTour;
        return tourToPlaces();
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

    private Places tourToPlaces() {
        int startIndex = getTourIndex(0);
        Places optimizedPlaces = new Places();

        for (int i = 0; i < tour.length; i++) {
            int tourIndex = (startIndex + i) % tour.length;
            int placesIndex = tour[tourIndex];
            Place nextPlace = places.get(placesIndex);
            optimizedPlaces.add(nextPlace);
        }

        return optimizedPlaces;
    }

    private long getTourDistance() {
        long totalDistance = 0;

        for (int i = 0; i < tour.length - 1; i++) {
            totalDistance += getDistance(i, i + 1);
        }

        totalDistance += getDistance(tour.length - 1, 0);

        return totalDistance;
    }

    private void setTourStart(int placesIndex) {
        int tourIndex = getTourIndex(placesIndex);
        swapElements(0, placesIndex);
    }

    private int getTourIndex(int placesIndex) {
        for (int i = 0; i < tour.length; i++) {
            if (tour[i] == placesIndex) {
                return i;
            }
        }

        return -1;
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

    protected void swapElements(int index1, int index2) {
        int temp = tour[index1];
        tour[index1] = tour[index2];
        tour[index2] = temp;
    }
	
	protected long getDistance(int index1, int index2) {
		int firstCity = tour[index1];
		int nextCity = tour[index2];
		
		return distances[firstCity][nextCity];
	}

    public int getTourLength() {
        return tour.length;
    }
    
    /* The following methods exist only for testing purposes and are not used
     during normal execution, including the constructor. */

     public void setRadius(double radius) {
        this.radius = radius;
     }

     public void setPlaces(Places places) {
        this.places = places;
     }

     public long[][] getDistances() {
        return distances;
     }

     public int[] getTour() {
        return tour;
     }

     public Place getPlaceFromPlaces(int index) {
        return places.get(index);
     }

     public Place getPlaceFromTour(int index) {
        int placeIndex = tour[index];
        return places.get(placeIndex);
     }

     public void initializeTourTest() {
        initializeTour();
     }

     public void initializeDistancesTest() {
        try {
            initializeDistances();
        } catch (Exception e) {

        }
     }

     public long getDistanceTest(int index1, int index2) {
        return getDistance(index1, index2);
     }

     public void nearestNeighborTest() {
        nearestNeighbor();
     }

     public void swapElementsTest(int index1, int index2) {
        swapElements(index1, index2);
     }

     public int getNearestNeighborIndexTest(int index) {
        return getNearestNeighborIndex(index);
     }

     public Places tourToPlacesTest() {
        return tourToPlaces();
     }

     public long getTourDistanceTest() {
        return getTourDistance();
     }

     public int getTourIndexTest(int placesIndex) {
        return getTourIndex(placesIndex);
     }

     public void setTourStartTest(int placesIndex) {
        setTourStart(placesIndex);
     }

     public void constructTest() {
        try {
            places = construct(places, radius, formula, 1.0);
        } catch (Exception e) {

        }
     }
}
