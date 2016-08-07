package com.lingo.redmart.totechallenge;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.BeforeClass;
import org.junit.Test;

import com.lingo.redmart.totechallenge.PriceWeightTuple;

public class PriceWeightTupleTest {
	static PriceWeightTuple<Integer> pInt;
	static PriceWeightTuple<Double> pDbl;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		pInt = new PriceWeightTuple<Integer>(100, 200);
		pDbl = new PriceWeightTuple<Double>(400.0d, 500.0d);
	}

	@Test
	public void testAdd() {
		assertEquals(
			new PriceWeightTuple<Integer>(300, 450), 
			pInt.add(new PriceWeightTuple<Integer>(200, 250))
		);
		assertEquals(
			new PriceWeightTuple<Integer>(0, 10), 
			pInt.add(new PriceWeightTuple<Integer>(-100, -190))
		);
		assertEquals(
			pInt, 
			pInt.add(PriceWeightTuple.ZERO)
		);

		assertEquals(
			new PriceWeightTuple<Integer>(300, 450), 
			pInt.add(200, 250)
		);
		assertEquals(
			new PriceWeightTuple<Integer>(0, 10), 
			pInt.add(-100, -190)
		);
		assertEquals(
			pInt, 
			pInt.add(PriceWeightTuple.ZERO)
		);

		assertEquals(
			new PriceWeightTuple<Double>(600.0d, 750.0d), 
			pDbl.add(new PriceWeightTuple<Double>(200.0d, 250.0d))
		);
		assertEquals(
			new PriceWeightTuple<Double>(300.0d, 310.d), 
			pDbl.add(new PriceWeightTuple<Double>(-100.0d, -190.0d))
		);
		assertEquals(
			pDbl, 
			pDbl.add(PriceWeightTuple.ZERO)
		);

		assertEquals(
			new PriceWeightTuple<Double>(600.0d, 750.0d), 
			pDbl.add(200.0d, 250.0d)
		);
		assertEquals(
			new PriceWeightTuple<Double>(300.0d, 310.d), 
			pDbl.add(-100.0d, -190.0d)
		);
		assertEquals(
			pDbl, 
			pDbl.add(PriceWeightTuple.ZERO)
		);
	}
	
	@Test
	public void testCompareTo() {
		assertEquals(0, pInt.compareTo(new PriceWeightTuple<Integer>(100, 200)));
		assertTrue(pInt.compareTo(new PriceWeightTuple<Integer>(101, 200)) < 0);
		assertTrue(pInt.compareTo(new PriceWeightTuple<Integer>(100, 199)) < 0);
		assertTrue(pInt.compareTo(new PriceWeightTuple<Integer>(99, 200)) > 0);
		assertTrue(pInt.compareTo(new PriceWeightTuple<Integer>(100, 201)) > 0);

		assertEquals(0, pDbl.compareTo(new PriceWeightTuple<Double>(400.0d, 500.0d)));
		assertTrue(pDbl.compareTo(new PriceWeightTuple<Double>(400.1d, 500.0d)) < 0);
		assertTrue(pDbl.compareTo(new PriceWeightTuple<Double>(400.0d, 499.9d)) < 0);
		assertTrue(pDbl.compareTo(new PriceWeightTuple<Double>(399.9d, 500.0d)) > 0);
		assertTrue(pDbl.compareTo(new PriceWeightTuple<Double>(400.0d, 500.1d)) > 0);
	}
	
	@Test
	public void testGetTotalPrice() {
		assertEquals(100, pInt.getPrice().intValue());

		assertEquals(400.0d, pDbl.getPrice().doubleValue(), 0.0d);
	}

	@Test
	public void testGetTotalWeight() {
		assertEquals(200, pInt.getWeight().intValue());

		assertEquals(500.0d, pDbl.getWeight().doubleValue(), 0.0d);
	}

	@Test
	public void testToDouble() {
		assertEquals(new PriceWeightTuple<Double>(100.0d, 200.0d), pInt.toDouble());
	}
}
