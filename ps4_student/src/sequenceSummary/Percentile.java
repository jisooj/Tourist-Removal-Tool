package sequenceSummary;

/**
 * A percentile object provides a utility for finding the nth percentile over
 * an array of objects.  The percentile can be found by sorting the objects,
 * then iterating until the relevant element is found.
 * For example, if we have 201 objects and the Percentile represented is the median
 * (percentile = .5), then we would return the 100th object.
 * Note that if there are an even number of objects, the median (or other percentiles)
 * may be ambiguous; choose the lower value.  For example, if there are 200 objects 
 * and percentile = .5, return the 100th element.
 *
 * @param <T> the type to find the percentile over (must implement Comparable interface)
 */
public class Percentile<T extends Comparable<T>> implements SummaryStatistic<T> {
	private double percent;
	private Sorter.sortType sortType;

	/**
	 * Creates a new Percentile object, with the percentile set as the parameter
	 * percent and sorter algorithm of type sort.
	 * @param percent the percentile of this summary statistic.
	 */
	public Percentile(double percent, Sorter.sortType sort) 
								throws SequenceSummaryException {
		this.percent = percent;
		sortType = sort;
		
	}
	/**
	 *  75th percentile = top 25 percent
	 *  e.g. test score that is greater than or equal to 75% of the scores of 
	 *  people taking the test is said to be at the 75th percentile rank.
	 * @return the percentile of this summary statistic instance.
	 */
	public double getPercentile() {
		return percent;
	}

	 /**A percentile object provides a utility for finding the nth percentile over
	 * an array of objects.  The percentile can be found by sorting the objects,
	 * then iterating until the relevant element is found.
	 * For example, if we have 201 objects and the Percentile represented is the median
	 * (percentile = .5), then we would return the 100th object.
	 * even number of objects, the median (or other percentiles)=choose the lower value.  
	 * 200 objects, percentile = .5, return the 100th element.
	 */
	@Override
	public T getValue(T[] arr) throws SequenceSummaryException {
		if (arr.length == 0)
			throw new IllegalArgumentException("given array is empty");

		Sorter<T> sorter = new Sorter<T>();
		sorter.sort(sortType, arr);
		
		T target = arr[0];
		for (int i = 0; i < arr.length; i++) {
			double currPercent = (double) (i + 1) / arr.length;
			if (currPercent <= percent)
				target = arr[i];
			else
				break;
		}
		return target;
	}
}
