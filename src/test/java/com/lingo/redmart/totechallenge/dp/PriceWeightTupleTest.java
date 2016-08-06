package com.lingo.redmart.totechallenge.dp;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.BeforeClass;
import org.junit.Test;

public class PriceWeightTupleTest {
	static PriceWeightTuple p;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		p = new PriceWeightTuple(100, 200);
	}

	@Test
	public void testCompareTo() {
		assertEquals(0, p.compareTo(new PriceWeightTuple(100, 200)));
		assertTrue(p.compareTo(new PriceWeightTuple(101, 200)) < 0);
		assertTrue(p.compareTo(new PriceWeightTuple(100, 199)) < 0);
		assertTrue(p.compareTo(new PriceWeightTuple(99, 200)) > 0);
		assertTrue(p.compareTo(new PriceWeightTuple(100, 201)) > 0);
	}
	
	@Test
	public void testGetTotalPrice() {
		assertEquals(100, p.getTotalPrice());
	}

	@Test
	public void testGetTotalWeight() {
		assertEquals(200, p.getTotalWeight());
	}
}
