package view.worldmap;

import view.BuildingsOfCities.RomeTown;
import view.DefendingArmyCountries.DefendingArmyRome;
import view.tools.MyButton;
import view.tools.MyFrame;
import view.tools.MyLabel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RomeFrame extends MyFrame implements ActionListener {
    private CitiesPanel citiesPanel=new CitiesPanel();
    private MyLabel romeLogoLabel;
    private MyButton closeButton;
    private RomeTown romeTown;

    private DefendingArmyRome defendingArmyRome=new DefendingArmyRome();



    public RomeFrame(boolean Gender, String namePlayer, double playerTreasury, double playerFood){
        citiesPanel.getCityButtonsPannel().getDefending().addActionListener(this);
        romeTown = new RomeTown(Gender,namePlayer,playerTreasury, playerFood);
        romeTown.setVisible(false);
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

        ImageIcon romeLogo=new ImageIcon("RomeEdited9.jpg");
        romeLogoLabel=new MyLabel();
        romeLogoLabel.setOpaque(false);
        romeLogoLabel.setIcon(romeLogo);
        romeLogoLabel.setVerticalAlignment(JLabel.CENTER);
        romeLogoLabel.setHorizontalAlignment(JLabel.CENTER);
        mapLabel.add(citiesPanel.getCityButtonsPannel());
        romeLogoLabel.setBounds(500,200,500,500);
        mapLabel.add(romeLogoLabel);
        citiesPanel.setLayout(new BorderLayout());
        romeLogoLabel.setVisible(true);
        mapLabel.add(romeLogoLabel);
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
            romeTown.setVisible(true);
            this.dispose();

        }
        if (e.getSource()==citiesPanel.getCityButtonsPannel().getDefending()){
            defendingArmyRome.setVisible(true);
            this.dispose();

        }

    }

    public RomeTown getRomeTown() {
        return romeTown;
    }

    public DefendingArmyRome getDefendingArmyRome() {
        return defendingArmyRome;
    }
}
