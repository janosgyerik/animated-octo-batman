package com.janosgyerik.codereview.skiwi.coffee;

import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class CoffeeMachineDisplay {
    private final CoffeeMachine coffeeMachine;
    private final Scanner scanner = new Scanner(System.in);

    private final List<Ingredient> indexedIngredients;
    private final List<Drink> indexedDrinks;

    public CoffeeMachineDisplay(final CoffeeMachine coffeeMachine) {
        this.coffeeMachine = Objects.requireNonNull(coffeeMachine, "coffeeMachine");

        this.indexedIngredients = coffeeMachine.getIngredients().stream()
                .sorted(Comparator.comparing(Ingredient::getName))
                .collect(Collectors.toList());
        this.indexedDrinks = coffeeMachine.getDrinks().stream()
                .sorted(Comparator.comparing(Drink::getName))
                .collect(Collectors.toList());
    }

    public void displayCurrentStock() {
        for (Ingredient ingredient : indexedIngredients) {
            int currentStock = coffeeMachine.getCurrentStock(ingredient);
            System.out.println("Ingredient: " + ingredient.getName() + " / Stock: " + currentStock + " / Cost: " + ingredient.getCost());
        }
        System.out.println();
    }

    public void displayMenu() {
        for (int i = 0; i < indexedDrinks.size(); i++) {
            Drink drink = indexedDrinks.get(i);
            System.out.print((i + 1) + ". Drink: " + drink.getName() + " / Cost: " + drink.getCost());
            if (coffeeMachine.isOutOfStock(drink)) {
                System.out.print(" [Out of stock]");
            }
            System.out.println();
        }
        System.out.println();
    }

    private static final Pattern IS_DIGIT_PATTERN = Pattern.compile("^\\d+$");
    private static final Pattern IS_R_PATTERN = Pattern.compile("^[rR]$");
    private static final Pattern IS_Q_PATTERN = Pattern.compile("^[qQ]$");

    public boolean processInputAndContinue() {
        System.out.print("Dispensing: ");

        String input = scanner.next();
        System.out.println();
        if (IS_DIGIT_PATTERN.matcher(input).matches()) {
            int selectedDrink = Integer.parseInt(input);
            if (selectedDrink < 1 || selectedDrink > indexedDrinks.size()) {
                System.out.println("Invalid drink number");
                System.out.println();
                return true;
            }
            Drink drink = indexedDrinks.get(selectedDrink - 1);
            if (coffeeMachine.isOutOfStock(drink)) {
                System.out.println("Drink " + drink + " is not in stock");
                System.out.println();
                return true;
            }
            coffeeMachine.makeDrink(drink);
            return true;
        }
        if (IS_R_PATTERN.matcher(input).matches()) {
            Map<Ingredient, Integer> newStock = coffeeMachine.getIngredients().stream()
                    .collect(Collectors.toMap(ingredient -> ingredient, ingredient -> 10));
            coffeeMachine.restock(newStock);
            System.out.println("Restocked");
            System.out.println();
            return true;
        }
        if (IS_Q_PATTERN.matcher(input).matches()) {
            System.out.println("Quit");
            return false;
        }
        System.out.println("Invalid input");
        System.out.println();
        return true;
    }
}