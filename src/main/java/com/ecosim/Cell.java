package com.ecosim;

import javafx.scene.paint.Color;

public abstract class Cell {
    public Coordinate position;
    protected NDigitString symbol;
    protected long hashCode;
    protected Color color = Color.WHITE;
    protected enum CellState {
        DEAD,
        IDLE,
        BREEDING,
        HUNTING,
        FLEEING
    }
    protected CellState state = CellState.IDLE;
    // Note: all cells have to have a one-parameter constructor that takes a coord as an argument
    public Cell(Coordinate position, String symbol){
        hashCode = symbol.charAt(0) * 31 + symbol.charAt(1) + (long)(Math.random() * 10);
        this.position = position;
        this.symbol = new NDigitString(symbol,2);
    }
    /** 
     * @return String
     */
    @Override
    public String toString(){
        return symbol.toString();
    }
    protected void Update(){
        switch (state) {
            case IDLE -> {
                idleBehavior();
            }
            case BREEDING -> {
                breedingBehavior();
            }
            case HUNTING -> {
                huntingBehavior();
            }
            case FLEEING -> {
                fleeingBehavior();
            }
            case DEAD -> {
                deadBehavior();
            }
            default -> throw new AssertionError();
        }
    }
    /** 
     * @return long
     */
    public long getHashCode() {
        return hashCode;
    }
    protected void idleBehavior(){

    }
    protected void breedingBehavior(){
        
    }
    protected void huntingBehavior(){
        
    }
    protected void fleeingBehavior(){
        
    }
    protected void deadBehavior(){
        
    }
    /** 
     * @return Color
     */
    public Color getColor(){
        return color;
    }
}
