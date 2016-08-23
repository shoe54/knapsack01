package nz.co.lingo.algos.knapsack01;

import java.util.Comparator;

/**
 * Pool implementation for DefaultItem
 * 
 * @author Shu
 *
 */
public class DefaultPool extends AbstractPool<DefaultItem> {
	public DefaultPool(int allowedCost) {
		super(allowedCost);
	}

	@Override
	public Comparator<DefaultItem> getValueToCostRatioComparator() {
		return (o1, o2) -> ((Double)(o1.getValue() / (double)o1.getCost())).compareTo((Double)(o2.getValue() / (double)o2.getCost()));
	}
}
