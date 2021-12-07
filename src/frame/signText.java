package frame;


import javax.swing.*;
import java.awt.*;

public class signText extends JFrame {
    private int frameWidth = 1200, frameHeight = 800;
    Dimension ss = Toolkit.getDefaultToolkit().getScreenSize();
    public signText() {
        setTitle("AI NUT MAI WAI LEW");
        setBounds(ss.width / 2 - frameWidth / 2, ss.height / 2 - frameHeight / 2, frameWidth/2, frameHeight);
        setResizable(false);
        setVisible(true);
        add(new JLabel(new ImageIcon("resources/object/sign.png")));
        validate();
    }
}
