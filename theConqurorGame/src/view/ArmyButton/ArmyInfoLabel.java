package view.ArmyButton;

import units.Status;

import javax.swing.*;
import java.awt.*;

public class ArmyInfoLabel extends JLabel {
    private Status status;
    private String cityName;
    private int numberOfSoldiers;
    private int armyNumber;
    private int distanceToTarget;
    private String targetCity;
    private JLabel targetCityLabel =new JLabel();
    private JLabel distanceToTargetLabel =new JLabel();
    private JLabel statusLabel =new JLabel();
    private JLabel cityNameLabel=new JLabel();
    JLabel armyNumberLabel =new JLabel();

    public ArmyInfoLabel(int armyNumber,Status status, String cityName, int numberOfSoldiers){
        this.armyNumber=armyNumber;
        this.status=status;
        this.cityName=cityName;
        this.numberOfSoldiers=numberOfSoldiers;
        this.setVisible(true);
        this.setPreferredSize(new Dimension(125,135));
        this.setBackground(Color.WHITE);
        this.setVisible(true);
        this.setOpaque(true);
        this.setEnabled(false);
        data();
        this.repaint();
        this.revalidate();

    }
    public void data(){

        armyNumberLabel.setBounds(0,0,120,20);
        armyNumberLabel.setText("Army Number : "+armyNumber);
        armyNumberLabel.setVisible(true);
        this.add(armyNumberLabel);





        cityNameLabel.setBounds(0,44-22,120,20);
        cityNameLabel.setText("City Name : "+cityName);
        cityNameLabel.setVisible(true);
        this.add(cityNameLabel);



        statusLabel.setBounds(0,66-22,120,20);
        statusLabel.setText("Statues : "+status);
        statusLabel.setVisible(true);
        this.add(statusLabel);

        distanceToTargetLabel.setBounds(0,88-22,120,20);
        distanceToTargetLabel.setText("distance target : "+distanceToTarget);
        distanceToTargetLabel.setVisible(false);
        this.add(distanceToTargetLabel);

        targetCityLabel.setBounds(0,88,120,20);
        targetCityLabel.setText("target City: ");
        targetCityLabel.setVisible(false);
        this.add(targetCityLabel);


    }

    public JLabel getArmyNumberLabel() {
        return armyNumberLabel;
    }

    public void setArmyNumberLabel(JLabel armyNumberLabel) {
        this.armyNumberLabel = armyNumberLabel;
    }

    public Status getStatus() {
        return status;
    }

    public String getCityName() {
        return cityName;
    }

    public int getNumberOfSoldiers() {
        return numberOfSoldiers;
    }

    public int getArmyNumber() {
        return armyNumber;
    }

    public int getDistanceToTarget() {
        return distanceToTarget;
    }

    public String getTargetCity() {
        return targetCity;
    }

    public JLabel getTargetCityLabel() {
        return targetCityLabel;
    }

    public JLabel getDistanceToTargetLabel() {
        return distanceToTargetLabel;
    }

    public JLabel getStatusLabel() {
        return statusLabel;
    }

    public JLabel getCityNameLabel() {
        return cityNameLabel;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public void setNumberOfSoldiers(int numberOfSoldiers) {
        this.numberOfSoldiers = numberOfSoldiers;
    }

    public void setArmyNumber(int armyNumber) {
        this.armyNumber = armyNumber;
    }

    public void setDistanceToTarget(int distanceToTarget) {
        this.distanceToTarget = distanceToTarget;
    }

    public void setTargetCity(String targetCity) {
        this.targetCity = targetCity;
    }

    public void setTargetCityLabel(JLabel targetCityLabel) {
        this.targetCityLabel = targetCityLabel;
    }

    public void setDistanceToTargetLabel(JLabel distanceToTargetLabel) {
        this.distanceToTargetLabel = distanceToTargetLabel;
    }

    public void setStatusLabel(JLabel statusLabel) {
        this.statusLabel = statusLabel;
    }

    public void setCityNameLabel(JLabel cityNameLabel) {
        this.cityNameLabel = cityNameLabel;
    }
}

