package nz.co.lingo.algos.knapsack01;

import java.util.List;

/**
 * DP provides the global optimum solution
 * 
 * Time complexity is O(n * tote volume)
 * 
 * TODO: Make a primitive int version of this which consumes less memory
 * 
 * @author Shu
 *
 */
public class DynamicProgramming<
		IV extends Comparable<? super IV>, 
		I extends Item<IV>, 
		P extends Pool<I>> 
	extends Solver<IV, I, P> {

	@Override
	protected void doSolve(List<I> items, P tote) {
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
		
		//TODO: Option to optimize so that if allowedCost is a high value we don't allocate array width for the costs < allowedCost
		IV S[][] = (IV[][])new Comparable[items.size()+1][tote.getAllowedCost()+1];
		
		I Pi = null; // P(i), aka products.get(i-1)
		for (int i = 0; i <= items.size(); i++) {
			if (i > 0)
				Pi = items.get(i-1);
			for (int c = 0; c <= tote.getAllowedCost(); c++) {
				if (i == 0 || c == 0)
					S[i][c] = items.get(0).getValueZero();
				else {
					IV didNotInclude = S[i-1][c];
					if (Pi.getCost() > c)
						S[i][c] = didNotInclude;
					else {
						IV didInclude = Pi.addValue(S[i-1][c-Pi.getCost()]);
						int cmp = didNotInclude.compareTo(didInclude);
						S[i][c] = cmp > 0 ? didNotInclude : didInclude;
					}
				}
			}
		}
		
		// Back track to find out which products were added to the optimal solution
		int c = tote.getAllowedCost();
		int i = items.size();
		while (c > 0 && i > 0) {
			if (!S[i][c].equals(S[i-1][c])) {
				// Item i was included in the optimal solution
				Pi = items.get(i-1);
				if (!tote.addItem(Pi))
					throw new IllegalStateException("This should not happen as the algorithm ensures each product added fits the capacity");
				c = c - Pi.getCost(); 
			}
			i--; // Move to P(i-1)
		}
	}

}
