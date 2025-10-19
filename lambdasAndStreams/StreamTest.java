package lambdasAndStreams;

import java.util.*;

public class StreamTest {
  public static void main(String[] args) {
    List<String> colors = List.of("Red", "Blue");
    /** Print using for loop */
    for (String color : colors) {
      System.out.println(color);
    }
    /** Print using Lambda function */
    colors.forEach(color -> System.out.println(color));
  }
}
