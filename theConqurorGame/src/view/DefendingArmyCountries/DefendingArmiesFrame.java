package view.DefendingArmyCountries;
//Todo just created not connected with the world map nor with the defendinarmy of each country
import view.tools.CloseButton;
import view.tools.MyButton;
import view.tools.MyFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DefendingArmiesFrame extends MyFrame implements ActionListener {
  private boolean cairoFlag;
    private boolean romeFlag;
    private boolean spartaFlag;
    private MyButton spartaButton=new MyButton();
    private MyButton cairoButton=new MyButton();
    private MyButton romeButton=new MyButton();
    private ImageIcon romeLogo=new ImageIcon("RomeEditedlk.jpg");
    private ImageIcon cairoLogo=new ImageIcon("CairoEdited.jpg");
    private  ImageIcon spartaLogo=new ImageIcon("Spartan.jpeg");
    private CloseButton closeButton=new CloseButton();



    private JLabel defendingLabel=new JLabel("Choose your Defending Army");

    public DefendingArmiesFrame(boolean cairoFlag,boolean spartaFlag,boolean romeFlag){

        this.setVisible(false);
        this.getContentPane().setBackground(new Color(229, 162, 56));
            this.setLayout(null);
            this.setBounds(300,200,1000,500);
        this.DefendingLabel();
        closeButton.addActionListener(this);
        closeButton.setBounds(900,00,100,70);
        this.add(closeButton);

        spartaButton.setVisible(true);
        cairoButton.setVisible(true);
        romeButton.setVisible(true);

        spartaButton.setEnabled(false);
        cairoButton.setEnabled(false);
        romeButton.setEnabled(false);
        cairoButton.setIcon(cairoLogo);
        romeButton.setIcon(romeLogo);
        spartaButton.setIcon(spartaLogo);

        romeButton.setBounds(20,150,300,300);
        cairoButton.setBounds(340,150,300,300);
        spartaButton.setBounds(660,150,300,300);


       this.add(spartaButton);
  this.add(cairoButton);
        this.add(romeButton);


        this.cairoFlag=cairoFlag;
        this.spartaFlag=spartaFlag;
        this.romeFlag=romeFlag;
        updateButtons();
    }
    public void DefendingLabel(){
        defendingLabel.setBounds(300,-100,500,300);
        defendingLabel.setFont(new Font("NEW ROMAN",Font.BOLD,30));
        defendingLabel.setForeground(Color.BLACK);
        this.add(defendingLabel);
    }
    public void updateButtons(){
        if (cairoFlag){
            cairoButton.setEnabled(true);


        }
        if (spartaFlag){
            spartaButton.setEnabled(true);

        }
        if (romeFlag){
            romeButton.setEnabled(true);
        }
    }

    public MyButton getSpartaButton() {
        return spartaButton;
    }

    public MyButton getCairoButton() {
        return cairoButton;
    }

    public MyButton getRomeButton() {
        return romeButton;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
       if (e.getSource()==closeButton)
           this.dispose();

    }
}
