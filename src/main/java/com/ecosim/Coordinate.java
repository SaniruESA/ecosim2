package com.ecosim;
import java.util.ArrayList;
public class Coordinate {
    private Map container;
    private int x; // X-coordinate of the object
    private int y; // Y-coordinate of the object
    public Coordinate(int x, int y, Map container) {
        this.x = x;
        this.y = y;
        this.container = container;
    }
    /** 
     * @param x
     * @param y
     * @return Coordinate
     */
    public Coordinate translate(int x, int y){
        this.x += x;
        this.y += y;
        if(this.x < 0){ this.x = 0; }
        if(this.y < 0){ this.y = 0; }
        if(this.x > container.cols - 1){ this.x = container.cols - 1; }
        if(this.y > container.rows - 1){ this.y = container.rows - 1; }
        return new Coordinate(this.x, this.y, this.container);
    }

    /** 
     * @return Map
     */
    public Map getMap(){
        return container;
    }
    /** 
     * @return ArrayList<Coordinate>
     */
    public ArrayList<Coordinate> getAdjacentCoords(){
        ArrayList<Coordinate> adjacentCoords = new ArrayList<>();
        if(container.isValidCoordinate(new Coordinate(x - 1, y - 1, container))) // Bottom-left
            adjacentCoords.add(new Coordinate(x - 1, y - 1, container));
        if(container.isValidCoordinate(new Coordinate(x - 1, y + 1, container))) //  Top-left
            adjacentCoords.add(new Coordinate(x - 1, y + 1, container));
        if(container.isValidCoordinate(new Coordinate(x - 1, y, container))) //  Middle-left
            adjacentCoords.add(new Coordinate(x - 1, y, container));
        if(container.isValidCoordinate(new Coordinate(x + 1, y - 1, container))) // Bottom-right
            adjacentCoords.add(new Coordinate(x + 1, y - 1, container));
        if(container.isValidCoordinate(new Coordinate(x + 1, y + 1, container))) //  Top-right
            adjacentCoords.add(new Coordinate(x + 1, y + 1, container));
        if(container.isValidCoordinate(new Coordinate(x + 1, y, container))) //  Middle-right
            adjacentCoords.add(new Coordinate(x + 1, y, container));
        if(container.isValidCoordinate(new Coordinate(x, y + 1, container))) //  Top
            adjacentCoords.add(new Coordinate(x, y + 1, container));
        if(container.isValidCoordinate(new Coordinate(x, y - 1, container))) //  Bottom
            adjacentCoords.add(new Coordinate(x, y - 1, container));
        return adjacentCoords;
    }
    /** 
     * @param otherCoord
     * @return Boolean
     */
    public Boolean equals(Coordinate otherCoord){
        return otherCoord.getX() == this.x && otherCoord.getY() == this.y && otherCoord.getContainer() == this.container;
    }
    /** 
     * @return int
     */
    public int getX() {
        return x;
    }
    /** 
     * @return int
     */
    public int getY() {
        return y;
    }
    /** 
     * @return Map
     */
    public Map getContainer() {
        return container;
    }

}
