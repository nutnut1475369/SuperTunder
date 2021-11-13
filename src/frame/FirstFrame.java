package frame;

import java.awt.BorderLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.*;

import editor.MyImageIcon;

public class FirstFrame extends JFrame implements KeyListener{
    private JPanel contentpane;
	private JLabel playerLabel;
	private JLabel drawpane;
	
	private MyImageIcon playerUp1Img,playerUp2Img,playerUp3Img, playerDown1Img,playerDown2Img,playerDown3Img, playerLeft1Img,playerLeft2Img,playerLeft3Img, playerRight1Img,playerRight2Img,playerRight3Img,playerDownmovementImg;
	private int playerWidth = 60, playerHeight = 60;
	private int frameWidth = 1366, frameHeight = 768;
	private int playerCurX = frameWidth / 2 - playerWidth / 2, playerCurY = frameHeight / 2 - playerHeight / 2;
	private boolean playerUp = false,playerDown= false,playerLeft= false,playerRight= false;
	
	private long timePressed;
	
	public FirstFrame(){
		setTitle("AI NUT MAI WAI LEW");
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
	    playerLabel = new JLabel(playerDown1Img);
	    playerLabel.setBounds(playerCurX,playerCurY,playerWidth,playerHeight);
	    playerLabel.setVisible(true);
	    drawpane = new JLabel();
	    drawpane.add(playerLabel);
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
	    setPlayerThread();
	    contentpane.add(drawpane, BorderLayout.CENTER);
	    validate();
	}
	public void keyTyped(KeyEvent e) {}

    public void keyPressed(KeyEvent e) {
        timePressed = System.currentTimeMillis();
        System.out.println(timePressed);
        if (e.getKeyCode()==KeyEvent.VK_RIGHT) { playerRight = true;}
        if (e.getKeyCode()==KeyEvent.VK_UP) { playerUp = true;}
        if (e.getKeyCode()==KeyEvent.VK_DOWN) { playerDown = true;}
        if (e.getKeyCode()==KeyEvent.VK_LEFT) { playerLeft = true;}
    }

    public void keyReleased(KeyEvent e) {
        timePressed = System.currentTimeMillis();
        if (e.getKeyCode()==KeyEvent.VK_RIGHT) { playerRight = false;}
        if (e.getKeyCode()==KeyEvent.VK_UP) { playerUp = false;}
        if (e.getKeyCode()==KeyEvent.VK_DOWN) { playerDown = false;}
        if (e.getKeyCode()==KeyEvent.VK_LEFT) { playerLeft = false;}
    }

    public void setPlayerThread()
    {
        Thread playerThread = new Thread(){
            public void run()
            {
                int moveMent = 0;
                while (true)
                {

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
                        playerCurX = playerCurX - 1;
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
                        playerCurX = playerCurX +1;
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
                        playerCurY= playerCurY - 1;
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
                        playerCurY= playerCurY + 1;
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
}
