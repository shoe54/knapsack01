package nz.co.lingo.algos.knapsack01.examples.knapsack;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.stream.Collectors;

import nz.co.lingo.algos.knapsack01.BruteForce;
import nz.co.lingo.algos.knapsack01.DefaultPool;

/**
 * An example of the classic 0/1 knapsack problem, we have a selection of gear to place into a 
 * knapsack that has a maximum capacity of 5000 grams. We want the maximise the selection to include 
 * the highest priced stuff over the lower priced ones.
 * 
 * This example uses BigDecimal to represent dollar price.
 * 
 * @author Shu
 *
 */
public class App {
	public App() {
		super();
	}
	
	public Knapsack go() {
		Gear gear01 = new Gear(1000, new BigDecimal("25.00")); // 1kg, $25.00
		Gear gear02 = new Gear(1000, new BigDecimal("1.50")); // 1kg, $1.50 
		Gear gear03 = new Gear(1000, new BigDecimal("12.75")); // 1kg, $12.75 
		Gear gear04 = new Gear(2000, new BigDecimal("9.99")); // 2kg, $9.99 
		Gear gear05 = new Gear(3000, new BigDecimal("10.99")); // 3kg, $10.99 
		
		Knapsack knapsack = new Knapsack(5000); // 5 kg capacity
		
		BruteForce<BigDecimal, Gear, Knapsack> bf = new BruteForce<>();
		bf.solve(Arrays.asList(gear01, gear02, gear03, gear04, gear05), knapsack);
		
		return knapsack;
	}

	public static void main(String[] args) {
		Knapsack knapsack = new App().go();
		
		System.out.println("Ad placements selected: " + knapsack.getItems().collect(Collectors.toList()));
	}

}
