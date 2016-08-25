package nz.co.lingo.algos.knapsack01.examples.knapsack;

import java.math.BigDecimal;

import nz.co.lingo.algos.knapsack01.GenericPool;

/**
 * A knapsack's items are Gears, whose values are BigDecimals to represent
 * price
 * 
 * @author Shu
 *
 */
public class Knapsack extends GenericPool<BigDecimal, Double, Gear> {

	public Knapsack(int allowedCost) {
		super(allowedCost);
	}
}
