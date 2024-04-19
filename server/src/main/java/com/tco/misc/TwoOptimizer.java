package com.tco.misc;

public class TwoOptimizer extends TourConstruction {
    
    public void improve() {
    }

    private boolean swapImproves(int i, int k) {
        return getDistance(i, k) + getDistance(i + 1, k + 1) < getDistance(i, i + 1) + getDistance(k, k + 1);
    }

    private void twoOptSwap(int i, int k) {
        while (i < k) {
            swapElements(i, k);
            i++;
            k--;
        }
    }
}
