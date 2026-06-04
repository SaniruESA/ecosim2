package com.ecosim;

import java.util.ArrayList;

public class PathFinderModule {
    private static ArrayList<Coordinate> cachedPath = null;
    private static Coordinate cachedEnd = null;
    private static int lastMapHash = 0;
    private static int pathIndex = 0;

    public static Coordinate findPath(Coordinate start, Coordinate end, Map map) {
        int currentMapHash = map.hashCode(); // Detect if map changed (water regenerated)
        
        // Recalculate path if: map changed, destination changed, or path exhausted
        if (cachedPath == null || currentMapHash != lastMapHash || 
            !end.equals(cachedEnd) || pathIndex >= cachedPath.size()) {
            
            cachedPath = calculatePath(start, end, map);
            cachedEnd = end;
            lastMapHash = currentMapHash;
            pathIndex = 0;
        }
        
        // Return next step in cached path
        if (cachedPath != null && pathIndex < cachedPath.size()) {
            return cachedPath.get(pathIndex++);
        }
        
        return start;
    }

    private static ArrayList<Coordinate> calculatePath(Coordinate start, Coordinate end, Map map) {
        // A* pathfinding to build full path
        ArrayList<Coordinate> path = new ArrayList<>();
        Coordinate current = start;
        
        while (!current.equals(end)) {
            Coordinate nextStep = getNextStep(current, end, map);
            if (nextStep.equals(current)) {
                break; // Stuck, no path available
            }
            path.add(nextStep);
            current = nextStep;
        }
        
        return path;
    }

    private static Coordinate getNextStep(Coordinate start, Coordinate end, Map map) {
        ArrayList<Coordinate> adjacentCoords = start.getAdjacentCoords();
        Coordinate nextStep = null;
        int lowestF = Integer.MAX_VALUE;
        
        for (Coordinate coord : adjacentCoords) {
            // Check if the coordinate is walkable (not water or obstacle)
            if (!map.isValidCoordinate(coord)) {
                continue;
            }
            
            int g = 1; // Cost from start to this coord
            int h = Math.abs(coord.getX() - end.getX()) + Math.abs(coord.getY() - end.getY());
            int f = g + h;
            
            if (f < lowestF) {
                lowestF = f;
                nextStep = coord;
            }
        }
        
        return (nextStep != null) ? nextStep : start;
    }
}