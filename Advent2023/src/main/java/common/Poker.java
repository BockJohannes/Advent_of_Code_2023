package common;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Poker {
	
	private static Map<String, Integer> cardLabel = new HashMap<>() {{
	    put("Z", 14);
	    put("Y", 13);
	    put("X", 12);
	    put("W", 11);
	    put("V", 10);
	    put("9", 9);
	    put("8", 8);
	    put("7", 7);
	    put("6", 6);
	    put("5", 5);
	    put("4", 4);
	    put("3", 3);
	    put("2", 2);
	}};
	
	public static int getcardValue(String string) {
		int value = cardLabel.get(string);
		return value;
	}

	public static String moveJocker(String hand) {
		StringBuilder stringWithoutJ = new StringBuilder();
	    StringBuilder stringWithJ = new StringBuilder();

	    for (char c : hand.toCharArray()) {
	    	if (c != 'W') {
	    		stringWithoutJ.append(c);
	    	} else {
	    		stringWithJ.append(c);
	         	}
	    }

	    return replaceJocker(stringWithoutJ.toString() + stringWithJ.toString());
	   
	}
	
	
	private static String replaceJocker(String oldHand) {
		
		Map<Character, Integer> frequencyMap = new HashMap<>();
		char[] hand = oldHand.toCharArray();
        for (char c : hand) {
            if (frequencyMap.containsKey(c)) {
                frequencyMap.put(c, frequencyMap.get(c) + 1);
            } else {
                frequencyMap.put(c, 1);
            }
        }
        
        char mostFrequentChar = 0;
        int maxFrequency = 0;
        for (Map.Entry<Character, Integer> entry : frequencyMap.entrySet()) {
            if (entry.getValue() > maxFrequency) {
                mostFrequentChar = entry.getKey();
                maxFrequency = entry.getValue();
            }
        }

        for (int i = 0; i < hand.length; i++) {
            if (hand[i] == 'W') {
                hand[i] = mostFrequentChar;
            }
        }
        return String.valueOf(hand);
	}
	
	public static String evaluateHand(char[] hand) {
        if (isFiveOfAKind(hand)) {
            return "Five of a kind";
        } else if (isFourOfAKind(hand)) {
            return "Four of a kind";
        } else if (isFullHouse(hand)) {
            return "Full house";
        } else if (isThreeOfAKind(hand)) {
            return "Three of a kind";
        } else if (isTwoPair(hand)) {
            return "Two pair";
        } else if (isOnePair(hand)) {
            return "One pair";
        } else {
            return "High card";
        }
    }

	private static boolean isFiveOfAKind(char[] hand) {
	    Arrays.sort(hand);
	    return hand[0] == hand[4];
	}

	private static boolean isFourOfAKind(char[] hand) {
	    Arrays.sort(hand);
	    return hand[0] == hand[3] || hand[1] == hand[4];
	}

	private static boolean isFullHouse(char[] hand) {
	    Arrays.sort(hand);
	    return (hand[0] == hand[1] && hand[2] == hand[4]) || (hand[0] == hand[2] && hand[3] == hand[4]);
	}

	private static boolean isThreeOfAKind(char[] hand) {
	    Arrays.sort(hand);
	    return (hand[0] == hand[2]) || (hand[1] == hand[3]) || (hand[2] == hand[4]);
	}

	private static boolean isTwoPair(char[] hand) {
	    Arrays.sort(hand);
	    return (hand[0] == hand[1] && hand[2] == hand[3]) || (hand[0] == hand[1] && hand[3] == hand[4]) || (hand[1] == hand[2] && hand[3] == hand[4]);
	}

	private static boolean isOnePair(char[] hand) {
	    Arrays.sort(hand);
	    for (int i = 0; i < hand.length - 1; i++) {
	        if (hand[i] == hand[i + 1]) {
	            return true;
	        }
	    }
	    return false;
	}
	
}
