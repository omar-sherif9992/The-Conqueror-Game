package Controller;

import engine.City;
import engine.Game;
import exceptions.*;
import units.Army;
import units.Status;
import units.Unit;
import view.ArmyButton.ArmyButton;
import view.BattleArenas.BattleArenas;
import view.BuildingsOfCities.BuildingButtons;
import view.BuildingsOfCities.BuyButton;
import view.BuildingsOfCities.recruitButton;
import view.BuildingsOfCities.upgradeButton;
import view.ControlledArmies.ControlledArmiesUnitsFrame;
import view.UnitsButtons.UnitButton;
import view.launchpage.LaunchPage;
import view.tools.MyButton;
import view.worldmap.WorldMap;

import javax.sound.sampled.*;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.concurrent.ConcurrentHashMap;

public class Controller implements ActionListener {
    private LaunchPage launchPage;
    private Game game;
    private boolean worldMapFlag=false;
    private Unit unit;
    private Army selectedMarchingArmy;
    private int indexOfSelectedMarchingArmy;
    private Army selectedBesiegingArmy;
    private int getIndexOfSelectedBesiegingArmy;
    private boolean flag;
    private boolean autoResolveFlag;
    private  int indexOfRelocateUnit;
    private ConcurrentHashMap<String, ArrayList<UnitButton>> enemyUnitButtonsMap;

    public Controller() {
        this.launchPage = new LaunchPage();
        this.launchPage.getContinueButton().addActionListener(this);
    }

    public void addActionListeners() { //TODO initiateArmyButton and relocateUnitButton visibility
        if (worldMapFlag){
            for (BuyButton button :this.launchPage.getWorldMap().getCairoFrame().getCairoTown().getButtons().getBuyButtons())
                button.addActionListener(this);
            for (upgradeButton button : this.launchPage.getWorldMap().getCairoFrame().getCairoTown().getButtons().getUpgradeButtons())
                button.addActionListener(this);
            for (recruitButton button: this.launchPage.getWorldMap().getCairoFrame().getCairoTown().getButtons().getRecruitButtons())
                button.addActionListener(this);

            for (BuyButton button :this.launchPage.getWorldMap().getRomeFrame().getRomeTown().getButtons().getBuyButtons())
                button.addActionListener(this);
            for (upgradeButton button : this.launchPage.getWorldMap().getRomeFrame().getRomeTown().getButtons().getUpgradeButtons())
                button.addActionListener(this);
            for (recruitButton button: this.launchPage.getWorldMap().getRomeFrame().getRomeTown().getButtons().getRecruitButtons())
                button.addActionListener(this);

            for (BuyButton button :this.launchPage.getWorldMap().getSpartaFrame().getSpartaTown().getButtons().getBuyButtons())
                button.addActionListener(this);
            for (upgradeButton button : this.launchPage.getWorldMap().getSpartaFrame().getSpartaTown().getButtons().getUpgradeButtons())
                button.addActionListener(this);
            for (recruitButton button: this.launchPage.getWorldMap().getSpartaFrame().getSpartaTown().getButtons().getRecruitButtons())
                button.addActionListener(this);

            this.launchPage.getWorldMap().getPlayerLabel().getEndTurn().addActionListener(this);
            this.launchPage.getWorldMap().getCairoFrame().getCairoTown().getPlayerLabel().getEndTurn().addActionListener(this);
            this.launchPage.getWorldMap().getSpartaFrame().getSpartaTown().getPlayerLabel().getEndTurn().addActionListener(this);
            this.launchPage.getWorldMap().getRomeFrame().getRomeTown().getPlayerLabel().getEndTurn().addActionListener(this);

        }
    }

    public void addLaySiegeActionListeners() {
        for (ControlledArmiesUnitsFrame frame : this.launchPage.getWorldMap().getMarchingArmiesFrame().getControlledArmiesUnitsFrame()) {
            frame.getLaySiege().addActionListener(this);
        }
    }

    public void addBattleFinishedActionListeners() {
        for (ControlledArmiesUnitsFrame frame : this.launchPage.getWorldMap().getMarchingArmiesFrame().getControlledArmiesUnitsFrame())
            if (frame.isFlag())
                frame.getBattleArenas().getBattleFinished().addActionListener(this);

        for (ControlledArmiesUnitsFrame frame : this.launchPage.getWorldMap().getBesiegingArmiesFrame().getControlledArmiesUnitsFrame())
            if (frame.isFlag())
                frame.getBattleArenas().getBattleFinished().addActionListener(this);
    }

    public void addInitiateArmyButtonsActionListeners() {
        this.launchPage.getWorldMap().getCairoFrame().getDefendingArmyCairo().getInitiateArmyCairo().addActionListener(this);
        this.launchPage.getWorldMap().getRomeFrame().getDefendingArmyRome().getInitiateArmyRome().addActionListener(this);
        this.launchPage.getWorldMap().getSpartaFrame().getDefendingArmySparta().getInitiateArmySparta().addActionListener(this);
    }

    public void addRelocateUnitActionListeners() {
        this.launchPage.getWorldMap().getCairoFrame().getDefendingArmyCairo().getRelocateUnitCairo().addActionListener(this);
        this.launchPage.getWorldMap().getSpartaFrame().getDefendingArmySparta().getRelocateUnitSparta().addActionListener(this);
        this.launchPage.getWorldMap().getRomeFrame().getDefendingArmyRome().getRelocateUnitRome().addActionListener(this);
    }

    public void addTargetCityButtonsActionListeners() {
        for (ControlledArmiesUnitsFrame frame : this.launchPage.getWorldMap().getControlledArmiesFrame().getControlledArmiesUnitsFrame()) {
            frame.getAttackCairoButton().addActionListener(this);
            frame.getAttackRomeButton().addActionListener(this);
            frame.getAttackSpartaButton().addActionListener(this);
        }

    }

    public void addUnitButtonsActionListeners() {
        for (UnitButton button : this.launchPage.getWorldMap().getCairoFrame().getDefendingArmyCairo().getUnitButtonsCairo())
            button.addActionListener(this);
        for (UnitButton button : this.launchPage.getWorldMap().getSpartaFrame().getDefendingArmySparta().getUnitButtonsSparta())
            button.addActionListener(this);
        for (UnitButton button : this.launchPage.getWorldMap().getRomeFrame().getDefendingArmyRome().getUnitButtonsRome())
            button.addActionListener(this);
    }

    public void addAutoResolveActionListeners() {
        for (ControlledArmiesUnitsFrame frame : this.launchPage.getWorldMap().getMarchingArmiesFrame().getControlledArmiesUnitsFrame()) {
            frame.getAutoResolve().addActionListener(this);
            frame.getLaySiege().addActionListener(this);
        }
    }

    public void handleContinueButton(ActionEvent e) throws IOException {
        MyButton button = this.launchPage.getContinueButton();

        if(e.getSource()==button){
            if((this.launchPage.getNameArea().getText().equals("Please Enter Your Name") || this.launchPage.getNameArea().getText().equals("")) ){
                JOptionPane.showMessageDialog(null,"Please enter your Username","Info missing",JOptionPane.WARNING_MESSAGE);
            }

            else if(!this.launchPage.getCairoRadio().isSelected() && !this.launchPage.getRomeRadio().isSelected() && !this.launchPage.getSpartaRadio().isSelected())
            {
                JOptionPane.showMessageDialog(null,"Please select a City","Info missing",JOptionPane.WARNING_MESSAGE);
            }
            else if(!this.launchPage.getKing().isSelected() && !this.launchPage.getQueen().isSelected())
            {
                JOptionPane.showMessageDialog(null,"Please select a character","Info missing",JOptionPane.WARNING_MESSAGE);
            }
            else {
                this.launchPage.setNamePlayer(this.launchPage.getNameArea().getText());
                if (this.launchPage.getRomeRadio().isSelected()){
                    this.launchPage.setCityName("rome");

                }
                else if(this.launchPage.getCairoRadio().isSelected()){
                    this.launchPage.setCityName("cairo");
                }
                else if (this.launchPage.getSpartaRadio().isSelected()){
                    this.launchPage.setCityName("sparta");
                }
                if(this.launchPage.getKing().isSelected()){
                    this.launchPage.setGender(true);
                }
                else if (this.launchPage.getQueen().isSelected())
                    this.launchPage.setGender(false);
                this.game = new Game(this.launchPage.getNamePlayer(),this.launchPage.getCityName());

                this.launchPage.setWorldMap(new WorldMap(this.launchPage.getCityName(),this.launchPage.isGender(),this.launchPage.getNamePlayer(),
                        this.game.getPlayer().getTreasury(),this.game.getPlayer().getFood(),this.game.getEnemyDefendingArmy()));
                worldMapFlag = true;
                addActionListeners();
                this.launchPage.getWorldMap().getControlledArmiesFrame().getPlayerNameLabel().setText("PLayer Name : " + this.launchPage.getNamePlayer());
                this.launchPage.getWorldMap().getMarchingArmiesFrame().getPlayerNameLabel().setText("Player Name : " + this.launchPage.getNamePlayer());
                this.launchPage.getWorldMap().getBesiegingArmiesFrame().getPlayerNameLabel().setText("Player Name : " + this.launchPage.getNamePlayer());
                this.launchPage.getWorldMap().getCairoFrame().getDefendingArmyCairo().getPlayerNameLabel().setText("Player Name : " + this.launchPage.getNamePlayer());
                this.launchPage.getWorldMap().getRomeFrame().getDefendingArmyRome().getPlayerNameLabel().setText("Player Name : " + this.launchPage.getNamePlayer());
                this.launchPage.getWorldMap().getSpartaFrame().getDefendingArmySparta().getPlayerNameLabel().setText("Player Name : " + this.launchPage.getNamePlayer());
                this.launchPage.getWorldMap().getControlledArmiesFrame().getFoodLabel().setText("Food : " + this.game.getPlayer().getFood());
                this.launchPage.getWorldMap().getMarchingArmiesFrame().getFoodLabel().setText("Food : " + this.game.getPlayer().getFood());
                this.launchPage.getWorldMap().getBesiegingArmiesFrame().getFoodLabel().setText("Food : " + this.game.getPlayer().getFood());
                this.launchPage.getWorldMap().getCairoFrame().getDefendingArmyCairo().getFoodLabel().setText("Food : " + this.game.getPlayer().getFood());
                this.launchPage.getWorldMap().getSpartaFrame().getDefendingArmySparta().getFoodLabel().setText("Food : " + this.game.getPlayer().getFood());
                this.launchPage.getWorldMap().getRomeFrame().getDefendingArmyRome().getFoodLabel().setText("Food : " + this.game.getPlayer().getFood());
                this.launchPage.getWorldMap().getControlledArmiesFrame().getTreasuryLabel().setText("Treasury : " + this.game.getPlayer().getTreasury());
                this.launchPage.getWorldMap().getMarchingArmiesFrame().getTreasuryLabel().setText("Treasury : " + this.game.getPlayer().getTreasury());
                this.launchPage.getWorldMap().getBesiegingArmiesFrame().getTreasuryLabel().setText("Treasury : " + this.game.getPlayer().getTreasury());
                this.launchPage.getWorldMap().getRomeFrame().getDefendingArmyRome().getTreasuryLabel().setText("Treasury : " + this.game.getPlayer().getTreasury());
                this.launchPage.getWorldMap().getSpartaFrame().getDefendingArmySparta().getTreasuryLabel().setText("Treasury : " +  this.game.getPlayer().getTreasury());
                this.launchPage.getWorldMap().getCairoFrame().getDefendingArmyCairo().getTreasuryLabel().setText("Treasury : " + this.game.getPlayer().getTreasury());
                enemyUnitButtonsMap=this.game.getEnemyDefendingArmy();
                this.launchPage.dispose();
            }
        }
    }

