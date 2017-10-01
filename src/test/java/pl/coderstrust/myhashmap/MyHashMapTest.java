package pl.coderstrust.myhashmap;

import static org.junit.Assert.assertEquals;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeSet;

@RunWith(JUnitParamsRunner.class)
public class MyHashMapTest {

  private MyHashMap<String, Integer> myHashMapForTest = new MyHashMap<>();
  private HashMap<String, Integer> map = new HashMap<>();
  private Collection<Integer> expectedValues = new TreeSet<>();

  /**
   * it checks for the correct operation of the map size.
   *
   * @throws Exception is required in tests.
   */
  @Test
  public void shouldCheckIfMyHashmapSize() throws Exception {
    //given
    initializeMapForTestUseNumber();
    int expectedSize = map.size();
    //when
    initializeMyHashMapForTestUseNumber();
    int actualSize = myHashMapForTest.size();
    //than
    assertEquals(expectedSize, actualSize);
  }

  /**
   * it checks for the correct operation of the map is empty.
   *
   * @throws Exception is required in tests.
   */
  @Test
  public void shouldCheckIfMyHashmapIsEmpty() throws Exception {
    //given
    //initializeMapForTestUseNumber();
    Boolean expected = false;
    //when
    initializeMyHashMapForTestUseNumber();
    Boolean actual = myHashMapForTest.isEmpty();
    //than
    assertEquals(expected, actual);
  }

  /**
   * it checks for the correct operation of the map contains key.
   *
   * @throws Exception is required in tests.
   */
  @Test
  public void shouldCheckIfContainsKey() throws Exception {
    //given
    initializeMapForTestUseList();
    final boolean expectedKey = map.containsKey("Agnieszka");
    final boolean expectedKeyNotExist = map.containsKey("Inny");
    final boolean expectedKey3 = map.containsKey(null);

    //when
    initializeMyHashmapUseList();
    boolean actualKey = myHashMapForTest.containsKey("Agnieszka");
    boolean actualKeyNotExist = myHashMapForTest.containsKey("Inny");
    boolean actualKey3 = myHashMapForTest.containsKey(null);

    //than
    assertEquals(expectedKey, actualKey);
    assertEquals(expectedKeyNotExist, actualKeyNotExist);
    assertEquals(expectedKey3, actualKey3);
  }

  /**
   * it checks for the correct operation of the map contains value.
   *
   * @throws Exception is required in tests.
   */
  @Test
  public void shouldCheckIfContainsValue() throws Exception {
    //given
    initializeMapForTestUseList();
    final boolean expectedValue = map.containsValue(8);
    final boolean expectedValueNotExist = map.containsValue(99);
    final boolean expectedValue3 = map.containsValue(null);
    //when
    initializeMyHashmapUseList();
    boolean actualValue = myHashMapForTest.containsValue(8);
    boolean actualValueNotExist = myHashMapForTest.containsValue(99);
    boolean actualValue3 = myHashMapForTest.containsValue(null);
    //than
    assertEquals(expectedValue, actualValue);
    assertEquals(expectedValueNotExist, actualValueNotExist);
    assertEquals(expectedValue3, actualValue3);
  }

  /**
   * it checks for the correct operation of the map get value.
   *
   * @throws Exception is required in tests.
   */
  @Test
  public void shouldGetValue() throws Exception {
    //given
    initializeMyHashMapForTestUseNumber();
    Integer expectedValue;
    //when
    Integer actualValue;
    //then
    for (Integer i = 0; i < 100; i++) {
      expectedValue = i;
      actualValue = myHashMapForTest.get(String.valueOf(i));
      assertEquals(expectedValue, actualValue);
    }
  }

  /**
   * it checks for the correct operation of  adding an entry object  to the map.
   *
   * @throws Exception is required in tests.
   */
  @Test
  public void shouldPutEntry() throws Exception {
    //given
    MyEntry<String, Integer> expectedEntry = new MyEntry<>("String", 123);
    int expectedSize = 1;
    int expectedEntryValue = expectedEntry.getValue();
    String expectedEntryToString = expectedEntry.toString();
    //when
    myHashMapForTest.put(expectedEntry.getKey(), expectedEntry.getValue());
    int actualSize = myHashMapForTest.size();
    int actualEntryValue = myHashMapForTest.get(expectedEntry.getKey());
    String actualEntryToString = new MyEntry<>(expectedEntry.getKey(),
        myHashMapForTest.get(expectedEntry.getKey())).toString();
    //then
    assertEquals(expectedSize, actualSize);
    assertEquals(expectedEntryValue, actualEntryValue);
    assertEquals(expectedEntryToString, actualEntryToString);
  }

