package days;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import common.AdventReader;

public class day11 {
	
	public static class Galaxy {

		private long xAxis;
		private long yAxis;

		public Galaxy(long x, long y) {
			this.xAxis = x;
			this.yAxis = y;
		}
		public long getDistance(Galaxy different) {
			return Math.abs(different.xAxis - xAxis) + Math.abs(different.yAxis - yAxis);
		}
	}

	private static long calculateTotalGalacticDistances(int width, int height, char[][] universeGrid, int increment) {

	    long totalDistancesX = 0, totalDistancesY = 0;

	    long[] columnDistances = new long[width];
	    long[] rowDistances = new long[height];

	    // Calculate distances for empty columns and update columnDistances array
	    for (int x = 0; x < width; x++) {
	        boolean columnEmpty = true;
	        for (int y = 0; y < height; y++) {
	            if (universeGrid[x][y] != '.') {
	                columnEmpty = false;
	                break;
	            }
	        }
	        if (columnEmpty) {
	            totalDistancesX += increment;
	        }
	        columnDistances[x] = totalDistancesX;
	    }

	    // Calculate distances for empty rows and update rowDistances array
	    for (int y = 0; y < height; y++) {
	        boolean rowEmpty = true;
	        for (int x = 0; x < width; x++) {
	            if (universeGrid[x][y] != '.') {
	                rowEmpty = false;
	                break;
	            }
	        }
	        if (rowEmpty) {
	            totalDistancesY += increment;
	        }
	        rowDistances[y] = totalDistancesY;
	    }

	    // Store coordinates of non-empty galaxies in the universe
	    List<Galaxy> galaxies = new ArrayList<>();
	    for (int y = 0; y < height; y++) {
	        for (int x = 0; x < width; x++) {
	            if (universeGrid[x][y] != '.') {
	                galaxies.add(new Galaxy(x + columnDistances[x], y + rowDistances[y]));
	            }
	        }
	    }

	    // Calculate total pairwise distances between galaxies in the universe
	    long totalGalacticDistances = IntStream.range(0, galaxies.size())
	            .mapToLong(src -> IntStream.range(src, galaxies.size())
	                    .mapToLong(dest -> galaxies.get(src).getDistance(galaxies.get(dest)))
	                    .sum())
	            .sum();

	    return totalGalacticDistances;
	}
	
	public static void main(String[] args) {
		
		char[][] universeGrid = AdventReader.parseSchematic(AdventReader.read("11"));
		int width = universeGrid.length, height = universeGrid[0].length;

		long part1 = calculateTotalGalacticDistances(width, height, universeGrid, 1);
		long part2 = calculateTotalGalacticDistances(width, height, universeGrid, 999999);

		AdventReader.printResult(part1, part2);
	}
}