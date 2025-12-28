package a_generalAlgorithms.backtracking.bctci.permutations;

import java.util.ArrayList;
import java.util.List;

public class Permutations {
  List<List<Character>> result = new ArrayList<>();
  List<List<Character>> resultWithBacktracking = new ArrayList<>();

  public static void main(String[] args) {
    List<Character> chars = new ArrayList<>();
    chars.add('a');
    chars.add('b');
    chars.add('c');
    Permutations permutations = new Permutations();
    System.out.println(permutations.solve(chars));
    System.out.println(permutations.solveWithBacktracking(chars));
  }

  private List<List<Character>> solve(List<Character> chars) {
    List<Character> processed = new ArrayList<>();
    visit(chars, processed);
    return result;
  }

  /**
   * What’s inefficient in the current solution
   *
   * <p>1. Excessive list creation inside loops. Inside the for loop you create: part1 part2
   * processedUpdated unprocessedUpdated (once per recursion level) That leads to O(n² · n!) list
   * operations instead of the optimal O(n · n!).
   *
   * <p>2. Rebuilding processed instead of modifying it in place You are reconstructing the list
   * every time instead of using backtracking (add → recurse → remove).
   *
   * <p>3. subList() + new ArrayList<>() everywhere subList() followed by copying is expensive and
   * avoidable.
   *
   * @param unprocessed - input list
   * @param processed - current processed list
   */
  private void visit(List<Character> unprocessed, List<Character> processed) {
    if (unprocessed.isEmpty()) {
      result.add(new ArrayList<>(processed));
      return;
    }
    Character ch = unprocessed.getFirst();
    List<Character> unprocessedUpdated =
        new ArrayList<>(unprocessed.subList(1, unprocessed.size()));
    for (int i = 0; i <= processed.size(); i++) {
      List<Character> part1 = new ArrayList<>(processed.subList(0, i));
      List<Character> part2 = new ArrayList<>(processed.subList(i, processed.size()));
      List<Character> processedUpdated = new ArrayList<>(part1);
      processedUpdated.add(ch);
      processedUpdated.addAll(part2);
      visit(unprocessedUpdated, processedUpdated);
    }
  }

  private List<List<Character>> solveWithBacktracking(List<Character> chars) {
    visitWithBacktracking(chars, 0, new ArrayList<>());
    return resultWithBacktracking;
  }

  /**
   * Optimal approach: in-place backtracking with index Key idea:
   *
   * <p>Build permutations incrementally. Insert the next character at all positions. Remove it
   * after recursion (backtrack)
   *
   * <p>Here Time complexity is O(n * n!) and memory usage is also minimal.
   *
   * @param input original list
   * @param index index of the original list
   * @param processed - current processed list
   */
  private void visitWithBacktracking(List<Character> input, int index, List<Character> processed) {
    if (index == input.size()) {
      resultWithBacktracking.add(new ArrayList<>(processed));
      return;
    }
    Character ch = input.get(index);
    for (int i = 0; i <= processed.size(); i++) {
      processed.add(i, ch); // insert
      visitWithBacktracking(input, index + 1, processed);
      processed.remove(i); // backtrack
    }
  }
}
