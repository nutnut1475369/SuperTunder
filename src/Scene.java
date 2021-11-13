//import editor.MyImageIcon;
//import editor.MySoundEffect;
//
//import javax.swing.*;
//
//public class Scene extends JLabel {
//    private JLabel background;
//    private MyImageIcon imgicon;
//    private String imgLocation,soundLocation;
//    private MySoundEffect soundeff;
//    public Scene(String imgLo, String soundLo){
//        imgLocation = imgLo;
//        soundLocation = soundLo;
//    }
//    public void run(){
//        imgicon = new MyImageIcon(imgLocation);
//        background = new JLabel(imgicon);
//        soundeff = new MySoundEffect(soundLocation);
//        soundeff.playLoop();
//        super.add(background);
//        revalidate();
//        repaint();
//    }
//}
