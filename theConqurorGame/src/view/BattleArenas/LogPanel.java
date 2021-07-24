package view.BattleArenas;

import view.tools.MyButton;

import javax.swing.*;
import java.awt.*;
import java.util.Locale;

public class LogPanel extends JPanel {
    private String targetCityName;
    private JLabel logLabel=new JLabel();
    private JTextPane logPane=new JTextPane();
    private JLabel targetCityNameLabel=new JLabel();
    private String message;
    private MyButton battleBegins=new MyButton();

    public LogPanel(String targetCityName) {
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK,4));
        this.setBackground(Color.WHITE);
        this.setBounds(1201,0,400,1000);
        this.targetCityName = targetCityName;
        this.setLayout(null);
        this.setVisible(true);
        this.setOpaque(true);

        targetCityNameLabel.setBounds(50,50-30,200,100);
        targetCityNameLabel.setText(targetCityName.toUpperCase());
        targetCityNameLabel.setVisible(true);

        targetCityNameLabel.setFont(new Font("NEW ROMAN",Font.BOLD,35));

        logLabel.setBounds(70,160-30-40,200,100);
        logLabel.setText("LOG");
        logLabel.setVisible(true);
        logLabel.setFont(new Font("NEW ROMAN",Font.BOLD,35));

        logPane.setBounds(10,160+100+10-30-40,300,500);
        logPane.setVisible(true);
        logPane.setOpaque(true);
        logPane.setBackground(Color.lightGray);

        logPane.setText(message);


        battleBegins.setBounds(10,160+100+10+500-30-40+10,300,100);
        battleBegins.setText("Battle Begins");
        battleBegins.setVisible(true);
        battleBegins.setFont(new Font("NEW ROMAN",Font.BOLD,35));
        battleBegins.setForeground(Color.WHITE);
        battleBegins.setBackground(Color.BLACK);
        logPane.setEditable(false);
        logPane.setFont(new Font("NEW ROMAN",Font.BOLD, 15));
        logPane.setForeground(Color.BLACK);


        this.add(battleBegins);
        this.add(targetCityNameLabel);
        this.add(logLabel);
        this.add(logPane);


    }

    public String getTargetCityName() {
        return targetCityName;
    }

    public void setTargetCityName(String targetCityName) {
        this.targetCityName = targetCityName;
    }

    public JLabel getLogLabel() {
        return logLabel;
    }

    public void setLogLabel(JLabel logLabel) {
        this.logLabel = logLabel;
    }

    public JTextPane getLogPane() {
        return logPane;
    }

    public void setLogPane(JTextPane logPane) {
        this.logPane = logPane;
    }

    public JLabel getTargetCityNameLabel() {
        return targetCityNameLabel;
    }

    public void setTargetCityNameLabel(JLabel targetCityNameLabel) {
        this.targetCityNameLabel = targetCityNameLabel;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public MyButton getBattleBegins() {
        return battleBegins;
    }

    public void setBattleBegins(MyButton battleBegins) {
        this.battleBegins = battleBegins;
    }
}
