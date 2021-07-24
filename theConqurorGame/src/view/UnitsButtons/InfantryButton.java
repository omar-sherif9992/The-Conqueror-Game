package view.UnitsButtons;

import javax.swing.*;
import java.awt.*;

public class InfantryButton extends JLabel{

    public InfantryButton(int level){
        this.setVisible(true);
        this.setPreferredSize(new Dimension(200,300));
        switch (level){
            case 1: this.setIcon(new ImageIcon("Infantry1Editednew.jpg")); break;
            case 2:   this.setIcon(new ImageIcon("Infantry2EditedNew.jpg"));  break;
            case 3:   this.setIcon(new ImageIcon("Infantry3EditedNew.jpg"));  break;
        }



    }
}
