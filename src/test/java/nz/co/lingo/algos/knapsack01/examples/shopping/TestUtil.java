package nz.co.lingo.algos.knapsack01.examples.shopping;

import java.util.HashSet;
import java.util.Set;

import nz.co.lingo.algos.knapsack01.DefaultItem;

public class TestUtil {

	public static Set<Product> setOfProducts(Product... products) {
		Set<Product> ret = new HashSet<>();
		for (Product p : products)
			ret.add(p);
		return ret;
	}

	public static Set<DefaultItem> setOfDefaultItems(DefaultItem... items) {
		Set<DefaultItem> ret = new HashSet<>();
		for (DefaultItem p : items)
			ret.add(p);
		return ret;
	}

}
