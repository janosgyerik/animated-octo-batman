package com.janosgyerik.codereview.skiwi.coffee;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class Default {
    private Default() {
        throw new UnsupportedOperationException();
    }

    private static final List<Ingredient> DEFAULT_INGREDIENTS = Arrays
            .asList(new Ingredient("Coffee", new BigDecimal("0.75")),
                    new Ingredient("Decaf Coffee", new BigDecimal("0.75")),
                    new Ingredient("Sugar", new BigDecimal("0.25")), new Ingredient("Cream", new BigDecimal("0.25")),
                    new Ingredient("Steamed Milk", new BigDecimal("0.35")),
                    new Ingredient("Foamed Milk", new BigDecimal("0.35")),
                    new Ingredient("Espresso", new BigDecimal("1.10")), new Ingredient("Cocoa", new BigDecimal("0.90")),
                    new Ingredient("Whipped Cream", new BigDecimal("1.00")));

    public static List<Ingredient> getDefaultIngredients() {
        return new ArrayList<>(DEFAULT_INGREDIENTS);
    }

    private static final List<Drink> DEFAULT_DRINKS = new ArrayList<>();

    static {
        Searcher<String, Ingredient> ingredientSearcher = Ingredient.createSearcher(getDefaultIngredients());
        DEFAULT_DRINKS.add(new Drink("Coffee",
                Arrays.asList(ingredientSearcher.search("Coffee"), ingredientSearcher.search("Coffee"),
                        ingredientSearcher.search("Coffee"), ingredientSearcher.search("Sugar"),
                        ingredientSearcher.search("Cream"))));
        DEFAULT_DRINKS.add(new Drink("Decaf Coffee",
                Arrays.asList(ingredientSearcher.search("Decaf Coffee"), ingredientSearcher.search("Decaf Coffee"),
                        ingredientSearcher.search("Decaf Coffee"), ingredientSearcher.search("Sugar"),
                        ingredientSearcher.search("Cream"))));
        DEFAULT_DRINKS.add(new Drink("Caffe Latte",
                Arrays.asList(ingredientSearcher.search("Espresso"), ingredientSearcher.search("Espresso"),
                        ingredientSearcher.search("Steamed Milk"))));
        DEFAULT_DRINKS.add(new Drink("Caffe Americano",
                Arrays.asList(ingredientSearcher.search("Espresso"), ingredientSearcher.search("Espresso"),
                        ingredientSearcher.search("Espresso"))));
        DEFAULT_DRINKS.add(new Drink("Caffe Mocha",
                Arrays.asList(ingredientSearcher.search("Espresso"), ingredientSearcher.search("Cocoa"),
                        ingredientSearcher.search("Steamed Milk"), ingredientSearcher.search("Whipped Cream"))));
        DEFAULT_DRINKS.add(new Drink("Cappuccino",
                Arrays.asList(ingredientSearcher.search("Espresso"), ingredientSearcher.search("Espresso"),
                        ingredientSearcher.search("Steamed Milk"), ingredientSearcher.search("Foamed Milk"))));
    }

    public static List<Drink> getDefaultDrinks() {
        return new ArrayList<>(DEFAULT_DRINKS);
    }
}