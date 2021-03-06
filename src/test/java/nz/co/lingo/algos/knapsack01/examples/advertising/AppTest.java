package nz.co.lingo.algos.knapsack01.examples.advertising;

import static org.junit.Assert.assertEquals;

import java.util.Set;
import java.util.stream.Collectors;

import nz.co.lingo.algos.knapsack01.IntValueItem;
import nz.co.lingo.algos.knapsack01.Item;
import nz.co.lingo.algos.knapsack01.examples.shopping.TestUtil;

import org.junit.Test;

public class AppTest {

	@Test
	public void testGo() {
		App app = new App();
		Set<Item<?, ?>> expected = TestUtil.setOfItems(new IntValueItem(40000, 15000));
		app.goBruteForce(app.budget);
		assertEquals(expected, app.budget.getItems().collect(Collectors.toSet()));
		
		app.budget.removeAllItems();

		app.goDynamicProgramming(app.budget);
		assertEquals(expected, app.budget.getItems().collect(Collectors.toSet()));

		app.budget.removeAllItems();
		
		app.goBranchAndBound(app.budget);
		assertEquals(expected, app.budget.getItems().collect(Collectors.toSet()));
	}

}
