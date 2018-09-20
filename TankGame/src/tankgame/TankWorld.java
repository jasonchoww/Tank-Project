/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tankgame;

// ****RUN THIS FILE**** THIS WILL GENERATE THE APPLICATION ****
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JApplet;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.LayoutManager;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.OverlayLayout;

/**
 *
 * @author jasonchow
 */
public class TankWorld extends JApplet implements Runnable {

    //Thread
    Thread thread;
    Event event1;
    Event event2;
    BufferedImage Eimge;

    //Wall Object
    static GameObstacles ww;
    
    //Power Up
    PowerUp powerup;

    //World Variables
    Graphics2D gfx;
    Image backgroundImg;

    Image tank;
    Player player1;
    Player player2;
    private BufferedImage imge, startPage, gameOver;
    private BufferedImage P1Screen;
    private BufferedImage P2Screen;
    private BufferedImage miniMapScreen;
    private BufferedImage redPowerUp;
    private BufferedImage bluePowerUp;
    private BufferedImage[] P1Lives = new BufferedImage[4];
    private BufferedImage[] P2Lives = new BufferedImage[4];

    public boolean P1FreeRoam = true;
    public boolean P2FreeRoam = true;
    
    

    CollisionCheck CC;

    //Window size of game
    int w = 1080, h = 720;

    //Split Screen
    JPanel panel = new JPanel();
    JPanel miniMap = new JPanel();
    JLabel P1Life = new JLabel();
    JLabel P2Life = new JLabel();

    static ArrayList<Bullet> bList;
    static ArrayList<EnemyTank> eList;
    static ArrayList<PowerUp> pList;

    private Random ha;
    private Random powerupRandom = new Random();
    
