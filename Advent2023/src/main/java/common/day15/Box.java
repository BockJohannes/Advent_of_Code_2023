package common.day15;

import java.util.LinkedHashMap;
import java.util.Map;

public class Box {
	
	int number = 0;
	public Map<String, Lens> lenses = new LinkedHashMap<String, Lens>();
	
	public Box(int number) {
		this.number = number;
	}

	public int getFocusingPower() {
		int result = 0;
		int index = 0;
		for (Lens lens : lenses.values()) {
			result += (number+1)*(++index)*lens.focalLength;			
		}
		return result;
	}

	public void putLens(Lens lens) {
		lenses.put(lens.label, lens);
	}

	public void removeLensWithLabel(String label) {
		lenses.remove(label);
	}
}