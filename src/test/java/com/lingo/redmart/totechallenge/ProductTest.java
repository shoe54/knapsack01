package com.lingo.redmart.totechallenge;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;

import com.lingo.redmart.totechallenge.Product;

public class ProductTest {
	static Product p;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		p = new Product(2, 100, 50, 40, 20, 500);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testProductInvalidPrice() {
		new Product(2, -1, 50, 40, 20, 500);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testProductInvalidLength() {
		new Product(2, 100, 0, 40, 20, 500);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testProductInvalidWidth() {
		new Product(2, 100, 50, 0, 20, 500);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testProductInvalidHeight() {
		new Product(2, 100, 50, 40, 0, 500);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testProductInvalidWeight() {
		new Product(2, 100, 50, 40, 20, 0);
	}
	
	@Test
	public void testGetId() {
		assertEquals(2, p.getId());
	}

	@Test
	public void testGetPrice() {
		assertEquals(100, p.getPrice());
	}

	@Test
	public void testGetWeight() {
		assertEquals(500, p.getWeight());
	}

	@Test
	public void testGetPriceToVolumeRatio() {
		assertEquals(0.0025d, p.getPriceToVolumeRatio(), 0.0d);
	}

	@Test
	public void testGetLength() {
		assertEquals(50, p.getLength());
	}

	@Test
	public void testGetWidth() {
		assertEquals(40, p.getWidth());
	}

	@Test
	public void testGetHeight() {
		assertEquals(20, p.getHeight());
	}

	@Test
	public void testGetVolume() {
		assertEquals(40000, p.getVolume());
	}

}
