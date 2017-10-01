package pl.coderstrust.myhashmap;

import java.util.LinkedList;
import java.util.List;

public class InnerList<K, V> {

  private List<MyEntry<K, V>> innerListOfEntries;

  public InnerList() {
    innerListOfEntries = new LinkedList<>();
  }

  public void addMyEntryToInnerList(MyEntry<K, V> myEntry) {
    innerListOfEntries.add(myEntry);
  }

  public void addNullToInnerList(MyEntry<K, V> myEntry) {
    innerListOfEntries.add(0, myEntry);
  }

  public void addNextNullToInnerList(MyEntry<K, V> myEntry) {
    innerListOfEntries.set(0, myEntry);
  }

  public List<MyEntry<K, V>> getInnerListOfEntries() {
    return innerListOfEntries;
  }

  public void removeEntryFromInnerList(MyEntry<K, V> myEntry) {
    this.innerListOfEntries.remove(myEntry);
  }
}
