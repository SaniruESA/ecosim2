package com.ecosim;

public class Cell {
    public Coordinate position;
    protected NDigitString symbol;
    public Cell(Coordinate position, String symbol){
        this.position = position;
        this.symbol = new NDigitString(symbol,2);
    }
    public String toString(){
        return symbol.toString();
    }
    public void Update(){
        position.translate(1, 0);
    }
}
