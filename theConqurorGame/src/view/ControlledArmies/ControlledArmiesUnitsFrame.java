package view.ControlledArmies;

import units.*;
import view.ArmyButton.ArmyButton;
import view.BattleArenas.BattleArenas;
import view.tools.CloseButton;
import view.tools.MyButton;
import view.tools.MyFrame;
import view.UnitsButtons.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.concurrent.ConcurrentHashMap;

public class ControlledArmiesUnitsFrame extends MyFrame  implements ActionListener {
    JLabel statusName =new JLabel();
    private JPanel rightPanel=new JPanel();
    private JPanel leftPanel=new JPanel();
    private int x=9;
    private int i=0;

    private JLabel numberOfUnits=new JLabel();
    private ArrayList <UnitButton> unitButtons =new ArrayList<>();
    private ArrayList<Unit> theUnits =new ArrayList<>();//todo for getting the index of the unit and taking that index and remove it from unitButtons accordingly
    private CloseButton closeButton=new CloseButton();


    private boolean marchingFlag;
    private boolean besiegingFlag;
    private boolean idleFlag=true;
//idle status
    private MyButton attackCairoButton =new MyButton();
    private MyButton attackSpartaButton =new MyButton();
    private MyButton attackRomeButton =new MyButton();
    private String targetCityName;

    //marching status
    private JLabel distanceLabel=new JLabel();
    //when distance=0 it will be enabled
    private MyButton autoResolve=new MyButton();
    private MyButton manualbattle =new MyButton();
    private MyButton LaySiege=new MyButton();

    //marching & besiege
    private JLabel targetCityNameLabel =new JLabel();

    //besiege
    private JLabel turnsUnderSiegeLabel=new JLabel();

    private BattleArenas battleArenas;//todo battleArena

    private ConcurrentHashMap<String,ArrayList<UnitButton>> enemyUnitButtonsMap;

    private int distanceLeft=100;
    private boolean flag ;
    private boolean tookAction;
    private int turnsUnderSiege=3;

    public ControlledArmiesUnitsFrame(Unit unit) {
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
        this.addToArmyUnits(unit);
        manualbattle.addActionListener(this);
        this.reload();

    }

    public void setRightPanel(){
        rightPanel.setLayout(null);
        closeButton.setBounds(140,2,90,70);
        closeButton.addActionListener(this);
        rightPanel.add(closeButton);

        if (this.idleFlag){
            attackCairoButton.setVisible(true);
            attackCairoButton.setBounds(20,450,200,50);
            attackCairoButton.setForeground(Color.WHITE);
            attackCairoButton.setFont(new Font("NEW ROMAN",Font.BOLD,25));
            attackCairoButton.setBackground(Color.BLACK);
            attackCairoButton.setText("Target Cairo");
        rightPanel.add(attackCairoButton);

            attackRomeButton.setVisible(true);
            attackRomeButton.setBounds(20,510,200,50);
            attackRomeButton.setForeground(Color.WHITE);
            attackRomeButton.setFont(new Font("NEW ROMAN",Font.BOLD,25));
            attackRomeButton.setBackground(Color.BLACK);
            attackRomeButton.setText("Target Rome");
            rightPanel.add(attackRomeButton);

            attackSpartaButton.setVisible(true);
            attackSpartaButton.setBounds(20,570,200,50);
            attackSpartaButton.setForeground(Color.WHITE);
            attackSpartaButton.setFont(new Font("NEW ROMAN",Font.BOLD,25));
            attackSpartaButton.setBackground(Color.BLACK);
            attackSpartaButton.setText("Target Sparta");
            rightPanel.add(attackSpartaButton);

        }

        JLabel logoPic=new JLabel();
        logoPic.setIcon(new ImageIcon("THE_Conquror_Logo.Edited.jpg"));
        logoPic.setBounds(20,100,200,250);

        statusName.setBounds(20,250,250,250);
        statusName.setVisible(true);

        if (besiegingFlag)
        statusName.setText("Besieging Army");

        if (idleFlag)
            statusName.setText("Idle Army");
        if (marchingFlag)
        statusName.setText("Marching Army");

        statusName.setFont(new Font("NEW ROMAN",Font.BOLD,20));



        numberOfUnits.setBounds(50,300,250,250);
        numberOfUnits.setVisible(true);
        numberOfUnits.setText("Total Units :"+i);
        numberOfUnits.setFont(new Font("NEW ROMAN",Font.BOLD,20));


        rightPanel.add(numberOfUnits);
        rightPanel.add(statusName);
        rightPanel.add(logoPic);
        rightPanel.repaint();
        rightPanel.revalidate();

    }





