package nz.co.lingo.algos.knapsack01.examples.knapsack;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.util.Set;
import java.util.stream.Collectors;

import nz.co.lingo.algos.knapsack01.Item;
import nz.co.lingo.algos.knapsack01.examples.shopping.TestUtil;

import org.junit.Test;

public class AppTest {

	@Test
	public void testGo() {
		App app = new App();
		Set<Item<?, ?>> expected = TestUtil.setOfItems(
				new Gear(1000, new BigDecimal("25.00")),
				new Gear(1000, new BigDecimal("1.50")),
				new Gear(1000, new BigDecimal("12.75")),
				new Gear(2000, new BigDecimal("9.99")));
		app.goBruteForce(app.knapsack);
		assertEquals(expected, app.knapsack.getItems().collect(Collectors.toSet()));
		
		app.knapsack.removeAllItems();
		
		app.goDynamicProgramming(app.knapsack);
		assertEquals(expected, app.knapsack.getItems().collect(Collectors.toSet()));

		app.knapsack.removeAllItems();
		
		app.goBranchAndBound(app.knapsack);
		assertEquals(expected, app.knapsack.getItems().collect(Collectors.toSet()));
	}

}
