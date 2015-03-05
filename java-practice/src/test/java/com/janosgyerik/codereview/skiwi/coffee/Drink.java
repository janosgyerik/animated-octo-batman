package com.janosgyerik.codereview.skiwi.coffee;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

public class Drink {
    private final String name;
    private final List<Ingredient> ingredients = new ArrayList<>();

    private final BigDecimal cost;

    public Drink(final String name, final Ingredient... ingredients) {
        this(name, Arrays.asList(ingredients));
    }

    public Drink(final String name, final Collection<? extends Ingredient> ingredients) {
        this.name = Objects.requireNonNull(name, "name");
        this.ingredients.addAll(ingredients);

        this.cost = this.ingredients.stream().map(Ingredient::getCost).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public String getName() {
        return name;
    }

    public List<Ingredient> getIngredients() {
        return new ArrayList<>(ingredients);
    }

    public BigDecimal getCost() {
        return cost;
    }

    public Map<Ingredient, Long> getIngredientCount() {
        return ingredients.stream()
                .collect(Collectors.groupingBy(ingredient -> ingredient, Collectors.counting()));
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        Drink drink = (Drink)obj;

        if (!ingredients.equals(drink.ingredients)) return false;
        if (!name.equals(drink.name)) return false;

        return true;
    }

    @Override
    public String toString() {
        return "(" + name + ", " + ingredients + ")";
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + ingredients.hashCode();
        return result;
    }

    public static Searcher<String, Drink> createSearcher(final Collection<? extends Drink> drinks) {
        return new Searcher<>(Drink::getName, drinks);
    }
}
