package nz.co.lingo.algos.knapsack01.examples.knapsack;

import java.math.BigDecimal;
import java.math.RoundingMode;

import nz.co.lingo.algos.knapsack01.AbstractNumberItem;

/**
 * BigDecimal valued AbstractItem. Cost is in grams
 * 
 * Immutable.
 * 
 * TODO test cases for this class
 * 
 * @author Shu
 *
 */
public class Gear extends AbstractNumberItem<BigDecimal> {

	public Gear(int cost, BigDecimal value) {
		super(cost, value);
	}

	@Override
	public BigDecimal getValueZero() {
		return BigDecimal.ZERO.setScale(2);
	}

	@Override
	public BigDecimal addValues(BigDecimal a, BigDecimal b) {
		return a.add(b);
	}

	@Override
	public BigDecimal multiplyValue(int multiplier) {
		return getValue().multiply(new BigDecimal(multiplier));
	}

	@Override
	public BigDecimal divideByCost(BigDecimal top) {
		return top.divide(new BigDecimal(getCost()), 6, RoundingMode.HALF_UP);
	}

}
