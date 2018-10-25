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

    public boolean UpgradeBlock(){
        if(level < 3){
            capacity += 5;
            level += 1;
            return true;
        }
        else
            return false;
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

    public boolean addHome(int numOfFloors, int numOfUnit) {
        Home home = new Home();
        if(numOfFloors < 3 | numOfFloors > 6 | numOfUnit > 4 | numOfUnit < 1)
            return false;
        for (int i = 0; i < numOfFloors; i++) {
            home.addFloor();
        }
        for (Floor f : home.getFloors()) {
            f.addUnit(numOfUnit);
        }
        buildings.add(home);
        return true;
    }

    public void addArmy() {
        buildings.add(new Army());
    }

    public boolean addDefense() {
        if(!flag_defense){
            flag_defense = true;
            buildings.add(new Defense());
            return true;
        }
        else
            return false;
    }

    public void addBazaar() {
        buildings.add(new Bazaar());
    }

    public boolean UpgradeHome(int homeID , int floorNum , int unitNum) {
        if(floorNum < 3 | floorNum > 6 | unitNum < 1 | unitNum > 4)
            return false;
        Home home = (Home) buildings.get(homeID);
        for(int i = 0 ; i < floorNum ; i++)
         home.addFloor();
        for (Floor f : home.getFloors())
            f.addUnit(unitNum);
        return true;
    }

    public boolean UpgradeArmy() {
            for(Building instance : buildings)
                if(instance instanceof Army)
                    if(((Army) instance).getLevel() < 5){
                        ((Army) instance).addLevel();
                        return true;
                    }
                    else
                        return false;
            return false;
    }

    public boolean UpgradeBazaar(int id) {
        Bazaar bazaar = (Bazaar) buildings.get(id);
        if(bazaar.getLevel() < 3){
            buildings.remove(id); //todo: why
            bazaar.addLevel();
            buildings.add(id, bazaar);
            return true;
        }
        else
            return false;
    }

    public boolean UpgradeDefense() {
        for(Building b : buildings)
            if(b instanceof Defense)
                if(((Defense) b).getLevel() < 5){
                    ((Defense) b).addLevel();
                    return true;
                }
                else
                    return false;
        return false;
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