package model;

public class Bazaar extends Building {
    private int level =1;
    private int numOfWorkers = 20;

    public void addLevel(){
        this.level+=1;
        this.numOfWorkers+=20;
    }

    public int getLevel() {
        return level;
    }

    public int getNumOfWorkers() {
        return numOfWorkers;
    }
}