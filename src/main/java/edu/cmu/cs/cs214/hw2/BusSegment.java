package edu.cmu.cs.cs214.hw2;
/**
 * Class to track trip segments that are bus rides.
 * @author Kathleen
 *
 */
public class BusSegment implements TripSegment {
    private String busName;
    private Stop segmentStartLoc, segmentEndLoc;
    private int segmentStartTime, segmentEndTime;
    /**
     * Constructor to initialize start, end locations, end time, and bus name.
     * @param pStart Start location.
     * @param pEnd Desired end location.
     * @param pBusName Name of bus traveling on this segment.
     * @param pStartTime Starting time at location.
     * @param pEndTime Arrival time at destination.
     */
    public BusSegment(Stop pStart, Stop pEnd, String pBusName,
            int pStartTime, int pEndTime){
        segmentStartLoc = pStart;
        segmentEndLoc = pEnd;
        busName = pBusName;
        segmentStartTime = pStartTime;
        segmentEndTime = pEndTime;
    }
    /**
     * Method to allow clients to determine the type of trip segment.
     * @return String representing type of trip segment.
     */
    public String getSegmentType(){
        return "Bus";
    }
    /**
     * Method to get starting location of segment.
     * @return segmentStartLoc attribute.
     */
    public Stop getSegmentStartLoc(){
        return this.segmentStartLoc;
    }
    /**
     * Method to get ending location of segment.
     * @return segmentEndLoc attribute.
     */
    public Stop getSegmentEndLoc(){
        return this.segmentEndLoc;
    }
    /**
     * Method to get starting time of trip segment.
     * @return segmentStartTime attribute.
     */
    public int getSegmentStartTime(){
        return this.segmentStartTime;
    }
    /**
     * Method to get ending time of trip segment.
     * @return segmentEndTime attribute.s
     */
    public int getSegmentEndTime(){
        return this.segmentEndTime;
    }
    /**
     * Method to get written instructions for traveling during trip segment.
     * @return String of instructions.
     */
    public String getSegmentInstructions(){
        String instr;
        instr = "Take the " + this.busName + " from "
                + segmentStartLoc.getName() + " to " 
                + segmentEndLoc.getName() + " which will take "
                + Integer.toString(segmentEndTime-segmentStartTime) 
                + " seconds .\n";
        return instr;
    }
}
