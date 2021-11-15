package frame;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.concurrent.TimeUnit;

import javax.swing.*;

import editor.MyImageIcon;
import frame.dungeon.Dungeon;

public class Ban extends JFrame implements KeyListener{
    private JPanel contentpane;
    private JLabel playerLabel, drawpane, objectdoor, objectcave;
    private MyImageIcon door,dooropen,cave,mapbg,playerUp1Img,playerUp2Img,playerUp3Img, playerDown1Img,playerDown2Img,playerDown3Img, playerLeft1Img,playerLeft2Img,playerLeft3Img, playerRight1Img,playerRight2Img,playerRight3Img,playerDownmovementImg;
    private int playerWidth = 33, playerHeight = 47;
    private int frameWidth = 1200, frameHeight = 800;
    private int playerCurX = frameWidth / 2 - playerWidth / 2, playerCurY = frameHeight / 2 - playerHeight / 2;
    private boolean playerrunning = false, playerUp = false,playerDown= false,playerLeft= false,playerRight= false;

    public Ban(){
        setTitle("Ban Sand Suk");
        contentpane = (JPanel)getContentPane();
        contentpane.setLayout( new BorderLayout() );
        setResizable(false);
        playerDown1Img = new MyImageIcon("resources/player/D1.png").resize(playerWidth, playerHeight);
        playerDown2Img = new MyImageIcon("resources/player/D2.png").resize(playerWidth, playerHeight);
        playerDown3Img = new MyImageIcon("resources/player/D3.png").resize(playerWidth, playerHeight);
        playerUp1Img = new MyImageIcon("resources/player/U1.png").resize(playerWidth, playerHeight);
        playerUp2Img = new MyImageIcon("resources/player/U2.png").resize(playerWidth, playerHeight);
        playerUp3Img = new MyImageIcon("resources/player/U3.png").resize(playerWidth, playerHeight);
        playerLeft1Img = new MyImageIcon("resources/player/L1.png").resize(playerWidth, playerHeight);
        playerLeft2Img = new MyImageIcon("resources/player/L2.png").resize(playerWidth, playerHeight);
        playerLeft3Img = new MyImageIcon("resources/player/L3.png").resize(playerWidth, playerHeight);
        playerRight1Img = new MyImageIcon("resources/player/R1.png").resize(playerWidth, playerHeight);
        playerRight2Img = new MyImageIcon("resources/player/R2.png").resize(playerWidth, playerHeight);
        playerRight3Img = new MyImageIcon("resources/player/R3.png").resize(playerWidth, playerHeight);
        playerDownmovementImg = new MyImageIcon("resources/player/STOP.png").resize(playerWidth, playerHeight);
        mapbg = new MyImageIcon("resources/map/MAP1.png").resize(frameWidth, frameHeight);
        door = new MyImageIcon("resources/object/Door.png").resize(52, 52);
        dooropen = new MyImageIcon("resources/object/OpenDoor.png").resize(52, 52);
        cave = new MyImageIcon("resources/object/Cavemouth.png").resize(52, 52);
        objectcave = new JLabel(cave);
        objectdoor = new JLabel(door);
        objectdoor.setBounds(120,145,52,52);
        objectcave.setBounds(1250,80,37,37);
        objectdoor.setVisible(true);
        objectcave.setVisible(true);
        playerLabel = new JLabel(playerDown1Img);
        playerLabel.setBounds(playerCurX,playerCurY,playerWidth,playerHeight);
        playerLabel.setVisible(true);
        drawpane = new JLabel();
        drawpane.setIcon(mapbg);
        drawpane.add(playerLabel);
        drawpane.add(objectdoor);
        drawpane.add(objectcave);
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        setPlayerThread();
        contentpane.add(drawpane, BorderLayout.CENTER);
        validate();
    }
    public void keyTyped(KeyEvent e) {}

    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode()==KeyEvent.VK_RIGHT) { playerRight = true;}
        if (e.getKeyCode()==KeyEvent.VK_UP) { playerUp = true;}
        if (e.getKeyCode()==KeyEvent.VK_DOWN) { playerDown = true;}
        if (e.getKeyCode()==KeyEvent.VK_LEFT) { playerLeft = true;}
        if (e.getKeyCode()==KeyEvent.VK_SHIFT){ playerrunning = true;}
        if (e.getKeyCode()==KeyEvent.VK_SPACE){ actionPlayer();}
    }

    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode()==KeyEvent.VK_RIGHT) { playerRight = false;}
        if (e.getKeyCode()==KeyEvent.VK_UP) { playerUp = false;}
        if (e.getKeyCode()==KeyEvent.VK_DOWN) { playerDown = false;}
        if (e.getKeyCode()==KeyEvent.VK_LEFT) { playerLeft = false;}
        if (e.getKeyCode()==KeyEvent.VK_SHIFT){ playerrunning = false;}
    }
    public void actionPlayer(){
        if (playerLabel.getBounds().intersects(objectdoor.getBounds())){
            this.dispose();
        }
        if (playerLabel.getBounds().intersects(objectcave.getBounds())){

        }
    }
    public void setPlayerThread()
    {
        Thread playerThread = new Thread(){
            public void run()
            {
                int moveMent = 0;
                int speed;
                while (true)
                {
                    if (playerrunning){
                        speed = 50;
                    }else {
                        speed = 1;
                    }
                    if (playerLeft)
                    {
                        if (moveMent%100==75){
                            playerLabel.setIcon(playerLeft1Img);
                        }else if(moveMent%100==50){
                            playerLabel.setIcon(playerLeft2Img);
                        }else if (moveMent%100==25){
                            playerLabel.setIcon(playerLeft1Img);
                        }else if (moveMent%100==0){
                            playerLabel.setIcon(playerLeft3Img);
                        }
                        if (playerCurX>0) {
                            playerCurX = playerCurX - speed;
                        }
                        if (playerCurX<=90&&playerCurY<=280) {
                            playerCurX = playerCurX + speed;
                        }
                        if ((playerCurX<=1100&&playerCurX>=190)&&playerCurY<=280) {
                            playerCurX = playerCurX + speed;
                        }
                    }else
                    if(playerRight)
                    {
                        if (moveMent%100==75){
                            playerLabel.setIcon(playerRight1Img);
                        }else if(moveMent%100==50){
                            playerLabel.setIcon(playerRight2Img);
                        }else if (moveMent%100==25){
                            playerLabel.setIcon(playerRight1Img);
                        }else if (moveMent%100==0){
                            playerLabel.setIcon(playerRight3Img);
                        }
                        if (playerCurX+playerWidth+(playerWidth/2)<frameWidth) {
                            playerCurX = playerCurX + speed;
                        }
                        if ((playerCurX>=135&&playerCurX<=1100)&&playerCurY<=280){
                            playerCurX = playerCurX - 1;
                        }
                    }else
                    if(playerUp)
                    {
                        if (moveMent%100==75){
                            playerLabel.setIcon(playerUp1Img);
                        }else if(moveMent%100==50){
                            playerLabel.setIcon(playerUp2Img);
                        }else if (moveMent%100==25){
                            playerLabel.setIcon(playerUp1Img);
                        }else if (moveMent%100==0){
                            playerLabel.setIcon(playerUp3Img);
                        }
                        if (playerCurY>0) {
                            playerCurY = playerCurY - speed;
                        }
                        if (playerCurY<183&&playerCurX<135){
                            playerCurY = playerCurY + 1;
                        }
                        if ((playerCurX<=90||(playerCurX>=135&&playerCurX<=1100))&&playerCurY<=280) {
                            playerCurY = playerCurY + 1;
                        }

                    }else
                    if(playerDown)
                    {
                        if (moveMent%100==75){
                            playerLabel.setIcon(playerDown1Img);
                        }else if(moveMent%100==50){
                            playerLabel.setIcon(playerDown2Img);
                        }else if (moveMent%100==25){
                            playerLabel.setIcon(playerDown1Img);
                        }else if (moveMent%100==0){
                            playerLabel.setIcon(playerDown3Img);
                        }
                        if (playerCurY<417) {
                            playerCurY = playerCurY + speed;
                        }
                    }else
                    {
                        if (moveMent%200==100){
                            playerLabel.setIcon(playerDownmovementImg);
                        }else if(moveMent%200==0){
                            playerLabel.setIcon(playerDown1Img);
                        }
                    }
                    System.out.println(playerCurX);
                    System.out.println(playerCurY);
                    playerLabel.setLocation(playerCurX, playerCurY);
                    repaint();
                    try { Thread.sleep(7); moveMent += 1;}
                    catch (InterruptedException e) { e.printStackTrace(); }
                } // end while
            };// end run
        }; // end thread creation
        playerThread.start();
    }
}
