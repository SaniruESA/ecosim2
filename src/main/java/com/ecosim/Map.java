package com.ecosim;

import java.util.ArrayList;

public class Map {
    ArrayList<Cell> cellHash = new ArrayList<>();
    int rows = 30;
    int cols = 30;
    public Map(int cols, int rows){
        this.cols = cols;
        this.rows = rows;
    }
    public void AddCell(Cell c){
        cellHash.add(c);
    }
    public String getCellInList(int i, int j){
        String result = "  ";
        for(Cell target : cellHash){
            if(target.position.equals(new Coordinate(i, j, this))){
                result = target.toString();
                break;
            } 
        }
        return result;
    }
    @Override
    public String toString(){
        String result = "";
        result += "+" + "--".repeat(cols) + "+\n";
        for(int i = 0; i < rows; i++){
            result += "|";
            for(int j = 0; j < cols; j++){
                result += getCellInList(j, i);
            }
            result += "|\n";
        }
        result += "+" + "--".repeat(cols) + "+";
        return result;
    }
    public void Update() {
        for(Cell c : cellHash){
            c.Update();
        }
    }
}
