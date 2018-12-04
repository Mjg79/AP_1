package Model.Places;

import Model.ElementAndBoxAndDirection.Element;

public class Well extends Element {
    private int current = 0;
    private boolean isInCharge = false;
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

    @Override
    public void upgrade() {
      level++ ;

    }

    public void chargeWell(double time) {
        if (current == 0 && !isInCharge) {
            isInCharge = true;
            firstTimeForCharge = time;
            lastTimeForCharge = time + 5;//in 5 seconds it charges itself completely
        }
    }

    public void disChargeWell () {
        if (current != 0 && haveOpportunityForExploitingFromWell) {
            current--;
        } else
            haveOpportunityForExploitingFromWell = false;
    }

    public void checkWell(double time) {
        if (time > lastTimeForCharge && isInCharge) {
            haveOpportunityForExploitingFromWell = true;
            isInCharge = false;
        }
    }

    public int getCurrent() {
        return current;
    }

}


//TODO:it's complete
