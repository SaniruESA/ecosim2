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
