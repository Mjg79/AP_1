package model.GilgArmy;

import model.Building;

public class Army extends Building {
    private int level=1;
    private int numOfSoldiers ;
    public void addLevel(){
        this.level+=1;
        this.numOfSoldiers+=20;
    }

    public void setNumOfSoldiers(int numOfSoldiers) {
        this.numOfSoldiers = numOfSoldiers;
    }

    public int getLevel() {
        return level;
    }

    public int getNumOfWorkers() {
        return numOfSoldiers;
    }
}
