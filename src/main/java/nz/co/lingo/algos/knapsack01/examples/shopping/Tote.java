package nz.co.lingo.algos.knapsack01.examples.shopping;

import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import nz.co.lingo.algos.knapsack01.Pool;
import nz.co.lingo.algos.knapsack01.examples.shopping.App.PriceToVolumeRatioComparator;

public class Tote extends Cuboid implements Pool<Product> {
	Set<Product> products = new HashSet<>();
	
	public Tote(int length, int width, int height) {
		super(length, width, height);
	}

	@Override
	public int getAllowedCost() {
		return getVolume();
	}
	
	int getOccupiedVolume() {
		return products.stream().collect(Collectors.summingInt(Product::getVolume));
	}

	public int getTotalPrice() {
		return products.stream().collect(Collectors.summingInt(Product::getPrice));
	}

	public Stream<Product> getProducts() {
		return products.stream();
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

	/**
	 * Attempts to add product to the tote. Throws IllegalStateException if product with the same id
	 * already exists in the tote
	 * 
	 * @param i
	 * @return false if product could not be accommodated and was not added
	 */
	@Override
	public boolean addItem(Product p) {
		if (p == null) throw new IllegalArgumentException("Missing product");
		
		if (!canAccommodateItem(p))
			return false;
		
		if (!products.add(p))
			throw new IllegalStateException("Adding a product id that already exists!");
		
		return true;
	}

	@Override
	public Comparator<Product> getValueToCostRatioComparator() {
		return new PriceToVolumeRatioComparator();
	}

	@Override
	public int getUsedCost() {
		return getOccupiedVolume();
	}

	@Override
	public Stream<Product> getItems() {
		return getProducts();
	}
	
}
