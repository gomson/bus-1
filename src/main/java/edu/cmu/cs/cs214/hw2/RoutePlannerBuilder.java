package edu.cmu.cs.cs214.hw2;
/**
 * Interface to build RoutePlanner.
 * @author Kathleen
 *
 */
public interface RoutePlannerBuilder {
    /**
     * This method builds the stop time graph and the RoutePlanner object.
     * @param filename Path to file that has stop time data.
     * @param maxWaitLimit Client given waiting limit at a bus stop.
     * @return RoutePlanner for computing travel routes.
     */
    RoutePlanner build(String filename, int maxWaitLimit);
}
