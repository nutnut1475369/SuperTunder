package frame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OptionFrame extends JFrame implements ActionListener {
    private int level = 1;
    private ButtonGroup bg;
    private JRadioButton levelManage1,levelManage2,levelManage3,levelManage4,levelManage5;
    private JButton confirm;
    public OptionFrame(){
        confirm = new JButton("Confirm");
        confirm.setBounds(100,600,80,30);
        levelManage1 = new JRadioButton("1");
        levelManage1.setActionCommand("1");
        levelManage1.addActionListener(this::actionPerformed);
        levelManage2 = new JRadioButton("2");
        levelManage2.setActionCommand("2");
        levelManage2.addActionListener(this::actionPerformed);
        levelManage3 = new JRadioButton("3");
        levelManage3.setActionCommand("3");
        levelManage3.addActionListener(this::actionPerformed);
        levelManage4 = new JRadioButton("4");
        levelManage4.setActionCommand("4");
        levelManage4.addActionListener(this::actionPerformed);
        levelManage5 = new JRadioButton("5");
        levelManage5.setActionCommand("5");
        levelManage5.addActionListener(this::actionPerformed);
        levelManage1.setBounds(100,100,80,30);
        levelManage2.setBounds(100,200,80,30);
        levelManage3.setBounds(100,300,80,30);
        levelManage4.setBounds(100,400,80,30);
        levelManage5.setBounds(100,500,80,30);
        getContentPane().setLayout(null);
        bg = new ButtonGroup();
        bg.add(levelManage1);
        bg.add(levelManage2);
        bg.add(levelManage3);
        bg.add(levelManage4);
        bg.add(levelManage5);
        getContentPane().add(levelManage1);
        getContentPane().add(levelManage2);
        getContentPane().add(levelManage3);
        getContentPane().add(levelManage4);
        getContentPane().add(levelManage5);
        getContentPane().add(confirm);
        setSize(300,300);
        setLayout(null);
        setVisible(true);
        confirm.addActionListener(e->{
            this.dispose();
            this.setVisible(false);
        });
    }
    public int getLevel(){
        return level;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        level = Integer.parseInt(bg.getSelection().getActionCommand());
        System.out.println(level);
    }
}
