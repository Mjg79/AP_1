package Model.Transportation;

import Model.ElementAndBoxAndDirection.Box;
import Model.ElementAndBoxAndDirection.Element;

import java.util.ArrayList;

public class Truck extends Element {
    private ArrayList<Box> boxes = new ArrayList<>();
    private int wallet = 0;
    private int numOfBoxes = 2;
    private double sellTime;
    private double startTime;
    private double endTime;
    private boolean isAvailable = true;


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

    public double getStartTime() {
        return startTime;
    }

    public void setStartTime(double startTime) {
        this.startTime = startTime;
    }

    public double getEndTime() {
        return endTime;
    }

    public void setEndTime(double endTime) {
        this.endTime = endTime;
    }

    public void removeBox(Box box){
        boxes.remove(box);
    }

    public void removeBox(int index){
        boxes.remove(index);
    }

    public void addBox(Box box){
        boxes.add(box);
        wallet+=box.getCost();// must have a method that return the value
    }

    public void addToABox(Element element,int index , int count){
        for (int i=0;i<count;i++) {
            boxes.get(index).addElement(element);
        }
    }

    public void sell(double time){//need to be synced with boxes
        startTime = time;
        endTime = time+sellTime;
        isAvailable = false;
    }

    public boolean checkDone(double time){
        if (time >= endTime){
            isAvailable = true;
            endTime=0;
            startTime=0;
        }
        return isAvailable;
    }

    public int price(){
        int price = 0;
        for(Box box:boxes){
            price += box.getCost();
        }
        return price;
    }
}
