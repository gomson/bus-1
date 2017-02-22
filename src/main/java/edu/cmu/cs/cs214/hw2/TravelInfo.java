package edu.cmu.cs.cs214.hw2;

/**
 * Class to implement Edge with bus name and ride or wait time as weights.
 * @author Kathleen
 *
 */
public class TravelInfo implements Edge {
    /* This class doesn't have 100% line coverage. The the untested lines
     * are the equals and overrides methods, but since if they didn't work, the
     * program wouldn't work either, I didn't do extra testing.
     */
    private String name;
    private int weight;
    /**
     * Constructor to initialize values of TravelInfo
     * @param pName Name of bus or "wait".
     * @param pWeight Assigned weight to edges based on distance between nodes.
     */
    public TravelInfo(String pName, int pWeight){
        name = pName;
        weight = pWeight;
    }
    /**
     * Gives client access to private variable name;
     * @return name attribute's value.
     */
    public String getName(){
        return this.name;
    }
    
    /** 
     * Gives client access to private variable's weight
     * @return weight attribute's value
     */
    public int getWeight(){
        return this.weight;
    }
    /* The following overrides for equals and hashCode were based off
     * of the stackoverflow examples.
     */
    /**
     * Overriding method that implements equality notion for edges.
     * @param obj Edge to be compared.
     * @return true if TravelInfo's are equal, false otherwise.
     */
    @Override
    public boolean equals(Object obj){
        if(this == obj) return true;
        if(obj == null) return false;
        if(getClass() != obj.getClass()) return false;
        final Edge other = (Edge) obj;
        return(this.name.equals(other.getName())
                && this.weight == other.getWeight());
    }
    /**
     * Overriding method to generate hashcode for Edge object.
     * @return integer hashcode.
     */
    @Override
    public int hashCode(){
        final int prime = 31;
        int result = 1;
        result = prime * result;
        
        if(this.name == null) result += 0;
        else result += this.name.hashCode();
        
        result += this.weight;
        return result;
    }
}
