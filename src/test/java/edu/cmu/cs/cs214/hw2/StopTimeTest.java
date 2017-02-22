package edu.cmu.cs.cs214.hw2;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
/**
 * Class for testing StopTime implementation of Vertex.
 * @author Kathleen
 *
 */
public class StopTimeTest {
    private Vertex s1;
    private Vertex s2;
    private Vertex s3;
    private static final int INFINITY = 86500;
    private static final int NEW_VALUE = 100;
    /**
     * Method to initialize values before each test
     */
    @Before
    public void setUp(){
        s1 = new StopTime("CMU");
        s2 = new StopTime("Pitt");
        s3 = new StopTime("CMU");
    }
    /**
     * Method to test getName.
     */
    @Test
    public void testGetName(){
        assertEquals(s1.getName(), "CMU");
        assertEquals(s2.getName(), "Pitt");
    }
    /**
     * Method to test getValue.
     */
    @Test
    public void testGetValue(){
        assertTrue(s1.getValue() == INFINITY);
    }
    /**
     * Method to test setValue.
     */
    @Test
    public void testSetValue(){
        s1.setValue(NEW_VALUE);
        assertTrue(s1.getValue() == NEW_VALUE);   
    }
    /**
     * Method to test equals.
     */
    @Test
    public void testEquals(){
        assertEquals(s1,s3);
        assertFalse(s1.equals(s2));
        s1.setValue(NEW_VALUE);
        assertEquals(s1,s3);
        assertFalse(s3.equals(null));
    }
}
