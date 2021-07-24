package view.DefendingArmyCountries;

import units.*;
import view.tools.CloseButton;
import view.tools.MyButton;
import view.tools.MyFrame;
import view.UnitsButtons.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class DefendingArmySparta extends MyFrame implements ActionListener {
    private int archerLevel;
    private int infantryLevel;
    private int cavalryLevel;
    private JPanel rightPanel = new JPanel();
    private JPanel leftPanel = new JPanel();
    private int x = 24;
    private int i = 0;
    private MyButton relocateUnitSparta = new MyButton();
    private MyButton initiateArmySparta = new MyButton();
    private JLabel numberOfUnits = new JLabel();
    private ArrayList<UnitButton> unitButtonsSparta = new ArrayList<>();
    private ArrayList<Unit> defendingArmySparta = new ArrayList<>();//todo for getting the index of the unit and taking that index and remove it from unitButtons accordingly
    private CloseButton closeButton = new CloseButton();
    private JLabel playerNameLabel = new JLabel();
    private JLabel treasuryLabel = new JLabel();
    private JLabel foodLabel = new JLabel();


    public DefendingArmySparta() {
        this.setLayout(null);
        this.setVisible(false);
        rightPanel.setBounds(1301, 0, 300, 1000);
        rightPanel.setBackground(Color.WHITE);


        leftPanel.setBounds(0, 0, 1300, 1000);
        leftPanel.setLayout(new FlowLayout(0, 0, 0));
        rightPanel.setVisible(true);
        leftPanel.setVisible(true);
        setRightPanel();
        this.add(rightPanel);
        this.add(leftPanel);

        this.reload();

    }

    public void setRightPanel() {
        closeButton.setBounds(140, 2, 90, 70);

        closeButton.addActionListener(this);
        rightPanel.add(closeButton);

        rightPanel.setLayout(null);
        relocateUnitSparta.setVisible(true);
        relocateUnitSparta.setBounds(20, 450, 200, 50);
        relocateUnitSparta.setForeground(Color.WHITE);
        relocateUnitSparta.setFont(new Font("NEW ROMAN", Font.BOLD, 25));
        relocateUnitSparta.setBackground(Color.BLACK);
        relocateUnitSparta.setText("Relocate Unit");
        rightPanel.add(relocateUnitSparta);


        initiateArmySparta.setVisible(true);
        initiateArmySparta.setEnabled(false);
        initiateArmySparta.setBounds(20, 550, 200, 50);
        initiateArmySparta.setForeground(Color.WHITE);
        initiateArmySparta.setFont(new Font("NEW ROMAN", Font.BOLD, 25));
        initiateArmySparta.setBackground(Color.BLACK);
        initiateArmySparta.setText("Initiate Army");
        rightPanel.add(initiateArmySparta);

        JLabel cairoPic = new JLabel();
        cairoPic.setIcon(new ImageIcon("SpartanKo.jpeg"));//todo fix image
        cairoPic.setBounds(20, 100, 200, 250);


        JLabel cairoName = new JLabel();

        cairoName.setBounds(20, 250, 250, 250);
        cairoName.setVisible(true);
        cairoName.setText("Sparta Defending Army");
        cairoName.setFont(new Font("NEW ROMAN", Font.BOLD, 20));


        playerNameLabel.setText("Player Name : ");


        numberOfUnits.setBounds(50, 300, 250, 250);
        numberOfUnits.setVisible(true);
        numberOfUnits.setText("Total Units :" + i);
        numberOfUnits.setFont(new Font("NEW ROMAN", Font.BOLD, 20));


        rightPanel.add(numberOfUnits);
        rightPanel.add(cairoName);
        rightPanel.add(cairoPic);

        playerNameLabel.setBounds(50, 550 + 50 + 40, 400, 40);
        playerNameLabel.setVisible(true);
        playerNameLabel.setText("Player Name:");
        playerNameLabel.setFont(new Font("NEW ROMAN", Font.BOLD, 20));

        treasuryLabel.setBounds(50, 550 + 50 + 40 + 50, 400, 40);
        treasuryLabel.setVisible(true);
        treasuryLabel.setText("Treasury :");
        treasuryLabel.setFont(new Font("NEW ROMAN", Font.BOLD, 20));

        foodLabel.setBounds(50, 550 + 50 + 40 + 50 + 50, 400, 40);
        foodLabel.setVisible(true);
        foodLabel.setText("Food :");
        foodLabel.setFont(new Font("NEW ROMAN", Font.BOLD, 20));


        rightPanel.add(treasuryLabel);
        rightPanel.add(foodLabel);
        rightPanel.add(playerNameLabel);

    }


    public void addToDefending(Unit unit){
        if (unit == null || unit.getCurrentSoldierCount() == 0)
            return;
        UnitButton tempButton=null;
        if(i>x)
            return;

        defendingArmySparta.add(unit);
        if(unit instanceof Archer) {
            tempButton=new UnitButton(Status.IDLE,unit.getLevel(),"Archer",unit.getCurrentSoldierCount(),unit.getMaxSoldierCount(),unit);
            if (!unitButtonsSparta.contains(tempButton)){
                leftPanel.add(tempButton);
                unitButtonsSparta.add(tempButton);}
        }
        else if (unit instanceof Infantry) {
            tempButton=new UnitButton(Status.IDLE,unit.getLevel(),"Infantry",unit.getCurrentSoldierCount(),unit.getMaxSoldierCount(),unit);
            if (!unitButtonsSparta.contains(tempButton)){
                leftPanel.add(tempButton);
                unitButtonsSparta.add(tempButton);}
        }
        else if (unit instanceof Cavalry) {
            tempButton=new UnitButton(Status.IDLE,unit.getLevel(),"Cavalry",unit.getCurrentSoldierCount(),unit.getMaxSoldierCount(),unit);
            if (!unitButtonsSparta.contains(tempButton)){
                leftPanel.add(tempButton);
                unitButtonsSparta.add(tempButton);}

        }
        i++;
        if (i!=25)
            numberOfUnits.setText("Total Units :"+i);
        this.reload();
    }
    public UnitButton RelocateToControl(Unit unit){
        int index=defendingArmySparta.indexOf(unit);
        UnitButton unitButton=unitButtonsSparta.get(index);
        unitButtonsSparta.remove(index);

        i--;
        numberOfUnits.setText("Total Units :"+i);
        defendingArmySparta.remove(index);
        leftPanel.remove(unitButton);
        leftPanel.revalidate();
        leftPanel.repaint();
        this.reload();

       return  unitButton ;
    }


    public static void main(String[] args) {
        Unit a=new Archer(2,2,2,2,2);
        Unit a1=new Archer(2,2,2,2,2);
        Unit a2=new Archer(1,2,2,2,2);
        Unit a3=new Archer(3,2,2,2,2);
        Unit a4=new Archer(2,2,2,2,2);
        Unit a5=new Archer(2,2,2,2,2);
        Unit a6=new Archer(2,2,2,2,2);
        DefendingArmySparta b=new DefendingArmySparta();
        b.addToDefending(a1);
        b.addToDefending(a2);
        b.addToDefending(a3);
        b.RelocateToControl(a2);



    }






    public int getArcherLevel() {
        return archerLevel;
    }

    public void setArcherLevel(int archerLevel) {
        this.archerLevel = archerLevel;
    }

    public int getInfantryLevel() {
        return infantryLevel;
    }

    public void setInfantryLevel(int infantryLevel) {
        this.infantryLevel = infantryLevel;
    }

    public int getCavalryLevel() {
        return cavalryLevel;
    }

    public void setCavalryLevel(int cavalryLevel) {
        this.cavalryLevel = cavalryLevel;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
       if (e.getSource()==closeButton)
           this.dispose();
    }

    public ArrayList<UnitButton> getUnitButtonsSparta() {
        return unitButtonsSparta;
    }

    public MyButton getRelocateUnitSparta() {
        return relocateUnitSparta;
    }

    public MyButton getInitiateArmySparta() {
        return initiateArmySparta;
    }

    public JLabel getNumberOfUnits() {
        return numberOfUnits;
    }

    public ArrayList<Unit> getDefendingArmySparta() {
        return defendingArmySparta;
    }

    public CloseButton getCloseButton() {
        return closeButton;
    }

    public JLabel getPlayerNameLabel() {
        return playerNameLabel;
    }

    public void setPlayerNameLabel(JLabel playerNameLabel) {
        this.playerNameLabel = playerNameLabel;
    }

    public JLabel getTreasuryLabel() {
        return treasuryLabel;
    }

    public void setTreasuryLabel(JLabel treasuryLabel) {
        this.treasuryLabel = treasuryLabel;
    }

    public JLabel getFoodLabel() {
        return foodLabel;
    }

    public void setFoodLabel(JLabel foodLabel) {
        this.foodLabel = foodLabel;
    }
}