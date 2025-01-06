import java.util.ArrayList;
import java.util.Iterator;

//class OuterClass {
//  public void oprint() {
//    System.out.println(0);
//  }
//
//  static class InnerClass {
//    public void iprint() {
//      System.out.println(1);
//    }
//  }
//}


//class Test<E> {
//  // An object of type E is declared
//  E obj;
//  Test(E obj) { this.obj = obj; } // constructor
//  public E getObject() { return this.obj; }
//}


//// Interface
//interface Animal {
//  public void animalSound(); // interface method (does not have a body)
//  public void sleep(); // interface method (does not have a body)
//}
//
//// Pig "implements" the Animal interface
//class Pig implements Animal {
//  public void animalSound() {
//    // The body of animalSound() is provided here
//    System.out.println("The pig says: wee wee");
//  }
//  public void sleep() {
//    // The body of sleep() is provided here
//    System.out.println("Zzz");
//  }
//}
//

//abstract class Animal {
//  public abstract void sound();
//  public abstract void sleep();
//  public void eat() {
//    System.out.println("eat");
//  }
//} 
//
//class Dog extends Animal {
//  public void sound() {
//    System.out.println("woof");
//  }
//
//  public void sleep() {
//    System.out.println("zzzdog");
//  }
//}



public class Main {
  public static void lS(String str)
  {
    String s = str.substring(0, 1);
    int c = 1;
    int cmax = Integer.MIN_VALUE;
    String smax = s;

    if (str.length() == 0) System.out.println(s + " " + c);

    for (int i = 1; i < str.length(); i++) {
      if (str.substring(i, i+1).equals(s)) {
        c++;
      } else {
        if (c > cmax) {
          smax = s;
          cmax = c;
        } else {
          cmax = Math.max(cmax, c);
        }
        c = 1;
        s = str.substring(i, i+1);
      }
    }

    System.out.println(smax + " " + cmax);
  }
  public static void main(String[] args) {
    //OuterClass.InnerClass I = new OuterClass.InnerClass();
    //I.iprint();


//    ArrayList<Integer> n = new ArrayList<Integer>();
//    n.add(0);
//    n.add(1);
//    n.add(2);
//    n.add(3);
//    Iterator<Integer> it = n.iterator();
//    System.out.println(it.next());
//

//    Dog d = new Dog();  // Create a Pig object
//    d.sound();
//    d.sleep();
//    d.eat();


    lS("aaabbbbbbbcc");


  }
}
