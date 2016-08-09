package com.lingo.redmart.totechallenge;

import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

/**
 * Note: Solver can be further generalized. Instead of dealing with Product and Tote
 * it could deal with more abstract forms (perhaps Item and Pool respectively) so
 * that we can use the Solver on other applications of the same problem, e.g.
 * assigning resources to tasks (Item=Task and Resources=Pool)
 * 
 * @author Shu
 *
 */
public abstract class Solver {
	
	/**
	 * Convenience class to determine if Product should be allowed in an empty Tote 
	 * based on whether the Product fits into the empty Tote either by volume only, or by
	 * dimensions (which implies a fit by volume as well).
	 */
	public static class DoesProductFitIntoEmptyTote implements Predicate<Product> {
		private Tote tote;
		/** true: check for fit using dimensions, false: check for fit by volume only */
		private boolean testByDimensions;

		public DoesProductFitIntoEmptyTote(Tote tote) {
			this(tote, true);
		}

		public DoesProductFitIntoEmptyTote(Tote tote, boolean testByDimensions) {
			super();
			this.tote = tote;
			this.testByDimensions = testByDimensions;
		}

		@Override
		public boolean test(Product p) {
			if (testByDimensions)
				return p.canFitInto(tote);
			else
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
	 * Return time taken in milliseconds
	 * Note: Can be generalized for not just shopping challenges
	 * 
	 * @param products
	 * @param tote
	 * @return
	 */
	public Duration maximizeMyShopping(List<Product> products, Tote tote) {
		Instant start = Instant.now();
		
		doMaximizeMyShopping(products, tote);
		
		return Duration.between(start, Instant.now());		
	}
	
	/**
	 * Note: Can be generalized for not just shopping challenges
	 * @param products
	 * @param tote
	 */
	protected abstract void doMaximizeMyShopping(List<Product> products, Tote tote);
}
