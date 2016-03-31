package sequenceSummary;

import java.util.*;

/**
 * Sorter<T> represents a set of sorting algorithms over a generic object
 * type.  It has only one public method, which can perform any of the sorting
 * algorithms, depending on the sortType provided.
 * 
 * @param <T> the type (must be comparable)
 */
public class Sorter<T extends Comparable<T>> {
	@SuppressWarnings("unused")
	private T[] sortedArr;
	/**
	 * Enum representing the various sort algorithms.
	 */
	public static enum sortType {
		INSERTION_SORT, MERGE_SORT, QUICK_SORT, HEAP_SORT
	};
	/**
	 * Constructor
	 * @throws SequenceSummaryException (Note that although we allow you
	 * to throw an exception in this method if you so choose, you do not
	 * need to do so.)
	 * 
	 */
	public Sorter() throws SequenceSummaryException {
		sortedArr = null;
	}

	/**
	 * Sorts the elements in arr by the value in the KeyValue object (the
	 * elements of type V) (performs in-place sorting.)
	 * 
	 * @param type the type of sorting algorithm to use.
	 * @param arr the array to sort
	 * @throws SequenceSummaryException (Note that although we allow you
	 * to throw an exception in this method if you so choose, you do not
	 * need to do so.)
	 */
	public void sort(Sorter.sortType type, T[] arr) throws SequenceSummaryException {
      if (arr == null || type == null)
         throw new SequenceSummaryException("array is null or sorting type is undefined");
		//TODO: implement me!
		if (type == sortType.INSERTION_SORT)
			insertionSort(arr);
		else if (type == sortType.MERGE_SORT)
			mergeSort(arr);
		else if (type == sortType.QUICK_SORT)
			quickSort(arr);
		else if (type == sortType.HEAP_SORT)
			heapSort(arr);
		else {
			System.out.println("given sorting technique not included");
			return;
		}

		sortedArr = arr;		
	}

	/**
	 * Sorts the element in give arr by using insertion sort
	 */
	private void insertionSort(T[] arr) {
		for (int i = 1; i < arr.length; i++) {
			T temp = arr[i]; // save element to insert
			int j = i;
			while (j >= 1 && temp.compareTo(arr[j - 1]) < 0) {
				arr[j] = arr[j - 1];
				j--;
			}
			arr[j] = temp;
		}
	}

	/**
	 * Sorts the element in give arr by using merge sort
	 */
	private void mergeSort(T[] arr) {
		if (arr.length >= 2) {
			// split array into two halves
			T[] left = Arrays.copyOfRange(arr, 0, arr.length / 2);
			T[] right = Arrays.copyOfRange(arr, arr.length / 2, arr.length);
			// recursively sort the two halves
			mergeSort(left);
			mergeSort(right);
			// merge the sorted halves into a sorted whole
			merge(arr, left, right);
		}		
	}

	private void merge(T[] result, T[] left, T[] right) {
		int i1 = 0; // index into left array
		int i2 = 0; // index into right array
		for (int i = 0; i < result.length; i++) {
			if (i2 >= right.length || (i1 < left.length && left[i1].compareTo(right[i2]) <= 0)) {
				result[i] = left[i1]; // take from left
				i1++;
			} else {
				result[i] = right[i2]; // take from right
				i2++;
			}
		}		
	}

	/**
	 * Sorts the element in give arr by using quick sort
	 */
	private void quickSort(T[] arr) {
		quicksort(arr, 0, arr.length - 1);
	}

	private void quicksort(T[] arr, int min, int max) {
		if (min >= max) // base case; no need to sort
			return;
		// first element as pivot, which might be bad
		T pivot = arr[min]; 
		swap(arr, min, max);

		// partition the two sides of the array
		int middle = partition(arr, min, max - 1, pivot);
		swap(arr, middle, max); // restore pivot to proper location

		// recursively sort the left and right partitions 
		quicksort(arr, min, middle - 1);
		quicksort(arr, middle + 1, max);
	}

	private void swap(T[] arr, int i, int j) {
		T temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}

	private int partition(T[] arr, int i, int j, T pivot) {
		while (i <= j) {
			// move index markers i, j toward center
			// until we find a pair of out-of-order elements
			while (i <= j && arr[i].compareTo(pivot) < 0) { i++; }
			while (i <= j && arr[j].compareTo(pivot) > 0) { j--; }

			if (i <= j) {
				swap(arr, i, j);
				i++;
				j--;
			}
		}
		return i;
	}

	/**
	 * Sorts the element in give arr by using heap sort
	 * (changes arr into min heap such that elements in arr are
	 * stored in increasing order)
	 */
	private void heapSort(T[] arr) {
		for (int i = arr.length / 2; i >= 0; i--) 
			bubbleDown(arr, i, arr.length - 1);
		for (int i = arr.length - 1; i > 0; i--) {
			swap(arr, 0, i);
			bubbleDown(arr, 0, i - 1);
		}
	}

	// swaps arr[hole] down with its larger child until in place
	private void bubbleDown(T[] arr, int hole, int max) {
		T temp = arr[hole];
		while (hole * 2 <= max) {
			// pick larger child 
			int child = hole * 2;
			if (child != max && arr[child + 1].compareTo(arr[child]) > 0) {
				child++;
			}
			if (arr[child].compareTo(temp) > 0) {
				arr[hole] = arr[child];
			} else {
				break;
			}
			hole = child;
		}
		arr[hole] = temp;
	}
}