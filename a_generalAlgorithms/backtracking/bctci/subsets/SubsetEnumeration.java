package a_generalAlgorithms.backtracking.bctci.subsets;

import java.util.ArrayList;
import java.util.List;

public class SubsetEnumeration {
  List<List<Character>> result = new ArrayList<>();
  List<List<Character>> resultWithBacktracking = new ArrayList<>();

  public static void main(String[] args) {
    ArrayList<Character> chars = new ArrayList<>();
    chars.add('a');
    chars.add('b');
    chars.add('c');
    SubsetEnumeration subsetEnumeration = new SubsetEnumeration();
    System.out.println("solve(chars) = " + subsetEnumeration.solve(chars));
    System.out.println(
        "solve(chars) With Backtracking = " + subsetEnumeration.solveWithBackTracking(chars));
  }

  /**
   * Problems in the current code
   *
   * <p>Too many ArrayList creations. You create new ArrayLists for unprocessed and processed on
   * every recursion. This causes O(n² · 2ⁿ) memory operations.
   *
   * <p>Duplicate logic. unprocess and unprocess2 are identical. You compute the same sublist twice.
   *
   * <p>Passing lists instead of indices. Passing slices of lists is much slower than passing an
   * index.
   *
   * @param chars - Character Array
   * @return List of Subsets
   */
  private List<List<Character>> solve(List<Character> chars) {
    List<Character> processed = new ArrayList<>();
    visit(chars, processed);
    return result;
  }

  private void visit(List<Character> unprocessed, List<Character> processed) {
    if (unprocessed.isEmpty()) {
      result.add(processed);
      return;
    }
    Character ch = unprocessed.getFirst();
    visit(new ArrayList<>(unprocessed.subList(1, unprocessed.size())), new ArrayList<>(processed));

    List<Character> withChar = new ArrayList<>(processed);
    withChar.add(ch);
    visit(new ArrayList<>(unprocessed.subList(1, unprocessed.size())), withChar);
  }

  /**
   * Best optimization: use an index + backtracking
   *
   * <p>This is the standard and most efficient way.
   *
   * <p>We use the BAD method to analyze the time and space complexity of the backtracking solution:
   * O(b raise to power d * A). where: b is the branching factor - 2. d is the depth of the cal tree
   * which is n+1. A is the additional (non-recursive) work per node: O(n) for the copy at the
   * leaves.
   *
   * @param chars Character array
   * @return returns List of all subsets
   */
  private List<List<Character>> solveWithBackTracking(List<Character> chars) {
    List<Character> processed = new ArrayList<>();
    visitWithBackTracking(chars, 0, processed);
    return resultWithBacktracking;
  }

  private void visitWithBackTracking(List<Character> input, int index, List<Character> processed) {
    if (index == input.size()) {
      resultWithBacktracking.add(new ArrayList<>(processed)); // Copy once
      return;
    }
    visitWithBackTracking(input, index + 1, processed);
    processed.add(input.get(index));
    visitWithBackTracking(input, index + 1, processed);
    processed.removeLast();
  }
}