  /**
   * it checks for the correct operation of remove object from the map using an iterator.
   *
   * @param hashMapSizeParameter gives size for my map in the test.
   * @throws Exception is required in tests.
   */
  @Test
  @Parameters(method = "getParametersForRemovingHashMapElementWithIterator")
  public void shouldRemoveHashMapElementWithIterator(Integer hashMapSizeParameter)
      throws Exception {
    //given
    map.clear();
    myHashMapForTest.clear();
    int hashMapSize = hashMapSizeParameter;
    for (int i = 0; i < hashMapSize; i++) {
      myHashMapForTest.put(String.valueOf(i), i);
    }
    //when
    Iterator<MyEntry<String, Integer>> mapIterator = myHashMapForTest.iterator();
    int counter = 0;
    while (mapIterator.hasNext()) {
      mapIterator.remove();
      counter++;
    }
    //then
    assertEquals(hashMapSize, counter);
    assertEquals(map.size(), myHashMapForTest.size());
  }

  private Object getParametersForRemovingHashMapElementWithIterator() {
    return new Integer[]{0, 3, 5, 32};
  }

  /**
   * it checks for the correct operation of remove object from the map with other data.
   *
   * @throws Exception is required in tests.
   */
  @Test
  public void shouldRemoveHashMapElementWithIteratorWithListInitialize()
      throws Exception {
    //given
    map.clear();
    initializeMyHashmapUseList();
    int hashMapSize = myHashMapForTest.size();
    //when
    Iterator<MyEntry<String, Integer>> mapIterator = myHashMapForTest.iterator();
    int counter = 0;
    while (mapIterator.hasNext()) {
      mapIterator.remove();
      counter++;
    }
    //then
    assertEquals(hashMapSize, counter);
    assertEquals(map.size(), myHashMapForTest.size());
  }

  /**
   * it checks for the correct operation of remove object from the map .
   *
   * @param hashMapSizeParameter gives size for my map in the test.
   * @throws Exception is required in tests.
   */
  @Test
  @Parameters(method = "getParametersForRemovingHashMapElement")
  public void shouldRemoveHashMapElement(Integer hashMapSizeParameter) throws Exception {
    //given
    map.clear();
    myHashMapForTest.clear();
    int hashMapSize = hashMapSizeParameter;
    for (int i = 0; i < hashMapSize; i++) {
      myHashMapForTest.put(String.valueOf(i), i);
    }
    //when
    int counter = 0;
    for (int i = 0; i < hashMapSize; i++) {
      myHashMapForTest.remove(String.valueOf(i));
      counter++;
    }
    //then
    assertEquals(hashMapSize, counter);
    assertEquals(map.size(), myHashMapForTest.size());
    myHashMapForTest.clear();

  }

  private Object getParametersForRemovingHashMapElement() {
    return new Integer[]{0, 8, 5, 32, 100};
  }


  /**
   * it checks for the correct operation of added other whole map in the my map.
   *
   * @throws Exception is required in tests.
   */
  @Test
  public void shouldPutAllEntries() throws Exception {
    //Given
    MyHashMap<String, Integer> mapForPutAll = new MyHashMap<>();
    for (int i = 0; i < 100; i++) {
      mapForPutAll.put(String.valueOf(i), i);
    }
    //When
    myHashMapForTest.clear();
    myHashMapForTest.putAll(mapForPutAll);
    //Then
    assertEquals(mapForPutAll.size(), myHashMapForTest.size());
    for (int i = 0; i < myHashMapForTest.size(); i++) {
      assertEquals(mapForPutAll.get(String.valueOf(i)), myHashMapForTest.get(String.valueOf(i)));
    }
  }

  /**
   * it checks for the correct operation of clear my map.
   *
   * @throws Exception is required in tests.
   */
  @Test
  public void shouldCheckIfMyHashMapCleared() throws Exception {
    //given
    map.clear();
    int expected = map.size();
    //when
    initializeMyHashMapForTestUseNumber();
    //given
    myHashMapForTest.clear();
    int actual = myHashMapForTest.size();
    assertEquals(expected, actual);
  }

