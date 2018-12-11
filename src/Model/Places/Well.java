package Model.Places;

import Model.ElementAndBoxAndDirection.Element;

public class Well extends Element {
    private int current = 0;
    private boolean isInCharging = false;
    private double firstTimeForCharge;
    private double lastTimeForCharge;
    private boolean haveOpportunityForExploitingFromWell = true;// make sure that we can use from well

    {
        volume = 5;
        level = 0;

    }

    @Override
    public void move(double finalX, double finalY) {
        //TODO: nothing
    }

    public boolean isHaveOpportunityForExploitingFromWell() {
        return haveOpportunityForExploitingFromWell;
    }

    @Override
    public void upgrade() {
        level++;

    }

    public void chargeWell(double time) {
        if (current == 0 && !isInCharging) {
            isInCharging = true;
            firstTimeForCharge = time;
            lastTimeForCharge = time + 5;//in 5 seconds it charges itself completely
        }
    }

    public boolean canDisChargeWell() {
        if (current != 0 && haveOpportunityForExploitingFromWell && !isInCharging) {
            current--;
            return true;
        } else
            haveOpportunityForExploitingFromWell = false;
        return false;
    }

    public void checkWell(double time) {
        if (time > lastTimeForCharge && isInCharging) {
            haveOpportunityForExploitingFromWell = true;
            isInCharging = false;
        }
    }

    public int getCurrent() {
        return current;
    }

}


//TODO:it's complete
