package view.BuildingsOfCities;

import view.tools.MyButton;
import view.tools.MyFrame;
import view.worldmap.PlayerLabel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class CairoTown extends MyFrame implements ActionListener {

   private ImageIcon cairoTown=new ImageIcon("CairoTown9Edited.jpg");
   private JLabel cairoTownLabel=new JLabel();
   private BuildingButtons buttons=new BuildingButtons();
   private ArrayList <MyButton> unitsButtons=new ArrayList<MyButton>();
   private boolean endTurn;
   private PlayerLabel playerLabel;


    public CairoTown(boolean Gender,String namePlayer,double playerTreasury,double playerFood){
        this.playerLabel=new PlayerLabel(Gender,namePlayer,playerTreasury, playerFood);
        playerLabel.setBounds(-20,500,1600,500);
        cairoTownLabel.add(playerLabel);
        buttons.getCloseButton().setBounds(50,50,100,70);



       cairoTownLabel.add(buttons.getCloseButton());
       buttons.getCloseButton().addActionListener(this);

       cairoTownLabel.setIcon(cairoTown);
       cairoTownLabel.setOpaque(false);
       cairoTownLabel.setVisible(true);
       cairoTownLabel.setVerticalAlignment(JLabel.CENTER);
       cairoTownLabel.setHorizontalAlignment(JLabel.CENTER);
       cairoTownLabel.setBounds(-60,0,1530,850);
       cairoTownLabel.setLayout(null);
       this.setBuyButtons();
       this.setUpgradeButtons();


       this.add(cairoTownLabel);

       this.reload();

    }

    public static void main(String[] args) {
        new CairoTown(true,"oma",33,33);
    }


    public void setBuyButtons(){
        buttons.getArcheryBuildingBuy().setBounds(350,250,100,70);
        cairoTownLabel.add(buttons.getArcheryBuildingBuy());
        buttons.getArcheryBuildingBuy().setToolTipText("Cost : 1500");


        buttons.getBarracksBuildingBuy().setBounds(610,350,100,70);
        cairoTownLabel.add(buttons.getBarracksBuildingBuy());
        buttons.getBarracksBuildingBuy().setToolTipText("Cost : 2000");

        buttons.getStableBuildingBuy().setBounds(890,450,100,70);
        cairoTownLabel.add(buttons.getStableBuildingBuy());
        buttons.getStableBuildingBuy().setToolTipText("Cost : 2500");

        buttons.getFarmBuy().setBounds(1110,250,100,70);
        cairoTownLabel.add(buttons.getFarmBuy());
        buttons.getFarmBuy().setToolTipText("Cost : 1000");

        buttons.getMarketBuy().setBounds(840,150,100,70);
        cairoTownLabel.add(buttons.getMarketBuy());
        buttons.getMarketBuy().setToolTipText("Cost : 1500");
    }
    public void setUpgradeButtons(){
        buttons.getArcheryBuildingUpgrade().setBounds(200,330,100,70);
        cairoTownLabel.add(buttons.getArcheryBuildingUpgrade());
        buttons.getArcheryBuildingUpgrade().setToolTipText("Upgrade Cost : ↗ Level 2  800");

        buttons.getBarracksBuildingUpgrade().setBounds(460,430,100,70);
        cairoTownLabel.add(buttons.getBarracksBuildingUpgrade());
        buttons.getBarracksBuildingUpgrade().setToolTipText("Upgrade Cost : ↗ Level 2  1000");

        buttons.getStableBuildingUpgrade().setBounds(740,530,100,70);
        cairoTownLabel.add(buttons.getStableBuildingUpgrade());
        buttons.getStableBuildingUpgrade().setToolTipText("Upgrade Cost : ↗ Level 2  1500");

        buttons.getFarmUpgrade().setBounds(960,330,100,70);
        cairoTownLabel.add(buttons.getFarmUpgrade());
        buttons.getFarmUpgrade().setToolTipText("Upgrade Cost : ↗ Level 2  500");



        buttons.getMarketUpgrade().setBounds(670,170,100,70);
        cairoTownLabel.add(buttons.getMarketUpgrade());
        buttons.getMarketUpgrade().setToolTipText("Upgrade Cost : ↗ Level 2  700");


        buttons.getArcheryBuildingRecruit().setBounds(350,350,100,70);
        cairoTownLabel.add(buttons.getArcheryBuildingRecruit());
        buttons.getArcheryBuildingRecruit().setToolTipText("Recruitment Cost : 400");

        buttons.getBarracksBuildingRecruit().setBounds(610,450,100,70);
        cairoTownLabel.add(buttons.getBarracksBuildingRecruit());
        buttons.getBarracksBuildingRecruit().setToolTipText("Recruitment Cost : 500");

        buttons.getStableBuildingRecruit().setBounds(890,550,100,70);
        cairoTownLabel.add(buttons.getStableBuildingRecruit());
        buttons.getStableBuildingRecruit().setToolTipText("Recruitment COst : 600");
    }


    public void setPlayerLabel(PlayerLabel playerLabel) {
        this.playerLabel = playerLabel;
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

    public ArrayList<MyButton> getUnitsButtons() {
        return unitsButtons;
    }

    public JLabel getCairoTownLabel() {
        return cairoTownLabel;
    }

    public boolean isEndTurn() {
        return endTurn;
    }

    public PlayerLabel getPlayerLabel() {
        return playerLabel;
    }

}
