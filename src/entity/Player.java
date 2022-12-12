package entity;


import main.GamePanel;
import main.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends Entity{

    GamePanel gp;
    KeyHandler keyH;
    public final int screenX;
    public final int screenY;
        public Player(GamePanel gp, KeyHandler keyH){

            this.gp = gp;
            this.keyH= keyH;

            screenX=(gp.screenWidth/2)-(gp.tileSize/2);
            screenY=(gp.screenHeight/2)-(gp.tileSize/2);

            SetDefaultValues();
            getPlayerImage();
        }
        public void SetDefaultValues(){
            worldX=gp.tileSize*23;
            worldY=gp.tileSize*21;
            speed=4;
            direction="down";
        }
        public void getPlayerImage(){

            try {
                up1= ImageIO.read(getClass().getResourceAsStream("/resource/player/boy_up_1.png"));
                up2= ImageIO.read(getClass().getResourceAsStream("/resource/player/boy_up_2.png"));
                down1= ImageIO.read(getClass().getResourceAsStream("/resource/player/boy_down_1.png"));
                down2= ImageIO.read(getClass().getResourceAsStream("/resource/player/boy_down_2.png"));
                right1= ImageIO.read(getClass().getResourceAsStream("/resource/player/boy_right_1.png"));
                right2= ImageIO.read(getClass().getResourceAsStream("/resource/player/boy_right_2.png"));
                left1= ImageIO.read(getClass().getResourceAsStream("/resource/player/boy_left_1.png"));
                left2= ImageIO.read(getClass().getResourceAsStream("/resource/player/boy_left_2.png"));

            }catch (IOException e){
                e.printStackTrace();
            }
        }
        public void update(){

            if (keyH.upPressed || keyH.downPressed
                    || keyH.leftPressed || keyH.rightPressed) {

                if (keyH.upPressed) {
                    direction = "up";
                    worldY -= speed;
                    //playerY=playerY-playerSpeed;
                } else if (keyH.downPressed) {
                    direction = "down";
                    worldY += speed;
                } else if (keyH.leftPressed) {
                    direction = "left";
                    worldX -= speed;
                } else if (keyH.rightPressed) {
                    direction = "right";
                    worldX += speed;
                }
                spriteCounter++;

                if (spriteCounter > 15) {
                    if (spriteNum == 1) {
                        spriteNum = 2;
                    } else if (spriteNum == 2) {
                        spriteNum = 1;
                    }
                    spriteCounter = 0;
                }
            }else {
                spriteCounter++;

                if (spriteCounter > 45) {
                    if (spriteNum == 1) {
                        spriteNum = 2;
                    } else if (spriteNum == 2) {
                        spriteNum = 1;
                    }
                    spriteCounter = 0;
                }
            }
        }
        public void draw(Graphics2D g2){
//            g2.setColor(Color.white);
//            g2.fillRect(x, y,gp.tileSize,gp.tileSize);

            BufferedImage image = null;
            switch (direction){
                case "up":{
                    if (spriteNum==1) {
                        image = up1;
                    }
                    if (spriteNum==2) {
                        image = up2;
                    }
                    break;
                }
                case "down": {
                    if (spriteNum == 1) {
                        image = down1;
                    }
                    if (spriteNum == 2) {
                    image = down2;
                    }
                    break;
                }
                case "left":{
                    if (spriteNum==1) {
                        image = left1;
                    }
                    if (spriteNum==2) {
                        image = left2;
                    }
                    break;
                }
                case "right":{
                    if (spriteNum==1) {
                        image = right1;
                    }
                    if (spriteNum==2) {
                        image = right2;
                    }
                    break;
                }
            }
            g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);

        }

}