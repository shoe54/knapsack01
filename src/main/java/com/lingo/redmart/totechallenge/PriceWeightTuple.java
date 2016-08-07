package com.lingo.redmart.totechallenge;

/**
 * Immutable. Represents the "value" of a product which is encompassed by price and weight. 
 * Higher price is higher value, and on a tie lower weight is higher value
 * 
 * T is the Number data type that the price and weight are stored in, e.g. Integer or Double 
 * 
 * @author Shu
 *
 */
public class PriceWeightTuple<T extends Number> implements Comparable<PriceWeightTuple<? extends Number>> {
	final T price; // cents
	final T weight; // grams

	public static final PriceWeightTuple ZERO = new PriceWeightTuple(0, 0);

	public PriceWeightTuple(T price, T weight) {
		super();
		this.price = price;
		this.weight = weight;
	}

	public PriceWeightTuple<T> add(PriceWeightTuple<T> toAdd) {
		// Note that it is safe to use doubleValue() because whole numbers between -2 pow 53
		// to 2 pow 53 can be exactly represented by doubles
		return new PriceWeightTuple(
				this.price.doubleValue() + toAdd.price.doubleValue(), 
				this.weight.doubleValue() + toAdd.weight.doubleValue()
		);
	}

	public PriceWeightTuple<T> add(T price, T weight) {
		// Note that it is safe to use doubleValue() because whole numbers between -2 pow 53
		// to 2 pow 53 can be exactly represented by doubles
		return new PriceWeightTuple(
				this.price.doubleValue() + price.doubleValue(), 
				this.weight.doubleValue() + weight.doubleValue()
		);
	}

	/*@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + price;
		result = prime * result + weight;
		return result;
	}*/

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(price.doubleValue());
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(weight.doubleValue());
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	/*@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PriceWeightTuple other = (PriceWeightTuple) obj;
		if (price != other.price)
			return false;
		if (weight != other.weight)
			return false;
		return true;
	}*/

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PriceWeightTuple<T> other = (PriceWeightTuple<T>) obj;
		if (Double.doubleToLongBits(price.doubleValue()) != Double.doubleToLongBits(other.price.doubleValue()))
			return false;
		if (Double.doubleToLongBits(weight.doubleValue()) != Double.doubleToLongBits(other.weight.doubleValue()))
			return false;
		return true;
	}

	
	/**
	 * A PriceWeightTuple A is larger than B if A's price is larger than B. If
	 * price is tied, then A is larger if A's weight is lower than B
	 */
	@Override
	public int compareTo(PriceWeightTuple<? extends Number> o) {
		// Note that it is safe to use doubleValue() because whole numbers between -2 pow 53
		// to 2 pow 53 can be exactly represented by doubles
		if (price.doubleValue() > o.price.doubleValue()) return 1;
		if (price.doubleValue() < o.price.doubleValue()) return -1;
		// Tied on price. Lower weight has more "value"
		if (o.weight.doubleValue() > weight.doubleValue()) return 1;
		if (o.weight.doubleValue() < weight.doubleValue()) return -1;
		return 0;
	}

	public T getPrice() {
		return price;
	}

	public T getWeight() {
		return weight;
	}

	public PriceWeightTuple<Double> toDouble() {
		return new PriceWeightTuple<Double>(this.price.doubleValue(), this.weight.doubleValue());
	}

	@Override
	public String toString() {
		return "PriceWeightTuple [price=" + price + ", weight="
				+ weight + "]";
	}
}
