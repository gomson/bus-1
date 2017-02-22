package edu.cmu.cs.cs214.hw2;
/**
 * Class for waiting times to implement TypeSegment.
 * @author Kathleen
 *
 */
public class WaitSegment implements TripSegment{
    private Stop segmentStartLoc, segmentEndLoc;
    private int segmentStartTime, segmentEndTime;
    /**
     * Constructor to initialize values for a segment that requires waiting.
     * @param pStart Start location.
     * @param pEnd Desired end location.
     * @param pStartTime starting time at location.
     * @param pEndTime Time associated with end location.
     */
    public WaitSegment(Stop pStart, Stop pEnd, int pStartTime, int pEndTime){
        segmentStartLoc = pStart;
        segmentEndLoc = pEnd;
        segmentEndTime = pEndTime;
        segmentStartTime = pStartTime;
    }
    /**
     * Method to indicate this is a wait segment.
     * @return String identifying type of trip segment.
     */
    public String getSegmentType(){
        return "Wait";
    }
    /**
     * Method to get starting location of trip segment.
     * @return segmentStartLoc attribute.
     */
    public Stop getSegmentStartLoc(){
        return this.segmentStartLoc;
    }
    /**
     * Method to get ending location of trip segment.
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
     * @return segmentEndTime attribute.
     */
    public int getSegmentEndTime(){
        return this.segmentEndTime;
    }
    /**
     * Method to get instructions describing this trip segment.
     * @return instructions attribute.
     */
    public String getSegmentInstructions(){
        String instr;
        instr = "Wait at " + segmentStartLoc.getName() + " for "
                + Integer.toString(segmentEndTime-segmentStartTime) + ".\n";
        return instr;
    }
}
