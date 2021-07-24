package view.ArmyButton;

import units.Status;
import view.UnitsButtons.CavalryButton;
import view.UnitsButtons.InfantryButton;
import view.UnitsButtons.UnitInfoLabel;
import view.UnitsButtons.archerButton;
import view.tools.MyToggledButton;

import javax.swing.*;
import java.awt.*;

public class ArmyButton extends MyToggledButton {
    private JLabel pictureLabel=new JLabel();
    private Status status;
    private String cityName;
    private int numberOfSoldiers;
    private ImageIcon image;
    private int armyNumber;
    private ArmyInfoLabel armyInfoLabel;



    public ArmyButton(int armyNumber,Status status, String cityName, int numberOfSoldiers){
        this.armyNumber=armyNumber;
        this.status=status;
        this.cityName=cityName;
        this.numberOfSoldiers=numberOfSoldiers;
        armyInfoLabel=new ArmyInfoLabel(armyNumber,status, cityName, numberOfSoldiers);


        this.setBackground(Color.DARK_GRAY);
        this.setVisible(true);
        this.setPreferredSize(new Dimension(325,135));
        this.setLayout(new GridLayout(1,2));

        switch (cityName.toLowerCase()){
            case "cairo": image=new ImageIcon("CairoEditedKo2.jpg");break;
            case "sparta":image=new ImageIcon("SpartanKo2.jpg");break;
            case "rome":image=new ImageIcon("RomeEditedlko2.jpg");break;

        }
        pictureLabel.setIcon(image);
        pictureLabel.setPreferredSize(new Dimension(200,135));
        pictureLabel.setVisible(true);
        this.add(pictureLabel);
        this.add(armyInfoLabel);

    }

    public JLabel getPictureLabel() {
        return pictureLabel;
    }

    public void setPictureLabel(JLabel pictureLabel) {
        this.pictureLabel = pictureLabel;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public int getNumberOfSoldiers() {
        return numberOfSoldiers;
    }

    public void setNumberOfSoldiers(int numberOfSoldiers) {
        this.numberOfSoldiers = numberOfSoldiers;
    }

    public ImageIcon getImage() {
        return image;
    }

    public void setImage(ImageIcon image) {
        this.image = image;
    }

    public int getArmyNumber() {
        return armyNumber;
    }

    public void setArmyNumber(int armyNumber) {
        this.armyNumber = armyNumber;
    }

    public ArmyInfoLabel getArmyInfoLabel() {
        return armyInfoLabel;
    }

    public void setArmyInfoLabel(ArmyInfoLabel armyInfoLabel) {
        this.armyInfoLabel = armyInfoLabel;
    }
}
