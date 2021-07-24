package engine;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

import buildings.EconomicBuilding;
import buildings.Farm;
import buildings.Market;
import buildings.MilitaryBuilding;
import exceptions.FriendlyFireException;
import units.*;
import view.UnitsButtons.UnitButton;

public class Game {
    private Player player;
    private ArrayList<City> availableCities;
    private ArrayList<Distance> distances;
    private final int maxTurnCount = 50;
    private int currentTurnCount;
    private ConcurrentHashMap<String,ArrayList<UnitButton>> enemyDefendingArmy=new ConcurrentHashMap<>();
    private boolean isVictorious;
    private boolean whoWon;

    public Game(String playerName, String playerCity) throws IOException {
        player = new Player(playerName);
        availableCities = new ArrayList<City>();
        distances = new ArrayList<Distance>();
        currentTurnCount = 1;
        loadCitiesAndDistances();
        for (City c : availableCities) {
            if (c.getName().equalsIgnoreCase(playerCity))
                player.getControlledCities().add(c);
            else
                loadArmy(c.getName(), c.getName().toLowerCase() + "_army.csv");
        }
    }

    private void loadCitiesAndDistances() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("distances.csv"));
        String currentLine = br.readLine();
        ArrayList<String> names = new ArrayList<String>();
        while (currentLine != null) {
            String[] content = currentLine.split(",");
            if (!names.contains(content[0])) {
                availableCities.add(new City(content[0]));
                names.add(content[0]);
            } else if (!names.contains(content[1])) {
                availableCities.add(new City(content[1]));
                names.add(content[1]);
            }
            distances.add(new Distance(content[0], content[1], Integer.parseInt(content[2])));
            currentLine = br.readLine();
        }
        br.close();
    }

    public void loadArmy(String cityName, String path) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(path));
        ArrayList<UnitButton>unitButtons=new ArrayList<>();
        String currentLine = br.readLine();
        Army resultArmy = new Army(cityName);
        while (currentLine != null) {
            String[] content = currentLine.split(",");
            String unitType = content[0].toLowerCase();
            int unitLevel = Integer.parseInt(content[1]);
            Unit unit = null;
            if (unitType.equalsIgnoreCase("archer")) {
                if (unitLevel == 1)
                    unit = (new Archer(1, 60, 0.4, 0.5, 0.6));

                else if (unitLevel == 2)
                    unit = (new Archer(2, 60, 0.4, 0.5, 0.6));
                else
                    unit = (new Archer(3, 70, 0.5, 0.6, 0.7));
            } else if (unitType.equalsIgnoreCase("infantry")) {
                if (unitLevel == 1)
                    unit = (new Infantry(1, 50, 0.5, 0.6, 0.7));
                else if (unitLevel == 2)
                    unit = (new Infantry(2, 50, 0.5, 0.6, 0.7));
                else
                    unit = (new Infantry(3, 60, 0.6, 0.7, 0.8));
            } else if (unitType.equalsIgnoreCase("cavalry")) {
                if (unitLevel == 1)
                    unit = (new Cavalry(1, 40, 0.6, 0.7, 0.75));
                else if (unitLevel == 2)
                    unit = (new Cavalry(2, 40, 0.6, 0.7, 0.75));
                else
                    unit = (new Cavalry(3, 60, 0.7, 0.8, 0.9));
            }
            if (unit != null)
            {  unit.setParentArmy(resultArmy);
                resultArmy.getUnits().add(unit);

                unitButtons.add(new UnitButton(unit.getParentArmy().getCurrentStatus(),unit.getLevel(),unitType,unit.getCurrentSoldierCount(),unit.getMaxSoldierCount(),unit));
            }
            currentLine = br.readLine();
        }
        br.close();
        for (City c : availableCities) {
            if (c.getName().toLowerCase().equals(cityName.toLowerCase()))
                c.setDefendingArmy(resultArmy);
        }
        enemyDefendingArmy.put(cityName.toLowerCase(),unitButtons);
    }

    public void targetCity(Army army, String targetName) {
        if (army == null)
            return;
        if (!army.getCurrentStatus().equals(Status.MARCHING) || !army.getCurrentLocation().equalsIgnoreCase("onRoad")) {
            army.setTarget(targetName);
            for (Distance distance : this.getDistances()) {
                if ((distance.getFrom().equalsIgnoreCase(army.getCurrentLocation()) && distance.getTo().equalsIgnoreCase(targetName)) ||
                        (distance.getFrom().equalsIgnoreCase(targetName) && distance.getTo().equalsIgnoreCase(army.getCurrentLocation()))) {
                    army.setDistancetoTarget(distance.getDistance());
                    break;
                }
            }

        }
    }

    public void endTurn() {
        currentTurnCount++;
        double totalUpkeep = 0;
        for (City c : player.getControlledCities()) {
            for (MilitaryBuilding b : c.getMilitaryBuildings()) {

                b.setCoolDown(false);
                b.setCurrentRecruit(0);

            }
            for (EconomicBuilding b : c.getEconomicalBuildings()) {

                b.setCoolDown(false);
                if (b instanceof Market)
                    player.setTreasury(player.getTreasury() + b.harvest());
                else if (b instanceof Farm)
                    player.setFood(player.getFood() + b.harvest());
            }
            totalUpkeep+=c.getDefendingArmy().foodNeeded();
        }
        for (Army a : player.getControlledArmies()) {
            if (!a.getTarget() .equals("") && a.getCurrentStatus() == Status.IDLE) {
                a.setCurrentStatus(Status.MARCHING);
                a.setCurrentLocation("onRoad");
            }
            if(a.getDistancetoTarget()>0 &&!a.getTarget().equals(""))
                a.setDistancetoTarget(a.getDistancetoTarget() - 1);
            if (a.getDistancetoTarget() == 0) {
                a.setCurrentLocation(a.getTarget());
                a.setTarget("");
                a.setCurrentStatus(Status.IDLE);
            }
            totalUpkeep +=  a.foodNeeded();

        }
        if (totalUpkeep <= player.getFood())
            player.setFood(player.getFood() - totalUpkeep);
        else {
            player.setFood(0);
            for (Army a : player.getControlledArmies()) {

                for (Unit u : a.getUnits()) {
                    u.setCurrentSoldierCount(u.getCurrentSoldierCount() - (int) (u.getCurrentSoldierCount() * 0.1));
                }
            }
        }

        for (City c : availableCities) {
            if (c.isUnderSiege()) {
                if(c.getTurnsUnderSiege() < 3){
                    c.setTurnsUnderSiege(c.getTurnsUnderSiege() + 1);

                }
                else{
                    // player should choose to attack
                    c.setUnderSiege(false);
                    return;
                }
                for (Unit u : c.getDefendingArmy().getUnits()) {
                    u.setCurrentSoldierCount(u.getCurrentSoldierCount() - (int) (u.getCurrentSoldierCount() * 0.1));
                }
            }
        }
    }

    public void occupy(Army army,String cityName){
        if(army == null)
            return;
       for(City city : this.getAvailableCities()) {
           if (city != null) {
               if (city.getName().equalsIgnoreCase(cityName)) {
                   city.setUnderSiege(false);
                   city.setTurnsUnderSiege(-1);
                   if (!this.getPlayer().getControlledCities().contains(city)) {
                       this.getPlayer().getControlledCities().add(city);
                   }
                   city.setDefendingArmy(army);
                   if (this.getPlayer().getControlledArmies().contains(army))
                       this.getPlayer().getControlledArmies().remove(army);

                   army.setCurrentStatus(Status.IDLE);
                   break;
               }
           }
       }
    }

    public void autoResolve(Army attacker, Army defender) throws FriendlyFireException {
        if(this.getPlayer().getControlledArmies().contains(attacker) && this.getPlayer().getControlledArmies().contains(defender))
            throw new FriendlyFireException("You can't attack a Friendly Army");
        boolean attackTurn = true;
        while (!attacker.getUnits().isEmpty() && !defender.getUnits().isEmpty()){
            int randomAttackerIndex = (int) (Math.random() * attacker.getUnits().size());
            while(attacker.getUnits().get(randomAttackerIndex) == null){
                attacker.getUnits().remove(randomAttackerIndex);
                randomAttackerIndex = (int) (Math.random() * attacker.getUnits().size());
            }
            int randomDefenderIndex = (int) (Math.random() * defender.getUnits().size());
            while(defender.getUnits().get(randomDefenderIndex) == null){
                defender.getUnits().remove(randomDefenderIndex);
                randomDefenderIndex = (int) (Math.random() * defender.getUnits().size());
            }
            Unit attackerUnit = attacker.getUnits().get(randomAttackerIndex);
            Unit defenderUnit = defender.getUnits().get(randomDefenderIndex);
             attackTurn = true;
            while (attackerUnit.getCurrentSoldierCount()!=0 && defenderUnit.getCurrentSoldierCount()!=0){
                if(attackTurn){
                    attackerUnit.attack(defenderUnit);
                }
                else {
                    defenderUnit.attack(attackerUnit);
                }
                attackTurn=!attackTurn;
            }
            if (attackerUnit.getCurrentSoldierCount() <= 0)
                attacker.getUnits().remove(attackerUnit);
            else if (defenderUnit.getCurrentSoldierCount() <= 0)
                defender.getUnits().remove(defenderUnit);
        }

        if(!attacker.getUnits().isEmpty() && defender.getUnits().isEmpty()){
            occupy(attacker,defender.getCurrentLocation());
            whoWon = true;
        }
        else {
            this.getPlayer().getControlledArmies().remove(attacker);
            City correspondingCity = getCorrespondingAvailableCity(defender.getCurrentLocation());
            if (correspondingCity != null) {
                correspondingCity.setUnderSiege(false);
                correspondingCity.setTurnsUnderSiege(-1);
            }
            whoWon = false;
        }
    }

    public boolean isGameOver() {
        this.isVictorious = this.getPlayer().getControlledCities().size() == this.getAvailableCities().size();
        return this.getPlayer().getControlledCities().size() == this.getAvailableCities().size() || getCurrentTurnCount() > getMaxTurnCount();
    }

    public City getCorrespondingAvailableCity(String target) {
        for (City city : this.getAvailableCities())
            if (city != null && city.getName().equalsIgnoreCase(target))
                return city;
        return null;
    }

    public void resetAllBuildingsAndHarvest() {
        for(City city : this.getPlayer().getControlledCities()) {
            for(EconomicBuilding economicBuilding : city.getEconomicalBuildings()) {
                economicBuilding.setCoolDown(false);
                if (economicBuilding instanceof Farm)
                    this.getPlayer().setFood(economicBuilding.harvest() + this.getPlayer().getFood());
                else if (economicBuilding instanceof Market)
                    this.getPlayer().setTreasury(economicBuilding.harvest() + this.getPlayer().getTreasury());
            }
            for (MilitaryBuilding militaryBuilding : city.getMilitaryBuildings()) {
                militaryBuilding.setCoolDown(false);
                militaryBuilding.setCurrentRecruit(0);
            }
        }
    }

    public  double getAllArmiesFoodNeeded(){
        double food = 0;
        for (Army army : this.getPlayer().getControlledArmies()) {
            if (army != null)
                food += army.foodNeeded();
        }
        return food;
    }

    public ArrayList<City> getAvailableCities() {
        return availableCities;
    }

    public ArrayList<Distance> getDistances() {
        return distances;
    }

    public int getCurrentTurnCount() {
        return currentTurnCount;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public int getMaxTurnCount() {
        return maxTurnCount;
    }

    public void setCurrentTurnCount(int currentTurnCount) {
        this.currentTurnCount = currentTurnCount;
    }

    public ConcurrentHashMap<String, ArrayList<UnitButton>> getEnemyDefendingArmy() {
        return enemyDefendingArmy;
    }

    public boolean isVictorious() {
        return isVictorious;
    }

    public void setVictorious(boolean victorious) {
        isVictorious = victorious;
    }

    public boolean isWhoWon() {
        return whoWon;
    }
}