package days;

import java.util.concurrent.atomic.AtomicInteger;

import common.AdventReader;

public class day02 {
	
	enum Color {
	       RED(12), GREEN(13), BLUE(14);
	       private final int value;

	       Color(int value) {
	           this.value = value;
	       }

	       public int getValue() {
	           return value;
	       }
	   }
	
	public static boolean isGamePossible (String input) {
		
		input = input.substring(input.indexOf(":") + 2).trim();
		String[] sets = input.split(";");
		
		for (String set : sets) {
			String[] colors = set.split(",");
			
			for (String color : colors) {
				int number = 0;
				number = Integer.parseInt(color.replaceAll("\\D+", ""));
		        String farbe = color.replaceAll("[^a-zA-Z]+", "").trim();
		        switch(farbe) {
		        	case "blue":
		        		if (number>Color.BLUE.getValue()) {return false;}
		        		break;
		        	case "red":
		        		if (number>Color.RED.getValue()) {return false;}
		        		break;
		        	case "green":
		        		if (number>Color.GREEN.getValue()) {return false;}
		        		break;
		        }
			}
		}
		
		return true;
	}
		
	public static int getPowerOfCubes(String input) {
		int power = 0;

		input = input.substring(input.indexOf(":") + 2).trim();
		
		int maxGreen = findMaxNumber(input, "green");
        int maxBlue = findMaxNumber(input, "blue");
        int maxRed = findMaxNumber(input, "red");
        
		power = maxGreen * maxBlue * maxRed;
		return power;
	}
	
	public static int findMaxNumber(String input, String color) {
        String[] entries = input.split("; ");
        int maxNumber = Integer.MIN_VALUE;
        for (String entry : entries) {
            String[] parts = entry.split(", ");
            for (String part : parts) {
                if (part.contains(color)) {
                    int number = Integer.parseInt(part.split(" ")[0]);
                    if (number > maxNumber) {
                        maxNumber = number;
                    }
                }
            }
        }
        return maxNumber;
    }
	
	public static void main(String[] args) {

		AtomicInteger gameID = new AtomicInteger(0);
		AtomicInteger part1 = new AtomicInteger(0);
		AtomicInteger part2 = new AtomicInteger(0);
		AdventReader.read("02")
		.forEach(value -> {
			gameID.incrementAndGet();
			if(isGamePossible(value)) {
				part1.getAndAdd(gameID.get());
			}
			part2.getAndAdd(getPowerOfCubes(value));
		});
		AdventReader.printResult(part1.get(), part2.get());
	}

}
