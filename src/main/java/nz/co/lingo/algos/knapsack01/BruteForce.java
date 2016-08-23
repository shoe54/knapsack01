package nz.co.lingo.algos.knapsack01;

import java.math.BigInteger;
import java.util.List;

/**
 * Brute Force approach with no back tracking. Implemented as a search through bit string
 * space, not as a search through a state space tree.
 * 
 * O(n * (2 pow n))
 * 
 * @author Shu
 *
 */
public class BruteForce<
		IV extends Comparable<? super IV>, 
		IVD extends Comparable<? super IVD>, 
		I extends Item<IV, IVD>, 
		P extends Pool<IV, IVD, I>>
	extends Solver<IV, IVD, I, P> {
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
	 * @param items
	 * @param tote
	 */
	@Override
	protected void doSolve(List<I> items, P tote) {
		IV combinationValue;
		int combinationCost;
		BigInteger bestCombination = null;
		IV bestCombinationValue = items.get(0).getValueZero();
		BigInteger numberOfPossibleCombinations = BigInteger.valueOf(2)
				.pow(items.size()).subtract(BigInteger.ONE);

combinationIteration:
		for (BigInteger combination = BigInteger.valueOf(1);
				combination.compareTo(numberOfPossibleCombinations) <= 0;
				combination = combination.add(BigInteger.ONE)) {

			// Calculate the value of the current combination
			combinationValue = items.get(0).getValueZero();
			combinationCost = 0;
			for (int bitIndex = 0; bitIndex < items.size(); bitIndex++) {
				if (combination.testBit(bitIndex)) {
					// System.out.println("Bit " + bitIndex + " is set");
					I itemAtBit = items.get(bitIndex);
					combinationValue = itemAtBit.addValue(combinationValue);
					combinationCost += itemAtBit.getCost();
					if (combinationCost > tote.getAllowedCost())
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
		for (int bitIndex = 0; bitIndex < items.size(); bitIndex++) {
			if (bestCombination.testBit(bitIndex)) {
				I productAtBit = items.get(bitIndex);
				tote.addItem(productAtBit);
			}
		}
	}

}
