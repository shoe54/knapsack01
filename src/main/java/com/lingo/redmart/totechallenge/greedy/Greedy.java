package com.lingo.redmart.totechallenge.greedy;

import java.util.Comparator;
import java.util.List;

import com.lingo.redmart.totechallenge.Product;
import com.lingo.redmart.totechallenge.Solution;
import com.lingo.redmart.totechallenge.Tote;

/**
 * Greedy algorithm
 */
public class Greedy extends Solution {
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
	public void maximizeMyShopping(List<Product> products, Tote tote) {
		// Sort products by value (price to volume ratio descending then weight
		// ascending). On a value tie, sort by volume descending. The most
		// valuable products will appear first in the list. The volume descending
		// tie breaker means that larger items appear earlier in the sorted list,
		// so that the tote will fill up with higher value items first.
		products.sort(new Comparator<Product>() {
			@Override
			public int compare(Product o1, Product o2) {
				if (o1.getPriceToVolumeRatio() < o2.getPriceToVolumeRatio())
					return 1;
				else if (o1.getPriceToVolumeRatio() > o2
						.getPriceToVolumeRatio())
					return -1;
				else if (o1.getWeight() != o2.getWeight())
					return o1.getWeight() - o2.getWeight();
				else
					return o2.getVolume() - o1.getVolume();
			}
		});

		// While the tote isn't full, fill up from the start of
		// the products list, i.e. most valuable products go in first
		for (Product p : products) {
			tote.addProduct(p);

			if (tote.isFull())
				break;
		}
	}

}
