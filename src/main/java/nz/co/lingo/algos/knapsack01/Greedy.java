package nz.co.lingo.algos.knapsack01;

import java.util.Collections;
import java.util.List;

/**
 * Greedy algorithm
 */
public class Greedy<
		IV extends Comparable<? super IV>, 
		IVD extends Comparable<? super IVD>, 
		I extends Item<IV, IVD>, 
		P extends Pool<IV, IVD, I>> 
	extends Solver<IV, IVD, I, P> {
	/**
	 * Put the most valuable products into tote until it fills up or can't
	 * fit any more. The most valuable product is one with the highest price
	 * to volume ratio. On a tie the lower weight is more valuable.
	 * 
	 * Time complexity is sort + O(n)
	 * 
	 * @param items
	 * @param pool
	 */
	@Override
	protected void doSolve(List<I> items, P pool) {
		// Sort items by value (price to volume ratio descending). On a value tie,
		// sort by volume descending. On a value and volume tie, sort by weight
		// ascending. The most valuable products will appear first in the list. 
		// The volume descending tie breaker means that larger items appear earlier
		// in the sorted list, so that the tote will fill up with higher value items first.
		items.sort(Collections.reverseOrder(pool.getValueToCostRatioComparator()));

		// While the tote isn't full, fill up from the start of
		// the products list, i.e. most valuable products go in first
		for (I i : items) {
			pool.addItem(i);

			if (pool.isFull())
				break;
		}
	}

}
