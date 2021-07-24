package view.tools;

import javax.swing.*;
import java.awt.*;

public class MyToggledButton extends JToggleButton {
    public MyToggledButton (){
        this.setCursor(new Cursor(Cursor.HAND_CURSOR));
        this.setVisible(true);
        this.setFocusable(false);
        this.setEnabled(true);


    }
}
