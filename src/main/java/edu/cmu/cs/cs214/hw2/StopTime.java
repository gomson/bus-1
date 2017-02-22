package edu.cmu.cs.cs214.hw2;

/**
 * This class represents a vertex in the bus map graph.
 * @author Kathleen
 *
 */
public class StopTime implements Vertex{
    private String name; //will be comma separated string of stopName, lat, long, time
    private static final int INFINITY = 86500;
    private int value; //used for Dijkstra computation
    /**
     * Stop time constructor to instantiate name and value for a vertex.
     * @param pName Provided name for stop.
     */
    public StopTime(String pName){
        name = pName;
        value = INFINITY; /* representation of infinity since max seconds in a 
                        day is 86400 - for Dijkstra's*/
    }
    /**
     * Method gives client access to private variable name.
     * @return name attribute's value.
     */
    public String getName(){
        return this.name;
    }
    /**
     * Method to give client access to private variable value. 
     * @return value attribute's value.
     */
    public int getValue(){
        return this.value;
    }
    /**
     * Method for clients to change private variable value's name.s 
     * @param pVal New value that client wants to associate with vertex.
     */
    public void setValue(int pVal){
        value = pVal;
    }
    /* The following overrides for equals and hashCode were based off
     * of the stackoverflow examples.
     */
    /**
     * Overriding method to check vertex equality based on name of StopTime.
     * @param obj vertex to be compared.
     * @return true if StopLocations are equal, false otherwise.
     */
    @Override
    public boolean equals(Object obj){
        if(this == obj) return true;
        if(obj == null) return false;
        if(getClass() != obj.getClass()) return false;
        final Vertex other = (Vertex) obj;
        return this.name.equals(other.getName());
        }
    /**
     * Overriding method to generate hashcode for objects.
     * @return integer hashcode.
     */
    @Override
    public int hashCode(){
        final int prime = 31;
        int result = 1;
        
        result = prime * result;
        
        if(this.name == null) result += 0;
        else result += this.name.hashCode();

        return result;
        }

}
