package com.lingo.redmart.totechallenge.algo;

import java.math.BigInteger;
import java.util.List;

import com.lingo.redmart.totechallenge.PriceWeightTuple;
import com.lingo.redmart.totechallenge.Product;
import com.lingo.redmart.totechallenge.Solver;
import com.lingo.redmart.totechallenge.Tote;

/**
 * Brute Force approach with no back tracking. Implemented as a search through bit string
 * space, not as a search through a state space tree.
 * 
 * O(n * (2 pow n))
 * 
 * @author Shu
 *
 */
public class BruteForce extends Solver {
	/**
	 * Iterate thru all possible combinations of products in the tote. A
	 * combination is represented as a series of bits where 0 means the product
	 * is not included and 1 means the product is included. A BigInteger is used
	 * to hold this series of bits since it can hold and operate on a large number
	 * if bits
	 * 
	 * We keep track of which combination has the highest value. At the end of
	 * the iteration this gives us the best solution possible.
	 * 
	 * n = number of products number of possible combinations = (2 power n) - 1
	 * number of bits required to represent a combination = n
	 *
	 * @param products
	 * @param tote
	 */
	@Override
	protected void doMaximizeMyShopping(List<Product> products, Tote tote) {
		PriceWeightTuple<Integer> combinationValue;
		int combinationVolume;
		BigInteger bestCombination = null;
		PriceWeightTuple<Integer> bestCombinationValue = PriceWeightTuple.ZERO;
		BigInteger numberOfPossibleCombinations = BigInteger.valueOf(2)
				.pow(products.size()).subtract(BigInteger.ONE);

	combinationIteration:
		for (BigInteger combination = BigInteger.valueOf(1);
				combination.compareTo(numberOfPossibleCombinations) <= 0;
				combination = combination.add(BigInteger.ONE)) {

			// Calculate the value of the current combination
			combinationValue = PriceWeightTuple.ZERO;
			combinationVolume = 0;
			for (int bitIndex = 0; bitIndex < products.size(); bitIndex++) {
				if (combination.testBit(bitIndex)) {
					// System.out.println("Bit " + bitIndex + " is set");
					Product productAtBit = products.get(bitIndex);
					combinationValue = combinationValue.add(
						productAtBit.getPrice(), productAtBit.getWeight());
					combinationVolume += productAtBit.getVolume();
					if (combinationVolume > tote.getVolume())
						// Solution is not feasible already at this point. Skip this combination
						continue combinationIteration; 
				}
			}
		
			// If current combination is better than the one we have so far, replace it
			if (combinationValue.compareTo(bestCombinationValue) > 0) {
				bestCombination = combination;
				bestCombinationValue = combinationValue;
			}
		}
		
		// Add products from best combination into tote
		for (int bitIndex = 0; bitIndex < products.size(); bitIndex++) {
			if (bestCombination.testBit(bitIndex)) {
				Product productAtBit = products.get(bitIndex);
				tote.addProduct(productAtBit);
			}
		}
	}

}
