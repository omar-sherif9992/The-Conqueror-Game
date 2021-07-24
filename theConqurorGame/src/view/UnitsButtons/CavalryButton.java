package view.UnitsButtons;

import javax.swing.*;
import java.awt.*;

public class CavalryButton extends JLabel{


    public CavalryButton(int level ){
        this.setVisible(true);
        this.setPreferredSize(new Dimension(200,300));
        switch (level){
            case 1: this.setIcon(new ImageIcon("Cavalry1New.jpg")); break;
            case 2:   this.setIcon(new ImageIcon("Cavalry2EditedNew.jpg"));  break;
            case 3:   this.setIcon(new ImageIcon("Cavalry3EditedNew.jpg"));  break;
        }





    }

}