    public void handleCairoButtons(ActionEvent e) {
        BuildingButtons buildingButtonsCairo = this.launchPage.getWorldMap().getCairoFrame().getCairoTown().getButtons();
        if (e.getSource()==buildingButtonsCairo.getArcheryBuildingBuy()) {
            try {
                this.game.getPlayer().build("ArcheryRange","Cairo");
                buildingButtonsCairo.getArcheryBuildingBuy().setVisible(false);
                buildingButtonsCairo.getArcheryBuildingUpgrade().setVisible(true);
                buildingButtonsCairo.getArcheryBuildingRecruit().setVisible(true);
                playSound("Cash Register (Kaching) - Sound Effect (HD).wav");
                updatePlayerLabel();
            }
            catch (NotEnoughGoldException ex) {
                JOptionPane.showMessageDialog(null,ex.getMessage(),"Action Denied",JOptionPane.WARNING_MESSAGE);
            }
        }
        else if (e.getSource()==buildingButtonsCairo.getBarracksBuildingBuy()) {
            try {
                this.game.getPlayer().build("Barracks","Cairo");
                buildingButtonsCairo.getBarracksBuildingBuy().setVisible(false);
                buildingButtonsCairo.getBarracksBuildingUpgrade().setVisible(true);
                buildingButtonsCairo.getBarracksBuildingRecruit().setVisible(true);
                playSound("Cash Register (Kaching) - Sound Effect (HD).wav");
                updatePlayerLabel();
            }
            catch (NotEnoughGoldException ex) {
                JOptionPane.showMessageDialog(null,ex.getMessage(),"Action Denied",JOptionPane.WARNING_MESSAGE);
            }
        }

        else if (e.getSource()==buildingButtonsCairo.getStableBuildingBuy()) {
            try {
                this.game.getPlayer().build("Stable","Cairo");
                buildingButtonsCairo.getStableBuildingBuy().setVisible(false);
                buildingButtonsCairo.getStableBuildingUpgrade().setVisible(true);
                buildingButtonsCairo.getStableBuildingRecruit().setVisible(true);
                playSound("Cash Register (Kaching) - Sound Effect (HD).wav");
                updatePlayerLabel();
            }
            catch (NotEnoughGoldException ex) {
                JOptionPane.showMessageDialog(null,"Not Enough Gold to build","Action Denied",JOptionPane.WARNING_MESSAGE);
            }
        }
        else if (e.getSource()==buildingButtonsCairo.getMarketBuy()) {
            try {
                this.game.getPlayer().build("Market","Cairo");
                buildingButtonsCairo.getMarketBuy().setVisible(false);
                buildingButtonsCairo.getMarketUpgrade().setVisible(true);
                playSound("Cash Register (Kaching) - Sound Effect (HD).wav");
                updatePlayerLabel();
            }
            catch (NotEnoughGoldException ex) {
                JOptionPane.showMessageDialog(null,"Not Enough Gold to build","Action Denied",JOptionPane.WARNING_MESSAGE);
            }
        }
        else if (e.getSource()==buildingButtonsCairo.getFarmBuy()) {
            try {
                this.game.getPlayer().build("Farm","Cairo");
                buildingButtonsCairo.getFarmBuy().setVisible(false);
                buildingButtonsCairo.getFarmUpgrade().setVisible(true);
                playSound("Cash Register (Kaching) - Sound Effect (HD).wav");
                updatePlayerLabel();
            }
            catch (NotEnoughGoldException ex) {
                JOptionPane.showMessageDialog(null,"Not Enough Gold to build","Action Denied",JOptionPane.WARNING_MESSAGE);
            }
        }
        else if (e.getSource()==buildingButtonsCairo.getArcheryBuildingUpgrade()) {
            try {
                this.game.getPlayer().upgradeBuilding(this.game.getPlayer().
                        getCorrespondingMilitaryBuilding("ArcheryRange",this.game.getPlayer().getCorrespondingCity("Cairo")));
                switch (this.game.getPlayer().getCorrespondingMilitaryBuilding("ArcheryRange",this.game.getPlayer().
                        getCorrespondingCity("Cairo")).getLevel()) {
                    case 2:
                        buildingButtonsCairo.getArcheryBuildingUpgrade().setToolTipText("Upgrade : ↗ Level 3  700");
                        buildingButtonsCairo.getArcheryBuildingRecruit().setToolTipText("Recruitment Cost : 450");
                        break;
                    case 3:
                        buildingButtonsCairo.getArcheryBuildingUpgrade().setToolTipText(null);
                        buildingButtonsCairo.getArcheryBuildingRecruit().setToolTipText("Recruitment Cost : 500");
                        break;
                }
                playSound("Cash Register (Kaching) - Sound Effect (HD).wav");
                updatePlayerLabel();
            }
            catch (NotEnoughGoldException | BuildingInCoolDownException | MaxLevelException ex) {
                JOptionPane.showMessageDialog(null,ex.getMessage(),"Action Denied",JOptionPane.WARNING_MESSAGE);
            }
        }
        else if (e.getSource()==buildingButtonsCairo.getBarracksBuildingUpgrade()) {
            try {
                this.game.getPlayer().upgradeBuilding(this.game.getPlayer().
                        getCorrespondingMilitaryBuilding("Barracks",this.game.getPlayer().getCorrespondingCity("Cairo")));
                switch (this.game.getPlayer().getCorrespondingMilitaryBuilding("Barracks",this.game.getPlayer().
                        getCorrespondingCity("Cairo")).getLevel()) {
                    case 2:
                        buildingButtonsCairo.getBarracksBuildingUpgrade().setToolTipText("Upgrade : ↗ Level 3  1500");
                        buildingButtonsCairo.getBarracksBuildingRecruit().setToolTipText("Recruitment Cost : 550");
                        break;
                    case 3:
                        buildingButtonsCairo.getBarracksBuildingUpgrade().setToolTipText(null);
                        buildingButtonsCairo.getBarracksBuildingRecruit().setToolTipText("Recruitment Cost : 600");
                        break;
                }
                playSound("Cash Register (Kaching) - Sound Effect (HD).wav");
                updatePlayerLabel();
            }
            catch (NotEnoughGoldException | BuildingInCoolDownException | MaxLevelException ex) {
                JOptionPane.showMessageDialog(null,ex.getMessage(),"Action Denied",JOptionPane.WARNING_MESSAGE);
            }
        }
        else if (e.getSource()==buildingButtonsCairo.getStableBuildingUpgrade()) {
            try {
                this.game.getPlayer().upgradeBuilding(this.game.getPlayer().
                        getCorrespondingMilitaryBuilding("Stable",this.game.getPlayer().getCorrespondingCity("Cairo")));
                switch (this.game.getPlayer().getCorrespondingMilitaryBuilding("Stable",this.game.getPlayer().
                        getCorrespondingCity("Cairo")).getLevel()) {
                    case 2:
                        buildingButtonsCairo.getStableBuildingUpgrade().setToolTipText("Upgrade : ↗ Level 3  2000");
                        buildingButtonsCairo.getStableBuildingRecruit().setToolTipText("Recruitment Cost : 650");
                        break;
                    case 3:
                        buildingButtonsCairo.getStableBuildingUpgrade().setToolTipText(null);
                        buildingButtonsCairo.getStableBuildingRecruit().setToolTipText("Recruitment Cost : 700");
                        break;
                }
                playSound("Cash Register (Kaching) - Sound Effect (HD).wav");
                updatePlayerLabel();
            }
            catch (NotEnoughGoldException | BuildingInCoolDownException | MaxLevelException ex) {
                JOptionPane.showMessageDialog(null,ex.getMessage(),"Action Denied",JOptionPane.WARNING_MESSAGE);
            }
        }
        else if (e.getSource()==buildingButtonsCairo.getFarmUpgrade()) {
            try {
                this.game.getPlayer().upgradeBuilding(this.game.getPlayer().
                        getCorrespondingEconomicBuilding(this.game.getPlayer().getCorrespondingCity("Cairo"),"Farm"));
                buildingButtonsCairo.getFarmUpgrade().setToolTipText("Upgrade : ↗ Level 3  700");
                playSound("Cash Register (Kaching) - Sound Effect (HD).wav");
                updatePlayerLabel();
            }
            catch (NotEnoughGoldException | BuildingInCoolDownException | MaxLevelException ex) {
                JOptionPane.showMessageDialog(null,ex.getMessage(),"Action Denied",JOptionPane.WARNING_MESSAGE);
            }
        }
        else if (e.getSource()==buildingButtonsCairo.getMarketUpgrade()) {
            try {
                this.game.getPlayer().upgradeBuilding(this.game.getPlayer().
                        getCorrespondingEconomicBuilding(this.game.getPlayer().getCorrespondingCity("Cairo"),"Market"));
                buildingButtonsCairo.getMarketUpgrade().setToolTipText("Upgrade : ↗ Level 3  1000");
                playSound("Cash Register (Kaching) - Sound Effect (HD).wav");
                updatePlayerLabel();
            }
            catch (NotEnoughGoldException | BuildingInCoolDownException | MaxLevelException ex) {
                JOptionPane.showMessageDialog(null,ex.getMessage(),"Action Denied",JOptionPane.WARNING_MESSAGE);
            }
        }
        else if (e.getSource()==buildingButtonsCairo.getArcheryBuildingRecruit()) {
            try {
                Unit recruitedUnit = this.game.getPlayer().recruitUnit("Archer","Cairo");
                this.launchPage.getWorldMap().getCairoFrame().getDefendingArmyCairo().addToDefending(recruitedUnit);
                this.addUnitButtonsActionListeners();
                updatePlayerLabel();
            }
            catch (BuildingInCoolDownException | MaxRecruitedException | NotEnoughGoldException ex) {
                JOptionPane.showMessageDialog(null,ex.getMessage(),"Action Denied",JOptionPane.WARNING_MESSAGE);
            }

        }

        else if (e.getSource()==buildingButtonsCairo.getBarracksBuildingRecruit()) {
            try {
                Unit recruitedUnit = this.game.getPlayer().recruitUnit("Infantry","Cairo");

                this.launchPage.getWorldMap().getCairoFrame().getDefendingArmyCairo().addToDefending(recruitedUnit);
                this.addUnitButtonsActionListeners();
                updatePlayerLabel();
            }
            catch (BuildingInCoolDownException | MaxRecruitedException | NotEnoughGoldException ex) {
                JOptionPane.showMessageDialog(null,ex.getMessage(),"Action Denied",JOptionPane.WARNING_MESSAGE);
            }
        }

        else if (e.getSource()==buildingButtonsCairo.getStableBuildingRecruit()) {
            try {
                Unit recruitedUnit = this.game.getPlayer().recruitUnit("Cavalry","Cairo");

                this.launchPage.getWorldMap().getCairoFrame().getDefendingArmyCairo().addToDefending(recruitedUnit);
                this.addUnitButtonsActionListeners();
                updatePlayerLabel();
            }
            catch (BuildingInCoolDownException | MaxRecruitedException | NotEnoughGoldException ex) {
                JOptionPane.showMessageDialog(null,ex.getMessage(),"Action Denied",JOptionPane.WARNING_MESSAGE);
            }

        }
    }

