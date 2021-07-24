package view.worldmap;

import view.BuildingsOfCities.CairoTown;
import view.DefendingArmyCountries.DefendingArmyCairo;
import view.tools.MyButton;
import view.tools.MyFrame;
import view.tools.MyLabel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CairoFrame extends MyFrame implements ActionListener {
    private CitiesPanel citiesPanel=new CitiesPanel();
    private MyLabel cairoLogoLabel;
    private MyButton closeButton;
    private CairoTown cairoTown;
    private DefendingArmyCairo defendingArmyCairo=new DefendingArmyCairo();



    public CairoFrame(boolean Gender, String namePlayer, double playerTreasury, double playerFood){
        citiesPanel.getCityButtonsPannel().getDefending().addActionListener(this);


        cairoTown = new CairoTown(Gender,namePlayer,playerTreasury, playerFood);
        citiesPanel.getCityButtonsPannel().getBuilding().addActionListener(this);
        cairoTown.setVisible(false);
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

        ImageIcon cairoLogo=new ImageIcon("CairoEdited.jpg");
        cairoLogoLabel=new MyLabel();
        cairoLogoLabel.setOpaque(false);
        cairoLogoLabel.setIcon(cairoLogo);
        cairoLogoLabel.setVerticalAlignment(JLabel.CENTER);
        cairoLogoLabel.setHorizontalAlignment(JLabel.CENTER);
        mapLabel.add(citiesPanel.getCityButtonsPannel());
        cairoLogoLabel.setBounds(500,200,500,500);
        mapLabel.add(cairoLogoLabel);
        citiesPanel.setLayout(new BorderLayout());
        cairoLogoLabel.setVisible(true);

        mapLabel.add(cairoLogoLabel);
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
            cairoTown.setVisible(true);
            this.dispose();

        }
        if (e.getSource()==citiesPanel.getCityButtonsPannel().getDefending()){
            defendingArmyCairo.setVisible(true);
            this.dispose();

        }


    }

    public CairoTown getCairoTown() {
        return cairoTown;
    }


    public CitiesPanel getCitiesPanel() {
        return citiesPanel;
    }

    public DefendingArmyCairo getDefendingArmyCairo() {
        return defendingArmyCairo;
    }
}
