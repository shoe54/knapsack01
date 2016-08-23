package nz.co.lingo.algos.knapsack01;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class AbstractNumberItemTest {
	public static class Target extends AbstractNumberItem<Double> {
		public Target(int cost, Double value) {
			super(cost, value);
		}
		@Override
		public Double getValueZero() {
			throw new RuntimeException();
		}
		@Override
		public Double addValues(Double a, Double b) {
			throw new RuntimeException();
		}
		@Override
		public Double multiplyValue(int multiplier) {
			throw new RuntimeException();
		}
		@Override
		public Double divideByCost(Double top) {
			throw new RuntimeException();
		}
		@Override
		public Double addDouble(Double d) {
			throw new RuntimeException();
		}
	}
	static Target t1, t2, t3, t4, t5;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		t1 = new Target(10, 5.0);
		t2 = new Target(5, 10.0);
		t3 = new Target(9, 9.0);
		t4 = new Target(9, 9.0);
		t5 = new Target(5, 10.5);
	}

	@Test
	public void testHashCode() {
		assertEquals(t3.hashCode(), t4.hashCode());
		assertNotEquals(t1.hashCode(), t2.hashCode());
		assertNotEquals(t2.hashCode(), t3.hashCode());
		assertNotEquals(t2.hashCode(), t5.hashCode());
	}

	@Test
	public void testAbstractNumberItem01() {
		assertEquals(10, t1.cost);
		assertEquals(5, t2.cost);
		assertEquals(9, t3.cost);
		assertEquals(9, t4.cost);
		assertEquals(new Double(5.0), t1.value);
		assertEquals(new Double(10.0), t2.value);
		assertEquals(new Double(9.0), t3.value);
		assertEquals(new Double(9.0), t4.value);
		assertEquals(new Double(10.5), t5.value);
	}

	@Test(expected=IllegalArgumentException.class)
	public void testAbstractNumberItem02() {
		new Target(9, null);
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
		assertEquals(new Double(5.0), t1.getValue());
		assertEquals(new Double(10.0), t2.getValue());
		assertEquals(new Double(9.0), t3.getValue());
		assertEquals(new Double(9.0), t4.getValue());
		assertEquals(new Double(10.5), t5.getValue());
	}

	@Test
	public void testValueToDouble01() {
		assertEquals(new Double(1.0), t1.valueToDouble(1.0));
		assertEquals(new Double(-2.0d), t1.valueToDouble(-2.0));
		assertEquals(new Double(0.0d), t1.valueToDouble(0.0));
		assertEquals(new Double(67.0d), t1.valueToDouble(67.0));
		assertEquals(new Double(3.334d), t1.valueToDouble(3.334));
	}

	@Test(expected=NullPointerException.class)
	public void testValueToDouble02() {
		t1.valueToDouble(null);
	}

	@Test
	public void testEqualsObject() {
		assertFalse(t1.equals(t2));
		assertFalse(t2.equals(t3));
		assertTrue(t3.equals(t4));
		assertTrue(t4.equals(t3));
		assertFalse(t4.equals(null));
		assertFalse(t4.equals(new Double(9.0)));
		assertFalse(t2.equals(t5));
	}

}