    public void handleSpartaButtons(ActionEvent e) {
        BuildingButtons buildingButtonsSparta = this.launchPage.getWorldMap().getSpartaFrame().getSpartaTown().getButtons();
        if (e.getSource()==buildingButtonsSparta.getArcheryBuildingBuy()) {
            try {
                this.game.getPlayer().build("ArcheryRange","Sparta");
                buildingButtonsSparta.getArcheryBuildingBuy().setVisible(false);
                buildingButtonsSparta.getArcheryBuildingUpgrade().setVisible(true);
                buildingButtonsSparta.getArcheryBuildingRecruit().setVisible(true);
                playSound("Cash Register (Kaching) - Sound Effect (HD).wav");
                updatePlayerLabel();
            }
            catch (NotEnoughGoldException ex) {
                JOptionPane.showMessageDialog(null,ex.getMessage(),"Action Denied",JOptionPane.WARNING_MESSAGE);
            }
        }
        else if (e.getSource()==buildingButtonsSparta.getBarracksBuildingBuy()) {
            try {
                this.game.getPlayer().build("Barracks","Sparta");
                buildingButtonsSparta.getBarracksBuildingBuy().setVisible(false);
                buildingButtonsSparta.getBarracksBuildingUpgrade().setVisible(true);
                buildingButtonsSparta.getBarracksBuildingRecruit().setVisible(true);
                playSound("Cash Register (Kaching) - Sound Effect (HD).wav");
                updatePlayerLabel();
            }
            catch (NotEnoughGoldException ex) {
                JOptionPane.showMessageDialog(null,ex.getMessage(),"Action Denied",JOptionPane.WARNING_MESSAGE);
            }
        }

        else if (e.getSource()==buildingButtonsSparta.getStableBuildingBuy()) {
            try {
                this.game.getPlayer().build("Stable","Sparta");
                buildingButtonsSparta.getStableBuildingBuy().setVisible(false);
                buildingButtonsSparta.getStableBuildingUpgrade().setVisible(true);
                buildingButtonsSparta.getStableBuildingRecruit().setVisible(true);
                playSound("Cash Register (Kaching) - Sound Effect (HD).wav");
                updatePlayerLabel();
            }
            catch (NotEnoughGoldException ex) {
                JOptionPane.showMessageDialog(null,"Not Enough Gold to build","Action Denied",JOptionPane.WARNING_MESSAGE);
            }
        }
        else if (e.getSource()==buildingButtonsSparta.getMarketBuy()) {
            try {
                this.game.getPlayer().build("Market","Sparta");
                buildingButtonsSparta.getMarketBuy().setVisible(false);
                buildingButtonsSparta.getMarketUpgrade().setVisible(true);
                playSound("Cash Register (Kaching) - Sound Effect (HD).wav");
                updatePlayerLabel();
            }
            catch (NotEnoughGoldException ex) {
                JOptionPane.showMessageDialog(null,"Not Enough Gold to build","Action Denied",JOptionPane.WARNING_MESSAGE);
            }
        }
        else if (e.getSource()==buildingButtonsSparta.getFarmBuy()) {
            try {
                this.game.getPlayer().build("Farm","Sparta");
                buildingButtonsSparta.getFarmBuy().setVisible(false);
                buildingButtonsSparta.getFarmUpgrade().setVisible(true);
                playSound("Cash Register (Kaching) - Sound Effect (HD).wav");
                updatePlayerLabel();
            }
            catch (NotEnoughGoldException ex) {
                JOptionPane.showMessageDialog(null,"Not Enough Gold to build","Action Denied",JOptionPane.WARNING_MESSAGE);
            }
        }
        else if (e.getSource()==buildingButtonsSparta.getArcheryBuildingUpgrade()) {
            try {
                this.game.getPlayer().upgradeBuilding(this.game.getPlayer().
                        getCorrespondingMilitaryBuilding("ArcheryRange",this.game.getPlayer().getCorrespondingCity("Sparta")));
                switch (this.game.getPlayer().getCorrespondingMilitaryBuilding("ArcheryRange",this.game.getPlayer().
                        getCorrespondingCity("Sparta")).getLevel()) {
                    case 2:
                        buildingButtonsSparta.getArcheryBuildingUpgrade().setToolTipText("Upgrade : ↗ Level 3  700");
                        buildingButtonsSparta.getArcheryBuildingRecruit().setToolTipText("Recruitment Cost : 450");
                        break;
                    case 3:
                        buildingButtonsSparta.getArcheryBuildingUpgrade().setToolTipText(null);
                        buildingButtonsSparta.getArcheryBuildingRecruit().setToolTipText("Recruitment Cost : 500");
                        break;
                }
                playSound("Cash Register (Kaching) - Sound Effect (HD).wav");
                updatePlayerLabel();
            }
            catch (NotEnoughGoldException | BuildingInCoolDownException | MaxLevelException ex) {
                JOptionPane.showMessageDialog(null,ex.getMessage(),"Action Denied",JOptionPane.WARNING_MESSAGE);
            }
        }
        else if (e.getSource()==buildingButtonsSparta.getBarracksBuildingUpgrade()) {
            try {
                this.game.getPlayer().upgradeBuilding(this.game.getPlayer().
                        getCorrespondingMilitaryBuilding("Barracks",this.game.getPlayer().getCorrespondingCity("Sparta")));
                switch (this.game.getPlayer().getCorrespondingMilitaryBuilding("Barracks",this.game.getPlayer().
                        getCorrespondingCity("Sparta")).getLevel()) {
                    case 2:
                        buildingButtonsSparta.getBarracksBuildingUpgrade().setToolTipText("Upgrade : ↗ Level 3  1500");
                        buildingButtonsSparta.getBarracksBuildingRecruit().setToolTipText("Recruitment Cost : 550");
                        break;
                    case 3:
                        buildingButtonsSparta.getBarracksBuildingUpgrade().setToolTipText(null);
                        buildingButtonsSparta.getBarracksBuildingRecruit().setToolTipText("Recruitment Cost : 600");
                        break;
                }
                playSound("Cash Register (Kaching) - Sound Effect (HD).wav");
                updatePlayerLabel();
            }
            catch (NotEnoughGoldException | BuildingInCoolDownException | MaxLevelException ex) {
                JOptionPane.showMessageDialog(null,ex.getMessage(),"Action Denied",JOptionPane.WARNING_MESSAGE);
            }
        }
        else if (e.getSource()==buildingButtonsSparta.getStableBuildingUpgrade()) {
            try {
                this.game.getPlayer().upgradeBuilding(this.game.getPlayer().
                        getCorrespondingMilitaryBuilding("Stable",this.game.getPlayer().getCorrespondingCity("Sparta")));
                switch (this.game.getPlayer().getCorrespondingMilitaryBuilding("Stable",this.game.getPlayer().
                        getCorrespondingCity("Sparta")).getLevel()) {
                    case 2:
                        buildingButtonsSparta.getStableBuildingUpgrade().setToolTipText("Upgrade : ↗ Level 3  2000");
                        buildingButtonsSparta.getStableBuildingRecruit().setToolTipText("Recruitment Cost : 650");
                        break;
                    case 3:
                        buildingButtonsSparta.getStableBuildingUpgrade().setToolTipText(null);
                        buildingButtonsSparta.getStableBuildingRecruit().setToolTipText("Recruitment Cost : 700");
                        break;
                }
                playSound("Cash Register (Kaching) - Sound Effect (HD).wav");
                updatePlayerLabel();
            }
            catch (NotEnoughGoldException | BuildingInCoolDownException | MaxLevelException ex) {
                JOptionPane.showMessageDialog(null,ex.getMessage(),"Action Denied",JOptionPane.WARNING_MESSAGE);
            }
        }
        else if (e.getSource()==buildingButtonsSparta.getFarmUpgrade()) {
            try {
                this.game.getPlayer().upgradeBuilding(this.game.getPlayer().
                        getCorrespondingEconomicBuilding(this.game.getPlayer().getCorrespondingCity("Sparta"),"Farm"));
                buildingButtonsSparta.getFarmUpgrade().setToolTipText("Upgrade : ↗ Level 3  700");
                playSound("Cash Register (Kaching) - Sound Effect (HD).wav");
                updatePlayerLabel();
            }
            catch (NotEnoughGoldException | BuildingInCoolDownException | MaxLevelException ex) {
                JOptionPane.showMessageDialog(null,ex.getMessage(),"Action Denied",JOptionPane.WARNING_MESSAGE);
            }
        }
        else if (e.getSource()==buildingButtonsSparta.getMarketUpgrade()) {
            try {
                this.game.getPlayer().upgradeBuilding(this.game.getPlayer().
                        getCorrespondingEconomicBuilding(this.game.getPlayer().getCorrespondingCity("Sparta"),"Market"));
                buildingButtonsSparta.getMarketUpgrade().setToolTipText("Upgrade : ↗ Level 3  1000");
                playSound("Cash Register (Kaching) - Sound Effect (HD).wav");
                updatePlayerLabel();
            }
            catch (NotEnoughGoldException | BuildingInCoolDownException | MaxLevelException ex) {
                JOptionPane.showMessageDialog(null,ex.getMessage(),"Action Denied",JOptionPane.WARNING_MESSAGE);
            }
        }
        else if (e.getSource()==buildingButtonsSparta.getArcheryBuildingRecruit()) {
            try {
                Unit recruitedUnit = this.game.getPlayer().recruitUnit("Archer","Sparta");
                this.launchPage.getWorldMap().getSpartaFrame().getDefendingArmySparta().addToDefending(recruitedUnit);
                this.addUnitButtonsActionListeners();
                updatePlayerLabel();
            }
            catch (BuildingInCoolDownException | MaxRecruitedException | NotEnoughGoldException ex) {
                JOptionPane.showMessageDialog(null,ex.getMessage(),"Action Denied",JOptionPane.WARNING_MESSAGE);
            }
        }

        else if (e.getSource()==buildingButtonsSparta.getBarracksBuildingRecruit()) {
            try {
                Unit recruitedUnit = this.game.getPlayer().recruitUnit("Infantry","Sparta");
                this.launchPage.getWorldMap().getSpartaFrame().getDefendingArmySparta().addToDefending(recruitedUnit);
                this.addUnitButtonsActionListeners();
                updatePlayerLabel();
            }
            catch (BuildingInCoolDownException | MaxRecruitedException | NotEnoughGoldException ex) {
                JOptionPane.showMessageDialog(null,ex.getMessage(),"Action Denied",JOptionPane.WARNING_MESSAGE);
            }
        }

        else if (e.getSource()==buildingButtonsSparta.getStableBuildingRecruit()) {
            try {
                Unit recruitedUnit = this.game.getPlayer().recruitUnit("Cavalry","Sparta");
                this.launchPage.getWorldMap().getSpartaFrame().getDefendingArmySparta().addToDefending(recruitedUnit);
                this.addUnitButtonsActionListeners();
                updatePlayerLabel();
            }
            catch (BuildingInCoolDownException | MaxRecruitedException | NotEnoughGoldException ex) {
                JOptionPane.showMessageDialog(null,ex.getMessage(),"Action Denied",JOptionPane.WARNING_MESSAGE);
            }
        }
    }

