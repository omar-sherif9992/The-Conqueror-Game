package view.worldmap;

import view.Camp.MarchingCamp;
import view.ControlledArmies.BesiegingArmiesFrame;
import view.ControlledArmies.ControlledArmiesFrame;
import view.ControlledArmies.ControlledArmiesUnitsFrame;
import view.ControlledArmies.MarchingArmiesFrame;
import view.DefendingArmyCountries.DefendingArmiesFrame;
import view.UnitsButtons.UnitButton;
import view.tools.MyButton;
import view.tools.MyFrame;

import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

public class WorldMap extends MyFrame implements ActionListener {
    private String firstSelectedCity;
    private JLabel mapLabel;

    private MyButton Cairo;
    private MyButton Rome;
    private MyButton Sparta;

    private CairoFrame cairoFrame;
    private SpartaFrame spartaFrame;
    private RomeFrame romeFrame;



    private MyButton cairoAttack;
    private MyButton romeAttack;
    private MyButton spartaAttack;

    private DefendingArmiesFrame defendingArmiesFrame;
    private ControlledArmiesFrame controlledArmiesFrame;
    private BesiegingArmiesFrame besiegingArmiesFrame;
    private MarchingArmiesFrame marchingArmiesFrame;



    private boolean cairoFlag;
    private boolean romeFlag;
    private boolean spartaFlag;
    private PlayerLabel playerLabel;
    boolean Gender;
    String namePlayer;
    double playerTreasury;
    double playerFood;

    private MyButton myControlledArmiesButton=new MyButton();
    private MyButton myDefendingArmiesButton=new MyButton();
    private MyButton myMarchingArmiesButton=new MyButton();
    private MyButton myBesiegingArmiesButton=new MyButton();

    private ConcurrentHashMap<String, ArrayList<UnitButton>> enemyDefendingArmy;

    public WorldMap(String firstCountrySelected,boolean Gender,String namePlayer,double playerTreasury,double playerFood, ConcurrentHashMap<String,ArrayList<UnitButton>> enemyDefendingArmy){
       this.enemyDefendingArmy=enemyDefendingArmy;
        myControlledArmiesButton.addActionListener(this);
        myBesiegingArmiesButton.addActionListener(this);
        myMarchingArmiesButton.addActionListener(this);
        try {
            File file = new File("Opening Credits _ Game of Thrones _ Season 8 (HBO).wav");
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(file);
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        }
        catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            System.out.println(e.getMessage());
        }
        playerLabel=new PlayerLabel(Gender,namePlayer,playerTreasury, playerFood);
        playerLabel.setBounds(6,500,1530,450);
        mapLabel=new JLabel();
        ImageIcon image=new ImageIcon("newMapEdited.jpg");
        mapLabel.setIcon(image);
        mapLabel.setOpaque(false);
        mapLabel.setVisible(true);
        mapLabel.setVerticalAlignment(JLabel.CENTER);
        mapLabel.setHorizontalAlignment(JLabel.CENTER);
        mapLabel.setBounds(6,-5,1530,850);
        mapLabel.setLayout(null);


        this.firstSelectedCity=firstCountrySelected;
        cairoFrame=new CairoFrame(Gender,namePlayer,playerTreasury, playerFood);
        spartaFrame=new SpartaFrame(Gender,namePlayer,playerTreasury, playerFood);
        romeFrame=new RomeFrame(Gender,namePlayer,playerTreasury, playerFood);

        Cairo=new MyButton();
        Cairo.setText("Cairo");
        Cairo.setBackground(Color.BLACK);
        Cairo.setForeground(new Color(255,230,200));
        Cairo.setFont(new Font("NEW ROMAN",Font.BOLD,35));
        Cairo.addActionListener(this);

        cairoAttack=new MyButton();
        cairoAttack.setFont(new Font("NEW ROMAN",Font.BOLD,35));
        cairoAttack.setBackground(Color.BLACK);
        cairoAttack.addActionListener(this);


        Rome=new MyButton();
        Rome.setText("Rome");
        Rome.setFont(new Font("NEW ROMAN",Font.BOLD,35));
        Rome.setBackground(Color.BLACK);
        Rome.setForeground(new Color(255,230,200));
        Rome.addActionListener(this);

        romeAttack=new MyButton();
        romeAttack.setBackground(Color.BLACK);
        romeAttack.addActionListener(this);
        romeAttack.setFont(new Font("NEW ROMAN",Font.BOLD,35));


