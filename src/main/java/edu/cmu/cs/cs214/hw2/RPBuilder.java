package edu.cmu.cs.cs214.hw2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.List;
import java.util.LinkedList;
import java.util.Map;
import java.util.HashMap;
/**
 * Class to implment RoutePlannerBuilder which constructs RoutePlanner.
 * @author Kathleen
 *
 */
public class RPBuilder implements RoutePlannerBuilder {
    private static final int NO_MORE_STOPS = 0;
    private static final int ID_LINE_LEN = 2;
    private static final int BUSSTOP_LINE_LEN = 4;
    private static final int STOP_NAME_INDEX = 0;
    private static final int BUS_NAME_INDEX = 0;
    private static final int NUM_STOPS_INDEX = 1;
    private static final int LAT_INDEX = 1;
    private static final int LONG_INDEX = 2;
    private static final int TIME_INDEX = 3;
    
    private Graph busMap = new AdjListGraph();
    /**
     * Method to get bus name from file line that is read in.
     * @param values Array of comma-separated values from file line.
     * @return String representing the bus name.
     */
    private String getBusName(String[] values){
        return values[BUS_NAME_INDEX].trim();
    }
    /**
     * Method to get number of stops in one bus route.
     * @param values Array of comma-separated values from file line. 
     * @return Number representing number of stops in one bus route.
     */
    private int getNumStops(String[] values){
        return Integer.parseInt(values[NUM_STOPS_INDEX].trim());
    }
    /**
     * Method to get bus stop name from file line that is read in.
     * @param values Array of comma-separated values from file line.
     * @return String representing bus stop name.
     */
    private String getStopName(String[] values){
        return values[STOP_NAME_INDEX].trim();
    }
    /**
     * Method to get latitude from file line that is read in.
     * @param values Array of comma-separated values from file line.
     * @return Number representing latitude of bus stop.
     */
    private double getLatitude(String[] values){
        return Double.parseDouble(values[LAT_INDEX].trim());
    }
    /**
     * Method to get longitude from file line that is read in.
     * @param values Array of comma-separated values from file line.
     * @return Number representing longitude of bus stop.
     */
    private double getLongitude(String[] values){
        return Double.parseDouble(values[LONG_INDEX].trim());
    }
    /**
     * Method to get time of event at bus stop.
     * @param values Array of comma-separated values from file line.
     * @return NUmber representing time of event at bus stop.
     */
    private int getTime(String[] values){
        return Integer.parseInt(values[TIME_INDEX].trim());
    }
    /**
     * Method to add edges representing bus rides to graph.
     * @param busGroup List of all stops visited by a particular bus.
     * @param busName Name of bus whose route is considered.
     */
    private void buildBusEdges(List<Vertex> busGroup, String busName){
        for(int i = 0; i < busGroup.size()-1; i++){
            /* Creates edge between from one vertex to its subsequent one 
             * Note: I noticed in the example on Piazza that there were edges
             * connecting a stop and all subsequent stops, but I didn't do that
             * here because it was too costly and if stops are traveled by
             * the same bus you will eventually get to the subsequent ones.*/
            Vertex v1 = busGroup.get(i);
            Vertex v2 = busGroup.get(i+1);
            int time1 = getTime(v1.getName().split(","));
            int time2 = getTime(v2.getName().split(","));
            Edge e = new TravelInfo(busName, time2-time1);
            busMap.addEdge(v1, v2, e);
        }
    }
    /**
     * Method to add edges representing waiting periods to a graph.
     * @param stopList List of stops that are same(same name diff time).
     * @param curr Current node.
     * @param maxWaitTime maximum waiting time to consider in construciton.
     */
    private void buildWaitEdges(List<Vertex> stopList, Vertex curr,
            int maxWaitTime){
        for(Vertex v : stopList){
            /* parse name to get time values */
            int currTime = getTime(curr.getName().split(","));
            int vTime = getTime(v.getName().split(","));
            /* If the wait time is less than user-supplied maxWaitTime,
             * add an edge from smaller time vertex to large time */
            if(currTime > vTime && currTime - vTime <= maxWaitTime){
                Edge e = new TravelInfo("Wait", currTime-vTime);
                busMap.addEdge(v, curr, e);
            }
            else if(currTime < vTime && vTime - currTime <= maxWaitTime){
                Edge e = new TravelInfo("Wait", vTime-currTime);
                busMap.addEdge(curr, v, e);
            }
        }
    }
    /**
     * Method to build a RoutePlanner object.
     * @param filename Path to file with information.
     * @param maxWaitLimit maximum waiting time to consider in making edges. 
     * @return RoutePlanner to determine best route.
     */
    public RoutePlanner build(String filename, int maxWaitLimit){
        /* Holds all bus stops in the network */
        List<Stop> allStops = new LinkedList<Stop>();
        /* Holds all bus stops visited by a particular bus */
        List<Vertex> busStops = new LinkedList<Vertex>();
        /* Holds stops with same name but different time */
        Map<String, List<Vertex>> sameStops = 
                new HashMap<String, List<Vertex>>();
        String busName = "";
        /* keeps track of remaining stops to be visited by one bus */
        int remainingStops = 0;
        try{
            FileReader inputFile = new FileReader(filename);
            BufferedReader bufferReader = new BufferedReader(inputFile);
            String fileLine, fileLineNoSpaces;
            
            while((fileLine = bufferReader.readLine()) != null ){
                //fileLineNoSpaces = "";
                StringBuffer buf = new StringBuffer();
                /* Create array with each index holding stop identifying val */
                String[] values = fileLine.split(",");
                /* get rid of trailing white space in list */
                for(int i = 0; i < values.length; i++){
                    buf.append(values[i].trim());
                    values[i] = values[i].trim();
                    if(i != values.length-1){
                        buf.append(",");
                    }
                }
                fileLineNoSpaces = buf.toString();
                
                /* Case on the type of line we read 
                 * An id line is of the form "busName, number stops in route" 
                 * A bus stop line has form "stopName, latitude, longitude,
                 * time"  */
                if(values.length == ID_LINE_LEN){
                    busName = getBusName(values); 
                    remainingStops = getNumStops(values);
                }
                else if(values.length == BUSSTOP_LINE_LEN){
                    /* this is for the list with all the stops */
                    Stop s = new StopLocation(getStopName(values),
                            getLatitude(values), getLongitude(values));
                    allStops.add(s);
                    /* This is for the list that we'll use to build graph */
                    Vertex v = new StopTime(fileLineNoSpaces);
                    busStops.add(v);
                    busMap.addVertex(v);
                    
                    /* if we haven't seen this bus stop before, add it */
                    if(!sameStops.containsKey(getStopName(values))){
                        List<Vertex> others = new LinkedList<Vertex>();
                        others.add(v);
                        sameStops.put(getStopName(values), others);
                    }
                    else{
                        buildWaitEdges(sameStops.get(getStopName(values)),v,
                                maxWaitLimit);
                    }
                    
                    remainingStops -= 1;
                    /* add to the graph after every set of stops from one bus */
                    if(remainingStops == NO_MORE_STOPS){
                        buildBusEdges(busStops, busName);
                        busStops.clear();
                    }
                }
            }
            bufferReader.close();
        }
        catch(Exception e){
            System.out.println("Error");
        }
        /* Give RoutePlanner the constructed graph and list */
        RoutePlanner rp = new Planner(busMap, allStops);
        return rp;
    }
}
