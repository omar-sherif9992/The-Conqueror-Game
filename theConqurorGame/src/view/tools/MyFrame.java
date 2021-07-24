package view.tools;

import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;

public class MyFrame extends JFrame {

    // You can use your own values frame.getContentPane().add(label)

    public MyFrame(){
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setBounds(-10,-5,1600,1000);
        ImageIcon image =new ImageIcon("THE_Conquror_Logo.jpeg");
        this.setIconImage(image.getImage());
        this.setTitle("THE CONQUEROR");
        this.setResizable(true);
        //Icon imgIcon = new ImageIcon(this.getClass().getResource("ajax-loader.gif"));
        //JLabel label = new JLabel(imgIcon); label.setBounds(668, 43, 46, 14);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent we)
            {
                String ObjButtons[] = {"Yes","No"};
                int PromptResult = JOptionPane.showOptionDialog(null,"Are you sure you want to exit?","Exit",JOptionPane.DEFAULT_OPTION,JOptionPane.WARNING_MESSAGE,null,ObjButtons,ObjButtons[1]);
                if(PromptResult==JOptionPane.YES_OPTION)
                {
                    System.exit(0);
                }
            }
        });
    }

    public void reload(){
        this.revalidate();
        this.repaint();
    }


}
