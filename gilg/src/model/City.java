package model;

import model.GilgArmy.Army;
import model.GilgArmy.Defense;
import model.Residency.Home;

import java.util.ArrayList;

public class City {
    public ArrayList<Block> blocks = new ArrayList<>();
    private boolean flagArmy = false;
    private int IDArmy;
    private int score;
    private int scorePlus;
    private int money;
    private int moneyPlus;
    public void addBlock(){
        Block block = new Block();
        this.blocks.add(block);
    }
    public void removeBlock(int index){
        blocks.set(index-1,null);
    }
    public boolean addHome(int index,int numOfFloors,int numOfUnits){
        if(blocks.get(index-1).addHome(numOfFloors,numOfUnits))
            return true;
        else
            return false;
    }
    public boolean addArmy(int index){
        if(!flagArmy){
            flagArmy = true;
            IDArmy = index-1;
            blocks.get(index-1).addArmy();
            return true;
        }
        else
            return false;
    }
    public boolean getArmyCheck(){
        return flagArmy;
    }
    public boolean addDefense(int index){
        if(blocks.get(index-1).addDefense())
            return true;
        else
            return false;
    }
    public void addBazaar(int index){
        blocks.get(index-1).addBazaar();
    }
    public boolean upgradeBlock(int index){
        if(blocks.get(index-1).UpgradeBlock())
            return true;
        else
            return false;
    }
    public boolean upgradeArmy(int index){
        if(blocks.get(index-1).UpgradeArmy())
            return true;
        else
            return false;
    }
    public boolean upgradeHome(int index,int ID, int numberOfFloorToAdd, int numberOfUnitToAdd){
        if(blocks.get(index-1).UpgradeHome(ID, numberOfFloorToAdd, numberOfUnitToAdd))
            return true;
        else
            return false;
    }
    public boolean upgradeDefense(int index){
        if(blocks.get(index-1).UpgradeDefense())
            return true;
        else
            return false;
    }
    public boolean upgradeBazaar(int index,int unitID){
        if(blocks.get(index-1).UpgradeBazaar(unitID))
            return true;
        else
            return false;
    }
    public boolean removeBazaarOrArmyOrDefense(int blockID,int unitID){
        if (blocks.get(blockID-1).getBuildings().get(unitID) instanceof Home) {
            return false;
        }else if (blocks.get(blockID-1).getBuildings().get(unitID) instanceof Bazaar){
            blocks.get(blockID-1).removeBazaar(unitID);
        }else if (blocks.get(blockID-1).getBuildings().get(unitID) instanceof Army){
            blocks.get(blockID-1).removeArmy();
        }else {
            blocks.get(blockID-1).removeDefense();
        }
        return true;
    }

    public boolean upgradeArmyOrDefenseOrbazaar(int blockID,int unitID){
        if (blocks.get(blockID-1).getBuildings().get(unitID) instanceof Army) {
            if(upgradeArmy(blockID))
                return true;
            else
                return false;
        }else if (blocks.get(blockID-1).getBuildings().get(unitID) instanceof Defense){
            if(upgradeDefense(blockID))
                return true;
            else
                return false;
        }else if (blocks.get(blockID-1).getBuildings().get(unitID) instanceof Bazaar){
            if(upgradeBazaar(blockID, unitID))
                return true;
            else
                return false;
        }
        return true;
    }

    public void setMoney() {
        money = moneyPlus;
        for (Block block:blocks){
            for (Building building:block.getBuildings()) {
                money+=building.getMoney();
            }
        }
    }


    public int getScore() {
        return score;
    }

    public int getMoney() {
        return money;
    }

    public boolean attack(int blockID, City city) {
        Block block = city.blocks.get(blockID-1);
        Defense defense = block.getDefense();
        Block A = blocks.get(IDArmy);
        Army army = A.getArmy();
        if (defense.getLevel()<=army.getLevel()){
            for (Building building:block.getBuildings()) {
                scorePlus+=building.getScore();
            }
            city.removeBlock(blockID);
            return true;
        }
        else
            return false;
    }
    public boolean loot (int blockID, City city) {
        Block block = city.blocks.get(blockID-1);
        if (block.getDefense()==null){
            moneyPlus+=block.getBuildings().size()*500;
            return true;
        }
        else
            return false;
    }


    public void setScore(){
        int score = scorePlus;
        for (Block b : blocks)
            score += b.scoreBlock();
    }
}
