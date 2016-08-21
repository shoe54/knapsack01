package nz.co.lingo.algos.knapsack01;

/**
 * Integer valued Item
 * 
 * Immutable.
 * @author Shu
 *
 */
public class DefaultItem implements Item<Integer> {

	private int cost;
	private Integer value;

	public DefaultItem(int cost, Integer value) {
		super();
		this.cost = cost;
		this.value = value;
	}

	@Override
	public int getCost() {
		return cost;
	}

	@Override
	public Integer getValue() {
		return value;
	}

	@Override
	public Comparable<?> valueToDouble(Integer v) {
		return v.doubleValue();
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + cost;
		result = prime * result + ((value == null) ? 0 : value.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DefaultItem other = (DefaultItem) obj;
		if (cost != other.cost)
			return false;
		if (value == null) {
			if (other.value != null)
				return false;
		} else if (!value.equals(other.value))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "DefaultItem [getCost()=" + getCost() + ", getValue()="
				+ getValue() + "]";
	}

	
}
