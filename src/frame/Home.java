package frame;

import editor.MyImageIcon;
import editor.MySoundEffect;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Home extends JFrame{

    private JPanel contentpane;
    private JLabel drawpane;
    private int level;
    private JButton start, setting, exit;
    private MyImageIcon indoorImg;
    private MySoundEffect heroThemeSound;
    private OptionFrame _optionFrame;
    private FirstFrame _firstFrame;

    // working variables - adjust the values as you want
    private int frameWidth = 1200, frameHeight = 800;
    private int optionWidth = 300, optionHeight = 700;
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
        //addKeyListener(this);
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

        drawpane = new JLabel();
        drawpane.setIcon(indoorImg);
        drawpane.setLayout(null);

        start = new JButton("Start");
        setting = new JButton("Setting");
        exit = new JButton("Exit");
        start.setBounds(frameWidth / 2-125, frameHeight - 450, 250, 80);
        setting.setBounds(frameWidth / 2-125, frameHeight - 350, 250, 80);
        exit.setBounds(frameWidth / 2-125, frameHeight - 250, 250, 80);
        start.addActionListener(e->{
            level = _optionFrame.getLevel();
            System.out.println(level);
            _firstFrame = new FirstFrame(level);
            _firstFrame.setBounds(ss.width / 2 - frameWidth / 2, ss.height / 2 - frameHeight / 2, frameWidth, frameHeight);
            _firstFrame.setVisible(true);
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
        heroThemeSound = new MySoundEffect("resources/herotheme.wav");
//        heroThemeSound.playLoop();
        contentpane.add(drawpane, BorderLayout.CENTER);
        validate();
    }
}
