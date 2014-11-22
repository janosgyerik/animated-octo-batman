package com.janosgyerik.stackoverflow.vogel;

/**
 * Class for containing the countWords method. Will only start up the Screen and keep
 * running until screen terminates (or rather signals execution stop)
 *
 * @author vogel612
 *
 */
public class Program {

    public static void main(String[] args) {
        new Screen().startGame();
//        Screen screen = new Screen();
//        screen.startGame();
//        while (screen.isRunning()) {
//        }
    }
}