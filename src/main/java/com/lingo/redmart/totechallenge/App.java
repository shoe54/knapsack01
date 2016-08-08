package com.lingo.redmart.totechallenge;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import com.lingo.redmart.totechallenge.Solution.DoesProductFitIntoEmptyTote;
import com.lingo.redmart.totechallenge.algo.BranchAndBound;
import com.lingo.redmart.totechallenge.algo.DynamicProgramming;
import com.lingo.redmart.totechallenge.algo.Greedy;
import com.lingo.redmart.totechallenge.algo.MemoryFunction;

public class App {

	public App() {
		super();
	}

	void letsGoShopping(String productsCSVFilePath, 
			List<Class<? extends Solution>> shoppingAlgos, Tote tote)
			throws IOException, InstantiationException, IllegalAccessException {
		// Convert CSV to list of Products
		List<Product> products = readProductsFromCSV(
				productsCSVFilePath, new DoesProductFitIntoEmptyTote(tote));

		// Run the shopping algorithms
		for (Class<? extends Solution> shoppingAlgo : shoppingAlgos) {
			System.out.println("Running " + shoppingAlgo.getSimpleName() + " algorithm. Please wait...");
			Duration timeTaken = shoppingAlgo.newInstance().maximizeMyShopping(products, tote);
			System.out.println(
				shoppingAlgo.getSimpleName() + " algorithm: Value of tote (cents): " + tote.getTotalPrice() +
				", Occupied space (cm3): " + tote.getOccupiedVolume() + "/" + tote.getVolume()
			);
			System.out.println("Product ids in tote: " + tote.getProducts().stream().map(p -> p.id).collect(Collectors.toList()));
			System.out.println("Sum of product ids in tote: " + tote.getProducts().stream().mapToInt(p -> p.id).sum());
			System.out.println("Time taken: " + timeTaken);
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
		ArrayList<Product> products = new ArrayList<>(19634); // For the purposes of this challenge
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
			List<Class<? extends Solution>> shoppingAlgos;
			if (args.length >= 2) {
				// Algorithm to run was specified as second command line parameter
				Class<? extends Solution> shoppingAlgo = (Class<? extends Solution>) Class.forName("com.lingo.redmart.totechallenge.algo." + args[1]);
				shoppingAlgos = Arrays.<Class<? extends Solution>>asList(shoppingAlgo);
			} else {
				// Algorithm not specified in command line. Run all except BruteForce
				shoppingAlgos = Arrays.<Class<? extends Solution>>asList(
					Greedy.class, 
					DynamicProgramming.class, 
					MemoryFunction.class, 
					BranchAndBound.class 
					/* Don't run BruteForce with the supplied data set unless you are running
					 * on a quantum supercomputer :)
					BruteForce.class*/
				);
			}
			
			new App().letsGoShopping(args[0], shoppingAlgos, new Tote(45, 30, 35));
		} catch (IOException | InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

}
