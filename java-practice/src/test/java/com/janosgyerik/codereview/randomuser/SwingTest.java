package com.janosgyerik.codereview.randomuser;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.TimerTask;

public class SwingTest {
}

@SuppressWarnings("serial")
class GameBoard extends JPanel {

    public final static int EARTH_ORBIT_RADIUS = 200;
    public final static int BOARD_WIDTH = 800;
    public final static int BOARD_HEIGHT = 600;
    public final static int INITIAL_EARTH_X = 700;
    public final static int INITIAL_EARTH_Y = 300;
    public final static int INITIAL_DELAY = 100;
    public final static int PERIOD_DELAY = 50;
    public final static int SUN_COORDINATE_X = 400;
    public final static int SUN_COORDINATE_Y = 300;
    public final static int INITIAL_ASTEROID_X = 500;
    public final static int INITIAL_ASTEROID_Y = 100;
    public final static int INITIAL_SHIP_X = 500;
    public final static int INITIAL_SHIP_Y = 500;

    public static int earth_x, earth_y;
    public static int asteroid_x, asteroid_y;
    public static int asteroid_x_previous = INITIAL_ASTEROID_X, asteroid_y_previous = INITIAL_ASTEROID_Y;
    public static int asteroid_velocity_x = 0, asteroid_velocity_y = 0;
    public static int ship_x, ship_y;
    public static int ship_x_previous = INITIAL_SHIP_X, ship_y_previous = INITIAL_SHIP_Y;
    public static int velocity_x = 0, velocity_y = 0;
    public int t = 0;
    public int ship_t = 0;
    public int keyheard = 0;
    public int[] earthcoordinates;
    public int[] asteroidcoordinates;
    public int[] shipcoordinates;
    public static String current_ship;

//    public static CollisionDetector c = new CollisionDetector();
    public static boolean isCollision = false;
    public static boolean we_are_mining = false;
    public static int mining_choice;

    Image earth;
    Image sun;
    Image asteroid;
    Image background;
    Image shipA, ship, shipL, shipR, shipU, shipD;

    Timer timer;

    JLabel rocketfuel_text = new JLabel();
    JLabel metals_text = new JLabel();

    GameBoard() {




        initGameBoard();
        keyListen();
        loadImage();


        earth_x = INITIAL_EARTH_X;
        earth_y = INITIAL_EARTH_Y;

        asteroid_x = INITIAL_ASTEROID_X;
        asteroid_y = INITIAL_ASTEROID_Y;

        ship_x = INITIAL_SHIP_X;
        ship_y = INITIAL_SHIP_Y;

//        timer = new Timer();
//        timer.scheduleAtFixedRate(new ScheduledTask(), INITIAL_DELAY,
//                PERIOD_DELAY);
    }

    void initGameBoard(){

        setBackground(Color.BLACK);
        setPreferredSize(new Dimension(BOARD_WIDTH, BOARD_HEIGHT));
        setDoubleBuffered(true);
        //addKeyListener(new KeyListen());
        setFocusable(true);

        rocketfuel_text.setVisible(true);
        rocketfuel_text.setForeground(Color.WHITE);
        this.add(rocketfuel_text);

        metals_text.setVisible(true);
        metals_text.setForeground(Color.WHITE);
        this.add(metals_text);

    }

    void loadImage() {
        ImageIcon earth_image_icon = new ImageIcon("earth2.png");
        earth = earth_image_icon.getImage();

        ImageIcon sun_image_icon = new ImageIcon("sun.png");
        sun = sun_image_icon.getImage();

        ImageIcon asteroid_image_icon = new ImageIcon("asteroid.png");
        asteroid = asteroid_image_icon.getImage();

        ImageIcon bg_image_icon = new ImageIcon("bg_pr.png");
        background = bg_image_icon.getImage();

        ImageIcon shipA_image_icon = new ImageIcon("ship_alpha.png");
        shipA = shipA_image_icon.getImage();

        ImageIcon ship_image_icon = new ImageIcon("ship_beta.png");
        ship = ship_image_icon.getImage();

        ImageIcon shipL_image_icon = new ImageIcon("ship_betaL.png");
        shipL = shipL_image_icon.getImage();

        ImageIcon shipR_image_icon = new ImageIcon("ship_betaR.png");
        shipR = shipR_image_icon.getImage();

        ImageIcon shipU_image_icon = new ImageIcon("ship_betaU.png");
        shipU = shipU_image_icon.getImage();

        ImageIcon shipD_image_icon = new ImageIcon("ship_betaD.png");
        shipD = shipD_image_icon.getImage();


    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        drawBackground(g);
        drawEarth(g);
        drawSun(g);
        drawAsteroid(g);
        drawShip(g);

    }

