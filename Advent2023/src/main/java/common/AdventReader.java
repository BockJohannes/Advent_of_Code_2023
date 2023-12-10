package common;

import lombok.SneakyThrows;

import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class AdventReader {
	
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
    
    // ------------------------------------------------------
    // ---------- Work with Two-Dimensional Arrays ----------
    // ------------------------------------------------------
    
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
    
    // -------------------------------------------
    // ---------- Output of the results ----------
    // -------------------------------------------
    
    public static void printResult(int part1, int part2) {
		System.out.println("Part 1 = " + part1);
		System.out.println("Part 2 = " + part2);	
    }
    
    public static void printResult(long part1, long part2) {
		System.out.println("Part 1 = " + part1);
		System.out.println("Part 2 = " + part2);	
    }
}