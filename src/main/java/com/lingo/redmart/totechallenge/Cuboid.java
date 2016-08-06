package com.lingo.redmart.totechallenge;

/**
 * Immutable
 * @author Shu
 *
 */
public class Cuboid {
	final int length, width, height; //cm
	final int volume; //cm3

	public Cuboid(int length, int width, int height) {
		if (length <= 0 || width <= 0 || height <= 0)
			throw new IllegalArgumentException("Not physically possible");
		this.length = length;
		this.width = width;
		this.height = height;
		this.volume = length * width * height;
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
