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
}