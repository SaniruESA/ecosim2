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
    // defensive: ensure symbol has at least 2 chars
    if (symbol == null) symbol = "  ";
    if (symbol.length() == 0) symbol = "  ";
    else if (symbol.length() == 1) symbol = symbol + " ";
    this.position = position;
    this.symbol = new NDigitString(symbol,2);
    hashCode = this.symbol.toString().charAt(0) * 31 + this.symbol.toString().charAt(1) + (long)(Math.random() * 10);
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
