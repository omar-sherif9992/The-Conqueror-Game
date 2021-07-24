package view.tools;

import javax.swing.*;
import java.awt.*;

public class MyButton extends JButton {
    public MyButton(){
       this.setCursor(new Cursor(Cursor.HAND_CURSOR));
        this.setVisible(true);
        this.setFocusable(false);
        this.setEnabled(true);

    }


}