    Sound bmusic;
    //Sound emusic;
    private int timer, endTimer;
    /**
     * Initialization method that will be called after the applet is loaded into
     * the browser.
     */
    @Override
    public void init() {
        timer = 100;
        endTimer = 200000;
        // TODO start asynchronous download of heavy resources
        setBackground(Color.white);
        setFocusable(true);
        try {
            backgroundImg = ImageIO.read(TankWorld.class.getResource("Images/Background.bmp"));
            Eimge = ImageIO.read(TankWorld.class.getResource("Images/Tank2.gif"));
            startPage=ImageIO.read(TankWorld.class.getResource("Images/Title.bmp"));
            gameOver = ImageIO.read(TankWorld.class.getResource("Images/gameover.jpg"));
           
            redPowerUp = ImageIO.read(TankWorld.class.getResource("Images/Shield1.gif"));
            bluePowerUp = ImageIO.read(TankWorld.class.getResource("Images/Shield2.gif"));
            

            P1Lives[0] = ImageIO.read(Tank.class.getResource("Images/P1gameover.bmp"));
            P1Lives[1] = ImageIO.read(Tank.class.getResource("Images/P1_LIVE1.bmp"));
            P1Lives[2] = ImageIO.read(Tank.class.getResource("Images/P1_LIVE2.bmp"));
            P1Lives[3] = ImageIO.read(Tank.class.getResource("Images/P1_LIVE3.bmp"));
            
            P2Lives[0] = ImageIO.read(Tank.class.getResource("Images/P2gameover.bmp"));
            P2Lives[1] = ImageIO.read(Tank.class.getResource("Images/P2_LIVE1.bmp"));
            P2Lives[2] = ImageIO.read(Tank.class.getResource("Images/P2_LIVE2.bmp"));
            P2Lives[3] = ImageIO.read(Tank.class.getResource("Images/P2_LIVE3.bmp"));

        } catch (Exception e) {
            System.out.println("ERROR: Invalid Resource");
        }
        bList = new ArrayList<Bullet>();
        eList = new ArrayList<EnemyTank>();
        pList = new ArrayList<PowerUp>();
        
        try {
            //gfx = backgroundImg.getGraphics();
            player1 = new Player(1, 3);
        } catch (IOException ex) {
            Logger.getLogger(TankWorld.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            player2 = new Player(2, 3);
        } catch (IOException ex) {
            Logger.getLogger(TankWorld.class.getName()).log(Level.SEVERE, null, ex);
        }
        event1 = new Event();
        event2 = new Event();
        if (event1 == null||event2==null) {
            System.out.println("GE null!");
        }
        KeyControl key = new KeyControl(event1, player1.getTank(), KeyEvent.VK_UP, KeyEvent.VK_DOWN, KeyEvent.VK_LEFT, KeyEvent.VK_RIGHT, KeyEvent.VK_SPACE);
        this.addKeyListener(key);
        this.event1.addObserver(player1.getTank());

        KeyControl key2 = new KeyControl(event1, player2.getTank(), KeyEvent.VK_W, KeyEvent.VK_S, KeyEvent.VK_A, KeyEvent.VK_D, KeyEvent.VK_Q);
        this.addKeyListener(key2);
        this.event1.addObserver(player2.getTank());
        ha = new Random();

        
        try {
            //System.out.println("Test");
            //INIT Enemy Tanks
            for (int i = 0; i < 10; i++) {
                int numx = ha.nextInt(900) + 450;
                int numy = ha.nextInt(500) + 350;
                eList.add(new EnemyTank(Eimge, numx, numy));

            }
        } catch (IOException ex) {
            Logger.getLogger(TankWorld.class.getName()).log(Level.SEVERE, null, ex);

        }

        //INIT Wall 
        ww = new GameObstacles();
        ww.draw();

        //Collision
        CC = new CollisionCheck();
        
        //music
        bmusic = new Sound("Images/bmusic.wav");

        //Power Up
        int n = powerupRandom.nextInt(10)+1;
        if (n > 5) {
            pList.add(new PowerUp(redPowerUp, 800, 450, 0));
            pList.add(new PowerUp(bluePowerUp, 1400, 450, 1));
            pList.add(new PowerUp(bluePowerUp, 1100, 650, 1));
        } else {
            pList.add(new PowerUp(bluePowerUp, 800, 450, 1));
            pList.add(new PowerUp(redPowerUp, 1400, 450, 0));
            pList.add(new PowerUp(redPowerUp, 1100, 650, 0));
            
        }
       
     
        

    }

    public void screenCaptureP1() {

        if (player1.getTank().xLocation() <= w / 2 && player1.getTank().yLocation() <= h) {
            P1Screen = imge.getSubimage(0, 0, w / 2, h);
            P1FreeRoam = false;
        } else {
            P1FreeRoam = true;
        }

        if (player1.getTank().xLocation() <= w / 2 && player1.getTank().yLocation() >= 720) {
            P1Screen = imge.getSubimage(0, 1440 - h, w / 2, h);
            P1FreeRoam = false;
        } else {
            P1FreeRoam = true;
        }

        if (player1.getTank().xLocation() >= 1970 - (w / 2) && player1.getTank().yLocation() <= h) {
            P1Screen = imge.getSubimage(1920 - (w / 2), 0, w / 2, h);

            P1FreeRoam = false;
        } else {
            P1FreeRoam = true;
        }

        /*
        if (player1.getTank().xLocation() >= 1970 - (w / 2) && player1.getTank().yLocation() >= 1680 - h) {
            P1Screen = imge.getSubimage(1920 - (w / 2), 1440 - h, w / 2, h);
            P1FreeRoam = false;
        } else {
            P1FreeRoam = true;
        }
*/
        if (P1FreeRoam) {
            P1Screen = imge.getSubimage(player1.getTank().xLocation() - 270, player1.getTank().yLocation() - 360, w / 2, h);

            if (player1.getTank().xLocation() - 200 > 200 || player1.getTank().xLocation() - 200 < 1520) {
                P1Screen = imge.getSubimage(player1.getTank().xLocation() - 200, player1.getTank().yLocation() - 300, w / 2, h);
                if (player1.getTank().yLocation() - 300 > 300 || player1.getTank().xLocation() - 200 < 840) {
                    P1Screen = imge.getSubimage(player1.getTank().xLocation() - 200, player1.getTank().yLocation() - 300, w / 2, h);
                }
            }

            P1FreeRoam = true;
        }

    }

    public void screenCaptureP2() {

        if (player2.getTank().xLocation() <= w / 2 && player2.getTank().yLocation() <= h) {
            P2Screen = imge.getSubimage(0, 0, w / 2, h);
            P2FreeRoam = false;
        } else {
            P2FreeRoam = true;
        }

        if (player2.getTank().xLocation() <= w / 2 && player2.getTank().yLocation() >= 720) {
            P2Screen = imge.getSubimage(0, 1440 - h, w / 2, h);
            P2FreeRoam = false;
        } else {
            P2FreeRoam = true;
        }

        if (player2.getTank().xLocation() >= 1970 - (w / 2) && player2.getTank().yLocation() <= h) {
            P2Screen = imge.getSubimage(1920 - (w / 2), 0, w / 2, h);

            P2FreeRoam = false;
        } else {
            P2FreeRoam = true;
        }

        /*
        if (player2.getTank().xLocation() >= 1970 - (w / 2) && player2.getTank().yLocation() >= 1680 - h) {
            P2Screen = imge.getSubimage(1920 - (w / 2), 1440 - h, w / 2, h);
            P2FreeRoam = false;
        } else {
            P2FreeRoam = true;
        }
*/

        if (P2FreeRoam) {
            if (player2.getTank().xLocation() - 200 > 200 || player2.getTank().xLocation() - 200 < 1520) {
                P2Screen = imge.getSubimage(player2.getTank().xLocation() - 200, player2.getTank().yLocation() - 300, w / 2, h);
                if (player2.getTank().yLocation() - 300 > 300 || player2.getTank().xLocation() - 200 < 840) {
                    P2Screen = imge.getSubimage(player2.getTank().xLocation() - 200, player2.getTank().yLocation() - 300, w / 2, h);
                }
            }

            P2FreeRoam = true;
        }

    }

    @Override
    public void paint(Graphics g) {
        if (imge == null) {

            imge = (BufferedImage) createImage(2240, 1680);

            //Tank Background 
            gfx = imge.createGraphics();
        }
        demo();
        if(timer>0){
          timer--;
          g.drawImage(startPage, 0, 0, this);
        }
        else{
        //Player Life remaining
        if(player1.getTank().getLifeCount()>=0){
        g.drawImage(P1Lives[player1.getTank().getLifeCount()], 0, 0, panel);
        }
         if(player2.getTank().getLifeCount()>=0){
        g.drawImage(P2Lives[player2.getTank().getLifeCount()], 700, 0, panel);
         }

        //Split Screen Capture & draw
        screenCaptureP1();
        screenCaptureP2();
        miniMapScreen = imge.getSubimage(288, 288, 1680, 1000);
        g.drawImage(miniMapScreen, w / 2 - 160, 0, 320, 200, miniMap);
        g.drawImage(P1Screen, 0, 200, panel);
        g.drawImage(P2Screen, (w + 2) / 2, 200, panel);
        if(eList.isEmpty()){
            while(endTimer > 0){
            endTimer--;
            g.drawImage(gameOver, 0, 0, 1080, 920, panel);
            /*
                try {
                    thread.sleep(2000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(TankWorld.class.getName()).log(Level.SEVERE, null, ex);
                }
*/
            }
            System.exit(0);
        }
        }
    }

    public void demo() {
        //Draws Background
        for (int i = 0; i < 2240; i += 320) {
            for (int j = 0; j < 1680; j += 240) {
                gfx.drawImage(backgroundImg, i, j, this);
            }
        }

        //Draws wall obstacles in map
        //Collision
        for (int e = 0; e < eList.size(); e++) {
            CC.TankandETank(player1.getTank(), eList.get(e));
            CC.TankandETank(player2.getTank(), eList.get(e));
        }

        // for (int i = 0; i < ww.getList().size(); i++) {
        //   CC.TankandWall(player1.getTank(), (Wall) ww.getList().get(i));
        // CC.TankandWall(player2.getTank(), (Wall) ww.getList().get(i));
        // }
        for (int i = 0; i < ww.getList().size(); i++) {
            for (int q = 0; q < eList.size(); q++) {
                CC.eTankandWall(eList.get(q), (Wall) ww.getList().get(i));
            }
        }

        for (int i = 0; i < ww.getList().size(); i++) {
            for (int b = 0; b < bList.size(); b++) {
                CC.BulletandWall(bList.get(b), (Wall) ww.getList().get(i));
            }
        }
        for (int i = 0; i < ww.getList().size(); i++) {
            Wall d = (Wall) ww.getList().get(i);
            d.draw(gfx, this);
        }

        for (int b = 0; b < bList.size(); b++) {
            CC.BulletandPTank(player1.getTank(), bList.get(b));
        }
        for (int b = 0; b < bList.size(); b++) {
            CC.BulletandPTank(player2.getTank(), bList.get(b));
        }
        for (int e = 0; e < eList.size(); e++) {
            for (int b = 0; b < bList.size(); b++) {
                CC.BulletandETank(eList.get(e), bList.get(b));
            }
        }
        
       for (int i = 0; i < pList.size(); i++) {
            CC.TankandPowerUp(player1.getTank(), pList.get(i));
            CC.TankandPowerUp(player2.getTank(), pList.get(i));
        }
        
        

        //Tank Draw
        for (int i = 0; i < eList.size(); i++) {
            eList.get(i).draw(gfx, this);
        }
        player1.getTank().draw(gfx, this);
        player2.getTank().draw(gfx, this);

        //Draws entire border around the map
        //wBorder.draw(gfx,this);
        for (int i = 0; i < bList.size(); i++) {
            bList.get(i).updata();
            bList.get(i).draw(gfx, this);
        }
        
        for (int i = 0; i < pList.size(); i++) {
            pList.get(i).draw(gfx, this);
        }
        if( player1.getTank().getLifeCount()<=0 &&player2.getTank().getLifeCount()<=0  ){
            stop();
        }
        //System.out.println("Test");
    }

    @Override
    public void run() {

        Thread game = Thread.currentThread();
        //System.out.print("test");
        while (thread == game) {
            repaint();
            //System.out.println("Test");
            try {
                thread.sleep(25);
            } catch (InterruptedException e) {
                break;
            }
        }

    }

    @Override
    public void start() {
        thread = new Thread(this);
        thread.setPriority(Thread.MIN_PRIORITY);
        thread.start();
        bmusic.start();

    }
    
    @Override
    public void stop(){
        bmusic.stop();

    }

}
