package edu.cmu.cs.cs214.hw2;

import org.junit.Before;
import org.junit.Test;
import java.util.List;
import java.util.Set;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
/**
 * Class for testing the adjacency list implementation of Graph interface.
 * @author Kathleen
 *
 */
public class AdjListGraphTest {
    private Graph graph;
    private Vertex v1;
    private Vertex v2;
    private Vertex v3;
    private Edge e1;
    private Edge e2;
    private static final int EDGE_WEIGHT1 = 20;
    private static final int EDGE_WEIGHT2 = 30;
   
    /**
     * Method to set up variables before each test.
     */
    @Before
    public void setUp(){
        graph = new AdjListGraph();
        v1 = new StopTime("Stop1");
        v2 = new StopTime("Stop2");
        v3 = new StopTime("Stop3");
        e1 = new TravelInfo("edge1", EDGE_WEIGHT1);
        e2 = new TravelInfo("edge2", EDGE_WEIGHT2);
    }
    
    /**
     * Method to test addVertex method.
     */
    @Test
    public void testAddVertex(){
        Set<Vertex> currSet;
        graph.addVertex(v1);
        currSet = graph.getVertexSet();
        assertTrue(currSet.contains(v1));
        graph.addVertex(v2);
        assertTrue(currSet.contains(v2));
        assertTrue(currSet.size() == 2);
        graph.addVertex(v2);
        assertTrue(currSet.size() == 2);
    }
  
    /**
     * Method to test addEdge method.
     */
    @Test
    public void testAddEdge(){
        Edge result;
        graph.addVertex(v1);
        graph.addVertex(v2);
        graph.addEdge(v1,v2,e1);
        result = graph.getEdge(v1,v2);
        assertEquals(result,e1);
        graph.addEdge(v1,v2,e1);
        graph.addEdge(v1,v3,e1);
        
    }
    /**
     * Method to test getEdge method
     */
    @Test
    public void testGetEdge(){
        Edge result;
        graph.addVertex(v1);
        graph.addVertex(v2);
        graph.addEdge(v1, v2, e1);
        graph.addVertex(v3);
        graph.addEdge(v1, v3, e2);
        result = graph.getEdge(v1,v2);
        assertEquals(result,e1);
        result = graph.getEdge(v1,v3);
        assertEquals(result, e2);
        assertNull(graph.getEdge(v3,v2));
    }
    /**
     * Method to test getVertexSet method.
     */
    @Test
    public void testGetVertexSet(){
        assertTrue(graph.getVertexSet().isEmpty());
        graph.addVertex(v1);
        assertFalse(graph.getVertexSet().isEmpty());
        graph.addVertex(v2);
        assertEquals(graph.getVertexSet().size(), 2);
    }
    /**
     * Method to test getNeighbors method
     */
    @Test
    public void testGetNeighbors(){
        List<Vertex> neighbors;
        int counter = 0;
        graph.addVertex(v1);
        graph.addVertex(v2);
        graph.addEdge(v2, v1, e1);
        graph.addVertex(v3);
        graph.addEdge(v2, v3, e2);
        neighbors = graph.getNeighbors(v2);
        for(Vertex v : neighbors){
            if(counter == 0){
                assertEquals(v,v1);
            }
            else if(counter == 1){
                assertEquals(v,v3);
            }
            counter++;
        }
        assertTrue(graph.graphContains(v2));
    }
}
