package frame;

import editor.MyImageIcon;
import editor.MySoundEffect;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Home extends JFrame implements KeyListener {

    private JPanel contentpane;
    private JLabel drawpane;
    private int level;
    private JFrame optionframe;
    private JComboBox combo;
    private JToggleButton[] tb;
    private JButton start, setting, exit;
    private JTextField scoreText;
    private JLabel playerLabel;
    private MyImageIcon indoorImg, outdoorImg, itemImg;
    private MyImageIcon playerUp1Img,playerUp2Img,playerUp3Img, playerDown1Img,playerDown2Img,playerDown3Img, playerLeft1Img,playerLeft2Img,playerLeft3Img, playerRight1Img,playerRight2Img,playerRight3Img,playerDownmovementImg;
    private boolean playerUp = false,playerDown= false,playerLeft= false,playerRight= false;
    private MySoundEffect hitSound, heroThemeSound;
    private long timePressed;
    private OptionFrame _optionFrame;

    // working variables - adjust the values as you want
    private int frameWidth = 1366, frameHeight = 768;
    private int playerWidth = 60, playerHeight = 60;
    private int optionWidth = 300, optionHeight = 700;
    private int playerCurX = frameWidth / 2 - playerWidth / 2, playerCurY = frameHeight / 2 - playerHeight / 2;
    Dimension ss = Toolkit.getDefaultToolkit().getScreenSize();

    public Home() {
        setTitle("SuperTunder v1.1.1");
        setBounds(ss.width / 2 - frameWidth / 2, ss.height / 2 - frameHeight / 2, frameWidth, frameHeight);
        setResizable(false);
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                heroThemeSound.stop();
            }
        });
        contentpane = (JPanel)getContentPane();
        contentpane.setLayout( new BorderLayout() );
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        AddComponents();
    }

    //////////////////////////////////////////////////////////////////////////
    public void AddComponents() {
        _optionFrame = new OptionFrame();
        _optionFrame.setVisible(false);
        _optionFrame.setBounds(ss.width / 2 - optionWidth / 2, ss.height / 2 - optionHeight / 2, optionWidth, optionHeight);
        indoorImg = new MyImageIcon("resources/indoor.jpg").resize(frameWidth, frameHeight);
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

        drawpane = new JLabel();
        drawpane.setIcon(indoorImg);
        drawpane.setLayout(null);

        playerLabel = new JLabel(playerDown1Img);
        playerLabel.setBounds(playerCurX,playerCurY,playerWidth,playerHeight);
        playerLabel.setVisible(false);
        start = new JButton("Start");
        setting = new JButton("Setting");
        exit = new JButton("Exit");
        start.setBounds(frameWidth / 2-125, frameHeight - 450, 250, 80);
        setting.setBounds(frameWidth / 2-125, frameHeight - 350, 250, 80);
        exit.setBounds(frameWidth / 2-125, frameHeight - 250, 250, 80);
        start.addActionListener(e->{
            level = _optionFrame.getLevel();
            System.out.println(level);
            playerLabel.setVisible(true);
        });
        setting.addActionListener(e->{
            _optionFrame.setVisible(true);
        });
        exit.addActionListener(e->{
            System.exit(1);
        });
        drawpane.add(start);
        drawpane.add(setting);
        drawpane.add(exit);
        drawpane.add(playerLabel);
        setPlayerThread();
        heroThemeSound = new MySoundEffect("resources/herotheme.wav");
        heroThemeSound.playLoop();
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
