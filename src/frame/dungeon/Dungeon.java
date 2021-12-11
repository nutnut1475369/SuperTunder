package frame.dungeon;

import editor.MyImageIcon;
import editor.MySoundEffect;
import frame.EndingFrame;
import frame.FirstFrame;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Dungeon extends JFrame {
    private JPanel contentpane;
    private JLabel playerLabel, drawpane, monsterLabel;
    private JProgressBar playerBar, monsterBar;
    private MyImageIcon mapbg, monsterLeftImg, monsterRightImg, playerUp1Img, playerUp2Img, playerUp3Img, playerDown1Img, playerDown2Img, playerDown3Img, playerLeft1Img, playerLeft2Img, playerLeft3Img, playerRight1Img, playerRight2Img, playerRight3Img, playerDownmovementImg;
    private int playerWidth = 60, playerHeight = 60;
    private int frameWidth = 1200, frameHeight = 800;
    private int state,cdHit = 0,cd = 0,level,position,monsterHp,hp;
    private int playerCurX, playerCurY = frameHeight / 2 - playerHeight / 2;
    private int monsterCurX = (frameWidth / 2 - playerWidth / 2) + 100, monsterCurY = (frameHeight / 2 - playerHeight / 2) + 100;
    private boolean playerAlive = true,attack = false, playerrunning = false, playerUp = false, playerDown = false, playerLeft = false, playerRight = false;
    private int[] monsterAlive;
    private String name,skin;
    private FirstFrame _firstFrame;
    private MySoundEffect Hitted, playerAtt,heroThemeSound;
    Dimension ss = Toolkit.getDefaultToolkit().getScreenSize();

    public Dungeon(int _state, int[] _monsterAlive ,int _level,int _position,String _name,String _skin) {
        name =_name;
        skin = _skin;
        monsterAlive=_monsterAlive;
        level=_level;
        state = _state;
        position=_position;
        monsterHp = 5*level;
        hp=10/level;
        playerBar = new JProgressBar(0,hp);
        playerBar.setBounds(40,40,160,30);
        playerBar.setValue(hp);
        playerBar.setStringPainted(true);
        monsterBar = new JProgressBar(0,monsterHp);
        monsterBar.setBounds(900,40,160,30);
        monsterBar.setValue(monsterHp);
        monsterBar.setStringPainted(true);
        monsterBar.setForeground(Color.RED);
        contentpane = (JPanel) getContentPane();
        contentpane.setLayout(new BorderLayout());
        setResizable(false);
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setBounds(ss.width / 2 - frameWidth / 2, ss.height / 2 - frameHeight / 2, frameWidth, frameHeight);
        Hitted = new MySoundEffect("resources/gothit.wav");
        playerAtt = new MySoundEffect("resources/Att.wav");
        heroThemeSound = new MySoundEffect("resources/dun.wav");
        heroThemeSound.playOnce();
        playerDown1Img = new MyImageIcon("resources/player/"+ skin +"/D1.png").resize(playerWidth, playerHeight);
        playerDown2Img = new MyImageIcon("resources/player/"+ skin +"/D2.png").resize(playerWidth, playerHeight);
        playerDown3Img = new MyImageIcon("resources/player/"+ skin +"/D3.png").resize(playerWidth, playerHeight);
        playerUp1Img = new MyImageIcon("resources/player/"+ skin +"/U1.png").resize(playerWidth, playerHeight);
        playerUp2Img = new MyImageIcon("resources/player/"+ skin +"/U2.png").resize(playerWidth, playerHeight);
        playerUp3Img = new MyImageIcon("resources/player/"+ skin +"/U3.png").resize(playerWidth, playerHeight);
        playerLeft1Img = new MyImageIcon("resources/player/"+ skin +"/L1.png").resize(playerWidth, playerHeight);
        playerLeft2Img = new MyImageIcon("resources/player/"+ skin +"/L2.png").resize(playerWidth, playerHeight);
        playerLeft3Img = new MyImageIcon("resources/player/"+ skin +"/L3.png").resize(playerWidth, playerHeight);
        playerRight1Img = new MyImageIcon("resources/player/"+ skin +"/R1.png").resize(playerWidth, playerHeight);
        playerRight2Img = new MyImageIcon("resources/player/"+ skin +"/R2.png").resize(playerWidth, playerHeight);
        playerRight3Img = new MyImageIcon("resources/player/"+ skin +"/R3.png").resize(playerWidth, playerHeight);
        playerDownmovementImg = new MyImageIcon("resources/player/"+ skin +"/STOP.png").resize(playerWidth, playerHeight);
        playerLabel = new JLabel(playerDown1Img);
        if (position==0){
            playerCurX = 50;
        }else {
            playerCurX = frameWidth-50;
        }
        playerLabel.setBounds(playerCurX, playerCurY, playerWidth, playerHeight);
        playerLabel.setVisible(true);
        mapbg = new MyImageIcon("resources/map/Dungeon/BG/1.png").resize(frameWidth, frameHeight);
        drawpane = new JLabel();
        drawpane.setIcon(mapbg);
        drawpane.add(playerLabel);
        drawpane.add(playerBar);
        drawpane.add(monsterBar);
        if (monsterAlive[state-1]==1)
        {
            setMonsterThread(drawpane);
        }
        setPlayerThread();
        contentpane.add(drawpane, BorderLayout.CENTER);
        validate();
    }
    public void setMonsterThread(JLabel drawpane) {
        monsterLeftImg = new MyImageIcon("resources/map/Dungeon/Monster/" + state + "10.png").resize(40, 40);
        monsterRightImg = new MyImageIcon("resources/map/Dungeon/Monster/" + state + "20.png").resize(40, 40);
        monsterLabel = new JLabel(monsterLeftImg);
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
            public void mouseMoved(MouseEvent e) {}
        });
        Thread monsterThread = new Thread(() -> {
            while (monsterAlive[state-1] == 1) {
                if (playerCurX != monsterCurX) {
                    if (playerCurX > monsterCurX){
                        monsterCurX += 1;
                        monsterLabel.setIcon(monsterRightImg);
                    }else {
                        monsterCurX -= 1;
                        monsterLabel.setIcon(monsterLeftImg);
                    }
                }
                if (playerCurY != monsterCurY) {
                    monsterCurY = playerCurY > monsterCurY ? monsterCurY + 1 : monsterCurY - 1;
                }
                if (attack) {
                    if (getBoundatt().intersects(monsterLabel.getBounds())) {
                        monsterHp-=1;
                        Hitted.playOnce();
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
                }
                monsterBar.setValue(monsterHp);
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
        this.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
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
                    playerAtt.playOnce();
                    attack = true;
                    cd=30;
                }
            }

            @Override
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
        });
        // end run
        Thread playerThread = new Thread(() -> {
            int moveMent = 0;
            int speed;
            hp = 10/level;
            while (playerAlive) {
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
                                new Dungeon(state, monsterAlive, level, 1,name,skin);
                            } else {
                                _firstFrame = new FirstFrame(level,name,skin,true,1);
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
                                new EndingFrame(name,skin,false);
                                heroThemeSound.stop();
                                this.dispose();
                                break;
                            }
                            new Dungeon(state, monsterAlive, level, 0,name,skin);
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
                        if (playerUp) {
                            if (moveMent % 40 >= 30) {
                                playerLabel.setIcon(new MyImageIcon("resources/player/"+skin+"/Attack/Us1.png").resize(playerWidth , playerHeight));
                            } else if (moveMent % 40 >= 20) {
                                playerLabel.setIcon(new MyImageIcon("resources/player/"+skin+"/Attack/Us2.png").resize(playerWidth , playerHeight));
                            } else if (moveMent % 40 >= 10) {
                                playerLabel.setIcon(new MyImageIcon("resources/player/"+skin+"/Attack/Us3.png").resize(playerWidth , playerHeight));
                            } else if (moveMent % 40 == 0) {
                                playerLabel.setIcon(playerUp1Img);
                            }
                        }
                        if (playerLeft) {
                            if (moveMent % 40 >= 30) {
                                playerLabel.setIcon(new MyImageIcon("resources/player/"+skin+"/Attack/Ls1.png").resize(playerWidth , playerHeight ));
                            } else if (moveMent % 40 >= 20) {
                                playerLabel.setIcon(new MyImageIcon("resources/player/"+skin+"/Attack/Ls2.png").resize(playerWidth , playerHeight ));
                            } else if (moveMent % 40 >= 10) {
                                playerLabel.setIcon(new MyImageIcon("resources/player/"+skin+"/Attack/Ls3.png").resize(playerWidth , playerHeight ));
                            } else if (moveMent % 40 == 0) {
                                playerLabel.setIcon(playerLeft1Img);
                            }
                        }
                        if (playerDown) {
                            if (moveMent % 40 >= 30) {
                                playerLabel.setIcon(new MyImageIcon("resources/player/"+skin+"/Attack/Ds1.png").resize(playerWidth , playerHeight ));
                            } else if (moveMent % 40 >= 20) {
                                playerLabel.setIcon(new MyImageIcon("resources/player/"+skin+"/Attack/Ds2.png").resize(playerWidth , playerHeight ));
                            } else if (moveMent % 40 >= 10) {
                                playerLabel.setIcon(new MyImageIcon("resources/player/"+skin+"/Attack/Ds3.png").resize(playerWidth , playerHeight ));
                            } else if (moveMent % 40 == 0) {
                                playerLabel.setIcon(playerDown1Img);
                            }
                        }
                        if (playerRight) {
                            if (moveMent % 40 >= 30) {
                                playerLabel.setIcon(new MyImageIcon("resources/player/"+skin+"/Attack/Rs1.png").resize(playerWidth , playerHeight ));
                            } else if (moveMent % 40 >= 20) {
                                playerLabel.setIcon(new MyImageIcon("resources/player/"+skin+"/Attack/Rs2.png").resize(playerWidth , playerHeight ));
                            } else if (moveMent % 40 >= 10) {
                                playerLabel.setIcon(new MyImageIcon("resources/player/"+skin+"/Attack/Rs3.png").resize(playerWidth , playerHeight ));
                            } else if (moveMent % 40 == 0) {
                                playerLabel.setIcon(playerRight1Img);
                            }
                        }
                    }
                }
                if (cdHit<=0&&monsterAlive[state - 1] == 1 && playerLabel.getBounds().intersects(monsterLabel.getBounds())) {
                    hp -= 1;
                    cdHit=50;
                    Hitted.playOnce();
                    if (Math.abs(playerCurX - monsterCurX)>=playerWidth){
                        if (playerCurX - monsterCurX>0) {
                            playerLabel.setIcon(new MyImageIcon("resources/player/"+skin+"/Hurt/HuL.png").resize(playerWidth, playerHeight));
                        }else {
                            playerLabel.setIcon(new MyImageIcon("resources/player/"+skin+"/Hurt/HuR.png").resize(playerWidth, playerHeight));
                        }
                    }else{
                        if (playerCurY - monsterCurY>0){
                            playerLabel.setIcon(new MyImageIcon("resources/player/"+skin+"/Hurt/HuU.png").resize(playerWidth, playerHeight));
                        }else {
                            playerLabel.setIcon(new MyImageIcon("resources/player/"+skin+"/Hurt/HuD.png").resize(playerWidth, playerHeight));
                        }
                    }
                    playerCurX = playerCurX + (playerCurX - monsterCurX);
                    playerCurY = playerCurY + (playerCurY - monsterCurY);
                }
                if (playerCurY>520){
                    playerCurY = 520;
                }
                if (playerCurY<170){
                    playerCurY = 170;
                }
                playerLabel.setLocation(playerCurX, playerCurY);
                repaint();
                if (hp<=0){
                    playerAlive = false;
                    new EndingFrame(name,skin,true);
                    heroThemeSound.stop();
                    this.dispose();
                    break;
                }
                playerBar.setValue(hp);
                try {
                    Thread.sleep(7);
                    cd-=1;
                    cdHit-=1;
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
            T.y = (int) (T.getY() - 5);
        } else if (playerDown) {
            System.out.println("playerDown");
            T.y = (int) (T.getY() + 5 + playerHeight/2);
        } else if (playerRight) {
            System.out.println("playerRight");
            T.x = (int) (T.getX() + 5 + playerWidth/2);
        } else if (playerLeft) {
            System.out.println("playerLeft");
            T.x = (int) (T.getX() - 5);
        }
        return T;
    }
}


