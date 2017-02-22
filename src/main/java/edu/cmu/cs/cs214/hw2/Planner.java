package edu.cmu.cs.cs214.hw2;

import java.util.List;
import java.util.LinkedList;
import java.util.Map;
import java.util.HashMap;
/**
 * Class to implement RoutePlanner
 * @author Kathleen
 *
 */
public class Planner implements RoutePlanner{
    private Graph map;
    private List<Stop> allStops;
    private List<Stop> validStops = new LinkedList<Stop>();
    /* This many seconds won't be reached in 24 hours, so use as infinity */
    private static final int INFINITY = 99999;
    private static final int TIME_INDEX = 3;
    private static final int NAME_INDEX = 0;
    private static final int LAT_INDEX = 1;
    private static final int LONG_INDEX = 2;
    /**
     * Constructor to instantiate map and allStops object by parameters.
     * @param g Graph associated with all routes covered by buses.
     * @param s List of all stops covered by buses.
     */
    public Planner(Graph g, List<Stop> s){
        map = g;
        allStops = s;
        
    }
    /**
     * This method returns vertex that is closest to the desired start time.
     * @param src Desired start location.
     * @param time Desired start time.
     * @return Vertex that minimizes diff between given time and actual time.
     */
    private Vertex findStartLoc(Stop src, int time){
        String name;
        int minDiff = INFINITY;
        int currDiff;
        int currVertexTime;
        String[] values;
        Vertex start = null;
        /* For each vertex, if the stop is potentially the stop we want to
         * start at, check the difference between its time and the desired
         * starting time. We want to closes time to the desired.
         */
        for(Vertex v : map.getVertexSet()){
            name = v.getName();
            if(name.contains(src.getName())){
                values = name.split(",");
                currVertexTime = Integer.parseInt(values[TIME_INDEX]);
                currDiff = Math.abs(time-currVertexTime);
                if(currDiff < minDiff){
                    minDiff = currDiff;
                    start = v;
                }
            }
        }
        return start;
    }
    /**
     * From a list of possible ending stops, find the one with minimum time.
     * @param vertices List of possible stops.
     * @param destName String of desired destination name.
     * @return Vertex representing optimal stopping point.
     */
    private Vertex findBestEnd(List<Vertex> vertices, String destName){
        String name;
        int minValue = INFINITY;
        Vertex end = null;
        /* For all vertices that have the name of the stop we want, check
         * the time, and return the minimum time.
         */
        for(Vertex v : map.getVertexSet()){
            name = v.getName();
            if(name.contains(destName) && v.getValue() <= minValue){
                minValue = v.getValue();
                end = v;
            }
        }
        return end;
    }
    /**
     * Method used by Dijkstra's to get the best node to evaluate next.
     * @param vertexSet Set of all vertices in the graph.
     * @param seen List of already visitied vertices
     * @return Vertex representing lowest priority vertex.
     */
    private Vertex getMin(List<Vertex> vertexSet, List<Vertex> seen){
        
        int minVal = INFINITY;
        Vertex minVertex = null;
        for(Vertex v : vertexSet){
            if(v.getValue() <= minVal && !seen.contains(v)){
                minVertex = v;
                minVal = v.getValue();   
            }
        }
        return minVertex;
    }
    /**
     * Method to return a list of stops whose names contain search string.
     * @param search String to search for in bus stop names.
     * @return List of stops that include supplied name.
     */
    public List<Stop> findStopsBySubstring(String search){
        String name;
        for(Stop s : allStops){
            name = s.getName();
            if(name.contains(search)){
                validStops.add(s);
            }
        }
        return validStops;
    }
    /**
     * Method to return an Itinerary for travel.
     * @param src Starting location of search.
     * @param dest Desired ending location of search.
     * @param time Time defining starting point.
     * @return Itinerary describing the best route of travel.
     */
    public Itinerary computeRoute(Stop src, Stop dest, int time){
        Itinerary plan = new Itinerary(src, dest, time);
        int totalWaitTime = 0;
        /* Each node maps to the vertex it's preceded by, so we can trace
         * back and get the actual path.
         */
        Map<Vertex,Vertex> previous= new HashMap<Vertex,Vertex>();
        List<Vertex> visited = new LinkedList<Vertex>();
        List<Vertex> queue = new LinkedList<Vertex>();
        
        /* This will be used to build Itinerary object */
        List<TripSegment> travelSegments = new LinkedList<TripSegment>();
        Vertex current;
        Vertex start = findStartLoc(src, time);
  
        start.setValue(0);
        /* The implementation of Dijkstra's is based on the pseudocode
         * from Wikipedia */
        for(Vertex v : map.getVertexSet()){
            previous.put(v, null);
            queue.add(v);
        }
        /* Search through all nodes and assign the min weighted value to
         * get to that node from the start.
         */
        while(queue.size() != visited.size()){
            current = getMin(queue, visited);
            visited.add(current); /* mark as visited so don't eval again */
            /* Get all adjacencies */
            List<Vertex> neighbors = map.getNeighbors(current);
            /* Search through all adjacencies, and update the value if it is
             * better than it was before.
             */
            for(Vertex v : neighbors){
                if(!visited.contains(v)){
                    Edge e = map.getEdge(current, v);
                    int edgeWeight = e.getWeight();
                    int currWeight = current.getValue();
                    /* Value is cumulative of weight of path up to this point*/
                    if(edgeWeight + currWeight < v.getValue()){
                        v.setValue(edgeWeight + currWeight);
                        previous.put(v, current);
                    }
                }
            }   
        }
        current = findBestEnd(queue, dest.getName());
        /* Get path by tracing backwards through the stored vertices 
         * Since the first node in the path won't have a previous, loop
         * until we hit null */
        while(previous.get(current) != null){
            String name;
            String[] values;
            TripSegment t;
            Vertex prev = previous.get(current);
            Edge e = map.getEdge(prev, current);
            
            /* parse through name of each node to get location name,
             * latitude, longitude, and time. This is the starting node
             * of the segment.
             */
            name = prev.getName();
            values = name.split(",");
            Stop segStart = new StopLocation(values[NAME_INDEX], 
                    Double.parseDouble(values[LAT_INDEX]),
                    Double.parseDouble(values[LONG_INDEX]));
            int startTime = Integer.parseInt(values[TIME_INDEX]);
            /* Parse through the ending node. Since we're tracing backwards
             * the current node is the end of the segment */
            name = current.getName();
            values = name.split(",");
            Stop segEnd = new StopLocation(values[NAME_INDEX], 
                    Double.parseDouble(values[LAT_INDEX]),
                    Double.parseDouble(values[LONG_INDEX]));
            int endTime = Integer.parseInt(values[TIME_INDEX]);
            
            /* Build trip segments based on type of edge */
            if(e.getName().equals("Wait")){
                t = new WaitSegment(segStart, segEnd,startTime, endTime);
            }
            else{
                t = new BusSegment(segStart, segEnd, e.getName(),
                        startTime,endTime);
            }
            travelSegments.add(0,t); /* add to front since tracing back */
            
            /* get next node in the trip */
            current = previous.get(current);
        }
        /* Go through travel segments to update actual start time, end time,
         * total wait time, and instructions.
         */
        for(int i = 0; i < travelSegments.size(); i++){
            TripSegment t = travelSegments.get(i);
            if(i == travelSegments.size()-1){
                plan.setEndTime(t.getSegmentEndTime());
            }
            if(i == 0){
                plan.setStartTime(t.getSegmentStartTime());
            }
            else{
                if(t.getSegmentType().equals("Wait")){
                    totalWaitTime += (t.getSegmentEndTime() 
                                        - t.getSegmentStartTime());
                }
            }
            plan.setInstructions(plan.getInstructions() 
                    + t.getSegmentInstructions());
            plan.setWaitTime(totalWaitTime);
        }
        return plan;
        
    }
}

