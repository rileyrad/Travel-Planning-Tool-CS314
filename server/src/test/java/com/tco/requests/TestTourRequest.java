package com.tco.requests;

import com.tco.misc.BadRequestException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TestTourRequest {
    private TourRequest tour;
    //Define places in a hexagon
    private Place a = new Place("39.75010942112556","-105.88623046875");
    private Place b = new Place("39.80187465926252","-104.39208984375001");
    private Place c = new Place("39.17327816268155","-103.90869140625001");
    private Place d = new Place("38.69412746142133","-104.26025390625001");
    private Place e = new Place("38.64259802640263","-105.3369140625");
    private Place f = new Place("39.087953049368785","-105.99609375000001");
    
    private int idealDist = 319;
    private Places placeList;
    @BeforeEach
    public void createTourRequestForTestCases(){
        placeList = new Places();
    }

    @Test
    @DisplayName("greeff: Test Nearest Neighbor Two Crossed Lines")
    public void testACBDEF(){
        placeList.add(a); placeList.add(c); placeList.add(b);
        placeList.add(d); placeList.add(e); placeList.add(f);
        tour = new TourRequest(placeList);
        try{
            tour.buildResponse();
        } catch(BadRequestException e){
        }
        placeList = tour.getPlaces();
        assertEquals(idealDist, returnTotalFromPlaces(placeList));
    }

    @Test
    @DisplayName("greeff: Test Nearest Neighbor Two Crossed Lines")
    public void testADCBEF(){
        placeList.add(a); placeList.add(d); placeList.add(c);
        placeList.add(b); placeList.add(e); placeList.add(f);
        tour = new TourRequest(placeList);
        try{
            tour.buildResponse();
        } catch(BadRequestException e){
        }
        placeList = tour.getPlaces();
        assertEquals(idealDist, returnTotalFromPlaces(placeList));
    }
    
    private Long returnTotalFromPlaces(Places placeIn){
        DistancesRequest dist = new DistancesRequest(placeList);
        try{
            dist.buildResponse();
        } catch(BadRequestException e){
        }
        Distances distList = dist.getDistances();
        return distList.total();
    }
}
