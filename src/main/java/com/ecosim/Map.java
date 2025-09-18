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
    public Cell getCellInList(Cell c){
        for(Cell target : cellHash){
            if(target.position.equals(c.position)){
                return target;
            } 
        }
        return null;
    }
    @Override
    public String toString(){
        String result = "";
        result += "+" + "--".repeat(cols) + "+\n";
        for(int i = 0; i < rows; i++){
            result += "|";
            for(int j = 0; j < cols; j++){
                var cellThere = getCellInList(new Cell(new Coordinate(j, i, this), result));
                if(cellThere != null){
                    result += cellThere;
                } else {
                    result += "  ";
                }
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
