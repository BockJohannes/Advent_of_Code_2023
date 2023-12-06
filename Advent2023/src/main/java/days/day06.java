package days;

import java.util.Arrays;

import common.AdventReader;

public class day06 {

	public static class Boat {		
		public static long charge(long milliseconds) {
			return milliseconds;	
		}
	}
	
	public static long winTheRace(long[] raceTime, long[] raceDistance) {
		long[] record = new long[raceTime.length];

		for(int i=0; i<raceTime.length; i++) {
		    for (long milliseconds = 0; raceTime[i] >= 0; raceTime[i]--, milliseconds++) {
		        if (Boat.charge(milliseconds) * raceTime[i] > raceDistance[i]) {
		        	record[i] = raceTime[i] - (Boat.charge(milliseconds)-1);
		            break;
		        }
		    }
		}
		return Arrays.stream(record).reduce(1, Math::multiplyExact);
	}
	
	public static void main(String[] args) {
		long[] time_part1 = {60, 94, 78, 82}, 	distance_part1 = {475, 2138, 1015, 1650};
		long[] time_part2 = {60947882L}, 		distance_part2 = {475213810151650L};
		
		long part1 = winTheRace(time_part1, distance_part1);
		long part2 = winTheRace(time_part2, distance_part2);
		AdventReader.printResult(part1, part2);
	}
}