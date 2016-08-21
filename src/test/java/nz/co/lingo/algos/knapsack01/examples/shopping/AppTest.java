package nz.co.lingo.algos.knapsack01.examples.shopping;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.List;

import nz.co.lingo.algos.knapsack01.examples.shopping.App;
import nz.co.lingo.algos.knapsack01.examples.shopping.Product;
import nz.co.lingo.algos.knapsack01.examples.shopping.Tote;
import nz.co.lingo.algos.knapsack01.examples.shopping.App.DoesProductFitIntoEmptyTote;

import org.junit.BeforeClass;
import org.junit.Test;

public class AppTest {
	static App a;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		a = new App();
	}
	
	/**
	 * Verified these results using Excel
	 * @throws IOException
	 */
	@Test
	public void testReadProductsFromCSV() throws IOException {
		Tote tote = new Tote(45, 30, 35);
		List<Product> products;

		products = a.readProductsFromCSV("src/test/resources/products.csv", null);
		assertEquals(20000, products.size());
		
		products = a.readProductsFromCSV("src/test/resources/products.csv", new DoesProductFitIntoEmptyTote(tote, false));
		assertEquals(19634, products.size());

		products = a.readProductsFromCSV("src/test/resources/products.csv", new DoesProductFitIntoEmptyTote(tote));
		assertEquals(17933, products.size());
	}

}
