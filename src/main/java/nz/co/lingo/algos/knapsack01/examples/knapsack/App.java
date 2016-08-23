package nz.co.lingo.algos.knapsack01.examples.knapsack;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.stream.Collectors;

import nz.co.lingo.algos.knapsack01.BranchAndBound;
import nz.co.lingo.algos.knapsack01.BruteForce;
import nz.co.lingo.algos.knapsack01.DynamicProgramming;

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
	Gear gear01, gear02, gear03, gear04, gear05;
	Knapsack knapsack;		

	public App() {
		super();
		
		gear01 = new Gear(1000, new BigDecimal("25.00")); // 1kg, $25.00
		gear02 = new Gear(1000, new BigDecimal("1.50")); // 1kg, $1.50 
		gear03 = new Gear(1000, new BigDecimal("12.75")); // 1kg, $12.75 
		gear04 = new Gear(2000, new BigDecimal("9.99")); // 2kg, $9.99 
		gear05 = new Gear(3000, new BigDecimal("10.99")); // 3kg, $10.99
		
		knapsack = new Knapsack(5000); // 5 kg capacity
	}
	
	public void goBruteForce(Knapsack knapsack) {
		BruteForce<BigDecimal, Gear, Knapsack> bf = new BruteForce<>();
		bf.solve(Arrays.asList(gear01, gear02, gear03, gear04, gear05), knapsack);
	}

	public void goDynamicProgramming(Knapsack knapsack) {
		DynamicProgramming<BigDecimal, Gear, Knapsack> bf = new DynamicProgramming<>();
		bf.solve(Arrays.asList(gear01, gear02, gear03, gear04, gear05), knapsack);
	}

	public void goBranchAndBound(Knapsack knapsack) {
		BranchAndBound<BigDecimal, Gear, Knapsack> bf = new BranchAndBound<>();
		bf.solve(Arrays.asList(gear01, gear02, gear03, gear04, gear05), knapsack);
	}

	public static void main(String[] args) {
		App app = new App();
		
		app.goBruteForce(app.knapsack);
		System.out.println("Gear selected with BruteForce: " + app.knapsack.getItems().collect(Collectors.toList()));
		
		app.knapsack.removeAllItems();

		app.goDynamicProgramming(app.knapsack);
		System.out.println("Gear selected with DynamicProgramming: " + app.knapsack.getItems().collect(Collectors.toList()));

		app.knapsack.removeAllItems();

		app.goBranchAndBound(app.knapsack);
		System.out.println("Gear selected with BranchAndBound: " + app.knapsack.getItems().collect(Collectors.toList()));
	}

}
