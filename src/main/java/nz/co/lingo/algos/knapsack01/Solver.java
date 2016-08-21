package nz.co.lingo.algos.knapsack01;

import java.time.Duration;
import java.time.Instant;
import java.util.List;

/**
 * Note: Solver can be further generalized. Instead of dealing with Product and Tote
 * it could deal with more abstract forms (perhaps Item and Pool respectively) so
 * that we can use the Solver on other applications of the same problem, e.g.
 * assigning resources to tasks (Item=Task and Resources=Pool)
 * 
 * @author Shu
 *
 */
public abstract class Solver<IV extends Comparable<? super IV>, I extends Item<IV>, P extends Pool<I>> {
	
	/**
	 * Return time taken in milliseconds
	 * 
	 * @param items
	 * @param pool
	 * @return
	 */
	public Duration solve(List<I> items, P pool) {
		Instant start = Instant.now();
		
		if (!items.isEmpty())
			doSolve(items, pool);
		
		return Duration.between(start, Instant.now());		
	}
	
	/**
	 * @param items Guaranteed by solve() to not be empty
	 * @param pool
	 */
	protected abstract void doSolve(List<I> items, P pool);
}
