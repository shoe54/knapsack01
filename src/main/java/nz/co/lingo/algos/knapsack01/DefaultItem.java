package nz.co.lingo.algos.knapsack01;

/**
 * Integer valued Item
 * 
 * Immutable.
 * @author Shu
 *
 */
public class DefaultItem extends AbstractNumberItem<Integer> {

	public DefaultItem(int cost, Integer value) {
		super(cost, value);
		if (cost < 0 || value < 0)
			throw new IllegalArgumentException("Negative cost or value not allowed");
	}

	@Override
	public Integer getValueZero() {
		return 0;
	}

	@Override
	public Integer addValues(Integer a, Integer b) {
		return a + b;
	}

	@Override
	public Integer multiplyValue(int multiplier) {
		return Math.multiplyExact(getValue(), multiplier);
	}

	@Override
	public Double divideByCost(Integer top) {
		return top / (double)getCost();
	}

	@Override
	public Double addDouble(Double d) {
		return getValue() + d;
	}
}
