package edu.cmu.cs.cs214.hw2;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
/**
 * Class for testing TravelInfo implementation of Edge.
 * @author Kathleen
 *
 */
public class TravelInfoTest {
    private Edge e1;
    private Edge e2;
    private Edge e3;
    private static final int WEIGHT1 = 20;
    private static final int WEIGHT2 = 30;
   
    /**
     * Method to set up variables before each test.
     */
    @Before
    public void setUp(){
        e1 = new TravelInfo("61C", WEIGHT1);
        e2 = new TravelInfo("61D", WEIGHT2);
        e3 = new TravelInfo("61C", WEIGHT1);
    }
    
    /**
     * Method to test getName method.
     */
    @Test
    public void testGetName(){
        assertEquals(e1.getName(),"61C");
        assertEquals(e2.getName(),"61D");
    }
  
    /**
     * Method to test getWeight method.
     */
    @Test
    public void testGetWeight(){
        assertTrue(e1.getWeight() == WEIGHT1);
        assertTrue(e2.getWeight() == WEIGHT2);
        
    }
    /**
     * Method to test equals method
     */
    @Test
    public void testEquals(){
        assertEquals(e1,e3);
        assertFalse(e1.equals(e2));
    }
}
