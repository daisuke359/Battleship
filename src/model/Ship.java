package model;

import java.util.ArrayList;
/**
 * The class is to have the information of Ship
 * @author Group 6
 *
 */
public class Ship {

    private ArrayList<String> location;
    private ArrayList<String> hit;

    /**
     * Contractor
     * @param location
     * @param hit
     */
    public Ship(ArrayList<String> location, ArrayList<String> hit) {
        this.location = location;
        this.hit = hit;
    }

    /**
     * Default Contractor
     */
    public Ship() {
        this.location = new ArrayList<String>();
        this.hit = new ArrayList<String>();
        hit.add("");
        hit.add("");
        hit.add("");
    
    }


    /**
     * Set the information of the location
     * @param location
     */
    public void setLocation(ArrayList<String> location) {
        this.location = location;
    }

    /**
     * Set the information of the hit
     * @param hit
     * @param index
     */
    public void setHit(String hit, int index) {
        this.hit.set(index, hit);
    }

    /**
     * Get the information of the location
     * @return
     */
    public ArrayList<String> getLocation() {
        return location;
    }
    
    /**
     * Get the information of the hit
     * @return
     */
    public ArrayList<String> getHit() {
        return hit;
    }
}
