package com.lingo.redmart.totechallenge;

/**
 * Immutable. Represents the running total price and weight
 * 
 * @author Shu
 *
 */
public class PriceWeightTuple implements Comparable<PriceWeightTuple> {
	final int totalPrice; // cents
	final int totalWeight; // grams

	public static final PriceWeightTuple ZERO = new PriceWeightTuple(0, 0);

	public PriceWeightTuple(int totalPrice, int totalWeight) {
		super();
		this.totalPrice = totalPrice;
		this.totalWeight = totalWeight;
	}

	public PriceWeightTuple add(PriceWeightTuple toAdd) {
		return new PriceWeightTuple(
				totalPrice + toAdd.totalPrice, 
				totalWeight	+ toAdd.totalWeight
		);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + totalPrice;
		result = prime * result + totalWeight;
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
		PriceWeightTuple other = (PriceWeightTuple) obj;
		if (totalPrice != other.totalPrice)
			return false;
		if (totalWeight != other.totalWeight)
			return false;
		return true;
	}

	/**
	 * A PriceWeightTuple A is larger than B if A's price is larger than B. If
	 * price is tied, then A is larger if A's weight is lower than B
	 */
	@Override
	public int compareTo(PriceWeightTuple o) {
		int priceDiff = totalPrice - o.totalPrice;
		if (priceDiff != 0)
			return priceDiff;
		// Tied on price. Lower weight has more "value"
		return o.totalWeight - totalWeight;
	}

	public int getTotalPrice() {
		return totalPrice;
	}

	public int getTotalWeight() {
		return totalWeight;
	}

	@Override
	public String toString() {
		return "PriceWeightTuple [totalPrice=" + totalPrice + ", totalWeight="
				+ totalWeight + "]";
	}
}
