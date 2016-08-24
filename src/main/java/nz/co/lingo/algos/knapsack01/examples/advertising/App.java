package nz.co.lingo.algos.knapsack01.examples.advertising;

import java.util.Arrays;
import java.util.stream.Collectors;

import nz.co.lingo.algos.knapsack01.BranchAndBound;
import nz.co.lingo.algos.knapsack01.BruteForce;
import nz.co.lingo.algos.knapsack01.DefaultItem;
import nz.co.lingo.algos.knapsack01.DefaultPool;
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
	DefaultItem adPlacement01, adPlacement02, adPlacement03;
	DefaultPool budget;		
	
	public App() {
		super();

		// Online ad placements
		adPlacement01 = new DefaultItem(30000, 10000); // $300, 10,000 impressions
		adPlacement02 = new DefaultItem(40000, 15000); // $400, 15,000 impressions 
		adPlacement03 = new DefaultItem(55000, 20000); // $550, 20,000 impressions 
		
		budget = new DefaultPool(40000);
		
	}
	
	public void goBruteForce(DefaultPool budget) {
		BruteForce<Integer, Double, DefaultItem, DefaultPool> bf = new BruteForce<>();
		bf.solve(Arrays.asList(adPlacement01, adPlacement02, adPlacement03), budget);
	}

	public void goDynamicProgramming(DefaultPool budget) {
		DynamicProgramming<Integer, Double, DefaultItem, DefaultPool> bf = new DynamicProgramming<>();
		bf.solve(Arrays.asList(adPlacement01, adPlacement02, adPlacement03), budget);
	}

	public void goBranchAndBound(DefaultPool budget) {
		BranchAndBound<Integer, Double, DefaultItem, DefaultPool> bf = new BranchAndBound<>();
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
