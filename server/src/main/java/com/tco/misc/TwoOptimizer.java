package com.tco.misc;

public class TwoOptimizer extends TourConstruction {
    
    @Override
    public void improve() {
        boolean improved = true;
        int tourLength = getTourLength();
        while (improved) {
            improved = false;
            for (int i = 0; i < tourLength - 3; i++) {
                for (int k = i + 2; k < tourLength - 1; k++) {
                    if (swapImproves(i, k)) {
                        twoOptSwap(i + 1, k);
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
        /* The following methods exist only for testing purposes and are not used
     during normal execution, including the constructor. */
     public boolean testSwapImproves(int i, int k) {
        return swapImproves(i, k);
     }
}