    public void handleRomeButtons(ActionEvent e) {
        BuildingButtons buildingButtonsRome = this.launchPage.getWorldMap().getRomeFrame().getRomeTown().getButtons();
        if (e.getSource()==buildingButtonsRome.getArcheryBuildingBuy()) {
            try {
                this.game.getPlayer().build("ArcheryRange","Rome");
                buildingButtonsRome.getArcheryBuildingBuy().setVisible(false);
                buildingButtonsRome.getArcheryBuildingUpgrade().setVisible(true);
                buildingButtonsRome.getArcheryBuildingRecruit().setVisible(true);
                playSound("Cash Register (Kaching) - Sound Effect (HD).wav");
                updatePlayerLabel();
            }
            catch (NotEnoughGoldException ex) {
                JOptionPane.showMessageDialog(null,ex.getMessage(),"Action Denied",JOptionPane.WARNING_MESSAGE);
            }
        }
        else if (e.getSource()==buildingButtonsRome.getBarracksBuildingBuy()) {
            try {
                this.game.getPlayer().build("Barracks","Rome");
                buildingButtonsRome.getBarracksBuildingBuy().setVisible(false);
                buildingButtonsRome.getBarracksBuildingUpgrade().setVisible(true);
                buildingButtonsRome.getBarracksBuildingRecruit().setVisible(true);
                playSound("Cash Register (Kaching) - Sound Effect (HD).wav");
                updatePlayerLabel();
            }
            catch (NotEnoughGoldException ex) {
                JOptionPane.showMessageDialog(null,ex.getMessage(),"Action Denied",JOptionPane.WARNING_MESSAGE);
            }
        }

        else if (e.getSource()==buildingButtonsRome.getStableBuildingBuy()) {
            try {
                this.game.getPlayer().build("Stable","Rome");
                buildingButtonsRome.getStableBuildingBuy().setVisible(false);
                buildingButtonsRome.getStableBuildingUpgrade().setVisible(true);
                buildingButtonsRome.getStableBuildingRecruit().setVisible(true);
                playSound("Cash Register (Kaching) - Sound Effect (HD).wav");
                updatePlayerLabel();
            }
            catch (NotEnoughGoldException ex) {
                JOptionPane.showMessageDialog(null,"Not Enough Gold to build","Action Denied",JOptionPane.WARNING_MESSAGE);
            }
        }
        else if (e.getSource()==buildingButtonsRome.getMarketBuy()) {
            try {
                this.game.getPlayer().build("Market","Rome");
                buildingButtonsRome.getMarketBuy().setVisible(false);
                buildingButtonsRome.getMarketUpgrade().setVisible(true);
                playSound("Cash Register (Kaching) - Sound Effect (HD).wav");
                updatePlayerLabel();
            }
            catch (NotEnoughGoldException ex) {
                JOptionPane.showMessageDialog(null,"Not Enough Gold to build","Action Denied",JOptionPane.WARNING_MESSAGE);
            }
        }
        else if (e.getSource()==buildingButtonsRome.getFarmBuy()) {
            try {
                this.game.getPlayer().build("Farm","Rome");
                buildingButtonsRome.getFarmBuy().setVisible(false);
                buildingButtonsRome.getFarmUpgrade().setVisible(true);
                playSound("Cash Register (Kaching) - Sound Effect (HD).wav");
                updatePlayerLabel();
            }
            catch (NotEnoughGoldException ex) {
                JOptionPane.showMessageDialog(null,"Not Enough Gold to build","Action Denied",JOptionPane.WARNING_MESSAGE);
            }
        }
        else if (e.getSource()==buildingButtonsRome.getArcheryBuildingUpgrade()) {
            try {
                this.game.getPlayer().upgradeBuilding(this.game.getPlayer().
                        getCorrespondingMilitaryBuilding("ArcheryRange",this.game.getPlayer().getCorrespondingCity("Rome")));
                switch (this.game.getPlayer().getCorrespondingMilitaryBuilding("ArcheryRange",this.game.getPlayer().
                        getCorrespondingCity("Rome")).getLevel()) {
                    case 2:
                        buildingButtonsRome.getArcheryBuildingUpgrade().setToolTipText("Upgrade : ↗ Level 3  700");
                        buildingButtonsRome.getArcheryBuildingRecruit().setToolTipText("Recruitment Cost : 450");
                        break;
                    case 3:
                        buildingButtonsRome.getArcheryBuildingUpgrade().setToolTipText(null);
                        buildingButtonsRome.getArcheryBuildingRecruit().setToolTipText("Recruitment Cost : 500");
                        break;
                }
                playSound("Cash Register (Kaching) - Sound Effect (HD).wav");
                updatePlayerLabel();
            }
            catch (NotEnoughGoldException | BuildingInCoolDownException | MaxLevelException ex) {
                JOptionPane.showMessageDialog(null,ex.getMessage(),"Action Denied",JOptionPane.WARNING_MESSAGE);
            }
        }
        else if (e.getSource()==buildingButtonsRome.getBarracksBuildingUpgrade()) {
            try {
                this.game.getPlayer().upgradeBuilding(this.game.getPlayer().
                        getCorrespondingMilitaryBuilding("Barracks",this.game.getPlayer().getCorrespondingCity("Rome")));
                switch (this.game.getPlayer().getCorrespondingMilitaryBuilding("Barracks",this.game.getPlayer().
                        getCorrespondingCity("Rome")).getLevel()) {
                    case 2:
                        buildingButtonsRome.getBarracksBuildingUpgrade().setToolTipText("Upgrade : ↗ Level 3  1500");
                        buildingButtonsRome.getBarracksBuildingRecruit().setToolTipText("Recruitment Cost : 550");
                        break;
                    case 3:
                        buildingButtonsRome.getBarracksBuildingUpgrade().setToolTipText(null);
                        buildingButtonsRome.getBarracksBuildingRecruit().setToolTipText("Recruitment Cost : 600");
                        break;
                }
                playSound("Cash Register (Kaching) - Sound Effect (HD).wav");
                updatePlayerLabel();
            }
            catch (NotEnoughGoldException | BuildingInCoolDownException | MaxLevelException ex) {
                JOptionPane.showMessageDialog(null,ex.getMessage(),"Action Denied",JOptionPane.WARNING_MESSAGE);
            }
        }
        else if (e.getSource()==buildingButtonsRome.getStableBuildingUpgrade()) {
            try {
                this.game.getPlayer().upgradeBuilding(this.game.getPlayer().
                        getCorrespondingMilitaryBuilding("Stable",this.game.getPlayer().getCorrespondingCity("Rome")));
                switch (this.game.getPlayer().getCorrespondingMilitaryBuilding("Stable",this.game.getPlayer().
                        getCorrespondingCity("Rome")).getLevel()) {
                    case 2:
                        buildingButtonsRome.getStableBuildingUpgrade().setToolTipText("Upgrade : ↗ Level 3  2000");
                        buildingButtonsRome.getStableBuildingRecruit().setToolTipText("Recruitment Cost : 650");
                        break;
                    case 3:
                        buildingButtonsRome.getStableBuildingUpgrade().setToolTipText(null);
                        buildingButtonsRome.getStableBuildingRecruit().setToolTipText("Recruitment Cost : 700");
                        break;
                }
                playSound("Cash Register (Kaching) - Sound Effect (HD).wav");
                updatePlayerLabel();
            }
            catch (NotEnoughGoldException | BuildingInCoolDownException | MaxLevelException ex) {
                JOptionPane.showMessageDialog(null,ex.getMessage(),"Action Denied",JOptionPane.WARNING_MESSAGE);
            }
        }
        else if (e.getSource()==buildingButtonsRome.getFarmUpgrade()) {
            try {
                this.game.getPlayer().upgradeBuilding(this.game.getPlayer().
                        getCorrespondingEconomicBuilding(this.game.getPlayer().getCorrespondingCity("Rome"),"Farm"));
                buildingButtonsRome.getFarmUpgrade().setToolTipText("Upgrade : ↗ Level 3  700");
                playSound("Cash Register (Kaching) - Sound Effect (HD).wav");
                updatePlayerLabel();
            }
            catch (NotEnoughGoldException | BuildingInCoolDownException | MaxLevelException ex) {
                JOptionPane.showMessageDialog(null,ex.getMessage(),"Action Denied",JOptionPane.WARNING_MESSAGE);
            }
        }
        else if (e.getSource()==buildingButtonsRome.getMarketUpgrade()) {
            try {
                this.game.getPlayer().upgradeBuilding(this.game.getPlayer().
                        getCorrespondingEconomicBuilding(this.game.getPlayer().getCorrespondingCity("Rome"),"Market"));
                buildingButtonsRome.getMarketUpgrade().setToolTipText("Upgrade : ↗ Level 3  1000");
                playSound("Cash Register (Kaching) - Sound Effect (HD).wav");
                updatePlayerLabel();
            }
            catch (NotEnoughGoldException | BuildingInCoolDownException | MaxLevelException ex) {
                JOptionPane.showMessageDialog(null,ex.getMessage(),"Action Denied",JOptionPane.WARNING_MESSAGE);
            }
        }
        else if (e.getSource()==buildingButtonsRome.getArcheryBuildingRecruit()) {
            try {
                Unit recruitedUnit = this.game.getPlayer().recruitUnit("Archer","Rome");
                this.launchPage.getWorldMap().getRomeFrame().getDefendingArmyRome().addToDefending(recruitedUnit);
                this.addUnitButtonsActionListeners();
                updatePlayerLabel();
            }
            catch (BuildingInCoolDownException | MaxRecruitedException | NotEnoughGoldException ex) {
                JOptionPane.showMessageDialog(null,ex.getMessage(),"Action Denied",JOptionPane.WARNING_MESSAGE);
            }
        }

        else if (e.getSource()==buildingButtonsRome.getBarracksBuildingRecruit()) {
            try {
                Unit recruitedUnit = this.game.getPlayer().recruitUnit("Infantry","Rome");
                this.launchPage.getWorldMap().getRomeFrame().getDefendingArmyRome().addToDefending(recruitedUnit);
                this.addUnitButtonsActionListeners();
                updatePlayerLabel();
            }
            catch (BuildingInCoolDownException | MaxRecruitedException | NotEnoughGoldException ex) {
                JOptionPane.showMessageDialog(null,ex.getMessage(),"Action Denied",JOptionPane.WARNING_MESSAGE);
            }
        }

        else if (e.getSource()==buildingButtonsRome.getStableBuildingRecruit()) {
            try {
                Unit recruitedUnit = this.game.getPlayer().recruitUnit("Cavalry","Rome");
                this.launchPage.getWorldMap().getRomeFrame().getDefendingArmyRome().addToDefending(recruitedUnit);
                this.addUnitButtonsActionListeners();
                updatePlayerLabel();
            }
            catch (BuildingInCoolDownException | MaxRecruitedException | NotEnoughGoldException ex) {
                JOptionPane.showMessageDialog(null,ex.getMessage(),"Action Denied",JOptionPane.WARNING_MESSAGE);
            }
        }
    }

    public void resetTargetButtons() {
        for (ControlledArmiesUnitsFrame frame : this.launchPage.getWorldMap().getControlledArmiesFrame().getControlledArmiesUnitsFrame()) {
            frame.resetTargetButtons();
        }
    }

    public void setControlledArmiesUnitFramesMap() {
        if (enemyUnitButtonsMap == null)
            return;
        for (ControlledArmiesUnitsFrame frame : this.launchPage.getWorldMap().getMarchingArmiesFrame().getControlledArmiesUnitsFrame())
            frame.setEnemyUnitButtonsMap(enemyUnitButtonsMap);

        for (ControlledArmiesUnitsFrame frame : this.launchPage.getWorldMap().getBesiegingArmiesFrame().getControlledArmiesUnitsFrame())
            frame.setEnemyUnitButtonsMap(enemyUnitButtonsMap);
    }

    public void handleInitiateArmyButton(Unit unit, ActionEvent e) {
        if (e.getSource()==this.launchPage.getWorldMap().getCairoFrame().getDefendingArmyCairo().getInitiateArmyCairo()) {
            if (unit == null || !this.launchPage.getWorldMap().getCairoFrame().getDefendingArmyCairo().getDefendingArmyCairo().contains(unit))
                return;
            Army newArmy = this.game.getPlayer().initiateArmy(this.game.getPlayer().getCorrespondingCity("Cairo"),unit);
            this.launchPage.getWorldMap().getControlledArmiesFrame().initiateArmyOfControlFrame(newArmy,"Cairo");
            reEnableAllButtons();
            disableInitiateArmyButtons();
            this.launchPage.getWorldMap().getCairoFrame().getDefendingArmyCairo().RelocateToControl(unit);
            resetTargetButtons();
            this.launchPage.getWorldMap().getCairoFrame().getDefendingArmyCairo().reload();
            this.launchPage.getWorldMap().getControlledArmiesFrame().reload();
            this.launchPage.getWorldMap().getCairoFrame().getDefendingArmyCairo().getRelocateUnitCairo().setEnabled(true);
        }

        if (e.getSource()==this.launchPage.getWorldMap().getRomeFrame().getDefendingArmyRome().getInitiateArmyRome()) {
            if (unit == null || !this.launchPage.getWorldMap().getRomeFrame().getDefendingArmyRome().getDefendingArmyRome().contains(unit))
                return;
            Army newArmy = this.game.getPlayer().initiateArmy(this.game.getPlayer().getCorrespondingCity("Rome"),unit);
            this.launchPage.getWorldMap().getControlledArmiesFrame().initiateArmyOfControlFrame(newArmy,"Rome");
            reEnableAllButtons();
            disableInitiateArmyButtons();
            this.launchPage.getWorldMap().getRomeFrame().getDefendingArmyRome().RelocateToControl(unit);
            resetTargetButtons();
            this.launchPage.getWorldMap().getRomeFrame().getDefendingArmyRome().reload();
            this.launchPage.getWorldMap().getControlledArmiesFrame().reload();
            this.launchPage.getWorldMap().getRomeFrame().getDefendingArmyRome().getRelocateUnitRome().setEnabled(true);

        }

        if (e.getSource() == this.launchPage.getWorldMap().getSpartaFrame().getDefendingArmySparta().getInitiateArmySparta()) {
            if (unit == null || !this.launchPage.getWorldMap().getSpartaFrame().getDefendingArmySparta().getDefendingArmySparta().contains(unit))
                return;
            Army newArmy = this.game.getPlayer().initiateArmy(this.game.getPlayer().getCorrespondingCity("Sparta"),unit);
            this.launchPage.getWorldMap().getControlledArmiesFrame().initiateArmyOfControlFrame(newArmy,"Sparta");
            reEnableAllButtons();
            disableInitiateArmyButtons();
            this.launchPage.getWorldMap().getSpartaFrame().getDefendingArmySparta().RelocateToControl(unit);
            resetTargetButtons();
            this.launchPage.getWorldMap().getSpartaFrame().getDefendingArmySparta().reload();
            this.launchPage.getWorldMap().getControlledArmiesFrame().reload();
            this.launchPage.getWorldMap().getSpartaFrame().getDefendingArmySparta().getRelocateUnitSparta().setEnabled(true);
        }
        this.addArmyButtonsActionListeners();
    }

