package days;

import common.AdventReader;

public class day14 {
	
	public static void main(String[] args) {

		char[][] grid = AdventReader.parseSchematic(AdventReader.read("14"));
		int[][] rocks = AdventReader.getAllPositionsOfElement(grid, 'O');
		
		char[][] gridPart1 = moveRocks(rocks, grid);
//		char[][] gridPart2 = grid;
		
//		for (int i = 0; i < 1000000000; i++) {
//			rocks = AdventReader.getAllPositionsOfElement(gridPart2, 'O'); 
//			gridPart2 = cycleRocks(rocks, gridPart2);
//		}
		
		int part1 = countRocks(gridPart1);
//		int part2 = countRocks(gridPart2);
		AdventReader.printResult(part1, null);
		
	}
	
	public static char[][] moveRocks(int[][] rocks, char[][] grid) {

		for (int[] rock : rocks) {
			int y = rock[0], x = rock[1];
			int newRockY = 	checkNorth(y,x,grid);
			if (newRockY > 0) {
				grid[y][x] = '.';
				grid[y-newRockY][x] = 'O';
			}
		}
		return grid;
	}
	
	public static char[][] cycleRocks(int[][] rocks, char[][] grid){
		
		for (int i = 0; i < 4; i++) {
			for (int[] rock : rocks = AdventReader.getAllPositionsOfElement(grid, 'O')) {
				int y = rock[0], x = rock[1];
				int newRockY = 	checkNorth(y,x,grid);
				if (newRockY > 0) {
					grid[y][x] = '.';
					grid[y-newRockY][x] = 'O';
				}
			}
			grid = rotate90DegreesCounterClockwise(grid);	
		}		
		return grid;
	}
	
	public static char[][] rotate90DegreesCounterClockwise(char[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;

        char[][] result = new char[cols][rows];

        for (int y = 0; y < rows; y++) {
            for (int x = 0; x < cols; x++) {
                result[cols - 1 - x][y] = matrix[x][y];
            }
        }
        return result;
    }
	
	public static int checkNorth(int y, int x, char[][] grid) {
		int count = 1;
		boolean north = true;
		while (north) {
			if  (y - count >= 0 && grid[y - count][x] == '.') count++;
			else return count-1;
		}
		return count-1;
	}

	public static int countRocks(char[][] newGrid) {
		int sum = 0;
		int[][] newRocks = AdventReader.getAllPositionsOfElement(newGrid, 'O');
		for(int[] newRock : newRocks) {
			sum += newGrid.length - newRock[0];
		}
		return sum;
	}
}