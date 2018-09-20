/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tankgame;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;

/**
 *
 * @author carolynchen
 */
public class Bullet extends GameObject {
    private int xD;
    private int yD;
    private BufferedImage imge;
    private boolean c;
    private boolean PTank;
    private short degree;
    private AffineTransform tx;
    public Bullet(BufferedImage img, int x, int y, int xDirections, int yDirections, boolean PTank,short degree) {
        super(img, x, y, 20);
        xD = xDirections;
        yD = yDirections;
        imge = img;
        c = false;
        this.PTank = PTank;
        this.degree=degree;
    }
    public void updata(){
        if(!c){
        x += xD*10;
        y += yD*10;
        }
    }
    @Override
     public void draw(Graphics g,ImageObserver obs){
         if(!c){
        tx = AffineTransform.getTranslateInstance(x, y);
        tx.rotate(Math.toRadians(degree), imge.getWidth() / 2, imge.getHeight() / 2);
        Graphics2D graphic2D = (Graphics2D) g;
        graphic2D.drawImage(img, tx, obs);
         }
     }
     
     public void collision(){
         c = true;

     }
     public boolean pBullet(){
         return PTank;
     }
}
