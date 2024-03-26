package com.tco.misc;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;


public class TestOptimizerFactory {
    private OptimizerFactory optFactory;
    private int N;
    private double response;

    @BeforeEach
    public void createOptimizerFactoryTestCases() {
        optFactory = new OptimizerFactory();
    }

    @Test
    @DisplayName("zjp32324: Test for no optimizer")
    public void testGetNoOptimizer() {
        N = 0;
        response = 0;
        assertEquals(optFactory.get(N, response).getClass(), NoOptimizer.class);
    }

    @Test
    @DisplayName("zjp32324: Test for no optimizer different N")
    public void testGetNoOptimizer2() {
        N = 500;
        response = 0;
        assertEquals(optFactory.get(N, response).getClass(), NoOptimizer.class);
    }

    @Test
    @DisplayName("zjp32324: Test for one optimizer different N")
    public void testGetOneOptimizer() {
        N = 400;
        response = 1;
        assertEquals(optFactory.get(N, response).getClass(), OneOptimizer.class);
    }

}
