package view.launchpage;

import view.tools.*;
import view.worldmap.WorldMap;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class LaunchPage extends MyFrame implements ActionListener {
    private TextField nameArea;
    private String namePlayer;//Todo it's the player's name
    private String cityName;//Todo it's the player's city
    private MyLabel label;
    private JPanel mainPanel;
    private JRadioButton spartaRadio;
    private JRadioButton cairoRadio;
    private JRadioButton romeRadio;
    private ArrayList charachter;
    private Choosed choosed=new Choosed();
    private MyToggledButton King;
    private MyToggledButton Queen;
    private MyButton continueButton;
    private boolean Gender;
    private WorldMap worldMap;
    private double playerTreasury;
    private double playerFood;
    public LaunchPage(){

        charachter=new ArrayList();
        ImageIcon image =new ImageIcon("THE_Conquror_Logo.jpeg");
        this.setIconImage(image.getImage());
        label=new MyLabel();
        label.setHorizontalAlignment(JLabel.CENTER);
        mainPanel=new JPanel();

        mainPanel.setBounds(320,380,1100,500);
        mainPanel.setVisible(true);
        mainPanel.setLayout(null);
        mainPanel.setOpaque(false);
         this.add(label);

        label.setIcon(image);
        this.PlayerName();
        this.Character();
        this.TheConquror();

        label.add(mainPanel);
        this.radioButtons();
        this.continueButton();
        this.reload();
    }
    public void continueButton(){
        continueButton =new MyButton();
        continueButton.setText("Start Game");
//       continueButton.addActionListener(this);
        continueButton.setFont(new Font("NEW ROMAN",Font.BOLD,35));
        continueButton.setBackground(Color.BLACK);
        continueButton.setBounds(350,320,250,40);
        mainPanel.add(continueButton);

    }
    public void radioButtons(){ //TODO

         cairoRadio=new JRadioButton("Cairo");
         cairoRadio.setPreferredSize(new Dimension(100,40));
        cairoRadio.setFont(new Font("NEW ROMAN",Font.BOLD,20));

        cairoRadio.addActionListener(this);
        romeRadio=new JRadioButton("Rome");
        romeRadio.addActionListener(this);
        romeRadio.setPreferredSize(new Dimension(100,40));
        romeRadio.setFont(new Font("NEW ROMAN",Font.BOLD,20));
        spartaRadio=new JRadioButton("Sparta");
        spartaRadio.addActionListener(this);
        spartaRadio.setPreferredSize(new Dimension(100,40));
        spartaRadio.setFont(new Font("NEW ROMAN",Font.BOLD,20));
        ButtonGroup group=new ButtonGroup();
        group.add(cairoRadio);
        group.add(romeRadio);
        group.add(spartaRadio);


JPanel tempPanel=new JPanel();
        tempPanel.setBounds(700,0,350,40);
        tempPanel.setVisible(true);

        tempPanel.setOpaque(false);
        tempPanel.add(cairoRadio);
        tempPanel.add(romeRadio);
        tempPanel.add(spartaRadio);
        mainPanel.add(tempPanel);
//        nameArea.setBounds(200,0,500,40);
    }

    public void  TheConquror(){
        MyLabel Templabel=new MyLabel();
        Templabel.setText("The Conqueror");
        Templabel.setForeground(new Color(0xA9FDFBFB, true)); // to color the Text
        Templabel.setFont(new Font("New Roman",Font.BOLD,130));
        Templabel.setOpaque(false);

        Templabel.setVerticalTextPosition(JLabel.CENTER);
        Templabel.setBounds(300,150,1300,200);
        label.add(Templabel);
    }

//    public JPanel cityButtonsPanel(){
//
//        JPanel cityFeatures=new JPanel();
//        cityFeatures.setVisible(true);
//        cityFeatures.setLayout(new FlowLayout(FlowLayout.CENTER,10,10));
//        cityFeatures.setSize(100,100);
//        cityFeatures.setOpaque(false);
//        JButton Idle=new JButton();
//        JButton Marching=new JButton();
//        JButton Beseiging=new JButton();
//        JButton Building=new JButton();
//
//        Building.setPreferredSize(new Dimension(45,30));
//        Idle.setPreferredSize(new Dimension(45,30));
//        Marching.setPreferredSize(new Dimension(45,30));
//        Beseiging.setPreferredSize(new Dimension(45,30));
//
//        cityFeatures.add(Building);
//        cityFeatures.add(Idle);
//        cityFeatures.add(Marching);
//        cityFeatures.add(Beseiging);
//
//        cityButtons.add(Building);
//        cityButtons.add(Idle);
//        cityButtons.add(Marching);
//        cityButtons.add(Beseiging);
//
//return cityFeatures;
//    }

    public MyButton getContinueButton() {
        return continueButton;
    }

    public WorldMap getWorldMap() {
        return worldMap;
    }

    public void Character(){
        King=new MyToggledButton();
        King.setVisible(true);
        King.addActionListener(this);
        ImageIcon image2=new ImageIcon("Kingedited.jpg");
        King.setIcon(image2);
        King.setBounds(70,100,300,200);//imp
        King.setVerticalAlignment(JButton.CENTER);

        King.setEnabled(true);
//    King.setHorizontalAlignment(JButton.CENTER);

        King.setLayout(null);

        charachter.add(King);

         Queen=new MyToggledButton();
         Queen.addActionListener(this);
        Queen.setVisible(true);
        ImageIcon image3=new ImageIcon("QueenEdited.jpg");
        Queen.setIcon(image3);
        Queen.setBounds(470,100,300,200);//imp

        Queen.setVerticalAlignment(JButton.CENTER);
        Queen.setHorizontalAlignment(JButton.CENTER);
        Queen.setLayout(null);
        charachter.add(Queen);
        mainPanel.add(King);
        mainPanel.add(Queen);
    }

    public void PlayerName(){
         nameArea=new TextField();
        nameArea.setVisible(true);
        nameArea.setText("Please Enter Your Name");
        nameArea.setFont(new Font("New Roman",Font.BOLD,30));
        MyTextArea playerName=new MyTextArea();
        playerName.setEditable(false);
        playerName.setVisible(true);
        playerName.setText("Player Name");
        playerName.setEditable(false);
        playerName.setForeground(Color.WHITE);
        playerName.setBorder(null);
        playerName.setBounds(0,0,190,40);
        nameArea.setBounds(200,0,500,40);
        mainPanel.add(playerName);
        mainPanel.add(nameArea);

    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==King){
            King.add(choosed);
            Queen.remove(choosed);
            this.reload();
        }
        else if(e.getSource()==Queen) {
            Queen.add(choosed);
            King.remove(choosed);
            this.reload();

        }
    }

    public String getNamePlayer() {
        return namePlayer;
    }

    public String getCityName() {
        return cityName;
    }

    public TextField getNameArea() {
        return nameArea;
    }

    public void setNameArea(TextField nameArea) {
        this.nameArea = nameArea;
    }

    public void setNamePlayer(String namePlayer) {
        this.namePlayer = namePlayer;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public JRadioButton getSpartaRadio() {
        return spartaRadio;
    }

    public void setSpartaRadio(JRadioButton spartaRadio) {
        this.spartaRadio = spartaRadio;
    }

    public JRadioButton getCairoRadio() {
        return cairoRadio;
    }

    public void setCairoRadio(JRadioButton cairoRadio) {
        this.cairoRadio = cairoRadio;
    }

    public JRadioButton getRomeRadio() {
        return romeRadio;
    }

    public void setRomeRadio(JRadioButton romeRadio) {
        this.romeRadio = romeRadio;
    }

    public MyToggledButton getKing() {
        return King;
    }

    public void setKing(MyToggledButton king) {
        King = king;
    }

    public MyToggledButton getQueen() {
        return Queen;
    }

    public void setQueen(MyToggledButton queen) {
        Queen = queen;
    }

    public void setContinueButton(MyButton continueButton) {
        this.continueButton = continueButton;
    }

    public boolean isGender() {
        return Gender;
    }

    public void setGender(boolean gender) {
        Gender = gender;
    }

    public void setWorldMap(WorldMap worldMap) {
        this.worldMap = worldMap;
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
}
