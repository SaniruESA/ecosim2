package com.ecosim;
import java.util.ArrayList;
public class WaterSourceCell extends Cell {
    protected int recursionDepth = 8;
    protected boolean hasSpread = false;
    protected ArrayList<Coordinate> adjacent = position.getAdjacentCoords();
    int[] randomArr = randomPermutation(adjacent.size());
    protected int nextIndex = 0;
    public WaterSourceCell(Coordinate position, int recursionDepth) {
        super(position, "░░");
        this.recursionDepth = recursionDepth;
        this.nextIndex = 0;
        state = CellState.IDLE;
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
                position.getMap().AddCell(new WaterSourceCell(nextCoord, recursionDepth - 1));
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
        // symbol = new NDigitString(recursionDepth + "", 2);
        switch (recursionDepth) {
            case 0 -> symbol = new NDigitString("░░", 2);
            case 1 -> symbol = new NDigitString("▒▒", 2);
            case 2 -> symbol = new NDigitString("▓▓", 2);
            case 3 -> symbol = new NDigitString("██", 2);
            default -> symbol = new NDigitString("██", 2);
        }
    }
}


