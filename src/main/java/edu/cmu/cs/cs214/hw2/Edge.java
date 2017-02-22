package edu.cmu.cs.cs214.hw2;
/**
 * Interface for graph edges.
 * @author Kathleen
 *
 */
public interface Edge {
    /**
     * Method to return name of edge.
     * @return Name of edge.
     */
    String getName();
    /**
     * Method to return weight of edge.
     * @return Assigned edge weight.
     */
    int getWeight();
    /**
     * Overriding method to determine if two edges are the same edge.
     * @param obj Edge to be compared.
     * @return true if edges are equal, false otherwise.
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
