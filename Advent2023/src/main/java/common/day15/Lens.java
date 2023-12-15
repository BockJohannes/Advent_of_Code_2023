package common.day15;

import java.util.Objects;

public class Lens {

	public String label;
	public int focalLength;

	public Lens(String label, int focalLength) {
		this.label = label;
		this.focalLength = focalLength;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null || !(obj instanceof Lens)) return false;
		return Objects.equals(label, ((Lens)obj).label);
	}
}