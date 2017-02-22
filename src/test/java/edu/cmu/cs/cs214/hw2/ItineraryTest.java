package edu.cmu.cs.cs214.hw2;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


import org.junit.Before;
import org.junit.Test;
/**
 * Class for testing Itinerary class.
 * @author Kathleen
 *
 */
public class ItineraryTest {
    private Itinerary it;
    private Stop start;
    private Stop end;
    private static final double START_LAT = 0.0;
    private static final double START_LONG = 1.0;
    private static final double END_LAT = 2.0;
    private static final double END_LONG = 3.0;
    private static final int START_TIME = 5;
    private static final int END_TIME = 12;
    private static final int NO_WAIT = 0;
    private static final int NEW_TIME = 223;
    /**
     * Method to set up variables before each test.
     */
    @Before
    public void setUp(){
        start = new StopLocation("stop1", START_LAT, START_LONG);
        end = new StopLocation("stop2", END_LAT, END_LONG);
        it = new Itinerary(start, end, START_TIME);
        it.setEndTime(END_TIME);
    }
    
    /**
     * Method to test getStartTime method.
     */
    @Test
    public void testGetStartTime(){
        assertTrue(it.getStartTime() == START_TIME);
    }
  
    /**
     * Method to test getEndTime method.
     */
    @Test
    public void testGetEndTime(){
        assertTrue(it.getEndTime() == END_TIME);
    }
    /**
     * Method to test getWaitTime method
     */
    @Test
    public void testGetWaitTime(){
        assertTrue(it.getWaitTime() == NO_WAIT);
    }
    /**
     * Method to test getStartLocation method.
     */
    @Test
    public void testGetStartLocation(){
        assertEquals(it.getStartLocation(), start);
    }
    /**
     * Method to test getEndLocation method
     */
    @Test
    public void testGetEndLocation(){
        assertEquals(it.getEndLocation(), end);
    }
    /**
     * Method to test getInstructions
     */
    @Test
    public void testGetInstructions(){
        String str = "Route plan from " + it.getStartLocation().getName() 
                + " to " + it.getEndLocation().getName() + "\n";
        assertEquals(it.getInstructions(), str);
    }
    /**
     * Method to test setStartTime.
     */
    @Test
    public void testSetStartTime(){
        it.setStartTime(NEW_TIME);
        assertTrue(it.getStartTime() == NEW_TIME);
    }
    /**
     * Method to test setEndTime.
     */
    @Test
    public void testSetEndTime(){
        it.setEndTime(NEW_TIME);
        assertTrue(it.getEndTime() == NEW_TIME);
    }
    /**
     * Method to test setWaitTime.
     */
    @Test
    public void testSetWaitTime(){
        it.setWaitTime(END_TIME-START_TIME);
        assertTrue(it.getWaitTime() == END_TIME-START_TIME);
        it.setWaitTime(it.getWaitTime() + NEW_TIME);
        assertTrue(it.getWaitTime() == END_TIME-START_TIME+NEW_TIME);
    }
    /**
     * Method to test setInstructions.
     */
    @Test
    public void testSetInstructions(){
        it.setInstructions("hello");
        it.setInstructions(it.getInstructions() + " there");
        assertEquals(it.getInstructions(), "hello there");
    }
}