    public void handleLaySiege(ActionEvent e) {

        for (ControlledArmiesUnitsFrame frame : this.launchPage.getWorldMap().getMarchingArmiesFrame().getControlledArmiesUnitsFrame()) {
            if (e.getSource() == frame.getLaySiege()) {
                try {
                    this.game.getPlayer().laySiege(selectedBesiegingArmy,this.game.getCorrespondingAvailableCity(selectedBesiegingArmy.getTarget().toLowerCase()));//added
                    this.launchPage.getWorldMap().getMarchingArmiesFrame().sendToBesiegingFrame(selectedBesiegingArmy);
                    ArmyButton button = this.launchPage.getWorldMap().getBesiegingArmiesFrame().addtoBesiegingArmyFrame(selectedBesiegingArmy, selectedBesiegingArmy.getCurrentLocation().toLowerCase());//5aleeha currentlocation
                    //this.launchPage.getWorldMap().ge().getControlledArmiesUnitsFrame().get(getIndexOfSelectedBesiegingArmy).updateToBesiegingFrame();
                    this.launchPage.getWorldMap().getBesiegingArmiesFrame().getControlledArmiesUnitsFrame().get(this.launchPage.getWorldMap().getBesiegingArmiesFrame().getControlledArmiesUnitsFrame().size()-1).updateToBesiegingFrame();

                    getIndexOfSelectedBesiegingArmy = this.launchPage.getWorldMap().getBesiegingArmiesFrame()
                            .getControlledArmiesOfAll().indexOf(selectedBesiegingArmy);
                    if (getIndexOfSelectedBesiegingArmy == -1)
                        return;

                    this.launchPage.getWorldMap().getBesiegingArmiesFrame().getControlledArmiesUnitsFrame().get(getIndexOfSelectedBesiegingArmy).setTargetCityName(selectedBesiegingArmy.getCurrentLocation().toLowerCase());
                    this.launchPage.getWorldMap().getBesiegingArmiesFrame().getControlledArmiesUnitsFrame().get(getIndexOfSelectedBesiegingArmy).getTargetCityNameLabel().setText(selectedBesiegingArmy.getCurrentLocation().toLowerCase());
                    this.launchPage.getWorldMap().getBesiegingArmiesFrame().getControlledArmiesUnitsFrame().get(getIndexOfSelectedBesiegingArmy).getTargetCityNameLabel().setVisible(true);
                    if (button == null)
                        return;
                    button.getArmyInfoLabel().getStatusLabel().setVisible(true);
                    button.getArmyInfoLabel().getArmyNumberLabel().setText("Army Number :"+button.getArmyInfoLabel().getArmyNumber()+1);
                    this.launchPage.getWorldMap().getBesiegingArmiesFrame().getControlledArmiesUnitsFrame().get(getIndexOfSelectedBesiegingArmy).getAutoResolve().addActionListener(this);

                    //  frame.getTurnsUnderSiegeLabel().setText("Turns Under Siege : " + this.game.getCorrespondingAvailableCity(selectedBesiegingArmy.getTarget()).getTurnsUnderSiege());
                    button.getArmyInfoLabel().getStatusLabel().setText("Status :" + Status.BESIEGING);
                    this.launchPage.getWorldMap().getBesiegingArmiesFrame().getControlledArmiesUnitsFrame().get(getIndexOfSelectedBesiegingArmy).updateToBesiegingFrame();
//                    this.launchPage.getWorldMap().getBesiegingArmiesFrame().getControlledArmiesUnitsFrame().get(getIndexOfSelectedBesiegingArmy).setEnemyUnitButtonsMap(enemyUnitButtonsMap);

                    //setControlledArmiesUnitFramesMap();
                    this.launchPage.getWorldMap().getBesiegingArmiesFrame().getControlledArmiesUnitsFrame().get(getIndexOfSelectedBesiegingArmy).setEnemyUnitButtonsMap(enemyUnitButtonsMap);

                    this.launchPage.getWorldMap().getMarchingArmiesFrame().reload();
                    this.launchPage.getWorldMap().getBesiegingArmiesFrame().reload();
                    this.launchPage.getWorldMap().getControlledArmiesFrame().reload();
                    this.launchPage.getWorldMap().reload();
                    break;
                }
                catch (TargetNotReachedException | FriendlyCityException ex) {
                    JOptionPane.showMessageDialog(null,ex.getMessage(),"Action Denied",JOptionPane.WARNING_MESSAGE);

                }


                selectedBesiegingArmy=null;
                getIndexOfSelectedBesiegingArmy=-1;

            }
        }


    }

    public void handleTargetCity(ActionEvent e){
        if (indexOfSelectedMarchingArmy >= this.launchPage.getWorldMap().getControlledArmiesFrame().getControlledArmiesUnitsFrame().size())
            return;

        if (e.getSource() == this.launchPage.getWorldMap().getControlledArmiesFrame().getControlledArmiesUnitsFrame().get(indexOfSelectedMarchingArmy).getAttackRomeButton()) {
            this.game.targetCity(selectedMarchingArmy,"rome");

            this.launchPage.getWorldMap().getControlledArmiesFrame().sendToMarchingFrame(selectedMarchingArmy);
            ArmyButton button = this.launchPage.getWorldMap().getMarchingArmiesFrame().addtoMarchingArmyFrame(selectedMarchingArmy,"onRoad");
            this.launchPage.getWorldMap().getControlledArmiesFrame().getControlledArmiesUnitsFrame().get(indexOfSelectedMarchingArmy).dispose();
            indexOfSelectedMarchingArmy= this.launchPage.getWorldMap().getMarchingArmiesFrame().getControlledArmiesOfAll().indexOf(selectedMarchingArmy);//added
            if (indexOfSelectedMarchingArmy == -1)
                return;
            this.launchPage.getWorldMap().getMarchingArmiesFrame().getControlledArmiesUnitsFrame().get(indexOfSelectedMarchingArmy).setTargetCityName("rome");
            this.launchPage.getWorldMap().getMarchingArmiesFrame().getControlledArmiesUnitsFrame().get(indexOfSelectedMarchingArmy).setDistanceLeft(selectedMarchingArmy.getDistancetoTarget());
            if (button == null)
                return;
            button.getArmyInfoLabel().getTargetCityLabel().setVisible(true);
            button.getArmyInfoLabel().getDistanceToTargetLabel().setVisible(true);
            button.getArmyInfoLabel().getStatusLabel().setVisible(true);
            button.getArmyInfoLabel().getTargetCityLabel().setText("Target : Rome");

            button.getArmyInfoLabel().getDistanceToTargetLabel().setText("DistanceToTarget : " + selectedMarchingArmy.getDistancetoTarget());
            button.getArmyInfoLabel().getStatusLabel().setText("Status : " + Status.MARCHING);//added
            this.launchPage.getWorldMap().getMarchingArmiesFrame().getControlledArmiesUnitsFrame().get(indexOfSelectedMarchingArmy).updateToMarchingFrame();
            //     this.launchPage.getWorldMap().getControlledArmiesFrame().getControlledArmiesUnitsFrame().get(indexOfSelectedMarchingArmy).resetRightPanel();
            this.launchPage.getWorldMap().getMarchingArmiesFrame().reload();
            this.launchPage.getWorldMap().getControlledArmiesFrame().reload();
            this.launchPage.getWorldMap().reload();
        }

        if (e.getSource() == this.launchPage.getWorldMap().getControlledArmiesFrame().getControlledArmiesUnitsFrame().get(indexOfSelectedMarchingArmy).getAttackSpartaButton()) {
            this.game.targetCity(selectedMarchingArmy,"sparta");

            this.launchPage.getWorldMap().getControlledArmiesFrame().sendToMarchingFrame(selectedMarchingArmy);
            ArmyButton button = this.launchPage.getWorldMap().getMarchingArmiesFrame().addtoMarchingArmyFrame(selectedMarchingArmy,"onRoad");
            this.launchPage.getWorldMap().getControlledArmiesFrame().getControlledArmiesUnitsFrame().get(indexOfSelectedMarchingArmy).dispose();
            indexOfSelectedMarchingArmy= this.launchPage.getWorldMap().getMarchingArmiesFrame().getControlledArmiesOfAll().indexOf(selectedMarchingArmy);//added
            if (indexOfSelectedMarchingArmy == -1)
                return;
            this.launchPage.getWorldMap().getMarchingArmiesFrame().getControlledArmiesUnitsFrame().get(indexOfSelectedMarchingArmy).setTargetCityName("sparta");
            this.launchPage.getWorldMap().getMarchingArmiesFrame().getControlledArmiesUnitsFrame().get(indexOfSelectedMarchingArmy).setDistanceLeft(selectedMarchingArmy.getDistancetoTarget());
            if (button == null)
                return;
            button.getArmyInfoLabel().getTargetCityLabel().setVisible(true);
            button.getArmyInfoLabel().getDistanceToTargetLabel().setVisible(true);
            button.getArmyInfoLabel().getStatusLabel().setVisible(true);
            button.getArmyInfoLabel().getTargetCityLabel().setText("Target : Sparta");
            button.getArmyInfoLabel().getDistanceToTargetLabel().setText("DistanceToTarget : " + selectedMarchingArmy.getDistancetoTarget());
            button.getArmyInfoLabel().getStatusLabel().setText("Status : " + Status.MARCHING);//added
            this.launchPage.getWorldMap().getMarchingArmiesFrame().getControlledArmiesUnitsFrame().get(indexOfSelectedMarchingArmy).updateToMarchingFrame();
            //this.launchPage.getWorldMap().getControlledArmiesFrame().getControlledArmiesUnitsFrame().get(indexOfSelectedMarchingArmy).resetRightPanel();
            this.launchPage.getWorldMap().getMarchingArmiesFrame().reload();
            this.launchPage.getWorldMap().getControlledArmiesFrame().reload();
            this.launchPage.getWorldMap().reload();
        }

        if (e.getSource() == this.launchPage.getWorldMap().getControlledArmiesFrame().getControlledArmiesUnitsFrame().get(indexOfSelectedMarchingArmy).getAttackCairoButton()) {
            this.game.targetCity(selectedMarchingArmy,"cairo");

            this.launchPage.getWorldMap().getControlledArmiesFrame().sendToMarchingFrame(selectedMarchingArmy);
            ArmyButton button = this.launchPage.getWorldMap().getMarchingArmiesFrame().addtoMarchingArmyFrame(selectedMarchingArmy,"onRoad");
            this.launchPage.getWorldMap().getControlledArmiesFrame().getControlledArmiesUnitsFrame().get(indexOfSelectedMarchingArmy).dispose();
            indexOfSelectedMarchingArmy= this.launchPage.getWorldMap().getMarchingArmiesFrame().getControlledArmiesOfAll().indexOf(selectedMarchingArmy);//added
            if (indexOfSelectedMarchingArmy == -1)
                return;
            this.launchPage.getWorldMap().getMarchingArmiesFrame().getControlledArmiesUnitsFrame().get(indexOfSelectedMarchingArmy).setTargetCityName("cairo");
            this.launchPage.getWorldMap().getMarchingArmiesFrame().getControlledArmiesUnitsFrame().get(indexOfSelectedMarchingArmy).setDistanceLeft(selectedMarchingArmy.getDistancetoTarget());
            if (button == null)
                return;
            button.getArmyInfoLabel().getTargetCityLabel().setVisible(true);
            button.getArmyInfoLabel().getDistanceToTargetLabel().setVisible(true);
            button.getArmyInfoLabel().getStatusLabel().setVisible(true);
            button.getArmyInfoLabel().getTargetCityLabel().setText("Target : Cairo");
            button.getArmyInfoLabel().getDistanceToTargetLabel().setText("DistanceToTarget : " + selectedMarchingArmy.getDistancetoTarget());
            button.getArmyInfoLabel().getStatusLabel().setText("Status : " + Status.MARCHING);//added
            this.launchPage.getWorldMap().getMarchingArmiesFrame().getControlledArmiesUnitsFrame().get(indexOfSelectedMarchingArmy).updateToMarchingFrame();
            //   this.launchPage.getWorldMap().getControlledArmiesFrame().getControlledArmiesUnitsFrame().get(indexOfSelectedMarchingArmy).resetRightPanel();
            this.launchPage.getWorldMap().getMarchingArmiesFrame().reload();
            this.launchPage.getWorldMap().getControlledArmiesFrame().reload();
            this.launchPage.getWorldMap().reload();

            selectedMarchingArmy=null;
            indexOfSelectedMarchingArmy=-1; }
        this.addAutoResolveActionListeners();
        this.setControlledArmiesUnitFramesMap();


    }

