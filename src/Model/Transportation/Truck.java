package Model.Transportation;

import Model.ElementAndBoxAndDirection.Box;
import Model.ElementAndBoxAndDirection.Element;

import java.util.ArrayList;

public class Truck extends Element {
    private ArrayList<Box> boxes = new ArrayList<>();
    private int wallet = 0;
    private int numOfBoxes = 2;
    private double sellTime;
    private double startTimeForSellingElements;
    private double endTimeForSellingElements;


    @Override
    public void move(double finalX, double finalY) {
        // it's for movement of it in map
    }

    @Override
    public void upgrade() {
        // it needs some detail for increasing volume,numOfboxes and decreasing sellTime and so on
    }

    public ArrayList<Box> getBoxes() {
        return boxes;
    }

    public void setBoxes(ArrayList<Box> boxes) {
        this.boxes = boxes;
    }

    public int getWallet() {
        return wallet;
    }

    public void setWallet(int wallet) {
        this.wallet = wallet;
    }

    public int getNumOfBoxes() {
        return numOfBoxes;
    }

    public void setNumOfBoxes(int numOfBoxes) {
        this.numOfBoxes = numOfBoxes;
    }

    public double getSellTime() {
        return sellTime;
    }

    public void setSellTime(double sellTime) {
        this.sellTime = sellTime;
    }

    public double getStartTimeForSellingElements() {
        return startTimeForSellingElements;
    }

    public void setStartTimeForSellingElements(double startTimeForSellingElements) {
        this.startTimeForSellingElements = startTimeForSellingElements;
    }

    public double getEndTimeForSellingElements() {
        return endTimeForSellingElements;
    }

    public void setEndTimeForSellingElements(double endTimeForSellingElements) {
        this.endTimeForSellingElements = endTimeForSellingElements;
    }
}
