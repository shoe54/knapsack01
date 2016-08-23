package nz.co.lingo.algos.knapsack01;


/**
 * An item to be added/allocated to/from the Pool, depending on how you are looking at the
 * problem. For example, in the literal knapsack example, would correspond to the items you
 * are filling the knapsack with, and the knapsack is the pool.
 * 
 * A Item's value can be a boxed primitive or a ComplexItemValue that has
 * properties of type V. The ComplexItemValue is responsible to determining what is considered
 * more valuable
 * 
 * Cost must be an int. If your cost is not an int multiply it by enough 10s to make it an int
 * 
 * @author Shu
 *
 * @param <V>
 */
public interface Item<IV extends Comparable<? super IV>, IVD extends Comparable<? super IVD>> {
	
	public static interface ComplexItemValue<VT extends Number> extends Comparable<ComplexItemValue<VT>> {
	}
	
	int getCost();
	
	IV getValue();
	
	IVD valueToDouble(IV v);

	IV getValueZero();
	
	default IV addValue(IV a) {
		return addValues(getValue(), a);		
	}

	IVD addDouble(IVD d);

	IV addValues(IV a, IV b);
	
	IVD addDoubles(IVD a, IVD b);

	IV multiplyValue(int multiplier);
	
	IVD divideByCost(IV top);

}
