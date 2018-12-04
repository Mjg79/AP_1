package Model.Animal;

import Model.ProductsAndForage.Product;

import java.util.ArrayList;

public class Cat extends Animal {
    private double normalSpeed = 1; // 3 cell per 0.33 second
    private double speedForCollecting = 3;
    private ArrayList<Product> collectProducts;
    {
      level = 0;
    }

    @Override
    public void move(double finalX, double finalY)  {
        //nothing
    }

    //TODO: for cat the move function differs from other elements
    public void move(double finalX, double finalY, String condition) {
       if (condition.equals("pickup")) {
           //TODO: pickup product from map by BFS from x,y to finalX,finalY
       }

       if (condition.equals("random")) {
           //TODO: move randomly
       }
    }

    @Override
    public void upgrade() {
        level++;
    }

    public double getNormalSpeed() {
        return normalSpeed;
    }

    public void setNormalSpeed(double normalSpeed) {
        this.normalSpeed = normalSpeed;
    }

    public double getSpeedForCollecting() {
        return speedForCollecting;
    }

    public void setSpeedForCollecting(double speedForCollecting) {
        this.speedForCollecting = speedForCollecting;
    }

    public void setCollectProducts(ArrayList<Product> products) {
        this.collectProducts = products;
    }

    public ArrayList<Product> giveProductToWareHouse() {
        return collectProducts;
    }
}
