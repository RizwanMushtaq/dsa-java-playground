package collectionsApi;

import java.util.*;

public class FinalList {
  public static void main(String[] args) {
    List<Integer> list = List.of(1, 2, 3);
    list = List.of(4, 5);
    System.out.println("list :" + list);

    final List<Integer> finalList = List.of(1, 2, 3);
    //    finalList = List.of(4, 5);
    System.out.println("finalList :" + finalList);
  }
}
