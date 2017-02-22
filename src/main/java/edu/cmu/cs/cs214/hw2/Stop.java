package edu.cmu.cs.cs214.hw2;
/**
 * Interface for a bus stop that has its name and location.
 * @author Kathleen
 *
 */
public interface Stop {
    /**
     * Gives clients access to private variable name.
     * @return Name that is an attribute of the object.
     */
    String getName();
    /**
     * Gives clients access to private variable latitude.
     * @return Latitude that is an attribute of the object, giving location.
     */
    double getLatitude();
    /**
     * Gives clients access to private variable longitude.
     * @return Longitude that is an attribute of the object, giving location.
     */
    double getLongitude();
}
