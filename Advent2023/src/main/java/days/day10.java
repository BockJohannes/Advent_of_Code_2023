package days;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import common.AdventReader;

public class day10 {

	 public static void main(String[] args) {
	    	
			char[][] grid = AdventReader.parseSchematic(AdventReader.read("10"));
			int[] postitionStartS = AdventReader.getPositionElement(grid, 'S');
	        int[][] distance = new int[grid.length][grid[0].length];
	        for (int[] row : distance) {
	            Arrays.fill(row, -1);
	        }
	        distance[postitionStartS[0]][postitionStartS[1]] = 0;
	        
	        for (int i = 0; i < grid.length * grid[0].length; i++) {
	            List<Integer> startX = new ArrayList<>();
	            List<Integer> startY = new ArrayList<>();
	            for (int x = 0; x < grid.length; x++) {
	                for (int y = 0; y < grid[0].length; y++) {
	                    if (distance[x][y] == i) {
	                        startX.add(x);
	                        startY.add(y);
	                    }
	                }
	            }
	            for (int j = 0; j < startX.size(); j++) {
	                int x = startX.get(j);
	                int y = startY.get(j);
	                if (x > 0 && (grid[x-1][y] == '7' || grid[x-1][y] == 'F' || grid[x-1][y] == '|') && distance[x-1][y] == -1) {
	                    distance[x-1][y] = i+1;
	                }
	                if (x < distance.length-1 && (grid[x+1][y] == 'J' || grid[x+1][y] == 'L' || grid[x+1][y] == '|') && distance[x+1][y] == -1) {
	                    distance[x+1][y] = i+1;
	                }
	                if (y > 0 && (grid[x][y-1] == 'L' || grid[x][y-1] == 'F' || grid[x][y-1] == '-') && distance[x][y-1] == -1) {
	                    distance[x][y-1] = i+1;
	                }
	                if (y < distance[0].length-1 && (grid[x][y+1] == 'J' || grid[x][y+1] == '7' || grid[x][y+1] == '-') && distance[x][y+1] == -1) {
	                    distance[x][y+1] = i+1;
	                }
	            }
	        }
	        
	        int maxDistance = 0;
	        for (int[] row : distance) {
	            for (int cell : row) {
	            	maxDistance = Math.max(maxDistance, cell);
	            }
	        }
	        AdventReader.printResult(maxDistance, null);
	        
	 }
}
