/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tankgame;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 *
 * @author carolynchen
 */
public class Player {
    private  BufferedImage tankImage;
    private int up;
    private int down;
    private int left;
    private int right; 
    private Tank pTank;
    private int num;
    private int life;
    private int speed;
    
    private int xLocation;
    private int yLocation;
    public Player(int num, int li) throws IOException{
    try{
        tankImage = ImageIO.read(Player.class.getResource("Images/tank1.gif"));
    }
    catch(Exception e){
        System.out.println("No image");
    }
    speed = 2;
    this.num = num;
    this.life = li;
    pTank = new Tank(num,600*this.num,1120,tankImage,speed,life);
    
}
    public Tank getTank(){
        return this.pTank;
    }
    
   
    

}
