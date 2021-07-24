package view.BuildingsOfCities;

import view.tools.CloseButton;
import view.tools.MyButton;

import java.util.ArrayList;

public class BuildingButtons{
    private upgradeButton archeryBuildingUpgrade;
    private upgradeButton barracksBuildingUpgrade;
    private upgradeButton stableBuildingUpgrade;
    private upgradeButton marketUpgrade;
    private upgradeButton farmUpgrade;

    private BuyButton archeryBuildingBuy;
    private BuyButton barracksBuildingBuy;
    private BuyButton stableBuildingBuy;
    private BuyButton marketBuy;
    private BuyButton farmBuy;

    private recruitButton archeryBuildingRecruit;
    private recruitButton barracksBuildingRecruit;
    private recruitButton stableBuildingRecruit;



    private ArrayList<upgradeButton>upgradeButtons=new ArrayList<>();
    private ArrayList<recruitButton> recruitButtons=new ArrayList<>();
    private ArrayList<BuyButton> buyButtons=new ArrayList<>();


    private CloseButton closeButton=new CloseButton();

    public  BuildingButtons(){

        archeryBuildingUpgrade=new upgradeButton();
        barracksBuildingUpgrade=new upgradeButton();
        stableBuildingUpgrade=new upgradeButton();
        marketUpgrade=new upgradeButton();
        farmUpgrade=new upgradeButton();

        upgradeButtons.add(archeryBuildingUpgrade);
        upgradeButtons.add(barracksBuildingUpgrade);
        upgradeButtons.add(stableBuildingUpgrade);
        upgradeButtons.add(marketUpgrade);
        upgradeButtons.add(farmUpgrade);


        archeryBuildingBuy=new BuyButton();
        barracksBuildingBuy=new BuyButton();
        stableBuildingBuy=new BuyButton();
        marketBuy=new BuyButton();
        farmBuy=new BuyButton();


        buyButtons.add(archeryBuildingBuy);
        buyButtons.add(barracksBuildingBuy);
        buyButtons.add(stableBuildingBuy);
        buyButtons.add(marketBuy);
        buyButtons.add(farmBuy);


        archeryBuildingRecruit=new recruitButton();
        barracksBuildingRecruit=new recruitButton();
        stableBuildingRecruit=new recruitButton();

        recruitButtons.add(archeryBuildingRecruit);
        recruitButtons.add(barracksBuildingRecruit);
        recruitButtons.add(stableBuildingRecruit);

    }

    public upgradeButton getArcheryBuildingUpgrade() {
        return archeryBuildingUpgrade;
    }

    public upgradeButton getBarracksBuildingUpgrade() {
        return barracksBuildingUpgrade;
    }

    public upgradeButton getStableBuildingUpgrade() {
        return stableBuildingUpgrade;
    }

    public upgradeButton getMarketUpgrade() {
        return marketUpgrade;
    }

    public MyButton getCloseButton() {
        return closeButton;
    }
    public upgradeButton getFarmUpgrade() {
        return farmUpgrade;
    }

    public BuyButton getArcheryBuildingBuy() {
        return archeryBuildingBuy;
    }

    public BuyButton getBarracksBuildingBuy() {
        return barracksBuildingBuy;
    }

    public BuyButton getStableBuildingBuy() {
        return stableBuildingBuy;
    }

    public BuyButton getMarketBuy() {
        return marketBuy;
    }

    public BuyButton getFarmBuy() {
        return farmBuy;
    }

    public recruitButton getArcheryBuildingRecruit() {
        return archeryBuildingRecruit;
    }

    public recruitButton getBarracksBuildingRecruit() {
        return barracksBuildingRecruit;
    }

    public recruitButton getStableBuildingRecruit() {
        return stableBuildingRecruit;
    }



    public ArrayList<upgradeButton> getUpgradeButtons() {
        return upgradeButtons;
    }

    public ArrayList<recruitButton> getRecruitButtons() {
        return recruitButtons;
    }

    public ArrayList<BuyButton> getBuyButtons() {
        return buyButtons;
    }

}
