package edu.grinnell.csc207.sorting;

import java.util.Comparator;
import java.util.Random;

/**
 * Something that sorts using Quicksort.
 *
 * @param <T> The types of values that are sorted.
 * @author Samuel A. Rebelsky
 */
public class Quicksorter<T> implements Sorter<T> {
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
  public Quicksorter(Comparator<? super T> comparator) {
    this.order = comparator;
  } // Quicksorter(Comparator)

  // +---------+-----------------------------------------------------
  // | Methods |
  // +---------+

  /**
   * Sort an array in place using Quicksort.
   *
   * @param values an array to sort.
   * @post The array has been sorted according to some order (often one given to the constructor).
   * @post For all i, 0 &lt; i &lt; values.length, order.compare(values[i-1], values[i]) &lt;= 0
   */
  @Override
  public void sort(T[] values) {
    nationaDutchFlagHelper(values, 0, values.length);
  } // sort(T[])

  /**
   * Dutch National Flag sorting based on the random pivot in the array.
   *
   * @param values an array to sort
   * @param lb lower bound
   * @param ub upper bound
   * @return
   */
  public void nationaDutchFlagHelper(T[] values, int lb, int ub) {
    if (lb >= ub - 1) {
      return;
    } // if base case
    // Selecting a random element in the array to be our pivot
    Random randNum = new Random();
    T pivot = values[randNum.nextInt(ub - lb) + lb];

    int tempLB = lb;
    int tempIndex = lb;
    int tempUB = ub - 1;

    while (tempIndex <= tempUB) {
      int compare = order.compare(values[tempIndex], pivot);
      if (compare > 0) {
        swap(values, tempIndex, tempUB--);
      } else if (compare < 0) {
        swap(values, tempIndex++, tempLB++);
      } else {
        // values[tempIndex] is equal to the pivot
        tempIndex++;
      } // if
    } // while
    nationaDutchFlagHelper(values, lb, tempLB);
    nationaDutchFlagHelper(values, tempUB, ub);
  } // nationaDutchFlag(T[],  int, int)

  /**
   * Swaps the two values int the array based on two indexes.
   *
   * @param arr array of some values
   * @param i index
   * @param j inedx
   */
  public void swap(T[] arr, int i, int j) {
    T temp = arr[i];
    arr[i] = arr[j];
    arr[j] = temp;
  } // swap(T[], int, int)
} // class Quicksorter
