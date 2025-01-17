import java.util.*;
/**
* comment this class completely, and in accordance with the style guide.
*/

public class MyHashSet<E>
{
	private static final int NUM_BUCKETS = 5;
	private LinkedList<E>[] buckets;
	private int size;

	public MyHashSet()
	{
		buckets = new LinkedList[NUM_BUCKETS];
		size = 0;

    for (int i = 0; i < NUM_BUCKETS; i++) {
      buckets[i] = new LinkedList<E>();
    }
		//MISSING CODE
	}

	//returns the index of the bucket where obj might be found
	private int toBucketIndex(Object obj)
	{
    return Math.abs(obj.hashCode()) % NUM_BUCKETS;
	}

	public int size()
	{
		return size;
	}

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

	// if obj is not present in this set, adds obj and
	// returns true; otherwise returns false
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

	// if obj is present in this set, removes obj and
	// returns true; otherwise returns false
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

	public String toString()
	{
		String s = "";
		for (int i = 0; i < buckets.length; i++)
			if (buckets[i] != null && buckets[i].size() > 0)
				s += i + ":" + buckets[i] + " ";
		return s;
	}


  public Iterator<E> iterator() {
    return new MyHashSetIterator();
  }

  private class MyHashSetIterator implements Iterator<E> {
    private ListIterator <E> it;
    int bucketN;
    E lastRet;

    public MyHashSetIterator() {
      bucketN = 0;
      it = buckets[bucketN].listIterator();
    }

    /**
     * @postcondition returns true if there are more elements
     *                to return
     */
    @Override
    public boolean hasNext() {
      return bucketN < NUM_BUCKETS-1 && it.hasNext();
    }

    /**
     * @postcondition returns the next element in the iteration
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
        it = buckets[bucketN].listIterator();
        next();
      }
      throw new RuntimeException("how did i get here");
    }

    // @postcondition removes the last element that was returned by next
    @Override
    public void remove() {
      MyHashSet.this.remove(lastRet);
      it.remove();
    }
  }
}
