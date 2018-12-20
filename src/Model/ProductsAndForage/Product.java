package Model.ProductsAndForage;

import Model.ElementAndBoxAndDirection.Element;

public class Product extends Element {
    private ProductTypes type;
    private double firstTime;
    private double secondTime;

    public Product(ProductTypes type, double x, double y){
        this.x = x;
        this.y = y;
        this.type = type;
    }

    public void setInMap(double firstTime) {
       this.firstTime = firstTime;
       this.secondTime = firstTime + 10;
    }


    public double getFirstTime() {
        return firstTime;
    }

    public double getSecondTime() {
        return secondTime;
    }


    @Override
    public void move(double finalX, double finalY) {
        //TODO:nothing
    }

    @Override
    public void upgrade() {
        //nothing
    }
}