    void drawEarth(Graphics g) {

        g.drawImage(earth, earth_x, earth_y, this);
        Toolkit.getDefaultToolkit().sync();
    }

    void drawSun(Graphics g) {
        g.drawImage(sun, SUN_COORDINATE_X, SUN_COORDINATE_Y, this);
        Toolkit.getDefaultToolkit().sync();
    }

    void drawAsteroid(Graphics g) {
        g.drawImage(asteroid, asteroid_x, asteroid_y, this);
        Toolkit.getDefaultToolkit().sync();
    }

    void drawBackground(Graphics g) {
        g.drawImage(background, 0, 0, null);
    }

    void drawShip(Graphics g){
//        if(keyheard == 0 || c.whoCollision() == "earth"){g.drawImage(ship,ship_x, ship_y, this);}
//        else if(c.whoCollision() == "asteroid"){g.drawImage(shipA, ship_x, ship_y, this);}
        if(keyheard == 1){g.drawImage(shipL,ship_x, ship_y, this); current_ship =  "shipL";}
        else if(keyheard == 2){g.drawImage(shipR,ship_x, ship_y, this); current_ship =  "shipR";}
        else if(keyheard == 3){g.drawImage(shipU,ship_x, ship_y, this); current_ship =  "shipU";}
        else if(keyheard == 4){g.drawImage(shipD,ship_x, ship_y, this); current_ship =  "shipD";}
        else if(keyheard == 5){g.drawImage(ship,ship_x, ship_y, this); current_ship =  "ship";}

    }
    public class ScheduledTask extends TimerTask {



        @Override
        public void run() {


//            OrbitCalculation oe = new OrbitCalculation();
//            earthcoordinates = oe.earthOrbitCalculation(t);
//            earth_x = earthcoordinates[0];
//            earth_y = earthcoordinates[1];
//
//            OrbitCalculation oa = new OrbitCalculation();
//            asteroidcoordinates = oa.asteroidOrbitCalculation(t);
//            asteroid_x = asteroidcoordinates[0];
//            asteroid_y = asteroidcoordinates[1];
//
//
//            OrbitCalculation os = new OrbitCalculation();
//            shipcoordinates = os.shipOrbitCalculation(ship_t,keyheard, ship_x, ship_y);
//            ship_x = shipcoordinates[0];
//            ship_y = shipcoordinates[1];
//            velocity_x = ship_x - ship_x_previous;
//            velocity_y = ship_y - ship_y_previous;

            ship_x_previous = ship_x;
            ship_y_previous = ship_y;

            asteroid_x_previous = asteroid_x;
            asteroid_y_previous = asteroid_y;
            //System.out.println("key is" + keyheard);


            if(false){//c.whoCollision() == "asteroid"){
                ship_x = asteroid_x;
                ship_y = asteroid_y;

                isCollision = true;
                velocity_x = 0;
                velocity_y = 0;

//                if(MineCalculation.can_start_mining == true){
//                    you are now mining so can_start_mining must be false until you are done mining
//                    MineCalculation.can_start_mining = false;
//
//                    JFrame popup_want_to_mine = new JFrame();
//                    Icon pr_icon = new ImageIcon("pricon.gif");
//                    Object stringArray[] = {"Metals", "Rocket Fuel"};
//                    mining_choice = JOptionPane.showOptionDialog(popup_want_to_mine, "What Would You Like To Mine?", "You Landed On 2002 TC70!",
//                            JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, pr_icon, stringArray, stringArray[0]);
//
//                    we_are_mining = true;
//                }
                if(we_are_mining){

//                    MineCalculation m = new MineCalculation();
//                    This step starts the mining process depending on the value of mining_choice
//                    if(mining_choice == 0){
//                        m.mining("Metals");
//                    }
//                    if(mining_choice == 1){
//                        m.mining("Rocket Fuel");
//                    }
//
//
//                    if(!MineCalculation.is_mining){
//                        we_are_mining = false;
//                        if( mining_choice == 0){
//                            System.out.println("You are done mining Metals!");
//                            JFrame popup_done_mining = new JFrame();
//                            JOptionPane.showMessageDialog(popup_done_mining, "You are done mining for Metals");
//                        }
//                        if(mining_choice == 1){
//                            System.out.println("You are done mining Rocket Fuel!");
//                            JFrame popup_done_mining = new JFrame();
//                            JOptionPane.showMessageDialog(popup_done_mining, "You are done mining for Rocket Fuel");
//                        }
//
//                        t = 0;
//                        keyheard = 0;
//                        MineCalculation.can_start_mining = true;
//                        MineCalculation.rocketfuel_amount = 0;
//                        MineCalculation.metals_amount = 0;
//
//                    }
                }


                /** Here a bit of code that produces a string called
                 * mining_popup_answer which feeds into the
                 * MineCalculation.Mining() method  */

            }
//            else if(c.whoCollision() == "earth"){
//                ship_x = earth_x;
//                ship_y = earth_y;
//
//                isCollision = true;
//                velocity_x = 0;
//                velocity_y = 0;
//            }
            else{
                isCollision = false;}

            t = t + 1;
            ship_t = ship_t + 1;

//            rocketfuel_text.setText(Double.toString(MineCalculation.rocketfuel_amount));
//            repaint();
//            metals_text.setText(Double.toString(MineCalculation.metals_amount));
//            repaint();
        }
    }

