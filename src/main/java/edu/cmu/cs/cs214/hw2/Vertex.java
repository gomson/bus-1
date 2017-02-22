package edu.cmu.cs.cs214.hw2;
/**
 * Interface for Vertex objects to be used to build graphs
 * @author Kathleen
 *
 */
public interface Vertex {
    /**
     * Function to give client stop time name value.
     * @return Name of bus stop.
     */
    String getName();
    /**
     * Method to give client value associated with vertex.
     * @return Value associated with vertex distance from source for Dijstra's.
     */
    int getValue();
    /**
     * Method to allow client to set private variable value. 
     * @param newVal Value that vertex should be set to.
     */
    void setValue(int newVal);
    /**
     * Overriding method to determine vertex equality.
     * @param obj Vertex to be compared to.
     * @return true if nodes are equal, false otherwise.
     */
    @Override
    boolean equals(Object obj);
    /**
     * Overriding method to generate hashcode for objects.
     * @return integer hashcode.
     */
    @Override
    int hashCode();

}
