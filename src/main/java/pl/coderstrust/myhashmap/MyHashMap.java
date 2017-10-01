package pl.coderstrust.myhashmap;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;


public class MyHashMap<K, V> implements Map<K, V>, Iterable<MyEntry<K, V>> {

  private List<InnerList<K, V>> primaryList = new ArrayList<>();
  private int sizeMap = 0;
  private int countNullKey = 0;

  @Override
  public int size() {
    return sizeMap;
  }

  @Override
  public boolean isEmpty() {
    return sizeMap == 0 ? true : false;
  }

  @Override
  public boolean containsKey(Object key) {
    int index = hash(key);
    if (key == null) {
      if (countNullKey == 0) {
        return false;
      } else {
        return true;
      }
    } else {
      if (index < primaryList.size()) {
        List<MyEntry<K, V>> innerListOfEntries = primaryList.get(index).getInnerListOfEntries();
        for (MyEntry<K, V> myEntry : innerListOfEntries) {
          if (myEntry.getKey().equals(key)) {
            return true;
          }
        }
      } else {
        return false;
      }
    }
    return false;
  }


  @Override
  public boolean containsValue(Object value) {
    for (InnerList<K, V> innerList : primaryList) {
      for (MyEntry<K, V> myEntry : innerList.getInnerListOfEntries()) {
        if (myEntry.getValue() != null) {
          if (myEntry.getValue().equals(value)) {
            return true;
          }
        } else {
          if (value == null) {
            return true;
          }
        }
      }
    }
    return false;

  }

  @Override
  public V get(Object key) {
    int index = hash(key);
    List<MyEntry<K, V>> innerListOfEntries = primaryList.get(index).getInnerListOfEntries();

    if (key != null) {
      for (MyEntry<K, V> myEntry : innerListOfEntries) {
        if (myEntry.getKey().equals(key)) {
          return (V) myEntry.getValue();
        }
      }
    } else {
      if (!innerListOfEntries.isEmpty()) {
        return (V) innerListOfEntries.get(index).getValue();
      }
    }
    return null;
  }

  @Override
  public V put(K key, V value) {
    MyEntry<K, V> myEntry = new MyEntry(key, value);
    int listSize = primaryList.size();
    int trueControl = 0;
    int index = hash(key);

    if (listSize <= index) {
      initializeArrayList(index, listSize);
      if (key != null) {
        primaryList.get(index).addMyEntryToInnerList(myEntry);
        sizeMap++;
      } else {
        primaryList.get(index).addNullToInnerList(myEntry);
        countNullKey++;
        sizeMap++;
      }
    } else {
      List<MyEntry<K, V>> myEntries = primaryList.get(index).getInnerListOfEntries();
      if (!myEntries.isEmpty() || key == null) {
        if (key != null) {
          for (int i = 0; i < myEntries.size(); i++) {
            if (myEntries.get(i).getKey().equals(myEntry.getKey())) {
              myEntries.set(i, myEntry);
              trueControl++;
            }
          }

          if (trueControl == 0) {
            primaryList.get(index).addMyEntryToInnerList(myEntry);
            sizeMap++;
          }

        } else {
          primaryList.get(index).addNextNullToInnerList(myEntry);
          countNullKey++;
        }
      } else {
        primaryList.get(index).addMyEntryToInnerList(myEntry);
        sizeMap++;
      }
    }
    return value;
  }

  private void initializeArrayList(int index, int listSize) {
    for (int i = listSize; i <= index; i++) {
      primaryList.add(i, new InnerList<>());
    }
  }

  @Override
  public V remove(Object key) {
    int index = hash(key);
    InnerList<K, V> innerList = primaryList.get(index);
    MyEntry<K, V> entryToRemouve = null;
    if (key != null) {
      for (MyEntry<K, V> myEntry : innerList.getInnerListOfEntries()) {
        if (myEntry.getKey().equals(key)) {
          entryToRemouve = myEntry;
          break;
        }
      }
      if (entryToRemouve != null) {
        innerList.removeEntryFromInnerList(entryToRemouve);
        sizeMap--;
      }
    } else {
      if (countNullKey > 0) {
        entryToRemouve = innerList.getInnerListOfEntries().get(0);
        innerList.removeEntryFromInnerList(entryToRemouve);
        sizeMap--;
      }
    }
    return null;
  }

  @Override
  public void putAll(Map<? extends K, ? extends V> addedMap) {
    for (Map.Entry<? extends K, ? extends V> entry : addedMap.entrySet()) {
      MyEntry<K, V> mm = new MyEntry<>(entry.getKey(), entry.getValue());
      put(mm.getKey(), mm.getValue());
    }
  }

  @Override
  public void clear() {
    primaryList = new ArrayList<>();
    sizeMap = 0;
  }

  @Override
  public Set<K> keySet() {
    Set<K> set = new HashSet<>();
    for (InnerList<K, V> innerList : primaryList) {
      for (MyEntry<K, V> myEntry : innerList.getInnerListOfEntries()) {
        set.add(myEntry.getKey());
      }
    }
    return set;
  }

  @Override
  public Collection<V> values() {
    Collection<V> set = new TreeSet<>();

    for (InnerList<K, V> innerList : primaryList) {
      for (MyEntry<K, V> myEntry : innerList.getInnerListOfEntries()) {
        set.add(myEntry.getValue());
      }
    }
    return set;
  }

  @Override
  public Set<Entry<K, V>> entrySet() {
    Set<Entry<K, V>> set = new HashSet<>();
    for (InnerList<K, V> innerList : primaryList) {
      for (MyEntry<K, V> myEntry : innerList.getInnerListOfEntries()) {
        set.add(myEntry);
      }
    }
    return set;
  }

  private int hash(Object key) {
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

  //Implementation of Iterable interface

  @Override
  public Iterator<MyEntry<K, V>> iterator() {
    Set<Map.Entry<K, V>> entrySet = entrySet();
    Iterator<Map.Entry<K, V>> mapIterator = entrySet.iterator();

    return new Iterator<MyEntry<K, V>>() {
      @Override
      public boolean hasNext() {
        return mapIterator.hasNext();
      }

      @Override
      public MyEntry<K, V> next() {
        return (MyEntry<K, V>) mapIterator.next();
      }

      @Override
      public void remove() {
        MyHashMap.this.remove((next().getKey()));
      }
    };
  }
}






