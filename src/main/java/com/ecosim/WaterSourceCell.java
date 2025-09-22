package com.ecosim;
import java.util.ArrayList;

import javafx.scene.paint.Color;
public class WaterSourceCell extends Cell {
    protected int recursionDepth = 8;
    protected boolean isAlpha = false;
    protected boolean hasSpread = false;
    protected ArrayList<Coordinate> adjacent = position.getAdjacentCoords();
    int[] randomArr = randomPermutation(adjacent.size());
    protected int nextIndex = 0;
    public WaterSourceCell(Coordinate position, int recursionDepth, boolean isAlpha) {
        super(position, "░░");
        color = Color.AQUA;
        this.recursionDepth = recursionDepth;
        this.nextIndex = 0;
        state = CellState.IDLE;
        this.isAlpha = isAlpha;
    }
    protected static int[] randomPermutation(int n){
        int[] arr = new int[n];
        for(int i = 0; i < n; i++) arr[i] = i;
        java.util.Random rnd = new java.util.Random();
        for(int i = n - 1; i > 0; i--){
            int j = rnd.nextInt(i + 1);
            int tmp = arr[i]; arr[i] = arr[j]; arr[j] = tmp;
        }
        return arr;
    }
    
    @Override
    protected void idleBehavior() {
        if(!hasSpread && recursionDepth > 0){
            setGrowthState();

            // Random spreading

            if(nextIndex < randomArr.length){
                Coordinate nextCoord = adjacent.get(randomArr[nextIndex]);
                position.getMap().AddCell(new WaterSourceCell(nextCoord, recursionDepth - 1, false));
                nextIndex++;
            } else {
                hasSpread = true;
            }

            // Uniform spreading 

            /*if(nextIndex < adjacent.size()){
                Coordinate nextCoord = adjacent.get(nextIndex);
                position.getMap().AddCell(new WaterSourceCell(nextCoord, recursionDepth - 1));
                nextIndex++;
            } else {
                hasSpread = true;
            }*/
        } 
    }
    protected void setGrowthState(){
        // DEBUG VIEW 
        /* if(isAlpha){
            symbol = new NDigitString("A", 2);
        } else {
            symbol = new NDigitString(recursionDepth + "", 2);
        }*/
        // VISUALIZATION VIEW 
        switch (recursionDepth) {
            case 0:
                symbol = new NDigitString("░░", 2);
                color = Color.AQUA;
                break;
            case 1:
                symbol = new NDigitString("▒▒", 2);
                color = Color.DEEPSKYBLUE;
                break;
            case 2:
                symbol = new NDigitString("▓▓", 2);
                color = Color.DEEPSKYBLUE;
                break;
            case 3:
                symbol = new NDigitString("██", 2);
                color = Color.DODGERBLUE;
                break;
            default:
                symbol = new NDigitString("██", 2);
                color = Color.DODGERBLUE;
                break;
        }
    }
}


