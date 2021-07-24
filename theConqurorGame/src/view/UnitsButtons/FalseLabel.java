package view.UnitsButtons;

import javax.swing.*;
import java.awt.*;

public class FalseLabel extends JLabel {


    public FalseLabel(){
        this.setVisible(true);
        this.setPreferredSize(new Dimension(200,300));
        this.setBackground(Color.BLACK);
        this.setOpaque(true);
        this.setEnabled(false);

    }

}
