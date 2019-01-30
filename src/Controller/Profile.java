package Controller;

import java.io.Serializable;

public class Profile implements Serializable {
    private String name;
    private String userName;
    private int transaction = 0;
    private int budget = 120;
    private int numOfGames = 0;

    public Profile(String name, String userName) {
        this.userName = userName;
        this.name = name;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTransaction() {
        return transaction;
    }

    public void setTransaction(int transaction) {
        this.transaction = transaction;
    }

    public int getBudget() {
        return budget;
    }

    public void setBudget(int budget) {
        this.budget = budget;
    }

    public int getNumOfGames() {
        return numOfGames;
    }

    public void increaseNumOfGames() {
        this.numOfGames ++;
    }
}