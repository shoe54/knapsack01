package com.lingo.redmart.totechallenge;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import com.lingo.redmart.totechallenge.Solution.DoesProductFitIntoEmptyTote;
import com.lingo.redmart.totechallenge.dp.DynamicProgramming;
import com.lingo.redmart.totechallenge.greedy.Greedy;

public class App {

	public App() {
		super();
	}

	void letsGoShopping(String productsCSVFilePath, Tote tote)
			throws IOException {
		List<Product> products = readProductsFromCSV(
				productsCSVFilePath, new DoesProductFitIntoEmptyTote(tote));

		new Greedy().maximizeMyShopping(products, tote);
		System.out.println(
			"Greedy algorithm solution (cents): " + tote.getTotalPrice() +
			", Occupied space (cm3): " + tote.getOccupiedVolume() +
			"/" + tote.getVolume()
		);
		tote.removeAllProducts();
		
		new DynamicProgramming().maximizeMyShopping(products, tote);
		System.out.println(
			"DP solution (cents): " + tote.getTotalPrice() +
			", Occupied space (cm3): " + tote.getOccupiedVolume() +
			"/" + tote.getVolume()
		);
		tote.removeAllProducts();
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
		ArrayList<Product> products = new ArrayList<>(20000); // For the purposes of this challenge we know the number of records

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
		if (args.length < 1) {
			System.err.println("Specify CSV filepath as command line parameter");
		}		
		
		try {
			new App().letsGoShopping(args[0], new Tote(45, 30, 35));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
