package com.lingo.redmart.totechallenge;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class ToteTest {
	Tote t;
	Product p1, p2, p3, p4, p5, p6;
	
	@Before
	public void setUp() throws Exception {
		t = new Tote(45, 30, 35);
		p1 = new Product(1, 100, 5, 4, 2, 50);
		p2 = new Product(2, 200, 10, 3, 6, 60);
		p3 = new Product(3, 400, 1, 8, 2, 40);
		p4 = new Product(4, 999, 50, 40, 20, 1000);
		p5 = new Product(5, 500, 50, 40, 20, 1200);
		p6 = new Product(5, 500, 1, 2, 3507, 600);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testProductInvalidLength() {
		new Tote(0, 30, 30);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testProductInvalidWidth() {
		new Tote(20, 0, 30);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testProductInvalidHeight() {
		new Tote(20, 30, 0);
	}
	
	@Test
	public void testStoresProductsByReference() {
		t.addProduct(p1);
		assertSame(p1, t.products.iterator().next());
		assertSame(p1, t.getProducts().iterator().next());
	}

	@Test(expected=IllegalArgumentException.class)
	public void testCanAccommodateProductNull() {
		t.canAccommodateProduct(null);
	}

	@Test
	public void testCanAccommodateProduct() {
		assertTrue(t.canAccommodateProduct(p1));
		
		t.addProduct(p1);
		assertTrue(t.canAccommodateProduct(p2));
		
		t.addProduct(p2);
		assertTrue(t.canAccommodateProduct(p3));

		t.addProduct(p3);
		assertTrue(t.canAccommodateProduct(p4));

		t.addProduct(p4);
		assertFalse(t.canAccommodateProduct(p5));
		assertTrue(t.canAccommodateProduct(p6));
	}

	@Test
	public void testGetOccupiedVolume() {
		assertEquals(0, t.getOccupiedVolume());
		
		t.addProduct(p1);
		assertEquals(40, t.getOccupiedVolume());
		
		t.addProduct(p2);
		assertEquals(220, t.getOccupiedVolume());

		t.addProduct(p3);
		assertEquals(236, t.getOccupiedVolume());

		t.addProduct(p4);
		assertEquals(40236, t.getOccupiedVolume());

		t.addProduct(p5);
		assertEquals(40236, t.getOccupiedVolume());

		t.addProduct(p6);
		assertEquals(47250, t.getOccupiedVolume());
	}

	@Test
	public void testGetTotalPrice() {
		assertEquals(0, t.getTotalPrice());
		
		t.addProduct(p1);
		assertEquals(100, t.getTotalPrice());
		
		t.addProduct(p2);
		assertEquals(300, t.getTotalPrice());

		t.addProduct(p3);
		assertEquals(700, t.getTotalPrice());

		t.addProduct(p4);
		assertEquals(1699, t.getTotalPrice());

		t.addProduct(p5);
		assertEquals(1699, t.getTotalPrice());

		t.addProduct(p6);
		assertEquals(2199, t.getTotalPrice());
	}

	@Test(expected=IllegalArgumentException.class)
	public void testAddProductNull() {
		t.addProduct(null);
	}

	@Test
	public void testAddProduct() {
		boolean ret;
		
		ret = t.addProduct(p1);
		assertTrue(ret);
		assertEquals(TestUtil.setOfProducts(p1), t.products);
		
		ret = t.addProduct(p2);
		assertTrue(ret);
		assertEquals(TestUtil.setOfProducts(p1,p2), t.products);
		
		ret = t.addProduct(p3);
		assertTrue(ret);
		assertEquals(TestUtil.setOfProducts(p1,p2,p3), t.products);
		
		ret = t.addProduct(p4);
		assertTrue(ret);
		assertEquals(TestUtil.setOfProducts(p1,p2,p3,p4), t.products);
		
		ret = t.addProduct(p5);
		assertFalse(ret);
		assertEquals(TestUtil.setOfProducts(p1,p2,p3,p4), t.products);

		ret = t.addProduct(p6);
		assertTrue(ret);
		assertEquals(TestUtil.setOfProducts(p1,p2,p3,p4,p5,p6), t.products);
	}

	@Test
	public void testIsFull() {
		t.addProduct(p1);
		assertFalse(t.isFull());
		
		t.addProduct(p2);
		assertFalse(t.isFull());

		t.addProduct(p3);
		assertFalse(t.isFull());

		t.addProduct(p4);
		assertFalse(t.isFull());

		t.addProduct(p5);
		assertFalse(t.isFull());

		t.addProduct(p6);
		assertTrue(t.isFull());
	}

	@Test
	public void testRemoveAllProducts() {
		t.addProduct(p1);
		t.addProduct(p2);
		t.addProduct(p3);
		t.addProduct(p4);
		t.addProduct(p5);
		t.addProduct(p6);
		t.removeAllProducts();
		assertEquals(0, t.products.size());
	}

	@Test
	public void testGetLength() {
		assertEquals(45, t.getLength());
	}

	@Test
	public void testGetWidth() {
		assertEquals(30, t.getWidth());
	}

	@Test
	public void testGetHeight() {
		assertEquals(35, t.getHeight());
	}

	@Test
	public void testGetVolume() {
		assertEquals(47250, t.getVolume());
	}

}
