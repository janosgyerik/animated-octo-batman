package com.janosgyerik.codereview.skiwi.coffee;

import java.util.*;

public class CoffeeMachine {
    private final List<Ingredient> ingredients = new ArrayList<>();
    private final Map<Ingredient, Integer> ingredientStock = new HashMap<>();
    private final List<Drink> drinks = new ArrayList<>();

    public CoffeeMachine(final Collection<? extends Ingredient> ingredients, final Map<? extends Ingredient, Integer> ingredientStock, final Collection<? extends Drink> drinks) {
        this.ingredients.addAll(ingredients);
        this.ingredientStock.putAll(ingredientStock);
        this.drinks.addAll(drinks);

        this.ingredients.forEach(ingredient -> this.ingredientStock.putIfAbsent(ingredient, 0));
    }

    public List<Ingredient> getIngredients() {
        return new ArrayList<>(ingredients);
    }

    public List<Drink> getDrinks() {
        return new ArrayList<>(drinks);
    }

    public int getCurrentStock(final Ingredient ingredient) {
        checkIsValidIngredient(ingredient);
        return ingredientStock.get(ingredient);
    }

    public boolean isOutOfStock(final Drink drink) {
        checkIsValidDrink(drink);
        return drink.getIngredientCount().entrySet().stream()
                .anyMatch(entry -> {
                    Ingredient ingredient = entry.getKey();
                    long count = entry.getValue();
                    return (ingredientStock.get(ingredient) < count);
                });
    }

    public void restock(final Map<? extends Ingredient, Integer> newIngredientStock) {
        this.ingredientStock.putAll(newIngredientStock);
    }

    public void makeDrink(final Drink drink) {
        checkIsValidDrink(drink);
        if (isOutOfStock(drink)) {
            throw new IllegalArgumentException("Drink " + drink + " is not in stock");
        }
        drink.getIngredients().forEach(this::decrementStock);
    }

    private void decrementStock(final Ingredient ingredient) {
        ingredientStock.compute(ingredient, (innerIngredient, stock) -> stock - 1);
    }

    private void checkIsValidIngredient(final Ingredient ingredient) {
        if (!ingredients.contains(ingredient)) {
            throw new IllegalArgumentException("Unknown ingredient: " + ingredient);
        }
    }

    private void checkIsValidDrink(final Drink drink) {
        if (!drinks.contains(drink)) {
            throw new IllegalArgumentException("Unknown drink: " + drink);
        }
    }
}