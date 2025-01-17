public class SimpleHashTable {
  private Employee[] ht;

  public SimpleHashTable() {
    ht = new Employee[10];
  }

  public Employee[] getHt() {
    return ht;
  }

  private int hashKey(String key) {
    return (key.hashCode() % ht.length + ht.length) % ht.length; 
  }

  public void put(String key, Employee employee) {
    int start = hashKey(key);

    //System.out.println("start: " + start);
    //System.out.println("ht[start]: " + ht[start]);
    while (ht[start] != null) {
      start++;
      if (start == ht.length) {
        break;
      }
    }
    //System.out.println("start: " + start);
    if (start > ht.length - 1) {
      System.out.println("oops no work");
    } else {
      ht[start] = employee;
    }

    //if (ht[start] != null) {
    //  System.out.println("already occupied");
    //} else {
    //  ht[start] = employee;
    //}
  }

  public Employee get(String key) {
    int start = hashKey(key);
    while (ht[start] != null && !ht[start].getFirstName().equals(key)) {
      start++;
      if (start == ht.length) {
        break;
      }
    }
    if (ht[start] == null) {
      return null;
    } else {
      return ht[start];
    }
  }
}
