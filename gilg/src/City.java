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


}
