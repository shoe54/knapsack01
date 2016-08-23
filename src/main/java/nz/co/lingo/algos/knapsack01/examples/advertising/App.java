package nz.co.lingo.algos.knapsack01.examples.advertising;

import java.util.Arrays;
import java.util.stream.Collectors;

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
	public App() {
		super();
	}
	
	public DefaultPool go() {
		// Online ad placements
		DefaultItem adPlacement01 = new DefaultItem(50000, 100000); // $500, 100,000 impressions
		DefaultItem adPlacement02 = new DefaultItem(60000, 150000); // $600, 150,000 impressions 
		DefaultItem adPlacement03 = new DefaultItem(75000, 200000); // $750, 200,000 impressions 
		
		DefaultPool budget = new DefaultPool(60000);
		
		DynamicProgramming<Integer, DefaultItem, DefaultPool> dp = new DynamicProgramming<Integer, DefaultItem, DefaultPool>();
		dp.solve(Arrays.asList(adPlacement01, adPlacement02, adPlacement03), budget);
		
		return budget;
	}

	public static void main(String[] args) {
		DefaultPool budget = new App().go();
		
		System.out.println("Ad placements selected: " + budget.getItems().collect(Collectors.toList()));
	}

}
