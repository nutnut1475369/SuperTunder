package frame;

import editor.MyImageIcon;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OptionFrame extends JFrame implements ActionListener {
    private int level = 1;
    private String name;
    private ButtonGroup bg;
    private JRadioButton levelManage1,levelManage2,levelManage3,levelManage4,levelManage5;
    private JButton confirm;
    private JTextField Name;
    private JLabel setName;
    private MyImageIcon backGround;
    private int frameWidth = 1200, frameHeight = 800;

    public OptionFrame(){
        backGround = new MyImageIcon("resources/setting/setting.jpg").resize(frameWidth, frameHeight);
        setContentPane(new JLabel(backGround));
        Name = new JTextField();
        Name.setBounds(600,200,150,30);
        setName = new JLabel("Your Name:");
        setName.setForeground(Color.white);
        setName.setBounds(500,200,80,30);
        confirm = new JButton("Confirm");
        confirm.setBounds(100,600,80,30);
        levelManage1 = new JRadioButton("");
        levelManage1.setActionCommand("1");
        levelManage1.addActionListener(this::actionPerformed);
        levelManage2 = new JRadioButton("");
        levelManage2.setActionCommand("2");
        levelManage2.addActionListener(this::actionPerformed);
        levelManage3 = new JRadioButton("");
        levelManage3.setActionCommand("3");
        levelManage3.addActionListener(this::actionPerformed);
        levelManage4 = new JRadioButton("");
        levelManage4.setActionCommand("4");
        levelManage4.addActionListener(this::actionPerformed);
        levelManage5 = new JRadioButton("");
        levelManage5.setActionCommand("5");
        levelManage5.addActionListener(this::actionPerformed);
        levelManage1.setBounds(380,280,50,50);
        levelManage2.setBounds(380,340,50,50);
        levelManage3.setBounds(380,400,50,50);
        levelManage4.setBounds(380,460,50,50);
        levelManage5.setBounds(380,520,50,50);
        getContentPane().setLayout(null);
        bg = new ButtonGroup();
        bg.add(levelManage1);
        bg.add(levelManage2);
        bg.add(levelManage3);
        bg.add(levelManage4);
        bg.add(levelManage5);
        getContentPane().add(Name);
        getContentPane().add(levelManage1);
        getContentPane().add(levelManage2);
        getContentPane().add(levelManage3);
        getContentPane().add(levelManage4);
        getContentPane().add(levelManage5);
        getContentPane().add(confirm);
        getContentPane().add(setName);
        setSize(300,300);
        setLayout(null);
        setVisible(true);
        confirm.addActionListener(e->{
            name = Name.getText();
            this.dispose();
            this.setVisible(false);
        });
    }
    public String getName(){ return name; }
    public int getLevel(){ return level; }
    @Override
    public void actionPerformed(ActionEvent e) {
        level = Integer.parseInt(bg.getSelection().getActionCommand());
        System.out.println(level);
    }
}
