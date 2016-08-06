package com.lingo.redmart.totechallenge.greedy;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import com.lingo.redmart.totechallenge.Product;
import com.lingo.redmart.totechallenge.TestUtil;
import com.lingo.redmart.totechallenge.Tote;
import com.lingo.redmart.totechallenge.greedy.Greedy;


public class GreedyTest {
	static Greedy g;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		g = new Greedy();
	}

	/**
	 * Verified these results using manual calculations
	 */
	@Test
	public void testMaximizeMyShopping01() {
		List<Product> products = new ArrayList<>();
		Tote t;

		Product p1 = new Product(31288, 1990, 15, 10, 10, 1584); // volume: 1500 price to volume: 1.326666667
		Product p2 = new Product(14301, 2139, 15, 13, 10, 804); // volume: 1950 price to volume: 1.096923077
		Product p3 = new Product(39987, 2229, 19, 11, 10, 1647); // volume: 2090 price to volume: 1.066507177
		Product p4 = new Product(26950, 2215, 15, 11, 13, 547); // volume: 2145 price to volume: 1.032634033
		Product p5 = new Product(10496, 2080, 17, 13, 10, 1417); // volume: 2210 price to volume: 0.941176471
		Product p6 = new Product(17788, 2509, 15, 10, 18, 2078); // volume: 2700 price to volume: 0.929259259
		Product p7 = new Product(5084, 1651, 15, 12, 10, 1160); // volume: 1800 price to volume: 0.917222222

		// Add in unsorted order
		products.add(p7);
		products.add(p2);
		products.add(p1);
		products.add(p3);
		products.add(p5);
		products.add(p6);
		products.add(p4);

		t = new Tote(20, 30, 25); // volume: 21000
		g.maximizeMyShopping(products, t);
		assertEquals(TestUtil.setOfProducts(p1,p2,p3,p4,p5,p6,p7), t.getProducts());
		
		t = new Tote(1, 10, 554); // volume: 5540
		g.maximizeMyShopping(products, t);
		assertEquals(TestUtil.setOfProducts(p1,p2,p3), t.getProducts());

		t = new Tote(1, 10, 553); // volume: 5530
		g.maximizeMyShopping(products, t);
		assertEquals(TestUtil.setOfProducts(p1,p2,p7), t.getProducts());

		t = new Tote(1, 10, 555); // volume: 5550
		g.maximizeMyShopping(products, t);
		assertEquals(TestUtil.setOfProducts(p1,p2,p3), t.getProducts());
	}
	
	/**
	 * Test weight tie breaker
	 */
	@Test
	public void testMaximizeMyShopping02() {
		List<Product> products = new ArrayList<>();
		Tote t = new Tote(1, 10, 1); // volume: 10

		Product p1 = new Product(1, 20, 5, 2, 1, 1); // volume: 10
		Product p2 = new Product(2, 20, 5, 2, 1, 2); // volume: 10
		Product p3 = new Product(3, 20, 5, 2, 1, 3); // volume: 10
		Product p4 = new Product(4, 20, 5, 2, 1, 4); // volume: 10
		Product p5 = new Product(5, 20, 5, 2, 1, 5); // volume: 10
		Product p6 = new Product(6, 20, 5, 2, 1, 6); // volume: 10
		Product p7 = new Product(7, 20, 5, 2, 1, 7); // volume: 10

		// Add in unsorted order
		products.add(p7);
		products.add(p2);
		products.add(p1);
		products.add(p3);
		products.add(p5);
		products.add(p6);
		products.add(p4);

		g.maximizeMyShopping(products, t);
		assertEquals(TestUtil.setOfProducts(p1), t.getProducts());
	
	}
}
