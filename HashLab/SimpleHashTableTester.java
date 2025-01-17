public class SimpleHashTableTester {
  public static void main(String[] args) {
    SimpleHashTable ht = new SimpleHashTable();

    Employee boxuan = new Employee("Boxuan", "Shan", 10001);
    Employee hongkang = new Employee("Hongkang", "Shan", 20002);
    Employee arav = new Employee("Arav", "Vuppala", 30003);
    Employee william = new Employee("William", "Jiang", 40004);
    Employee andrew = new Employee("Andrew", "Shi", 50005);
    Employee[] employees = {boxuan, hongkang, arav, william, andrew}; 


    for(Employee employee : employees) {
      System.out.print("put " + employee.getFirstName() + "\n\t now: ");
      ht.put(employee.getFirstName(), employee);
      for(Employee e : ht.getHt()) {
        if (e == null) {
          System.out.print("null ");
        } else {
          System.out.print(e.getFirstName() + " ");
        }
      }
      System.out.println(); 
    }
  }
}
