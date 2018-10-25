import model.Block;

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
        blocks.set(index,null);
    }
    public void addHome(int index,int numOfFloors,int numOfUnits){
        blocks.get(index).addHome(numOfFloors,numOfUnits);
    }
    public void addArmy(int index){
        blocks.get(index).addArmy();
    }
    public void addDefense(int index){
        blocks.get(index).addDefense();
    }
    public void addBazaar(int index){
        blocks.get(index).addBazaar();
    }


}
