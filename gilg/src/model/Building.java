package model;

public abstract class Building {
    private int ID;
    private int blockID;
    int day=1;
    int money = 0;
    int score = 0;

    public int getID() {
        return ID;
    }

    public int getBlockID() {
        return blockID;
    }

    public int getDay() {
        return day;
    }

    public int getMoney() {
        return money;
    }

    public int getScore() {
        return score;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public void setDay() {
        this.day++;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setBlockID(int blockID) {
        this.blockID = blockID;
    }
}