  /**
   * it checks for the correct operation of creating a set of key from my map.
   *
   * @throws Exception is required in tests.
   */
  @Test
  public void shouldReturnKeySetOfEntries() throws Exception {
    //given
    initializeMapForTestUseList();
    Set<String> keySetExpected = map.keySet();
    //when
    initializeMyHashmapUseList();
    Set<String> keySetActual = myHashMapForTest.keySet();
    //then
    List<String> expectedKey = new ArrayList<>();
    List<String> actualKey = new ArrayList<>();
    expectedKey.addAll(keySetExpected);
    actualKey.addAll(keySetActual);

    assertEquals(keySetExpected.size(), keySetActual.size());
    for (int i = 0; i < expectedKey.size(); i++) {
      assertEquals(expectedKey.get(i), actualKey.get(i));
    }
  }

  /**
   * it checks for the correct operation of creating a collection of values from my map.
   *
   * @throws Exception is required in tests.
   */
  @Test
  public void shouldReturnValues() throws Exception {
    //given
    initializeMapForTestUseNumber();
    boolean expectedContainsValue = true;
    //when
    initializeMyHashMapForTestUseNumber();
    Collection<Integer> actualValues = myHashMapForTest.values();
    Integer actualValue;

    //then
    assertEquals(expectedValues.size(), actualValues.size());

    Iterator<Integer> iterator = actualValues.iterator();
    while (iterator.hasNext()) {
      actualValue = iterator.next();
      boolean actual = expectedValues.contains(actualValue);
      assertEquals(expectedContainsValue, actual);
    }
  }

  /**
   * it checks for the correct operation of creating a set of entries from my map.
   *
   * @throws Exception is required in tests.
   */
  @Test
  public void shouldReturnEntrySet() throws Exception {
    //given
    initializeMapForTestUseNumber();
    boolean expectedEntry = true;
    //when
    initializeMyHashMapForTestUseNumber();
    Set<Entry<String, Integer>> actualEntries = myHashMapForTest.entrySet();
    Entry<String, Integer> actualEntry;
    //then
    Iterator<Entry<String, Integer>> iterator = actualEntries.iterator();
    while (iterator.hasNext()) {
      actualEntry = iterator.next();
      assertEquals(expectedEntry, actualEntries.contains(actualEntry));
    }
  }

  /**
   * it checks for the correct operation of the iterator.
   *
   * @throws Exception is required in tests.
   */
  @Test
  public void shouldReturnIterator() throws Exception {
    //given
    int hashMapSize = 25;
    MyHashMap<String, Integer> myMap = new MyHashMap<>();
    for (int i = 1; i <= hashMapSize; i++) {
      myMap.put(String.valueOf(i), i);
    }
    //when
    Iterator<MyEntry<String, Integer>> mapIterator = myMap.iterator();
    int counter = 0;
    MyEntry<String, Integer> myEntry;
    while (mapIterator.hasNext()) {
      myEntry = mapIterator.next();
      String actualKey = myEntry.getKey();
      Integer actualValue = myEntry.getValue();
      boolean containsExpectedKey = true;
      boolean containsActualKey = myMap.containsKey(actualKey);
      Integer expectedValue = myMap.get(actualKey);
      //then
      assertEquals(containsExpectedKey, containsActualKey);
      assertEquals(expectedValue, actualValue);
      counter++;
    }
    assertEquals(hashMapSize, counter);
  }


  private void initializeMapForTestUseList() {
    map.clear();
    for (int i = 0; i < getArrayNameStringForMap().size(); i++) {
      map.put(getArrayNameStringForMap().get(i), getArrayOfNumbersForMap().get(i));
    }
  }

  private void initializeMyHashmapUseList() {
    myHashMapForTest.clear();
    for (int i = 0; i < getArrayNameStringForMap().size(); i++) {
      myHashMapForTest.put(getArrayNameStringForMap().get(i), getArrayOfNumbersForMap().get(i));
    }
  }

  private void initializeMapForTestUseNumber() {
    map.clear();
    for (int i = 0; i < 100; i++) {
      map.put(String.valueOf(i), i);
      expectedValues.add(i);
    }
  }

  private void initializeMyHashMapForTestUseNumber() {
    myHashMapForTest.clear();
    for (int i = 0; i < 100; i++) {
      myHashMapForTest.put(String.valueOf(i), i);
    }
  }

  private List<String> getArrayNameStringForMap() {
    List<String> nameList = Arrays
        .asList(null, "Ala", "Anna", "Ala", "Bozena", "Beata", "Celina", "Dosia", null, "Majka",
            "Iwona", "Ula", "Dominika", "Agnieszka", "Iza");
    return nameList;
  }

  private List<Integer> getArrayOfNumbersForMap() {
    List<Integer> numberList = Arrays
        .asList(33, 5, 8, 7, 24, 9, 12, null, 3, 28, null, 88, 44, 145, 12457);
    return numberList;
  }

}