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
    /** 
     * @param n
     * @return int[]
     */
    protected static int[] randomPermutation(int n){
        ArrayList<Integer> intPicker = new ArrayList<>();
        java.util.Random rnd = new java.util.Random();
        for(int i = 0; i < n; i++) {
            int num =  rnd.nextInt(n);
            while(intPicker.contains(num)) {
                num = rnd.nextInt(n);
            }
            intPicker.add(num);
        }
        return intPicker.stream().mapToInt(Integer::intValue).toArray();
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


