package com.janosgyerik.stackoverflow.winterbash.warriors;

import org.junit.Test;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedWriter;

class Human {
    protected String name;
    protected int health;
    protected int armorLevel;
    protected int magicLevel;
    protected int experience;
    protected int level;

    Human() {
        name = "Andrew The Magic";
        health = 100;
        armorLevel = 1;
        magicLevel = 1;
        experience = 0;
        level = 1;
    }

    Human(String name) {
        this(); //calling main constructor
        this.name = name;
    }

    void fight() {
    }

    void levelUp() {
    }

    void showAbility() {
        System.out.println("Name: " + name + " HP: " + health + " ARMOR: " + armorLevel + " MAGIC: " + magicLevel + " EXP: " + experience + " LVL: " + level);
    }

    void yell() {
        System.out.println("IM A CHARACTER ;-(");
    }
}

//--------------------------------------------------------------
class Warrior extends Human {

    Warrior() {
        super();
    }

    Warrior(String name) {
        super(name);
    }

    void fight() {
        System.out.println("I attack with sword");
        experience += 10;
        if (experience >= 20) {
            levelUp();
        }
    }

    void levelUp() {
        System.out.println("LEVEL UP!");
        health += 100;
        armorLevel += 25;
        level++;
    }

    void yell() {
        System.out.println("IM A GLORIOUS WARRIOR " + name);
    }
}

class World {


    static void greeting(Human p) {
        p.yell();
    }

    public static void main(String[] args) {
        Warrior w = new Warrior("Noname the Warrior");
        w.showAbility();
        w.fight();
        w.showAbility();
        w.fight();
        w.showAbility();

        greeting(w);
    }
}

public class WarriorTest {
    @Test
    public void testExample() {
        Warrior w = new Warrior("Noname the Warrior");
        w.showAbility();
        w.fight();
        w.showAbility();
        w.fight();
        w.showAbility();
        w.yell();
    }
}
