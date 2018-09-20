/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tankgame;

/**
 *
 * @author carolynchen
 */
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.Observer;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.IOException;
import java.util.Observable;
import javax.imageio.ImageIO;

public class Tank extends GameObject implements Observer {

    private int health, coldDown, shootC;
    private BufferedImage[] HBar;
    private BufferedImage[] P1Lives;
    private BufferedImage[] P2Lives;
    private int LifeCount;
    private int healthBar;
    private GameObject Bar;
    private GameObject Life;
    private Observable observable;
    private BufferedImage im, bullet, imge;
    private short degree;
    private AffineTransformOp op;
    private boolean alive, expl;
    private Explosion haha;
    public boolean up, down, left, right, shoot;
    private int xLocation;
    private int yLocation;
    private AffineTransform tx;
    private int xc, yc;
    private int powerType = 0;

    private Rectangle leftR;
    private Rectangle rightR;
    private Rectangle upR;
    private Rectangle downR;
    private Rectangle W;
    private int num;

    public Tank(int num,int x, int y, BufferedImage image, int s, int h) throws IOException {
        super(image, x, y, s);
        this.num = num;
        health = h;
        healthBar = 3;
        LifeCount = 3;
        im = image;

        HBar = new BufferedImage[4];
        HBar[0] = ImageIO.read(Tank.class.getResource("Images/health.png"));
        HBar[1] = ImageIO.read(Tank.class.getResource("Images/health1.png"));
        HBar[2] = ImageIO.read(Tank.class.getResource("Images/health2.png"));
        HBar[3] = ImageIO.read(Tank.class.getResource("Images/health3.png"));
        bullet = ImageIO.read(Tank.class.getResource("Images/Rocket.gif"));
        alive = true;
        coldDown = 0;
        degree = 0;
        shootC = 0;
        expl = false;
        haha = new Explosion();

    }

    @Override
    public void draw(Graphics w, ImageObserver obs) {
        if (shootC != 0) {
            shootC--;
        }
        if (coldDown != 0) {
            coldDown--;
            alive = !alive;
        }
        if (expl) {
            if (!haha.Done()) {
                w.drawImage(haha.draw(), x, y, obs);
            } else {
                expl = false;
                haha.reset();
                this.y = 1120;
                this.x = 600*num;
            }
        } else if (alive) {
            tx = AffineTransform.getTranslateInstance(x, y);
            tx.rotate(Math.toRadians(degree), im.getWidth() / 2, im.getHeight() / 2);
            Graphics2D graphic2D = (Graphics2D) w;
            graphic2D.drawImage(img, tx, null);
            if (healthBar == 3) {
                Bar = new GameObject(HBar[0], x, y - 20, 0);
                Bar.draw(w, obs);
            } else if (healthBar == 2) {
                Bar = new GameObject(HBar[1], x, y - 20, 0);
                Bar.draw(w, obs);
            } else if (healthBar == 1) {
                Bar = new GameObject(HBar[2], x, y - 20, 0);
                Bar.draw(w, obs);
            } else {
                Bar = new GameObject(HBar[3], x, y - 20, 0);
                Bar.draw(w, obs);
            }

        }

        xLocation = x;
        yLocation = y;

    }

    public void Died(boolean tank) {
        if (coldDown == 0) {
            if (healthBar > 0 && !tank) {
                healthBar--;
            } else {
                expl = true;
                if (health > 1) {
                    health--;
                    powerType = 0;
                    LifeCount = LifeCount - 1;
                    healthBar = 3;
                    speed = 2;
                    coldDown = 20;
                    imge = im;
                } else {
                    LifeCount = LifeCount - 1;
                    alive = false;

                }
            }
        }
    }

    public void setPowerTypeRed(boolean tank) {
        powerType = 1;
    }

    public void setPowerTypeBlue(boolean tank) {
        powerType = 2;
    }

    public void Item(boolean tank) {

        //Red Power Up
        if (powerType == 1) {
            health = 3;
            healthBar = 3;
            LifeCount = 3;
        }

    }

