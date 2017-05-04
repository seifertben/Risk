package edu.brown.cs.jhbgbssg.tuple;

import com.google.common.base.Objects;

/**
 * Represents a Pair.
 *
 * @author sarahgilmore
 *
 * @param <K> type of first element in the pair
 * @param <V> type of second element in the pair
 */
public class Pair<K, V> {
  private K el1;
  private V el2;

  /**
   * Constructor for a pair.
   *
   * @param el1 - element 1
   * @param el2 - element 2
   * @throws IllegalArgumentException if null input
   */
  public Pair(K el1, V el2) throws IllegalArgumentException {
    if (el1 == null || el2 == null) {
      throw new IllegalArgumentException("ERROR: null input");
    }
    this.el1 = el1;
    this.el2 = el2;
  }

  /**
   * Returns the first element in the pair.
   *
   * @return first element
   */
  public K getFirstElement() {
    return el1;
  }

  /**
   * Returns the second element in the pair.
   *
   * @return second element
   */
  public V getSecondElement() {
    return el2;
  }

  /**
   * Two pairs are equal if and only if their corresponding first and second
   * elements are equal.
   *
   * @return true if equal; false otherwise
   */
  @Override
  public boolean equals(Object o) {
    if (!(o instanceof Pair)) {
      return false;
    }
    Pair<K, V> pair = (Pair<K, V>) o;
    if (pair.el1.equals(el1) && pair.el2.equals(el2)) {
      return true;
    }
    return false;
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(el1, el2);
  }

}
