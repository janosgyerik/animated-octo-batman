package com.janosgyerik.codereview.skiwi.coffee;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Objects;

public class Ingredient {
    private final String name;
    private final BigDecimal cost;

    public Ingredient(final String name, final BigDecimal cost) {
        this.name = Objects.requireNonNull(name, "name");
        this.cost = Objects.requireNonNull(cost, "cost");
    }

    public String getName() {
        return name;
    }

    public BigDecimal getCost() {
        return cost;
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        Ingredient that = (Ingredient)obj;

        if (cost.compareTo(that.cost) != 0) return false;
        if (!name.equals(that.name)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + cost.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "(" + name + ", " + cost + ")";
    }

    public static Searcher<String, Ingredient> createSearcher(final Collection<? extends Ingredient> ingredients) {
        return new Searcher<>(Ingredient::getName, ingredients);
    }
}