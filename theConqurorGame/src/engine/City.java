package engine;

import buildings.EconomicBuilding;
import buildings.MilitaryBuilding;
import units.Army;

import java.util.ArrayList;

public class City {

    private String name;
    private ArrayList<EconomicBuilding> economicalBuildings;
    private ArrayList<MilitaryBuilding> militaryBuildings;
    private Army defendingArmy;
    private int turnsUnderSiege;
    private boolean underSiege;

    public City(String name) {
        this.name = name;
        this.economicalBuildings = new ArrayList<EconomicBuilding>();
        this.militaryBuildings = new ArrayList<MilitaryBuilding>();
        this.defendingArmy = new Army(name);
        this.turnsUnderSiege = -1;
        this.underSiege = false;
    }

    public String getName() {
        return name;
    }

    public ArrayList<EconomicBuilding> getEconomicalBuildings() {
        return economicalBuildings;
    }

    public ArrayList<MilitaryBuilding> getMilitaryBuildings() {
        return militaryBuildings;
    }

    public Army getDefendingArmy() {
        return defendingArmy;
    }

    public void setDefendingArmy(Army defendingArmy) {
        this.defendingArmy = defendingArmy;
    }

    public int getTurnsUnderSiege() {
        return turnsUnderSiege;
    }

    public void setTurnsUnderSiege(int turnsUnderSiege) {
        this.turnsUnderSiege = turnsUnderSiege;
    }

    public boolean isUnderSiege() {
        return underSiege;
    }

    public void setUnderSiege(boolean underSiege) {
        this.underSiege = underSiege;
    }
}
