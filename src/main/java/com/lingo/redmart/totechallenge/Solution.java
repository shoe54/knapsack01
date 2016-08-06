package com.lingo.redmart.totechallenge;

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
	 * Note: Can be generalized for not just shopping challenges
	 * @param products
	 * @param tote
	 */
	public abstract void maximizeMyShopping(List<Product> products, Tote tote);
}
