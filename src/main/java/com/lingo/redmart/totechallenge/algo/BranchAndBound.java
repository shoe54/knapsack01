package com.lingo.redmart.totechallenge.algo;

import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

import com.lingo.redmart.totechallenge.PriceWeightTuple;
import com.lingo.redmart.totechallenge.Product;
import com.lingo.redmart.totechallenge.Solver;
import com.lingo.redmart.totechallenge.Tote;

/**
 * Best-first search Branch and Bound implementation. The PriceWeight value object 
 * is used as the algorithm's upper bound value. I delegate to the PriceWeight object to 
 * determine what is considered "better" or "more valuable", e.g. higher price first 
 * followed by lower weight first. Like Brute Force, B and B provides the most optimum 
 * solution. Unlike Brute Force, it has the intelligence to avoid searching thru 
 * unpromising branches of the tree. This can give the algorithm a huge time cost savings 
 * depending on the nature of the input data.
 * 
 * At any point on the tree, a Node's volume is the total of its own volume and the volume
 * of all its parent Nodes that are considered included in the search result. The Node's 
 * bound is the maximum possible PriceWeight value obtainable by greedily including Products 
 * from the rest of the products list (while it still fits the tote). The bound allows the 
 * fractional inclusion of a product since we are interested in calculating the 
 * mathematically largest value to serve as an upper bound, even if it isn't
 * realizable due to the 0/1 constraints of the problem. The algorithm can then use the
 * upper bound to exclude unpromising branches from being searched. 
 * 
 * Promising branches are branches where the Node at the start of that branch has a volume 
 * less than the tote volume and the bound is greater than the maximum Node value encountered
 * so far when traversing the search space.
 * 
 * The PriorityQueue allows searching the solution space in order of best likely (but not
 * guaranteed best) fit first. The bound value is used to determine best likely fit. 
 * 
 * @author Shu
 *
 */
public class BranchAndBound extends Solver {
	
	/**
	 * A Node that is evaluated and fed into the Priority Queue if promising.
	 * 
	 * @author Shu
	 *
	 */
	public static class Node implements Comparable<Node> {
		/** Is the index to the product to be either included or not included. Is also the 
		 * branch level on the state space tree. */
		int level;
		
		/** Volume of current Node product and all its parent Nodes that are considered 
		 * included in the search result */
		int volume;
		
		/** The Node's "value" */
		PriceWeightTuple<Integer> value;
		
		/** The upper bound value on this Node */
		PriceWeightTuple<Double> bound;
		
		/** The nodes from above this Node in the tree. Is used to track which products are included in a search result */
		Node parent;
		
		boolean included = false;

		public static final Node ZERO = new Node(0,PriceWeightTuple.ZERO,0,null); 

		public Node(int level, PriceWeightTuple<Integer> value, int volume, Node parent) {
			this.level = level;
			this.value = value;
			this.volume = volume;
			this.parent = parent;
		}

		@Override
		public String toString() {
			return "Node [level=" + level + ", value=" + value + ", volume="
					+ volume + ", bound=" + bound + ", included=" + included + "]";
		}

		/**
		 * For the purpose of the priority queue a node's comparison value is its bound
		 * 
		 * @param arg0
		 * @return
		 */
		@Override
		public int compareTo(Node arg0) {
			return this.bound.compareTo(arg0.bound);
		}
	}
	
	@Override
	protected void doMaximizeMyShopping(List<Product> products, Tote tote) {
		// This sort ensures that the bounding logic obtains a bound value that is the
		// maximum possible value for that branch
		products.sort(Collections.reverseOrder(new PriceToVolumeRatioComparator()));

		Node best = Node.ZERO,
			 u = Node.ZERO, 
			 v = Node.ZERO;
		
		// On each iteration nodes are potentially added to the queue. On the next iteration
		// nodes are pulled out in the order of potential best fit first
		PriorityQueue<Node> q = new PriorityQueue<Node>();
		
		// Start at the root of the tree
		v.bound = bound(v, tote, products);
		q.offer(v);

		while (!q.isEmpty()) {
			v = q.poll(); // Remove node with best bound. 
						  // v is now the node at the branch under evaluation
			if (v.bound.compareTo(best.value) > 0) { // Is node still promising?
				// Get the product at the tree level we are currently on
				Product product = products.get(v.level);
				
				// Set u to the next product (child of current node)
				u = new Node(v.level + 1, 
						v.value.add(product.getPrice(), product.getWeight()), 
						v.volume + product.getVolume(),
						v);
				
				// Should we update maxValue?
				if (u.volume <= tote.getVolume() && u.value.compareTo(best.value) > 0) {
					best = u;
					u.included = true;
				}
				
				u.bound = bound(u, tote, products);
				// Is the node promising?
				if (u.bound.compareTo(best.value) > 0) {
					u.included = true;
					q.offer(u);
				}

				// Set u to not include the next child of v
				u = new Node(v.level + 1, v.value, v.volume, v);
				
				u.bound = bound(u, tote, products);
				// Is the node promising?
				if (u.bound.compareTo(best.value) > 0) {
					q.offer(u);
				}
			}
		}

		Node trace = best;
		while (trace != null) {
			if (trace.included)
				tote.addProduct(products.get(trace.level-1));
			trace = trace.parent;
		}
	}

	/**
	 * Calculate maximum possible value obtainable on the branch starting at node by
	 * greedily including the rest of the products from that node onwards
	 * @param u
	 * @param tote
	 * @param products
	 * @return
	 */
	PriceWeightTuple<Double> bound(Node u, Tote tote, List<Product> products) {
		int j, k;
		int totalVolume;
		PriceWeightTuple<Double> result;
		
		if (u.volume >= tote.getVolume())
			return PriceWeightTuple.ZERO;
		else {
			result = u.value.toDouble(); // Get a real number representation of PriceWeightTuple
			j = u.level + 1;
			totalVolume = u.volume;
			// Iterate thru products. Stop iterating when the whole product does not fit in tote
			while (j <= products.size() 
					&& totalVolume + products.get(j-1).getVolume() <= tote.getVolume()) {
				Product product = products.get(j-1);
				totalVolume += product.getVolume();
				result = result.add(
					(double)product.getPrice(), (double)product.getWeight()
				);
				j++;
			}
			// Add a fraction of the next product to fill up the tote
			k = j;
			if (k <= products.size()) {
				Product product = products.get(k-1);
				int remainingVolume = tote.getVolume() - totalVolume;
				result = result.add(
					remainingVolume * (double)product.getPrice()/product.getVolume(), 
					remainingVolume * (double)product.getWeight()/product.getVolume()
				);
			}
			
			return result;
		}
	}

}
