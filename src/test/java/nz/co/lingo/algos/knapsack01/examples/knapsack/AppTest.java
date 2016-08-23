package nz.co.lingo.algos.knapsack01.examples.knapsack;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.util.stream.Collectors;

import nz.co.lingo.algos.knapsack01.examples.shopping.TestUtil;

import org.junit.Test;

public class AppTest {

	@Test
	public void testGo() {
		App app = new App();
		Knapsack result = app.go();
		assertEquals(TestUtil.setOfItems(
				new Gear(1000, new BigDecimal("25.00"))), 
				result.getItems().collect(Collectors.toSet()));
	}

}
