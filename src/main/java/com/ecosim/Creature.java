package com.ecosim;
import java.util.PriorityQueue;
import java.util.Queue;
public class Creature extends Cell {
    Queue<String> actionQueue;
    public int[] movementVector = {0, 0};
    public Creature(Coordinate position) {
        super(position, "SK");
        state = CellState.IDLE;
        actionQueue = new PriorityQueue<>();
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
