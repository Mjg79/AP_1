package Model.Animal.LiveStocks;

import Model.Animal.Animal;

public class LiveStock extends Animal {
    private double speed = 1; // 1 cell per 0.33 second
    private boolean isEatingForage = false;
    private double startTimeForEatingForage;
    private boolean isDead = false;
    private double startTimeBeingInMap;
    private double hungerLevel = 8;
    private static final double maxHungerLevel = 8;
    private double firstTimeReleasingEgg;
    //TODO:check out for releasing egg:how and when continuously

    public double getStartTimeBeingInMap() {
        return startTimeBeingInMap;
    }

    public LiveStock(double startBeingInMap) {
        this.startTimeBeingInMap = startBeingInMap;
    }

    //check it out: private egg EggProduct


    @Override
    public void move(double finalX, double finalY) {
        //nothing
    }
    //TODO:function move for live stocks differ from other elements
    public void move(double finalX, double finalY, String condition) {
        if(condition.equals("emergency")) {
            //TODO:BFS from X,Y to finalX,finalY
        }
        if (condition.equals("random")) {
            //TODO:move randomly tu finalX, finalY
        }
    }

    public void setIsEatingForage(boolean check) {
        isEatingForage = check;
    }

    public void setHungerLevel() {
        hungerLevel--;
    }

    public void setFirstTimeReleasingEgg(double time) {
        firstTimeReleasingEgg = time;
    }

    public double getFirstTimeReleasingEgg() {
        return firstTimeReleasingEgg;
    }

    //TODO:check this function later for checking in turn that uses in map
    public void checkLiveStock() {
        if (isEatingForage) {
            //not move
        }

        else
        {
            //move it
        }
    }

    public void setIsDead(boolean check) {
        isDead = check;
    }

    public boolean getIsDead() {
        return isDead;
    }

    @Override
    public void upgrade() {
        //nothing
    }
}
//TODO: it's complete