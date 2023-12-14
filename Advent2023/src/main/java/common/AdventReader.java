package common;

import lombok.SneakyThrows;

import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.ArrayList;

public class AdventReader {
	
    // ------------------------------------
    // ---------- read .txt file ----------
    // ------------------------------------
	
    @SneakyThrows
    public static List<String> read(String fileName) {
    	try {
    		URL url = AdventReader.class.getClassLoader().getResource(String.format("input/%s.txt", fileName));
    		return Files.lines(Paths.get(url.toURI())).collect(Collectors.toList());
    	} catch (Exception e) {
    		e.printStackTrace();
    		return null;
    	}
    }
    
    // -----------------------------------------
    // ----------  generic Pair class ----------
    // -----------------------------------------
    
	public static class Pair<K, V> {
	    private K key;
	    private V value;

	    public Pair(K key, V value) {
	        this.key = key;
	        this.value = value;
	    }

	    public K getKey() {
	        return key;
	    }

	    public V getValue() {
	        return value;
	    }
	}
	
    // -----------------------------------------------------------
    // ---------- Work with Two-Dimensional char Arrays ----------
    // -----------------------------------------------------------
    
    public static char[][] parseSchematic(List<String> lines) {
        char[][] schematic = new char[lines.size()][lines.get(0).length()];
        for (int i = 0; i < lines.size(); i++) {
            schematic[i] = lines.get(i).toCharArray();
        }
        return schematic;
    }

    public static int[] getPositionElement(char[][] schematic, char element) {
        for (int i = 0; i < schematic.length; i++) {
            for (int j = 0; j < schematic[i].length; j++) {
            	if (schematic[i][j] == element) {
                    return new int[]{i, j};
                }
            }
        }
        return new int[]{-1, -1}; 
    }
    
    public static int[][] getAllPositionsOfElement(char[][] schematic, char element) {
        ArrayList<int[]> positions = new ArrayList<>();

        for (int i = 0; i < schematic.length; i++) {
            for (int j = 0; j < schematic[i].length; j++) {
                if (schematic[i][j] == element) {
                    positions.add(new int[]{i, j});
                }
            }
        }

        if (positions.isEmpty()) {
            return new int[][]{{-1, -1}};
        } else {
            int[][] result = new int[positions.size()][2];
            for (int k = 0; k < positions.size(); k++) {
                result[k] = positions.get(k);
            }
            return result;
        }
    }
    
    public static char[] findNeighbors(char[][] schematic, int[] postition) {
    	int i = postition[0];
    	int j = postition[1];
        char[] neighbors = new char[4];
        neighbors[0] = (j < schematic[i].length - 1) ? schematic[i][j + 1] : ' '; // right
        neighbors[1] = (i < schematic.length - 1) ? schematic[i + 1][j] : ' '; // bottom
        neighbors[2] = (j > 0) ? schematic[i][j - 1] : ' '; // left 
        neighbors[3] = (i > 0) ? schematic[i - 1][j] : ' '; // top 
        return neighbors;
    }
    
    public static void printGrid (char[][] schematic) {
		for (int y = 0; y < schematic.length; y++) {
			for (int x = 0; x < schematic[1].length; x++) {
				System.out.print(schematic[y][x]);
			}
			System.out.println();
		}
    }
    
    // -------------------------------------------
    // ---------- Output of the results ----------
    // -------------------------------------------
    
    public static <T, U> void printResult(T part1, U part2) {
        part1 = part1 != null ? part1 : (T) (String) "---";
        part2 = part2 != null ? part2 : (U) (String) "---";;
        System.out.println("Part 1 = " + part1);
        System.out.println("Part 2 = " + part2);
    }
}