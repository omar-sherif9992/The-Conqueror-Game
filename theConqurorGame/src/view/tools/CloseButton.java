package view.tools;

import java.awt.*;

public class CloseButton extends MyButton {
    public CloseButton(){

        this.setVisible(true);
        this.setBackground(Color.WHITE);
        this.setFont(new Font("New Roman",Font.BOLD,30));
        this.setForeground(Color.BLACK);
        this.setText("❌");



    }
}
