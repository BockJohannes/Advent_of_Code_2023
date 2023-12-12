package common;

import java.util.List;
import java.util.stream.Collectors;
import java.util.ArrayList;
import java.math.BigInteger;

public class AdventMath {
	
    public static BigInteger getLCM(List<Integer> numbers) {
        ArrayList<BigInteger> bigIntegers = numbers.stream()
                .map(BigInteger::valueOf)
                .collect(Collectors.toCollection(ArrayList::new));
        return bigIntegers.stream()
                .reduce(BigInteger.ONE, (a, b) -> a.multiply(b).divide(a.gcd(b)));
    }
    
	public static List<Integer> getCalculateDifferences(List<Integer> numbers) {
        List<Integer> differences = new ArrayList<>();
        for (int i = 0; i < numbers.size() - 1; i++) {
            int diff = numbers.get(i + 1) - numbers.get(i);
            differences.add(diff);
        }
        return differences;
    }
}