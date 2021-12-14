/*
            Member
        Nutapon   manusopit     6313127
        Thanawat  Tejapijaya    6313173
        Pasid     Khumjanad     6313177
        Pisit     Lounseng      6313178
*/
package editor;

import javax.swing.*;
import java.awt.*;

public class MyImageIcon extends ImageIcon {
    public MyImageIcon(String fname) {
        super(fname);
    }

    public MyImageIcon(Image image) {
        super(image);
    }

    public MyImageIcon resize(int width, int height) {
        Image oldimg = this.getImage();
        Image newimg = oldimg.getScaledInstance(width, height, java.awt.Image.SCALE_SMOOTH);
        return new MyImageIcon(newimg);
    }
};