package ctci.ch1_arraysAndStrings.customImplOfArrayList;

import java.util.Arrays;

/**
 * This is custom implementation of custom Array list. Following methods have been implemented for
 * this custom implementation: add, remove, get, size.
 *
 * <p>Further Improvements: We can further improve the implementation by adding feature of
 * downsizing custom array when elements are being removed from this list.
 *
 * @param <E> We can any type of Data with this Custom list.
 */
class MyArrayList<E> {
  private static final int DEFAULT_CAPACITY = 10;
  private E[] elements;
  private int size;

  @SuppressWarnings("unchecked")
  public MyArrayList() {
    this.elements = (E[]) new Object[DEFAULT_CAPACITY];
    this.size = 0;
  }

  public static void main() {
    System.out.println("Custom ArrayList Demo");
    MyArrayList<String> myArrayList = new MyArrayList<>();
    for (int i = 0; i < 50; i++) {
      myArrayList.add("Hallo" + i);
    }
    System.out.println("removed element : " + myArrayList.remove(10));
    for (int i = 0; i < myArrayList.size; i++) {
      System.out.println(myArrayList.get(i));
    }
    System.out.println("Size of an array : " + myArrayList.size());
  }

  public void add(E element) {
    if (size == elements.length) {
      resize();
    }
    elements[size] = element;
    size++;
  }

  public E get(int index) {
    if (index < 0 || index >= size) {
      throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
    }
    return elements[index];
  }

  public E remove(int index) {
    if (index < 0 || index >= size) {
      throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
    }
    E removedValue = elements[index];
    for (int i = index; i < size - 1; i++) {
      elements[i] = elements[i + 1];
    }
    elements[size] = null;
    size--;
    return removedValue;
  }

  public int size() {
    return size;
  }

  private void resize() {
    int newSize = elements.length * 2;
    elements = Arrays.copyOf(elements, newSize);
  }
}
