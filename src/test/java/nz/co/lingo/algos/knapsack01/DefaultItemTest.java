package nz.co.lingo.algos.knapsack01;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import org.junit.BeforeClass;
import org.junit.Test;

public class DefaultItemTest {
	static DefaultItem di1, di2, di3, di4;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		di1 = new DefaultItem(10, 5);
		di2 = new DefaultItem(5, 10);
		di3 = new DefaultItem(9, 9);
		di4 = new DefaultItem(9, 9);
	}

	@Test
	public void testHashCode() {
		assertEquals(di3.hashCode(), di4.hashCode());
		assertNotEquals(di1.hashCode(), di2.hashCode());
		assertNotEquals(di2.hashCode(), di3.hashCode());
	}

	@Test
	public void testDefaultItem01() {
		assertEquals(10, di1.cost);
		assertEquals(5, di2.cost);
		assertEquals(9, di3.cost);
		assertEquals(9, di4.cost);
		assertEquals(new Integer(5), di1.value);
		assertEquals(new Integer(10), di2.value);
		assertEquals(new Integer(9), di3.value);
		assertEquals(new Integer(9), di4.value);
	}

	@Test(expected=IllegalArgumentException.class)
	public void testDefaultItem02() {
		new DefaultItem(9, null);
	}

	@Test
	public void testGetCost() {
		assertEquals(10, di1.getCost());
		assertEquals(5, di2.getCost());
		assertEquals(9, di3.getCost());
		assertEquals(9, di4.getCost());
	}

	@Test
	public void testGetValue() {
		assertEquals(new Integer(5), di1.getValue());
		assertEquals(new Integer(10), di2.getValue());
		assertEquals(new Integer(9), di3.getValue());
		assertEquals(new Integer(9), di4.getValue());
	}

	@Test
	public void testValueToDouble01() {
		assertEquals(1.0d, di1.valueToDouble(1));
		assertEquals(-2.0d, di1.valueToDouble(-2));
		assertEquals(0.0d, di1.valueToDouble(0));
		assertEquals(67.0d, di1.valueToDouble(67));
	}

	@Test(expected=NullPointerException.class)
	public void testValueToDouble02() {
		di1.valueToDouble(null);
	}

	@Test
	public void testGetValueZero() {
		assertEquals(new Integer(0), di1.getValueZero());
	}

	@Test
	public void testAddValue01() {
		assertEquals(new Integer(17), di1.addValue(12));
		assertEquals(new Integer(22), di2.addValue(12));
		assertEquals(new Integer(21), di3.addValue(12));
		assertEquals(new Integer(21), di4.addValue(12));
	}

	@Test(expected=NullPointerException.class)
	public void testAddValue02() {
		di1.addValue(null);
	}

	@Test
	public void testAddValues01() {
		assertEquals(new Integer(1), di1.addValues(1, 0));
		assertEquals(new Integer(0), di1.addValues(0, 0));
		assertEquals(new Integer(-4), di1.addValues(-4, 0));
		assertEquals(new Integer(20), di1.addValues(1, 19));
	}

	@Test(expected=NullPointerException.class)
	public void testAddValues02() {
		di1.addValues(1, null);
	}

	@Test(expected=NullPointerException.class)
	public void testAddValues03() {
		di1.addValues(null, 1);
	}

	@Test
	public void testMultiplyValue() {
		assertEquals(new Integer(15), di1.multiplyValue(3));
		assertEquals(new Integer(20), di2.multiplyValue(2));
		assertEquals(new Integer(0), di3.multiplyValue(0));
		assertEquals(new Integer(-9), di4.multiplyValue(-1));
	}

	@Test
	public void testDivideByCost01() {
		assertEquals(new Integer(3), di1.divideByCost(30));
		assertEquals(new Integer(2), di2.divideByCost(10));
		assertEquals(new Integer(1), di3.divideByCost(9));
		assertEquals(new Integer(0), di4.divideByCost(0));
	}

	@Test(expected=NullPointerException.class)
	public void testDivideByCost02() {
		di1.divideByCost(null);
	}

	@Test
	public void testEqualsObject() {
		assertFalse(di1.equals(di2));
		assertFalse(di2.equals(di3));
		assertTrue(di3.equals(di4));
		assertTrue(di4.equals(di3));
		assertFalse(di4.equals(null));
		assertFalse(di4.equals(new Integer(9)));
	}

}
