package com.ecosim;

public class Map {
    int rows = 30;
    int cols = 30;
    Cell[][] grid;
    public Map(int cols, int rows){
        this.cols = cols;
        this.rows = rows;
        grid = new Cell[rows][cols];
    }
    public void AddCell(Cell c){
        grid[c.position.getY()][c.position.getX()] = c;
    }
    public String getCellInList(int i, int j){
        String result = "  ";
        if(grid[j][i] != null){
            grid[j][i].Update();
            result = grid[j][i].toString();
        }
        return result;
    }
    public boolean isValidCoordinate(Coordinate coord){
        return coord.getX() >= 0 && coord.getX() < cols && coord.getY() >= 0 && coord.getY() < rows && grid[coord.getY()][coord.getX()] == null;
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
}
