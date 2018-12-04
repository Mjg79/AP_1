package Model.Places.Workshop;

import Model.ElementAndBoxAndDirection.Element;
import Model.ProductsAndForage.Product;

import java.util.ArrayList;

public abstract class WorkShop extends Element {
    private boolean isInWorking = false;    private ArrayList<String> kindOfInputProduct;//to determine that what kind of input we need to get from wareHouse
    private String kindOfOutputProduct;//to determine that what kind of output we need to give to the map
    private ArrayList<Product> outputProduct;
    private int maxInputNumber;
    private int maxOutputNumber;
    private double startTimeForWorking;
    private double lastTimeForWorking;

    {
        //TODO: intialize all workshops
    }

    public void startWorking(ArrayList<Integer> count, double startTime) {
        boolean isItHaveOneZeroCount = false;
        for (Integer integer: count)
            if (integer == 0) {
                isItHaveOneZeroCount = true;
                break;
            }

            if (!isInWorking && !isItHaveOneZeroCount) {
             isInWorking = true;
             this.startTimeForWorking = startTime;
             this.lastTimeForWorking = startTime + 15;//every Work should be done at 15 seconds
         }

         //TODO: specific number of outputs
    }


    public ArrayList<String> getKindOfInputProduct() {
        return kindOfInputProduct;
    }

    public String getKindOfOutputProduct() {
        return kindOfOutputProduct;
    }

    public boolean checkWorkShopForDistributingOutputs(double time) {// checking it at turn function in map.java
       if (isInWorking && time > lastTimeForWorking) {
           isInWorking = false;
           return true;
       }
       return false;
    }

    public ArrayList<Product> distributeOutputs(double time) {
        return outputProduct;
    }

    public boolean getIsInWorking() {
        return isInWorking;
    }

    @Override
    public void move (double finalX, double finalY) {
        //nothing
    }

    @Override
    public void upgrade() {
       maxInputNumber++;
       maxOutputNumber++;
       level++;
    }
}
//TODO: check for TODO functions please !!