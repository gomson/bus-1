package edu.cmu.cs.cs214.hw2;
/**
 * This class provides the determined travel route and directions.
 * @author Kathleen
 *
 */
public class Itinerary {
    private String name, instructions;
    private int startTime, endTime, waitTime;
    private Stop startLocation, endLocation;
    /**
     * Constructor that assigns start/end location and start time.
     * @param pStartLoc Client-supplied starting location.
     * @param pEndLoc Client-supplied desired ending location.
     * @param pStartTime Client-supplied starting time.
     */
    public Itinerary(Stop pStartLoc, Stop pEndLoc, int pStartTime){
        startTime = pStartTime;
        startLocation = pStartLoc;
        endLocation = pEndLoc;
        name = "Route plan from " + startLocation.getName() + " to " 
                + endLocation.getName() + "\n";
        instructions = name;
    }
    /**
     * Method to see private variable startTime's value.
     * @return value of startTime attribute.
     */
    public int getStartTime(){
        return this.startTime;
    }
    /**
     * Method to see private variable endTime's value.
     * @return value of endTime attribute.
     */
    public int getEndTime(){
        return this.endTime;
    }
    /**
     * Method to see private variable waitTime's value.
     * @return value of waitTime 
     */
    public int getWaitTime(){
        return this.waitTime;
    }
    /**
     * Method to see private variable startLocation's value.
     * @return value of startLocation.
     */
    public Stop getStartLocation(){
        return this.startLocation;
    }
    /**
     * Method to see private variable endLocation's value.
     * @return value of endLocation.
     */
    public Stop getEndLocation(){
        return this.endLocation;
    }
    /**
     * Method to see private variable instructions' value.
     * @return value of instructions.
     */
    public String getInstructions(){
        return this.instructions;
    }
    /**
     * Method to set the startTime attribute.
     * @param pStartTime Time at start location.
     */
    public void setStartTime(int pStartTime){
        this.startTime = pStartTime;
    }
    /**
     * Method to set private variable endTime's value based on route found.
     * @param pEndTime Best possible end time of travel.
     */
    public void setEndTime(int pEndTime){
        this.endTime = pEndTime;
    }
    /**
     * Method to set private variable waitTime's value based on route found.
     * @param pWaitTime wait time associated with best travel path.
     */
    public void setWaitTime(int pWaitTime){
        this.waitTime = pWaitTime;
    }
    /**
     * Method to update private variable instructions' value based on route.
     * @param pInstructions written out instructions associated with path.
     */
    public void setInstructions(String pInstructions){
        this.instructions = pInstructions;
    }
}
