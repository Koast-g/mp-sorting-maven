package edu.grinnell.csc207.sorting;

import java.util.Comparator;

/**
 * Something that sorts using weird jumping sort.
 *
 * @param <T> The types of values that are sorted.
 * @author Tsymbal Koast
 * @author ChatGPT
 */
public class TsymbalKoastSorter<T> implements Sorter<T> {
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
  public TsymbalKoastSorter(Comparator<? super T> comparator) {
    this.order = comparator;
  } // Quicksorter(Comparator)

  // +---------+-----------------------------------------------------
  // | Methods |
  // +---------+

  /**
   * This fake sorting algorithm that behaves in a bouncing, zigzag pattern. Elements "bounce" back
   * and forth across the array until the entire array is sorted. Time complexity of this algorithm
   * is O(n^2).
   *
   * @param values an array to sort.
   * @post The array has been sorted according to some order (often one given to the constructor).
   * @post For all i, 0 &lt; i &lt; values.length, order.compare(values[i-1], values[i]) &lt;= 0
   */
  @Override
  public void sort(T[] values) {
    int length = values.length;
    for (int i = 0; i < length; i++) {

      // "Bouncing" forward - sort from left to right
      for (int j = 0; j < length - i - 1; j++) {
        if (order.compare(values[j], values[j + 1]) > 0) {
          swap(values, j, j + 1);
        } // if
      } // for
      // "Bouncing" backward - sort from right to left
      for (int j = length - i - 2; j > i; j--) {
        if (order.compare(values[j], values[j - 1]) < 0) {
          swap(values, j, j - 1);
        } // if
      } // for
    } // for
  } // sort(T[])

  /**
   * Swaps the two values int the array based on two indexes.
   *
   * @param values array of some values
   * @param i index
   * @param j inedx
   */
  private void swap(T[] values, int i, int j) {
    T temp = values[i];
    values[i] = values[j];
    values[j] = temp;
  } // swap(T[], int, int)
} // TsymbalKoastSorter
