package edu.grinnell.csc207.sorting;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Something that sorts using merge sort.
 *
 * @param <T> The types of values that are sorted.
 * @author Samuel A. Rebelsky
 */
public class MergeSorter<T> implements Sorter<T> {
  // +--------+------------------------------------------------------
  // | Fields |
  // +--------+

  /** The way in which elements are ordered. */
  Comparator<? super T> order;

  // +--------------+------------------------------------------------
  // | Constructors |
  // +--------------+

  /**
   * Create a sorter using a particular comparator.
   *
   * @param comparator The order in which elements in the array should be ordered after sorting.
   */
  public MergeSorter(Comparator<? super T> comparator) {
    this.order = comparator;
  } // MergeSorter(Comparator)

  // +---------+-----------------------------------------------------
  // | Methods |
  // +---------+

  /**
   * Sort an array in place using merge sort.
   *
   * @param values an array to sort.
   * @post The array has been sorted according to some order (often one given to the constructor).
   * @post For all i, 0 &lt; i &lt; values.length, order.compare(values[i-1], values[i]) &lt;= 0
   */
  @Override
  public void sort(T[] values) {
    T[] sorted = mergeHelper(values);
    System.arraycopy(sorted, 0, values, 0, sorted.length);
  } // sort(T[])

  /**
   * Sorting two array by merging them into one.
   *
   * @param subArrayOne one half of the original array
   * @param subArrayTwo second half of the the orginal array
   * @return sorted aray
   */
  @SuppressWarnings("unchecked")
  public T[] merge(T[] subArrayOne, T[] subArrayTwo) {
    T[] sortedArray = (T[]) new Object[subArrayOne.length + subArrayTwo.length];
    int indexOne = 0;
    int indexTwo = 0;
    int mergedIndex = 0;

    while (indexOne < subArrayOne.length && indexTwo < subArrayTwo.length) {
      int compare = order.compare(subArrayOne[indexOne], subArrayTwo[indexTwo]);
      if (compare < 0) {
        sortedArray[mergedIndex++] = subArrayOne[indexOne++];
      } else if (compare > 0) {
        sortedArray[mergedIndex++] = subArrayTwo[indexTwo++];
      } else {
        sortedArray[mergedIndex++] = subArrayOne[indexOne++];
      } // if
    } // while

    while (indexTwo < subArrayTwo.length) {
      sortedArray[mergedIndex++] = subArrayTwo[indexTwo++];
    } // while

    while (indexOne < subArrayOne.length) {
      sortedArray[mergedIndex++] = subArrayOne[indexOne++];
    } // while

    return sortedArray;
  } // merge(T[], T[])

  /**
   * Sorts an array recursively.
   *
   * @param values an array we are trying to sort
   * @return sorted array
   */
  public T[] mergeHelper(T[] values) {
    if (values.length < 2) {
      return values;
    } else {
      T[] arrayL = Arrays.copyOfRange(values, 0, values.length / 2);
      T[] arrayR = Arrays.copyOfRange(values, values.length / 2, values.length);
      return merge(mergeHelper(arrayL), mergeHelper(arrayR));
    } // if
  } // mergeHelper(T[])
} // class MergeSorter
