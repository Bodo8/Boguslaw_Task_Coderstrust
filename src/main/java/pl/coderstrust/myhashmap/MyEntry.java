package pl.coderstrust.myhashmap;

import java.util.Map.Entry;

public class MyEntry<K, V> implements Entry<K, V> {

  private K key;
  private V value;

  public MyEntry(K key, V value) {
    this.key = key;
    this.value = value;
  }

  public K getKey() {
    return key;
  }

  public V getValue() {
    return value;
  }

  public V setValue(V value) {
    this.value = value;
    return value;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (!(obj instanceof MyEntry)) {
      return false;
    }

    MyEntry<?, ?> myEntry = (MyEntry<?, ?>) obj;

    if (key != null ? !key.equals(myEntry.key) : myEntry.key != null) {
      return false;
    }
    return value != null ? value.equals(myEntry.value) : myEntry.value == null;
  }

  @Override
  public int hashCode() {
    if (key != null) {
      int hash = Math.abs(key.hashCode() % 32);
      if (hash == 0) {
        hash = hash + 10;
      }
      return hash;
    } else {
      return 0;
    }
  }

  @Override
  public String toString() {
    return
        "key="
            + key
            +
            ", value=" + value;
  }
}
