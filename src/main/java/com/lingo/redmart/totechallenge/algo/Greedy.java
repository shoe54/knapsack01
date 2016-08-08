package com.lingo.redmart.totechallenge.algo;

import java.util.Collections;
import java.util.List;

import com.lingo.redmart.totechallenge.Product;
import com.lingo.redmart.totechallenge.Solver;
import com.lingo.redmart.totechallenge.Tote;

/**
 * Greedy algorithm
 */
public class Greedy extends Solver {
	/**
	 * Put the most valuable products into tote until it fills up or can't
	 * fit any more. The most valuable product is one with the highest price
	 * to volume ratio. On a tie the lower weight is more valuable.
	 * 
	 * Time complexity is sort + O(n)
	 * 
	 * @param products
	 * @param tote
	 */
	@Override
	protected void doMaximizeMyShopping(List<Product> products, Tote tote) {
		// Sort products by value (price to volume ratio descending). On a value tie,
		// sort by volume descending. On a value and volume tie, sort by weight
		// ascending. The most valuable products will appear first in the list. 
		// The volume descending tie breaker means that larger items appear earlier
		// in the sorted list, so that the tote will fill up with higher value items first.
		products.sort(Collections.reverseOrder(new PriceToVolumeRatioComparator()));

		// While the tote isn't full, fill up from the start of
		// the products list, i.e. most valuable products go in first
		for (Product p : products) {
			tote.addProduct(p);

			if (tote.isFull())
				break;
		}
	}

}
