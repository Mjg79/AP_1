package model.GilgArmy;
import java.lang.Math;
import model.Building;

public class Army extends Building {
    private int level=1;
    private int numOfSoldiers = 100 ;
    public void addLevel(){
        this.level += 1;
        this.numOfSoldiers += 10;
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

    public int scoreArmy(){
        return (int) Math.pow(10 , getDay());
    }

}