    public void resetRightPanel(){
        if (besiegingFlag)
            statusName.setText("Besieging Army");

        if (idleFlag)
            statusName.setText("Idle Army");

        if (marchingFlag){
            statusName.setText("Marching Army");
            attackCairoButton.setVisible(false);
            attackSpartaButton.setVisible(false);
            attackRomeButton.setVisible(false);
            rightPanel.remove(attackCairoButton);
            rightPanel.remove(attackRomeButton);
            rightPanel.remove(attackSpartaButton);

            targetCityNameLabel.setVisible(true);
            targetCityNameLabel.setBounds(20,500,200,50);
            targetCityNameLabel.setForeground(Color.WHITE);
            targetCityNameLabel.setFont(new Font("NEW ROMAN",Font.BOLD,15));
            targetCityNameLabel.setBackground(Color.BLACK);
            targetCityNameLabel.setText("Target City Name :"+targetCityName);
            targetCityNameLabel.setOpaque(true);

            rightPanel.add(targetCityNameLabel);


            distanceLabel.setVisible(true);
            distanceLabel.setBounds(20,550+10,200,50);
            distanceLabel.setForeground(Color.WHITE);
            distanceLabel.setFont(new Font("NEW ROMAN",Font.BOLD,15));
            distanceLabel.setBackground(Color.BLACK);
            distanceLabel.setText("Distance to Target : "+ distanceLeft);
            distanceLabel.setOpaque(true);
            rightPanel.add(distanceLabel);
            for (UnitButton unitButton:unitButtons){
                unitButton.getUnitInfoLabel().getStatusLabel().setText("Marching");
            }


        }

        else if(besiegingFlag){
            attackCairoButton.setVisible(false);
            attackRomeButton.setVisible(false);
            attackSpartaButton.setVisible(false);
            rightPanel.remove(attackCairoButton);
            rightPanel.remove(attackSpartaButton);
            rightPanel.remove(attackRomeButton);


//            rightPanel.remove(distanceLabel);
//            rightPanel.remove(targetCityNameLabel);
//            targetCityNameLabel.setVisible(false);
//            distanceLabel.setVisible(false);

            turnsUnderSiegeLabel.setVisible(true);
            turnsUnderSiegeLabel.setBounds(20,650-100,200,50);
            turnsUnderSiegeLabel.setForeground(Color.WHITE);
            turnsUnderSiegeLabel.setFont(new Font("NEW ROMAN",Font.BOLD,17));
            turnsUnderSiegeLabel.setBackground(Color.BLACK);
            turnsUnderSiegeLabel.setText("Turns Under Siege : 3");
            turnsUnderSiegeLabel.setOpaque(true);
            rightPanel.add(turnsUnderSiegeLabel);
            autoResolve.setVisible(true);
            autoResolve.setBounds(20, 650, 200, 50);
            autoResolve.setForeground(Color.WHITE);
            autoResolve.setFont(new Font("NEW ROMAN", Font.BOLD, 15));
            autoResolve.setBackground(Color.BLACK);
            autoResolve.setText("Auto-Resolve ");
            autoResolve.setOpaque(true);
            rightPanel.add(autoResolve);

            manualbattle.setVisible(true);
            manualbattle.setBounds(20, 650+60, 200, 50);
            manualbattle.setForeground(Color.WHITE);
            manualbattle.setFont(new Font("NEW ROMAN", Font.BOLD, 15));
            manualbattle.setBackground(Color.BLACK);
            manualbattle.setText("Battle");
            manualbattle.setOpaque(true);
            rightPanel.add(manualbattle);

            for (UnitButton unitButton:unitButtons){
                unitButton.getUnitInfoLabel().getStatusLabel().setText("Besieging");
            }

            targetCityNameLabel.setVisible(true);
            targetCityNameLabel.setBounds(20,500,200,50);
            targetCityNameLabel.setForeground(Color.WHITE);
            targetCityNameLabel.setFont(new Font("NEW ROMAN",Font.BOLD,25));
            targetCityNameLabel.setBackground(Color.BLACK);
            targetCityNameLabel.setText("Target City Name :" + targetCityName);
            rightPanel.add(targetCityNameLabel);
        }
        rightPanel.repaint();
        rightPanel.revalidate();
            }
      public void takeAction(){
        turnsUnderSiege--;

        if (turnsUnderSiege==0){
            JOptionPane.showMessageDialog(null,"Battle or AutoResolve","Action Denied ",JOptionPane.WARNING_MESSAGE);
        }
        else {
        turnsUnderSiegeLabel.setText("Turns UnderSiege : "+turnsUnderSiege);
        }
      }