    private class KeyPressAction extends AbstractAction {
        private final int key;

        private KeyPressAction(int key) {
            this.key = key;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            keyheard = key;
        }
    }

    public void keyListen(){

        InputMap im = getInputMap(WHEN_FOCUSED);
        ActionMap am = getActionMap();

        KeyStroke left_press = KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0, false);
        im.put(left_press, "Left_Press");
        am.put("Left_Press", new KeyPressAction(1));

        am.put("Left_Press", new KeyPressAction(1));
        am.put("Left_Release", new KeyPressAction(5));
        am.put("Right_Press", new KeyPressAction(2));
        am.put("Up_Press", new KeyPressAction(3));

        KeyStroke left_release = KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0, true);
        im.put(left_release, "Left_Release");
        am.put("Left_Release", new AbstractAction(){public void actionPerformed(ActionEvent arg0) {keyheard = 5;}});

        KeyStroke right_press = KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0, false);
        im.put(right_press, "Right_Press");
        am.put("Right_Press", new AbstractAction(){public void actionPerformed(ActionEvent arg0) {keyheard = 2;}});

        KeyStroke right_release = KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0, true);
        im.put(right_release, "Right_Release");
        am.put("Right_Release", new AbstractAction(){public void actionPerformed(ActionEvent arg0) {keyheard = 5;}});

        KeyStroke up_press = KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0, false);
        im.put(up_press, "Up_Press");
        am.put("Up_Press", new AbstractAction(){public void actionPerformed(ActionEvent arg0) {keyheard = 3;}});

        KeyStroke up_release = KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0, true);
        im.put(up_release, "Up_Release");
        am.put("Up_Release", new AbstractAction(){public void actionPerformed(ActionEvent arg0) {keyheard = 5;}});

        KeyStroke down_press = KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0, false);
        im.put(down_press, "Down_Press");
        am.put("Down_Press", new AbstractAction(){public void actionPerformed(ActionEvent arg0) {keyheard = 4;}});

        KeyStroke down_release = KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0, true);
        im.put(down_release, "Down_Release");
        am.put("Down_Release", new AbstractAction(){public void actionPerformed(ActionEvent arg0) {keyheard = 5;}});

    }
}
