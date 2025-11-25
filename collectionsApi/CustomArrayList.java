package collectionsApi;

import java.util.ArrayList;

public class CustomArrayList<T> extends ArrayList<T> {
  public static void main(String[] args) {
    CustomArrayList<Integer> myList = new CustomArrayList<>();
    myList.add(1);
    myList.add(1);
    myList.add(1);
    myList.add(2);
    myList.add(2);
    System.out.println("myList :" + myList);
  }

  @Override
  public boolean add(T element) {
    if (this.contains(element)) {
      return true;
    } else {
      return super.add(element);
    }
  }
}
