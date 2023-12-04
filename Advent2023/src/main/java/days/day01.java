package days;

import common.AdventReader;
import java.util.concurrent.atomic.AtomicInteger;

public class day01 {

	public static int combineFirstAndLastDigit(String input) {
		input = input.replaceAll("[a-z]", "");
		String combined = String.valueOf(input.charAt(0)) + input.charAt(input.length() - 1);
		return Integer.parseInt(combined);
	}
	
	
	//Part 2 Nicht schön aber funktioniert 
    public static String replaceWords(String input) {
        input = input.replaceAll("one", "o1e");
        input = input.replaceAll("two", "t2o");
        input = input.replaceAll("three", "t3e");
        input = input.replaceAll("four", "f4r");
        input = input.replaceAll("five", "f5e");
        input = input.replaceAll("six", "s6x");
        input = input.replaceAll("seven", "s7n");
        input = input.replaceAll("eight", "e8t");
        input = input.replaceAll("nine", "n9e");
        return input;
    }

    
	public static void main(String[] args) {
		
		AtomicInteger sum = new AtomicInteger(0);
		AtomicInteger sum2 = new AtomicInteger(0);
		AdventReader.read("01")
			.forEach(value -> {
				sum.addAndGet(combineFirstAndLastDigit(value));
				sum2.addAndGet(combineFirstAndLastDigit(replaceWords(value)));
			});
		AdventReader.printResult(sum.get(), sum2.get());	
	}

}
