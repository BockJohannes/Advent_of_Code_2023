package days;

import java.util.*;

import common.AdventReader;
import common.day15.*;

public class day15 {

	public static void main(String[] args) {
		String line = AdventReader.readAsString("15");
		List<String> partsOfLine = Arrays.asList(line.split(","));
		
		int part1 = partsOfLine.stream().mapToInt(day15::getHash).sum();
		
		
		Box[] boxes = new Box[256];
		for (int i = 0; i < 256; i++) boxes[i] = new Box(i);
		
		for (String step : partsOfLine) {
			int index = Math.max(step.indexOf("-"), step.indexOf("="));
			String label = step.substring(0, index);
			int boxIndex = getHash(label);
			char operation = step.charAt(index);
			
			if (operation == '=') {
				int focalLength = Integer.parseInt(step.substring(index+1));
				Lens lens = new Lens(label, focalLength);
				boxes[boxIndex].putLens(lens);
			}
			else {
				boxes[boxIndex].removeLensWithLabel(label);
			}
		}
		
		int part2 = Arrays.stream(boxes).mapToInt(Box::getFocusingPower).sum();

		AdventReader.printResult(part1, part2);
		
	}
	
	public static int getHash(String part) {
	    return part.chars().reduce(0, (hash, c) -> (hash + c) * 17 % 256);
	}		
}