package Model.Animal.LiveStocks;

import Model.Animal.Animal;
import Model.Products.LiveStockProducts.Egg;
import Model.Products.LiveStockProducts.Feather;
import Model.Products.LiveStockProducts.Horn;
import Model.Products.Product;

public class LiveStock extends Animal {
    private double speed = 1; // 1 cell per 0.33 second
    private double startTimeForEatingForage = -5;//just for denying some requests from the start of game
    private double startTimeBeingInMap;
    private static final double maxHungerLevel = 8;
    private double hungerLevel = maxHungerLevel;
    private boolean mustEatForage = false;
    private AnimalType type;
    private double priceForSale;


    public AnimalType getType() {
        return type;
    }

    public double getStartTimeForEatingForage() {
        return this.startTimeForEatingForage;
    }


    public void setStartTimeForEatingForage(double time) {
        this.startTimeForEatingForage = time;
    }

    public LiveStock(double startBeingInMap, String type) {
        this.startTimeBeingInMap = startBeingInMap;

        if (type.equals("chicken")) {
            this.type = AnimalType.chicken;
            price = 100;
            volume = 2;
            level = 0;
            name = "chicken";
            priceForSale = 50;
        }

        if (type.equals("cow")) {
            this.type = AnimalType.cow;
            price = 10000;
            volume = 10;
            level = 0;
            name = "cow";
            priceForSale = 5000;
        }

        if (type.equals("ostrich")) {
            this.type = AnimalType.ostrich;
            price = 1000;
            volume = 5;
            level = 0;
            name = "ostrich";
            priceForSale = 500;
        }
    }

    public Product releaseProduct(double time) {
        if (type.equals(AnimalType.chicken))
            return new Egg(time);
        if (type.equals(AnimalType.ostrich))
            return new Feather(time);
        return new Horn(time);
    }


    @Override
    public void move(double finalX, double finalY) {

    }


    public void mustEatForage(boolean check) {
        this.mustEatForage = check;
    }

    public boolean isMustEatForage() {
        return this.mustEatForage;
    }

    public void changeHungerLevel(double number) {
        hungerLevel += number;
    }

    public double getHungerLevel() {
        return hungerLevel;
    }

    public void checkLiveStock() {
        if(this.hungerLevel <= 3)
            this.mustEatForage(true);
        if(this.hungerLevel >= 7)
            this.mustEatForage(false);
    }


    public double getStartTimeBeingInMap() {
        return startTimeBeingInMap;
    }


    public static void main(String[] args) {
        AnimalType type = AnimalType.cow;
        System.out.println(type);
    }

}
