package a_generalAlgorithms.backtracking.bctci.problem39point4;

import java.util.*;

public class ToBeOrNotToBe {
  ArrayList<ArrayList<String>> result = new ArrayList<>();

  public static void main(String[] args) {
    System.out.println(new ToBeOrNotToBe().solve("I love dogs"));
  }

  public List<String> solve(String str) {
    ArrayList<String> input = new ArrayList<>(Arrays.asList(str.split(" ")));
    visit(input, 0, new ArrayList<>());
    return result.stream().map(item -> String.join(" ", item)).toList();
  }

  private void visit(ArrayList<String> input, int index, ArrayList<String> processed) {
    if (index == input.size()) {
      result.add(new ArrayList<>(processed));
      return;
    }
    visit(input, index + 1, processed);
    processed.add(input.get(index));
    visit(input, index + 1, processed);
    processed.removeLast();
  }
}
