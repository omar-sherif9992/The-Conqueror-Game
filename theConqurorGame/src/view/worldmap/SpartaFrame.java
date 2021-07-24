package view.worldmap;

import view.BuildingsOfCities.SpartaTown;
import view.DefendingArmyCountries.DefendingArmySparta;
import view.tools.MyButton;
import view.tools.MyFrame;
import view.tools.MyLabel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SpartaFrame extends MyFrame implements ActionListener {
    private CitiesPanel citiesPanel=new CitiesPanel();
    private MyLabel spartaLogoLabel;
    private MyButton closeButton;
    private SpartaTown spartaTown;
    private DefendingArmySparta defendingArmySparta=new DefendingArmySparta();



    public SpartaFrame(boolean Gender, String namePlayer, double playerTreasury, double playerFood){
        citiesPanel.getCityButtonsPannel().getDefending().addActionListener(this);
        spartaTown = new SpartaTown(Gender,namePlayer,playerTreasury, playerFood);
        spartaTown.setVisible(false);
        citiesPanel.getCityButtonsPannel().getBuilding().addActionListener(this);
        closeButton=citiesPanel.getCityButtonsPannel().getClose();
        closeButton.addActionListener(this);
        citiesPanel.setVisible(true);
        this.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        JLabel mapLabel=new JLabel();
        ImageIcon image=new ImageIcon("newMapEdited.jpg");
        mapLabel.setIcon(image);
        mapLabel.setOpaque(false);
        mapLabel.setVisible(true);
        mapLabel.setVerticalAlignment(JLabel.CENTER);
        mapLabel.setHorizontalAlignment(JLabel.CENTER);
        mapLabel.setBounds(6,-5,1530,850);
        mapLabel.setLayout(null);

        ImageIcon spartaLogo=new ImageIcon("Spartan.jpeg");
        spartaLogoLabel=new MyLabel();
        spartaLogoLabel.setOpaque(false);
        spartaLogoLabel.setIcon(spartaLogo);
        spartaLogoLabel.setVerticalAlignment(JLabel.CENTER);
        spartaLogoLabel.setHorizontalAlignment(JLabel.CENTER);
        mapLabel.add(citiesPanel.getCityButtonsPannel());
        spartaLogoLabel.setBounds(500,200,500,500);
        mapLabel.add(spartaLogoLabel);
        citiesPanel.setLayout(new BorderLayout());
        spartaLogoLabel.setVisible(true);

        mapLabel.add(spartaLogoLabel);
        this.setVisible(false);

        this.add(mapLabel);
        this.add(citiesPanel);
        this.reload();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==closeButton){
            this.dispose();
        }
        if (e.getSource()==citiesPanel.getCityButtonsPannel().getBuilding()){
            spartaTown.setVisible(true);
            this.dispose();

        }
        if (e.getSource()==citiesPanel.getCityButtonsPannel().getDefending()){
            defendingArmySparta.setVisible(true);
            this.dispose();

        }
    }

    public SpartaTown getSpartaTown() {
        return spartaTown;
    }
    public DefendingArmySparta getDefendingArmySparta() {
        return defendingArmySparta;
    }
}
