package days;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import common.AdventReader;

public class day09 {
	
    public static int getDifferences(List<Integer> sequenz, boolean part1) {
    	if (new HashSet<>(sequenz).size() == 1) return sequenz.get(0);
        List<Integer> nextSequence = new ArrayList<>();
        for (int i = 0; i < sequenz.size() - 1; i++) {
            nextSequence.add(sequenz.get(i + 1) - sequenz.get(i));
        }
        int nextNumber = getDifferences(nextSequence, part1);
        return part1 ? sequenz.get(sequenz.size() - 1) + nextNumber : sequenz.get(0) - nextNumber;
    }

    public static int getNext(List<List<Integer>> inputs, boolean part1) {
        int sum = 0;
        for (List<Integer> sequenz : inputs) {
            sum += getDifferences(sequenz, part1);
        }
        return sum;
    }

    public static void main(String[] args) {
    	
		List<String> lines = AdventReader.read("09");
		List<List<Integer>> inputs = new ArrayList<>();

        for (String line : lines) {
            String[] numbers = line.split(" ");
            List<Integer> intList = new ArrayList<>();
            for (String number : numbers) {
                intList.add(Integer.parseInt(number));
            }
            inputs.add(intList);
        }  
        int part1 = getNext(inputs, true);
        int part2 = getNext(inputs, false);
        AdventReader.printResult(part1, part2); 
	}
}