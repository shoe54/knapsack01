package nz.co.lingo.algos.knapsack01;

import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Best-first search Branch and Bound implementation. The Items's "value" object
 * is used as the algorithm's upper bound value. I delegate to the Item's "value" object to 
 * determine what is considered "better" or "more valuable", e.g. higher price first 
 * followed by lower weight first. Like Brute Force, B and B provides the global optimum 
 * solution. Unlike Brute Force, it has the intelligence to avoid searching thru 
 * unpromising branches of the tree. This can give the algorithm a huge time cost savings 
 * depending on the nature of the input data.
 * 
 * At any point on the tree, a Node's cost is the total of its own cost and the cost
 * of all its parent Nodes that are considered included in the search result. The Node's 
 * bound is the maximum possible Item value obtainable by greedily including Items 
 * from the rest of the items list (while it still fits the Pool). The bound allows the 
 * fractional inclusion of an item since we are interested in calculating the 
 * mathematically largest value to serve as an upper bound, even if it isn't
 * realizable due to the 0/1 constraints of the problem. The algorithm can then use the
 * upper bound to exclude unpromising branches from being searched. 
 * 
 * Promising branches are branches where the Node at the start of that branch has a cost 
 * less than the pool cost and the bound is greater than the maximum Node value encountered
 * so far when traversing the search space.
 * 
 * The PriorityQueue allows searching the solution space in order of best likely (but not
 * guaranteed best) fit first. The bound value is used to determine best likely fit. 
 * 
 * @author Shu
 *
 */
public class BranchAndBound<
		IV extends Comparable<? super IV>, 
		IVD extends Comparable<? super IVD>, 
		I extends Item<IV, IVD>, 
		P extends Pool<IV, IVD, I>>
	extends Solver<IV, IVD, I, P> {
	
	/**
	 * A Node that is evaluated and fed into the Priority Queue if promising.
	 * 
	 * @author Shu
	 *
	 */
	public static class Node<IV extends Comparable<? super IV>, IVD extends Comparable<? super IVD>> implements Comparable<Node<IV, IVD>> {
		/** Is the index to the item to be either included or not included. Is also the 
		 * branch level on the state space tree. */
		int level;
		
		/** Cost of current Node item and all its parent Nodes that are considered 
		 * included in the search result */
		int cost;
		
		/** The Node's "value" */
		IV value;
		
		/** The upper bound value on this Node */
		IVD bound;
		
		/** The nodes from above this Node in the tree. Is used to gather the items that 
		 * are included in a search result */
		Node<IV, IVD> parent;
		
		/** Is this node's corresponding item included in this branch of the tree? */
		boolean included = false;

		public Node(int level, IV value, int cost, Node<IV, IVD> parent) {
			this.level = level;
			this.value = value;
			this.cost = cost;
			this.parent = parent;
		}

		@Override
		public String toString() {
			return "Node [level=" + level + ", value=" + value + ", cost="
					+ cost + ", bound=" + bound + ", included=" + included + "]";
		}

		/**
		 * For the purpose of the priority queue a node's comparison value is its bound
		 * 
		 * @param arg0
		 * @return
		 */
		@Override
		public int compareTo(Node<IV, IVD> arg0) {
			return this.bound.compareTo(arg0.bound);
		}
	}
	
	@Override
	protected void doSolve(List<I> items, P pool) {
		// This sort ensures that the bounding logic obtains a bound value that is the
		// maximum possible value for that branch
		items.sort(Collections.reverseOrder(pool.getValueToCostRatioComparator()));

		//public static final Node ZERO = new Node(0,PriceWeightTuple.ZERO,0,null); 
		final Node<IV, IVD> ZERO = getZeroNode(items.get(0).getValueZero());
		Node<IV, IVD> best = ZERO,
			 u = ZERO, 
			 v = ZERO;
		
		// On each iteration nodes are potentially added to the queue. On the next iteration
		// nodes are pulled out in the order of potential best fit first
		PriorityQueue<Node<IV, IVD>> q = new PriorityQueue<Node<IV, IVD>>();
		
		// Start at the root of the tree
		v.bound = bound(v, pool, items);
		q.offer(v);

		while (!q.isEmpty()) {
			v = q.poll(); // Remove node with best bound. 
						  // v is now the node at the branch under evaluation
			if (v.bound.compareTo(items.get(0).valueToDouble(best.value)) > 0) { // Is node still promising?
				// Get the item at the tree level we are currently on
				I item = items.get(v.level);
				
				// Set u to the next item (child of current node)
				u = new Node<IV, IVD>(v.level + 1,
						item.addValue(v.value),
						v.cost + item.getCost(),
						v);
				
				// Should we update maxValue?
				if (u.cost <= pool.getAllowedCost() && u.value.compareTo(best.value) > 0) {
					best = u;
					u.included = true;
				}
				
				u.bound = bound(u, pool, items);
				// Is the node promising?
				if (u.bound.compareTo(items.get(0).valueToDouble(best.value)) > 0) {
					u.included = true;
					q.offer(u);
				}

				// Set u to not include the next child of v
				u = new Node<IV, IVD>(v.level + 1, v.value, v.cost, v);
				
				u.bound = bound(u, pool, items);
				// Is the node promising?
				if (u.bound.compareTo(items.get(0).valueToDouble(best.value)) > 0) {
					q.offer(u);
				}
			}
		}

		Node<IV, IVD> trace = best;
		while (trace != null) {
			if (trace.included)
				pool.addItem(items.get(trace.level-1));
			trace = trace.parent;
		}
	}

	public Node<IV, IVD> getZeroNode(IV itemValueZero) {
		return new Node<IV, IVD>(0,itemValueZero,0,null);
	}

	/**
	 * Calculate maximum possible value obtainable on the branch starting at node by
	 * greedily including the rest of the items from that node onwards
	 * @param u
	 * @param pool
	 * @param items
	 * @return
	 */
	IVD bound(Node<IV, IVD> u, P pool, List<I> items) {
		int j, k;
		int totalVolume;
		IVD result;
		
		if (u.cost >= pool.getAllowedCost())
			return items.get(0).valueToDouble(items.get(0).getValueZero());
		else {
			result = (IVD) items.get(0).valueToDouble(u.value); // Get a real number representation of the item value
			//result = u.value.toDouble(); // Get a real number representation of PriceWeightTuple
			j = u.level + 1;
			totalVolume = u.cost;
			// Iterate thru items. Stop iterating when the whole item does not fit in pool
			while (j <= items.size() 
					&& totalVolume + items.get(j-1).getCost() <= pool.getAllowedCost()) {
				I item = items.get(j-1);
				totalVolume += item.getCost();
				result = item.addDouble(result);
				j++;
			}
			// Add a fraction of the next item to fill up the pool
			k = j;
			if (k <= items.size()) {
				I item = items.get(k-1);
				int remainingVolume = pool.getAllowedCost() - totalVolume;
				IVD fraction = item.divideByCost(item.multiplyValue(remainingVolume));
				result = item.addDoubles(result, fraction);
			}
			
			return result;
		}
	}

}
