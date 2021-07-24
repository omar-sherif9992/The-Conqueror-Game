package view.BuildingsOfCities;

import view.tools.MyFrame;
import view.worldmap.PlayerLabel;

import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class SpartaTown extends MyFrame implements ActionListener {
    private ImageIcon spartaTown=new ImageIcon("SpartaTown1Edited.jpg");

    private JLabel spartaTownLabel=new JLabel();
    private BuildingButtons buttons=new BuildingButtons();
    private PlayerLabel playerLabel;

    public SpartaTown(boolean Gender,String namePlayer,double playerTreasury,double playerFood){
        this.playerLabel=new PlayerLabel(Gender,namePlayer,playerTreasury, playerFood);
        playerLabel.setBounds(-20,500,1600,500);
        spartaTownLabel.add(playerLabel);
        buttons.getCloseButton().setBounds(50,50,100,70);
        spartaTownLabel.add(buttons.getCloseButton());
        buttons.getCloseButton().addActionListener(this);

        spartaTownLabel.setIcon(spartaTown);
        spartaTownLabel.setOpaque(false);
        spartaTownLabel.setVisible(true);
        spartaTownLabel.setVerticalAlignment(JLabel.CENTER);
        spartaTownLabel.setHorizontalAlignment(JLabel.CENTER);
        spartaTownLabel.setBounds(-60,0,1530,850);
        spartaTownLabel.setLayout(null);
        this.setBuyButtons();
        this.setUpgradeButtons();
        this.add(spartaTownLabel);
        this.reload();
    }
    public void setBuyButtons(){
        buttons.getArcheryBuildingBuy().setBounds(350,250,100,70);
        spartaTownLabel.add(buttons.getArcheryBuildingBuy());
        buttons.getArcheryBuildingBuy().setToolTipText("Cost : 1500");


        buttons.getBarracksBuildingBuy().setBounds(610,350,100,70);
        spartaTownLabel.add(buttons.getBarracksBuildingBuy());
        buttons.getBarracksBuildingBuy().setToolTipText("Cost : 2000");

        buttons.getStableBuildingBuy().setBounds(890,450,100,70);
        spartaTownLabel.add(buttons.getStableBuildingBuy());
        buttons.getStableBuildingBuy().setToolTipText("Cost : 2500");

        buttons.getFarmBuy().setBounds(1110,250,100,70);
        spartaTownLabel.add(buttons.getFarmBuy());
        buttons.getFarmBuy().setToolTipText("Cost : 1000");

        buttons.getMarketBuy().setBounds(840,150,100,70);
        spartaTownLabel.add(buttons.getMarketBuy());
        buttons.getMarketBuy().setToolTipText("Cost : 1500");
    }
    public void setUpgradeButtons(){
        buttons.getArcheryBuildingUpgrade().setBounds(200,330,100,70);
        spartaTownLabel.add(buttons.getArcheryBuildingUpgrade());
        buttons.getArcheryBuildingUpgrade().setToolTipText("Upgrade : ↗ Level 2  800");

        buttons.getBarracksBuildingUpgrade().setBounds(460,430,100,70);
        spartaTownLabel.add(buttons.getBarracksBuildingUpgrade());
        buttons.getBarracksBuildingUpgrade().setToolTipText("Upgrade : ↗ Level 2  1000");

        buttons.getStableBuildingUpgrade().setBounds(740,530,100,70);
        spartaTownLabel.add(buttons.getStableBuildingUpgrade());
        buttons.getStableBuildingUpgrade().setToolTipText("Upgrade : ↗ Level 2  1500");

        buttons.getFarmUpgrade().setBounds(960,330,100,70);
        spartaTownLabel.add(buttons.getFarmUpgrade());
        buttons.getFarmUpgrade().setToolTipText("Upgrade : ↗ Level 2  500");

        buttons.getMarketUpgrade().setBounds(670,170,100,70);
        spartaTownLabel.add(buttons.getMarketUpgrade());
        buttons.getMarketUpgrade().setToolTipText("Upgrade : ↗ Level 2  700");

        buttons.getArcheryBuildingRecruit().setBounds(350,350,100,70);
        spartaTownLabel.add(buttons.getArcheryBuildingRecruit());
        buttons.getArcheryBuildingRecruit().setToolTipText("Recruitment Cost : 400");

        buttons.getBarracksBuildingRecruit().setBounds(610,450,100,70);
        spartaTownLabel.add(buttons.getBarracksBuildingRecruit());
        buttons.getBarracksBuildingRecruit().setToolTipText("Recruitment Cost : 500");

        buttons.getStableBuildingRecruit().setBounds(890,550,100,70);
        spartaTownLabel.add(buttons.getStableBuildingRecruit());
        buttons.getStableBuildingRecruit().setToolTipText("Recruitment Cost : 600");
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==buttons.getCloseButton()){
            this.dispose();
        }

    }


    public BuildingButtons getButtons() {
        return buttons;
    }

    public PlayerLabel getPlayerLabel() {
        return playerLabel;
    }
}
