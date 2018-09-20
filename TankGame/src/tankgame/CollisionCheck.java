/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tankgame;

import java.awt.Rectangle;

/**
 *
 * @author carolynchen
 */
public class CollisionCheck {

    Rectangle left;
    Rectangle right;
    Rectangle up;
    Rectangle down;
    Rectangle W;
    Rectangle A;
    
    public CollisionCheck(){

    }
    public void TankandETank(Tank a, EnemyTank b){
         A = new Rectangle(a.getX(),a.getY(),a.getWidth()-1,a.getHeight()-1);
         W = new Rectangle(b.getX(),b.getY(),b.getWidth()-1,b.getHeight()-1);
         if(A.intersects(W)){
            b.Died(true);
            a.Died(true);
         }
        
    }
    
    public void TankandPowerUp(Tank a, PowerUp b){
        A = new Rectangle(a.getX(),a.getY(),a.getWidth()-1,a.getHeight()-1);
        W = new Rectangle(b.getX(),b.getY(),b.getWidth()-1,b.getHeight()-1);
        if(A.intersects(W)){
            if(b.getSpeed() == 0){
                a.setPowerTypeRed(true);
                a.Item(true);
                b.redItem(true);
            }
            
            if(b.getSpeed() == 1){
                a.setPowerTypeBlue(true);
                a.Item(true);
                b.blueItem(true);
            }
         }
    }
    
    
   
     public void eTankandWall(EnemyTank a, Wall b){
        //System.out.println("Test");
        left = new Rectangle(a.getX(),a.getY()+1,1,a.getHeight()-2);
        right = new Rectangle(a.getX()+a.getWidth()-2,a.getY()+1,1,a.getHeight()-2);
        up = new Rectangle(a.getX()+1,a.getY(),a.getWidth()-2,1);
        down = new Rectangle(a.getX()+1, a.getY()+a.getHeight()-2,a.getWidth()-2,1);
        
        W = new Rectangle(b.getX(),b.getY(),b.getWidth()-1,b.getHeight()-1);
        
         if(up.intersects(W)){
            a.changeDirection(1);
            a.y += 1;
        }
        else if(down.intersects(W)){
            a.changeDirection(3);
            a.y -= 1;
        }
        else if(left.intersects(W)){
            a.changeDirection(2);
            a.x +=1;
        }
         else if(right.intersects(W)){
            a.changeDirection(0);
            a.x -= 1;
        }
         
       }
     
     public void BulletandWall(Bullet a, Wall b){
          A = new Rectangle(a.getX(),a.getY(),a.getWidth()-1,a.getHeight()-1);
          W = new Rectangle(b.getX(),b.getY(),b.getWidth()-1,b.getHeight()-1);
          if(A.intersects(W)){
            if(b.Breakable){
                b.breaked();
                a.collision();
                TankWorld.ww.getList().remove(b);
                TankWorld.bList.remove(a);
             }
             else{
                a.collision();
                TankWorld.bList.remove(a);
            }
          }
     }
     
     public void BulletandETank(EnemyTank a, Bullet b){
        A = new Rectangle(a.getX(),a.getY(),a.getWidth()-1,a.getHeight()-1); // EnemyTank
        W = new Rectangle(b.getX(),b.getY(),b.getWidth()-1,b.getHeight()-1); // Bullet
        
        if(A.intersects(W)){
            if(b.pBullet()){
               b.collision();
               TankWorld.bList.remove(b);
               a.Died(false);
            }
        }
     }
     
     public void BulletandPTank(Tank a, Bullet b){
        A = new Rectangle(a.getX(),a.getY(),a.getWidth()-1,a.getHeight()-1); // Tank
        W = new Rectangle(b.getX(),b.getY(),b.getWidth()-1,b.getHeight()-1); // Bullet
        
        if(A.intersects(W)&& a.getColdDown()==0){
            if(!b.pBullet()){
               b.collision();
               TankWorld.bList.remove(b);
               a.Died(false);
            }
        }
     }
}