    public void disableCorrespondingCityButton() {
        if (selectedMarchingArmy.getCurrentLocation().equalsIgnoreCase("cairo")) {
            this.launchPage.getWorldMap().getControlledArmiesFrame().getControlledArmiesUnitsFrame().get(indexOfSelectedMarchingArmy).getAttackRomeButton().setEnabled(true);
            this.launchPage.getWorldMap().getControlledArmiesFrame().getControlledArmiesUnitsFrame().get(indexOfSelectedMarchingArmy).getAttackSpartaButton().setEnabled(true);
        }
        else if (selectedMarchingArmy.getCurrentLocation().equalsIgnoreCase("sparta")) {
            this.launchPage.getWorldMap().getControlledArmiesFrame().getControlledArmiesUnitsFrame().get(indexOfSelectedMarchingArmy).getAttackRomeButton().setEnabled(true);
            this.launchPage.getWorldMap().getControlledArmiesFrame().getControlledArmiesUnitsFrame().get(indexOfSelectedMarchingArmy).getAttackCairoButton().setEnabled(true);
        }
        else if (selectedMarchingArmy.getCurrentLocation().equalsIgnoreCase("rome")) {
            this.launchPage.getWorldMap().getControlledArmiesFrame().getControlledArmiesUnitsFrame().get(indexOfSelectedMarchingArmy).getAttackCairoButton().setEnabled(true);
            this.launchPage.getWorldMap().getControlledArmiesFrame().getControlledArmiesUnitsFrame().get(indexOfSelectedMarchingArmy).getAttackSpartaButton().setEnabled(true);
        }
    }

    public void handleRelocateUnitButton(Unit unit,ActionEvent e) { //TODO
        if (e.getSource() == this.launchPage.getWorldMap().getCairoFrame().getDefendingArmyCairo().getRelocateUnitCairo()) {
            try {
                if ( !this.launchPage.getWorldMap().getCairoFrame().getDefendingArmyCairo().getDefendingArmyCairo().contains(unit))
                    return;
                //.relocateUnit(unit);
                int index=-1;

                for (ControlledArmiesUnitsFrame controlledArmiesUnitsFrame: this.launchPage.getWorldMap().getControlledArmiesFrame().getControlledArmiesUnitsFrame()) {
                    int counter=0;
                    for (ArmyButton armyButton : this.launchPage.getWorldMap().getControlledArmiesFrame().getArmyButtons()) {
                        if (armyButton.getCityName().equalsIgnoreCase(unit.getParentArmy().getCurrentLocation())&& controlledArmiesUnitsFrame.getI()<9) {
                            index = counter;
                        }
                        counter++;
                    } }
                if (index==-1){
                    JOptionPane.showMessageDialog(null,"You cant Relocate","Action Denied",JOptionPane.WARNING_MESSAGE);
                    return;
                }
                this.launchPage.getWorldMap().getControlledArmiesFrame().getControlledArmiesOfAll().get(index).relocateUnit(unit);

                UnitButton unitButton = this.launchPage.getWorldMap().getCairoFrame().getDefendingArmyCairo().RelocateToControl(unit);
                this.launchPage.getWorldMap().getControlledArmiesFrame().relocateUnitToUnitFrame(unitButton,index);
            }
            catch (MaxCapacityException ex) {
                JOptionPane.showMessageDialog(null,ex.getMessage(),"Action Denied",JOptionPane.WARNING_MESSAGE);
                this.launchPage.getWorldMap().getCairoFrame().getDefendingArmyCairo().dispose();
                this.launchPage.getWorldMap().getCairoFrame().getDefendingArmyCairo().getRelocateUnitCairo().setEnabled(false);
            }
        }
        if (e.getSource() == this.launchPage.getWorldMap().getSpartaFrame().getDefendingArmySparta().getRelocateUnitSparta()) {
            try {
                if ( !this.launchPage.getWorldMap().getSpartaFrame().getDefendingArmySparta().getDefendingArmySparta().contains(unit))
                    return;

                int index=-1;
                for (ControlledArmiesUnitsFrame controlledArmiesUnitsFrame: this.launchPage.getWorldMap().getControlledArmiesFrame().getControlledArmiesUnitsFrame()) {
                    int counter=0;
                    for (ArmyButton armyButton : this.launchPage.getWorldMap().getControlledArmiesFrame().getArmyButtons()) {
                        if (armyButton.getCityName().equalsIgnoreCase(unit.getParentArmy().getCurrentLocation())&& controlledArmiesUnitsFrame.getI()<9) {
                            index = counter;
                        }
                        counter++;
                    }
                }
                if (index==-1){
                    JOptionPane.showMessageDialog(null,"You cant Relocate","Action Denied",JOptionPane.WARNING_MESSAGE);
                    return;
                }
                this.launchPage.getWorldMap().getControlledArmiesFrame().getControlledArmiesOfAll().get(index).relocateUnit(unit);

                UnitButton unitButton = this.launchPage.getWorldMap().getSpartaFrame().getDefendingArmySparta().RelocateToControl(unit);
                this.launchPage.getWorldMap().getControlledArmiesFrame().relocateUnitToUnitFrame(unitButton,index);
            }
            catch (MaxCapacityException ex) {
                JOptionPane.showMessageDialog(null,ex.getMessage(),"Action Denied",JOptionPane.WARNING_MESSAGE);
                this.launchPage.getWorldMap().getSpartaFrame().getDefendingArmySparta().getRelocateUnitSparta().setEnabled(false);
            }
        }
        if (e.getSource() == this.launchPage.getWorldMap().getRomeFrame().getDefendingArmyRome().getRelocateUnitRome()) {
            try {
                if ( !this.launchPage.getWorldMap().getRomeFrame().getDefendingArmyRome().getDefendingArmyRome().contains(unit))
                    return;

                //.relocateUnit(unit);
                int index=-1;
                for (ControlledArmiesUnitsFrame controlledArmiesUnitsFrame: this.launchPage.getWorldMap().getControlledArmiesFrame().getControlledArmiesUnitsFrame()) {
                    int counter=0;
                    for (ArmyButton armyButton : this.launchPage.getWorldMap().getControlledArmiesFrame().getArmyButtons()) {
                        if (armyButton.getCityName().equalsIgnoreCase(unit.getParentArmy().getCurrentLocation())&& controlledArmiesUnitsFrame.getI()<9) {
                            index = counter;
                        }
                        counter++;
                    }

                }
                if (index==-1){
                    JOptionPane.showMessageDialog(null,"You cant Relocate","Action Denied",JOptionPane.WARNING_MESSAGE);
                    return;
                }
                this.launchPage.getWorldMap().getControlledArmiesFrame().getControlledArmiesOfAll().get(index).relocateUnit(unit);

                UnitButton unitButton = this.launchPage.getWorldMap().getRomeFrame().getDefendingArmyRome().RelocateToControl(unit);
                this.launchPage.getWorldMap().getControlledArmiesFrame().relocateUnitToUnitFrame(unitButton,index);
            }
            catch (MaxCapacityException ex) {
                JOptionPane.showMessageDialog(null,ex.getMessage(),"Action Denied",JOptionPane.WARNING_MESSAGE);
                this.launchPage.getWorldMap().getRomeFrame().getDefendingArmyRome().getRelocateUnitRome().setEnabled(false);
            }
        }

    }

    public void disableRemainingButtons(UnitButton unitButton) {
        for (UnitButton button : this.launchPage.getWorldMap().getCairoFrame().getDefendingArmyCairo().getUnitButtonsCairo()) {
            if (!button.equals(unitButton))
                button.setEnabled(false);
        }

        for (UnitButton button : this.launchPage.getWorldMap().getRomeFrame().getDefendingArmyRome().getUnitButtonsRome()) {
            if (!button.equals(unitButton))
                button.setEnabled(false);
        }

        for (UnitButton button : this.launchPage.getWorldMap().getSpartaFrame().getDefendingArmySparta().getUnitButtonsSparta()) {
            if (!button.equals(unitButton))
                button.setEnabled(false);
        }
    }

    public void reEnableAllButtons() {
        for (UnitButton button : this.launchPage.getWorldMap().getCairoFrame().getDefendingArmyCairo().getUnitButtonsCairo())
            button.setEnabled(true);
        for (UnitButton button : this.launchPage.getWorldMap().getRomeFrame().getDefendingArmyRome().getUnitButtonsRome())
            button.setEnabled(true);
        for (UnitButton button : this.launchPage.getWorldMap().getSpartaFrame().getDefendingArmySparta().getUnitButtonsSparta())
            button.setEnabled(true);
    }

    public void disableInitiateArmyButtons() {
        this.launchPage.getWorldMap().getCairoFrame().getDefendingArmyCairo().getInitiateArmyCairo().setEnabled(false);
        this.launchPage.getWorldMap().getRomeFrame().getDefendingArmyRome().getInitiateArmyRome().setEnabled(false);
        this.launchPage.getWorldMap().getSpartaFrame().getDefendingArmySparta().getInitiateArmySparta().setEnabled(false);
    }

