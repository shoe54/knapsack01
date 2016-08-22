package nz.co.lingo.algos.knapsack01;

import static org.junit.Assert.*;

import java.util.Collections;
import java.util.Comparator;
import java.util.stream.Collectors;

import nz.co.lingo.algos.knapsack01.examples.shopping.TestUtil;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class DefaultPoolTest {
	static DefaultPool dp1, dp2, dp3, dp4, dp5, dp6, dp7, dp8, dp9;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		dp1 = new DefaultPool(10);
		dp2 = new DefaultPool(5);
		dp3 = new DefaultPool(9);
		dp4 = new DefaultPool(9);
		dp5 = new DefaultPool(11);
		dp5.addItem(new DefaultItem(11, 12));
		dp6 = new DefaultPool(11);
		dp6.addItem(new DefaultItem(11, 12));
		dp7 = new DefaultPool(12);
		dp7.addItem(new DefaultItem(12, 13));
		dp8 = new DefaultPool(12);
		dp8.addItem(new DefaultItem(2, 2));
		dp9 = new DefaultPool(19);
		dp9.addItem(new DefaultItem(12, 13));
	}

	@Test
	public void testHashCode() {
		assertNotEquals(dp1, dp2);
		assertNotEquals(dp2, dp3);
		assertEquals(dp3, dp4);
		assertEquals(dp4, dp3);
		assertNotEquals(dp4, dp5);
		assertEquals(dp5, dp6);
		assertNotEquals(dp6, dp7);
	}

	@Test
	public void testDefaultPool() {
		assertEquals(10, dp1.allowedCost);
		assertEquals(5, dp2.allowedCost);
		assertEquals(9, dp3.allowedCost);
		assertEquals(9, dp4.allowedCost);
		assertEquals(11, dp5.allowedCost);
		assertEquals(11, dp6.allowedCost);
		assertEquals(12, dp7.allowedCost);
		assertEquals(Collections.EMPTY_SET, dp1.items);
		assertEquals(Collections.EMPTY_SET, dp2.items);
		assertEquals(Collections.EMPTY_SET, dp3.items);
		assertEquals(Collections.EMPTY_SET, dp4.items);
		assertEquals(TestUtil.setOfDefaultItems(new DefaultItem(11, 12)), dp5.items);
		assertEquals(TestUtil.setOfDefaultItems(new DefaultItem(11, 12)), dp6.items);
		assertEquals(TestUtil.setOfDefaultItems(new DefaultItem(12, 13)), dp7.items);
	}

	@Test
	public void testAddItem01() {
		DefaultPool dpTest = new DefaultPool(10);
		boolean ret;
		ret = dpTest.addItem(new DefaultItem(5, 6));
		assertEquals(TestUtil.setOfDefaultItems(new DefaultItem(5, 6)), dpTest.items);
		assertTrue(ret);
		ret = dpTest.addItem(new DefaultItem(3, 4));
		assertEquals(TestUtil.setOfDefaultItems(
				new DefaultItem(3, 4), new DefaultItem(5, 6)), dpTest.items);
		assertTrue(ret);
		ret = dpTest.addItem(new DefaultItem(1, 1));
		assertEquals(TestUtil.setOfDefaultItems(
				new DefaultItem(3, 4), new DefaultItem(5, 6), new DefaultItem(1, 1)), dpTest.items);
		assertTrue(ret);
		ret = dpTest.addItem(new DefaultItem(3, 3));
		assertEquals(TestUtil.setOfDefaultItems(
				new DefaultItem(3, 4), new DefaultItem(5, 6), new DefaultItem(1, 1)), dpTest.items);
		assertFalse(ret);
	}

	@Test(expected=IllegalArgumentException.class)
	public void testAddItem02() {
		dp1.addItem(null);
	}

	@Test
	public void testGetUsedCost() {
		DefaultPool dpTest = new DefaultPool(10);
		assertEquals(0, dpTest.getUsedCost());
		dpTest.addItem(new DefaultItem(2, 2));
		assertEquals(2, dpTest.getUsedCost());
		dpTest.addItem(new DefaultItem(3, 3));
		assertEquals(5, dpTest.getUsedCost());
		dpTest.addItem(new DefaultItem(7, 7));
		assertEquals(5, dpTest.getUsedCost());
	}

	@Test
	public void testGetValueToCostRatioComparator() {
		Comparator<DefaultItem> comparator = dp1.getValueToCostRatioComparator();
		assertEquals(0, comparator.compare(new DefaultItem(11, 12), new DefaultItem(11, 12)));
		assertTrue(comparator.compare(new DefaultItem(11, 13), new DefaultItem(11, 12)) > 0);
		assertTrue(comparator.compare(new DefaultItem(11, 11), new DefaultItem(11, 12)) < 0);
	}

	@Test
	public void testGetAllowedCost() {
		assertEquals(10, dp1.getAllowedCost());
		assertEquals(5, dp2.getAllowedCost());
		assertEquals(9, dp3.getAllowedCost());
		assertEquals(9, dp4.getAllowedCost());
		assertEquals(11, dp5.getAllowedCost());
		assertEquals(11, dp6.getAllowedCost());
		assertEquals(12, dp7.getAllowedCost());
	}

	@Test
	public void testGetItems() {
		assertEquals(Collections.EMPTY_SET, dp1.getItems().collect(Collectors.toSet()));
		assertEquals(TestUtil.setOfDefaultItems(new DefaultItem(11, 12)), dp5.getItems().collect(Collectors.toSet()));
		assertEquals(TestUtil.setOfDefaultItems(new DefaultItem(12, 13)), dp7.getItems().collect(Collectors.toSet()));
	}

	@Test
	public void testEqualsObject() {
		assertFalse(dp1.equals(dp2));
		assertFalse(dp2.equals(dp3));
		assertTrue(dp3.equals(dp4));
		assertTrue(dp4.equals(dp3));
		assertFalse(dp4.equals(dp5));
		assertTrue(dp5.equals(dp6));
		assertFalse(dp6.equals(dp7));
		assertFalse(dp7.equals(dp8));
		assertFalse(dp7.equals(dp9));
		assertFalse(dp9.equals(dp7));
	}

}