        Sparta=new MyButton();
        Sparta.setText("Sparta");
        Sparta.setFont(new Font("NEW ROMAN",Font.BOLD,35));
        Sparta.setBackground(Color.BLACK);
        Sparta.setForeground(new Color(255,230,200));
        Sparta.addActionListener(this);
        spartaAttack=new MyButton();
        spartaAttack=new MyButton();

        spartaAttack.setBackground(Color.BLACK);

        spartaAttack.addActionListener(this);
        spartaAttack.setFont(new Font("NEW ROMAN",Font.BOLD,35));

        Cairo.setBounds(600,390,145,50);
        Rome.setBounds(100,390,145,50);
        Sparta.setBounds(1170,390,145,50);

        cairoAttack.setBounds(750,390,145,50);
        romeAttack.setBounds(250,390,145,50);
        spartaAttack.setBounds(1320,390,145,50);

        mapLabel.add(Cairo);
        mapLabel.add(Rome);
        mapLabel.add(Sparta);


        this.flags();

        mapLabel.add(cairoAttack);
        mapLabel.add(romeAttack);
        mapLabel.add(spartaAttack);


       mapLabel.add(playerLabel);

       this.createArmyButtons();

        controlledArmiesFrame=new ControlledArmiesFrame();

        controlledArmiesFrame.setVisible(false);
        for(ControlledArmiesUnitsFrame controlledArmiesUnitsFrame:controlledArmiesFrame.getControlledArmiesUnitsFrame()){
            controlledArmiesUnitsFrame.setEnemyUnitButtonsMap(enemyDefendingArmy);

        }
        marchingArmiesFrame=new MarchingArmiesFrame();
        marchingArmiesFrame.setVisible(false);
        besiegingArmiesFrame=new BesiegingArmiesFrame();
        besiegingArmiesFrame.setVisible(false);


        this.add(mapLabel);
        defendingArmiesFrame=new DefendingArmiesFrame(cairoFlag,spartaFlag,romeFlag);

        defendingArmiesFrame.getSpartaButton().addActionListener(this);
        defendingArmiesFrame.getCairoButton().addActionListener(this);
        defendingArmiesFrame.getRomeButton().addActionListener(this);
        defendingArmiesFrame.setVisible(false);
        this.reload();



    }
