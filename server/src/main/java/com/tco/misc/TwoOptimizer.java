package com.tco.misc;

public class TwoOptimizer extends TourConstruction {
    
    @Override
    public void improve() {
        boolean improved = true;
        int tourLength = getTourLength();
        while (improved) {
            improved = false;
            for (int i = 0; i < tourLength - 1; i++) {
                for (int k = i + 1; k < tourLength; k++) {
                    if (swapImproves(i, k)) {
                        twoOptSwap(i, k);
                        improved = true;
                    }
                }
            }
        }
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
