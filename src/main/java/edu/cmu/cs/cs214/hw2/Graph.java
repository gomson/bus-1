package edu.cmu.cs.cs214.hw2;

import java.util.Set;
import java.util.List;
/**
 * This is an interface for directed graphs.
 * @author Kathleen
 *
 */
public interface Graph {
    /**
     * This method adds a vertex to the graph.
     * @param v Vertex object to be added as vertex to Graph.
     */
    void addVertex(Vertex v);
    /**
     * This method creates a directed edge from vertex 1 to vertex 2.
     * @param v1 First vertex in the edge (source).
     * @param v2 Second vertex in the edge (target).
     * @param e Edge to be added from v1 to v2.
     */
    void addEdge(Vertex v1, Vertex v2, Edge e);
    /**
     * This method gets the edge object that connects 2 vertices.
     * @param v1 First vertex in the edge (source)
     * @param v2 Second vertex in the edge (target)
     * @return The directed edge from v1 to v2.
     */
    Edge getEdge(Vertex v1, Vertex v2);
    /**
     * Method to retrieve all vertices that are in graph.
     * @return Set representing all vertices.
     */
    Set<Vertex> getVertexSet();
    /**
     * Method to retrieve all vertices that are adjacent to given vertex.
     * @param v Vertex whose neighbors we are looking for.
     * @return List of vertices adjacent to given vertex.
     */
    List<Vertex> getNeighbors(Vertex v);
    /**
     * Method to determine if given vertex is in the graph.
     * @param v Vertex to be searched for.
     * @return True if v is in, false otherwise.
     */
    boolean graphContains(Vertex v);

    
}
