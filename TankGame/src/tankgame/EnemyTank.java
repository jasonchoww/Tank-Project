/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tankgame;

import java.awt.Graphics;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author jasonchow & carolynchen
 */
public class EnemyTank extends GameObject {
    
   
    private BufferedImage eTank, imR,imU,imL,imD;
    private int direction = 0;
    private BufferedImage imge;
    private BufferedImage bimge;
    private AffineTransform tx = new AffineTransform();
    private AffineTransformOp op;
    private BufferedImage[] HBar;
    private int healthBar;
    private GameObject Bar;
    private int shootTimer;
    private boolean died;
    private Explosion haha;

    public EnemyTank(BufferedImage imge,int x, int y) throws IOException{
     super(imge,x,y,1);
     this.imge= imge;
     eTank = imge;
     healthBar = 3;
     HBar = new BufferedImage[4];
     HBar[0] = ImageIO.read(EnemyTank.class.getResource("Images/health.png"));
     HBar[1] = ImageIO.read(EnemyTank.class.getResource("Images/health1.png"));
     HBar[2] = ImageIO.read(EnemyTank.class.getResource("Images/health2.png"));
     HBar[3] = ImageIO.read(EnemyTank.class.getResource("Images/health3.png"));
     imR = rota(0);
     imL = rota(180);
     imU = rota(90);
     imD = rota(-180);
     died = false;
     changeDirection(0);
     haha = new Explosion();
     
}
   public void changeDirection(int num){
       //System.out.println("Test D");
       Random r = new Random();
       while(direction == num){
        direction = r.nextInt(4);
       }
       if(direction == 0 ){
           eTank = imR;//right
       }
       else if(direction == 1){
           eTank = imU;//up
       }
       else if(direction == 2){
           eTank = imL;//left
       }
       else if(direction == 3){
           eTank = imD;//down
       }
   } 
   
    private BufferedImage rota(double num){
        tx.rotate(Math.toRadians(num), imge.getWidth()/2.0, imge.getHeight()/2.0);
        op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);
        return op.filter(imge, null);
    }
    
    public void Died(boolean tank){
        if(tank || healthBar==0){
           died = true;
        }
        else{
            healthBar-- ;
        }
    }
    
   

    @Override
    public void draw(Graphics g,ImageObserver obs){
       if(!died){
       try {
            update();
        } catch (IOException ex) {
            Logger.getLogger(EnemyTank.class.getName()).log(Level.SEVERE, null, ex);
        }
       
        if(healthBar==3){
            Bar = new GameObject(HBar[0],x,y-20,0);
            Bar.draw(g, obs);
        }
        else if(healthBar == 2){
            Bar = new GameObject(HBar[1],x,y-20,0);
            Bar.draw(g, obs);
        }
        else if(healthBar == 1) {
            Bar = new GameObject(HBar[2],x,y-20,0);
            Bar.draw(g, obs);
        }
        else{
            Bar = new GameObject(HBar[3],x,y-20,0);
            Bar.draw(g, obs);
        }
        g.drawImage(eTank, x, y, obs);
       }
       else{
           haha.start();
           if(!haha.Done()){
               g.drawImage(haha.draw(), x, y, obs);
           }
           else{
            TankWorld.eList.remove(this);
           }
       }
    }



   public void update() throws IOException  {
       if(direction == 0  ){
           x += speed;//right
       }
       else if(direction == 1){
           y -= speed;//up
       }
       else if(direction == 2){
           x -= speed;//left
       }
       else if(direction == 3){
            y += speed;//down
       }
       shootTimer++;
       if(shootTimer == 22){
           shoot();
           shootTimer = 0;
       }
    }
    
   public void shoot() throws IOException{
       bimge = ImageIO.read(EnemyTank.class.getResource("Images/Weapon.gif"));
        Bullet b;
        int xs = x+((imge.getWidth()-1)/2);
        int ys=y+((imge.getHeight()-1)/2);
       if(direction == 0){
        b = new Bullet(bimge,xs,ys,1,0,false, (short) 0);//right
       }
       else if(direction == 1){
            b = new Bullet(bimge,xs,ys,0,-1,false,(short)90);//up
       }
       else if(direction == 2){
           b = new Bullet(bimge,xs,ys,-1,0,false,(short)180);//left
       }
        else {
            b = new Bullet(bimge,xs,ys,0,1,false,(short)-180);//down
       }
       TankWorld.bList.add(b);

   }
    

  
    
}
