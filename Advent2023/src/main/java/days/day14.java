package days;

import java.util.HashMap;
import java.util.Map;

import common.AdventReader;

public class day14 {
	
	static int width;
	static int height;

	public static void main(String[] args){
		
		char[][] map = AdventReader.createGridFromString(AdventReader.read("14"));
		width = map[1].length;
		height = map.length;
		
		long part1 = countRocks(moveRocksNorth(map));
		
		Map<String, Long> index = new HashMap<String, Long>();
		for (long i = 0; i < 1000000000; i++) {
			map = cycle(map);
			String str = AdventReader.gridToString(map);
			if (index.containsKey(str)) {
				long delta = i - index.get(str);
				i += delta * ((1000000000-i) / delta);
			}
			index.put(str, i);
		}
		long part2 = countRocks(map); 
		
		AdventReader.printResult(part1, part2);
	}

	public static int countRocks(char[][] newGrid) {
		int sum = 0;
		int[][] newRocks = AdventReader.getAllPositionsOfElement(newGrid, 'O');
		for(int[] newRock : newRocks) {
			sum += newGrid.length - newRock[1];
		}
		return sum;
	}

	private static char[][] rotate90DegreesClockwise(char[][] map) {
		char[][] result = new char[height][width];
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				result[x][y] = map[y][height-x-1];
			}
		}
		return result;
	}
	
	private static char[][] cycle(char[][] map) {
		for (int i = 0; i < 4; i++) { map = rotate90DegreesClockwise(moveRocksNorth(map)); }
		return map;
	}

	private static char[][] moveRocksNorth(char[][] map) {
		for (int x = 0; x < width; x++) {
			boolean move = true;
			while (move) {
				move = false;
				for (int y = 1; y < height; y++) {
					if (map[x][y] == 'O' && map[x][y-1] == '.') {
						map[x][y] = '.';
						map[x][y-1] = 'O';
						move = true;
					}
				}
			}
		}
		return map;
	}
}