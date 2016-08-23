package nz.co.lingo.algos.knapsack01;


public abstract class AbstractNumberItem<IV extends Number & Comparable<? super IV>> implements Item<IV, Double> {
	int cost;
	IV value;
	
	public AbstractNumberItem(int cost, IV value) {
		super();
		if (value == null) throw new IllegalArgumentException("value cannot be null");
		this.cost = cost;
		this.value = value;		
	}
	
	@Override
	public int getCost() {
		return cost;
	}

	@Override
	public IV getValue() {
		return value;
	}

	@Override
	public Double valueToDouble(IV v) {
		return v.doubleValue();
	}

	@Override
	public Double addDoubles(Double a, Double b) {
		return a + b;
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
		AbstractNumberItem other = (AbstractNumberItem) obj;
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
		return getClass().getSimpleName() + " [getCost()=" + getCost() + ", getValue()="
				+ getValue() + "]";
	}

	
}
