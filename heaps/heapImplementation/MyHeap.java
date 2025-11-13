package heaps.heapImplementation;

import java.util.*;

public class MyHeap<T extends Comparable<T>> {
  private final ArrayList<T> list;

  public MyHeap() {
    list = new ArrayList<>();
  }

  public static void main(String[] args) throws Exception {
    System.out.println("Hallo");
    MyHeap<Integer> myHeap = new MyHeap<>();
    myHeap.insert(4);
    myHeap.insert(2);
    myHeap.insert(6);
    myHeap.insert(9);
    myHeap.insert(5);
    System.out.println(myHeap.list);
    myHeap.remove();
    System.out.println(myHeap.list);
  }

  private void swap(int first, int second) {
    T temp = list.get(first);
    list.set(first, list.get(second));
    list.set(second, temp);
  }

  private int parent(int index) {
    return (index - 1) / 2;
  }

  private int left(int index) {
    return index * 2 + 1;
  }

  private int right(int index) {
    return index * 2 + 2;
  }

  public void insert(T value) {
    list.add(value);
    upheap(list.size() - 1);
  }

  private void upheap(int index) {
    if (index == 0) return;
    int p = parent(index);
    if (list.get(index).compareTo(list.get(p)) < 0) {
      swap(index, p);
    }
  }

  public T remove() throws Exception {
    if (list.isEmpty()) throw new Exception("Removing from an empty List");
    T temp = list.getFirst();
    T last = list.removeLast();
    if (!list.isEmpty()) {
      list.set(0, last);
      downHeap(0);
    }
    return temp;
  }

  private void downHeap(int index) {
    int min = index;
    int left = left(index);
    int right = right(index);
    if (left < list.size() && list.get(min).compareTo(list.get(left)) > 0) {
      min = left;
    }
    if (right < list.size() && list.get(min).compareTo(list.get(right)) > 0) {
      min = right;
    }

    if (min != index) {
      swap(min, index);
      downHeap(min);
    }
  }

  public ArrayList<T> heapSort() throws Exception {
    ArrayList<T> result = new ArrayList<>();
    while (!list.isEmpty()) {
      result.add(this.remove());
    }
    return result;
  }
}
