package com.ecosim;

public class WaterSourceCell extends Cell {
    protected int recursionDepth = 8;
    protected boolean hasSpread = false;
    protected java.util.List<Coordinate> adjacentToConsider = null;
    protected int nextIndex = 0;
    public WaterSourceCell(Coordinate position, int recursionDepth) {
        super(position, "▧▧");
        this.recursionDepth = recursionDepth;
        state = CellState.IDLE;
    }

    @Override
    protected void idleBehavior() {
        if (recursionDepth <= 0) return;

        if (adjacentToConsider == null) {
            adjacentToConsider = new java.util.ArrayList<>();
            for (Coordinate coord : position.getAdjacentCoordinates()) {
                if (coord.isInBounds()) {
                    adjacentToConsider.add(coord);
                }
            }
            nextIndex = 0;
        }
        while (nextIndex < adjacentToConsider.size()) {
            Coordinate coord = adjacentToConsider.get(nextIndex);
            nextIndex++;
            if (coord.getCell() == null) {
                coord.getMap().AddCell(new WaterSourceCell(coord, recursionDepth - 1));
                return;
            }
        }
        if (nextIndex >= adjacentToConsider.size()) {
            hasSpread = true;
            setGrowthState();
        }
        
    }
    protected void setGrowthState(){
        switch ((int)recursionDepth) {
            case 1 -> symbol = new NDigitString("▧▧", 2);
            case 2 -> symbol = new NDigitString("▦▦", 2);
            case 3 -> symbol = new NDigitString("▩▩", 2);
            case 4 -> symbol = new NDigitString("■■", 2);
            case 5 -> symbol = new NDigitString("■■", 2);
            case 6 -> symbol = new NDigitString("■■", 2);
            default -> symbol = new NDigitString("■■", 2);
        }
    }
}


