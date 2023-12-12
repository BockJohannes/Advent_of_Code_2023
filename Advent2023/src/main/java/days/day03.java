package days;

import common.AdventReader;

public class day03 {
	
	public static int getPair(char[][] schematic, int c, int r, int length) {

		int number = 1;
		int sum=0;
		int counter=0;
		
	    if (r + 1 < length && Character.isDigit(schematic[c][r + 1])){
	    	sum = buildNumber(schematic, c, r+1, length);
	    	number = (sum > 0) ? number * sum : number;
	    	counter++;
	    }
	    
	    if (r - 1 >= 0 && Character.isDigit(schematic[c][r - 1])){
	    	sum = buildNumber(schematic, c, r-1, length);
	    	number = (sum > 0) ? number * sum : number;
	    	counter++;
	    }
	    
	    if (c - 1 >= 0 && r - 1 >= 0 && Character.isDigit(schematic[c-1][r-1])){
	    	sum = buildNumber(schematic, c-1, r-1, length);
	    	number = (sum > 0) ? number * sum : number;
	    	counter++;
	    } else if(c - 1 >= 0 && Character.isDigit(schematic[c-1][r])){
	    	sum = buildNumber(schematic, c-1, r, length);
	    	number = (sum > 0) ? number * sum : number;
	    	counter++;
	    }else if(c - 1 >= 0 && r + 1 < length && Character.isDigit(schematic[c-1][r+1])){
	    	sum = buildNumber(schematic, c-1, r+1, length);
	    	number = (sum > 0) ? number * sum : number;
	    	counter++;
	    }
	    
	    if (c + 1 < length && r - 1 >= 0 && Character.isDigit(schematic[c+1][r-1])) {
	    	sum = buildNumber(schematic, c+1, r-1, length);
	    	number = (sum > 0) ? number * sum : number;
	    	counter++;
	    }else if (c + 1 < length && Character.isDigit(schematic[c+1][r])) {
	    	sum = buildNumber(schematic, c+1, r, length);
	    	number = (sum > 0) ? number * sum : number;
	    	counter++;
	    }else if(c + 1 < length && r + 1 < length && Character.isDigit(schematic[c+1][r+1])) {
	    	sum = buildNumber(schematic, c+1, r+1, length);
	    	number = (sum > 0) ? number * sum : number;
	    	counter++;
	    }
		
	    schematic[c][r]='.'; 
	    if (counter>1) {
	    	return number;
	    }
		return 0;
	}
	
	public static int buildNumber(char[][] schematic, int c, int r, int length) {
		
		String number = "";
		int rr = r;
		
		while (r < length && Character.isDigit(schematic[c][r])) {
			number = number + schematic[c][r];
			r++;
		}
		
		while (rr > 0 && Character.isDigit(schematic[c][rr-1])) {
			rr--;
			number = schematic[c][rr] + number;
		}	
		return Integer.parseInt(number);	

	}

	public static int getNumber(char[][] schematic, int c, int r, int length) {
		
		String number = "";
		boolean neighbor = false;
		while (c < length && r < length && Character.isDigit(schematic[c][r])) {
			if (isNeighborSymbol(schematic, c, r, length) == true) {
				neighbor = true;
			}
			number = number + schematic[c][r];
			r++;
		}		
		return neighbor ? Integer.parseInt(number) : 0;		
	}
	
	public static boolean isNeighborSymbol(char[][] schematic, int c, int r, int length) {

	    int[][] directions = {{0, 1}, {1, 1}, {1, 0}, {1, -1}, {0, -1}, {-1, -1}, {-1, 0}, {-1, 1}};

	    for (int[] direction : directions) {
	        int newRow = c + direction[0];
	        int newCol = r + direction[1];
	        
	        if (newRow >= 0 && newRow < length && newCol >= 0 && newCol < length
	                && schematic[newRow][newCol] != '.' && !Character.isDigit(schematic[newRow][newCol])) {
	            return true;
	        }
	    }
	    return false;
	}
	
	public static void main(String[] args) {
		
		char[][] schematic = AdventReader.parseSchematic(AdventReader.read("03"));
		
		int part1 = 0;
		int part2 = 0;
		int sum = 0;
		int length = schematic.length;

		//Part 1
		for (int column = 0; column <= schematic[0].length; column++) {
	        for (int row = 0; row <= schematic.length; row++) {
	        			        	
	        	sum = getNumber(schematic, column, row, length);
	        	if(sum > 0) {
	        		row += (int) (Math.log10(sum) + 1);
	        	}
	        	part1 += sum;  
	        }
	    }
		
		//Part 2
		for (int column = 0; column < schematic[0].length; column++) {
	        for (int row = 0; row < schematic.length; row++) {
	        			        	
	        	if(schematic[column][row]=='*') {
	        		part2 += getPair(schematic, column, row, length);
	        	}
	        }
	    }
		AdventReader.printResult(part1, part2);
	}

}