    public void destroy(){
        if (distanceLeft==0) {
            JOptionPane.showMessageDialog(null,"You have reached the enemy city, take an action Manual Battle, Auto Resolve or Lay Siege","Take Action",
                    JOptionPane.INFORMATION_MESSAGE);
            distanceLabel.setVisible(false);
            rightPanel.remove(distanceLabel);

            distanceLabel.setText("Distance to Target : "+ distanceLeft);
            autoResolve.setVisible(true);

            autoResolve.setBounds(20, 600+20, 200, 50);
            autoResolve.setForeground(Color.WHITE);
            autoResolve.setFont(new Font("NEW ROMAN", Font.BOLD, 15));
            autoResolve.setBackground(Color.BLACK);
            autoResolve.setText("Auto-Resolve ");
            autoResolve.setOpaque(true);
            rightPanel.add(autoResolve);

            LaySiege.setVisible(true);
            LaySiege.setBounds(20, 650 +30, 200, 50);
            LaySiege.setForeground(Color.WHITE);
            LaySiege.setFont(new Font("NEW ROMAN", Font.BOLD, 15));
            LaySiege.setBackground(Color.BLACK);
            LaySiege.setText("Lay Siege");
            LaySiege.setOpaque(true);
            rightPanel.add(LaySiege);

            manualbattle.setVisible(true);
            manualbattle.setBounds(20, 650+50+40, 200, 50);
            manualbattle.setForeground(Color.WHITE);
            manualbattle.setFont(new Font("NEW ROMAN", Font.BOLD, 15));
            manualbattle.setBackground(Color.BLACK);
            manualbattle.setText("Battle");
            manualbattle.setOpaque(true);
            rightPanel.add(manualbattle);

        }
    }
    public void updateDistance(){
        distanceLeft--;
        this.destroy();
    }


