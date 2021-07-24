package view.BattleArenas;

import exceptions.FriendlyFireException;
import units.Unit;
import view.UnitsButtons.UnitButton;

import javax.swing.*;
import java.awt.*;

public class BoardLabel extends JLabel {
    private UnitButton attackUnitButton;
    private UnitButton enemyUnitButton;
    private Unit attackUnit;
    private Unit enemyUnit;
    private String message;
    private boolean playerFlag;


    public BoardLabel(){
        this.setVisible(true);
        this.setOpaque(true);
        this.setBackground(Color.WHITE);
        this.setBounds(0,300,1200,400);

    }

    public void AddAttackPlayerUnit(UnitButton unitButton){

        this.setBorder(BorderFactory.createLineBorder(Color.BLACK,4));
        attackUnitButton=unitButton;
        attackUnit=attackUnitButton.getUnit();
        attackUnitButton.setVisible(true);
        attackUnitButton.setBounds(400,250,400,150);
        this.add(unitButton);
        this.repaint();
        this.revalidate();


    }

    public void RemoveDiedPlayerUnit(){
        this.remove(attackUnitButton);

    }


    public  void updateAttackPlayerUnit(int oldSoldierCount){
       message+=" Enemy Unit attacked Attack Unit \n Attack Unit lost "+(oldSoldierCount-attackUnit.getCurrentSoldierCount())+" \n";

        if (attackUnit.getCurrentSoldierCount()<=0)
            RemoveDiedPlayerUnit();
    }

    public  void AddEnemyUnit(UnitButton unitButton){
        enemyUnitButton=unitButton;
        enemyUnit=enemyUnitButton.getUnit();
        enemyUnitButton.setVisible(true);
        enemyUnitButton.setBounds(400,0,400,150);
        this.add(enemyUnitButton);
        this.repaint();
        this.revalidate();
    }

    public void RemoveEnemyUnit(){
        this.remove(enemyUnitButton);
    }

    public UnitButton battleBegins()  {
        boolean attackTurn=true;
        int old=0;

        while (enemyUnit.getCurrentSoldierCount()>0 && attackUnit.getCurrentSoldierCount()>0){

                if(attackTurn){
                    try {
                        old=enemyUnit.getCurrentSoldierCount();
                        System.out.println(enemyUnit.getCurrentSoldierCount());
                    attackUnit.attack(enemyUnit);
                    enemyUnitButton.getUnitInfoLabel().getSoldierCountLabel().setText("Health :"+enemyUnit.getCurrentSoldierCount());
                    updateEnemyUnit(old);
                }
                catch (FriendlyFireException e){
                        JOptionPane.showMessageDialog(null,e.getMessage(),"Action Denied",JOptionPane.WARNING_MESSAGE);
                }
                }
                else {
                    try{
                        old=attackUnit.getCurrentSoldierCount();
                    enemyUnit.attack(attackUnit);
                    updateAttackPlayerUnit(old);
                        attackUnitButton.getUnitInfoLabel().getSoldierCountLabel().setText("Health :"+attackUnit.getCurrentSoldierCount());

                    }
                    catch (FriendlyFireException e){
                        JOptionPane.showMessageDialog(null,e.getMessage(),"Action Denied",JOptionPane.WARNING_MESSAGE);
                    }
                }


                attackTurn=!attackTurn;
            }
        System.out.println("end");
        this.revalidate();
        this.repaint();
        if (attackUnit.getCurrentSoldierCount()==0){
            playerFlag=false;
            UnitButton enButton=enemyUnitButton;
            enemyUnit=null;
            attackUnit=null;
            attackUnitButton=null;
            enemyUnitButton=null;
            return enButton;
        }
        else {
            playerFlag=true;
            UnitButton enButton=attackUnitButton;
            enemyUnit=null;
            attackUnit=null;
            attackUnitButton=null;
            enemyUnitButton=null;
            return enButton;
        }

        }


    public  void updateEnemyUnit(int oldSoldierCount){
        message+=" Attack Unit attacked Enemy Unit \n Enemy Unit lost "+(oldSoldierCount-enemyUnit.getCurrentSoldierCount())+" \n";
        if (enemyUnit.getCurrentSoldierCount()<=0)
            RemoveEnemyUnit();
    }


    public UnitButton getAttackUnitButton() {
        return attackUnitButton;
    }

    public void setAttackUnitButton(UnitButton attackUnitButton) {
        this.attackUnitButton = attackUnitButton;
    }

    public UnitButton getEnemyUnitButton() {
        return enemyUnitButton;
    }

    public void setEnemyUnitButton(UnitButton enemyUnitButton) {
        this.enemyUnitButton = enemyUnitButton;
    }

    public Unit getAttackUnit() {
        return attackUnit;
    }

    public void setAttackUnit(Unit attackUnit) {
        this.attackUnit = attackUnit;
    }

    public Unit getEnemyUnit() {
        return enemyUnit;
    }

    public void setEnemyUnit(Unit enemyUnit) {
        this.enemyUnit = enemyUnit;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isPlayerFlag() {
        return playerFlag;
    }

    public void setPlayerFlag(boolean playerFlag) {
        this.playerFlag = playerFlag;
    }
}
