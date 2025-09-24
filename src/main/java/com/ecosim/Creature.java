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
        position.translateTo(PathFinderModule.findPath(position, new Coordinate(25, 20, position.getMap()), position.getMap()));
    }
    
}
