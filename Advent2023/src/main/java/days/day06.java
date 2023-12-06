package days;

import common.AdventReader;

public class day06 {

	public static class Boat {
		long speed = 0;
		
		public static long charge(long milliseconds) {
			return milliseconds;	
		}
	}
	
	public static long winTheRace(String part, long[] raceTime, long[] raceDistance) {
		
		long[] record = new long[raceTime.length];

		for(int i=0; i<raceTime.length; i++) {
			long countWins = 0;
		    for (long milliseconds = 0; raceTime[i] >= 0; raceTime[i]--, milliseconds++) {
		        if (Boat.charge(milliseconds) * raceTime[i] > raceDistance[i]) {
		            countWins++;
		        }
		    }
			record[i] = countWins;

		}
		long value = part.equals("part1") ? record[0] * record[1] * record[2] * record[3] : record[0];
		return value;
	}
	
	public static void main(String[] args) {
		
		long[] time_part1 = {60, 94, 78, 82};
		long[] distance_part1 = {475, 2138, 1015, 1650};
		long[] time_part2 = {60947882L};
		long[] distance_part2 = {475213810151650L};
		
		long part1 = winTheRace("part1", time_part1, distance_part1);
		long part2 = winTheRace("part2", time_part2, distance_part2);
		AdventReader.printResult(part1, part2);
	}
}