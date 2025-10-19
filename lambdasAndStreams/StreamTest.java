package lambdasAndStreams;

import java.util.*;
import java.util.stream.Collectors;

public class StreamTest {
  public static void main(String[] args) {
    List<String> colors = List.of("Red", "Blue");
    /** Print using for loop */
    for (String color : colors) {
      System.out.println(color);
    }
    /** Print using Lambda function */
    colors.forEach(color -> System.out.println(color));
    /** Print using Method reference */
    colors.forEach(System.out::println);

    List<String> strings = List.of("I", "am", "a", "list", "of", "strings");
    List<String> result =
        strings.stream().skip(2).limit(3).collect(Collectors.toUnmodifiableList());
    result.forEach(System.out::println);
  }
}
