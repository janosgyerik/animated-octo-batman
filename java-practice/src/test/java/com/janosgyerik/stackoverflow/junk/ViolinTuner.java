package com.janosgyerik.stackoverflow.junk;

import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

class ViolinTunerTest {
}

public class ViolinTuner {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Violin Tuner");

        ImageIcon icon = new ImageIcon("Images/Icon.png");
        frame.setIconImage(icon.getImage());

        JPanel panel = new JPanel();

        for (String key : new String[]{"G", "D", "A", "E"}) {
            JButton keyButton = new JButton(key);
            ActionListener listener = new KeyButtonListener(keyButton, String.format("Sounds/%s.wav", key));
            keyButton.addActionListener(listener);
            panel.add(keyButton);
        }

        frame.add(panel);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(225, 75);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    static class KeyButtonListener implements ActionListener {
        private final JButton button;
        private final String filename;

        KeyButtonListener(JButton button, String filename) {
            this.button = button;
            this.filename = filename;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            AudioInputStream audioInputStream = null;
            try {
                audioInputStream = AudioSystem.getAudioInputStream(new File(filename));
                Clip clip = AudioSystem.getClip();
                clip.open(audioInputStream);
                clip.start();
            } catch (IOException | UnsupportedAudioFileException | LineUnavailableException x) {
                x.printStackTrace();
            } finally {
                if (audioInputStream != null) {
                    try {
                        audioInputStream.close();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }
            }
        }
    }
}
