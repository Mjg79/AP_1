import model.Block;
import model.Building;
import model.GilgArmy.Army;
import model.GilgArmy.Defense;

import java.util.ArrayList;

public class City {
    public ArrayList<Block> blocks = new ArrayList<>();
    private boolean flagArmy = false;
    private int IDArmy;
    private int score;
    private int scorePlus;
    private int money;
    public void addBlock(){
        Block block = new Block();
        this.blocks.add(block);
    }
    public void removeBlock(int index){
        blocks.set(index-1,null);
    }
    public void addHome(int index,int numOfFloors,int numOfUnits){
        blocks.get(index-1).addHome(numOfFloors,numOfUnits);
    }
    public void addArmy(int index){
        flagArmy = true;
        IDArmy = index-1;
        blocks.get(index-1).addArmy();
    }
    public boolean getArmyCheck(){
        return flagArmy;
    }
    public void addDefense(int index){
        blocks.get(index-1).addDefense();
    }
    public void addBazaar(int index){
        blocks.get(index-1).addBazaar();
    }
    public void upgradeBlock(int index){
        blocks.get(index-1).UpgradeBlock();
    }
    public void upgradeArmy(int index){
        blocks.get(index-1).UpgradeArmy();
    }
    public void upgradeHome(int index,int ID){
        blocks.get(index-1).UpgradeHome(ID);
    }
    public void upgradeDefense(int index){
        blocks.get(index-1).UpgradeDefense();
    }
    public void upgradeBazaar(int index,int unitID){
        blocks.get(index-1).UpgradeBazaar(unitID);
    }

    public void setMoney() {
        money = 0;
        for (Block block:blocks){
            for (Building building:block.getBuildings()) {
                money+=building.getMoney();
            }
        }
    }

    public void setScore() {
        score=0;
        for (Block block:blocks){
            for (Building building:block.getBuildings()) {
                score+=building.getScore();
            }
        }
    }
    public void attack(int blockID,City city) {
        Block block = city.blocks.get(blockID-1);
        Defense defense = block.getDefense();
        Block A = blocks.get(IDArmy);
        Army army = A.getArmy();
        if (defense.getNumWorker()==0){
            loot();
        }else {
            if (defense.getNumWorker()<=army.getNumOfWorkers()){
                block.removeDefense();

            }
        }
    }
}
