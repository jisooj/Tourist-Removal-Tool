package sequenceSummary;

import java.util.*;

/**
 * Represents the summary statistic mode.
 * The mode is the value that occurs most frequently in a dataset.
 * If two or more values occur with the same max frequency, returning either
 * is fine.
 *
 * @param <T> The type, which must implement Comparable, to find the mode over.
 */
public class Mode<T extends Comparable<T>> implements SummaryStatistic<T> {
	private T mode;
	/**
	 * Constructor for the mode object.  Note that you can have any fields 
	 * you want (including sort algorithms, if you desire),
	 *  but you must implement this constructor so that it takes no parameters.
	 * @throws SequenceSummaryException (Note that although we allow you
	 * to throw an exception in this method if you so choose, you do not
	 * need to do so.)
	 */
	public Mode() throws SequenceSummaryException{
		// more implementation 
		mode = null; // perhaps this is not needed
	}
	
	@Override
	public T getValue(T[] arr) throws SequenceSummaryException {
		if (arr.length == 0)
			throw new IllegalArgumentException("given array does not contain any elements"); 
		Map<T,Integer> data = new HashMap<T, Integer>();
		for (T element: arr) {
			if (!data.containsKey(element))
				data.put(element, 1);
			else
				data.put(element, data.get(element) + 1);
		} 
			
		mode = null;
		for (T element: data.keySet())
			if (mode == null || data.get(mode) < data.get(element))
				mode = element;
		return mode;
	}
}