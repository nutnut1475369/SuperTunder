package frame.dungeon;

import editor.MyImageIcon;
import frame.EndingFrame;
import frame.FirstFrame;
import frame.Home;
import frame.OptionFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class Dungeon extends JFrame implements MouseMotionListener, KeyListener {
    private JPanel contentpane;
    private JLabel playerLabel, drawpane, monsterLabel;
    private MyImageIcon mapbg, monsterImg, playerUp1Img, playerUp2Img, playerUp3Img, playerDown1Img, playerDown2Img, playerDown3Img, playerLeft1Img, playerLeft2Img, playerLeft3Img, playerRight1Img, playerRight2Img, playerRight3Img, playerDownmovementImg;
    private int playerWidth = 33, playerHeight = 47;
    private int frameWidth = 1200, frameHeight = 800;
    private int state,cd = 0,level,position,monsterHp;
    private int playerCurX, playerCurY = frameHeight / 2 - playerHeight / 2;
    private int monsterCurX = (frameWidth / 2 - playerWidth / 2) + 100, monsterCurY = (frameHeight / 2 - playerHeight / 2) + 100;
    private boolean playerAlive = true,attack = false, playerrunning = false, playerUp = false, playerDown = false, playerLeft = false, playerRight = false;
    private int[] monsterAlive;
    private FirstFrame _firstFrame;
    Dimension ss = Toolkit.getDefaultToolkit().getScreenSize();

    public Dungeon(int _state, int[] _monsterAlive ,int _level,int _position) {
        monsterAlive=_monsterAlive;
        level=_level;
        state = _state;
        position=_position;
        monsterHp = 5*level;
        contentpane = (JPanel) getContentPane();
        contentpane.setLayout(new BorderLayout());
        setResizable(false);
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setBounds(ss.width / 2 - frameWidth / 2, ss.height / 2 - frameHeight / 2, frameWidth, frameHeight);
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
        if (position==0){
            playerCurX = 50;
        }else {
            playerCurX = frameWidth-50;
        }
        playerLabel.setBounds(playerCurX, playerCurY, playerWidth, playerHeight);
        playerLabel.setVisible(true);
        mapbg = new MyImageIcon("resources/map/Dungeon/BG/" + state + ".png").resize(frameWidth, frameHeight);
        drawpane = new JLabel();
        drawpane.setIcon(mapbg);
        drawpane.add(playerLabel);
        if (monsterAlive[state-1]==1)
        {
            setMonsterThread(drawpane);
        }
        setPlayerThread();
        addKeyListener(this);
        contentpane.add(drawpane, BorderLayout.CENTER);
        validate();
    }

    public void keyTyped(KeyEvent e) {
    }

    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            playerRight = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            playerUp = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            playerDown = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            playerLeft = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_SHIFT) {
            playerrunning = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            attack = true;
            cd=30;
        }
    }

    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            playerRight = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            playerUp = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            playerDown = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            playerLeft = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_SHIFT) {
            playerrunning = false;
        }
    }

    public void setMonsterThread(JLabel drawpane) {
        monsterImg = new MyImageIcon("resources/map/Dungeon/Monster/" + state + "1.png").resize(50, 50);
        monsterLabel = new JLabel(monsterImg);
        monsterLabel.setBounds(monsterCurX, monsterCurY, 50, 50);
        monsterLabel.setVisible(true);
        drawpane.add(monsterLabel);
        monsterLabel.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {
                monsterCurX = monsterCurX+e.getX();
                monsterCurY = monsterCurY+e.getY();
                monsterLabel.setLocation(monsterCurX,monsterCurY);
                if (monsterCurX<0) monsterHp=0;
                if (monsterCurY<0) monsterHp=0;
                if (monsterCurX>frameWidth) monsterHp=0;
                if (monsterCurY>frameHeight) monsterHp=0;
            }

            @Override
            public void mouseMoved(MouseEvent e) {

            }
        });
        Thread monsterThread = new Thread(() -> {
            while (true) {
                if (playerCurX != monsterCurX) {
                    monsterCurX = playerCurX > monsterCurX ? monsterCurX + 1 : monsterCurX - 1;
                }
                if (playerCurY != monsterCurY) {
                    monsterCurY = playerCurY > monsterCurY ? monsterCurY + 1 : monsterCurY - 1;
                }
                if (attack) {
                    if (getBoundatt().intersects(monsterLabel.getBounds())) {
                        monsterHp-=1;
                        System.out.println("Monster = "+monsterHp);
                        monsterCurX = monsterCurX + (monsterCurX - playerCurX);
                        monsterCurY = monsterCurY + (monsterCurY - playerCurY);
                    }
                }
                monsterLabel.setLocation(monsterCurX, monsterCurY);
                repaint();
                if (monsterHp<=0) {
                    drawpane.remove(monsterLabel);
                    monsterLabel.setBounds(-monsterCurX, -monsterCurY, 50, 50);
                    monsterAlive[state-1] = 0;
                    break;
                }
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        monsterThread.start();
    }

    public void setPlayerThread() {
        // end run
        Thread playerThread = new Thread(() -> {
            int moveMent = 0;
            int speed;
            int hp = 10/level;
            while (true) {
                if (!attack&&cd<=0)
                {
                    if (playerrunning) {
                        speed = 5;
                    } else {
                        speed = 1;
                    }
                    if (playerLeft) {
                        if (moveMent % 100 == 75) {
                            playerLabel.setIcon(playerLeft1Img);
                        } else if (moveMent % 100 == 50) {
                            playerLabel.setIcon(playerLeft2Img);
                        } else if (moveMent % 100 == 25) {
                            playerLabel.setIcon(playerLeft1Img);
                        } else if (moveMent % 100 == 0) {
                            playerLabel.setIcon(playerLeft3Img);
                        }
                        if (playerCurX > 0) {
                            playerCurX = playerCurX - speed;
                        }
                        if (playerCurX < 100) {
                            state -= 1;
                            if (state != 0) {
                                new Dungeon(state, monsterAlive, level, 1);
                            } else {
                                _firstFrame = new FirstFrame(level);
                                _firstFrame.setVisible(true);
                                _firstFrame.setBounds(ss.width / 2 - frameWidth / 2, ss.height / 2 - frameHeight / 2, frameWidth, frameHeight);
                                this.dispose();
                            }
                            this.dispose();
                            break;
                        }
                    } else if (playerRight) {
                        if (moveMent % 100 == 75) {
                            playerLabel.setIcon(playerRight1Img);
                        } else if (moveMent % 100 == 50) {
                            playerLabel.setIcon(playerRight2Img);
                        } else if (moveMent % 100 == 25) {
                            playerLabel.setIcon(playerRight1Img);
                        } else if (moveMent % 100 == 0) {
                            playerLabel.setIcon(playerRight3Img);
                        }
                        if (playerCurX < frameWidth - playerWidth) {
                            playerCurX = playerCurX + speed;
                        }
                        if (playerCurX > frameWidth - 100 && monsterAlive[state - 1] == 0) {
                            state += 1;
                            if (state == level + 1) {
                                new EndingFrame();
                                this.dispose();
                                break;
                            }
                            new Dungeon(state, monsterAlive, level, 0);
                            this.dispose();
                            break;
                        }
                    } else if (playerUp) {
                        if (moveMent % 100 == 75) {
                            playerLabel.setIcon(playerUp1Img);
                        } else if (moveMent % 100 == 50) {
                            playerLabel.setIcon(playerUp2Img);
                        } else if (moveMent % 100 == 25) {
                            playerLabel.setIcon(playerUp1Img);
                        } else if (moveMent % 100 == 0) {
                            playerLabel.setIcon(playerUp3Img);
                        }
                        if (playerCurY > 0) {
                            playerCurY = playerCurY - speed;
                        }

                    } else if (playerDown) {
                        if (moveMent % 100 == 75) {
                            playerLabel.setIcon(playerDown1Img);
                        } else if (moveMent % 100 == 50) {
                            playerLabel.setIcon(playerDown2Img);
                        } else if (moveMent % 100 == 25) {
                            playerLabel.setIcon(playerDown1Img);
                        } else if (moveMent % 100 == 0) {
                            playerLabel.setIcon(playerDown3Img);
                        }
                        if (playerCurY < 710) {
                            playerCurY = playerCurY + speed;
                        }
                    } else {
                        if (moveMent % 200 == 100) {
                            playerLabel.setIcon(playerDownmovementImg);
                        } else if (moveMent % 200 == 0) {
                            playerLabel.setIcon(playerDown1Img);
                        }
                    }

                }else {
                    {
                        playerLabel.setBounds(playerCurX, playerCurY, playerWidth, playerHeight * 2);
                        if (playerUp) {
                            if (moveMent % 40 >= 30) {
                                playerLabel.setIcon(new MyImageIcon("resources/player/Attack/Us1.png").resize(playerWidth * 2, playerHeight));
                            } else if (moveMent % 40 >= 20) {
                                playerLabel.setIcon(new MyImageIcon("resources/player/Attack/Us2.png").resize(playerWidth * 2, playerHeight));
                            } else if (moveMent % 40 >= 10) {
                                playerLabel.setIcon(new MyImageIcon("resources/player/Attack/Us3.png").resize(playerWidth * 2, playerHeight));
                            } else if (moveMent % 40 == 0) {
                                playerLabel.setIcon(playerUp1Img);
                            }
                        }
                        if (playerLeft) {
                            if (moveMent % 40 >= 30) {
                                playerLabel.setIcon(new MyImageIcon("resources/player/Attack/Ls1.png").resize(playerWidth * 2, playerHeight * 2));
                            } else if (moveMent % 40 >= 20) {
                                playerLabel.setIcon(new MyImageIcon("resources/player/Attack/Ls2.png").resize(playerWidth * 2, playerHeight * 2));
                            } else if (moveMent % 40 >= 10) {
                                playerLabel.setIcon(new MyImageIcon("resources/player/Attack/Ls3.png").resize(playerWidth * 2, playerHeight * 2));
                            } else if (moveMent % 40 == 0) {
                                playerLabel.setIcon(playerLeft1Img);
                            }
                        }
                        if (playerDown) {
                            if (moveMent % 40 >= 30) {
                                playerLabel.setIcon(new MyImageIcon("resources/player/Attack/Ds1.png").resize(playerWidth * 2, playerHeight * 2));
                            } else if (moveMent % 40 >= 20) {
                                playerLabel.setIcon(new MyImageIcon("resources/player/Attack/Ds2.png").resize(playerWidth * 2, playerHeight * 2));
                            } else if (moveMent % 40 >= 10) {
                                playerLabel.setIcon(new MyImageIcon("resources/player/Attack/Ds3.png").resize(playerWidth * 2, playerHeight * 2));
                            } else if (moveMent % 40 == 0) {
                                playerLabel.setIcon(playerDown1Img);
                            }
                        }
                        if (playerRight) {
                            if (moveMent % 40 >= 30) {
                                playerLabel.setIcon(new MyImageIcon("resources/player/Attack/Rs1.png").resize(playerWidth * 2, playerHeight * 2));
                            } else if (moveMent % 40 >= 20) {
                                playerLabel.setIcon(new MyImageIcon("resources/player/Attack/Rs2.png").resize(playerWidth * 2, playerHeight * 2));
                            } else if (moveMent % 40 >= 10) {
                                playerLabel.setIcon(new MyImageIcon("resources/player/Attack/Rs3.png").resize(playerWidth * 2, playerHeight * 2));
                            } else if (moveMent % 40 == 0) {
                                playerLabel.setIcon(playerRight1Img);
                            }
                        }
                    }
                }
                if (monsterAlive[state - 1] == 1 && playerLabel.getBounds().intersects(monsterLabel.getBounds())) {
                    hp -= 1;
                    System.out.println("Player = " + hp);
                    if (Math.abs(playerCurX - monsterCurX)>=playerWidth){
                        if (playerCurX - monsterCurX>0) {
                            playerLabel.setIcon(new MyImageIcon("resources/player/Hurt/HuL.png").resize(playerWidth, playerHeight));
                        }else {
                            playerLabel.setIcon(new MyImageIcon("resources/player/Hurt/HuR.png").resize(playerWidth, playerHeight));
                        }
                    }else{
                        if (playerCurY - monsterCurY>0){
                            playerLabel.setIcon(new MyImageIcon("resources/player/Hurt/HuU.png").resize(playerWidth, playerHeight));
                        }else {
                            playerLabel.setIcon(new MyImageIcon("resources/player/Hurt/HuD.png").resize(playerWidth, playerHeight));
                        }
                    }
                    playerCurX = playerCurX + (playerCurX - monsterCurX);
                    playerCurY = playerCurY + (playerCurY - monsterCurY);
                }
                playerLabel.setLocation(playerCurX, playerCurY);
                repaint();
                if (hp<=0){

                }
                try {
                    Thread.sleep(7);
                    cd-=1;
                    if (cd<=0){
                        attack=false;
                    }
                    moveMent += 1;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } // end while
        }); // end thread creation
        playerThread.start();
    }

    public Rectangle getBoundatt() {
        Rectangle T = playerLabel.getBounds();
        T.width = 30;
        T.height = 30;
        if (playerUp) {
            System.out.println("playerUp");
            T.y = (int) (T.getY() - 25);
        } else if (playerDown) {
            System.out.println("playerDown");
            T.y = (int) (T.getY() + 25+playerHeight);
        } else if (playerRight) {
            System.out.println("playerRight");
            T.x = (int) (T.getX() + 25+playerWidth);
        } else if (playerLeft) {
            System.out.println("playerLeft");
            T.x = (int) (T.getX() - 25);
        }
        return T;
    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }
}


