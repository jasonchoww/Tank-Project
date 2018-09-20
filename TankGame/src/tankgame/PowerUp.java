/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tankgame;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import javax.imageio.ImageIO;

/**
 *
 * @author carolynchen
 */
public class PowerUp extends GameObject {

    private BufferedImage redPowerUp;
    private BufferedImage bluePowerUp;

    public PowerUp(BufferedImage img, int x, int y, int speed){
        super(img, x, y, speed);
        this.img = img;
    }
    
    
    public void redItem(boolean redItemActive) {
            TankWorld.pList.remove(this);
        
    }

    public void blueItem(boolean blueItemActive) {
            TankWorld.pList.remove(this);
    }
    
    public int getSpeed(){
        return speed;
    }

    
    @Override
    public void draw(Graphics g, ImageObserver obs) {
        g.drawImage(img, x, y, obs);
    }


   

}
