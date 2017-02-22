package edu.cmu.cs.cs214.hw2;
/**
 * Interface for TripSegments to be used by Itinerary.
 * @author Kathleen
 *
 */
public interface TripSegment {
    /**
     * Method to distinguish between wait and bus segments.
     * @return Returns "wait" or "bus" depending on type of segment.
     */
    String getSegmentType();
    /**
     * Method to get starting location of trip segment.
     * @return Stop representing start location.
     */
    Stop getSegmentStartLoc();
    /**
     * Method to get ending location of trip segment.
     * @return Stop representing end location.
     */
    Stop getSegmentEndLoc();
    /**
     * Method to get starting time of trip segment.
     * @return int representing starting time.
     */
    int getSegmentStartTime();
    /**
     * Method to get ending time of trip segment.
     * @return int representing ending time.
     */
    int getSegmentEndTime();
    /**
     * Method to get written instructions for traveling on trip segment.
     * @return String representing instructions.
     */
    String getSegmentInstructions();
}
