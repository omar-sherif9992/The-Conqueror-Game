package view.UnitsButtons;

import units.Status;

import javax.swing.*;
import java.awt.*;

public class UnitInfoLabel extends JLabel {
    private Status status;
    private int level;
    private String type;
    private double soldierCount;
    private int maxSoldierCount;
    private JLabel statusLabel =new JLabel();
    private JLabel soldierCountLabel =new JLabel();
  public UnitInfoLabel(Status status,int level,String type,double soldierCount,int maxSoldierCount){
      this.maxSoldierCount=maxSoldierCount;
      this.soldierCount=soldierCount;
      this.status=status;
      this.level=level;
      this.type=type;
      this.setVisible(true);
      this.setPreferredSize(new Dimension(125,135));
      this.setBackground(Color.WHITE);
      this.setOpaque(true);
      this.setEnabled(false);
      data();

  }
  public void data(){
      JLabel typeLabel =new JLabel();
      typeLabel.setBounds(0,0,120,20);
      typeLabel.setText("Unit Type : "+type);
      typeLabel.setVisible(true);
      this.add(typeLabel);

      JLabel levelLabel =new JLabel();
      levelLabel.setBounds(0,22,120,20);
      levelLabel.setText("Unit level : "+level);
      levelLabel.setVisible(true);
      this.add(levelLabel);



      statusLabel.setBounds(0,44,120,20);
      statusLabel.setText("Statues : "+status);
      statusLabel.setVisible(true);
      this.add(statusLabel);



      soldierCountLabel.setBounds(0,66,120,20);
      soldierCountLabel.setText("Health :"+soldierCount);
      soldierCountLabel.setVisible(true);
      this.add(soldierCountLabel);

      JLabel maxSoldierCountLabel =new JLabel();
      maxSoldierCountLabel.setBounds(0,88,120,20);
      maxSoldierCountLabel.setText("Max Health: "+maxSoldierCount);
      maxSoldierCountLabel.setVisible(true);
      this.add(maxSoldierCountLabel);



  }

    public Status getStatus() {
        return status;
    }

    public int getLevel() {
        return level;
    }

    public String getType() {
        return type;
    }

    public double getSoldierCount() {
        return soldierCount;
    }

    public int getMaxSoldierCount() {
        return maxSoldierCount;
    }

    public JLabel getStatusLabel() {
        return statusLabel;
    }

    public JLabel getSoldierCountLabel() {
        return soldierCountLabel;
    }
}
