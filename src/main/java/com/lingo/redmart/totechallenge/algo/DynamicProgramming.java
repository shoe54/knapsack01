package com.lingo.redmart.totechallenge.algo;

import java.util.List;

import com.lingo.redmart.totechallenge.PriceWeightTuple;
import com.lingo.redmart.totechallenge.Product;
import com.lingo.redmart.totechallenge.Solution;
import com.lingo.redmart.totechallenge.Tote;

public class DynamicProgramming extends Solution {
	/**
	 * Time complexity is O(n * tote volume)
	 * 
	 * @param products
	 * @param tote
	 */
	@Override
	public void maximizeMyShopping(List<Product> products, Tote tote) {
		// Where:
		// i: 1 <= i <= products.size()
		// v(i): is the volume of the i'th product
		// $(i): is the price of the i'th product
		// w(i): is the weight of the i'th product
		// P(i): is the i'th product
		// 
		// Build array S[i][c] where the value is the optimal (price,weight) solution for
		// first i products in the list and 0 <= c <= tote.getVolume(). Start
		// building from the simplest case, which is where i = 0 and c = 0
		//
		// S[0][c] = (0,0) (Maximum price solution of zero items is zero)
		// S[i][0] = (0,0) (Maximum price solution when there is no space is zero)
		// S[1][1] = ($(i),w(i)) if v(i) <= 1
		//
		// The final solution is derived by building on top of the above starting
		// point:
		//
		// If v(i) > c
		// 	  S[i][c] = S[i-1][c] (can't fit P(i) in a space of size c, so the solution is the same as the solution for i-1)
		// Else
		//	  S[i][c] = max(S[i-1][c], $(i) + S[i-1][c-v(i)]) (max compares by price then reverse weight)
		//
		// Note that S[i-1][c] effectively means the product was not included in the solution
		
		PriceWeightTuple S[][] = new PriceWeightTuple[products.size()+1][tote.getVolume()+1];
		
		Product Pi = null; // P(i), aka products.get(i-1)
		for (int i = 0; i <= products.size(); i++) {
			if (i > 0)
				Pi = products.get(i-1);
			for (int c = 0; c <= tote.getVolume(); c++) {
				if (i == 0 || c == 0)
					S[i][c] = PriceWeightTuple.ZERO;
				else {
					PriceWeightTuple didNotInclude = S[i-1][c];
					if (Pi.getVolume() > c)
						S[i][c] = didNotInclude;
					else {
						int didIncludeTotalPrice = Pi.getPrice() + S[i-1][c-Pi.getVolume()].getTotalPrice();  
						int didIncludeTotalWeight = Pi.getWeight() + S[i-1][c-Pi.getVolume()].getTotalWeight();
						PriceWeightTuple didInclude = new PriceWeightTuple(didIncludeTotalPrice, didIncludeTotalWeight);
						int cmp = didNotInclude.compareTo(didInclude);
						S[i][c] = cmp > 0 ? didNotInclude : didInclude;
					}
				}
			}
		}
		
		// Back track to find out which products were added to the optimal solution
		int c = tote.getVolume();
		int i = products.size();
		while (c > 0 && i > 0) {
			if (!S[i][c].equals(S[i-1][c])) {
				// Item i was included in the optimal solution
				Pi = products.get(i-1);
				if (!tote.addProduct(Pi))
					throw new IllegalStateException("This should not happen as the algorithm ensures each product added fits the capacity");
				c = c - Pi.getVolume(); 
			}
			i--; // Move to P(i-1)
		}
	}

}
