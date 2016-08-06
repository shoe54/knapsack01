package com.lingo.redmart.totechallenge;

import java.util.HashSet;
import java.util.Set;

public class TestUtil {

	public static Set<Product> setOfProducts(Product... products) {
		Set<Product> ret = new HashSet<>();
		for (Product p : products)
			ret.add(p);
		return ret;
	}

}
