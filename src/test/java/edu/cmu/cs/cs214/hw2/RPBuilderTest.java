package edu.cmu.cs.cs214.hw2;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertTrue;
/**
 * Class for both RoutePlanner and RoutePlannerBuilder.
 * @author Kathleen
 *
 */
public class RPBuilderTest {
    private RoutePlanner planner1;
    private RoutePlanner planner2;
    private RoutePlanner planner3;
    private Stop source1;
    private Stop source2;
    private Stop source3;
    private Stop dest1;
    private Stop dest2;
    private Stop dest3;
    private static final String FILE1 = "src/main/resources/Test.txt";
    private static final String FILE2 = "src/main/resources/Test2.txt";
    private static final String FILE3 = "src/main/resources/Test3.txt";
    private static final int MAX_WAIT = 1200;
    private static final int TIME1 = 21000;
    private static final int TIME2 = 25400;
    private static final int TIME3 = 26000;
    private static final int NUM_STOPS = 6;
    private static final double WOOD_LAT = 40.440785;
    private static final double WOOD_LONG = -80.000814;
    private static final double LILAC_LAT = 40.423348;
    private static final double LILAC_LONG = -79.928751;
    private static final double MAGEE_LAT = 40.437697;
    private static final double MAGEE_LONG = -79.988717;
    private static final double JUMON_LAT = 40.437207;
    private static final double JUMON_LONG = -79.977887;
    private static final double WELFER_LAT = 40.424861;
    private static final double WELFER_LONG = -79.928042;
    private static final int START_TIME1 = 20000;
    private static final int START_TIME2 = 25620;
    private static final int START_TIME3 = 26040;
    private static final int END_TIME1 = 27180;
    private static final int END_TIME2 = 25850;
    private static final int END_TIME3 = 32460;
    private static final int WAIT_TIME1 = 910;
    private static final int WAIT_TIME2 = 10;
    private static final int WAIT_TIME3 = 1040;
    
   
    /**
     * Method to set up variables before each test.
     */
    @Before
    public void setUp(){
        planner1 = new RPBuilder().build(FILE1, MAX_WAIT);
        source1 = new StopLocation("5TH AVE AT WOOD ST",WOOD_LAT, WOOD_LONG);
        dest1 = new StopLocation("MURRAY AVE AT LILAC ST",
                LILAC_LAT,LILAC_LONG);
        planner2 = new RPBuilder().build(FILE2, MAX_WAIT);
        source2 = new StopLocation("5TH AVE AT WOOD ST",
                WOOD_LAT, WOOD_LONG);
        dest2 = new StopLocation("FORBES AVE AT MAGEE ST",
                MAGEE_LAT,MAGEE_LONG);
        planner3 = new RPBuilder().build(FILE3, MAX_WAIT);
        source3 = new StopLocation("FORBES AVE AT JUMONVILLE ST",
                JUMON_LAT, JUMON_LONG);
        dest3 = new StopLocation("MURRAY AVE AT WELFER ST",
                WELFER_LAT,WELFER_LONG);
    }
    
    /**
     * Test build and methods associated with build's returned planner.
     */
    @Test
    public void testBuild(){
        
        /* Testing findStobsBySubstring */
        
        List<Stop> stops = planner1.findStopsBySubstring("5TH");
        assertTrue(stops.size() == NUM_STOPS);
        
        /* General test case */ 
        Itinerary it1 = planner1.computeRoute(source1, dest1, TIME1);
        assertTrue(it1.getStartTime() == START_TIME1);
        assertTrue(it1.getEndTime() == END_TIME1);
        assertTrue(it1.getWaitTime() == WAIT_TIME1);
        assertTrue(it1.getStartLocation().getLatitude() == WOOD_LAT);
        assertTrue(it1.getStartLocation().getLongitude() == WOOD_LONG);
        
        /* Test case where there are multiple possible destinations */
        Itinerary it2 = planner2.computeRoute(source2, dest2, TIME2);
        assertTrue(it2.getStartTime() == START_TIME2);
        assertTrue(it2.getEndTime() == END_TIME2);
        assertTrue(it2.getWaitTime() == WAIT_TIME2);
        
        /* Test case with multiple transfers */
        Itinerary it3 = planner3.computeRoute(source3, dest3, TIME3);
        assertTrue(it3.getStartTime() == START_TIME3);
        assertTrue(it3.getEndTime() == END_TIME3);
        assertTrue(it3.getWaitTime() == WAIT_TIME3);
    }
}
