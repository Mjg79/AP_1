package Model.Transportation;
import Model.ElementAndBoxAndDirection.Box;
import Model.ElementAndBoxAndDirection.Element;

import java.util.ArrayList;

public class Helicopter extends Element {
    private double volume;
    private double current;
    private ArrayList<Box> items = new ArrayList<>();
    private ArrayList<Class> products = new ArrayList<>();//products that are available in the market of city
    private int numOfBoxes;
    private int cost = 0;
    private boolean isAvailable = true;
    private int buyTime;
    private int startTime;
    private int endTime;


    public void buy(Class productClass,int number){

    }

    @Override
    public void move(double finalX, double finalY) {

    }

    @Override
    public void upgrade() {
        // decrease buyTime and increase volume for boxes
    }

    public int getUpdateCost(){
        return 150;
        //FIXME
    }
}
