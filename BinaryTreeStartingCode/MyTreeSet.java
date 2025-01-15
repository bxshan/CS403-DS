import java.util.*;

// Replace the MyTreeSet.java in your BST project with this skeleton
// Call the BSTUtilites methods to add (insert), remove and search 
// for a value. Make sure to return a sensible value where applicable. Test the working using the TreeSetTester Next write an iterator. 
// Use the inner class skeleton for the Lists Lab to model one for the TreeSet.
// Make sure to return the values in ascending order when iterating.
// Use the TreeSetIteratorTester to test the working.
// Submit your work to Schoology when done.
//
// @author Anu Datar
// @version 01/17/2023
public class MyTreeSet<E>
{
	private TreeNode root;
	private int size;
	public TreeDisplay display;

	public MyTreeSet()
	{
    root = null;
    size = 0;
		// Initialize all instance variables 
		display = new TreeDisplay();
	}

	//returns the number of values in the set
	public int size()
	{
    return size;
	}

	public boolean contains(Object obj)
	{
    return BSTUtilities.contains(root, (Comparable)obj, display);
	}

	// if obj is not present in this set, adds obj and
	// returns true; otherwise returns false
	public boolean add(E obj)
	{
    if (contains(obj)) {
      return false;
    } else {
      root = BSTUtilities.insert(root, (Comparable)obj, display);
      size++;
      return true;
    }
	}

	// if obj is present in this set, removes obj and
	// returns true; otherwise returns false}
	public boolean remove(Object obj)
	{
    if (contains(obj)) {
      root = BSTUtilities.delete(root, (Comparable)obj, display);
      size--;
      return true;
    } else {
      return false;
    }
	}

	public String toString()
	{
		return toString(root);
	}

	private String toString(TreeNode t)
	{
		if (t == null)
			return " ";
		return toString(t.getLeft()) + t.getValue() + toString(t.getRight());
	}

  public Iterator<E> iterator() {
    return new MyTreeListIterator();
  }

  private class MyTreeListIterator implements Iterator<E> {
    // the index of the value that will be returned by next()
    private int ni;
    private ArrayList<E> io;
    private Iterator <E> it;

    E lastReturned;

    public MyTreeListIterator() {
      ni = 0;
      // initialize io
      io = new ArrayList<E>();
      getInOrder(root, display);
      it = io.iterator();
    }

    private void getInOrder(TreeNode t, TreeDisplay display)
    {
      if (t == null) {
        return;
      } else {
        getInOrder(t.getLeft(), display);
        io.add((E) t.getValue());
        getInOrder(t.getRight(), display);
      }
    }

    /**
     * @postcondition returns true if there are more elements
     *                to return
     */
    public boolean hasNext() {
      return it.hasNext();
    }

    /**
     * @postcondition returns the next element in the iteration
     */
    public E next() {
      if (!it.hasNext()) {
        throw new NoSuchElementException();
      }
      lastReturned = it.next();
      return lastReturned; 
      //if (!hasNext()) {
      //  return null;
      //} 
      //E toReturn = io.get(ni);
      //ni++;
      //return toReturn;
    }

    // @postcondition removes the last element that was returned by next
    public void remove() {
      MyTreeSet.this.remove(lastReturned);
      it.remove();
      //io.remove(ni);
      //MyTreeSet.this.remove(io.get(ni));  
      //ni++;
    }
  }
}
