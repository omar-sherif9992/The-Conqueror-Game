package view.worldmap;

import view.tools.MyButton;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;

public class PlayerLabel extends JLabel {
   private JLabel turnslabel;
   private JLabel character;
   private String playerName;
   private double playerTreasury;
   private double playerFood;
   private JLabel playerLabel=new JLabel();
    private JLabel treasuryLabel=new JLabel();
    private JLabel foodLabel=new JLabel();
   private boolean Gender;
    private MyButton endTurn =new MyButton();

    public PlayerLabel(boolean Gender, String playerName, double playerTreasury, double playerFood){
     // MyFrame f=new MyFrame();
        this.playerTreasury= playerTreasury;
        this.playerFood=playerFood;
        this.Gender=Gender;
        this.playerName=playerName;
        this.setVisible(true);
        this.setOpaque(false); //Todo
        this.setLayout(null);


        turnsSlider();
        createEndTurnButton();
        createPlayerLabel();
        createTreasuryLabel();
        createFoodLabel();
     //this.setBounds(200,-100,500,500);
     //   f.setBounds(200,200,1100,600);
   //    f.add(this);


//f.reload();
this.repaint();
this.revalidate();
    }

    public static void main(String[] args) {
        new PlayerLabel(true,"cc",22,22);
    }




    public void createEndTurnButton(){
        endTurn.setVisible(true);
        endTurn.setBounds(1330,220,200,90);
        endTurn.setForeground(Color.WHITE);
        endTurn.setBackground(Color.BLACK);

       //Todo endTurn.addActionListener();
        endTurn.setFont(new Font("New Roman",Font.BOLD,35));

        endTurn.setText("End Turn");
        this.add(endTurn);



    }
    public JLabel createPlayerLabel(){

        playerLabel=new JLabel();
        playerLabel.setVisible(true);

        playerLabel.setText("Player Name: "+playerName   );

        playerLabel.setBounds(400,70,400,100);

        playerLabel.setOpaque(false);

        playerLabel.setFont(new Font("New Roman",Font.BOLD,25));
//        Font font = playerLabel.getFont();
//        Map attributes = font.getAttributes();
//        attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
//        playerLabel.setFont(font.deriveFont(attributes));
        playerLabel.setForeground(Color.BLACK);

        this.add(playerLabel);
        return playerLabel;

    }
    public void createTreasuryLabel(){

        treasuryLabel=new JLabel();
        treasuryLabel.setVisible(true);

        treasuryLabel.setText("Treasury :  \uD83D\uDCB0"+playerTreasury );
        treasuryLabel.setBounds(400,120,400,100);
        treasuryLabel.setOpaque(false);
        treasuryLabel.setForeground(Color.BLACK);
        treasuryLabel.setFont(new Font("New Roman",Font.BOLD,25));
//        Font font = treasuryLabel.getFont();
//        Map attributes = font.getAttributes();
//        attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
//        treasuryLabel.setFont(font.deriveFont(attributes));
        this.add(treasuryLabel);

    }
    public void createFoodLabel(){

        foodLabel=new JLabel();
        foodLabel.setVisible(true);
        foodLabel.setText("Food :"+playerFood  );
        foodLabel.setBounds(400,170,400,100);
        foodLabel.setOpaque(false);
        foodLabel.setForeground(Color.BLACK);
        foodLabel.setFont(new Font("New Roman",Font.BOLD,25));
//        Font font = foodLabel.getFont();
//        Map attributes = font.getAttributes();
//        attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
//        foodLabel.setFont(font.deriveFont(attributes));

        foodLabel.setForeground(Color.BLACK);
        this.add(foodLabel);

    }

    public  void turnsSlider(){
        turnslabel=new JLabel();
        turnslabel.setVisible(true);

        turnslabel.setBounds(400,250,120,35);
        turnslabel.setForeground(Color.BLACK);
        turnslabel.setFont(new Font("New Roman",Font.BOLD,25));
        turnslabel.setText(" 1 Turns");
        this.Character();
        this.add(turnslabel);

    }
    public void Character(){
        character=new JLabel();
        character.setVisible(true);
        character.setOpaque(false);

        if(Gender){
            ImageIcon image2=new ImageIcon("Kingedited.jpg");
            character.setIcon(image2);

        }
        else {
            ImageIcon image3=new ImageIcon("QueenEdited.jpg");
            character.setIcon(image3);
        }
        character.setBounds(70,90,300,200);//imp
        character.setVerticalAlignment(JButton.CENTER);

        this.add(character);


    }

//    public static void main(String[] args) {
//        new PlayerPanel(false,"ssss",900,10000);
//    }


    public JLabel getTurnslabel() {
        return turnslabel;
    }

    public void setTurnslabel(JLabel turnslabel) {
        this.turnslabel = turnslabel;
    }


    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public double getPlayerTreasury() {
        return playerTreasury;
    }

    public void setPlayerTreasury(double playerTreasury) {
        this.playerTreasury = playerTreasury;
    }

    public double getPlayerFood() {
        return playerFood;
    }

    public void setPlayerFood(double playerFood) {
        this.playerFood = playerFood;
    }

    public JLabel getPlayerLabel() {
        return playerLabel;
    }

    public void setPlayerLabel(JLabel playerLabel) {
        this.playerLabel = playerLabel;
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

    public boolean isGender() {
        return Gender;
    }

    public void setGender(boolean gender) {
        Gender = gender;
    }

    public MyButton getEndTurn() {
        return endTurn;
    }

    public void setEndTurn(MyButton endTurn) {
        this.endTurn = endTurn;
    }
}
