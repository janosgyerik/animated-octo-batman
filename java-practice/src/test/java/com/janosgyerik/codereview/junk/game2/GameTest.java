package com.janosgyerik.codereview.junk.game2;

import org.junit.Test;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

abstract class Game {
    private final int targetFPS;
    private boolean running = true;

    private long runningFPS;

    protected Game(int fps) {
        this.targetFPS = fps;
    }

    public void run(JPanel panel, BufferedImage image) {
        int currentFPS = 0;
        long counterstart = System.nanoTime();
        long counterelapsed = 0;
        long start;
        long elapsed;
        long wait;
        long targetTime = 1000 / targetFPS;

        while (running) {
            start = System.nanoTime();

            processInput();
            update();
            // time to update and process input
            elapsed = System.nanoTime() - start;
            wait = targetTime - elapsed / 1000000;
            if (hasTimeToDraw(wait)) {
                //CREATE AND ANTIALIAS GRAPHICS
                Graphics2D g = image.createGraphics();
                g.addRenderingHints(new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON));
                g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
                g.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
                //Draw
                draw(g);
                g.dispose();
                panel.repaint();
                //Take account for the time it took to draw
                elapsed = System.nanoTime() - start;
                wait = targetTime - elapsed / 1000000;
            }
            counterelapsed = System.nanoTime() - counterstart;
            currentFPS++;

            // at the end of every second
            if (counterelapsed >= 1000000000L) {
                //runningFPS is how many frames we processed last second
                runningFPS = currentFPS;
                currentFPS = 0;
                counterstart = System.nanoTime();
            }

            if (wait > 0) {
                try {
                    Thread.sleep(wait);
                } catch (InterruptedException e) {
                    // e.printStackTrace();
                }
            }
        }
    }

    public long getCurrentFPS() {
        return runningFPS;
    }

    private boolean hasTimeToDraw(long wait) {
        //Not really sure how to implement this method... Maybe just time the draw method and hardcode it in?
        return true;
    }

    public void stop() {
        running = false;
    }

    public abstract void processInput();

    public abstract void update();

    public abstract void draw(Graphics2D g);
}

class GameFrame extends JFrame {

    private static final long serialVersionUID = 1L;
    private static final int WIDTH = 800;
    private static final int HEIGHT = 800;
    private final BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_3BYTE_BGR);
    private final JPanel panel;

    public GameFrame() {
        panel = new JPanel() {
            private static final long serialVersionUID = 1L;

            @Override
            protected void paintComponent(Graphics g) {
                g.drawImage(image, 0, 0, null);
            }
        };
        panel.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        this.add(panel);
        this.pack();
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        new GameFrame().run();
    }

    private Game game = new Game(60) {
        int i = 0;
        int x = 5;

        @Override
        public void processInput() {
            // TODO Auto-generated method stub

        }

        @Override
        public void update() {
            GameFrame.this.setTitle("FPS: " + getCurrentFPS());
            if (i > WIDTH || i < 0)
                x = -x;

            i += x;
        }

        @Override
        public void draw(Graphics2D g) {
            g.setColor(Color.BLACK);
            g.fillRect(0, 0, WIDTH, HEIGHT);

            g.setColor(Color.RED);
            g.fillRect(i, 50, 20, 53);
        }

    };

    public void run() {
        game.run(panel, image);
    }


}

public class GameTest {
    @Test
    public void testNothing() {

    }
}
