package view.worldmap;

import view.tools.MyLabel;

import javax.swing.*;
import java.awt.*;

public class CitiesPanel extends JPanel {
    private CityButtonsPannel cityButtonsPannel;
    private MyLabel romeLogoLabel;
    private MyLabel spartaLogoLabel;


    public CitiesPanel() {

        cityButtonsPannel = new CityButtonsPannel();//cityFeatures is created

        this.setLayout(null);


//        ImageIcon cairoLogo=new ImageIcon("CairoEdited.jpg");
//        MyLabel cairoLogoLabel=new MyLabel();
//
//        cairoLogoLabel.setOpaque(false);
//        cairoLogoLabel.setIcon(cairoLogo);
//        cairoLogoLabel.setVerticalAlignment(JLabel.CENTER);
//        cairoLogoLabel.setHorizontalAlignment(JLabel.CENTER);
        this.setBounds(550, 100, 400, 600);
        this.setLayout(new BorderLayout());

        cityButtonsPannel.setBounds(615, 715, 300, 100);
        this.add(cityButtonsPannel);
        this.setVisible(true);
    }

//
//    public void CairoPanel(){
//        MyFrame frameCairo=frame();
//        ImageIcon cairoLogo=new ImageIcon("CairoEdited.jpg");
//        cairoLogoLabel=new MyLabel();
//        cairoLogoLabel.setOpaque(false);
//        cairoLogoLabel.setIcon(cairoLogo);
//        cairoLogoLabel.setVerticalAlignment(JLabel.CENTER);
//        cairoLogoLabel.setHorizontalAlignment(JLabel.CENTER);
//        this.add(cairoLogoLabel);
//        this.setVisible(true);
//        frameCairo.add(this);
//    }


//   public MyFrame frame(){
//
//       MyFrame frame=new MyFrame();
//       frame.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
//       JLabel mapLabel=new JLabel();
//       ImageIcon image=new ImageIcon("newMapEdited.jpg");
//       mapLabel.setIcon(image);
//       mapLabel.setOpaque(false);
//       mapLabel.setVisible(true);
//       mapLabel.setVerticalAlignment(JLabel.CENTER);
//       mapLabel.setHorizontalAlignment(JLabel.CENTER);
//       mapLabel.setBounds(6,-5,1530,850);
//
//        return frame;
//   }


    public void setCityButtonsPannel(CityButtonsPannel cityButtonsPannel) {
        this.cityButtonsPannel = cityButtonsPannel;
    }
    public CityButtonsPannel getCityButtonsPannel() {
        return cityButtonsPannel;
    }

}