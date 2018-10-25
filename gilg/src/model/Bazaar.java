package model;
import java.lang.Math;
public class Bazaar extends Building {
    private int level =1;
    private int numOfWorkers = 50;
    private static double personScore = 1;
    public void addLevel(){
        this.level += 1;
        this.numOfWorkers += 20;
        personScore *= 1.2 ;
    }

    public int getLevel() {
        return level;
    }

    public int getNumOfWorkers() {
        return numOfWorkers;
    }

    public double bazaarScore(){
        return  Math.pow(5 , getDay()) + numOfWorkers * Math.pow(personScore , getDay());
    }
}
