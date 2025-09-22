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
    /** 
     * @param xStart
     * @param yStart
     * @param xEnd
     * @param yEnd
     * @param cellType
     */
    public void AddCellInRange(int xStart, int yStart, int xEnd, int yEnd,  Class<? extends Cell> cellType){
        for(int i = yStart; i <= yEnd; i++){
            for(int j = xStart; j <= xEnd; j++){
                try {
                    AddCell(cellType.getDeclaredConstructor(Coordinate.class).newInstance(new Coordinate(j, i, this)));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
    /** 
     * @param c
     */
    public void AddCell(Cell c){
        if(isValidCoordinate(c.position) || c instanceof WaterSourceCell){
            grid[c.position.getY()][c.position.getX()] = c;
        } else {
            c.position = c.position.getAdjacentCoords().get(0);
        }
    }
    /** 
     * @param i
     * @param j
     * @return String
     */
    public String getCellInList(int i, int j){
        String result = "  ";
        if(grid[j][i] != null){
            grid[j][i].Update();
            result = grid[j][i].toString();
        }
        return result;
    }
    /** 
     * @param coord
     * @return boolean
     */
    public boolean isValidCoordinate(Coordinate coord){
        return coord.getX() >= 0 && coord.getX() < cols && coord.getY() >= 0 && coord.getY() < rows && grid[coord.getY()][coord.getX()] == null;
    }
    
    /**
     * O(1) lookup for a cell at x,y. Returns null if out of bounds or empty.
     */
    public Cell getCellAt(int x, int y){
        if(x < 0 || y < 0 || x >= cols || y >= rows) return null;
        return grid[y][x];
    }
    /** 
     * @return String
     */
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
    } public void recalculateWater(){
        for(int i = 0; i < rows; i++){
            for(int j = 0; j < cols; j++){
                if(grid[i][j] != null && grid[i][j] instanceof WaterSourceCell){
                    if(((WaterSourceCell)grid[i][j]).isAlpha){
                        ((WaterSourceCell) grid[i][j]).hasSpread = false;
                        ((WaterSourceCell) grid[i][j]).nextIndex = 0;
                        ((WaterSourceCell) grid[i][j]).randomArr = WaterSourceCell.randomPermutation(((WaterSourceCell) grid[i][j]).adjacent.size());
                    } else {
                        grid[i][j] = null;
                    }
                }
            }
        }
    }
}