    @Override
    public void update(Observable object, Object argument) {
        boolean check = false;
        if (alive) {

            if (left) {
                degree -= 3;
                for (int i = 0; i < TankWorld.ww.getList().size(); i++) {
                    if (TankandWall(this, (Wall) TankWorld.ww.getList().get(i))) {
                        check = true;
                    }
                }
                if (check) {
                    degree += 3;
                }

            } else if (right) {
                degree += 3;
                for (int i = 0; i < TankWorld.ww.getList().size(); i++) {
                    if (TankandWall(this, (Wall) TankWorld.ww.getList().get(i))) {
                        check = true;
                    }
                }
                if (check) {
                    degree -= 3;
                }

            } else if (up) {
                xc = (int) Math.round(speed * Math.cos(Math.toRadians(degree)));
                yc = (int) Math.round(speed * Math.sin(Math.toRadians(degree)));
                x += xc;
                y += yc;
                for (int i = 0; i < TankWorld.ww.getList().size(); i++) {
                    if (TankandWall(this, (Wall) TankWorld.ww.getList().get(i))) {
                        check = true;
                    }
                }
                if (check) {
                    x -= xc;
                    y -= yc;
                }

            } else if (down) {
                xc = (int) Math.round(speed * Math.cos(Math.toRadians(degree)));
                yc = (int) Math.round(speed * Math.sin(Math.toRadians(degree)));
                x -= xc;
                y -= yc;
                for (int i = 0; i < TankWorld.ww.getList().size(); i++) {
                    if (TankandWall(this, (Wall) TankWorld.ww.getList().get(i))) {
                        check = true;
                    }
                }
                if (check) {
                    x += xc;
                    y += yc;
                }
            } else if (shoot && shootC == 0) {
                Bullet b = new Bullet(bullet, x, y, (int) Math.round(6 * Math.cos(Math.toRadians(degree))),
                        (int) Math.round(6 * Math.sin(Math.toRadians(degree))), true, degree);
                TankWorld.bList.add(b);
                shootC = 10;
                
                
                //BLUE POWER UP
                if(powerType == 2){
                    Bullet b2 = new Bullet(bullet, x + 30, y + 30, (int) Math.round(6 * Math.cos(Math.toRadians(degree))),
                        (int) Math.round(6 * Math.sin(Math.toRadians(degree))), true, degree);
                TankWorld.bList.add(b2);
                shootC = 10;
                }
                
                if(powerType == 2){
                    Bullet b3 = new Bullet(bullet, x - 30, y - 30, (int) Math.round(6 * Math.cos(Math.toRadians(degree))),
                        (int) Math.round(6 * Math.sin(Math.toRadians(degree))), true, degree);
                TankWorld.bList.add(b3);
                shootC = 10;
                }
                //BLUE POWER UP ^^^^^^^
                
                
            }
        }
    }

    public int getXC() {
        return xc;
    }

    public int getYC() {
        return yc;
    }

    public int getColdDown() {
        return coldDown;
    }

    public int xLocation() {
        return xLocation;
    }

    public int yLocation() {
        return yLocation;
    }

    public int getLifeCount() {
        return LifeCount;
    }

    public boolean TankandWall(Tank a, Wall b) {

        leftR = new Rectangle(a.getX(), a.getY(), 1, a.getHeight() - 1);
        rightR = new Rectangle(a.getX() + a.getWidth() - 1, a.getY(), 1, a.getHeight() - 1);
        upR = new Rectangle(a.getX(), a.getY(), a.getWidth() - 1, 1);
        downR = new Rectangle(a.getX(), a.getY() + a.getHeight() - 1, a.getWidth() - 1, 1);

        W = new Rectangle(b.getX(), b.getY(), b.getWidth() - 1, b.getHeight() - 1);

        if (upR.intersects(W)) {
            return true;
        } else if (downR.intersects(W)) {
            return true;
        } else if (leftR.intersects(W)) {
            return true;
        } else if (rightR.intersects(W)) {
            return true;
        } else {
            return false;
        }

    }
}
