package com.janosgyerik.codereview.Emz;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

class GUI implements ActionListener {
    public static final int WINDOW_MIN_X = 240;
    public static final int WINDOW_MIN_Y = 200;

    public static final int COLS = 3;
    public static final int ROWS = 3;
    public static final int HGAP = 2;
    public static final int VGAP = 2;

    public static final int NUMBER_OF_CELLS = ROWS * COLS;

    private final JFrame frame;
    private final List<Cell> buttons = new ArrayList<>();
    private Cell emptyButton;

    private int movesCounter;

    /**
     * Constructor for objects of class GUI
     */
    public GUI() {
        frame = new JFrame("Slide Game");

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        frame.setMinimumSize(new Dimension(WINDOW_MIN_X, WINDOW_MIN_Y));

        frame.setLayout(new GridLayout(ROWS, COLS, HGAP, VGAP));

        for (Integer i = NUMBER_OF_CELLS - 1; i >= 0; i--) {
            createCell(i);
        }

        emptyButton = buttons.get(NUMBER_OF_CELLS - 1);

        frame.setVisible(true);
        frame.pack();
    }

    public void actionPerformed(ActionEvent e) {
        if (checkNeighbour((Cell) e.getSource())) {
            swap((Cell) e.getSource());
            movesCounter++;
            checkVictory();
        } else {
            Toolkit.getDefaultToolkit().beep();
        }
    }

    /**
     * createCell (String s)
     * <p>
     * Creates a Cell and adds an ActionListener
     *
     * @param v String s
     */
    private void createCell(int v) {
        Cell c = new Cell(v);
        c.addActionListener(this::actionPerformed);
        buttons.add(c);
        frame.add(c);
    }

    /**
     * checkNeighbour (Cell c)
     * <p>
     * Checks if c is within 1 grid of emptyButton (non-diagonal)
     *
     * @param c Cell c
     * @return false if c is more than one grid away from emptyButton
     */
    private boolean checkNeighbour(Cell c) {
        int x = Math.abs(c.getCol() - emptyButton.getCol());
        int y = Math.abs(c.getRow() - emptyButton.getRow());

        System.out.printf("%s, %s\n", x, y);

        return x == 0 && y == 1 || y == 0 && x == 1;
    }

    /**
     * swap (Cell c)
     * <p>
     * Swaps c with emptyButton
     *
     * @param c Cell c
     */
    private void swap(Cell c) {
        // swap c and empty
        String tmp = emptyButton.getText();
        emptyButton.setText(c.getText());
        c.setText(tmp);

        int value = c.getValue();
        c.setValue(emptyButton.getValue());
        emptyButton.setValue(value);

        // update reference to empty button
        emptyButton = c;
    }

    /**
     * checkVictory ()
     * <p>
     * Compares buttons to correct lists, if match declare victory!
     */
    private void checkVictory() {
        for (int i = 0; i < NUMBER_OF_CELLS - 1; i++) {
            if (buttons.get(i).getValue() != i + 1) {
//                System.out.printf("%s != %s\n", i + 1, buttons.get(i).getValue());
                return;
            }
            System.out.printf("%s ok\n", i + 1);
        }
        System.out.println("should be good!");

        JOptionPane.showMessageDialog(null, "You achieved victory in " + movesCounter + " moves.", "Congratulations!", JOptionPane.INFORMATION_MESSAGE);
    }
}

class Cell extends JButton {
    private static final long serialVersionUID = 8822510600483479089L;
    private final int row;
    private final int col;
    private int value;

    public Cell(Integer value) {
        if (value == 0) {
            setText(" ");
        } else {
            setText(value.toString());
        }
        setText(value == 0 ? " " : value.toString());
        setText(value == 0 ? " " : String.valueOf(value));
        this.value = value;

        row = GUI.NUMBER_OF_CELLS / GUI.COLS - 1 - (value / GUI.COLS);
        col = GUI.NUMBER_OF_CELLS - 1 - (row * GUI.COLS + value);
    }

    public int getCol() {
        return col;
    }

    public int getRow() {
        return row;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}

public class CellGame {
    public static void main(String[] args) {
        new GUI();
    }
}
