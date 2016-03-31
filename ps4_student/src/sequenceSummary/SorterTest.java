// compilations error b/c sorter is in package
// remove all lines in Sorter class that involve packaging 
// and then run this test
package sequenceSummary;
import java.util.*;

public class SorterTest {
   public static void main(String[] args) {
//      Integer[] arr = makeArr(20);
//       System.out.println("Before sorting:");
//       System.out.println(Arrays.toString(arr));
//       Sorter<Integer> sorter = new Sorter<Integer>(); 
//      sorter.sort(Sorter.sortType.INSERTION_SORT, arr);
//      sorter.sort(Sorter.sortType.MERGE_SORT, arr);
//      sorter.sort(Sorter.sortType.QUICK_SORT, arr);      
//       sorter.sort(Sorter.sortType.HEAP_SORT, arr);            
//       System.out.println("After sorting:");
//       System.out.println(Arrays.toString(arr));
      Integer[] arr = new Integer[1];
      for (int i = 0; i < 1; i++) {
         arr[i] = i+1;
      }
      
      System.out.println(getValue(arr));
      System.out.println(Arrays.toString(arr));
   }
   
   public static Integer[] makeArr(int size) {
      Random r = new Random();
      Integer[] arr = new Integer[size];
      for (int i = 0; i < size; i++) {
         arr[i] = r.nextInt() % 1000;
      }
      return arr;
   }
   
   
	 /**A percentile object provides a utility for finding the nth percentile over
	 * an array of objects.  The percentile can be found by sorting the objects,
	 * then iterating until the relevant element is found.
	 * For example, if we have 201 objects and the Percentile represented is the median
	 * (percentile = .5), then we would return the 100th object.
	 * even number of objects, the median (or other percentiles)=choose the lower value.  
	 * 200 objects, percentile = .5, return the 100th element.
	 */
	public static Integer getValue(Integer[] arr) {
		if (arr.length == 0)
			throw new IllegalArgumentException("given array is empty");
		
		Integer target = arr[0];
		for (int i = 0; i < arr.length; i++) {
			double currPercent = (double) (i + 1) / arr.length;
         System.out.println("ith element: " + (i + 1) + "\tpercent: " + currPercent);
			if (currPercent <= .5)
				target = arr[i];
         else 
            break;
		}
		return target;
	}   
}