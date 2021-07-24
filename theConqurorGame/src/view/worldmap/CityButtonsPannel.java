package view.worldmap;

import view.tools.MyButton;

import javax.swing.*;
import java.awt.*;

public class CityButtonsPannel  extends JPanel {
    private MyButton Idle=new MyButton();
    private MyButton defending=new MyButton();

    private MyButton Building=new MyButton();
    private ImageIcon IdleIcon=new ImageIcon("idleIconEdited.jpg");
    private ImageIcon DefendingIcon=new ImageIcon("shieldIconEdited.jpg");
    private ImageIcon BuildingIcon=new ImageIcon("BuildingIconEdited.jpg");
    private MyButton close=new MyButton();


    public CityButtonsPannel(){
        close.setText( "❌");

        close.setBackground(Color.white);
        close.setBounds(100,10,50,50);
        this.add(close);
        this.setVisible(true);
        this.setLayout(new FlowLayout(FlowLayout.CENTER,10,10));
        this.setSize(100,100);
        this.setOpaque(false);

        Building.setPreferredSize(new Dimension(50,50));
        Idle.setPreferredSize(new Dimension(50,50));
        defending.setPreferredSize(new Dimension(50,50));
        setImages();

        this.add(Building);
        this.add(Idle);
        this.add(defending);

    }
    public void setImages(){
        Idle.setIcon(IdleIcon);
        defending.setIcon(DefendingIcon);

        Building.setIcon(BuildingIcon);

    }

    public MyButton getIdle() {
        return Idle;
    }

    public void setIdle(MyButton idle) {
        Idle = idle;
    }


    public MyButton getBuilding() {
        return Building;
    }

    public void setBuilding(MyButton building) {
        Building = building;
    }

    public MyButton getClose() {
        return close;
    }

    public void setClose(MyButton close) {
        this.close = close;
    }

    public MyButton getDefending() {
        return defending;
    }

    public void setDefending(MyButton defending) {
        this.defending = defending;
    }

    public ImageIcon getIdleIcon() {
        return IdleIcon;
    }

    public void setIdleIcon(ImageIcon idleIcon) {
        IdleIcon = idleIcon;
    }

    public ImageIcon getDefendingIcon() {
        return DefendingIcon;
    }

    public void setDefendingIcon(ImageIcon defendingIcon) {
        DefendingIcon = defendingIcon;
    }

    public ImageIcon getBuildingIcon() {
        return BuildingIcon;
    }

    public void setBuildingIcon(ImageIcon buildingIcon) {
        BuildingIcon = buildingIcon;
    }
}
