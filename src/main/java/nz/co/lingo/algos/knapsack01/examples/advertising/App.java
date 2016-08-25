package nz.co.lingo.algos.knapsack01.examples.advertising;

import java.util.Arrays;
import java.util.stream.Collectors;

import nz.co.lingo.algos.knapsack01.BranchAndBound;
import nz.co.lingo.algos.knapsack01.BruteForce;
import nz.co.lingo.algos.knapsack01.IntValueItem;
import nz.co.lingo.algos.knapsack01.IntValuePool;
import nz.co.lingo.algos.knapsack01.DynamicProgramming;

/**
 * A simple example of allocating budget money to online ad placements. We want to maximise the number
 * of ad impressions received for our ad spend. Uses DynamicProgramming approach to solve this
 * knapsack 0/1 problem
 * 
 * @author Shu
 *
 */
public class App {
	IntValueItem adPlacement01, adPlacement02, adPlacement03;
	IntValuePool budget;		
	
	public App() {
		super();

		// Online ad placements
		adPlacement01 = new IntValueItem(30000, 10000); // $300, 10,000 impressions
		adPlacement02 = new IntValueItem(40000, 15000); // $400, 15,000 impressions 
		adPlacement03 = new IntValueItem(55000, 20000); // $550, 20,000 impressions 
		
		budget = new IntValuePool(40000);
		
	}
	
	public void goBruteForce(IntValuePool budget) {
		BruteForce<Integer, Double, IntValueItem, IntValuePool> bf = new BruteForce<>();
		bf.solve(Arrays.asList(adPlacement01, adPlacement02, adPlacement03), budget);
	}

	public void goDynamicProgramming(IntValuePool budget) {
		DynamicProgramming<Integer, Double, IntValueItem, IntValuePool> bf = new DynamicProgramming<>();
		bf.solve(Arrays.asList(adPlacement01, adPlacement02, adPlacement03), budget);
	}

	public void goBranchAndBound(IntValuePool budget) {
		BranchAndBound<Integer, Double, IntValueItem, IntValuePool> bf = new BranchAndBound<>();
		bf.solve(Arrays.asList(adPlacement01, adPlacement02, adPlacement03), budget);
	}
	
	public static void main(String[] args) {
		App app = new App();
		
		app.goBruteForce(app.budget);
		System.out.println("Ad placements selected with BruteForce: " + app.budget.getItems().collect(Collectors.toList()));
		
		app.budget.removeAllItems();

		app.goDynamicProgramming(app.budget);
		System.out.println("Ad placements selected with DynamicProgramming: " + app.budget.getItems().collect(Collectors.toList()));
		
		app.budget.removeAllItems();

		app.goBranchAndBound(app.budget);
		System.out.println("Ad placements selected with BranchAndBound: " + app.budget.getItems().collect(Collectors.toList()));
	}

}