    public void addToArmyUnits(Unit unit){
        UnitButton tempButton=null;
        if(i>x)
            return;
        theUnits.add(unit);
        if(unit instanceof Archer) {
            tempButton=new UnitButton(Status.IDLE,unit.getLevel(),"Archer",unit.getCurrentSoldierCount(),unit.getMaxSoldierCount(),unit);
            if (!unitButtons.contains(tempButton)){
                leftPanel.add(tempButton);
                unitButtons.add(tempButton);}
        }
        else if (unit instanceof Infantry) {
            tempButton=new UnitButton(Status.IDLE,unit.getLevel(),"Infantry",unit.getCurrentSoldierCount(),unit.getMaxSoldierCount(),unit);
            if (!unitButtons.contains(tempButton)){
                leftPanel.add(tempButton);
                unitButtons.add(tempButton);}
        }
        else if (unit instanceof Cavalry) {
            tempButton=new UnitButton(Status.IDLE,unit.getLevel(),"Cavalry",unit.getCurrentSoldierCount(),unit.getMaxSoldierCount(),unit);
            if (!unitButtons.contains(tempButton)){
                leftPanel.add(tempButton);
                unitButtons.add(tempButton);}
        }
        i++;
        if (i!=x)
            numberOfUnits.setText("Total Units :"+i);

        leftPanel.revalidate();
        leftPanel.repaint();
        rightPanel.repaint();
        rightPanel.revalidate();
        this.reload();
    }


    public void updateToMarchingFrame(){
            this.marchingFlag=true;
            this.besiegingFlag=false;
            this.idleFlag=false;
            this.resetRightPanel();
            this.reload();
    }

    public void disableTargetButtons() {
        attackRomeButton.setVisible(false);
        attackSpartaButton.setVisible(false);
        attackRomeButton.setVisible(false);
    }

    public void resetTargetButtons() {
        attackRomeButton.setVisible(true);
        attackSpartaButton.setVisible(true);
        attackRomeButton.setVisible(true);
    }
    public void updateToBesiegingFrame(){
        this.marchingFlag=false;
        this.besiegingFlag=true;
        this.idleFlag=false;
        this.resetRightPanel();
        this.reload();
    }

    public static void main(String[] args) {
        Unit a=new Archer(2,2,2,2,2);
        Unit a1=new Archer(2,2,2,2,2);
        Unit a2=new Archer(1,2,2,2,2);
        Unit a3=new Archer(3,2,2,2,2);
        Unit a4=new Archer(2,2,2,2,2);
        Unit a5=new Archer(2,2,2,2,2);
        Unit a6=new Archer(2,2,2,2,2);
        ControlledArmiesUnitsFrame b=new ControlledArmiesUnitsFrame(a);
        b.addToArmyUnits(a1);
        b.addToArmyUnits(a2);
        b.addToArmyUnits(a3);
        b.addToArmyUnits(a2);
       // b.updateToMarchingFrame();
    }





    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==closeButton){
            this.dispose();
        }

        if (e.getSource() == manualbattle) {
            if (enemyUnitButtonsMap != null) {
                battleArenas = new BattleArenas(targetCityName.toLowerCase(), enemyUnitButtonsMap.get(targetCityName.toLowerCase()), unitButtons);
                //disableAllButtons();
                flag = true;
            }
        }
