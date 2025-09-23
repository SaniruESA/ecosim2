package com.ecosim;

public class Creature extends Cell {
    public int[] movementVector = {0, 0};
    public Creature(Coordinate position) {
        super(position, "");
        state = CellState.IDLE;
    }
    @Override
    protected void idleBehavior(){
        //Translate randomly every update
        // (int)(Math.random() * 3) - 1
        if(Math.random() < 0.25){
            movementVector[0] = (int)(Math.random() * 3) - 1;
            movementVector[1] = (int)(Math.random() * 3) - 1;
        }
        position.translate(movementVector[0], movementVector[1]);
    }
    
}
