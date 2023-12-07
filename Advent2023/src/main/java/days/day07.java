package days;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import common.AdventReader;
import common.Poker;

public class day07 {
	
	public static int[] sortHandAndGetValue(int[] part, Map<String, Integer> sourceMap) {
		
		Map<String, Integer> sortedMap = new TreeMap<String, Integer>(sourceMap);
		
		for (Map.Entry<String, Integer> entry : sortedMap.entrySet()) {
			System.out.println("hand = " + entry.getKey() + ", bid  = " + entry.getValue());
			part[1] += (entry.getValue() * part[0]);
			part[0]++;
		}
		return part;
	}
		
	public static void main(String[] args) {
			
		List<String> lines = AdventReader.read("07");
		Map<String, Integer> inputMap = new HashMap<>();
		
		lines.stream()
		    .map(line -> line.replaceAll("A", "Z")
		                    .replaceAll("K", "Y")
		                    .replaceAll("Q", "X")
		                    .replaceAll("J", "W")
		                    .replaceAll("T", "V")
		                    .split(" "))
		    .forEach(parts -> inputMap.put(parts[0], Integer.parseInt(parts[1])));
		
		Map<String, Integer> fiveOfAKind = new HashMap<>();
		Map<String, Integer> fourOfAKind = new HashMap<>();
		Map<String, Integer> fullHouse = new HashMap<>();
		Map<String, Integer> threeOfAKind = new HashMap<>();
		Map<String, Integer> twoPair = new HashMap<>();
		Map<String, Integer> onePair = new HashMap<>();
		Map<String, Integer> highCard = new HashMap<>();
		
		for (Map.Entry<String, Integer> entry : inputMap.entrySet()) {
		    String hand = Poker.evaluateHand(entry.getKey().toCharArray());
		    switch(hand){
	        	case "Five of a kind":
	        		fiveOfAKind.put(entry.getKey(),entry.getValue());
	        		break;
	        	case "Four of a kind":
	        		fourOfAKind.put(entry.getKey(),entry.getValue());
	        		break;
	        	case "Full house":
	        		fullHouse.put(entry.getKey(),entry.getValue());
	        		break;
	        	case "Three of a kind":
	        		threeOfAKind.put(entry.getKey(),entry.getValue());
	        		break;
	        	case "Two pair":
	        		twoPair.put(entry.getKey(),entry.getValue());
	        		break;
	        	case "One pair":
	        		onePair.put(entry.getKey(),entry.getValue());
	        		break;
	        	case "High card":
	        		highCard.put(entry.getKey(),entry.getValue());
	        		break;
		    }
		}
		
		int[] part1 = {1, 0}; //{multiplier, value}
		sortHandAndGetValue(part1, highCard);
		sortHandAndGetValue(part1, onePair);
		sortHandAndGetValue(part1, twoPair);
		sortHandAndGetValue(part1, threeOfAKind);
		sortHandAndGetValue(part1, fullHouse);
		sortHandAndGetValue(part1, fourOfAKind);
		sortHandAndGetValue(part1, fiveOfAKind);
		
		AdventReader.printResult(part1[1], 0);		
	}
}
