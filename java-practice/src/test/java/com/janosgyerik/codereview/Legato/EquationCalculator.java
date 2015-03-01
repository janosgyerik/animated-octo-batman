package com.janosgyerik.codereview.Legato;

import javax.swing.*;
import java.awt.*;

public class EquationCalculator {
    static interface Equation {
        double compute(double val1, double val2);
    }

    enum Operation {
        Sum("Sum", (x, y) -> x + y),
        Difference("Difference", (x, y) -> x - y),
        Product("Product", (x, y) -> x * y),
        Quotient("Quotient", (x, y) -> x / y),
        Exponent("Exponent", Math::pow);

        private final String label;
        private final Equation equation;

        private Operation(String label, Equation equation) {
            this.label = label;
            this.equation = equation;
        }

        @Override
        public String toString() {
            return label;
        }
    }

    public static void main(String[] args) throws ClassNotFoundException, UnsupportedLookAndFeelException, InstantiationException, IllegalAccessException {
        for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
            if ("Nimbus".equals(info.getName())) {
                UIManager.setLookAndFeel(info.getClassName());
                break;
            }
        }

        SwingUtilities.invokeLater(EquationCalculator::new);
    }

    public EquationCalculator() {
        JFrame frame = new JFrame("Basic Operations");
        JPanel panel = new JPanel(new GridLayout(1, 3));
        JTextField first = new JTextField("2");
        JTextField second = new JTextField("3");
        JComboBox<Operation> calculate = new JComboBox<>();

        calculate.addItem(Operation.Sum);
        calculate.addItem(Operation.Difference);
        calculate.addItem(Operation.Product);
        calculate.addItem(Operation.Quotient);
        calculate.addItem(Operation.Exponent);
        calculate.addActionListener(e -> JOptionPane.showMessageDialog(null, "The result is " + calculate(((Operation) calculate.getSelectedItem()).equation, Double.parseDouble(first.getText()), Double.parseDouble(second.getText())), "Result", JOptionPane.INFORMATION_MESSAGE));

        panel.add(first);
        panel.add(second);
        panel.add(calculate);

        frame.add(panel);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public static double calculate(Equation operation, double val1, double val2) {
        return operation.compute(val1, val2);
    }
}