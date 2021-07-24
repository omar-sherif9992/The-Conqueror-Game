package view.UnitsButtons;

import view.tools.MyButton;

import javax.swing.*;
import java.awt.*;

public class AddUnitLabel extends JLabel {
    MyButton addButton=new MyButton();
    public AddUnitLabel(){
        this.setPreferredSize(new Dimension(325,135));

        this.setVisible(true);
        this.setBackground(Color.WHITE);
        this.setText("Add Unit");
        ImageIcon image=new ImageIcon();

        this.setFont(new Font("New Roman",Font.BOLD,35));
        this.setHorizontalAlignment(JLabel.CENTER);
        this.setVerticalAlignment(JLabel.TOP);
        this.setForeground(Color.red);
        this.setOpaque(true);

        addButton.setText("+");
        addButton.setBackground(Color.lightGray);
        addButton.setFont(new Font("New Roman",Font.BOLD,27));
        addButton.setBounds(145,65,50,50);
        addButton.setVisible(true);
        this.add(addButton);
    }

}
