/**
 * Student.java
 * @author Boxuan Shan
 * @version 10252024
 */
public class Student {
  private String name;
  private int studentID;
  private String gradeLevel;

  /**
   * Constructor for Student class
   * @param n name of student
   * @param s ID of student
   * @param g student's grade level
   */
  public Student(String n, int s, String g) {
    this.name = n;
    this.studentID = s;
    this.gradeLevel = g;
  }
}
