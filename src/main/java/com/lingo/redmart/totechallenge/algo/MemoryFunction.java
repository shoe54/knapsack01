package com.lingo.redmart.totechallenge.algo;

import java.util.List;

import com.lingo.redmart.totechallenge.PriceWeightTuple;
import com.lingo.redmart.totechallenge.Product;
import com.lingo.redmart.totechallenge.Solution;
import com.lingo.redmart.totechallenge.Tote;

/**
 * Top down and recursive variant of DP
 * 
 * @author Shu
 *
 */
public class MemoryFunction extends Solution {

	@Override
	protected void doMaximizeMyShopping(List<Product> products, Tote tote) {
		// Where:
		// i: 1 <= i <= products.size()
		// P(i): is the i'th product
		// c: 0 <= c <= tote.getVolume()
		
		@SuppressWarnings("unchecked")
		PriceWeightTuple<Integer> S[][] = new PriceWeightTuple[products.size()+1][tote.getVolume()+1];
		for (int i = 0; i < S.length; i++)
			for (int c = 0; c < S[i].length; c++)
				S[i][c] = null;
		for( int i = 0; i < S.length; i++) {
			S[i][0] = PriceWeightTuple.ZERO;
		}
		for( int i = 0; i < S[0].length; i++) {
			S[0][i] = PriceWeightTuple.ZERO;
		}
		
		int i = products.size();
		int c = tote.getVolume();
		
		memoryFunction(i, c, S, products);

		// find tote items
		int k = tote.getVolume();
		for( i = products.size(); i > 0; i--) {
			if( S[i][k] != S[i - 1][k]) {
				tote.addProduct(products.get(i - 1));
				k -= products.get(i - 1).getVolume();
				//value += products.get(i - 1).getPrice();
			}
		}	
	}

	/**
	 * Similar recurrence relation as DP algo
	 * @param i
	 * @param c
	 * @param S
	 * @param products
	 * @return
	 */
	PriceWeightTuple<Integer> memoryFunction(int i, int c, PriceWeightTuple<Integer> S[][], List<Product> products) {
		PriceWeightTuple<Integer> value = S[i][c];
		
		if (value == null) {
			Product Pi = products.get(i-1);
			int productVolume = Pi.getVolume();
			if( c < productVolume) {
				value = memoryFunction(i-1, c, S, products);
			}
			else {
				PriceWeightTuple<Integer> didNotInclude = memoryFunction(i-1, c, S, products);
				PriceWeightTuple<Integer> previousIncluded = memoryFunction(i-1, c-productVolume, S, products);
				PriceWeightTuple<Integer> didInclude = 
					new PriceWeightTuple<Integer>(
							Pi.getPrice() + previousIncluded.getPrice(),
							Pi.getWeight() + previousIncluded.getWeight());
				int cmp = didNotInclude.compareTo(didInclude);
				value = cmp > 0 ? didNotInclude : didInclude;
			}
			
			S[i][c] = value;
		}
		
		return value;
	}
}
