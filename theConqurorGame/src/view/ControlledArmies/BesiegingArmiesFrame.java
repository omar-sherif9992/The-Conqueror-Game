package view.ControlledArmies;

import units.*;
import view.ArmyButton.ArmyButton;

import view.tools.CloseButton;
import view.tools.MyButton;
import view.tools.MyFrame;
import view.UnitsButtons.AddUnitLabel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class BesiegingArmiesFrame extends MyFrame  implements ActionListener {

    private AddUnitLabel addUnitLabel=new AddUnitLabel();

    private JPanel rightPanel=new JPanel();
    private JPanel leftPanel=new JPanel();
    private int x=24;
    private int numberOfArmies =0;

    private JLabel numberOfArmiesLabel =new JLabel();
    private ArrayList<ArmyButton> armyButtons =new ArrayList<>();
    private ArrayList<Army> controlledArmiesOfAll =new ArrayList<>();//todo for getting the index of the unit and taking that index and remove it from unitButtons accordingly
    private CloseButton closeButton=new CloseButton();
    private ArrayList <ControlledArmiesUnitsFrame>controlledArmiesUnitsFrame=new ArrayList();
    private JLabel playerNameLabel=new JLabel();
    private JLabel treasuryLabel=new JLabel();
    private JLabel foodLabel=new JLabel();


    public BesiegingArmiesFrame() {

        this.setLayout(null);
        this.setVisible(true);
        rightPanel.setBounds(1301,0,300,1000);
        rightPanel.setBackground(Color.WHITE);
        leftPanel.setBounds(0,0,1300,1000);
        leftPanel.setLayout(new FlowLayout(0,0,0));
        rightPanel.setVisible(true);
        leftPanel.setVisible(true);
        this.setRightPanel();
        addUnitLabel.setText("Add Army");
        leftPanel.add(addUnitLabel);
        this.add(rightPanel);
        this.add(leftPanel);


        this.reload();


    }
    public void setRightPanel(){

        rightPanel.setLayout(null);

        closeButton.setBounds(140,2,90,70);
        closeButton.addActionListener(this);
        rightPanel.add(closeButton);

        leftPanel.setVisible(true);



        JLabel controlPic=new JLabel();
        controlPic.setIcon(new ImageIcon("THE_Conquror_Logo.Edited.jpg"));//todo fix image
        controlPic.setBounds(20,100,200,250);

        JLabel cairoName=new JLabel();

        cairoName.setBounds(20,250+100,250,70);
        cairoName.setVisible(true);
        cairoName.setText("Besieging Armies");
        cairoName.setFont(new Font("NEW ROMAN",Font.BOLD,20));



        numberOfArmiesLabel.setBounds(50,250+170,250,70);
        numberOfArmiesLabel.setVisible(true);
        numberOfArmiesLabel.setText("Total Armies :"+ (numberOfArmies + 1));
        numberOfArmiesLabel.setFont(new Font("NEW ROMAN",Font.BOLD,20));

        playerNameLabel.setBounds(50,250+270,400,70);
        playerNameLabel.setVisible(true);
        playerNameLabel.setText("Player Name:" );
        playerNameLabel.setFont(new Font("NEW ROMAN",Font.BOLD,20));

        treasuryLabel.setBounds(50,250+370,400,70);
        treasuryLabel.setVisible(true);
        treasuryLabel.setText("Treasury :" );
        treasuryLabel.setFont(new Font("NEW ROMAN",Font.BOLD,20));

        foodLabel.setBounds(50,250+470,400,70);
        foodLabel.setVisible(true);
        foodLabel.setText("Food :" );
        foodLabel.setFont(new Font("NEW ROMAN",Font.BOLD,20));


        rightPanel.add(treasuryLabel);
        rightPanel.add(foodLabel);
        rightPanel.add(playerNameLabel);

        rightPanel.add(numberOfArmiesLabel);
        rightPanel.add(cairoName);
        rightPanel.add(controlPic);

    }


    public ArmyButton addtoBesiegingArmyFrame(Army army, String cityName){
        ArmyButton tempButton=null;
        leftPanel.remove(addUnitLabel);
        if(numberOfArmies >x)
            return null;



        tempButton=new ArmyButton(numberOfArmies,army.getCurrentStatus(),cityName,army.getUnits().size());
        tempButton.getArmyInfoLabel().getArmyNumberLabel().setText("Army : " + (numberOfArmies + 1));
        tempButton.getArmyInfoLabel().getTargetCityLabel().setText("Current Location : " + cityName);
        tempButton.getArmyInfoLabel().getStatusLabel().setText("Status " + Status.BESIEGING);
        tempButton.addActionListener(this);

        ControlledArmiesUnitsFrame c=new ControlledArmiesUnitsFrame(army.getUnits().get(0));
        controlledArmiesUnitsFrame.add(c);// todo to put the unit in the army Frame

        for (Unit unit: army.getUnits()){
            if (unit!=army.getUnits().get(0)){
                c.addToArmyUnits(unit);
            }
        }

        if (!controlledArmiesOfAll.contains(army)){
            leftPanel.add(tempButton);
            armyButtons.add(tempButton);
        }
        controlledArmiesOfAll.add(army);

        numberOfArmies++;
        if (numberOfArmies<24)
            leftPanel.add(addUnitLabel);
        if (numberOfArmies !=25){


            numberOfArmiesLabel.setText("Total Units :"+ numberOfArmies);}
        leftPanel.repaint();
        leftPanel.revalidate();
        this.reload();
        return tempButton;
    }


    public void deleteArmy(Army army){
        if (!controlledArmiesOfAll.contains(army))
            return;
        int index= controlledArmiesOfAll.indexOf(army);
        ArmyButton armyButton= armyButtons.get(index);
        armyButtons.remove(index);
        controlledArmiesOfAll.remove(index);
        numberOfArmies--;
        numberOfArmiesLabel.setText("Total Armies :"+ numberOfArmies);

        leftPanel.remove(armyButton);
        this.reload();
    }

    public static void main(String[] args) {
        Army a=new Army("cairo");
        Army a1=new Army("sparta");
        Unit a2=new Archer(1,2,2,2,2);
        Unit a3=new Archer(3,2,2,2,2);
        Unit a4=new Archer(2,2,2,2,2);
        Unit a5=new Archer(2,2,2,2,2);
        Unit a6=new Archer(2,2,2,2,2);
        a.getUnits().add(a2);

        ControlledArmiesFrame b=new ControlledArmiesFrame();

        b.initiateArmyOfControlFrame(a,"Cairo");
        b.initiateArmyOfControlFrame(a1,"Sparta");
        b.initiateArmyOfControlFrame(a1,"Sparta");
        b.initiateArmyOfControlFrame(a1,"Sparta");
        b.initiateArmyOfControlFrame(a1,"Sparta");           b.initiateArmyOfControlFrame(a1,"Sparta");
        b.initiateArmyOfControlFrame(a1,"Sparta");
        b.initiateArmyOfControlFrame(a1,"Sparta");
        b.initiateArmyOfControlFrame(a1,"Sparta");           b.initiateArmyOfControlFrame(a1,"Sparta");
        b.initiateArmyOfControlFrame(a1,"Sparta");           b.initiateArmyOfControlFrame(a1,"Sparta");
        b.initiateArmyOfControlFrame(a1,"Sparta");           b.initiateArmyOfControlFrame(a1,"Sparta");
        b.initiateArmyOfControlFrame(a1,"Sparta");           b.initiateArmyOfControlFrame(a1,"Sparta");
        b.initiateArmyOfControlFrame(a1,"Sparta");           b.initiateArmyOfControlFrame(a1,"Sparta");
        b.initiateArmyOfControlFrame(a1,"Sparta");           b.initiateArmyOfControlFrame(a1,"Sparta");
        b.initiateArmyOfControlFrame(a1,"Sparta");           b.initiateArmyOfControlFrame(a1,"Sparta");
        b.initiateArmyOfControlFrame(a1,"Sparta");
        b.initiateArmyOfControlFrame(a1,"Sparta");









    }



    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==closeButton){
            this.dispose();
        }
        for (ArmyButton armyButton: armyButtons){
            if (e.getSource()==armyButton){
                controlledArmiesUnitsFrame.get(armyButtons.indexOf(armyButton)).setVisible(true);
                this.dispose();}
        }
    }

    public AddUnitLabel getAddUnitLabel() {
        return addUnitLabel;
    }

    public JPanel getRightPanel() {
        return rightPanel;
    }

    public JPanel getLeftPanel() {
        return leftPanel;
    }

    @Override
    public int getX() {
        return x;
    }

    public int getNumberOfArmies() {
        return numberOfArmies;
    }

    public JLabel getNumberOfArmiesLabel() {
        return numberOfArmiesLabel;
    }

    public ArrayList<ArmyButton> getArmyButtons() {
        return armyButtons;
    }

    public ArrayList<Army> getControlledArmiesOfAll() {
        return controlledArmiesOfAll;
    }

    public CloseButton getCloseButton() {
        return closeButton;
    }

    public ArrayList<ControlledArmiesUnitsFrame> getControlledArmiesUnitsFrame() {
        return controlledArmiesUnitsFrame;
    }


    public void setAddUnitLabel(AddUnitLabel addUnitLabel) {
        this.addUnitLabel = addUnitLabel;
    }

    public void setRightPanel(JPanel rightPanel) {
        this.rightPanel = rightPanel;
    }

    public void setLeftPanel(JPanel leftPanel) {
        this.leftPanel = leftPanel;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setNumberOfArmies(int numberOfArmies) {
        this.numberOfArmies = numberOfArmies;
    }

    public void setNumberOfArmiesLabel(JLabel numberOfArmiesLabel) {
        this.numberOfArmiesLabel = numberOfArmiesLabel;
    }

    public void setArmyButtons(ArrayList<ArmyButton> armyButtons) {
        this.armyButtons = armyButtons;
    }

    public void setControlledArmiesOfAll(ArrayList<Army> controlledArmiesOfAll) {
        this.controlledArmiesOfAll = controlledArmiesOfAll;
    }

    public void setCloseButton(CloseButton closeButton) {
        this.closeButton = closeButton;
    }

    public void setControlledArmiesUnitsFrame(ArrayList<ControlledArmiesUnitsFrame> controlledArmiesUnitsFrame) {
        this.controlledArmiesUnitsFrame = controlledArmiesUnitsFrame;
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
