package nz.co.lingo.algos.knapsack01;

import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 
 * @author Shu
 *
 */
public class DefaultPool implements Pool<DefaultItem> {
	Set<DefaultItem> items = new HashSet<>();
	int allowedCost;

	public DefaultPool(int allowedCost) {
		super();
		this.allowedCost = allowedCost;
	}

	@Override
	public boolean addItem(DefaultItem i) {
		if (i == null) throw new IllegalArgumentException("Missing item");
		
		if (!canAccommodateItem(i))
			return false;
		
		if (!items.add(i))
			throw new IllegalStateException("Adding a product id that already exists!");
		
		return true;
	}

	@Override
	public int getUsedCost() {
		return items.stream().collect(Collectors.summingInt(DefaultItem::getCost));
	}

	@Override
	public Comparator<DefaultItem> getValueToCostRatioComparator() {
		return (o1, o2) -> ((Double)(o1.getValue() / (double)o1.getCost())).compareTo((Double)(o2.getValue() / (double)o2.getCost()));
	}

	@Override
	public int getAllowedCost() {
		return allowedCost;
	}

	@Override
	public Stream<DefaultItem> getItems() {
		return items.stream();
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
		DefaultPool other = (DefaultPool) obj;
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
		return "DefaultPool [getUsedCost()=" + getUsedCost()
				+ ", getAllowedCost()=" + getAllowedCost() + ", getItems()="
				+ getItems() + "]";
	}

	
}
