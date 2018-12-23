package Model.Transportation;
import Model.ElementAndBoxAndDirection.Box;
import Model.ElementAndBoxAndDirection.Element;

import java.util.ArrayList;

public class Helicopter extends Element {
    private double volumeOfBoxes;
    private double current;
    private ArrayList<Box> items = new ArrayList<>();
    private ArrayList<Class> products = new ArrayList<>();//products that are available in the market of city
    private int numOfBoxes;
    private int cost = 0;
    private boolean isAvailable = true;
    private double buyTime;
    private double startTime;
    private double endTime;
    private int updateCost;

    public Helicopter(ArrayList<Class> products) {
        this.products = products;
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


    public double getVolumeOfBoxes() {
        return volume;
    }

    public void setVolumeOfBoxes(double volume) {
        this.volumeOfBoxes = volume;
    }

    public double getCurrent() {
        return current;
    }

    public void setCurrent(double current) {
        this.current = current;
    }

    public ArrayList<Box> getItems() {
        return items;
    }

    public void setItems(ArrayList<Box> items) {
        this.items = items;
    }

    public ArrayList<Class> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<Class> products) {
        this.products = products;
    }

    public int getNumOfBoxes() {
        return numOfBoxes;
    }

    public void setNumOfBoxes(int numOfBoxes) {
        this.numOfBoxes = numOfBoxes;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public double getBuyTime() {
        return buyTime;
    }

    public void setBuyTime(double buyTime) {
        this.buyTime = buyTime;
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

    public void setUpdateCost(int updateCost) {
        this.updateCost = updateCost;
    }

    public void addBox(Box box){
        items.add(box);
        cost+=box.getCost();// must have a method that return the value
    }

    public void addToABox(Element element,int index , int count){
        for (int i=0;i<count;i++) {
            items.get(index).addElement(element);
        }
    }

    public void buy(double time){//need to be synced with boxes
        startTime = time;
        endTime = time+buyTime;
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
        for(Box box:items){
            price += box.getCost();
        }
        return price;
    }
}
