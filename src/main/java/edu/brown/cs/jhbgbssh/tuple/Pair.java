package edu.brown.cs.jhbgbssh.tuple;

public class Pair<K, V> {
  private K el1;
  private V el2;

  public Pair(K el1, V el2) {
    this.el1 = el1;
    this.el2 = el2;
  }

  public K getFirstElement() {
    return el1;
  }

  public V getSecondElement() {
    return el2;
  }

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
}
