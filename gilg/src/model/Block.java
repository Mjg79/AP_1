package model;

import model.GilgArmy.Army;
import model.GilgArmy.Defense;
import model.Residency.Floor;
import model.Residency.Home;

import java.util.ArrayList;

public class Block {
    private int level ;
    private ArrayList <Building> buildings = new ArrayList<>();
    boolean flag_defense = false;
    int ID ;
    private int limit = 15;

    public void addHome(int numOfFloors , int numOfUnit){
        Home home = new Home();
        for (int i = 0 ; i < numOfFloors ; i++) {
            home.addFloor();
        }
        for (Floor f : home.getFloors()) {
            f.addUnit(numOfUnit);
        }
      buildings.add(home);
    }

    public void addArmy() {
        buildings.add(new Army());
    }

    public void addDefense() {
        flag_defense = true;
        buildings.add(new Defense());
    }

    public void addBazaar() {
        buildings.add(new Bazaar());
    }

    public void UpgradeHome(int homeID) {
        Home home = (Home) buildings.get(homeID);
        home.addFloor();
        for(Floor f : home.getFloors())
            f.addUnit(1);
    }

    public void UpgradeArmy(){

    }
    public void upgrade(){
        this.level++;
        this.limit+=5;
    }
}
