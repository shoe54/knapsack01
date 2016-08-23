package nz.co.lingo.algos.knapsack01.examples.shopping;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import nz.co.lingo.algos.knapsack01.BranchAndBound;
import nz.co.lingo.algos.knapsack01.BruteForce;
import nz.co.lingo.algos.knapsack01.DynamicProgramming;
import nz.co.lingo.algos.knapsack01.Greedy;
import nz.co.lingo.algos.knapsack01.MemoryFunction;
import nz.co.lingo.algos.knapsack01.Solver;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

public class App {
	public static class ShoppingGreedy extends Greedy<PriceWeightTuple<Integer>, PriceWeightTuple<Double>, Product, Tote> {}
	public static class ShoppingMemoryFunction extends MemoryFunction<PriceWeightTuple<Integer>, PriceWeightTuple<Double>, Product, Tote> {}
	public static class ShoppingDynamicProgramming extends DynamicProgramming<PriceWeightTuple<Integer>, PriceWeightTuple<Double>, Product, Tote> {}
	public static class ShoppingBranchAndBound extends BranchAndBound<PriceWeightTuple<Integer>, PriceWeightTuple<Double>, Product, Tote> {}
	public static class ShoppingBruteForce extends BruteForce<PriceWeightTuple<Integer>, PriceWeightTuple<Double>, Product, Tote> {}
	
	/**
	 * Convenience class to determine if Product should be allowed in an empty Tote 
	 * based on whether the Product fits into the empty Tote either by volume only, or by
	 * dimensions (which implies a fit by volume as well).
	 */
	public static class DoesProductFitIntoEmptyTote implements Predicate<Product> {
		private Tote tote;
		/** true: check for fit using dimensions, false: check for fit by volume only */
		private boolean testByDimensions;

		public DoesProductFitIntoEmptyTote(Tote tote) {
			this(tote, true);
		}

		public DoesProductFitIntoEmptyTote(Tote tote, boolean testByDimensions) {
			super();
			this.tote = tote;
			this.testByDimensions = testByDimensions;
		}

		@Override
		public boolean test(Product p) {
			if (testByDimensions)
				return p.canFitInto(tote);
			else
				return p.getCost() <= tote.getAllowedCost();
		}
	}

	/**
	 * Product with a higher price to volume ratio is higher by comparison, i.e.
	 * a sort will place these higher ratio items last. On a price tie, higher volume
	 * items are higher in the comparison. On a price and volume tie, lower weight
	 * is higher in the comparison 
	 */
	public static class PriceToVolumeRatioComparator implements Comparator<Product> {
		@Override
		public int compare(Product o1, Product o2) {
			if (o1.getPriceToVolumeRatio() < o2.getPriceToVolumeRatio())
				return -1;
			else if (o1.getPriceToVolumeRatio() > o2.getPriceToVolumeRatio())
				return 1;
			else if (o2.getVolume() != o1.getVolume())
				return o1.getVolume() - o2.getVolume();
			else
				return o2.getWeight() - o1.getWeight();
		}
	}
	
	public App() {
		super();
	}

	void letsGoShopping(String productsCSVFilePath, 
			List<Class<? extends Solver<PriceWeightTuple<Integer>, PriceWeightTuple<Double>, Product, Tote>>> shoppingAlgos, 
			Tote tote)
			throws IOException, InstantiationException, IllegalAccessException {
		// Convert CSV to list of Products
		List<Product> products = readProductsFromCSV(
				productsCSVFilePath, new DoesProductFitIntoEmptyTote(tote));

		// Run the shopping algorithms
		for (Class<? extends Solver<PriceWeightTuple<Integer>, PriceWeightTuple<Double>, Product, Tote>> shoppingAlgo : shoppingAlgos) {
			System.out.println("Running " + shoppingAlgo.getSimpleName() + " algorithm. Please wait...");
			Duration timeTaken = shoppingAlgo.newInstance().solve(products, tote);
			System.out.println(
				shoppingAlgo.getSimpleName() + " algorithm: Value of tote (cents): " + tote.getTotalPrice() +
				", Occupied space (cm3): " + tote.getOccupiedVolume() + "/" + tote.getVolume()
			);
			System.out.println("Product ids in tote: " + tote.getProducts().map(p -> p.id).collect(Collectors.toList()));
			System.out.println("Sum of product ids in tote: " + tote.getProducts().mapToInt(p -> p.id).sum());
			System.out.println("Time taken: " + timeTaken);
			System.out.println("");
			tote.removeAllProducts();
		}
	}

