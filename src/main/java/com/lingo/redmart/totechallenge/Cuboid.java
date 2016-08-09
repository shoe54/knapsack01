package com.lingo.redmart.totechallenge;

import java.util.Arrays;

/**
 * Immutable
 * @author Shu
 *
 */
public class Cuboid {
	final int length, width, height; //cm
	final int volume; //cm3
	/** dimensions of cuboid sorted in ascending order. */
	final int[] sortedDimensions; 

	public Cuboid(int length, int width, int height) {
		if (length <= 0 || width <= 0 || height <= 0)
			throw new IllegalArgumentException("Not physically possible");
		this.length = length;
		this.width = width;
		this.height = height;
		this.volume = length * width * height;
		// Create an array of the item's dimensions sorted in ascending order
		this.sortedDimensions = new int[] { length, width, height };
		Arrays.sort(this.sortedDimensions);
	}

	/**
	 * Can this cuboid fit into other based on the dimensions
	 * @param other
	 * @return
	 */
	public boolean canFitInto(Cuboid other) {
		for (int i = 0; i < sortedDimensions.length; i++) {
			if (sortedDimensions[i] > other.sortedDimensions[i]) {
				return false;
			}
		}
		return true;
	}
	
	public int getLength() {
		return length;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public int getVolume() {
		return volume;
	}
}
