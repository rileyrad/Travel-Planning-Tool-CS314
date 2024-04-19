package com.tco.misc;

public class TwoOptimizer extends TourConstruction {
    
    public void improve() {
    }

    private void twoOptSwap(int i, int k) {
        while (i < k) {
            swapElements(i, k);
            i++;
            k--;
        }
    }
}
