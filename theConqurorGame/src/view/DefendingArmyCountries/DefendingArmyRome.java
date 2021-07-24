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

public class DefendingArmyRome extends MyFrame implements ActionListener {
    private int archerLevel;
    private int infantryLevel;
    private int cavalryLevel;
    private JPanel rightPanel=new JPanel();
    private JPanel leftPanel=new JPanel();
    private int x=24;
    private int i=0;
    private MyButton relocateUnitRome=new MyButton();
    private MyButton initiateArmyRome =new MyButton();
    private JLabel numberOfUnits=new JLabel();
    private ArrayList<UnitButton> unitButtonsRome =new ArrayList<>();
    private ArrayList<Unit> defendingArmyRome =new ArrayList<>();
    private CloseButton closeButton=new CloseButton();
    private JLabel playerNameLabel=new JLabel();
    private JLabel treasuryLabel=new JLabel();
    private JLabel foodLabel=new JLabel();



    public DefendingArmyRome(){
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
        relocateUnitRome.setVisible(true);
        relocateUnitRome.setBounds(20,450,200,50);
        relocateUnitRome.setForeground(Color.WHITE);
        relocateUnitRome.setFont(new Font("NEW ROMAN",Font.BOLD,25));
        relocateUnitRome.setBackground(Color.BLACK);
        relocateUnitRome.setText("Relocate Unit");
        rightPanel.add(relocateUnitRome);


        initiateArmyRome.setVisible(true);
        initiateArmyRome.setEnabled(false);
        initiateArmyRome.setBounds(20,550,200,50);
        initiateArmyRome.setForeground(Color.WHITE);
        initiateArmyRome.setFont(new Font("NEW ROMAN",Font.BOLD,25));
        initiateArmyRome.setBackground(Color.BLACK);
        initiateArmyRome.setText("Initiate Army");
        rightPanel.add(initiateArmyRome);

        JLabel romePic=new JLabel();
        romePic.setIcon(new ImageIcon("RomeEditedKo.jpg"));//todo fix image
        romePic.setBounds(20,100,200,250);


        JLabel romeName=new JLabel();

        romeName.setBounds(20,250,250,250);
        romeName.setVisible(true);
        romeName.setText("Rome Defending Army");
        romeName.setFont(new Font("NEW ROMAN",Font.BOLD,20));



        numberOfUnits.setBounds(50,300,250,250);
        numberOfUnits.setVisible(true);
        numberOfUnits.setText("Total Units :"+i);
        numberOfUnits.setFont(new Font("NEW ROMAN",Font.BOLD,20));


        rightPanel.add(numberOfUnits);
        rightPanel.add(romeName);
        rightPanel.add(romePic);


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
        defendingArmyRome.add(unit);
        if(unit instanceof Archer) {
            tempButton=new UnitButton(Status.IDLE,unit.getLevel(),"Archer",unit.getCurrentSoldierCount(),unit.getMaxSoldierCount(),unit);
            if (!unitButtonsRome.contains(tempButton)){
            leftPanel.add(tempButton);
            unitButtonsRome.add(tempButton);

            }
        }
        else if (unit instanceof Infantry) {
            tempButton=new UnitButton(Status.IDLE,unit.getLevel(),"Infantry",unit.getCurrentSoldierCount(),unit.getMaxSoldierCount(),unit);
            if (!unitButtonsRome.contains(tempButton)){
                leftPanel.add(tempButton);
                unitButtonsRome.add(tempButton);}
        }
        else if (unit instanceof Cavalry) {
            tempButton=new UnitButton(Status.IDLE,unit.getLevel(),"Cavalry",unit.getCurrentSoldierCount(),unit.getMaxSoldierCount(),unit);
            if (!unitButtonsRome.contains(tempButton)){
                leftPanel.add(tempButton);
                unitButtonsRome.add(tempButton);}

        }
        i++;
        if (i!=25)
            numberOfUnits.setText("Total Units :"+i);
        this.reload();
    }
    public UnitButton RelocateToControl(Unit unit){
        int index=defendingArmyRome.indexOf(unit);
        UnitButton unitButton= unitButtonsRome.get(index);
        unitButtonsRome.remove(index);
        System.out.println(unitButton);

        i--;
        numberOfUnits.setText("Total Units :"+i);
        defendingArmyRome.remove(index);
        leftPanel.remove(unitButton);
        leftPanel.revalidate();
        leftPanel.repaint();
        this.reload();

        return unitButton;
    }


    public static void main(String[] args) {
        Unit a=new Archer(2,2,2,2,2);
        Unit a1=new Archer(2,2,2,2,2);
        Unit a2=new Archer(1,2,2,2,2);
        Unit a3=new Archer(3,2,2,2,2);
        Unit a4=new Archer(2,2,2,2,2);
        Unit a5=new Archer(2,2,2,2,2);
        Unit a6=new Archer(2,2,2,2,2);
        DefendingArmyRome b=new DefendingArmyRome();
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
        if (e.getSource()==closeButton){
            this.dispose();
        }
    }

    public ArrayList<UnitButton> getUnitButtonsRome() {
        return unitButtonsRome;
    }

    public MyButton getRelocateUnitRome() {
        return relocateUnitRome;
    }

    public MyButton getInitiateArmyRome() {
        return initiateArmyRome;
    }

    public JLabel getNumberOfUnits() {
        return numberOfUnits;
    }

    public ArrayList<Unit> getDefendingArmyRome() {
        return defendingArmyRome;
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
