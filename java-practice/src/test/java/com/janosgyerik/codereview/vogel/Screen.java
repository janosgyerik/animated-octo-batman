package com.janosgyerik.codereview.vogel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.LineBorder;

/**
 * Screen Class: Contains... everything frankly. Teams their colors a few settings and handlers for buttons.
 * While that maybe isn't the best idea, the simplicity of the problem allows this.
 *
 * @author vogel612
 *
 */
public class Screen extends JFrame {

    private static final long serialVersionUID = 8208211925302053925L;

    private static final Color teamOneColor = new Color(0.3f, 0.0f, 0.3f, 0.8f);
    private static final Color teamTwoColor = new Color(0.0f, 0.3f, 0.3f, 0.8f);
    private static final Color noTeamColor = new Color(0.8f, 0.8f, 0.8f);
    private static final Color black = new Color(0.0f, 0.0f, 0.0f);

    private enum Team {
        Team_1(teamOneColor, "Male 1 & Female 1"),
        Team_2(teamTwoColor, "Male 2 & Female 2");

        private final String teamName;
        private final Color teamColor;
        private int position;

        Team(Color color, String name) {
            this.teamName = name;
            this.teamColor = color;
            this.position = 0;
        }

        public int getPosition() {
            return position;
        }

        public Color getColor() {
            return teamColor;
        }

        public boolean advance(int by) {
            if (by == 0 || by < -1) {
                throw new IllegalArgumentException();
            }
            if (position + by > FIELD_COUNT) {
                position = FIELD_COUNT;
                return true;
            }
            this.position += by;
            return false; // no game end
        }

        public String getTeamName() {
            return teamName;
        }
    }

    private static final int FIELD_COUNT = 40;
    private static final int FIELD_WIDTH = 50;
    private static final int FIELD_HEIGHT = 50;
    private static final Dimension defaultFieldDimension = new Dimension(
            FIELD_WIDTH, FIELD_HEIGHT);
    private static final Dimension defaultWindowDimension = new Dimension(
            ((int) Math.sqrt(FIELD_COUNT) + 3) * FIELD_WIDTH,
            ((int) Math.sqrt(FIELD_COUNT) + 3) * FIELD_HEIGHT);

    private final JComboBox<Team> teamInput = new JComboBox<>(Team.values());
    private final JTextField textInput = new JTextField(1);
    private final JButton submit = new JButton("OK");

    private final EventQueue events = Toolkit.getDefaultToolkit()
            .getSystemEventQueue();

    private final List<JPanel> fields = new ArrayList<>(FIELD_COUNT + 1);

    public Screen() {
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setTitle("Hausspiel Frankenhofen");
        this.setPreferredSize(defaultWindowDimension);
        this.setSize(defaultWindowDimension);
        this.setLayout(new FlowLayout(FlowLayout.LEFT));
    }

    public void startGame() {
        initialize();
        this.setVisible(true);
        repaintPositions();
    }

    private void repaintPositions() {
        fields.stream().forEach(f -> f.setBackground(noTeamColor));
        Arrays.stream(Team.values()).forEach(t -> fields.get(t.getPosition()).setBackground(t.getColor()));

        this.repaint();
    }

    private void initialize() {
        createFields();
    }

    private void createFields() {
        for (int i = 0; i <= FIELD_COUNT; i++) {
            JPanel field = new JPanel();
            field.add(new JLabel((i == 0) ? "Start" : i + ""));
            field.setName(i + "");
            field.setSize(defaultFieldDimension);
            field.setPreferredSize(defaultFieldDimension);
            field.setBackground(noTeamColor);
            field.setBorder(new LineBorder(black));
            this.add(field);
            fields.add(field);
        }

        for (Team team : Team.values()) {
            JPanel legend = new JPanel() {
                private static final long serialVersionUID = -2035379018013186886L;

                {
                    JLabel teamLabel = new JLabel(team + ": " + team.getTeamName());
                    setBackground(team.getColor());
                    add(teamLabel);
                }
            };
            this.add(legend);
        }
//        Arrays.stream(Team.values()).forEach(t -> {
//            JPanel legend = new JPanel() {
//                private static final long serialVersionUID = -2035379018013186886L;
//
//                {
//                    JLabel teamLabel = new JLabel(t + ": " + t.getTeamName());
//                    setBackground(t.getColor());
//                    add(teamLabel);
//                }
//            };
//            this.add(legend);
//        });

        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (textInput.getText().isEmpty()) {
                    return;
                }
                int advanceBy;
                try {
                    advanceBy = Integer.parseInt(textInput.getText());
                } catch (NumberFormatException ex) {
                    return;
                }

                Team team = (Team) teamInput.getSelectedItem();
                if (team.advance(advanceBy)) {
                    showWinAlert(team);
                }
                repaintPositions();
                textInput.setText("");
            }

            private void showWinAlert(Team team) {
                JFrame winAlert = new JFrame("Gewonnen");
                winAlert.setSize(new Dimension(300, 150));
                winAlert.setLayout(new FlowLayout());
                winAlert.add(new JLabel("Sieger: " + team.getTeamName()));
                JButton dismiss = new JButton("OK");
                dismiss.addActionListener(a -> events.postEvent(new WindowEvent(winAlert,
                        WindowEvent.WINDOW_CLOSING)));
                winAlert.add(dismiss);
                SwingUtilities.invokeLater(() -> winAlert.setVisible(true));
            }
        });

        this.add(submit);
        this.add(textInput);
        this.add(teamInput);

        this.doLayout();
    }
}