package com.ecosim;
import java.util.PriorityQueue;
import java.util.Queue;
public class Creature extends Cell {
    Queue<String> actionQueue;
    public int[] movementVector = {0, 0};
    public Creature(Coordinate position) {
        super(position, ":)");
        state = CellState.IDLE;
        actionQueue = new PriorityQueue<>();
    }
    public Creature(Coordinate position, String symbol) {
        super(position, symbol);
        state = CellState.IDLE;
        actionQueue = new PriorityQueue<>();
    }
    @Override
    protected void idleBehavior(){
        Coordinate target = new Coordinate(30, 0, position.getMap());
        Coordinate nextStep = PathFinderModule.findPath(position, target, position.getMap());
        
        // Only move if we got a valid next step (not stuck)
        if (!nextStep.equals(position)) {
            position.translateTo(nextStep);
        }
    }
    
}
