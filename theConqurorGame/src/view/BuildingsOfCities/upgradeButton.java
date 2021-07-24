package view.BuildingsOfCities;

import view.tools.MyButton;

import java.awt.*;

public class upgradeButton extends MyButton {


    public upgradeButton(){
        this.setVisible(false);
        this.setBackground(Color.WHITE);
        this.setFont(new Font("New Roman",Font.BOLD,15));
        this.setForeground(Color.BLACK);
        this.setText("Upgrade");


    }
}
