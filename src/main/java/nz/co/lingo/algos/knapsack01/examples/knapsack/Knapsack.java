package nz.co.lingo.algos.knapsack01.examples.knapsack;

import java.math.BigDecimal;
import java.util.Comparator;

import nz.co.lingo.algos.knapsack01.AbstractPool;

public class Knapsack extends AbstractPool<Gear> {

	public Knapsack(int allowedCost) {
		super(allowedCost);
	}

	@Override
	public Comparator<Gear> getValueToCostRatioComparator() {
		return (Gear o1, Gear o2) -> 
			((o1.getValue().divide(new BigDecimal(o1.getCost()))).compareTo((o2.getValue().divide(new BigDecimal(o2.getCost())))));
	}

}
