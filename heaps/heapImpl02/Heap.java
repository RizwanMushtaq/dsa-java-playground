package heaps.heapImpl02;

import java.util.*;
import java.util.function.BiFunction;

public class Heap<T extends Comparable<T>> {
  private final List<T> heap;
  private final BiFunction<T, T, Boolean> higherPriority;

  public Heap(BiFunction<T, T, Boolean> higherPriority, List<T> heap) {
    this.heap = heap == null ? new ArrayList<>() : new ArrayList<>(heap);
    this.higherPriority = higherPriority == null ? (x, y) -> x.compareTo(y) < 0 : higherPriority;
    if (!this.heap.isEmpty()) heapify();
  }

  public Heap(List<T> heap) {
    this(null, heap);
  }

  public Heap(BiFunction<T, T, Boolean> higherPriority) {
    this(higherPriority, null);
  }

  public Heap() {
    this(null, null);
  }

  public static void main(String[] args) {
    new RunTests().runTests();
  }

  private int parent(int idx) {
    if (idx == 0) return -1;
    return (idx - 1) / 2;
  }

  private int left(int idx) {
    return 2 * idx + 1;
  }

  private int right(int idx) {
    return 2 * idx + 2;
  }

  public int size() {
    return heap.size();
  }

  private void heapify() {
    for (int idx = heap.size() / 2; idx >= 0; idx--) {
      bubbleDown(idx);
    }
  }

  public void push(T elem) {
    heap.add(elem);
    bubbleUp(heap.size() - 1);
  }

  public T pop() {
    if (heap.isEmpty()) {
      return null;
    }

    T top = heap.getFirst();
    if (heap.size() == 1) {
      heap.clear();
      return top;
    }

    heap.set(0, heap.getLast());
    heap.removeLast();
    bubbleDown(0);

    return top;
  }

  private void bubbleUp(int idx) {
    if (idx == 0) {
      return;
    }

    int parent = parent(idx);
    if (higherPriority.apply(heap.get(idx), heap.get(parent))) {
      swap(idx, parent);
      bubbleUp(parent);
    }
  }

  private void bubbleDown(int idx) {
    int index = idx;
    int left = left(idx);
    int right = right(idx);

    if (left < heap.size() && higherPriority.apply(heap.get(left), heap.get(index))) {
      index = left;
    }
    if (right < heap.size() && higherPriority.apply(heap.get(right), heap.get(index))) {
      index = right;
    }

    if (index != idx) {
      swap(index, idx);
      bubbleDown(index);
    }
  }

  private void swap(int first, int second) {
    T temp = heap.get(first);
    heap.set(first, heap.get(second));
    heap.set(second, temp);
  }
}

class RunTests {
  public void runTests() {
    // Test min heap
    Heap<Integer> minHeap = new Heap<>();
    Integer[] values = {4, 8, 2, 6, 1, 7, 3, 5};
    for (Integer val : values) {
      minHeap.push(val);
    }

    // Should pop in ascending order
    Integer[] sortedValues = new Integer[values.length];
    int i = 0;
    while (minHeap.size() > 0) {
      sortedValues[i++] = minHeap.pop();
    }
    Integer[] want = {1, 2, 3, 4, 5, 6, 7, 8};
    if (!Arrays.equals(sortedValues, want)) {
      throw new RuntimeException(
          String.format(
              "\nmin heap popped values: got: %s, want: %s\n",
              Arrays.toString(sortedValues), Arrays.toString(want)));
    } else {
      System.out.print(".");
    }

    // Test max heap
    Heap<Integer> maxHeap = new Heap<>((x, y) -> x > y);
    for (Integer val : values) {
      maxHeap.push(val);
    }

    // Should pop in descending order
    sortedValues = new Integer[values.length];
    i = 0;
    while (maxHeap.size() > 0) {
      sortedValues[i++] = maxHeap.pop();
    }
    Integer[] want2 = {8, 7, 6, 5, 4, 3, 2, 1};
    if (!Arrays.equals(sortedValues, want2)) {
      throw new RuntimeException(
          String.format(
              "\nmax heap popped values: got: %s, want: %s\n",
              Arrays.toString(sortedValues), Arrays.toString(want2)));
    } else {
      System.out.print(".");
    }

    // Test heapify
    Heap<Integer> heap = new Heap<>(Arrays.asList(4, 8, 2, 6, 1, 7, 3, 5));
    if (heap.pop() != 1) {
      throw new RuntimeException("heap.pop(): expected 1");
    }
    if (heap.pop() != 2) {
      throw new RuntimeException("heap.pop(): expected 2");
    }
    if (heap.pop() != 3) {
      throw new RuntimeException("heap.pop(): expected 3");
    }
    System.out.print(".");
  }
}
