package com.lingo.redmart.totechallenge;

import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

/**
 * Note: Solution can be generalized. Instead of dealing with Product and Tote
 * it could deal with more abstract forms (perhaps Item and Pool respectively) so
 * that we can use the Solution on other applications of the same problem, e.g.
 * assigning resources to tasks (Item=Task and Resources=Pool)
 * 
 * Other improvements would be to time the maximize method invocation
 * 
 * @author Shu
 *
 */
public abstract class Solution {
	
	/**
	 * Convenience class to determine if Product should be allowed in Tote based
	 * solely on whether the Product fits into the empty Tote by volume
	 */
	public static class DoesProductFitIntoEmptyTote implements Predicate<Product> {
		private Tote tote;

		public DoesProductFitIntoEmptyTote(Tote tote) {
			super();
			this.tote = tote;
		}

		@Override
		public boolean test(Product p) {
			return p.getVolume() <= tote.getVolume();
		}
	}

	/**
	 * Product with a higher price to volume ratio is higher by comparison, i.e.
	 * a sort will place these higher ratio items last. On a price tie, higher volume
	 * items are higher in the comparison. On a price and volume tie, lower weight
	 * is higher in the comparison 
	 */
	public static class PriceToVolumeRatioComparator implements Comparator<Product> {
		@Override
		public int compare(Product o1, Product o2) {
			if (o1.getPriceToVolumeRatio() < o2.getPriceToVolumeRatio())
				return -1;
			else if (o1.getPriceToVolumeRatio() > o2.getPriceToVolumeRatio())
				return 1;
			else if (o2.getVolume() != o1.getVolume())
				return o1.getVolume() - o2.getVolume();
			else
				return o2.getWeight() - o1.getWeight();
		}
	}
	
	/**
	 * Note: Can be generalized for not just shopping challenges
	 * @param products
	 * @param tote
	 */
	public abstract void maximizeMyShopping(List<Product> products, Tote tote);
}
