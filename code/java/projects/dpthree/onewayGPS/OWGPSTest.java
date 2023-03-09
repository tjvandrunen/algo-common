package onewayGPS;

import static org.junit.Assert.*;

import org.junit.Test;

public class OWGPSTest {

    @Test
    public void threeEastTwoNorth() {
        assertEquals(9, 
                OneWayGPS.getBestTravelTime(3, 2, 
                        new int[][] {{5, 8}, {3, 3}}, new int[][] {{4}, {1}, {2}}));
    }

    @Test
    public void twoEastFourNorth() {
        assertEquals(16, 
                OneWayGPS.getBestTravelTime(2, 4, 
                        new int[][] {{1, 4, 3, 2}}, new int[][] {{5, 7, 12}, {11, 4, 3}}));
    }
    
    @Test
    public void fiveEastFourNorth() {
        assertEquals(23,
                OneWayGPS.getBestTravelTime(5, 4,
                        new int[][] {{5, 3, 11, 3}, {4, 1, 3, 2}, {11, 8, 7, 4}, {4, 1, 3, 8}}, 
                        new int[][] {{1, 6, 7}, {8, 12, 4}, {13, 3, 6}, {7, 9, 10}, {5, 2, 7}}));
    }
   
    @Test
    public void threeEastThreeNorth() {
        assertEquals(9, OneWayGPS.getBestTravelTime(3, 3,
                new int[][]{{1, 2, 4}, {5, 4, 9}}, new int[][]{{1, 1}, {7, 6}, {8, 2}}));
    }
    
}
