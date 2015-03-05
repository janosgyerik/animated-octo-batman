package com.janosgyerik.codereview.skiwi.coffee;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CoffeeMachineTest {
    @Test
    public void test() {
        List<Ingredient> ingredients = Default.getDefaultIngredients();
        Map<Ingredient, Integer> ingredientStock = ingredients.stream().
                collect(Collectors.toMap(ingredient -> ingredient, ingredient -> 10));
        List<Drink> drinks = Default.getDefaultDrinks();

        CoffeeMachine coffeeMachine = new CoffeeMachine(ingredients, ingredientStock, drinks);

        Searcher<String, Ingredient> ingredientSearcher = Ingredient.createSearcher(Default.getDefaultIngredients());
        Drink justCoffee = new Drink("Just Coffee", Arrays.asList(ingredientSearcher.search("Coffee")));
//        coffeeMachine.makeDrink(justCoffee);

        coffeeMachine = new CoffeeMachine(Arrays.asList(), ingredientStock, drinks);
//        coffeeMachine = new CoffeeMachine(Arrays.asList(), Collections.emptyMap(), drinks);
        while (!coffeeMachine.isOutOfStock(drinks.get(0))) {
            coffeeMachine.makeDrink(drinks.get(0));
        }
//        coffeeMachine.makeDrink(drinks.get(0));
//        coffeeMachine.makeDrink(drinks.get(0));
//        coffeeMachine.makeDrink(drinks.get(0));
//        coffeeMachine.makeDrink(drinks.get(0));
//        coffeeMachine.makeDrink(drinks.get(0));
    }
}
