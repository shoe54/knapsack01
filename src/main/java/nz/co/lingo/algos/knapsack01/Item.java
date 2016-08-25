package nz.co.lingo.algos.knapsack01;


/**
 * An item to be added/allocated to/from the Pool, depending on how you are looking at the
 * problem. For example, in the literal knapsack example, would correspond to the items you
 * are filling the knapsack with, and the knapsack is the pool.
 * 
 * A Item's value can be a boxed primitive (e.g. Integer, BigDecimal, etc) or a ComplexItemValue 
 * that has value properties of type V. The ComplexItemValue is responsible to determining what is 
 * considered more valuable
 * 
 * Cost must be an int. If your cost is not an int multiply it by enough 10s to make it an int
 * 
 * @author Shu
 *
 * @param <IV> The type of the item's value, e.g. Integer, BigDecimal, subclass of ComplexItemValue, etc
 * @param <IVD>
 */
public interface Item<IV extends Comparable<? super IV>, IVD extends Comparable<? super IVD>> {
	
	public static interface ComplexItemValue<VT extends Number> extends Comparable<ComplexItemValue<VT>> {
	}
	
	int getCost();
	
	IV getValue();
	
	/**
	 * Convert item value v to a double (real number) representation of that value
	 * @param v
	 * @return
	 */
	IVD valueToDouble(IV v);

	/**
	 * Returns an item value that is considered zero (e.g. 0 for Integers)
	 * @return
	 */
	IV getValueZero();
	
	default IV addValue(IV a) {
		return addValues(getValue(), a);		
	}

	/**
	 * Add d to this item value
	 * @param d
	 * @return
	 */
	IVD addDouble(IVD d);

	/**
	 * Add a to b
	 * @param a
	 * @param b
	 * @return
	 */
	IV addValues(IV a, IV b);
	
	IVD addDoubles(IVD a, IVD b);

	/**
	 * Multiply this item value by multiplier
	 * @param multiplier
	 * @return
	 */
	IV multiplyValue(int multiplier);
	
	/**
	 * Divide top by this item value's cost
	 * @param top
	 * @return
	 */
	IVD divideByCost(IV top);

}
