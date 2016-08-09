package com.lingo.redmart.totechallenge;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.BeforeClass;
import org.junit.Test;

public class CuboidTest {
	static Cuboid c;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		c = new Cuboid(20, 10, 30);
	}

	@Test
	public void testCanFitInto() {
		// Test dimensions are equal but in potentially different order
		assertTrue(new Cuboid(10, 20, 30).canFitInto(c));
		assertTrue(new Cuboid(30, 20, 10).canFitInto(c));
		assertTrue(new Cuboid(10, 30, 20).canFitInto(c));
		assertTrue(new Cuboid(20, 30, 10).canFitInto(c));

		// Test one dimension slightly larger and the other 2 slightly smaller
		assertFalse(new Cuboid(29, 19, 11).canFitInto(c));
		assertFalse(new Cuboid(21, 29, 9).canFitInto(c));
		assertFalse(new Cuboid(31, 19, 9).canFitInto(c));

		// Test one dimension slightly larger and the other 2 much smaller
		assertFalse(new Cuboid(29, 19, 11).canFitInto(c));
		assertFalse(new Cuboid(21, 29, 9).canFitInto(c));
		assertFalse(new Cuboid(31, 19, 9).canFitInto(c));
		
		// Test 2 dimensions averaged
		assertFalse(new Cuboid(30, 15, 15).canFitInto(c));
		assertFalse(new Cuboid(20, 20, 20).canFitInto(c));
		assertFalse(new Cuboid(25, 25, 10).canFitInto(c));
		
		// Other tests
		assertTrue(new Cuboid(11, 10, 10).canFitInto(c));
		assertFalse(new Cuboid(11, 11, 20).canFitInto(c));
		assertTrue(new Cuboid(11, 11, 10).canFitInto(c));		
	}

	@Test
	public void testGetLength() {
		assertEquals(20, c.getLength());
	}

	@Test
	public void testGetWidth() {
		assertEquals(10, c.getWidth());
	}

	@Test
	public void testGetHeight() {
		assertEquals(30, c.getHeight());
	}

	@Test
	public void testGetVolume() {
		assertEquals(6000, c.getVolume());
	}

}
