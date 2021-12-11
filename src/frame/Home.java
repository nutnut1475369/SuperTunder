package frame;

import editor.MyImageIcon;
import editor.MyLabel;
import editor.MySoundEffect;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Home extends JFrame{

    private JPanel contentpane;
    private JLabel drawpane,credit, start, setting;
    private int level;
    private String skin;
    private MyImageIcon indoorImg;
    private MySoundEffect heroThemeSound;
    private OptionFrame _optionFrame;
    private String name;

    private int frameWidth = 1200, frameHeight = 800;
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
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        AddComponents();
    }

    //////////////////////////////////////////////////////////////////////////
    public void AddComponents() {
        _optionFrame = new OptionFrame();
        _optionFrame.setVisible(false);
        _optionFrame.setBounds(ss.width / 2 - frameWidth / 2, ss.height / 2 - frameHeight / 2, frameWidth, frameHeight);
        indoorImg = new MyImageIcon("resources/mainmenu/main_menu.png").resize(frameWidth, frameHeight);
        drawpane = new JLabel();
        drawpane.setIcon(indoorImg);
        drawpane.setLayout(null);
        start = new JLabel(new ImageIcon("resources/mainmenu/start.png"));
        setting = new JLabel(new ImageIcon("resources/mainmenu/option.png"));
        credit = new JLabel(new ImageIcon("resources/mainmenu/credit.png"));
        heroThemeSound = new MySoundEffect("resources/herotheme.wav");
        heroThemeSound.playLoop();
//        exit = new JButton("Exit");
        start.setBounds(200, 350, 300, 80);
        setting.setBounds(200, 450, 300, 80);
        credit.setBounds(200,550,300,80);
//        exit.setBounds(200,400,250,80);
        start.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                level = _optionFrame.getLevel();
                heroThemeSound.stop();
                name = _optionFrame.getName();
                skin = _optionFrame.getSkin();
                new FirstFrame(level,name,skin,false,0);
                dispose();
            }
        });
        setting.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                _optionFrame.setVisible(true);
            }
        });
//        exit.addMouseListener(new MouseAdapter() {
//            @Override
//            public void mouseClicked(MouseEvent e) {
//                super.mouseClicked(e);
//                System.exit(1);
//            }
//        });
        drawpane.add(start);
        drawpane.add(setting);
//        drawpane.add(exit);
        drawpane.add(credit);
        contentpane.add(drawpane, BorderLayout.CENTER);
        validate();
    }
}
