/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tankgame;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import javax.imageio.ImageIO;

/**
 *
 * @author carolynchen
 */
public class Wall extends GameObject {
    boolean Breakable;
    private Image wall;
    private boolean breaked;
    public Wall(boolean B, int x, int y, BufferedImage img){
        super(img,x,y,0);
        Breakable = B;
        breaked = false;
    }
    @Override
    public void draw(Graphics g, ImageObserver obs){
        if(!breaked ){
            g.drawImage(img, x, y, obs);
        }
    }
    
    public void breaked(){
        breaked = true;
    }
}
