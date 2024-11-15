package edu.grinnell.csc207.sorting;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import edu.grinnell.csc207.util.ArrayUtils;
import java.util.Arrays;
import org.junit.jupiter.api.Test;

/**
 * Tests of Sorter objects. Please do not use this class directly. Rather, you should subclass it
 * and initialize stringSorter and intSorter in a static @BeforeAll method.
 *
 * @author Koast Tsymbal
 * @uathor Samuel A. Rebelsky
 */
public class TestSorter {

  // +---------+-----------------------------------------------------
  // | Globals |
  // +---------+

  /** The sorter we use to sort arrays of strings. */
  static Sorter<String> stringSorter = null;

  /** The sorter we use to sort arrays of integers. */
  static Sorter<Integer> intSorter = null;

  // +-----------+---------------------------------------------------
  // | Utilities |
  // +-----------+

  /**
   * Given a sorted array and a permutation of the array, sort the permutation and assert that it
   * equals the original.
   *
   * @param <T> The type of values in the array.
   * @param sorted The sorted array.
   * @param perm The permuted sorted array.
   * @param sorter The thing to use to sort.
   */
  public <T> void assertSorts(T[] sorted, T[] perm, Sorter<? super T> sorter) {
    T[] tmp = perm.clone();
    sorter.sort(perm);
    assertArrayEquals(
        sorted,
        perm,
        () ->
            String.format(
                "sort(%s) yields %s rather than %s",
                Arrays.toString(tmp), Arrays.toString(perm), Arrays.toString(sorted)));
  } // assertSorts

  // +-------+-------------------------------------------------------
  // | Tests |
  // +-------+

  /**
   * A fake test. I've forgotten why I've included this here. Probably just to make sure that some
   * test succeeds.
   */
  @Test
  public void fakeTest() {
    assertTrue(true);
  } // fakeTest()

  /** Ensure that an array that is already in order gets sorted correctly. */
  @Test
  public void orderedStringTest() {
    if (null == stringSorter) {
      return;
    } // if
    String[] original = {"alpha", "bravo", "charlie", "delta", "foxtrot"};
    String[] expected = original.clone();
    assertSorts(expected, original, stringSorter);
  } // orderedStringTest

  /** Ensure that an array that is ordered backwards gets sorted correctly. */
  @Test
  public void reverseOrderedStringTest() {
    if (null == stringSorter) {
      return;
    } // if
    String[] original = {"foxtrot", "delta", "charlie", "bravo", "alpha"};
    String[] expected = {"alpha", "bravo", "charlie", "delta", "foxtrot"};
    assertSorts(expected, original, stringSorter);
  } // orderedStringTest

  /** Ensure that a randomly permuted version of a moderate-sized array sorts correctly. */
  @Test
  public void permutedIntegersTest() {
    int SIZE = 100;
    if (null == intSorter) {
      return;
    } // if
    Integer[] original = new Integer[SIZE];
    for (int i = 0; i < SIZE; i++) {
      original[i] = i;
    } // for
    Integer[] expected = original.clone();
    ArrayUtils.permute(original);
    assertSorts(expected, original, intSorter);
  } // permutedIntegers

  /**
   * Ensures that if passed an empty array it retunr an empty array
   */
  @Test
  public void testEmptyArray() {
    if (intSorter == null) {
      return;
    } //if
    Integer[] input = {};
    Integer[] expected = {};
    assertSorts(expected, input, intSorter);
  } //testEmptyArray

  /**
   * Testing if an array can handl a single element arrays
   */
  @Test
  public void testSingleArray(){
    if(intSorter == null){
      return;
    } //if
    Integer[] input = {8};
    Integer[] expected = {8};
    assertSorts(expected, input,intSorter);
  } //testSingleArray

    /** Ensure that an array that is random gets sorted correctly. */
    @Test
    public void testRandomString() {
      if (null == stringSorter) {
        return;
      } // if
      String[] original = {"q", "b", "a", "a", "i", "z", "c"};
      String[] expected = {"a", "a", "b", "c", "i", "q", "z"};;
      assertSorts(expected, original, stringSorter);
    } // testRandomString
  
    /** Ensure that an array that is has the same element gets sorted correctly. */
    @Test
    public void testSameElement() {
      if (null == stringSorter) {
        return;
      } // if
      String[] original = {"z", "z", "z", "z", "z"};
      String[] expected = {"z", "z", "z", "z", "z"};
      assertSorts(expected, original, stringSorter);
    } // testSameElement

        /** Ensure that an array that is has mirrored elements gets sorted correctly. */
        @Test
        public void testMirror() {
          if (null == stringSorter) {
            return;
          } // if
          String[] original = {"a", "b", "c", "d", "e", "e", "d", "c", "b", "a"};
          String[] expected = {"a", "a", "b", "b", "c", "c", "d", "d", "e", "e"};
          assertSorts(expected, original, stringSorter);
        } // testMirror
} // class TestSorter