    public void handleEndTurnButton(ActionEvent e) {
        MyButton buttonMap = this.launchPage.getWorldMap().getPlayerLabel().getEndTurn();
        MyButton buttonCairo = this.launchPage.getWorldMap().getCairoFrame().getCairoTown().getPlayerLabel().getEndTurn();
        MyButton buttonSparta = this.launchPage.getWorldMap().getSpartaFrame().getSpartaTown().getPlayerLabel().getEndTurn();
        MyButton buttonRome = this.launchPage.getWorldMap().getRomeFrame().getRomeTown().getPlayerLabel().getEndTurn();
        if (e.getSource() == buttonMap || e.getSource() == buttonCairo || e.getSource() == buttonSparta || e.getSource() == buttonRome) {
            this.game.endTurn();
            handleGameOver();

            this.launchPage.getWorldMap().getMarchingArmiesFrame().updateDistance();
            for (ControlledArmiesUnitsFrame controlledArmiesUnitsFrame :this.launchPage.getWorldMap().getMarchingArmiesFrame().getControlledArmiesUnitsFrame()){
                controlledArmiesUnitsFrame.updateDistance();
                if ( this.autoResolveFlag) {
                    //   controlledArmiesUnitsFrame.setFlag(false);
                    this.autoResolveFlag = false;
                    int index = this.launchPage.getWorldMap().getMarchingArmiesFrame().getControlledArmiesUnitsFrame().indexOf(controlledArmiesUnitsFrame);
                    this.launchPage.getWorldMap().getMarchingArmiesFrame().deleteArmy(this.launchPage.getWorldMap().
                            getMarchingArmiesFrame().getControlledArmiesOfAll().get(index));
                }
            }
            for (ControlledArmiesUnitsFrame controlledArmiesUnitsFrame :this.launchPage.getWorldMap().getBesiegingArmiesFrame().getControlledArmiesUnitsFrame()){
                if (controlledArmiesUnitsFrame.isFlag() || this.autoResolveFlag) {
                    controlledArmiesUnitsFrame.setFlag(false);
                    this.autoResolveFlag = false;
                    int index = this.launchPage.getWorldMap().getBesiegingArmiesFrame().getControlledArmiesUnitsFrame().indexOf(controlledArmiesUnitsFrame);
                    this.launchPage.getWorldMap().getBesiegingArmiesFrame().deleteArmy(this.launchPage.getWorldMap().
                            getBesiegingArmiesFrame().getControlledArmiesOfAll().get(index));
                }
            }

            this.launchPage.getWorldMap().getControlledArmiesFrame().getFoodLabel().setText("Food : " +  this.game.getPlayer().getFood());
            this.launchPage.getWorldMap().getMarchingArmiesFrame().getFoodLabel().setText("Food : " + this.game.getPlayer().getFood());
            this.launchPage.getWorldMap().getBesiegingArmiesFrame().getFoodLabel().setText("Food : " + this.game.getPlayer().getFood());
            this.launchPage.getWorldMap().getCairoFrame().getDefendingArmyCairo().getFoodLabel().setText("Food : " + this.game.getPlayer().getFood());
            this.launchPage.getWorldMap().getRomeFrame().getDefendingArmyRome().getFoodLabel().setText("Food : " + this.game.getPlayer().getFood());
            this.launchPage.getWorldMap().getSpartaFrame().getDefendingArmySparta().getFoodLabel().setText("Food : " + this.game.getPlayer().getFood());
            this.launchPage.getWorldMap().getControlledArmiesFrame().getTreasuryLabel().setText("Treasury : " + this.game.getPlayer().getTreasury());
            this.launchPage.getWorldMap().getMarchingArmiesFrame().getTreasuryLabel().setText("Treasury : " + this.game.getPlayer().getTreasury());
            this.launchPage.getWorldMap().getBesiegingArmiesFrame().getTreasuryLabel().setText("Treasury : " + this.game.getPlayer().getTreasury());
            this.launchPage.getWorldMap().getSpartaFrame().getDefendingArmySparta().getTreasuryLabel().setText("Treasury : " + this.game.getPlayer().getTreasury());
            this.launchPage.getWorldMap().getCairoFrame().getDefendingArmyCairo().getTreasuryLabel().setText("Treasury : " + this.game.getPlayer().getTreasury());
            this.launchPage.getWorldMap().getRomeFrame().getDefendingArmyRome().getTreasuryLabel().setText("Treasury : " + this.game.getPlayer().getTreasury());
            updatePlayerLabel();
        }
    }

    public void handleGameOver() {
        if (this.game.isGameOver()) {
            ImageIcon imageIcon;
            if (this.game.isVictorious()) {
                imageIcon = new ImageIcon("victory.jpg");
                JOptionPane.showMessageDialog(null, "YOU ARE VICTORIOUS", "GAME OVER", JOptionPane.INFORMATION_MESSAGE, imageIcon);
            }
            else {
                imageIcon = new ImageIcon("defeat.jpg");
                JOptionPane.showMessageDialog(null, "YOU ARE DEFEATED", "GAME OVER", JOptionPane.INFORMATION_MESSAGE, imageIcon);
            }
            System.exit(0);
        }
    }

    public void updatePlayerLabel() {
        this.launchPage.getWorldMap().getPlayerLabel().getFoodLabel().setText("Food: " + this.game.getPlayer().getFood());
        this.launchPage.getWorldMap().getPlayerLabel().getTreasuryLabel().setText("Treasury: \uD83D\uDCB0 " + this.game.getPlayer().getTreasury());
        this.launchPage.getWorldMap().getPlayerLabel().getTurnslabel().setText(this.game.getCurrentTurnCount() + " Turns");
        this.launchPage.getWorldMap().getPlayerLabel().repaint();
        this.launchPage.getWorldMap().getPlayerLabel().revalidate();
        this.launchPage.getWorldMap().getCairoFrame().getCairoTown().getPlayerLabel().getTreasuryLabel().setText(
                "Treasury: \uD83D\uDCB0 "+this.game.getPlayer().getTreasury());
        this.launchPage.getWorldMap().getCairoFrame().getCairoTown().getPlayerLabel().getFoodLabel().setText(
                "Food: " + this.game.getPlayer().getFood()
        );
        this.launchPage.getWorldMap().getCairoFrame().getCairoTown().getPlayerLabel().getTurnslabel().setText(this.game.getCurrentTurnCount() + " Turns");
        this.launchPage.getWorldMap().getCairoFrame().getCairoTown().getPlayerLabel().repaint();
        this.launchPage.getWorldMap().getCairoFrame().getCairoTown().getPlayerLabel().revalidate();

        this.launchPage.getWorldMap().getRomeFrame().getRomeTown().getPlayerLabel().getTreasuryLabel().setText(
                "Treasury: \uD83D\uDCB0 "+this.game.getPlayer().getTreasury());
        this.launchPage.getWorldMap().getRomeFrame().getRomeTown().getPlayerLabel().getFoodLabel().setText(
                "Food: " + this.game.getPlayer().getFood()
        );
        this.launchPage.getWorldMap().getRomeFrame().getRomeTown().getPlayerLabel().getTurnslabel().setText(this.game.getCurrentTurnCount() + " Turns");
        this.launchPage.getWorldMap().getRomeFrame().getRomeTown().getPlayerLabel().repaint();
        this.launchPage.getWorldMap().getRomeFrame().getRomeTown().getPlayerLabel().revalidate();

        this.launchPage.getWorldMap().getSpartaFrame().getSpartaTown().getPlayerLabel().getTreasuryLabel().setText(
                "Treasury: \uD83D\uDCB0 "+this.game.getPlayer().getTreasury());
        this.launchPage.getWorldMap().getSpartaFrame().getSpartaTown().getPlayerLabel().getFoodLabel().setText(
                "Food: " + this.game.getPlayer().getFood()
        );
        this.launchPage.getWorldMap().getSpartaFrame().getSpartaTown().getPlayerLabel().getTurnslabel().setText(this.game.getCurrentTurnCount() + " Turns");
        this.launchPage.getWorldMap().getSpartaFrame().getSpartaTown().getPlayerLabel().repaint();
        this.launchPage.getWorldMap().getSpartaFrame().getSpartaTown().getPlayerLabel().revalidate();
    }

    public void addArmyButtonsActionListeners() {
        for (ArmyButton button : this.launchPage.getWorldMap().getControlledArmiesFrame().getArmyButtons())
            button.addActionListener(this);
        for (ArmyButton button : this.launchPage.getWorldMap().getMarchingArmiesFrame().getArmyButtons())
            button.addActionListener(this);
        for (ArmyButton button: this.launchPage.getWorldMap().getBesiegingArmiesFrame().getArmyButtons())
            button.addActionListener(this);
    }

