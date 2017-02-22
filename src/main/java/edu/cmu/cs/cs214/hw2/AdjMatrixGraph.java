package edu.cmu.cs.cs214.hw2;

import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import java.util.List;
import java.util.LinkedList;
/**
 * Implements Graph using an adjacency matrix.
 * @author Kathleen
 *
 */
public class AdjMatrixGraph implements Graph{
    private Map<Vertex,Map<Vertex,Edge>> graph;

    /**
     * Constructor that instantiates the private variable graph.
     */
    public AdjMatrixGraph(){
        graph = new HashMap<Vertex, Map<Vertex,Edge>>();
    }
    /**
     * Adds a vertex to graph object.
     * @param v Vertex to be added to graph.
     */
    public void addVertex(Vertex v){
        if(graph.containsKey(v)){
            System.out.println("Error. Adding existing vertex");
        }
        else{
            /* subvector to be associated with new vertex */
            Map<Vertex,Edge> inner = new HashMap<Vertex,Edge>();
            inner.put(v,null);
            Vertex temp;
            Iterator<Vertex> k = graph.keySet().iterator();
            /* loop through existing values to update matrix */
            while(k.hasNext()){
                temp = k.next();
                /* add current vertex to each existing vertex's subvector */
                graph.get(temp).put(v, null);
                /* build the subvector to be associated with current vertex */
                inner.put(temp, null); 
            }
            graph.put(v,inner);
        }
    }
    /**
     * Adds a directed edge from v1 to v2
     * @param v1 Source vertex in edge to be added.
     * @param v2 Target vertex in edge to be added.
     * @param e Edge to be added from v1 to v2.
     */
    public void addEdge(Vertex v1, Vertex v2, Edge e){
        /* Check both vertices exist */
        if(!(graph.containsKey(v1) && graph.containsKey(v2))){
            System.out.println("Error. Adding edge between nonexisting nodes");
        }
        /* Check if edge exists already */
        else if(graph.get(v1).get(v2) != null){
            System.out.println("Error. Adding existing edge");
        }
        /* Valid edge, add it. */
        else{
            graph.get(v1).put(v2,e);
        }
        
    }
    /**
     * Method to get edge that goes from v1 to v2.
     * @param v1 Source vertex.
     * @param v2 Target vertex.
     * @return Edge that goes from v1 to v2.
     */
    public Edge getEdge(Vertex v1, Vertex v2){
        Map<Vertex,Edge> adjacencies = graph.get(v1);
        return adjacencies.get(v2);
    }
    /**
     * Method to get all the vertices in the graph.
     * @return Set containing all vertices in graph.
     */
    public Set<Vertex> getVertexSet(){
        return graph.keySet();
    }
    /**
     * Method to retrieve all vertices that are adjacent to given vertex.
     * @param curr Vertex whose neighbors we are looking for.
     * @return List of vertices adjacent to given vertex.
     */
    public List<Vertex> getNeighbors(Vertex curr){
        Map<Vertex,Edge> veSet = graph.get(curr);
        List<Vertex> neighbors = new LinkedList<Vertex>();
        /* There was a FindBugs error that said I should loop through the
         * entrySet rather than the keySet so I wouldn't have to use get()
         * however, I need both the key and the entry so I just left this.
         */
        for(Vertex v : veSet.keySet()){
            if(veSet.get(v) != null){
                neighbors.add(v);
            }
        }
        return neighbors;
    }
    /**
     * Method to determine whether or not a vertex is in the graph.
     * @param v vertex to search for.
     * @return true if v is in, false otherwise.
     */
    public boolean graphContains(Vertex v){
        return graph.containsKey(v);
    }
}
