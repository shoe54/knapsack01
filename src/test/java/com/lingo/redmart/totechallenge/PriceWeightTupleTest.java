package com.lingo.redmart.totechallenge;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.BeforeClass;
import org.junit.Test;

import com.lingo.redmart.totechallenge.PriceWeightTuple;

public class PriceWeightTupleTest {
	static PriceWeightTuple p;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		p = new PriceWeightTuple(100, 200);
	}

	@Test
	public void testAdd() {
		assertEquals(
			new PriceWeightTuple(300, 450), 
			p.add(new PriceWeightTuple(200, 250)
		));
		assertEquals(
			new PriceWeightTuple(0, 10), 
			p.add(new PriceWeightTuple(-100, -190)
		));
		assertEquals(
			p, 
			p.add(PriceWeightTuple.ZERO
		));
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
