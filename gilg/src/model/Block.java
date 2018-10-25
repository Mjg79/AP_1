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
    int ID ;

    public void UpgradeBlock(){
        capacity += 5;
        if(level < 3)
        level += 1;
    }

    public int getCapacity(){
        return capacity;
    }

    public ArrayList<Building> getBuildings(){
        return buildings;
    }

    public boolean canAdd(){
        if(capacity > buildings.size())
            return true;
        return false;
    }

    public void addHome(int numOfFloors, int numOfUnit) {
        Home home = new Home();
        for (int i = 0; i < numOfFloors - 1; i++) {
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
        if(!flag_defense) {
            flag_defense = true;
            buildings.add(new Defense());
        }
    }

    public void addBazaar() {
        buildings.add(new Bazaar());
    }

    public void UpgradeHome(int homeID , int floorNum , int unitNum) {
        Home home = (Home) buildings.get(homeID);
        for(int i = 0 ; i < floorNum ; i++)
         home.addFloor();
        for (Floor f : home.getFloors())
            f.addUnit(unitNum);
    }

    public void UpgradeArmy() {
            for(Building instance : buildings)
                if(instance instanceof Army)
                    ((Army) instance).addLevel();
    }

    public void UpgradeBazaar(int id) {
     for(Building b : buildings)
         if(b.getID() == id)
             ((Bazaar) b).addLevel();
    }

    public void UpgradeDefense() {
        for(Building b : buildings)
            if(b instanceof Defense) {
                ((Defense) b).addLevel();
                break;
            }
    }

    public void removeBazaar(int id){
        for(Building b : buildings)
            if(b.getID() == id)
             b = null;
    }

    public void removeArmy(){
        for(Building b : buildings)
            if(b instanceof Army)
                b = null;
    }

    public void removeDefense(){
        for(Building b : buildings)
            if(b instanceof Defense)
                b = null;
    }
    public Army getArmy(){
        for(Building b : buildings)
            if(b instanceof Army)
                return (Army) b;
            return null;
    }

    public Defense getDefense(){
        for(Building b : buildings)
            if(b instanceof Defense)
                return (Defense) b;
            return null;
    }

    public int scoreBlock(){
        int score = 0;
        for(Building b : buildings) {
            if (b instanceof Home)
                score += ((Home) b).homeScore();
            if(b instanceof Army)
                score += ((Army) b).scoreArmy();
            if(b instanceof Defense)
                score += ((Defense) b).scoreDefense();
            if(b instanceof Bazaar)
                score += ((Bazaar) b).bazaarScore();
        }
        return score;
    }



}