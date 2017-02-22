package edu.cmu.cs.cs214.hw2;

import java.util.Map;
import java.util.List;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Set;
/**
 * Class that implements Graph using adjacency lists.
 * @author Kathleen
 *
 */
public class AdjListGraph implements Graph {
    /* There isn't 100% line coverage inside the VertexEdge class
     * I don't explicitly test equals and hashcode since VertexEdge
     * is hidden from clients, and since my graph searching requires
     * equals and hashcode to work, it should be ok. 
     */
    /**
     * Private class to describe weighted graph.
     * @author Kathleen
     *
     */
    private static class VertexEdge{
        private Vertex vertex;
        private Edge edge;
        /**
         * Constructor to instantiate VertexEdge with target vertex and edge.
         * @param v Target vertex that edge goes to.
         * @param e Object of type Edge that connects two vertices.
         */
        public VertexEdge(Vertex v, Edge e){
            vertex = v;
            edge = e;
        }
        /**
         * Method to return private variable vertex.
         * @return Vertex object
         */
        private Vertex getVertex(){
            return this.vertex;
        }
        /**
         * Method to return private variable's edge.
         * @return Edge object.
         */
        private Edge getEdge(){
            return this.edge;
        }
        /* The following overrides for equals and hashCode were based off
         * of the stackoverflow examples.
         */
        /**
         * Overiding method to determine equality of VertexEdge objects.
         * @param obj VertexEdge to be compared to.
         * @return True if equal, false otherwise.
         */
        @Override
        public boolean equals(Object obj){
            if(this == obj) return true;
            if(obj == null) return false;
            if(getClass() != obj.getClass()) return false;
            final VertexEdge other = (VertexEdge) obj;
            return(this.vertex.equals(other.getVertex())
                    && this.edge.equals(other.getEdge()));
        }
        /**
         * Overriding method to get hashcode for objects.
         * @return integer hashcode.
         */
        @Override
        public int hashCode(){
            final int prime = 31;
            int result = 1;
            result *= prime;
            
            if(this.vertex == null) result += 0;
            else result += this.vertex.hashCode();
            
            if(this.edge == null) result += 0;
            else result += this.edge.hashCode();
            
            return result;
        }
        
    }
    private static final int INFINITY = 86500;
    private Map<Vertex, List<VertexEdge>> graph;
    /**
     * Constructor to build AdjListGraph
     */
    public AdjListGraph(){
        graph = new HashMap<Vertex, List<VertexEdge>>();
    }
    /**
     * Adds a vertex to the graph object.
     * @param v Vertex to be added to graph.
     */
    public void addVertex(Vertex v){
        if(graph.containsKey(v)){
            System.out.println("Error. Adding existing vertex");
        }
        else{
            List<VertexEdge> adjacencies= new LinkedList<VertexEdge>();
            graph.put(v, adjacencies);
        }
    }
    /**
     * Adds an edge to the graph object.
     * @param v1 Source vertex in edge to be added.
     * @param v2 Target vertex in edge to be added.
     * @param e Edge to be added from v1 to v2.
     */
    public void addEdge(Vertex v1, Vertex v2, Edge e){
        VertexEdge ve = new VertexEdge(v2,e);
        boolean edgeExists = false;
        /* Check if both vertices exist in the graph */
        if(!(graph.containsKey(v1) && graph.containsKey(v2))){
            System.out.println("Adding edge between nonexisting nodes.");
        }
        else{
            List<VertexEdge> adjacencies = graph.get(v1);
            /* Check if edge already exists between the source and target */
            for(VertexEdge temp : adjacencies){
                if(ve.equals(temp)){
                    System.out.println("Error. Adding existing edge.");
                    edgeExists = true;
                    break;
                }
            }
            /* Valid edge, add it */
            if(!edgeExists){
                graph.get(v1).add(ve);
            }
        }
    }
    /**
     * Method to get edge that goes from v1 to v2.
     * @param v1 Source vertex.
     * @param v2 Target vertex.
     * @return Edge that goes from v1 to v2.
     */
    public Edge getEdge(Vertex v1, Vertex v2){
        List<VertexEdge> adjacencies = graph.get(v1);
        /* Search through adjacencies, if target node found, return edge */
        for(VertexEdge ve : adjacencies){
            if(ve.getVertex().equals(v2)){
                return ve.getEdge();
            }
        }
        return null;
    }
    /**
     * Method to get all vertices in the graph.
     * @return Set including all vertices.
     */
    public Set<Vertex> getVertexSet(){
        return graph.keySet();
    }
    /**
     * Method to retrieve all vertices that are adjacent to given vertex.
     * @param v Vertex whose neighbors we are looking for.
     * @return List of vertices adjacent to given vertex.
     */
    public List<Vertex> getNeighbors(Vertex v){
        List<Vertex> neighbors = new LinkedList<Vertex>();
        List<VertexEdge> veList = graph.get(v);
        /* For every entry mapped to v, add the vertex to list of vertices */
        for(VertexEdge ve : veList){
            neighbors.add(ve.getVertex());
        }
        return neighbors;
    }
    /**
     * Method to determine if a given vertex is in the graph.
     * @param v Vertex to search for.
     * @return true if vertex is in, false otherwise.
     */
    public boolean graphContains(Vertex v){
        return graph.containsKey(v);
    }
}
