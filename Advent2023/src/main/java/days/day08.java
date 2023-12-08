package days;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;

import common.AdventMath;
import common.AdventReader;

public class day08 {
	
	public static int getSteps(char[] leftRight, Map<String, String[]> network, String start, ArrayList<String> end) {
		
		int steps = 0;
		int i = 0;
		while(i<leftRight.length) {
			steps++;
			String[] directions = network.get(start);
			start = (leftRight[i] == 'R') ? directions[1] : directions[0];
			for (String value : end) {
			    if(value.equals(start)) {
			        return steps;
			    }
		    }
			i++;
			i = (i == leftRight.length) ? 0 : i;
		}
		return -1;	
	}
	
	public static void main(String[] args) {
		
		List<String> inputListe = AdventReader.read("08");
		
		char[] leftRight = inputListe.get(0).toCharArray();
		inputListe.subList(0, 2).clear();
		
		Map<String, String[]> network = new HashMap<>();
		for (String line : inputListe) {
			String place = line.substring(0, 3);
			String[] directions = line.substring(line.indexOf("(") + 1, line.indexOf(")")).split(", ");
			network.put(place, directions);
		}
		
		//Part 1
		ArrayList<String> end = new ArrayList<>(Arrays.asList("ZZZ"));
		int stepsPart1 = getSteps(leftRight, network, "AAA", end);
				
		//Part 2
		ArrayList<String> endA = new ArrayList<>();
		ArrayList<String> endZ = new ArrayList<>();
		
		for (String key : network.keySet()) {
		    String substring = key.substring(2);
		    if (substring.equals("A")) {
		        endA.add(key);
		    }
		    if (substring.equals("Z")) {
		        endZ.add(key);
		    }
		}
		
		List<Integer> part2 = new ArrayList<>();
		for (String start : endA) {
			part2.add(getSteps(leftRight, network, start, endZ));
		}
		BigInteger stepsPart2 = AdventMath.getLCM(part2);
		
		AdventReader.printResult(stepsPart1, stepsPart2.longValue());
	}
}