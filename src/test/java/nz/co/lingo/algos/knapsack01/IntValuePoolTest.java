package nz.co.lingo.algos.knapsack01;

import static org.junit.Assert.*;

import java.util.Collections;
import java.util.Comparator;
import java.util.stream.Collectors;

import nz.co.lingo.algos.knapsack01.examples.shopping.TestUtil;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class IntValuePoolTest {
	static IntValuePool t1, t2, t3, t4, t5, t6, t7, t8, t9;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		t1 = new IntValuePool(10);
		t2 = new IntValuePool(5);
		t3 = new IntValuePool(9);
		t4 = new IntValuePool(9);
		t5 = new IntValuePool(11);
		t5.addItem(new IntValueItem(11, 12));
		t6 = new IntValuePool(11);
		t6.addItem(new IntValueItem(11, 12));
		t7 = new IntValuePool(12);
		t7.addItem(new IntValueItem(12, 13));
		t8 = new IntValuePool(12);
		t8.addItem(new IntValueItem(2, 2));
		t9 = new IntValuePool(19);
		t9.addItem(new IntValueItem(12, 13));
	}

	@Test
	public void testHashCode() {
		assertNotEquals(t1, t2);
		assertNotEquals(t2, t3);
		assertEquals(t3, t4);
		assertEquals(t4, t3);
		assertNotEquals(t4, t5);
		assertEquals(t5, t6);
		assertNotEquals(t6, t7);
	}

	@Test
	public void testDefaultPool() {
		assertEquals(10, t1.allowedCost);
		assertEquals(5, t2.allowedCost);
		assertEquals(9, t3.allowedCost);
		assertEquals(9, t4.allowedCost);
		assertEquals(11, t5.allowedCost);
		assertEquals(11, t6.allowedCost);
		assertEquals(12, t7.allowedCost);
		assertEquals(Collections.EMPTY_SET, t1.items);
		assertEquals(Collections.EMPTY_SET, t2.items);
		assertEquals(Collections.EMPTY_SET, t3.items);
		assertEquals(Collections.EMPTY_SET, t4.items);
		assertEquals(TestUtil.setOfItems(new IntValueItem(11, 12)), t5.items);
		assertEquals(TestUtil.setOfItems(new IntValueItem(11, 12)), t6.items);
		assertEquals(TestUtil.setOfItems(new IntValueItem(12, 13)), t7.items);
	}

	@Test
	public void testAddItem01() {
		IntValuePool dpTest = new IntValuePool(10);
		boolean ret;
		ret = dpTest.addItem(new IntValueItem(5, 6));
		assertEquals(TestUtil.setOfItems(new IntValueItem(5, 6)), dpTest.items);
		assertTrue(ret);
		ret = dpTest.addItem(new IntValueItem(3, 4));
		assertEquals(TestUtil.setOfItems(
				new IntValueItem(3, 4), new IntValueItem(5, 6)), dpTest.items);
		assertTrue(ret);
		ret = dpTest.addItem(new IntValueItem(1, 1));
		assertEquals(TestUtil.setOfItems(
				new IntValueItem(3, 4), new IntValueItem(5, 6), new IntValueItem(1, 1)), dpTest.items);
		assertTrue(ret);
		ret = dpTest.addItem(new IntValueItem(3, 3));
		assertEquals(TestUtil.setOfItems(
				new IntValueItem(3, 4), new IntValueItem(5, 6), new IntValueItem(1, 1)), dpTest.items);
		assertFalse(ret);
	}

	@Test(expected=IllegalArgumentException.class)
	public void testAddItem02() {
		t1.addItem(null);
	}

	@Test
	public void testGetUsedCost() {
		IntValuePool dpTest = new IntValuePool(10);
		assertEquals(0, dpTest.getUsedCost());
		dpTest.addItem(new IntValueItem(2, 2));
		assertEquals(2, dpTest.getUsedCost());
		dpTest.addItem(new IntValueItem(3, 3));
		assertEquals(5, dpTest.getUsedCost());
		dpTest.addItem(new IntValueItem(7, 7));
		assertEquals(5, dpTest.getUsedCost());
	}

	@Test
	public void testGetValueToCostRatioComparator() {
		Comparator<IntValueItem> comparator = t1.getValueToCostRatioComparator();
		assertEquals(0, comparator.compare(new IntValueItem(11, 12), new IntValueItem(11, 12)));
		assertTrue(comparator.compare(new IntValueItem(11, 13), new IntValueItem(11, 12)) > 0);
		assertTrue(comparator.compare(new IntValueItem(11, 11), new IntValueItem(11, 12)) < 0);
	}

	@Test
	public void testGetAllowedCost() {
		assertEquals(10, t1.getAllowedCost());
		assertEquals(5, t2.getAllowedCost());
		assertEquals(9, t3.getAllowedCost());
		assertEquals(9, t4.getAllowedCost());
		assertEquals(11, t5.getAllowedCost());
		assertEquals(11, t6.getAllowedCost());
		assertEquals(12, t7.getAllowedCost());
	}

	@Test
	public void testGetItems() {
		assertEquals(Collections.EMPTY_SET, t1.getItems().collect(Collectors.toSet()));
		assertEquals(TestUtil.setOfItems(new IntValueItem(11, 12)), t5.getItems().collect(Collectors.toSet()));
		assertEquals(TestUtil.setOfItems(new IntValueItem(12, 13)), t7.getItems().collect(Collectors.toSet()));
	}

	@Test
	public void testEqualsObject() {
		assertFalse(t1.equals(t2));
		assertFalse(t2.equals(t3));
		assertTrue(t3.equals(t4));
		assertTrue(t4.equals(t3));
		assertFalse(t4.equals(t5));
		assertTrue(t5.equals(t6));
		assertFalse(t6.equals(t7));
		assertFalse(t7.equals(t8));
		assertFalse(t7.equals(t9));
		assertFalse(t9.equals(t7));
	}

}
