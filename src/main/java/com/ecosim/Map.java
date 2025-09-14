package com.ecosim;

public class Map {
    int rows = 30;
    int cols = 30;
    public Map(int cols, int rows){
        this.cols = cols;
        this.rows = rows;
    }
    @Override
    public String toString(){
        String result = "";
        result += "+" + "--".repeat(cols) + "+\n";
        for(int i = 0; i < rows; i++){
            result += "|";
            for(int j = 0; j < cols; j++){
                result += "  ";
            }
            result += "|\n";
        }
        result += "+" + "--".repeat(cols) + "+";
        return result;
    }
}
