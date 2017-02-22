package edu.cmu.cs.cs214.hw2;
/**
 * Class that implements Stop to describe a bus stop's name and location.
 * @author Kathleen
 *
 */
public class StopLocation implements Stop{
    private String name;
    private double latitude, longitude;
    /**
     * Constructor for StopLocation that initializes attributes.
     * @param pName Bus stop name.
     * @param pLat Latitude describing actual stop location.
     * @param pLong Longitude describing actual stop location.
     */
    public StopLocation(String pName, double pLat, double pLong){
        name = pName;
        latitude = pLat;
        longitude = pLong;
    }
    /**
     * Method to give access to private variable name's value.
     * @return name attribute's value. 
     */
    public String getName(){
        return this.name;
    }
    /**
     * Method to give access to private variable latitude's name.
     * @return latitude attribute's value.
     */
    public double getLatitude(){
        return this.latitude;
    }
    /**
     * Method to give access to private variable longitude's name.
     * @return longitude attribute's value.
     */
    public double getLongitude(){
        return this.longitude;
    }
}
