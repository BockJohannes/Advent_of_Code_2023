package days;

import common.AdventReader;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

public class day04 {

	public static int[][] splitAndConvert(String input) {
	    String[] parts = input.split("\\|");
	    return new int[][]{convertStringArray(parts[0]), convertStringArray(parts[1])};
	}

	private static int[] convertStringArray(String input) {
	    String[] values = input.trim().split("\\s+");
	    int[] array = new int[values.length];
	    for (int i = 0; i < values.length; i++) {
	        array[i] = Integer.parseInt(values[i]);
	    }
	    return array;
	}
        
    public static int getPoints(String input, String part) {
    	int[][] result = splitAndConvert(input);
    	int sum = countIntersections(result[0], result[1], part);
    	return sum;
    }

    public static int countIntersections(int[] arr1, int[] arr2, String part) {
        int count = 0;
        for (int i = 0; i < arr1.length; i++) {
            for (int j = 0; j < arr2.length; j++) {
            	count = (arr1[i] == arr2[j] && "part 1".equals(part)) ? (count == 0 ? 1 : count * 2) : count;
            	count += (arr1[i] == arr2[j] && "part 2".equals(part)) ? 1 : 0;
            }
        }
        return count;
    }
    
	public static void main(String[] args) {
		
		//-----Part 1-----
		AtomicInteger part1 = new AtomicInteger(0);
		AdventReader.read("04")
		.forEach(value -> {
			String input = value.substring(value.indexOf(":") + 2).trim();
			part1.getAndAdd(getPoints(input, "part 1"));
		});
		
		//-----Part 2-----
        List<String> inputListe = AdventReader.read("04");

        int[][] scratchcards = new int[inputListe.size() + 1][2];
        
        IntStream.range(0, scratchcards.length).forEach(i -> {
            scratchcards[i][0] = i + 1;
            scratchcards[i][1] = 1;
        });
        
        for (int i=0; i<inputListe.size(); i++){
        	String input = inputListe.get(i).substring(inputListe.get(i).indexOf(":") + 2).trim();
        	int[][] card = splitAndConvert(input);        	
        	int count = countIntersections(card[0], card[1], "part 2");
       
        	int next = i + 1;
        	int x = scratchcards[next][1];

        	while (count-- > 0) {
        	    scratchcards[++next][1] += x;
        	}
        	
        }
		int part2 = 0;
        for (int i = 1; i <= inputListe.size(); i++) {
        	part2 += scratchcards[i][1];
        }
        AdventReader.printResult(part1.get(), part2);
	}
}