//        if (e.getSource()==manualbattle){
//            battleArenas=new BattleArenas(targetCityName,enemyUnitButtonsMap.get(targetCityName),unitButtons);
//        }
    }



    public JLabel getStatusName() {
        return statusName;
    }

    public void setStatusName(JLabel statusName) {
        this.statusName = statusName;
    }

    public JPanel getRightPanel() {
        return rightPanel;
    }

    public void setRightPanel(JPanel rightPanel) {
        this.rightPanel = rightPanel;
    }

    public JPanel getLeftPanel() {
        return leftPanel;
    }

    public void setLeftPanel(JPanel leftPanel) {
        this.leftPanel = leftPanel;
    }

    @Override
    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }

    public JLabel getNumberOfUnits() {
        return numberOfUnits;
    }

    public void setNumberOfUnits(JLabel numberOfUnits) {
        this.numberOfUnits = numberOfUnits;
    }

    public ArrayList<UnitButton> getUnitButtons() {
        return unitButtons;
    }

    public void setUnitButtons(ArrayList<UnitButton> unitButtons) {
        this.unitButtons = unitButtons;
    }

    public ArrayList<Unit> getTheUnits() {
        return theUnits;
    }

    public void setTheUnits(ArrayList<Unit> theUnits) {
        this.theUnits = theUnits;
    }

    public CloseButton getCloseButton() {
        return closeButton;
    }

    public void setCloseButton(CloseButton closeButton) {
        this.closeButton = closeButton;
    }

    public boolean isMarchingFlag() {
        return marchingFlag;
    }

    public void setMarchingFlag(boolean marchingFlag) {
        this.marchingFlag = marchingFlag;
    }

    public boolean isBesiegingFlag() {
        return besiegingFlag;
    }

    public void setBesiegingFlag(boolean besiegingFlag) {
        this.besiegingFlag = besiegingFlag;
    }

    public boolean isIdleFlag() {
        return idleFlag;
    }

    public void setIdleFlag(boolean idleFlag) {
        this.idleFlag = idleFlag;
    }

    public MyButton getAttackCairoButton() {
        return attackCairoButton;
    }

    public void setAttackCairoButton(MyButton attackCairoButton) {
        this.attackCairoButton = attackCairoButton;
    }

    public MyButton getAttackSpartaButton() {
        return attackSpartaButton;
    }

    public void setAttackSpartaButton(MyButton attackSpartaButton) {
        this.attackSpartaButton = attackSpartaButton;
    }

    public MyButton getAttackRomeButton() {
        return attackRomeButton;
    }

    public void setAttackRomeButton(MyButton attackRomeButton) {
        this.attackRomeButton = attackRomeButton;
    }

    public String getTargetCityName() {
        return targetCityName;
    }

    public void setTargetCityName(String targetCityName) {
        this.targetCityName = targetCityName;
    }

    public JLabel getDistanceLabel() {
        return distanceLabel;
    }

    public void setDistanceLabel(JLabel distanceLabel) {
        this.distanceLabel = distanceLabel;
    }

    public MyButton getAutoResolve() {
        return autoResolve;
    }

    public void setAutoResolve(MyButton autoResolve) {
        this.autoResolve = autoResolve;
    }

    public MyButton getManualbattle() {
        return manualbattle;
    }

    public void setManualbattle(MyButton manualbattle) {
        this.manualbattle = manualbattle;
    }

    public MyButton getLaySiege() {
        return LaySiege;
    }

    public void setLaySiege(MyButton laySiege) {
        LaySiege = laySiege;
    }

    public JLabel getTargetCityNameLabel() {
        return targetCityNameLabel;
    }

    public void setTargetCityNameLabel(JLabel targetCityNameLabel) {
        this.targetCityNameLabel = targetCityNameLabel;
    }

    public JLabel getTurnsUnderSiegeLabel() {
        return turnsUnderSiegeLabel;
    }

    public void setTurnsUnderSiegeLabel(JLabel turnsUnderSiegeLabel) {
        this.turnsUnderSiegeLabel = turnsUnderSiegeLabel;
    }

    public BattleArenas getBattleArenas() {
        return battleArenas;
    }

    public void setBattleArenas(BattleArenas battleArenas) {
        this.battleArenas = battleArenas;
    }

    public ConcurrentHashMap<String, ArrayList<UnitButton>> getEnemyUnitButtonsMap() {
        return enemyUnitButtonsMap;
    }

    public void setEnemyUnitButtonsMap(ConcurrentHashMap<String, ArrayList<UnitButton>> enemyUnitButtonsMap) {
        this.enemyUnitButtonsMap = enemyUnitButtonsMap;
    }

    public int getDistanceLeft() {
        return distanceLeft;
    }

    public void setDistanceLeft(int distanceLeft) {
        this.distanceLeft = distanceLeft;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public boolean isTookAction() {
        return tookAction;
    }

    public void setTookAction(boolean tookAction) {
        this.tookAction = tookAction;
    }

    public int getTurnsUnderSiege() {
        return turnsUnderSiege;
    }

    public void setTurnsUnderSiege(int turnsUnderSiege) {
        this.turnsUnderSiege = turnsUnderSiege;
    }
}
