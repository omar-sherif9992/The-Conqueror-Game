package view.BattleArenas;

import units.Cavalry;
import units.Status;
import units.Unit;
import view.UnitsButtons.UnitButton;
import view.tools.MyButton;
import view.tools.MyFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class BattleArenas extends MyFrame implements ActionListener {
    private ArrayList<UnitButton>enemyUnitButtons;
    private ArrayList<UnitButton> attackUnitButtons;
    private String targetCityName;
    private BoardLabel boardLabel;
    private PlayerArmyPanel playerArmyPanel;
    private LogPanel logPanel;
    private EnemyArmyPanel enemyArmyPanel;
    private boolean playerChosed=true;
    private boolean enemyChosed=true;
    private boolean battleFlag=true;
    private boolean whoWon;
    private MyButton battleFinished=new MyButton();

    public BattleArenas(String targetCityName,ArrayList<UnitButton> enemyUnitButtons,ArrayList<UnitButton> attackUnitButtons) {
        this.enemyUnitButtons = enemyUnitButtons;
        this.attackUnitButtons = attackUnitButtons;
        boardLabel =new BoardLabel();
        this.targetCityName = targetCityName;
        this.setLayout(null);
        this.setVisible(true);

        battleFinished.setVisible(false);
        battleFinished.addActionListener(this);
        battleFinished.setText("Battle Finished");
        battleFinished.setBackground(Color.BLACK);
        battleFinished.setOpaque(true);
        battleFinished.setForeground(Color.WHITE);
        battleFinished.setBounds(550,500,300,200);
        battleFinished.setFont(new Font("NEW ROMAN",Font.BOLD,25));
        this.add(battleFinished);
        enemyArmyPanel=new EnemyArmyPanel(enemyUnitButtons);
        playerArmyPanel=new PlayerArmyPanel(attackUnitButtons);
        logPanel=new LogPanel(targetCityName);
        this.add(enemyArmyPanel);
        this.add(playerArmyPanel);
        this.add(boardLabel);
        this.add(logPanel);
        ImageIcon image=null;
        switch (targetCityName.toLowerCase()){
            case "cairo":image=new ImageIcon("cairoBattleArenaEditedk.jpg");//todo fix image
            case "sparta":image=new ImageIcon("BattleAreaSpartaEditedk.jpg");
            case "rome":image=new ImageIcon("SpartaBattleArenaEditedk.jpg");
        }
        boardLabel.setIcon(image);

        for (UnitButton unitButton: attackUnitButtons){
            unitButton.addActionListener(this);
        }
        for (UnitButton unitButton:enemyUnitButtons){
            unitButton.addActionListener(this);
        }

logPanel.getBattleBegins().addActionListener(this);
this.reload();
    }

    public static void main(String[] args) {
        UnitButton a=new UnitButton(Status.IDLE, 1, "cavalry", 33, 33, new Cavalry(1,33,2,2,2));
        UnitButton a1=new UnitButton(Status.IDLE, 1, "cavalry", 33, 33, new Cavalry(1,33,2,2,2));
        UnitButton a2=new UnitButton(Status.IDLE, 1, "cavalry", 33, 33, new Cavalry(1,33,2,2,2));
        UnitButton a3=new UnitButton(Status.IDLE, 1, "cavalry", 33, 33, new Cavalry(1,33,2,2,2));
        UnitButton a4=new UnitButton(Status.IDLE, 1, "cavalry", 33, 33, new Cavalry(1,33,2,2,2));

        ArrayList <UnitButton>enemy=new ArrayList();
        ArrayList <UnitButton>attack=new ArrayList();
        enemy.add(a3);
        enemy.add(a4);

        attack.add(a);
        enemy.add(a1);
        attack.add(a2);


        BattleArenas g=new BattleArenas("cairo",enemy,attack);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (playerChosed){

            for (UnitButton unitButton: attackUnitButtons){
                if (e.getSource()==unitButton){
                    playerArmyPanel.remove(unitButton);
                    boardLabel.AddAttackPlayerUnit(unitButton);
                    this.reload();
                    playerChosed=false;
                    playerArmyPanel.getPlayerUnitButtons().remove(unitButton);
                    break;
                }

            }

        }


        if (enemyChosed) {
            for (UnitButton unitButton : enemyUnitButtons) {
                if (e.getSource() == unitButton) {
                    enemyArmyPanel.remove(unitButton);
                    boardLabel.AddEnemyUnit(unitButton);
                    enemyChosed=false;
                    enemyArmyPanel.getEnemyUnitButtons().remove(unitButton);

                    this.reload();
                    break;
                }

            }

        }




//        if (!enemyChosed && !playerChosed){

        if (e.getSource()==logPanel.getBattleBegins()){
            if (boardLabel.getAttackUnit()!=null && boardLabel.getEnemyUnit()!=null){

                UnitButton damagedUnit= boardLabel.battleBegins();
                if (boardLabel.isPlayerFlag()){
                    playerArmyPanel.add(damagedUnit);
                    playerArmyPanel.getPlayerUnitButtons().add(damagedUnit);
                }
                else {
                    enemyArmyPanel.add(damagedUnit);
                    enemyArmyPanel.getEnemyUnitButtons().add(damagedUnit);
                }
                logPanel.getLogPane().setText(boardLabel.getMessage());
                boardLabel.setMessage("");
                if (enemyArmyPanel.getEnemyUnitButtons().isEmpty()){
                    JOptionPane.showMessageDialog(null,"you Won","Hooray",JOptionPane.PLAIN_MESSAGE);
                    battleFinished.setVisible(true);
                    whoWon=true;


                }
                if (playerArmyPanel.getPlayerUnitButtons().isEmpty()){
                    JOptionPane.showMessageDialog(null,"you lost","cry",JOptionPane.PLAIN_MESSAGE);
                    battleFinished.setVisible(true);
                    whoWon=false;

                }
            }
            else {
                JOptionPane.showMessageDialog(null,"Must choose the units","Action Denied",JOptionPane.WARNING_MESSAGE);
            }
            playerChosed=true;
            enemyChosed=true;
        }

        if (e.getSource() == battleFinished) {
            this.dispose();
        }

    }


    public ArrayList<UnitButton> getEnemyUnitButtons() {
        return enemyUnitButtons;
    }

    public void setEnemyUnitButtons(ArrayList<UnitButton> enemyUnitButtons) {
        this.enemyUnitButtons = enemyUnitButtons;
    }

    public ArrayList<UnitButton> getAttackUnitButtons() {
        return attackUnitButtons;
    }

    public void setAttackUnitButtons(ArrayList<UnitButton> attackUnitButtons) {
        this.attackUnitButtons = attackUnitButtons;
    }

    public String getTargetCityName() {
        return targetCityName;
    }

    public void setTargetCityName(String targetCityName) {
        this.targetCityName = targetCityName;
    }

    public BoardLabel getBoardLabel() {
        return boardLabel;
    }

    public void setBoardLabel(BoardLabel boardLabel) {
        this.boardLabel = boardLabel;
    }

    public PlayerArmyPanel getPlayerArmyPanel() {
        return playerArmyPanel;
    }

    public void setPlayerArmyPanel(PlayerArmyPanel playerArmyPanel) {
        this.playerArmyPanel = playerArmyPanel;
    }

    public LogPanel getLogPanel() {
        return logPanel;
    }

    public void setLogPanel(LogPanel logPanel) {
        this.logPanel = logPanel;
    }

    public EnemyArmyPanel getEnemyArmyPanel() {
        return enemyArmyPanel;
    }

    public void setEnemyArmyPanel(EnemyArmyPanel enemyArmyPanel) {
        this.enemyArmyPanel = enemyArmyPanel;
    }

    public boolean isPlayerChosed() {
        return playerChosed;
    }

    public boolean isEnemyChosed() {
        return enemyChosed;
    }

    public boolean isBattleFlag() {
        return battleFlag;
    }

    public boolean isWhoWon() {
        return whoWon;
    }

    public MyButton getBattleFinished() {
        return battleFinished;
    }
}
