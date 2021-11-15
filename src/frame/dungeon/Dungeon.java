package frame.dungeon;

import editor.MyImageIcon;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

public class Dungeon extends JFrame implements MouseListener, KeyListener {
    private JPanel contentpane;
    private JLabel playerLabel, drawpane, monsterLabel, objectLabelprev, objectLabelnext;
    private MyImageIcon door,dooropen,mapbg,monsterImg ,playerUp1Img,playerUp2Img,playerUp3Img, playerDown1Img,playerDown2Img,playerDown3Img, playerLeft1Img,playerLeft2Img,playerLeft3Img, playerRight1Img,playerRight2Img,playerRight3Img,playerDownmovementImg;
    private int playerWidth = 33, playerHeight = 47;
    private int frameWidth = 1200, frameHeight = 800;
    private int scenestate;
    private Scene _scene;
    private ArrayList<Scene> sceneList = new ArrayList<>();
    private int playerCurX = frameWidth / 2 - playerWidth / 2, playerCurY = frameHeight / 2 - playerHeight / 2;
    private boolean playerrunning = false, playerUp = false,playerDown= false,playerLeft= false,playerRight= false;
    public Dungeon(){}
    public void Dungeon(String scene,String monster,int state){
        scenestate=state;
        setTitle(scene);
        _scene = new Scene(scene,monster);
        sceneList.add(_scene);
        contentpane = (JPanel)getContentPane();
        contentpane.setLayout( new BorderLayout() );
        setResizable(false);
    }
    public void start(int state){
        scenestate = state;
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
        playerLabel = new JLabel(playerDown1Img);
        playerLabel.setBounds(playerCurX,playerCurY,playerWidth,playerHeight);
        playerLabel.setVisible(true);
        drawpane = new JLabel();
        drawpane.setIcon(sceneList.get(state).getMap());
        drawpane.add(playerLabel);
        drawpane.add(sceneList.get(state).getMonsterLabel());
        contentpane.add(drawpane, BorderLayout.CENTER);
        validate();
        setPlayerThread();
    }
    public void keyTyped(KeyEvent e) {}

    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode()==KeyEvent.VK_RIGHT) { playerRight = true;}
        if (e.getKeyCode()==KeyEvent.VK_UP) { playerUp = true;}
        if (e.getKeyCode()==KeyEvent.VK_DOWN) { playerDown = true;}
        if (e.getKeyCode()==KeyEvent.VK_LEFT) { playerLeft = true;}
        if (e.getKeyCode()==KeyEvent.VK_SHIFT){ playerrunning = true;}
    }

    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode()==KeyEvent.VK_RIGHT) { playerRight = false;}
        if (e.getKeyCode()==KeyEvent.VK_UP) { playerUp = false;}
        if (e.getKeyCode()==KeyEvent.VK_DOWN) { playerDown = false;}
        if (e.getKeyCode()==KeyEvent.VK_LEFT) { playerLeft = false;}
        if (e.getKeyCode()==KeyEvent.VK_SHIFT){ playerrunning = false;}
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
                        if (playerCurY>26) {
                            playerCurY = playerCurY - speed;
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
                    playerLabel.setLocation(playerCurX, playerCurY);
                    repaint();
                    try { Thread.sleep(7); moveMent += 1;}
                    catch (InterruptedException e) { e.printStackTrace(); }
                } // end while
            };// end run
        }; // end thread creation
        playerThread.start();
    }
    @Override
    public void mouseClicked(MouseEvent e) {
    }
    @Override
    public void mousePressed(MouseEvent e) {
    }
    @Override
    public void mouseReleased(MouseEvent e) {
    }
    @Override
    public void mouseEntered(MouseEvent e) {
    }
    @Override
    public void mouseExited(MouseEvent e) {
    }
}
class Scene extends JLabel{
    private MyImageIcon mapbg,monsterImg;
    private JLabel monsterLabel;
    private int frameWidth = 1200, frameHeight = 800;
    public Scene(String scene,String monster){
        mapbg = new MyImageIcon("resources/map/"+scene+".png").resize(frameWidth, frameHeight);
        monsterImg = new MyImageIcon("resources/player/"+monster+".png").resize(50,50);
        monsterLabel = new JLabel(monsterImg);
        monsterLabel.setBounds(1000,500,50,50);
        monsterLabel.setVisible(true);
    }
    public JLabel getMonsterLabel(){
        return monsterLabel;
    }
    public MyImageIcon getMap(){
        return mapbg;
    }
}