import java.util.*;
/**
 * MyHashMap.java
 * MyHashMap acts like a map.
 *
 * may contain excessive checks for null
 * 
 * @author Boxuan Shan
 * @version 01272025
 * @param <K>   the type of key
 * @param <V>   the type of value
 */
@SuppressWarnings("deprecated")
public class MyHashMap<K, V> implements Map<K, V>
{
  private static final int NUM_BUCKETS = 5;
  private LinkedList<MapEntry<K, V>>[] buckets;
  private int size;

  /**
   * constructor for MyHashMap
   */
  public MyHashMap()
  {
    size = 0;
    buckets = new LinkedList[NUM_BUCKETS];

    for (int i = 0; i < NUM_BUCKETS; i++) {
      buckets[i] = new LinkedList<MapEntry<K, V>>();
    }
  }

  /**
   * returns the bucket index for a given object
   * @param obj the object to find the bucket index for
   * @return the correct bucket index for that object
   */
  private int toBucketIndex(Object obj)
  {
    return Math.abs(obj.hashCode()) % NUM_BUCKETS;
  }


  /**
   * returns the size of this map
   * @return the size of this map
   */
  public int size()
  {
    return size;
  }

  /**
   * returns whether this map is empty
   * @return whether this map is empty
   */
  public boolean isEmpty()
  {
    return size == 0;
  }

  /**
   * returns whether this map contains a given key
   * @param key the key to check for
   * @return whether this map contains a given key
   */
  @Override
  public boolean containsKey(Object key)
  {
    for(LinkedList<MapEntry<K, V>> l : buckets) {
      if (l == null) {
        continue;
      }
      for(MapEntry<K, V> e : l) {
        if (e.getKey().equals(key)) {
          return true;
        }
      }
    }
    return false;
  }

  /**
   * returns whether this map contains a given value
   * @param value the value to check for
   * @return whether this map contains a given value
   */
  public boolean containsValue(Object value)
  {
    for(LinkedList<MapEntry<K, V>> l : buckets) {
      if (l == null) {
        continue;
      }
      for(MapEntry<K, V> e : l) {
        if (e.getValue().equals(value)) {
          return true;
        }
      }
    }
    return false;
  }

  /**
   * returns the value associated with a given key
   * @param key the key to get the value for
   * @return the value associated with a given key
   */
  public V get(Object key)
  {
    for(LinkedList<MapEntry<K, V>> l : buckets) {
      for(MapEntry<K, V> e: l) {
        if (e.getKey().equals(key)) {
          return e.getValue();
        }
      }
    }
    return null;
  }

  /**
   * puts a key-value pair into this map
   * @param key key to put
   * @param value value to put
   * @return value previously associated with the key, null if none
   */
  public V put(K key, V value)
  {
    LinkedList<MapEntry<K, V>> curr = buckets[toBucketIndex(key)];

    if (curr == null) {
      curr = new LinkedList<MapEntry<K, V>>();
      curr.add(new MapEntry<K, V>(key, value));
      size++;
      return null;
    }

    for(MapEntry e : curr) {
      if (e == null) {
        continue;
      }
      if (e.getKey().equals(key)) {
        V tmp = (V) e.getValue();
        e.setValue(value);
        return tmp;
      }
    }

    curr.add(new MapEntry<K, V>(key, value));
    size++;

    return null;
  }

  /**
   * removes a key-value pair from this map
   * @param key key to remove
   * @return value previously associated with the key, null if none
   */
  public V remove(Object key)
  {
    LinkedList<MapEntry<K, V>> curr = buckets[toBucketIndex(key)];
    if (curr == null) {
      return null;
    }

    for(MapEntry<K, V> e : curr) {
      if (e == null) {
        continue;
      }
      if (e.getKey().equals(key)) {
        MapEntry tmp = e;
        curr.remove(e);
        size--;
        return (V) tmp.getValue();
      }
    }

    return null;
  }

  /**
   * puts all key-value pairs from a given map into this map
   * @param m map to put all key-value pairs from
   */
  public void putAll(Map<? extends K, ? extends V> m)
  {
    for (K key : m.keySet())
    {
      put(key, m.get(key));
    }
  }


  /**
   * clears this map
   */
  public void clear()
  {
    for (int i = 0; i < NUM_BUCKETS; i++)
    {
      buckets[i] = null;
    }
  }

  /**
   * returns the keys in this map in set representation
   * @return the keys in this map in set representaion
   */
  public Set<K> keySet()
  {
    HashSet<K> ret = new HashSet<K>();

    for (LinkedList<MapEntry<K, V>> l : buckets) {
      for (MapEntry<K, V> e : l) {
        ret.add(e.getKey());
      }
    }

    return ret;
  }

  /**
   * returns the values in this map in collection representation
   * @return the values in this map in collection representation
   */
  public Collection<V> values()
  {
    HashSet<V> ret = new HashSet<V>();

    for (LinkedList<MapEntry<K, V>> l : buckets) {
      for (MapEntry<K, V> e : l) {
        ret.add(e.getValue());
      }
    }

    return ret;
  }

  /**
   * returns the key-value pairs in this map in set representation
   * @return the key-value pairs in this map in set representation
   */
  @Override
  public Set<java.util.Map.Entry<K, V>> entrySet()
  {
    HashSet<java.util.Map.Entry<K, V>> ret = new HashSet<java.util.Map.Entry<K, V>>();

    for (LinkedList<MapEntry<K, V>> l : buckets) {
      if (l == null) {
        continue;
      }
      for (MapEntry<K, V> e : l) {
        if (e == null) {
          continue;
        }
        ret.add(e);
      }
    }

    return ret;
  }

  /**
   * toString method i wrote bc there was none provided and i cant debug otherwise
   * @return string representation of this map
   */
  @Override
  public String toString() {
    String ret = "{";
    HashSet<MapEntry<K, V>> s = (HashSet) this.entrySet();

    if (s == null) {
      return "{}";
    }

    for(MapEntry<K, V> e : s) {
      if (e == null) {
        continue;
      }
      ret += "" + e.getKey() + "=" + e.getValue() + ",";
    }

    return ret + "}";
  }
}
