package days;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;

import common.AdventReader;

public class day08 {
	
	public static int getSteps(char[] leftRight, Map<String, String[]> network, String start, String end) {
		
		int steps = 0;
		int i = 0;
		while(i<leftRight.length) {
			steps++;
			String[] directions = network.get(start);
			start = (leftRight[i] == '1') ? directions[1] : directions[0];
			if(start.equals(end)) return steps;
			i++;
			i = (i == leftRight.length) ? 0 : i;
		}
		return -1;	
	}
	
	public static long getStepsPart2(char[] leftRight, Map<String, String[]> network, ArrayList<String> start,ArrayList<String> end) {
		
		long steps = 0;
		int i = 0;
		while(i<leftRight.length) {
			int count = 0;
			steps++;
			for (int j = 0; j < start.size(); j++) {
				String s = start.get(j);
			    String[] directions = network.get(s);
			    start.set(j, (leftRight[i] == '1') ? directions[1] : directions[0]);
			    for (String str : end) {
				    if(str.equals(s)) {
				        count++;
				    }
			    }
			}
			i++;
			i = (i == leftRight.length) ? 0 : i;
			if(count == start.size()) return steps;
		}
		return -1;	
	}
	
	public static void main(String[] args) {
		
		List<String> inputListe = AdventReader.read("08");
		
		char[] leftRight = inputListe.get(0).replaceAll("L", "0").replaceAll("R", "1").toCharArray();
		inputListe.subList(0, 2).clear();
		
		Map<String, String[]> network = new HashMap<>();
		for (String line : inputListe) {
			String place = line.substring(0, 3);
			String[] directions = line.substring(line.indexOf("(") + 1, line.indexOf(")")).split(", ");
			network.put(place, directions);
		}
		
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
		
		int stepsPart1 = getSteps(leftRight, network, "AAA", "ZZZ");
		long stepsPart2 = getStepsPart2(leftRight, network, endA, endZ);

		AdventReader.printResult(stepsPart1, stepsPart2);
	}
}