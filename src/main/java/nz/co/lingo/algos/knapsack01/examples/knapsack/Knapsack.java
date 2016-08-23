package nz.co.lingo.algos.knapsack01.examples.knapsack;

import java.math.BigDecimal;
import java.util.Comparator;

import nz.co.lingo.algos.knapsack01.AbstractPool;

/**
 * TODO test cases for this class
 * @author Shu
 *
 */
public class Knapsack extends AbstractPool<Gear> {

	public Knapsack(int allowedCost) {
		super(allowedCost);
	}

	@Override
	public Comparator<Gear> getValueToCostRatioComparator() {
		return (o1, o2) -> 
			(o1.divideByCost(o1.getValue()).compareTo(o2.divideByCost(o2.getValue())));
	}

}
