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
import java.util.ArrayList;
import javax.imageio.ImageIO;

/**
 *
 * @author jasonchow
 * 
 * Carolyn: I need to do the collision check to the wall so I move the image to the wall class.
 */
public class GameObstacles {

    private Wall wallObstacle;
    private Wall wallBorder;
    private ArrayList<Wall> WallList;
    private BufferedImage imgO;
    private BufferedImage imgB;
    public GameObstacles() {
        
        //Wall Border & Obstacle
        try {
            imgB = ImageIO.read(GameObstacles.class.getResource("Images/Wall2.gif"));
            imgO = ImageIO.read(GameObstacles.class.getResource("Images/Wall1.gif"));
        } catch (Exception e) { 
            System.out.println("ERROR: Invalid Resource");
        }
       
        WallList = new ArrayList<Wall>();
    }

    public void draw() {

        //Wall Border horizontal
        for (int i = 384; i < 1856; i += 32) {
            for(int j = 288; j < 384; j+= 32){
            wallBorder = new Wall(false,i,j,imgB);
            WallList.add(wallBorder);
            }
        }

        for (int i = 384; i < 1856; i += 32) {
            for(int j = 1200; j < 1296; j+= 32){
            wallBorder = new Wall(false,i,j,imgB);
            WallList.add(wallBorder);
            }
        }

        //Wall Border vertical
        for (int i = 288; i < 1296; i += 32) {
            for(int j = 288; j < 384; j+= 32){
            wallBorder = new Wall(false,j,i,imgB);
            WallList.add(wallBorder);
            }
        }

        for (int i = 288; i < 1296; i += 32) {
            for(int j = 1856; j < 1952; j+= 32){
            wallBorder = new Wall(false,j,i,imgB);
            WallList.add(wallBorder);
            }
        }
        

        //Wall Obstacles horitzontal
        for (int i = 416; i < 512; i += 32) {
            wallObstacle = new Wall(true,i,512,imgO);
            WallList.add(wallObstacle);
        }
        
     //   for (int i = 832; i < 1024; i += 32) {
       //     wallObstacle = new Wall(true,i,512,imgO);
         //   WallList.add(wallObstacle);
        //}
        
        for (int i = 1056; i < 1216; i += 32) {
            wallObstacle = new Wall(false,i,512,imgB);
            WallList.add(wallObstacle);
        }
        
        for (int i = 448; i < 508; i += 32) {
            wallObstacle = new Wall(true,i,544,imgO);
            WallList.add(wallObstacle);
        }
        
        for (int i = 768; i < 992; i += 32) {
            wallObstacle = new Wall(true,i,544,imgO);
            WallList.add(wallObstacle);
        }
        
      //  for (int i = 832; i < 1248; i += 32) {
      //      wallObstacle = new Wall(true,i,544,imgO);
      //      WallList.add(wallObstacle);
      //  }
        
        for (int i = 1344; i < 1760; i += 32) {
            wallObstacle = new Wall(false,i,544,imgB);
            WallList.add(wallObstacle);
        }
        
        for (int i = 512; i < 640; i += 32) {
            wallObstacle = new Wall(true,i,576,imgO);
            WallList.add(wallObstacle);
        }
        
        for (int i = 704; i < 928; i += 32) {
            wallObstacle = new Wall(true,i,576,imgO);
            WallList.add(wallObstacle);
        }
        
      //  for (int i = 1120; i < 1376; i += 32) {
      //      wallObstacle = new Wall(true,i,576,imgO);
       //     WallList.add(wallObstacle);
       // }
        
        for (int i = 544; i < 704; i += 32) {
            wallObstacle = new Wall(true,i,640,imgO);
            WallList.add(wallObstacle);
        }
        
        for (int i = 730; i < 832; i += 32) {
            wallObstacle = new Wall(true,i,640,imgO);
            WallList.add(wallObstacle);
        }
        
        for (int i = 1024; i < 1088; i += 32) {
            wallObstacle = new Wall(true,i,640,imgO);
            WallList.add(wallObstacle);
        }
        
        for (int i = 1120; i < 1440; i += 32) {
            wallObstacle = new Wall(true,i,640,imgO);
            WallList.add(wallObstacle);
        }
        
        for (int i = 416; i < 608; i += 32) {
            wallObstacle = new Wall(true,i,672,imgO);
            WallList.add(wallObstacle);
        }
        
        for (int i = 832; i < 1024; i += 32) {
            wallObstacle = new Wall(true,i,672,imgO);
            WallList.add(wallObstacle);
        }
        
        for (int i = 1120; i < 1376; i += 32) {
            wallObstacle = new Wall(true,i,672,imgO);
            WallList.add(wallObstacle);
        }
        
        for (int i = 1472; i < 1760; i += 32) {
            wallObstacle = new Wall(true,i,672,imgO);
            WallList.add(wallObstacle);
        }
        
        for (int i = 832; i < 1248; i += 32) {
            wallObstacle = new Wall(true,i,736,imgO);
            WallList.add(wallObstacle);
        }
        
        for (int i = 1568; i < 1664; i += 32) {
            wallObstacle = new Wall(true,i,736,imgO);
            WallList.add(wallObstacle);
        }
        
        for (int i = 448; i < 704; i += 32) {
            wallObstacle = new Wall(true,i,768,imgO);
            WallList.add(wallObstacle);
        }
        
        for (int i = 960; i < 1384; i += 32) {
            wallObstacle = new Wall(true,i,768,imgO);
            WallList.add(wallObstacle);
        }
        
        for (int i = 1344; i < 1568; i += 32) {
            wallObstacle = new Wall(true,i,768,imgO);
            WallList.add(wallObstacle);
        }
        
        for (int i = 544; i < 672; i += 32) {
            wallObstacle = new Wall(true,i,800,imgO);
            WallList.add(wallObstacle);
        }
        
        for (int i = 992; i < 1280; i += 32) {
            wallObstacle = new Wall(true,i,800,imgO);
            WallList.add(wallObstacle);
        }
        
        for (int i = 1344; i < 1504; i += 32) {
            wallObstacle = new Wall(true,i,800,imgO);
            WallList.add(wallObstacle);
        }
        
        for (int i = 704; i < 1440; i += 32) {
            wallObstacle = new Wall(true,i,832,imgO);
            WallList.add(wallObstacle);
        }
        
      //  for (int i = 608; i < 896; i += 32) {
     //       wallObstacle = new Wall(true,i,864,imgO);
     //       WallList.add(wallObstacle);
      //  }
        
        for (int i = 992; i < 1152; i += 32) {
            wallObstacle = new Wall(false,i,864,imgB);
            WallList.add(wallObstacle);
        }
        
        for (int i = 852; i < 1568; i += 32) {
            wallObstacle = new Wall(true,i,928,imgO);
            WallList.add(wallObstacle);
        }
        
        for (int i = 544; i < 992; i += 32) {
            wallObstacle = new Wall(true,i,928,imgO);
            WallList.add(wallObstacle);
        }
        
      //  for (int i = 544; i < 992; i += 32) {
     //       wallObstacle = new Wall(true,i,928,imgO);
            //WallList.add(wallObstacle);
      //  }
        
        for (int i = 416; i < 672; i += 32) {
            wallObstacle = new Wall(true,i,960,imgO);
            WallList.add(wallObstacle);
        }
        
        for (int i = 928; i < 1024; i += 32) {
            wallObstacle = new Wall(false,i,960,imgB);
            WallList.add(wallObstacle);
        }
        
        for (int i = 1216; i < 1504; i += 32) {
            wallObstacle = new Wall(true,i,960,imgO);
            WallList.add(wallObstacle);
        }
        
        for (int i = 1216; i < 1504; i += 32) {
            wallObstacle = new Wall(true,i,1024,imgO);
            WallList.add(wallObstacle);
        }
        
        for (int i = 512; i < 640; i += 32) {
            wallObstacle = new Wall(true,i,1056,imgO);
            WallList.add(wallObstacle);
        }
        
        for (int i = 704; i < 928; i += 32) {
            wallObstacle = new Wall(false,i,1056,imgB);
            WallList.add(wallObstacle);
        }
        
        for (int i = 1120; i < 1376; i += 32) {
            wallObstacle = new Wall(true,i,1056,imgO);
            WallList.add(wallObstacle);
        }
      
        
        for (int i = 544; i < 772; i += 32) {
            wallObstacle = new Wall(true,i,1088,imgO);
            WallList.add(wallObstacle);
        }
        
      //  for (int i = 992; i < 1280; i += 32) {
      //      wallObstacle = new Wall(true,i,1088,imgO);
      //      WallList.add(wallObstacle);
      //  }
        
        for (int i = 1344; i < 1760; i += 32) {
            wallObstacle = new Wall(true,i,1088,imgO);
            WallList.add(wallObstacle);
        }
        
        for (int i = 416; i < 512; i += 32) {
            wallObstacle = new Wall(true,i,1120,imgO);
            WallList.add(wallObstacle);
        }
        
        for (int i = 832; i < 1024; i += 32) {
            wallObstacle = new Wall(true,i,1120,imgO);
            WallList.add(wallObstacle);
        }
        
      //  for (int i = 1056; i < 1216; i += 32) {
       //     wallObstacle = new Wall(true,i,1120,imgO);
       //     WallList.add(wallObstacle);
      //  }
        
        
        


    }
    
    public ArrayList getList(){
        return WallList;
    }

}
