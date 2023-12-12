package common;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Poker {
	
	public static String moveJocker(String hand) {
		StringBuilder stringWithoutW = new StringBuilder();
	    StringBuilder stringWithW = new StringBuilder();

	    for (char c : hand.toCharArray()) {
	    	if (c != 'W') {stringWithoutW.append(c);} 
	    	else {stringWithW.append(c);}
	    }
	    System.out.println(stringWithoutW + "   " + stringWithW);
	    return replaceJocker(stringWithoutW.toString() + stringWithW.toString());  
	}
		
	public static String replaceJocker(String text) {
	        Map<Character, Integer> charFrequency = new HashMap<>();
	        for (char c : text.toCharArray()) {
	            if (c != '-') {
	                charFrequency.put(c, charFrequency.getOrDefault(c, 0) + 1);
	            }
	        }
	        char mostCommonChar = 'Z';
	        int maxFrequency = 0;
	        for (Map.Entry<Character, Integer> entry : charFrequency.entrySet()) {
	            if (entry.getValue() > maxFrequency || (entry.getValue() == maxFrequency && entry.getKey() > mostCommonChar)) {
	                mostCommonChar = entry.getKey();
	                maxFrequency = entry.getValue();
	            }
	        }
	        String newString = text.replace('-', mostCommonChar);
	        return newString;
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
