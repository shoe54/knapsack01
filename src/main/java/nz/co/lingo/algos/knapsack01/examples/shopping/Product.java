package nz.co.lingo.algos.knapsack01.examples.shopping;

import nz.co.lingo.algos.knapsack01.Item;

/**
 * Immutable
 * @author Shu
 *
 */
public class Product extends Cuboid implements Item<PriceWeightTuple<Integer>, PriceWeightTuple<Double>> {
	final int id;
	final int price; //cents
	final int weight; //grams
	final double priceToVolumeRatio;
	
	public Product(int id, int price, int length, int width, int height, int weight) {
		super(length, width, height);
		
		if (price < 0)
			throw new IllegalArgumentException("Are you paying people to buy your product?");
		if (weight <= 0)
			throw new IllegalArgumentException("Not physically possible");
			
		this.id = id;
		this.price = price;
		this.weight = weight;
		this.priceToVolumeRatio = (double) price / getVolume();
	}

	public int getId() {
		return id;
	}

	public int getPrice() {
		return price;
	}

	public int getWeight() {
		return weight;
	}

	public double getPriceToVolumeRatio() {
		return priceToVolumeRatio;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Product other = (Product) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", price=" + price + ", weight=" + weight
				+ ", priceToVolumeRatio=" + priceToVolumeRatio
				+ ", getLength()=" + getLength() + ", getWidth()=" + getWidth()
				+ ", getHeight()=" + getHeight() + ", getVolume()="
				+ getVolume() + "]";
	}

	@Override
	public PriceWeightTuple<Integer> getValue() {
		return new PriceWeightTuple<Integer>(getPrice(), getWeight());
	}

	@Override
	public PriceWeightTuple<Integer> getValueZero() {
		return PriceWeightTuple.ZERO;
	}

	@Override
	public PriceWeightTuple<Integer> addValues(PriceWeightTuple<Integer> a, PriceWeightTuple<Integer> b) {
		return a.add(b);
	}

	@Override
	public PriceWeightTuple<Integer> multiplyValue(int multiplier) {
		return getValue().multiply(multiplier);
	}

	@Override
	public PriceWeightTuple<Double> divideByCost(PriceWeightTuple<Integer> top) {
		return top.divide(getCost());
	}

	@Override
	public PriceWeightTuple<Double> valueToDouble(PriceWeightTuple<Integer> v) {
		return (PriceWeightTuple<Double>) v.toDouble();
	}

	@Override
	public int getCost() {
		return getVolume();
	}

	@Override
	public PriceWeightTuple<Double> addDouble(PriceWeightTuple<Double> d) {
		return getValue().addDouble(d);
	}

	@Override
	public PriceWeightTuple<Double> addDoubles(PriceWeightTuple<Double> a,
			PriceWeightTuple<Double> b) {
		return a.add(b);
	}
}
