package nz.co.lingo.algos.knapsack01;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import org.junit.BeforeClass;
import org.junit.Test;

public class DefaultItemTest {
	static DefaultItem t1, t2, t3, t4;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		t1 = new DefaultItem(10, 5);
		t2 = new DefaultItem(5, 10);
		t3 = new DefaultItem(9, 9);
		t4 = new DefaultItem(9, 9);
	}

	@Test
	public void testHashCode() {
		assertEquals(t3.hashCode(), t4.hashCode());
		assertNotEquals(t1.hashCode(), t2.hashCode());
		assertNotEquals(t2.hashCode(), t3.hashCode());
	}

	@Test
	public void testDefaultItem01() {
		assertEquals(10, t1.cost);
		assertEquals(5, t2.cost);
		assertEquals(9, t3.cost);
		assertEquals(9, t4.cost);
		assertEquals(new Integer(5), t1.value);
		assertEquals(new Integer(10), t2.value);
		assertEquals(new Integer(9), t3.value);
		assertEquals(new Integer(9), t4.value);
	}

	@Test(expected=IllegalArgumentException.class)
	public void testDefaultItem02() {
		new DefaultItem(9, null);
	}

	@Test(expected=IllegalArgumentException.class)
	public void testDefaultItem03() {
		new DefaultItem(-1, 1);
	}

	@Test(expected=IllegalArgumentException.class)
	public void testDefaultItem04() {
		new DefaultItem(1, -1);
	}

	@Test
	public void testGetCost() {
		assertEquals(10, t1.getCost());
		assertEquals(5, t2.getCost());
		assertEquals(9, t3.getCost());
		assertEquals(9, t4.getCost());
	}

	@Test
	public void testGetValue() {
		assertEquals(new Integer(5), t1.getValue());
		assertEquals(new Integer(10), t2.getValue());
		assertEquals(new Integer(9), t3.getValue());
		assertEquals(new Integer(9), t4.getValue());
	}

	@Test
	public void testValueToDouble01() {
		assertEquals(new Double(1.0d), t1.valueToDouble(1));
		assertEquals(new Double(-2.0d), t1.valueToDouble(-2));
		assertEquals(new Double(0.0d), t1.valueToDouble(0));
		assertEquals(new Double(67.0d), t1.valueToDouble(67));
	}

	@Test(expected=NullPointerException.class)
	public void testValueToDouble02() {
		t1.valueToDouble(null);
	}

	@Test
	public void testGetValueZero() {
		assertEquals(new Integer(0), t1.getValueZero());
	}

	@Test
	public void testAddValue01() {
		assertEquals(new Integer(17), t1.addValue(12));
		assertEquals(new Integer(22), t2.addValue(12));
		assertEquals(new Integer(21), t3.addValue(12));
		assertEquals(new Integer(21), t4.addValue(12));
	}

	@Test(expected=NullPointerException.class)
	public void testAddValue02() {
		t1.addValue(null);
	}

	@Test
	public void testAddValues01() {
		assertEquals(new Integer(1), t1.addValues(1, 0));
		assertEquals(new Integer(0), t1.addValues(0, 0));
		assertEquals(new Integer(-4), t1.addValues(-4, 0));
		assertEquals(new Integer(20), t1.addValues(1, 19));
	}

	@Test(expected=NullPointerException.class)
	public void testAddValues02() {
		t1.addValues(1, null);
	}

	@Test(expected=NullPointerException.class)
	public void testAddValues03() {
		t1.addValues(null, 1);
	}

	@Test
	public void testMultiplyValue01() {
		assertEquals(new Integer(15), t1.multiplyValue(3));
		assertEquals(new Integer(20), t2.multiplyValue(2));
		assertEquals(new Integer(0), t3.multiplyValue(0));
		assertEquals(new Integer(-9), t4.multiplyValue(-1));
	}

	@Test(expected=ArithmeticException.class)
	public void testMultiplyValue02() {
		new DefaultItem(10, 999999999).multiplyValue(99999999);
	}

	@Test
	public void testDivideByCost01() {
		assertEquals(new Double(3), t1.divideByCost(30));
		assertEquals(new Double(2), t2.divideByCost(10));
		assertEquals(new Double(1), t3.divideByCost(9));
		assertEquals(new Double(0), t4.divideByCost(0));
	}

	@Test(expected=NullPointerException.class)
	public void testDivideByCost02() {
		t1.divideByCost(null);
	}

	@Test
	public void testEqualsObject() {
		assertFalse(t1.equals(t2));
		assertFalse(t2.equals(t3));
		assertTrue(t3.equals(t4));
		assertTrue(t4.equals(t3));
		assertFalse(t4.equals(null));
		assertFalse(t4.equals(new Integer(9)));
	}

}
