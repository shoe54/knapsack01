package nz.co.lingo.algos.knapsack01.examples.advertising;

import static org.junit.Assert.assertEquals;

import java.util.stream.Collectors;

import nz.co.lingo.algos.knapsack01.DefaultItem;
import nz.co.lingo.algos.knapsack01.DefaultPool;
import nz.co.lingo.algos.knapsack01.examples.shopping.TestUtil;

import org.junit.Test;

public class AppTest {

	@Test
	public void testGo() {
		App app = new App();
		DefaultPool result = app.go();
		assertEquals(TestUtil.setOfItems(new DefaultItem(60000, 150000)), 
				result.getItems().collect(Collectors.toSet()));
	}

}
