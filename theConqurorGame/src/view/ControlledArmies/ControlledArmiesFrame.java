package view.ControlledArmies;

import units.*;
import view.ArmyButton.ArmyButton;

import view.UnitsButtons.UnitButton;
import view.tools.CloseButton;
import view.tools.MyButton;
import view.tools.MyFrame;
import view.UnitsButtons.AddUnitLabel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

 public class ControlledArmiesFrame extends MyFrame  implements ActionListener {

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

        private ArrayList<ArmyButton> cairoControlledArmies=new ArrayList<>();
        private ArrayList<ArmyButton> spartaControlledArmies=new ArrayList<>();
        private ArrayList<ArmyButton> romeControlledArmies=new ArrayList<>();

     private JLabel playerNameLabel=new JLabel();
     private JLabel treasuryLabel=new JLabel();
     private JLabel foodLabel=new JLabel();


        public ControlledArmiesFrame() {

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
            cairoName.setText("Controlled Armies");
            cairoName.setFont(new Font("NEW ROMAN",Font.BOLD,20));



            numberOfArmiesLabel.setBounds(50,250+170,250,70);
            numberOfArmiesLabel.setVisible(true);
            numberOfArmiesLabel.setText("Total Armies :"+ numberOfArmies);
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


        public void initiateArmyOfControlFrame(Army army, String cityName){
            ArmyButton tempButton=null;
            leftPanel.remove(addUnitLabel);
            if(numberOfArmies >x)
                return;

            tempButton=new ArmyButton(numberOfArmies+1,army.getCurrentStatus(),cityName,army.getUnits().size());
            tempButton.getArmyInfoLabel().getArmyNumberLabel().setText("Army : " + (numberOfArmies + 1));
            tempButton.getArmyInfoLabel().getStatusLabel().setText("Status " + Status.IDLE);
            tempButton.getArmyInfoLabel().getCityNameLabel().setText("City : " + cityName);

                switch (cityName.toLowerCase()){
                    case "sparta": spartaControlledArmies.add(tempButton);break;
                    case "cairo":cairoControlledArmies.add(tempButton);break;
                    case "rome": romeControlledArmies.add(tempButton);break;
                }
                controlledArmiesUnitsFrame.add(new ControlledArmiesUnitsFrame(army.getUnits().get(0)));// todo to put the unit in the army Frame

                if (!controlledArmiesOfAll.contains(army)){
                    leftPanel.add(tempButton);
                    armyButtons.add(tempButton);
                }
          controlledArmiesOfAll.add(army);

            numberOfArmies++;
        if (numberOfArmies<24)
            leftPanel.add(addUnitLabel);
            if (numberOfArmies !=25){

                numberOfArmiesLabel.setText("Total Armies :"+ numberOfArmies);}
            leftPanel.repaint();
            leftPanel.revalidate();
            this.reload();
        }
        public  void  relocateUnitToUnitFrame(UnitButton unitButton,int index){
          String cityName=  unitButton.getUnit().getParentArmy().getCurrentLocation();

          for (ArmyButton armyButton:armyButtons){
            if (cityName.equalsIgnoreCase(armyButton.getArmyInfoLabel().getCityName()))
                armyButton.setEnabled(true);
            else
                armyButton.setEnabled(false);
          }
          this.setVisible(true);

          this.getControlledArmiesUnitsFrame().get(index).addToArmyUnits(unitButton.getUnit());

          this.reload();

        }
        public void resetAllArmyButtons(){
            for (ArmyButton armyButton:armyButtons){
                armyButton.setEnabled(true);
            }
        }






        public void sendToMarchingFrame(Army army){
            if (!controlledArmiesOfAll.contains(army))
                return;
            int index= controlledArmiesOfAll.indexOf(army);
            ArmyButton armyButton= armyButtons.get(index);
            armyButtons.remove(index);
            controlledArmiesOfAll.remove(index);
            numberOfArmies--;
            numberOfArmiesLabel.setText("Total Units :"+ numberOfArmies);

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
            a1.getUnits().add(a3);

            ControlledArmiesFrame b=new ControlledArmiesFrame();

            b.initiateArmyOfControlFrame(a,"Cairo");
            b.initiateArmyOfControlFrame(a1,"Sparta");









        }



        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource()==closeButton){
                for (ControlledArmiesUnitsFrame frame : this.controlledArmiesUnitsFrame)
                    frame.resetTargetButtons();
                this.dispose();
            }
//            for (ArmyButton armyButton: armyButtons){
//                if (e.getSource()==armyButton){
//                    controlledArmiesUnitsFrame.get(armyButtons.indexOf(armyButton)).setVisible(true);
//                    this.dispose();
//                }
//            }
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

     public ArrayList<ArmyButton> getCairoControlledArmies() {
         return cairoControlledArmies;
     }

     public ArrayList<ArmyButton> getSpartaControlledArmies() {
         return spartaControlledArmies;
     }

     public ArrayList<ArmyButton> getRomeControlledArmies() {
         return romeControlledArmies;
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

     public void setCairoControlledArmies(ArrayList<ArmyButton> cairoControlledArmies) {
         this.cairoControlledArmies = cairoControlledArmies;
     }

     public void setSpartaControlledArmies(ArrayList<ArmyButton> spartaControlledArmies) {
         this.spartaControlledArmies = spartaControlledArmies;
     }

     public void setRomeControlledArmies(ArrayList<ArmyButton> romeControlledArmies) {
         this.romeControlledArmies = romeControlledArmies;
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
