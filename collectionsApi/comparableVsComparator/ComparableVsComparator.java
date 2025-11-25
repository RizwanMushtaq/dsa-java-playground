package collectionsApi.comparableVsComparator;

import java.util.*;

public class ComparableVsComparator {
  public static void main(String[] args) {

    List<Student> studentList = new ArrayList<>();
    Student student1 = new Student(327, "Kasun");
    Student student2 = new Student(100, "Dasun");
    Student student3 = new Student(167, "Thisun");

    studentList.add(student1);
    studentList.add(student2);
    studentList.add(student3);
    System.out.println(studentList);
    Collections.sort(studentList);
    System.out.println("Sort by id: " + studentList);
    Collections.sort(studentList, new NameComparator());
    System.out.println("Sort by name: " + studentList);
    Collections.sort(studentList, new IdComparator());
    System.out.println("Sort by id: " + studentList);
  }
}

class Student implements Comparable<Student> {
  private int id;
  private String name;

  public Student(int id, String name) {
    this.id = id;
    this.name = name;
  }

  @Override
  public String toString() {
    return "Student{" + "id=" + id + ", name='" + name + '\'' + '}';
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Override
  public boolean equals(Object o) {
    if (o == null || getClass() != o.getClass()) return false;
    Student student = (Student) o;
    return id == student.id && Objects.equals(name, student.name);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name);
  }

  @Override
  public int compareTo(Student student) {
    if (id == student.id) {
      return 0;
    } else if (id > student.id) {
      return 1;
    } else {
      return -1;
    }
  }
}

class IdComparator implements Comparator<Student> {
  @Override
  public int compare(Student o1, Student o2) {
    if (o1.getId() == o2.getId()) {
      return 0;
    } else if (o1.getId() > o2.getId()) {
      return 1;
    } else {
      return -1;
    }
  }
}

class NameComparator implements Comparator<Student> {
  @Override
  public int compare(Student o1, Student o2) {
    return o1.getName().compareTo(o2.getName());
  }
}
