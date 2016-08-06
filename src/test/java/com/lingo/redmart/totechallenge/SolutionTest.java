package com.lingo.redmart.totechallenge;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import com.lingo.redmart.totechallenge.Solution.DoesProductFitIntoEmptyTote;

public class SolutionTest {
	static Solution s;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		s = new Solution() {
			@Override
			public void maximizeMyShopping(List<Product> products, Tote tote) {}
		};
	}
	
	@Test
	public void testDoesProductFitIntoEmptyTote() {
		DoesProductFitIntoEmptyTote filter = new DoesProductFitIntoEmptyTote(new Tote(50, 40, 30));
		assertTrue(filter.test(new Product(1, 1, 50, 40, 30, 1)));
		assertTrue(filter.test(new Product(1, 1, 40, 50, 30, 1)));
		assertTrue(filter.test(new Product(1, 1, 40, 40, 30, 1)));
		assertTrue(filter.test(new Product(1, 1, 50, 30, 30, 1)));
		assertTrue(filter.test(new Product(1, 1, 50, 40, 20, 1)));
		assertTrue(filter.test(new Product(1, 1, 60, 40, 20, 1)));
		assertFalse(filter.test(new Product(1, 1, 51, 40, 30, 1)));
		assertFalse(filter.test(new Product(1, 1, 50, 41, 30, 1)));
		assertFalse(filter.test(new Product(1, 1, 50, 40, 31, 1)));
		assertFalse(filter.test(new Product(1, 1, 40, 40, 40, 1)));
	}
	
}
