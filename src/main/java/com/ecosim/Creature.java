package com.ecosim;

public class Creature extends Cell {
    public Creature(Coordinate position) {
        super(position, "C1");
        state = CellState.IDLE;
    }
    @Override
    protected void idleBehavior(){
        position.translate(1, 0);
    }
    
}
