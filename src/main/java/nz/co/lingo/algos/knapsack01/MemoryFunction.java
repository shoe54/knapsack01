package nz.co.lingo.algos.knapsack01;

import java.util.List;

/**
 * Top down and recursive variant of DP
 * 
 * @author Shu
 *
 */
public class MemoryFunction<IV extends Comparable<? super IV>, I extends Item<IV>, P extends Pool<I>> 
	extends Solver<IV, I, P> {

	@Override
	protected void doSolve(List<I> products, P tote) {
		// Where:
		// i: 1 <= i <= products.size()
		// P(i): is the i'th product
		// c: 0 <= c <= tote.getVolume()
		
		IV S[][] = (IV[][])new Comparable[products.size()+1][tote.getAllowedCost()+1];
		for (int i = 0; i < S.length; i++)
			for (int c = 0; c < S[i].length; c++)
				S[i][c] = null;
		for( int i = 0; i < S.length; i++) {
			S[i][0] = products.get(0).getValueZero();
		}
		for( int i = 0; i < S[0].length; i++) {
			S[0][i] = products.get(0).getValueZero();
		}
		
		int i = products.size();
		int c = tote.getAllowedCost();
		
		memoryFunction(i, c, S, products);

		// find tote items
		int k = tote.getAllowedCost();
		for( i = products.size(); i > 0; i--) {
			if( S[i][k] != S[i - 1][k]) {
				tote.addItem(products.get(i - 1));
				k -= products.get(i - 1).getCost();
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
	/*PriceWeightTuple<Integer> memoryFunction(int i, int c, PriceWeightTuple<Integer> S[][], List<I> products) {
		PriceWeightTuple<Integer> value = S[i][c];
		
		if (value == null) {
			I Pi = products.get(i-1);
			int productVolume = Pi.getCost();
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
	}*/

	IV memoryFunction(int i, int c, IV S[][], List<I> products) {
		IV value = S[i][c];
		
		if (value == null) {
			I Pi = products.get(i-1);
			int productVolume = Pi.getCost();
			if( c < productVolume) {
				value = memoryFunction(i-1, c, S, products);
			}
			else {
				IV didNotInclude = memoryFunction(i-1, c, S, products);
				IV previousIncluded = memoryFunction(i-1, c-productVolume, S, products);
				IV didInclude = Pi.addValue(previousIncluded);
				int cmp = didNotInclude.compareTo(didInclude);
				value = cmp > 0 ? didNotInclude : didInclude;
			}
			
			S[i][c] = value;
		}
		
		return value;
	}
}
