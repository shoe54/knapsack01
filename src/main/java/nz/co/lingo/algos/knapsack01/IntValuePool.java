package nz.co.lingo.algos.knapsack01;


/**
 * Pool implementation for DefaultItem
 * 
 * @author Shu
 *
 */
public class IntValuePool extends GenericPool<Integer, Double, IntValueItem> {
	public IntValuePool(int allowedCost) {
		super(allowedCost);
	}

}
