package com.ecosim;

import javafx.scene.paint.Color;

public class GrassCell extends Cell {
    private float growthStage = 0;
    public GrassCell(Coordinate position) {
        super(position, "__");
        state = CellState.IDLE;
        int RNG = (int)(Math.random() * 6);
        switch (RNG) {
            case 0:
                color = Color.LIGHTGREEN;
                break;
            case 1:
                color = Color.GREENYELLOW;
                break;
            case 2:
                color = Color.LIMEGREEN;
                break;
            case 3:
                color = Color.LIME;
                break;
            case 4:
                color = Color.FORESTGREEN;
                break;
            case 5:
                color = Color.DARKGREEN;
                break;
            default:
                throw new AssertionError();
        }
    }
    @Override
    protected void idleBehavior(){
        setGrowthState();
        growthStage += 1f;
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
