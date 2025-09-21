package com.ecosim;

public class Coordinate {
    private Map container;
    private int x; // X-coordinate of the object
    private int y; // Y-coordinate of the object
    public Coordinate(int x, int y, Map container) {
        this.x = x;
        this.y = y;
        this.container = container;
    }
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
     * Return the 8 adjacent coordinates (N, S, E, W and diagonals) around this coordinate.
     */
    public java.util.List<Coordinate> getAdjacentCoordinates(){
        java.util.List<Coordinate> out = new java.util.ArrayList<>();
        for(int dy = -1; dy <= 1; dy++){
            for(int dx = -1; dx <= 1; dx++){
                if(dx == 0 && dy == 0) continue;
                out.add(new Coordinate(this.x + dx, this.y + dy, this.container));
            }
        }
        return out;
    }

    /** Return true if this coordinate falls within its map bounds. */
    public boolean isInBounds(){
        return this.x >= 0 && this.y >= 0 && this.x < container.cols && this.y < container.rows;
    }

    /**
     * Convenience getter for the map this coordinate belongs to.
     */
    public Map getMap(){
        return container;
    }

    /**
     * Return the cell at this coordinate in the map, or null if none exists.
     */
    public Cell getCell(){
        if(!isInBounds()) return null;
        for(Cell c : container.cellHash){
            if(c.position.equals(this)) return c;
        }
        return null;
    }
    public Boolean equals(Coordinate otherCoord){
        return otherCoord.getX() == this.x && otherCoord.getY() == this.y && otherCoord.getContainer() == this.container;
    }
    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
    public Map getContainer() {
        return container;
    }

}
