package frame;

import java.awt.*;

import javax.swing.*;

import editor.MyImageIcon;

public class EndingFrame extends JFrame {
    private JPanel contentpane;
    private JLabel playerLabel, drawpane;
    private MyImageIcon endingbg, playerDown1Img,playerDownmovementImg;
    private int playerWidth = 60, playerHeight = 60;

    private int frameWidth = 1200, frameHeight = 800;
    private int playerCurX = frameWidth / 2 - playerWidth / 2, playerCurY = frameHeight / 2 - playerHeight / 2;
    private String name,skin;
    private JButton Ban,homeScene;

    Dimension ss = Toolkit.getDefaultToolkit().getScreenSize();

    public EndingFrame(String _name,String _skin,boolean died){
        name = _name;
        skin = _skin;
        setTitle("AI NUT MAI WAI LEW");
        setBounds(ss.width / 2 - frameWidth / 2, ss.height / 2 - frameHeight / 2, frameWidth, frameHeight);
        setResizable(false);
        setVisible(true);
//        addWindowListener(new WindowAdapter() {
//            public void windowClosing(WindowEvent e) {
//                heroThemeSound.stop();
//            }
//        });
        homeScene = new JButton("menu");
        homeScene.setBounds(100,10,10,10);
        homeScene.addActionListener(e->{
            new Home();
            this.dispose();
        });
        Ban = new JButton("ban");
        Ban.setBounds(100,100,10,10);
        Ban.addActionListener(e->{
            new Ban(name,skin,false,0);
            this.dispose();
        });
        contentpane = (JPanel)getContentPane();
        contentpane.setLayout( new BorderLayout() );
        setResizable(false);
        playerDown1Img = new MyImageIcon("resources/player/"+skin+"/D1.png").resize(playerWidth, playerHeight);
        playerDownmovementImg = new MyImageIcon("resources/player/"+skin+"/STOP.png").resize(playerWidth, playerHeight);
        endingbg = new MyImageIcon("resources/ending/1.png").resize(frameWidth, frameHeight);
        playerLabel = new JLabel(playerDown1Img);
        playerLabel.setBounds(playerCurX,playerCurY,playerWidth,playerHeight);
        playerLabel.setVisible(true);
        drawpane = new JLabel();
        drawpane.setIcon(endingbg);
        drawpane.add(playerLabel);
        getContentPane().add(homeScene);
        getContentPane().add(Ban);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        setPlayerThread();
        contentpane.add(drawpane, BorderLayout.CENTER);
        validate();
    }

    public void setPlayerThread()
    {
        // end run
        Thread playerThread = new Thread(() -> {
            int moveMent=0;
            while (true)
            {
                    if (moveMent%200==100){
                        playerLabel.setIcon(playerDownmovementImg);
                    }else if(moveMent%200==0){
                        playerLabel.setIcon(playerDown1Img);
                    }
                playerLabel.setLocation(playerCurX, playerCurY);
                repaint();
                try { Thread.sleep(7); moveMent += 1;}
                catch (InterruptedException e) { e.printStackTrace(); }
            } // end while
        }); // end thread creation
        playerThread.start();
    }
}
