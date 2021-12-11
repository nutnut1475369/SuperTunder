package frame;

import editor.MyImageIcon;

import javax.swing.*;
import java.awt.*;

public class River extends JFrame {

    private JPanel contentpane;
    private JLabel playerLabel, drawpane, riverLabel;
    private MyImageIcon riverScene, playerDown1Img,playerDownmovementImg;
    private int playerWidth = 60, playerHeight = 60;
    private int frameWidth = 1200, frameHeight = 800;
    private int playerCurX = 0, playerCurY, riverCurX;
    private String name,skin;
    private JButton Ban,homeScene;

    Dimension ss = Toolkit.getDefaultToolkit().getScreenSize();

    public River(String _name,String _skin){
        name = _name;
        skin = _skin;
        setTitle("AI NUT MAI WAI LEW");
        setBounds(ss.width / 2 - frameWidth / 2, ss.height / 2 - frameHeight / 2, frameWidth, frameHeight);
        setResizable(false);
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        contentpane = (JPanel)getContentPane();
        contentpane.setLayout( new BorderLayout() );
        playerDown1Img = new MyImageIcon("resources/player/"+skin+"/D1.png").resize(playerWidth, playerHeight);
        riverScene = new MyImageIcon("resources/ending/1.png").resize(frameWidth*2, frameHeight);
        riverLabel = new JLabel(riverScene,SwingConstants.LEFT);
        riverLabel.setBounds(0,0,frameWidth*2,frameHeight);
        playerLabel = new JLabel(playerDown1Img);
        playerLabel.setBounds(playerCurX,playerCurY = frameHeight / 2 - playerHeight / 2,playerWidth,playerHeight);
        playerLabel.setVisible(true);
        drawpane = new JLabel();
        drawpane.add(playerLabel);
        drawpane.add(riverLabel);
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
            while (true)
            {
                if (playerCurX<frameWidth/2) {
                    playerCurX += 1;
                    playerLabel.setLocation(playerCurX, playerCurY);
                    repaint();
                }else {
                    if (Math.abs(riverCurX)!=frameWidth) {
                        riverCurX -= 1;
                        riverLabel.setLocation(riverCurX, 0);
                        repaint();
                    }else {
                        playerCurX += 1;
                        playerLabel.setLocation(playerCurX, playerCurY);
                        repaint();
                    }
                }
                try { Thread.sleep(7);}
                catch (InterruptedException e) { e.printStackTrace(); }
            } // end while
        }); // end thread creation
        playerThread.start();
    }
}
