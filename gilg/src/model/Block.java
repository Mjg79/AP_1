package model;

import model.GilgArmy.Army;
import model.GilgArmy.Defense;
import model.Residency.Floor;
import model.Residency.Home;

import java.util.ArrayList;

public class Block {
    private int level = 1;
    private ArrayList<Building> buildings = new ArrayList<>();
    private int capacity = 15;
    boolean flag_defense = false;

    private Army army = new Army();
    int ID ;
    private int limit = 15;


    public void UpgradeBlock(){
        capacity += 5;
        if(level < 3)
        level += 1;
    }

    public int getCapacity(){
        return capacity;
    }

    public boolean canAdd(){
        if(capacity > buildings.size())
            return true;
        return false;
    }

    public void addHome(int numOfFloors, int numOfUnit) {
        Home home = new Home();
        for (int i = 0; i < numOfFloors; i++) {
            home.addFloor();
        }
        for (Floor f : home.getFloors()) {
            f.addUnit(numOfUnit);
        }
        buildings.add(home);
    }

    public void addArmy() {
        army = new Army();
        buildings.add(army);
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
        for (Floor f : home.getFloors())
            f.addUnit(1);
    }

    public void UpgradeArmy() {
        if (army != null)
            army.addLevel();
    }

    public void UpgradeBazaar(int id) {
        Bazaar bazaar = (Bazaar) buildings.get(id);
        buildings.remove(id);
        bazaar.addLevel();
        buildings.add(id, bazaar);
    }

    public void UpgradeDefense(int id) {
        Defense defense = null;
        for (Building b : buildings) {
            if (b.getID() == id) {
                defense = (Defense) b;
                defense.addLevel();
                break;
            }
        }
    }

//    public void remove
    public void upgrade(){
        this.level++;
        this.limit+=5;
    }
}
