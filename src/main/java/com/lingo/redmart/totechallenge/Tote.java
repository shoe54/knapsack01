package com.lingo.redmart.totechallenge;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class Tote extends Cuboid {
	Set<Product> products = new HashSet<>();
	
	public Tote(int length, int width, int height) {
		super(length, width, height);
	}

	public boolean canAccommodateProduct(Product p) {
		if (p == null) throw new IllegalArgumentException("Missing product");

		return getVolume() >= getOccupiedVolume() + p.getVolume();
	}
	
	int getOccupiedVolume() {
		return products.stream().collect(Collectors.summingInt(Product::getVolume));
	}

	public int getTotalPrice() {
		return products.stream().collect(Collectors.summingInt(Product::getPrice));
	}

	/**
	 * Attempts to add product to the tote. Throws IllegalStateException if product with the same id
	 * already exists in the tote
	 * 
	 * @param p
	 * @return false if product could not be accommodated and was not added
	 */
	public boolean addProduct(Product p) {
		if (p == null) throw new IllegalArgumentException("Missing product");
		
		if (!canAccommodateProduct(p))
			return false;
		
		if (!products.add(p))
			throw new IllegalStateException("Adding a product id that already exists!");
		
		return true;
	}
	
	public Set<Product> getProducts() {
		return products;
	}

	public boolean isFull() {
		return getOccupiedVolume() >= getVolume();
	}

	@Override
	public String toString() {
		return "Tote [products=" + products + ", getLength()=" + getLength()
				+ ", getWidth()=" + getWidth() + ", getHeight()=" + getHeight()
				+ ", getVolume()=" + getVolume() + "]";
	}

	public void removeAllProducts() {
		products.clear();
	}
	
	
}
