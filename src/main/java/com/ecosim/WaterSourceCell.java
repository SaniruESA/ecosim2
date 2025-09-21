package com.ecosim;
import java.util.ArrayList;
public class WaterSourceCell extends Cell {
    protected int recursionDepth = 8;
    protected boolean hasSpread = false;
    protected ArrayList<Coordinate> adjacent = position.getAdjacentCoords();;
    protected int nextIndex = 0;
    public WaterSourceCell(Coordinate position, int recursionDepth) {
        super(position, "░░");
        this.recursionDepth = recursionDepth;
        this.nextIndex = 0;
        state = CellState.IDLE;
    }

    @Override
    protected void idleBehavior() {
        if(!hasSpread && recursionDepth > 0){
            if(nextIndex < adjacent.size()){
                Coordinate nextCoord = adjacent.get(nextIndex);
                position.getMap().AddCell(new WaterSourceCell(nextCoord, recursionDepth - 1));
                nextIndex++;
                return;
            }
            hasSpread = true;
            setGrowthState();
        }
    }
    protected void setGrowthState(){
        switch ((int)recursionDepth) {
            case 1 -> symbol = new NDigitString("▒▒", 2);
            case 2 -> symbol = new NDigitString("▓▓", 2);
            case 3 -> symbol = new NDigitString("██", 2);
            default -> symbol = new NDigitString("██", 2);
        }
    }
}


