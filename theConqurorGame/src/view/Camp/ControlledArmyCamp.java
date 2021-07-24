package view.Camp;

import units.Army;
import view.tools.MyFrame;

import java.util.ArrayList;

public class ControlledArmyCamp extends MyFrame {
    private ArrayList<Army> idleArmy;


//
//    public ControlledArmyCamp(ArrayList ) {
//        JPanel Unitpanel1=new JPanel();
//
//        JPanel Unitpanel2=new JPanel();
////        Unitpanel1.setBounds(200,500,1500,300);
////        Unitpanel2.setBounds(200,500,1500,300);
//        idleArmy.add(new archerButton(2));
//        idleArmy.add(new archerButton(1));
//        idleArmy.add(new archerButton(3));
//        idleArmy.add(new InfantryButton(1));
//        idleArmy.add(new InfantryButton(1));
//        idleArmy.add(new InfantryButton(1));
//        idleArmy.add(new CavalryButton(1));
//        idleArmy.add(new CavalryButton(2));
//        idleArmy.add(new CavalryButton(3));
//
//
//
//        JPanel UnitPanelinfo1=new JPanel();
//       // UnitPanelinfo1.setBounds(200,400,1400,300);
//        JPanel UnitPanelinfo2=new JPanel();
//        Unitpanel1.setVisible(true);
//        UnitPanelinfo1.setVisible(true);
//        Unitpanel2.setVisible(true);
//        UnitPanelinfo2.setVisible(true);
//        int counter=0;
//
//        for (int i=0;i<10;i++){
//            if(i<idleArmy.size()){
//             if (i<5){
//                  Unitpanel1.add(idleArmy.get(i));
//                  if(counter<5){
//                 UnitPanelinfo1.add(new UnitInfoLabel(idleArmy.get(i).)); todo
//                  }
//
//                        }
//
//             if (i>=5){
//
//                 Unitpanel2.add(idleArmy.get(i));
//                 if(counter>=5){
//                 UnitPanelinfo2.add(new UnitInfoLabel()); todo
//                 }
//             }
//
//
//            }
//
//            else
//            {if (i<5)
//                Unitpanel1.add(new AddUnitLabel());
//                if(counter<5)
//                    UnitPanelinfo1.add(new FalseLabel());
//
//
//                if (i>=5){
//                    Unitpanel2.add(new AddUnitLabel());
//                    if(counter>=5)
//                    UnitPanelinfo2.add(new FalseLabel());
//                }}
//            counter++;
//            }
//
//        this.add(Unitpanel1);
//        this.add(UnitPanelinfo1);
//        this.add(Unitpanel2);
//       this.add(UnitPanelinfo2);
//
//       this.reload();
//    }
//
//    public static void main(String[] args) {
//        new ControlledArmyCamp();
//    }
//
//}
}