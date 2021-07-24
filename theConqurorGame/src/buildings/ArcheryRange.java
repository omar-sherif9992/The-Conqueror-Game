package buildings;

import exceptions.BuildingInCoolDownException;
import exceptions.MaxLevelException;
import exceptions.MaxRecruitedException;
import units.Archer;
import units.Unit;

public class ArcheryRange extends MilitaryBuilding {

    public ArcheryRange() {
        super(1500, 800, 400);
    }

    public void upgrade() throws BuildingInCoolDownException, MaxLevelException {
        super.upgrade();
        if (this.getLevel() == 1) {
            super.setLevel(2);
            super.setUpgradeCost(700);
            super.setRecruitmentCost(450);
        } else if (this.getLevel() == 2) {
            super.setLevel(3);
            super.setRecruitmentCost(500);
        }
    }

    @Override
    public Unit recruit() throws BuildingInCoolDownException, MaxRecruitedException {
        if (this.isCoolDown())
            throw new BuildingInCoolDownException("Building is cooling down, please wait for next turn");

        if (this.getCurrentRecruit() >= this.getMaxRecruit())
            throw new MaxRecruitedException("You have reached the maximum number of recruits , please wait for next turn");


        Unit unit = null;
        switch (this.getLevel()) {
            case 1:
            case 2:
                unit = new Archer(this.getLevel(), 60, 0.4, 0.5, 0.6);
                break;
            case 3:
                unit = new Archer(3, 70, 0.5, 0.6, 0.7);
                break;
        }
        this.setCurrentRecruit(this.getCurrentRecruit() + 1);
        return unit;
    }
}
