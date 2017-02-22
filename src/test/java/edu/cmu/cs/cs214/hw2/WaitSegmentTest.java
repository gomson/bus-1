package edu.cmu.cs.cs214.hw2;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
/**
 * Class for testing BusSegment implementation of TripSegment.
 * @author Kathleen
 *
 */
public class WaitSegmentTest {
   private TripSegment t1;
   private Stop start;
   private Stop end;
   private static final double START_LAT = 0.0;
   private static final double START_LONG = 1.0;
   private static final double END_LAT = 2.0;
   private static final double END_LONG = 3.0;
   private static final int START_TIME = 5;
   private static final int END_TIME = 6;
    /**
     * Method to set up variables before each test.
     */
    @Before
    public void setUp(){
        start = new StopLocation("stop1", START_LAT, START_LONG);
        end = new StopLocation("stop2", END_LAT, END_LONG);
        t1 = new WaitSegment(start, end, START_TIME, END_TIME);
    }
    
    /**
     * Method to test getSegmentType method.
     */
    @Test
    public void testgetSegmentType(){
        assertEquals(t1.getSegmentType(), "Wait");
    }
  
    /**
     * Method to test getSegmentStartLoc method.
     */
    @Test
    public void testGetSegmentStartLoc(){
        assertEquals(t1.getSegmentStartLoc(), start);
    }
    /**
     * Method to test segmentEndLoc method
     */
    @Test
    public void testSegmentEndLoc(){
        assertEquals(t1.getSegmentEndLoc(), end);

    }
    /**
     * Method to test getSegmentStartTime method.
     */
    @Test
    public void testGetSegmentStartTime(){
        assertTrue(t1.getSegmentStartTime() == START_TIME);
    }
    /**
     * Method to test getSegmentEndTime method.
     */
    @Test
    public void testGetSegmentEndTime(){
        assertTrue(t1.getSegmentEndTime() == END_TIME);
    }
    /**
     * Method to test getSegmentInstructions.
     */
    @Test
    public void testGetSegmentInstructions(){
        String str = "Wait at " + t1.getSegmentStartLoc().getName() + " for "
                + Integer.toString(t1.getSegmentEndTime()
                        -t1.getSegmentStartTime()) + ".\n";
        assertEquals(t1.getSegmentInstructions(),str);
        
    }
}