public void createArmyButtons(){
    myControlledArmiesButton.setText("My Controlled Armies");
    myControlledArmiesButton.setFont(new Font("NEW ROMAN",Font.BOLD,19));
    myControlledArmiesButton.setBackground(Color.BLACK);
    myControlledArmiesButton.setForeground(new Color(255,230,200));
    myControlledArmiesButton.addActionListener(this);
    myControlledArmiesButton.setBounds(10,0,250,80);
    mapLabel.add(myControlledArmiesButton);


    myDefendingArmiesButton.setText("My Defending Armies");
    myDefendingArmiesButton.setFont(new Font("NEW ROMAN",Font.BOLD,19));
    myDefendingArmiesButton.setBackground(Color.BLACK);
    myDefendingArmiesButton.setForeground(new Color(255,230,200));
    myDefendingArmiesButton.addActionListener(this);
    myDefendingArmiesButton.setBounds(10+420,0,250,80);
mapLabel.add(myDefendingArmiesButton);


    myMarchingArmiesButton.setText("My Marching Armies");
    myMarchingArmiesButton.setFont(new Font("NEW ROMAN",Font.BOLD,19));
    myMarchingArmiesButton.setBackground(Color.BLACK);
    myMarchingArmiesButton.setForeground(new Color(255,230,200));
    myMarchingArmiesButton.addActionListener(this);
    myMarchingArmiesButton.setBounds(10+420*2,0,250,80);
    mapLabel.add(myMarchingArmiesButton);



    myBesiegingArmiesButton.setText("My Besieging Armies");
    myBesiegingArmiesButton.setFont(new Font("NEW ROMAN",Font.BOLD,19));
    myBesiegingArmiesButton.setBackground(Color.BLACK);
    myBesiegingArmiesButton.setForeground(new Color(255,230,200));
    myBesiegingArmiesButton.addActionListener(this);
    myBesiegingArmiesButton.setBounds(10+420*3,0,250,80);
    mapLabel.add(myBesiegingArmiesButton);






}


    public void flags(){
        if(!cairoFlag  ){
            cairoAttack.setForeground(new Color(230, 25, 25));
            cairoAttack.setText("\uD83D\uDD13");
            Cairo.setEnabled(false);

        }
        if (firstSelectedCity.equalsIgnoreCase("Cairo")|| cairoFlag)
        {
            cairoAttack.setForeground(new Color(25, 230, 28));
            cairoAttack.setText("✔");
            Cairo.setEnabled(true);
            cairoFlag=true;


        }
        if (!romeFlag ){
            romeAttack.setForeground(new Color(230, 25, 25));
            romeAttack.setText("\uD83D\uDD13");
            Rome.setEnabled(false);
        }
        if ( firstSelectedCity.equalsIgnoreCase("Rome") || romeFlag ){
            romeAttack.setForeground(new Color(25, 230, 28));
            romeAttack.setText("✔");
            Rome.setEnabled(true);
            romeFlag=true;
        }
        if (!spartaFlag){
            spartaAttack.setForeground(new Color(230, 25, 25));
            spartaAttack.setText("\uD83D\uDD13");
            Sparta.setEnabled(false);
        }
        if (firstSelectedCity.equalsIgnoreCase("sparta") || spartaFlag){
            spartaAttack.setForeground(new Color(25, 230, 28));
            spartaAttack.setText("✔");
            Sparta.setEnabled(true);
            spartaFlag=true;
        }

        this.reload();
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==Cairo){
            cairoFrame.setVisible(true);
            romeFrame.setVisible(false);
            spartaFrame.setVisible(false);
            this.validate();

        }
        else if(e.getSource()==Rome){
            cairoFrame.setVisible(false);
            romeFrame.setVisible(true);
            spartaFrame.setVisible(false);

            this.validate();
        }
        else if(e.getSource()==Sparta){

            spartaFrame.setVisible(true);
            romeFrame.setVisible(false);
            cairoFrame.setVisible(false);
            this.reload();

        }
        if (e.getSource()==myDefendingArmiesButton){
            defendingArmiesFrame.setVisible(true);
            this.reload();
        }
        if (e.getSource()==defendingArmiesFrame.getCairoButton()){
            getCairoFrame().getDefendingArmyCairo().setVisible(true);
        }
        if (e.getSource()==defendingArmiesFrame.getRomeButton()){
            getRomeFrame().getDefendingArmyRome().setVisible(true);


        }
        if (e.getSource()==defendingArmiesFrame.getSpartaButton()){
            getSpartaFrame().getDefendingArmySparta().setVisible(true);

        }
        if (e.getSource()==myControlledArmiesButton){
            controlledArmiesFrame.setVisible(true);
        }

        if (e.getSource()==myBesiegingArmiesButton){
            besiegingArmiesFrame.setVisible(true);
        }
        if (e.getSource()==myMarchingArmiesButton){
            marchingArmiesFrame.setVisible(true);
        }
    }



    public String getFirstSelectedCity() {
        return firstSelectedCity;
    }

    public CairoFrame getCairoFrame() {
        return cairoFrame;
    }

    public SpartaFrame getSpartaFrame() {
        return spartaFrame;
    }

    public RomeFrame getRomeFrame() {
        return romeFrame;
    }

    public MyButton getCairoAttack() {
        return cairoAttack;
    }

    public MyButton getRomeAttack() {
        return romeAttack;
    }

    public MyButton getSpartaAttack() {
        return spartaAttack;
    }

    public PlayerLabel getPlayerLabel() {
        return playerLabel;
    }

    public boolean isGender() {
        return Gender;
    }

    public String getNamePlayer() {
        return namePlayer;
    }

    public double getPlayerTreasury() {
        return playerTreasury;
    }

    public double getPlayerFood() {
        return playerFood;
    }


    public JLabel getMapLabel() {
        return mapLabel;
    }

    public MyButton getCairo() {
        return Cairo;
    }

    public MyButton getRome() {
        return Rome;
    }

    public MyButton getSparta() {
        return Sparta;
    }

    public DefendingArmiesFrame getDefendingArmiesFrame() {
        return defendingArmiesFrame;
    }

    public ControlledArmiesFrame getControlledArmiesFrame() {
        return controlledArmiesFrame;
    }

    public boolean isCairoFlag() {
        return cairoFlag;
    }

    public boolean isRomeFlag() {
        return romeFlag;
    }

    public boolean isSpartaFlag() {
        return spartaFlag;
    }

    public MyButton getMyControlledArmiesButton() {
        return myControlledArmiesButton;
    }

    public MyButton getMyDefendingArmiesButton() {
        return myDefendingArmiesButton;
    }

    public MyButton getMyMarchingArmiesButton() {
        return myMarchingArmiesButton;
    }

    public MyButton getMyBesiegingArmiesButton() {
        return myBesiegingArmiesButton;
    }

    public BesiegingArmiesFrame getBesiegingArmiesFrame() {
        return besiegingArmiesFrame;
    }

    public MarchingArmiesFrame getMarchingArmiesFrame() {
        return marchingArmiesFrame;
    }

    public void setFirstSelectedCity(String firstSelectedCity) {
        this.firstSelectedCity = firstSelectedCity;
    }

    public void setMapLabel(JLabel mapLabel) {
        this.mapLabel = mapLabel;
    }

    public void setCairo(MyButton cairo) {
        Cairo = cairo;
    }

    public void setRome(MyButton rome) {
        Rome = rome;
    }

    public void setSparta(MyButton sparta) {
        Sparta = sparta;
    }

    public void setCairoFrame(CairoFrame cairoFrame) {
        this.cairoFrame = cairoFrame;
    }

    public void setSpartaFrame(SpartaFrame spartaFrame) {
        this.spartaFrame = spartaFrame;
    }

    public void setRomeFrame(RomeFrame romeFrame) {
        this.romeFrame = romeFrame;
    }

    public void setCairoAttack(MyButton cairoAttack) {
        this.cairoAttack = cairoAttack;
    }

    public void setRomeAttack(MyButton romeAttack) {
        this.romeAttack = romeAttack;
    }

    public void setSpartaAttack(MyButton spartaAttack) {
        this.spartaAttack = spartaAttack;
    }

    public void setDefendingArmiesFrame(DefendingArmiesFrame defendingArmiesFrame) {
        this.defendingArmiesFrame = defendingArmiesFrame;
    }

    public void setControlledArmiesFrame(ControlledArmiesFrame controlledArmiesFrame) {
        this.controlledArmiesFrame = controlledArmiesFrame;
    }

    public void setBesiegingArmiesFrame(BesiegingArmiesFrame besiegingArmiesFrame) {
        this.besiegingArmiesFrame = besiegingArmiesFrame;
    }

    public void setMarchingArmiesFrame(MarchingArmiesFrame marchingArmiesFrame) {
        this.marchingArmiesFrame = marchingArmiesFrame;
    }

    public void setCairoFlag(boolean cairoFlag) {
        this.cairoFlag = cairoFlag;
    }

    public void setRomeFlag(boolean romeFlag) {
        this.romeFlag = romeFlag;
    }

    public void setSpartaFlag(boolean spartaFlag) {
        this.spartaFlag = spartaFlag;
    }

    public void setPlayerLabel(PlayerLabel playerLabel) {
        this.playerLabel = playerLabel;
    }

    public void setGender(boolean gender) {
        Gender = gender;
    }

    public void setNamePlayer(String namePlayer) {
        this.namePlayer = namePlayer;
    }

    public void setPlayerTreasury(double playerTreasury) {
        this.playerTreasury = playerTreasury;
    }

    public void setPlayerFood(double playerFood) {
        this.playerFood = playerFood;
    }

    public void setMyControlledArmiesButton(MyButton myControlledArmiesButton) {
        this.myControlledArmiesButton = myControlledArmiesButton;
    }

    public void setMyDefendingArmiesButton(MyButton myDefendingArmiesButton) {
        this.myDefendingArmiesButton = myDefendingArmiesButton;
    }

    public void setMyMarchingArmiesButton(MyButton myMarchingArmiesButton) {
        this.myMarchingArmiesButton = myMarchingArmiesButton;
    }

    public void setMyBesiegingArmiesButton(MyButton myBesiegingArmiesButton) {
        this.myBesiegingArmiesButton = myBesiegingArmiesButton;
    }

    public void setEnemyDefendingArmy(ConcurrentHashMap<String, ArrayList<UnitButton>> enemyDefendingArmy) {
        this.enemyDefendingArmy = enemyDefendingArmy;
    }

    public ConcurrentHashMap<String, ArrayList<UnitButton>> getEnemyDefendingArmy() {
        return enemyDefendingArmy;
    }
}