package nz.co.lingo.algos.knapsack01;

import java.util.Comparator;
import java.util.stream.Stream;

public interface Pool<I extends Item<?>> {

	boolean addItem(I i);
	
	Stream<I> getItems();
	
	default boolean isFull() {
		return getUsedCost() >= getAllowedCost();		
	}
	
	int getUsedCost();

	Comparator<I> getValueToCostRatioComparator();
	
	int getAllowedCost();
	
	default boolean canAccommodateItem(I i) {
		if (i == null) throw new IllegalArgumentException("Missing item");

		return getAllowedCost() >= getUsedCost() + i.getCost();
	}

}
