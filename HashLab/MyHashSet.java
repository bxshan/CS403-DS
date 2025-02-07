import java.util.*;
/**
 * MyHashSet.java
 * @author Boxuan Shan
 * @version 01272025
 */
public class MyHashSet<E>
{
	private static final int NUM_BUCKETS = 5;
	private LinkedList<E>[] buckets;
	private int size;

  /**
   * constructor for MyHashSet  
   */
	public MyHashSet()
	{
		buckets = new LinkedList[NUM_BUCKETS];
		size = 0;

    for (int i = 0; i < NUM_BUCKETS; i++) {
      buckets[i] = new LinkedList<E>();
    }
	}

  /**
   * returns the index of the bucket where obj might be found
   * @param obj the object to find the bucket index for
   * @return the bucket index for that object
   */
	private int toBucketIndex(Object obj)
	{
    return Math.abs(obj.hashCode()) % NUM_BUCKETS;
	}

  /**
   * returns the number of elements in this set
   * @return the number of elements in this set
   */
	public int size()
	{
		return size;
	}

  /**
   * returns whether the set contains obj
   * @param obj the object to find in the set
   * @return true if the set contains obj
   */
	public boolean contains(Object obj)
	{
    int index = toBucketIndex(obj);
    ListIterator<E> it = buckets[index].listIterator();
    while (it.hasNext()) {
      if (it.next().equals(obj)) {
        return true;
      }
    }
    return false;
	}

  /**
   * if obj is not present in this set, adds obj and
   * returns true; otherwise returns false
   * @param obj the object to add to the set
   * @return true if modification was made
   */
	public boolean add(E obj)
	{
    int index = toBucketIndex(obj);
    ListIterator<E> it = buckets[index].listIterator();
    while (it.hasNext()) {
      if (it.next().equals(obj)) {
        return false;
      }
    }
    buckets[index].add(obj);
    size++;
    return true;
	}

  /**
   * if obj is present in this set, removes obj and
   * returns true; otherwise returns false
   * @param obj the object to remove from the set
   * @return true if modification was made
   */
	public boolean remove(Object obj)
	{
    int index = toBucketIndex(obj);
    ListIterator<E> it = buckets[index].listIterator();
    while (it.hasNext()) {
      if (it.next().equals(obj)) {
        it.remove();
        size--;
        return true;
      }
    }
    return false;
	}

  /**
   * returns a string representation of this set
   * @return a string representation of this set
   */
	public String toString()
	{
		String s = "";
		for (int i = 0; i < buckets.length; i++)
			if (buckets[i] != null && buckets[i].size() > 0)
				s += i + ":" + buckets[i] + " ";
		return s;
	}

  /**
   * returns an iterator over the elements in this set
   * @return an iterator over the elements in this set
   */
  public Iterator<E> iterator() {
    return new MyHashSetIterator();
  }

  /**
   * private class for the iterator
   */
  private class MyHashSetIterator implements Iterator<E> {
    private ListIterator <E> it;
    int bucketN;
    E lastRet;

    public MyHashSetIterator() {
      bucketN = 0;
      it = buckets[bucketN].listIterator();
    }

    /**
     * @return returns true if there are more elements to return
     */
    @Override
    public boolean hasNext() {
      while (!it.hasNext() && bucketN < NUM_BUCKETS) {
        it = buckets[bucketN].listIterator();
        bucketN++;
      }
      return it.hasNext();
    }

    /**
     * @return returns the next element in the iteration
     */
    @Override
    public E next() {
      if (it.hasNext()) {
        lastRet = it.next();
        System.out.println("\nok just returning\n");
        return lastRet;
      } else {
        bucketN++;
        System.out.println("\n++buckets\n");
        //if (bucketN == NUM_BUCKETS) {
        //  throw new NoSuchElementException();
        //}
        if (bucketN < NUM_BUCKETS) {
          it = buckets[bucketN].listIterator();
          next();
        }
      }
      throw new RuntimeException("how did i get here");
    }

    /**
     * removes the last element returned by next
     */
    @Override
    public void remove() {
      MyHashSet.this.remove(lastRet);
      it.remove();
    }
  }
}