    public void playSound(String path) {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(path).getAbsoluteFile());
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        }
        catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            handleContinueButton(e);
        } catch (IOException ioException) {
            System.out.println(ioException.getMessage());
        }
        if (worldMapFlag) {
            handleCairoButtons(e);
            handleSpartaButtons(e);
            handleRomeButtons(e);
            handleEndTurnButton(e);
            //intiate
            for (UnitButton button : this.launchPage.getWorldMap().getCairoFrame().getDefendingArmyCairo().getUnitButtonsCairo()) {
                if (e.getSource() == button) {
                    disableRemainingButtons(button);
                    this.launchPage.getWorldMap().getCairoFrame().getDefendingArmyCairo().getInitiateArmyCairo().setEnabled(true);
                    this.launchPage.getWorldMap().getCairoFrame().getDefendingArmyCairo().reload();
                    this.addInitiateArmyButtonsActionListeners();
                    this.addRelocateUnitActionListeners();
                    this.launchPage.getWorldMap().getCairoFrame().getDefendingArmyCairo().reload();
                    unit=button.getUnit();
                    break;
                }
            }
            //intiate
            for (UnitButton button : this.launchPage.getWorldMap().getRomeFrame().getDefendingArmyRome().getUnitButtonsRome()) {
                if (e.getSource() == button) {
                    disableRemainingButtons(button);
                    this.launchPage.getWorldMap().getRomeFrame().getDefendingArmyRome().getInitiateArmyRome().setEnabled(true);
                    this.launchPage.getWorldMap().getRomeFrame().getDefendingArmyRome().reload();
                    this.addInitiateArmyButtonsActionListeners();
                    this.addRelocateUnitActionListeners();
                    this.launchPage.getWorldMap().getRomeFrame().getDefendingArmyRome().reload();
                    unit=button.getUnit();
                    break;
                }
            }
//intiate
            for (UnitButton button : this.launchPage.getWorldMap().getSpartaFrame().getDefendingArmySparta().getUnitButtonsSparta()) {
                if (e.getSource() == button) {
                    disableRemainingButtons(button);
                    this.launchPage.getWorldMap().getSpartaFrame().getDefendingArmySparta().getInitiateArmySparta().setEnabled(true);
                    this.launchPage.getWorldMap().getSpartaFrame().getDefendingArmySparta().reload();
                    this.addInitiateArmyButtonsActionListeners();
                    this.addRelocateUnitActionListeners();
                    this.launchPage.getWorldMap().getSpartaFrame().getDefendingArmySparta().reload();
                    unit=button.getUnit();
                    break;
                }
            }


            if (unit!=null) {
                handleInitiateArmyButton(unit, e);
                handleRelocateUnitButton(unit, e);
                reEnableAllButtons();
            }
//target
            for (ArmyButton button : this.launchPage.getWorldMap().getControlledArmiesFrame().getArmyButtons()) {
                if (e.getSource() == button) {
                    this.launchPage.getWorldMap().getControlledArmiesFrame().getControlledArmiesUnitsFrame().get(this.launchPage.
                            getWorldMap().getControlledArmiesFrame().getArmyButtons().indexOf(button)).setVisible(true);
                    this.launchPage.getWorldMap().getControlledArmiesFrame().dispose();
                    indexOfSelectedMarchingArmy = this.launchPage.getWorldMap().getControlledArmiesFrame().getArmyButtons().indexOf(button);
                    if(indexOfSelectedMarchingArmy==-1)
                        return;
                    selectedMarchingArmy = this.launchPage.getWorldMap().getControlledArmiesFrame().getControlledArmiesOfAll().get(indexOfSelectedMarchingArmy);
                    this.addTargetCityButtonsActionListeners();
                    disableCorrespondingCityButton();
                    break;
                }
            }

            //laySiege

            for (ArmyButton button : this.launchPage.getWorldMap().getMarchingArmiesFrame().getArmyButtons()) {
                if (e.getSource() == button) {
                    this.launchPage.getWorldMap().getMarchingArmiesFrame().getControlledArmiesUnitsFrame().get(this.launchPage.getWorldMap().
                            getMarchingArmiesFrame().getArmyButtons().indexOf(button)).setVisible(true);

                    this.launchPage.getWorldMap().getMarchingArmiesFrame().dispose();
                    getIndexOfSelectedBesiegingArmy = this.launchPage.getWorldMap().getMarchingArmiesFrame().getArmyButtons().indexOf(button);
                    if (getIndexOfSelectedBesiegingArmy == -1)
                        return;
                    selectedBesiegingArmy = this.launchPage.getWorldMap().getMarchingArmiesFrame().getControlledArmiesOfAll().get(getIndexOfSelectedBesiegingArmy);
                    this.addLaySiegeActionListeners();
                    break;
                }
            }

            if (selectedMarchingArmy != null && indexOfSelectedMarchingArmy!=-1 ) {
                handleTargetCity(e);
                //this.addBattleFinishedActionListeners();
            }


            if (selectedBesiegingArmy != null && getIndexOfSelectedBesiegingArmy != -1) {
                handleLaySiege(e);
            }


            for (ControlledArmiesUnitsFrame frame : this.launchPage.getWorldMap().getMarchingArmiesFrame().getControlledArmiesUnitsFrame()) {
                UnitButton button = null;
                for (UnitButton unitButton : frame.getUnitButtons()) {
                    if (unitButton != null) {
                        button = unitButton;
                        break;
                    }
                }
                if (frame.isFlag()) {
                    if (frame.getBattleArenas().isWhoWon()) {
                        if (button != null) {
                            this.game.occupy(button.getUnit().getParentArmy(), frame.getTargetCityName().toLowerCase());
                            if (frame.getTargetCityName().equalsIgnoreCase("Rome")) {
                                this.launchPage.getWorldMap().setRomeFlag(true);
                                for (Unit unit : button.getUnit().getParentArmy().getUnits()) {
                                    this.launchPage.getWorldMap().getRomeFrame().getDefendingArmyRome().addToDefending(unit);
                                }
                                this.launchPage.getWorldMap().flags();
                            } else if (frame.getTargetCityName().equalsIgnoreCase("Cairo")) {
                                this.launchPage.getWorldMap().setCairoFlag(true);
                                for (Unit unit : button.getUnit().getParentArmy().getUnits()) {
                                    this.launchPage.getWorldMap().getCairoFrame().getDefendingArmyCairo().addToDefending(unit);
                                }
                                this.launchPage.getWorldMap().flags();
                            } else if (frame.getTargetCityName().equalsIgnoreCase("Sparta")) {
                                this.launchPage.getWorldMap().setSpartaFlag(true);
                                for (Unit unit : button.getUnit().getParentArmy().getUnits()) {
                                    this.launchPage.getWorldMap().getSpartaFrame().getDefendingArmySparta().addToDefending(unit);
                                }
                                this.launchPage.getWorldMap().flags();
                            }
                            this.launchPage.getWorldMap().reload();
                        }
                        break;
                    } else if (!frame.getBattleArenas().isWhoWon() && button != null) {
                        this.game.getPlayer().getControlledArmies().remove(button.getUnit().getParentArmy());
                        City correspondingCity = this.game.getCorrespondingAvailableCity(frame.getTargetCityName().toLowerCase());
                        if (correspondingCity != null) {
                            correspondingCity.setUnderSiege(false);
                            correspondingCity.setTurnsUnderSiege(-1);
                        }
                        break;
                    }
                    frame.setFlag(false);
                    this.launchPage.getWorldMap().reload();
                }
                else if (e.getSource() == frame.getAutoResolve() && button != null && button.getUnit().getParentArmy() != null) {
                    try {
                        this.autoResolveFlag = true;
                        Army defender = null;
                        switch (frame.getTargetCityName().toLowerCase()) {
                            case "rome":
                                defender = this.game.getCorrespondingAvailableCity("rome").getDefendingArmy();
                                break;
                            case "cairo":
                                defender = this.game.getCorrespondingAvailableCity("cairo").getDefendingArmy();
                                break;
                            case "sparta":
                                defender = this.game.getCorrespondingAvailableCity("sparta").getDefendingArmy();
                                break;
                        }
                        if (defender != null) {
                            this.game.autoResolve(button.getUnit().getParentArmy(), defender);

                            if (this.game.isWhoWon()) {
                                if (frame.getTargetCityName().equalsIgnoreCase("Rome")) {
                                    this.launchPage.getWorldMap().setRomeFlag(true);
                                    for (Unit unit : button.getUnit().getParentArmy().getUnits()) {
                                        this.launchPage.getWorldMap().getRomeFrame().getDefendingArmyRome().addToDefending(unit);
                                    }
                                    this.launchPage.getWorldMap().flags();
                                } else if (frame.getTargetCityName().equalsIgnoreCase("Cairo")) {
                                    this.launchPage.getWorldMap().setCairoFlag(true);
                                    for (Unit unit : button.getUnit().getParentArmy().getUnits()) {
                                        this.launchPage.getWorldMap().getCairoFrame().getDefendingArmyCairo().addToDefending(unit);
                                    }
                                    this.launchPage.getWorldMap().flags();
                                } else if (frame.getTargetCityName().equalsIgnoreCase("Sparta")) {
                                    this.launchPage.getWorldMap().setSpartaFlag(true);
                                    for (Unit unit : button.getUnit().getParentArmy().getUnits()) {
                                        this.launchPage.getWorldMap().getSpartaFrame().getDefendingArmySparta().addToDefending(unit);
                                    }
                                    this.launchPage.getWorldMap().flags();
                                }
                                this.launchPage.getWorldMap().reload();
                                JOptionPane.showMessageDialog(null, "YOU WON THE BATTLE", "Battle Over", JOptionPane.INFORMATION_MESSAGE);
                                break;
                            }
                            else {
                                this.autoResolveFlag = true;
                                if (!flag) {
                                    JOptionPane.showMessageDialog(null, "YOU LOST THE BATTLE", "Battle Over", JOptionPane.INFORMATION_MESSAGE);
                                    flag = true;
                                }
                                break;
                            }

                        }
                        this.autoResolveFlag = true;
                        frame.dispose();
                        break;
                    }
                    catch (FriendlyFireException ex) {
                        JOptionPane.showMessageDialog(null,ex.getMessage(),"Action Denied",JOptionPane.WARNING_MESSAGE);
                    }
                    this.autoResolveFlag = true;
                    break;
                }
            }

            handleSiegeBattle(e);
        }

    }

    public void handleSiegeBattle(ActionEvent e) {
        for (ControlledArmiesUnitsFrame frame : this.launchPage.getWorldMap().getBesiegingArmiesFrame().getControlledArmiesUnitsFrame()) {
            UnitButton button = null;
            for (UnitButton unitButton : frame.getUnitButtons()) {
                if (unitButton != null) {
                    button = unitButton;
                    break;
                }
            }
            if (frame.isFlag()) {
                if (frame.getBattleArenas().isWhoWon()) {
                    if (button != null) {
                        this.game.occupy(button.getUnit().getParentArmy(), frame.getTargetCityName().toLowerCase());
                        if (frame.getTargetCityName().equalsIgnoreCase("Rome")) {
                            this.launchPage.getWorldMap().setRomeFlag(true);
                            for (Unit unit : button.getUnit().getParentArmy().getUnits()) {
                                this.launchPage.getWorldMap().getRomeFrame().getDefendingArmyRome().addToDefending(unit);
                            }
                            this.launchPage.getWorldMap().flags();
                        } else if (frame.getTargetCityName().equalsIgnoreCase("Cairo")) {
                            this.launchPage.getWorldMap().setCairoFlag(true);
                            for (Unit unit : button.getUnit().getParentArmy().getUnits()) {
                                this.launchPage.getWorldMap().getCairoFrame().getDefendingArmyCairo().addToDefending(unit);
                            }
                            this.launchPage.getWorldMap().flags();
                        } else if (frame.getTargetCityName().equalsIgnoreCase("Sparta")) {
                            this.launchPage.getWorldMap().setSpartaFlag(true);
                            for (Unit unit : button.getUnit().getParentArmy().getUnits()) {
                                this.launchPage.getWorldMap().getSpartaFrame().getDefendingArmySparta().addToDefending(unit);
                            }
                            this.launchPage.getWorldMap().flags();
                        }
                        this.launchPage.getWorldMap().reload();
                    }
                    break;
                } else if (!frame.getBattleArenas().isWhoWon() && button != null) {
                    this.game.getPlayer().getControlledArmies().remove(button.getUnit().getParentArmy());
                    City correspondingCity = this.game.getCorrespondingAvailableCity(frame.getTargetCityName().toLowerCase());
                    if (correspondingCity != null) {
                        correspondingCity.setUnderSiege(false);
                        correspondingCity.setTurnsUnderSiege(-1);
                    }
                    break;
                }
                frame.setFlag(false);
                this.launchPage.getWorldMap().reload();
            }
            else if (e.getSource() == frame.getAutoResolve() && button != null && button.getUnit().getParentArmy() != null) {
                try {
                    Army defender = null;
                    this.autoResolveFlag = true;
                    switch (frame.getTargetCityName().toLowerCase()) {
                        case "rome":
                            defender = this.game.getCorrespondingAvailableCity("rome").getDefendingArmy();
                            break;
                        case "cairo":
                            defender = this.game.getCorrespondingAvailableCity("cairo").getDefendingArmy();
                            break;
                        case "sparta":
                            defender = this.game.getCorrespondingAvailableCity("sparta").getDefendingArmy();
                            break;
                    }
                    if (defender != null) {
                        this.game.autoResolve(button.getUnit().getParentArmy(), defender);

                        if (this.game.isWhoWon()) {
                            if (frame.getTargetCityName().equalsIgnoreCase("Rome")) {
                                this.launchPage.getWorldMap().setRomeFlag(true);
                                for (Unit unit : button.getUnit().getParentArmy().getUnits()) {
                                    this.launchPage.getWorldMap().getRomeFrame().getDefendingArmyRome().addToDefending(unit);
                                }
                                this.launchPage.getWorldMap().flags();
                            } else if (frame.getTargetCityName().equalsIgnoreCase("Cairo")) {
                                this.launchPage.getWorldMap().setCairoFlag(true);
                                for (Unit unit : button.getUnit().getParentArmy().getUnits()) {
                                    this.launchPage.getWorldMap().getCairoFrame().getDefendingArmyCairo().addToDefending(unit);
                                }
                                this.launchPage.getWorldMap().flags();
                            } else if (frame.getTargetCityName().equalsIgnoreCase("Sparta")) {
                                this.launchPage.getWorldMap().setSpartaFlag(true);
                                for (Unit unit : button.getUnit().getParentArmy().getUnits()) {
                                    this.launchPage.getWorldMap().getSpartaFrame().getDefendingArmySparta().addToDefending(unit);
                                }
                                this.launchPage.getWorldMap().flags();
                            }
                            this.launchPage.getWorldMap().reload();
                            JOptionPane.showMessageDialog(null, "YOU WON THE BATTLE", "Battle Over", JOptionPane.INFORMATION_MESSAGE);
                            break;
                        }
                        else {
                            this.autoResolveFlag = true;
                            if (!flag) {
                                JOptionPane.showMessageDialog(null, "YOU LOST THE BATTLE", "Battle Over", JOptionPane.INFORMATION_MESSAGE);
                                flag = true;
                            }
                            break;
                        }

                    }
                    this.autoResolveFlag = true;
                    frame.dispose();
                    break;
                }
                catch (FriendlyFireException ex) {
                    JOptionPane.showMessageDialog(null,ex.getMessage(),"Action Denied",JOptionPane.WARNING_MESSAGE);
                }
                this.autoResolveFlag = true;
                break;
            }
        }
    }
}