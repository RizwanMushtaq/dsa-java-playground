package backtracking.kunal;

import java.util.ArrayList;

public class ArraySubsets {
  public static void main(String[] args) {
    ArrayList<Integer> arr = new ArrayList<>();
    arr.add(1);
    arr.add(2);
    arr.add(3);
    printSubsets(arr);
  }

  private static void printSubsets(ArrayList<Integer> arr) {
    ArrayList<Integer> p = new ArrayList<>();
    printSubsetsHelper(p, arr);
  }

  private static void printSubsetsHelper(ArrayList<Integer> p, ArrayList<Integer> up) {
    if (up.isEmpty()) {
      System.out.println(p);
      return;
    }
    // created new processed array
    ArrayList<Integer> updatedP = new ArrayList<>(p);
    updatedP.add(up.getFirst());
    // create new unprocessed array
    ArrayList<Integer> updatedUP = new ArrayList<>(up);
    updatedUP.removeFirst();

    printSubsetsHelper(updatedP, updatedUP);
    printSubsetsHelper(p, updatedUP);
  }
}
