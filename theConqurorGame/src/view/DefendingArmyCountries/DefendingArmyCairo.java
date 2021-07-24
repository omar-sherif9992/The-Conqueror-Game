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

public class DefendingArmyCairo extends MyFrame  implements ActionListener {
    private int archerLevel;
    private int infantryLevel;
    private int cavalryLevel;
    private JPanel rightPanel=new JPanel();
    private JPanel leftPanel=new JPanel();
    private int x=24;
    private int i=0;
    private MyButton relocateUnitCairo=new MyButton();
    private MyButton initiateArmyCairo =new MyButton();
    private JLabel numberOfUnits=new JLabel();
    private ArrayList <UnitButton> unitButtonsCairo =new ArrayList<>();
    private JLabel playerNameLabel=new JLabel();
    private JLabel treasuryLabel=new JLabel();
    private JLabel foodLabel=new JLabel();


    private ArrayList<Unit> defendingArmyCairo =new ArrayList<>();//todo for getting the index of the unit and taking that index and remove it from unitButtons accordingly
    private CloseButton closeButton=new CloseButton();


    public DefendingArmyCairo() {

        this.setLayout(null);
        this.setVisible(false);
        rightPanel.setBounds(1301,0,300,1000);
        rightPanel.setBackground(Color.WHITE);
        leftPanel.setBounds(0,0,1300,1000);
        leftPanel.setLayout(new FlowLayout(0,0,0));
        rightPanel.setVisible(true);
        leftPanel.setVisible(true);
        setRightPanel();
        this.add(rightPanel);
        this.add(leftPanel);

        this.reload();

    }
    public void setRightPanel(){

        closeButton.setBounds(140,2,90,70);
        closeButton.addActionListener(this);
        rightPanel.add(closeButton);
        rightPanel.setLayout(null);
        relocateUnitCairo.setVisible(true);
        relocateUnitCairo.setBounds(20,450,200,50);
        relocateUnitCairo.setForeground(Color.WHITE);
        relocateUnitCairo.setFont(new Font("NEW ROMAN",Font.BOLD,25));
        relocateUnitCairo.setBackground(Color.BLACK);
        relocateUnitCairo.setText("Relocate Unit");
        rightPanel.add(relocateUnitCairo);


        initiateArmyCairo.setVisible(true);
        initiateArmyCairo.setEnabled(false);
        initiateArmyCairo.setBounds(20,550,200,50);
        initiateArmyCairo.setForeground(Color.WHITE);
        initiateArmyCairo.setFont(new Font("NEW ROMAN",Font.BOLD,25));
        initiateArmyCairo.setBackground(Color.BLACK);
        initiateArmyCairo.setText("Initiate Army");
        rightPanel.add(initiateArmyCairo);

        JLabel cairoPic=new JLabel();
        cairoPic.setIcon(new ImageIcon("CairoEditedKo.jpg"));//todo fix image
        cairoPic.setBounds(20,100,200,250);
        cairoPic.setText("Cairo Defending Army");

        JLabel cairoName=new JLabel();

        cairoName.setBounds(20,250,250,250);
        cairoName.setVisible(true);
        cairoName.setText("Cairo Defending Army");
        cairoName.setFont(new Font("NEW ROMAN",Font.BOLD,20));



        numberOfUnits.setBounds(50,300,250,250);
        numberOfUnits.setVisible(true);
        numberOfUnits.setText("Total Units :"+i);
        numberOfUnits.setFont(new Font("NEW ROMAN",Font.BOLD,20));


            rightPanel.add(numberOfUnits);
            rightPanel.add(cairoName);
        rightPanel.add(cairoPic);


        playerNameLabel.setBounds(50,550+50+40,400,40);
        playerNameLabel.setVisible(true);
        playerNameLabel.setText("Player Name:" );
        playerNameLabel.setFont(new Font("NEW ROMAN",Font.BOLD,20));

        treasuryLabel.setBounds(50,550+50+40+50,400,40);
        treasuryLabel.setVisible(true);
        treasuryLabel.setText("Treasury :" );
        treasuryLabel.setFont(new Font("NEW ROMAN",Font.BOLD,20));

        foodLabel.setBounds(50,550+50+40+50+50,400,40);
        foodLabel.setVisible(true);
        foodLabel.setText("Food :" );
        foodLabel.setFont(new Font("NEW ROMAN",Font.BOLD,20));


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

        if(unit instanceof Archer) {
            tempButton=new UnitButton(Status.IDLE,unit.getLevel(),"Archer",unit.getCurrentSoldierCount(),unit.getMaxSoldierCount(),unit);
            if (!unitButtonsCairo.contains(tempButton)){
                leftPanel.add(tempButton);
                unitButtonsCairo.add(tempButton);
                defendingArmyCairo.add(unit);


            }
        }
        else if (unit instanceof Infantry) {
            tempButton=new UnitButton(Status.IDLE,unit.getLevel(),"Infantry",unit.getCurrentSoldierCount(),unit.getMaxSoldierCount(),unit);
            if (!unitButtonsCairo.contains(tempButton)){
                leftPanel.add(tempButton);
                unitButtonsCairo.add(tempButton);
                defendingArmyCairo.add(unit);
            }
        }
        else if (unit instanceof Cavalry) {
            tempButton=new UnitButton(Status.IDLE,unit.getLevel(),"Cavalry",unit.getCurrentSoldierCount(),unit.getMaxSoldierCount(),unit);
            if (!unitButtonsCairo.contains(tempButton)){
                leftPanel.add(tempButton);
                defendingArmyCairo.add(unit);
                unitButtonsCairo.add(tempButton);}

        }
        i++;
        if (i!=25)
            numberOfUnits.setText("Total Units :"+i);
        this.reload();
    }
    public UnitButton RelocateToControl(Unit unit){
        int index= defendingArmyCairo.indexOf(unit);
        UnitButton unitButton= unitButtonsCairo.get(index);
        unitButtonsCairo.remove(index);
        System.out.println(unitButton);
        i--;
        numberOfUnits.setText("Total Units :"+i);
        defendingArmyCairo.remove(index);
        leftPanel.remove(unitButton);
        leftPanel.revalidate();
        leftPanel.repaint();
        this.reload();
       return  unitButton;
    }


    public static void main(String[] args) {
        Unit a=new Archer(2,2,2,2,2);
        Unit a1=new Archer(2,2,2,2,2);
        Unit a2=new Archer(1,2,2,2,2);
        Unit a3=new Archer(3,2,2,2,2);
        Unit a4=new Archer(2,2,2,2,2);
        Unit a5=new Archer(2,2,2,2,2);
        Unit a6=new Archer(2,2,2,2,2);
DefendingArmyCairo b=new DefendingArmyCairo();
        b.addToDefending(a1);
        b.addToDefending(a2);
        b.addToDefending(a3);
        b.RelocateToControl(a2);




    }



    public void setUnitButtonsCairo(ArrayList<UnitButton> unitButtonsCairo) {
        this.unitButtonsCairo = unitButtonsCairo;
    }

    public MyButton getRelocateUnitCairo() {
        return relocateUnitCairo;
    }

    public MyButton getInitiateArmyCairo() {
        return initiateArmyCairo;
    }

    public ArrayList<UnitButton> getUnitButtonsCairo() {
        return unitButtonsCairo;
    }

    public ArrayList<Unit> getDefendingArmyCairo() {
        return defendingArmyCairo;
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
        if (e.getSource()==closeButton){
            this.dispose();
        }
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
