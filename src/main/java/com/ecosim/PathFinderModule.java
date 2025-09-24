package com.ecosim;

import java.util.ArrayList;

public class PathFinderModule {
    public static Coordinate findPath(Coordinate start, Coordinate end, Map map) {
        int f = 0; // Total cost
        int g = 0; // Cost from start to current node
        int h = Integer.MAX_VALUE; // Heuristic cost from current node to end
        ArrayList<Coordinate> adjacentCoords = start.getAdjacentCoords();
        Coordinate nextStep = null;
        int lowestF = Integer.MAX_VALUE;
        for(Coordinate coord : adjacentCoords){
            g += 1;
            h = Math.abs(coord.getX() - end.getX()) + Math.abs(coord.getY() - end.getY()); 
            f = g + h; // Total cost
            if(f < lowestF){
                lowestF = f;
                nextStep = coord;
            }
            g -= 1; 
        }
        if(nextStep != null){
            return nextStep;
        } else {
            return start;
        }
    }
}
