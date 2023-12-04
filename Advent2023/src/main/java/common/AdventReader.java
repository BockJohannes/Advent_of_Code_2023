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
    
    public static char[][] parseSchematic(List<String> lines) {
        char[][] schematic = new char[lines.size()][lines.get(0).length()];
        for (int i = 0; i < lines.size(); i++) {
            schematic[i] = lines.get(i).toCharArray();
        }
        return schematic;
    }
    
    public static void printResult(int part1, int part2) {
		System.out.println("Part 1 = " + part1);
		System.out.println("Part 2 = " + part2);	
    }
}