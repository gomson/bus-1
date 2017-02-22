package edu.cmu.cs.cs214.hw2;

import java.util.List;
/**
 * Interface setting up a RoutePlanner to find best travel route.
 * @author Kathleen
 *
 */
public interface RoutePlanner {
    /**
     * This method returns a list of stops whose name contains the parameter.
     * @param search The substring to look for in bus stop names.
     * @return List of stops whose name contains the parameter as a substring
     */
    List<Stop> findStopsBySubstring(String search);
    /**
     * 
     * @param src The stop from which the user's route will start.
     * @param dest The stop that the user is trying to reach.
     * @param time Time uniquely identifying starting stop.
     * @return The Itinerary object which describes a trip that minimizes
     *         the time it takes to get to dest.
     */
    Itinerary computeRoute(Stop src, Stop dest, int time);
}
