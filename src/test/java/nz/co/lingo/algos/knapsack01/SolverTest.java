package nz.co.lingo.algos.knapsack01;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import nz.co.lingo.algos.knapsack01.examples.shopping.App.DoesProductFitIntoEmptyTote;
import nz.co.lingo.algos.knapsack01.examples.shopping.App.PriceToVolumeRatioComparator;
import nz.co.lingo.algos.knapsack01.examples.shopping.PriceWeightTuple;
import nz.co.lingo.algos.knapsack01.examples.shopping.Product;
import nz.co.lingo.algos.knapsack01.examples.shopping.Tote;

import org.junit.BeforeClass;
import org.junit.Test;

public class SolverTest {
	static Solver<PriceWeightTuple<Integer>, Product, Tote> s;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		s = new Solver<PriceWeightTuple<Integer>, Product, Tote>() {
			@Override
			public void doSolve(List<Product> products, Tote tote) {}
		};
	}

	/**
	 * Test checking by volume only
	 */
	@Test
	public void testDoesProductFitIntoEmptyTote01() {
		DoesProductFitIntoEmptyTote filter = new DoesProductFitIntoEmptyTote(new Tote(50, 40, 30), false);
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
	
	/**
	 * Test checking by dimensions
	 */
	@Test
	public void testDoesProductFitIntoEmptyTote02() {
		DoesProductFitIntoEmptyTote filter = new DoesProductFitIntoEmptyTote(new Tote(50, 40, 30), true);
		assertTrue(filter.test(new Product(1, 1, 50, 40, 30, 1)));
		assertTrue(filter.test(new Product(1, 1, 40, 50, 30, 1)));
		assertTrue(filter.test(new Product(1, 1, 40, 40, 30, 1)));
		assertTrue(filter.test(new Product(1, 1, 50, 30, 30, 1)));
		assertTrue(filter.test(new Product(1, 1, 50, 40, 20, 1)));
		assertFalse(filter.test(new Product(1, 1, 60, 40, 20, 1)));
		assertFalse(filter.test(new Product(1, 1, 51, 40, 30, 1)));
		assertFalse(filter.test(new Product(1, 1, 50, 41, 30, 1)));
		assertFalse(filter.test(new Product(1, 1, 50, 40, 31, 1)));
		assertFalse(filter.test(new Product(1, 1, 40, 40, 40, 1)));
	}

	@Test
	public void testPriceToVolumeRatioComparator() {
		PriceToVolumeRatioComparator cmp = new PriceToVolumeRatioComparator();

		assertEquals(0, cmp.compare(new Product(1, 10, 5, 1, 1, 2), new Product(1, 10, 5, 1, 1, 2)));
		assertNotEquals(0, cmp.compare(new Product(1, 20, 10, 1, 1, 2), new Product(1, 10, 5, 1, 1, 2)));
		
		assertTrue(cmp.compare(new Product(1, 11, 5, 1, 1, 2), new Product(1, 10, 5, 1, 1, 2)) > 0);
		assertTrue(cmp.compare(new Product(1, 9, 5, 1, 1, 2), new Product(1, 10, 5, 1, 1, 2)) < 0);

		assertTrue(cmp.compare(new Product(1, 20, 10, 1, 1, 2), new Product(1, 10, 5, 1, 1, 2)) > 0);
		assertTrue(cmp.compare(new Product(1, 10, 5, 1, 1, 2), new Product(1, 20, 10, 1, 1, 2)) < 0);

		assertTrue(cmp.compare(new Product(1, 10, 5, 1, 1, 3), new Product(1, 10, 5, 1, 1, 2)) < 0);
		assertTrue(cmp.compare(new Product(1, 10, 5, 1, 1, 1), new Product(1, 10, 5, 1, 1, 2)) > 0);
	}
	
}
