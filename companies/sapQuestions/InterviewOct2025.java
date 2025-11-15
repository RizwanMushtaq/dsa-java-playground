package companies.sapQuestions;

import java.util.*;

/**
 * Your task is to write a Java method that takes two arguments: a customer's shopping list and a
 * recipe.
 *
 * <p>The method must validate the shopping list against the recipe and return a detailed, sorted
 * list of validation errors. The validation must adhere to the following rules:
 *
 * <p>Required Ingredients: All non-optional ingredients from the recipe must be present in the
 * shopping list.
 *
 * <p>Correct Quantities: For every ingredient present in the shopping list that is also in the
 * recipe, the quantity must match exactly.
 *
 * <p>No Extra Items: The shopping list must not contain any ingredients that are not in the recipe.
 *
 * <p>Output: If the shopping list is valid, return an empty list. Otherwise, return a list of
 * validation errors containing the ingredient's name, the error type (e.g., "missing", "incorrect
 * quantity", "ingredient not needed"), and the expected and found quantities. The final errors must
 * be sorted alphabetically by the ingredient name.
 *
 * <p>ingresients = {""}
 */
public class InterviewOct2025 {

  public static void main(String[] args) {
    List<Ingredient> shopping = new ArrayList<>();
    shopping.add(new Ingredient("a", 5, false));
    shopping.add(new Ingredient("aa", 5, false));
    shopping.add(new Ingredient("b", 15, false));
    shopping.add(new Ingredient("d", 15, false));

    List<Ingredient> recipe = new ArrayList<>();
    recipe.add(new Ingredient("a", 5, false));
    recipe.add(new Ingredient("b", 5, false));
    recipe.add(new Ingredient("c", 5, false));

    List<Error> result = new InterviewOct2025().solve(shopping, recipe);
    System.out.println(result);
  }

  List<Error> solve(List<Ingredient> shoppingList, List<Ingredient> recipe) {
    List<Error> errors = new ArrayList<>();

    List<Ingredient> nonOptionalIngredients = new ArrayList<>();
    for (Ingredient item : recipe) {
      if (!item.optional()) {
        nonOptionalIngredients.add(item);
      }
    }

    for (Ingredient item : nonOptionalIngredients) {
      if (!shoppingList.contains(item)) {
        errors.add(new Error(item.name(), ErrorType.Missing));
      }
    }

    for (Ingredient item : shoppingList) {
      for (Ingredient ingredient : recipe) {
        if (Objects.equals(ingredient.name(), item.name())) {
          if (ingredient.quantity() != item.quantity()) {
            errors.add(new Error(item.name(), ErrorType.IncorrectQuantity));
          }
        }
      }
    }

    for (Ingredient item : shoppingList) {
      boolean isPresent = false;
      for (Ingredient ingredient : recipe) {
        if (Objects.equals(ingredient.name(), item.name())) {
          isPresent = true;
          break;
        }
      }
      if (!isPresent) {
        errors.add(new Error(item.name(), ErrorType.IngredientNotNeeded));
      }
    }

    errors.sort(Comparator.comparing(Error::ingredientName));

    return errors;
  }

  enum ErrorType {
    Missing("missing"),
    IncorrectQuantity("incorrect quantity"),
    IngredientNotNeeded("ingredient not needed");

    private final String description;

    ErrorType(String description) {
      this.description = description;
    }

    public String getDescription() {
      return description;
    }
  }

  record Error(String ingredientName, ErrorType type) {}

  record Ingredient(String name, int quantity, boolean optional) {}
}
