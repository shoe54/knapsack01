package nz.co.lingo.algos.knapsack01;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public abstract class AbstractPool<I extends Item<?>> implements Pool<I> {
	Set<I> items = new HashSet<>();
	int allowedCost;

	public AbstractPool(int allowedCost) {
		super();
		this.allowedCost = allowedCost;
	}

	@Override
	public boolean addItem(I i) {
		if (i == null) throw new IllegalArgumentException("Missing item");
		
		if (!canAccommodateItem(i))
			return false;
		
		if (!items.add(i))
			throw new IllegalStateException("Adding an item that already exists!");
		
		return true;
	}

	@Override
	public Stream<I> getItems() {
		return items.stream();
	}
	
	public void removeAllItems() {
		// TODO test case
		items.clear();
	}

	@Override
	public int getUsedCost() {
		return getItems().collect(Collectors.summingInt(I::getCost));
	}

	@Override
	public int getAllowedCost() {
		return allowedCost;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + allowedCost;
		result = prime * result + ((items == null) ? 0 : items.hashCode());
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
		AbstractPool other = (AbstractPool) obj;
		if (allowedCost != other.allowedCost)
			return false;
		if (items == null) {
			if (other.items != null)
				return false;
		} else if (!items.equals(other.items))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return getClass().getSimpleName() + " [getItems()=" + getItems() + ", getUsedCost()="
				+ getUsedCost() + ", getAllowedCost()=" + getAllowedCost()
				+ "]";
	}

	
}
