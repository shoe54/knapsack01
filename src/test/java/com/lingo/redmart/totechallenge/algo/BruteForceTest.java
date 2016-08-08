package com.lingo.redmart.totechallenge.algo;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import com.lingo.redmart.totechallenge.Product;
import com.lingo.redmart.totechallenge.TestUtil;
import com.lingo.redmart.totechallenge.Tote;

public class BruteForceTest {
	static BruteForce bf;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		bf = new BruteForce();
	}

	@Test
	public void testMaximizeMyShopping01() {
		List<Product> products = new ArrayList<>();
		Tote t = new Tote(10, 12, 14); // Volume: 1680
		
		Product p1 = new Product(31288, 1990, 15, 10, 10, 1584); // volume: 1500 price to volume: 1.326666667
		Product p2 = new Product(14301, 2139, 15, 13, 10, 804); // volume: 1950 price to volume: 1.096923077
		Product p3 = new Product(39987, 2229, 19, 11, 10, 1647); // volume: 2090 price to volume: 1.066507177
		Product p4 = new Product(26950, 2215, 15, 11, 13, 547); // volume: 2145 price to volume: 1.032634033
		Product p5 = new Product(10496, 2080, 17, 13, 10, 1417); // volume: 2210 price to volume: 0.941176471
		Product p6 = new Product(17788, 2509, 15, 10, 18, 2078); // volume: 2700 price to volume: 0.929259259
		Product p7 = new Product(5084, 1651, 15, 12, 10, 1160); // volume: 1800 price to volume: 0.917222222
		
		products.add(p1);
		products.add(p2);
		products.add(p3);
		products.add(p4);
		products.add(p5);
		products.add(p6);
		products.add(p7);

		bf.doMaximizeMyShopping(products, t);
		assertEquals(TestUtil.setOfProducts(p1), t.getProducts());
	}

	@Test
	public void testMaximizeMyShopping02() {
		Tote t = new Tote(5, 1, 2); // volume: 10
		List<Product> products = new ArrayList<>();

		Product p1 = new Product(1, 10, 1, 1, 5, 10); // volume: 5 
		Product p2 = new Product(2, 40, 1, 1, 4, 10); // volume: 4
		Product p3 = new Product(3, 30, 1, 1, 6, 10); // volume: 6
		Product p4 = new Product(4, 50, 1, 1, 3, 10); // volume: 3

		products.add(p1);
		products.add(p2);
		products.add(p3);
		products.add(p4);

		bf.doMaximizeMyShopping(products, t);
		assertEquals(TestUtil.setOfProducts(p2,p4), t.getProducts());
	}

	/**
	 * Test the weight tie breaker
	 */
	@Test
	public void testMaximizeMyShopping03() {
		Tote t = new Tote(5, 1, 2); // volume: 10
		List<Product> products = new ArrayList<>();

		Product p1 = new Product(1, 5, 1, 1, 10, 5); // volume: 10 
		Product p2 = new Product(2, 5, 1, 1, 10, 6); // volume: 10

		products.add(p1);
		products.add(p2);

		bf.doMaximizeMyShopping(products, t);
		assertEquals(TestUtil.setOfProducts(p1), t.getProducts());
		
		t.removeAllProducts();
		products.clear();
		products.add(p2);
		products.add(p1);
		
		bf.doMaximizeMyShopping(products, t);
		assertEquals(TestUtil.setOfProducts(p1), t.getProducts());
	}
	
	/**
	 * Test using data set and expected results from https://rosettacode.org/wiki/Knapsack_problem/0-1
	 */
	@Test
	public void testMaximizeMyShopping04() {
		Tote t = new Tote(10, 10, 4); // volume: 400
		List<Product> products = new ArrayList<>();

		Product p1 = new Product(1, 150, 1, 1, 9, 10); 
		Product p2 = new Product(2, 35, 1, 1, 13, 10); 
		Product p3 = new Product(3, 200, 1, 1, 153, 10); 
		Product p4 = new Product(4, 160, 1, 1, 50, 10); 
		Product p5 = new Product(5, 60, 1, 1, 15, 10); 
		Product p6 = new Product(6, 45, 1, 1, 68, 10); 
		Product p7 = new Product(7, 60, 1, 1, 27, 10); 
		Product p8 = new Product(8, 40, 1, 1, 39, 10); 
		Product p9 = new Product(9, 30, 1, 1, 23, 10); 
		Product p10 = new Product(10, 10, 1, 1, 52, 10); 
		Product p11 = new Product(11, 70, 1, 1, 11, 10); 
		Product p12 = new Product(12, 30, 1, 1, 32, 10); 
		Product p13 = new Product(13, 15, 1, 1, 24, 10); 
		Product p14 = new Product(14, 10, 1, 1, 48, 10); 
		Product p15 = new Product(15, 40, 1, 1, 73, 10); 
		Product p16 = new Product(16, 70, 1, 1, 42, 10); 
		Product p17 = new Product(17, 75, 1, 1, 43, 10); 
		Product p18 = new Product(18, 80, 1, 1, 22, 10); 
		Product p19 = new Product(19, 20, 1, 1, 7, 10); 
		Product p20 = new Product(20, 12, 1, 1, 18, 10); 
		Product p21 = new Product(21, 50, 1, 1, 4, 10); 
		Product p22 = new Product(22, 10, 1, 1, 30, 10); 

		products.add(p1);
		products.add(p2);
		products.add(p3);
		products.add(p4);
		products.add(p5);
		products.add(p6);
		products.add(p7);
		products.add(p8);
		products.add(p9);
		products.add(p10);
		products.add(p11);
		products.add(p12);
		products.add(p13);
		products.add(p14);
		products.add(p15);
		products.add(p16);
		products.add(p17);
		products.add(p18);
		products.add(p19);
		products.add(p20);
		products.add(p21);
		products.add(p22);

		bf.doMaximizeMyShopping(products, t);
		assertEquals(TestUtil.setOfProducts(p1,p2,p3,p4,p5,p7,p11,p16,p17,p18,p19,p21), t.getProducts());
	}

	/**
	 * Test with no products and just 1 product
	 */
	@Test
	public void testMaximizeMyShopping05() {
		List<Product> products = new ArrayList<>();
		Tote t = new Tote(1, 10, 1); // volume: 10

		bf.doMaximizeMyShopping(products, t);
		assertEquals(Collections.EMPTY_SET, t.getProducts());
	
		Product p1 = new Product(1, 20, 5, 2, 1, 1); // volume: 10
		products.add(p1);
		t.removeAllProducts();
		bf.doMaximizeMyShopping(products, t);
		assertEquals(TestUtil.setOfProducts(p1), t.getProducts());
	}

}
