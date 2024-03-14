package com.tco.requests;

import com.tco.misc.DistanceCalculator;
import com.tco.misc.VincentyDistance;
import com.tco.misc.CosinesDistance;
import com.tco.misc.HaversineDistance;
import com.tco.misc.BadRequestException;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TestDistancesRequest {

    private DistancesRequest dist;

    private Place northPole = new Place("90", "0");
    private Place southPole = new Place("-90", "0");
    private Place equatorMiddle = new Place("0", "0");
    private Place equatorWest = new Place("0", "-90");
    private Place equatorEast = new Place("0", "90");

    @BeforeEach
    public void createDistancesForTestCases() {
        dist = new DistancesRequest();
        try{
            dist.buildResponse();
        }catch (BadRequestException e){
        }
    }

    @Test
    @DisplayName("bodorol: Request type is \"distances\"")
    public void testType() {
        String type = dist.getRequestType();
        assertEquals("distances", type);
    }

    @Test
    @DisplayName("bodorol: Distance calculator type is \"vincenty\"")
    public void testFormulaNotSpecified() {
        DistanceCalculator distanceCalculator = dist.getDistanceCalculator();
        assertTrue(distanceCalculator instanceof VincentyDistance);
    }

    @Test
    @DisplayName("bodorol: Distance calculator type is \"vincenty\"")
    public void testFormulaNotSupported() {
        dist.setFormula("chord");
        Exception exception = assertThrows(BadRequestException.class, () -> {
            dist.buildResponse();
        });
    }

    @Test
    @DisplayName("bodorol: Distance calculator type is \"vincenty\"")
    public void testFormulaVincenty() {
        dist.setFormula("vincenty");
        try{
            dist.buildResponse();
        }catch (BadRequestException e){
        }

        DistanceCalculator distanceCalculator = dist.getDistanceCalculator();
        assertTrue(distanceCalculator instanceof VincentyDistance);
    }

    @Test
    @DisplayName("bodorol: Distance calculator type is \"haversine\"")
    public void testFormulaHaversine() {
        dist.setFormula("haversine");
        try{
            dist.buildResponse();
        }catch (BadRequestException e){
        }

        DistanceCalculator distanceCalculator = dist.getDistanceCalculator();
        assertTrue(distanceCalculator instanceof HaversineDistance);
    }

    @Test
    @DisplayName("bodorol: Distance calculator type is \"cosines\"")
    public void testFormulaCosines() {
        dist.setFormula("cosines");
        try{
            dist.buildResponse();
        }catch (BadRequestException e){
        }

        DistanceCalculator distanceCalculator = dist.getDistanceCalculator();
        assertTrue(distanceCalculator instanceof CosinesDistance);
    }

    @Test
    @DisplayName("bodorol: Distances object is empty")
    public void testDistancesEmpty() {
        Distances distances = dist.getDistances();
        assertTrue(distances.isEmpty());
    }

    @Test
    @DisplayName("bodorol: Distances contains 1 element")
    public void testDistancesOnePlace() {
        dist.addPlace(northPole);

        try{
            dist.buildResponse();
        }catch (BadRequestException e){
        }

        Distances distances = dist.getDistances();
        assertEquals(1, distances.size());
    }

    @Test
    @DisplayName("bodorol: Distances contains 2 elements")
    public void testDistancesTwoPlaces() {
        dist.setFormula("cosines");

        dist.addPlace(northPole);
        dist.addPlace(southPole);

        try{
            dist.buildResponse();
        }catch (BadRequestException e){
        }

        Distances distances = dist.getDistances();
        assertEquals(2, distances.size());
    }

    @Test
    @DisplayName("bodorol: Distances contains 5 elements")
    public void testDistancesFivePlaces() {
        dist.setFormula("haversine");

        dist.addPlace(northPole);
        dist.addPlace(southPole);
        dist.addPlace(equatorMiddle);
        dist.addPlace(equatorWest);
        dist.addPlace(equatorEast);

        try{
            dist.buildResponse();
        }catch (BadRequestException e){
        }

        Distances distances = dist.getDistances();
        assertEquals(5, distances.size());
    }

    @Test
    @DisplayName("bodorol: Earth radius is 3959")
    public void testEarthRadiusDefault() {
        Double earthRadius = dist.getRadius();
        assertEquals(3959, earthRadius);
    }
}