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
		return getValue() * multiplier;
	}

	@Override
	public Integer divideByCost(Integer top) {
		return top / getCost();
	}
}
