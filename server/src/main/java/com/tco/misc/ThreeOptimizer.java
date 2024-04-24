package com.tco.misc;

public class ThreeOptimizer extends TourConstruction {
    
    public void improve() {
    }

    private int threeOptReversals(int i, int j, int k) {
        int reversal = 0;
        
        if (getDistance(i, j) + getDistance(i + 1, j + 1) < getDistance(i, i + 1) + getDistance(j, j + 1)) {
            reversal = 1;
        }

        if (getDistance(j, k) + getDistance(j + 1, k + 1) < getDistance(j, j + 1) + getDistance(k, k + 1)) {
            reversal = 2;
        }

        if (getDistance(i, k) + getDistance(i + 1, k + 1) < getDistance(i, i + 1) + getDistance(k, k + 1)) {
            reversal = 4;
        }

        if (getDistance(i, j) + getDistance(i + 1, k) + getDistance(j + 1, k + 1)
                 < getDistance(i, i + 1) + getDistance(j, j + 1) + getDistance(k, k + 1)) {
            reversal = 3;
        }

        if (getDistance(i, k) + getDistance(j + 1, i + 1) + getDistance(j, k + 1)
                 < getDistance(i, i + 1) + getDistance(j, j + 1) + getDistance(k, k + 1)) {
            reversal = 5;
        }

        if (getDistance(i, j + 1) + getDistance(k, j) + getDistance(i + 1, k + 1)
                 < getDistance(i, i + 1) + getDistance(j, j + 1) + getDistance(k, k + 1)) {
            reversal = 6;
        }

        if (getDistance(i, j + 1) + getDistance(k, i + 1) + getDistance(j, k + 1)
                 < getDistance(i, i + 1) + getDistance(j, j + 1) + getDistance(k, k + 1)) {
            reversal = 7;
        }

        return reversal;
    }
}
