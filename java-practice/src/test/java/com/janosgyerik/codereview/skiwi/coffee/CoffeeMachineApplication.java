package com.janosgyerik.codereview.skiwi.coffee;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CoffeeMachineApplication {
    public static void main(String[] args) {
        List<Ingredient> ingredients = Default.getDefaultIngredients();
        Map<Ingredient, Integer> ingredientStock = ingredients.stream().
                collect(Collectors.toMap(ingredient -> ingredient, ingredient -> 10));
        List<Drink> drinks = Default.getDefaultDrinks();

        CoffeeMachine coffeeMachine = new CoffeeMachine(ingredients, ingredientStock, drinks);
        CoffeeMachineDisplay coffeeMachineDisplay = new CoffeeMachineDisplay(coffeeMachine);

        do {
            coffeeMachineDisplay.displayCurrentStock();
            coffeeMachineDisplay.displayMenu();
        } while (coffeeMachineDisplay.processInputAndContinue());
    }
}