	/**
	 * Create list of Products from CSV file and optionally filter by a Predicate.
	 * Filtering the list here allows the algorithms to run with as small of a data
	 * set as possible, considering that the data set size may affect how much
	 * resources (memory/CPU) the algorithm consumes.
	 * 
	 * @param csvPathname
	 * @param filter
	 * @return
	 * @throws IOException
	 */
	List<Product> readProductsFromCSV(String csvPathname,
			Predicate<Product> filter) throws IOException {
		File csvData = new File(csvPathname);
		CSVParser parser = CSVParser.parse(csvData, Charset.defaultCharset(),
				CSVFormat.RFC4180);
		ArrayList<Product> products = new ArrayList<>(17933); // For the purposes of this challenge
															  // I know the number of filtered 
															  // records, so I can avoid the costs 
															  // of resizing the ArrayList

		int csvIndex;
		int id;
		int price;
		int length, width, height;
		int weight;
		for (CSVRecord csvRecord : parser) {
			csvIndex = 0;
			id = Integer.parseInt(csvRecord.get(csvIndex++));
			price = Integer.parseInt(csvRecord.get(csvIndex++));
			length = Integer.parseInt(csvRecord.get(csvIndex++));
			width = Integer.parseInt(csvRecord.get(csvIndex++));
			height = Integer.parseInt(csvRecord.get(csvIndex++));
			weight = Integer.parseInt(csvRecord.get(csvIndex++));

			Product p = new Product(id, price, length, width, height, weight);
			if (filter == null || filter.test(p))
				products.add(p);
		}
		return products;
	}
	
	public static void main(String[] args) {
		try {
			if (args.length < 1) {
				System.err.println("Specify CSV filepath as the first command line parameter");
				return;
			}		
			List<Class<? extends Solver<PriceWeightTuple<Integer>, PriceWeightTuple<Double>, Product, Tote>>> shoppingAlgos;
			if (args.length >= 2) {
				// Algorithm to run was specified as second command line parameter
				Class<? extends Solver<PriceWeightTuple<Integer>, PriceWeightTuple<Double>, Product, Tote>> shoppingAlgo = 
						(Class<? extends Solver<PriceWeightTuple<Integer>, PriceWeightTuple<Double>, Product, Tote>>) 
						Class.forName("nz.co.lingo.algos.knapsack01.examples.shopping.App$Shopping" + args[1]);
				shoppingAlgos = Arrays.<Class<? extends Solver<PriceWeightTuple<Integer>, PriceWeightTuple<Double>, Product, Tote>>>asList(shoppingAlgo);
			} else {
				// Algorithm not specified in command line. Run all except BruteForce
				shoppingAlgos = Arrays.<Class<? extends Solver<PriceWeightTuple<Integer>, PriceWeightTuple<Double>, Product, Tote>>>asList(
					ShoppingGreedy.class,
					ShoppingDynamicProgramming.class,
					ShoppingMemoryFunction.class,
					ShoppingBranchAndBound.class
					/* Don't run BruteForce with the supplied data set unless you are running
					 * on a quantum supercomputer :)
					ShoppingBruteForce.class*/
				);
			}
			
			new App().letsGoShopping(args[0], shoppingAlgos, new Tote(45, 30, 35));
		} catch (IOException | InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

}
