package view.UnitsButtons;

import view.tools.MyFrame;

import javax.swing.*;
import java.awt.*;

public class unitSoldierCount {


        JProgressBar bar;
        public unitSoldierCount(){
           MyFrame frame=new MyFrame();
            bar=new JProgressBar();
            frame.setLayout(null);
            frame.setSize(420,420);
            bar.setValue(100);
            bar.setStringPainted(true);
            bar.setBounds(0,0,420,50);
            bar.setForeground(Color.RED);
            bar.setBackground(Color.BLACK);
            frame.add(bar);
            fill();

        }
        public void fill(){
            int counter=100;
            while(counter>0){
                bar.setValue(counter);
                try {
                    Thread.sleep(1000);

                }catch (InterruptedException e){
                    e.printStackTrace();
                }
                counter-=10;

            }
            bar.setString("Done");
        }
        public static void main(String[] args) {
            new unitSoldierCount();
        }


    }

