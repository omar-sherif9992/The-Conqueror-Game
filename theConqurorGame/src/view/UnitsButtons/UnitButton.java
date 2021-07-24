package view.UnitsButtons;

import units.*;
import view.tools.MyToggledButton;

import javax.swing.*;
import java.awt.*;

public class UnitButton extends MyToggledButton {

        private int level;
        private JLabel pictureLabel;
        private Status status;

        private String type;
        private double soldierCount;
        private int maxSoldierCount;
        private Unit unit;
        private UnitInfoLabel unitInfoLabel;


        public UnitButton(Status status, int level, String type, double soldierCount, int maxSoldierCount,Unit unit){
            this.unit=unit;
            this.setBackground(Color.DARK_GRAY);
            this.setVisible(true);
            this.setPreferredSize(new Dimension(325,135));
            this.setLayout(new GridLayout(1,2));

            switch (type.toLowerCase()){
                case "archer": pictureLabel=new archerButton(level);break;
                case "infantry":pictureLabel=new InfantryButton(level);break;
                case "cavalry":pictureLabel=new CavalryButton(level);break;

            }
            this.add(pictureLabel);
            unitInfoLabel=new UnitInfoLabel(status,level,type,soldierCount,maxSoldierCount);
            this.add(unitInfoLabel);
        }

    public UnitInfoLabel getUnitInfoLabel() {
        return unitInfoLabel;
    }

    public JLabel getPictureLabel() {
        return pictureLabel;
    }

    public Status getStatus() {
        return status;
    }

    public String getType() {
        return type;
    }

    public int getLevel() {
        return level;
    }

    public double getSoldierCount() {
        return soldierCount;
    }

    public void setSoldierCount(double soldierCount) {
        this.soldierCount = soldierCount;
    }

    public int getMaxSoldierCount() {
        return maxSoldierCount;
    }

    public void setMaxSoldierCount(int maxSoldierCount) {
        this.maxSoldierCount = maxSoldierCount;
    }

    public Unit getUnit() {
        return unit;
    }
}
