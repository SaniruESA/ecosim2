package com.ecosim;

public class GrassCell extends Cell {
    private float growthStage = 0;
    public GrassCell(Coordinate position) {
        super(position, "__");
        state = CellState.IDLE;
    }
    @Override
    protected void idleBehavior(){
        setGrowthState();
        growthStage += 3f;
    }
    protected void setGrowthState(){
        switch ((int)growthStage) {
            case 0 -> symbol = new NDigitString("⏑", 2);
            case 1 -> symbol = new NDigitString("╭", 2);
            case 2 -> symbol = new NDigitString("⎠", 2);
            case 3 -> symbol = new NDigitString("⌠", 2);
            case 4 -> symbol = new NDigitString("⎫", 2);
            case 5 -> symbol = new NDigitString("|", 2);
            default -> symbol = new NDigitString("|", 2);
        }
    }
}
