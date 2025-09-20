package com.ecosim;

public class Cell {
    public Coordinate position;
    protected NDigitString symbol;
    protected long hashCode;
    public Cell(Coordinate position, String symbol){
        hashCode = symbol.charAt(0) * 31 + symbol.charAt(1) + (long)(Math.random() * 10);
        this.position = position;
        this.symbol = new NDigitString(symbol,2);
    }
    @Override
    public String toString(){
        return symbol.toString();
    }
    public void Update(){
        position.translate(1, 0);
    }
    public long getHashCode() {
        return hashCode;
    }
}
