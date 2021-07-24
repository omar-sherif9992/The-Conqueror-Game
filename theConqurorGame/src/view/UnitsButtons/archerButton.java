package view.UnitsButtons;

import javax.swing.*;
import java.awt.*;

public class archerButton extends JLabel {
    private int level;
    private String type="Archer";


    public archerButton(int level){
        this.setVisible(true);
        this.setPreferredSize(new Dimension(200,135));

        switch (level){
            case 1: this.setIcon(new ImageIcon("Archer1EditedNew.jpg")); break;
            case 2:   this.setIcon(new ImageIcon("Archer2EditedNew.jpg"));  break;
            case 3:   this.setIcon(new ImageIcon("Archer3EditedNew.jpg"));  break;
        }

    }



}
