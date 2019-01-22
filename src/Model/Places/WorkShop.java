package Model.Places;

import Model.ElementAndBoxAndDirection.Element;
import Model.Products.Product;

import java.util.ArrayList;

public class WorkShop extends Element {
    private boolean isInWorking = false;
    protected ArrayList<String> nameOfInputProducts = new ArrayList<>();
    private int possibleNumberOfOutputProducts;
    protected int maxNumberOfProducts;
    protected String nameOfOutputProduct;
    protected double timeLastingForWorking = 1;
    protected ArrayList<Product> outputProduct = new ArrayList<>();
    private double endOfTimeForWorking;


    public WorkShop(String name, int x, int y) {
        this.checkCakeBakery(name);
        this.checkCookieBakery(name);
        this.checkEggPowderedPlant(name);
        this.checkWeavingFactory(name);
        this.checkSewingFactory(name);
        this.checkSpinnery(name);
        this.x = x;
        this.y = y;
    }



    private void checkSpinnery(String name) {
    }

    private void checkCakeBakery(String name) {
        if (name.equals("CakeBakery")) {
            nameOfInputProducts.add("cookie");
            nameOfInputProducts.add("flour");
            nameOfOutputProduct = "cake";
            maxNumberOfProducts = 1;
            this.name = "CakeBakery";
        }
    }

    private void checkCookieBakery(String name) {
        if (name.equals("CookieBakery")) {
            this.name = "cookieBakery";
            nameOfInputProducts.add("powderedEgg");
            maxNumberOfProducts = 1;
            nameOfOutputProduct = "cookie";
        }
    }

    private void checkEggPowderedPlant(String name) {
        if (name.equals("EggPowderedPlant")) {
            this.name = "EggPowderedPlant";
            nameOfInputProducts.add("egg");
            maxNumberOfProducts = 1;
            nameOfOutputProduct = "powderedEgg";
            x = 250;
            y = 250;
        }
    }

    private void checkSewingFactory(String name) {
        if (name.equals("SewingFactory")) {
            nameOfInputProducts.add("wool");
            nameOfOutputProduct = "sewing";
            maxNumberOfProducts = 1;
            this.name = "sewingFactory";
        }
    }


    private void checkWeavingFactory(String name) {
        if (name.equals("WeavingFactory")) {
            this.name = "WeavingFactory";
            nameOfInputProducts.add("sewing");
            nameOfOutputProduct = "cloth";
            maxNumberOfProducts = 1;
        }
    }

    public void setPossibleNumberOfOutputProducts(int num) {
        this.possibleNumberOfOutputProducts = num;
    }

    public int getPossibleNumberOfOutputProducts() {
        return possibleNumberOfOutputProducts;
    }

    public ArrayList<String> getNameOfInputProducts() {
        return nameOfInputProducts;
    }


    public int getMaxNumberOfProducts() {
        return maxNumberOfProducts;
    }

    public void startWorking(double time) {
        isInWorking = true;
        endOfTimeForWorking = time + timeLastingForWorking;
        outputProduct.clear();
    }

    public boolean isInWorking() {
        return isInWorking;
    }

    public boolean checkWorkShopForDistributingOutputs(double time) {// checking it at turn function in map.java
        if (isInWorking && time > endOfTimeForWorking) {
            isInWorking = false;
            return true;
        }
        return false;
    }

    public ArrayList<Product> distributeOutputs(double time) {
        outputProduct.clear();
        if (name.equals("CakeBakery")) {
            for (int i = 0; i < this.getPossibleNumberOfOutputProducts(); i++)
                this.outputProduct.add(new Product(time, "cake"));
            return outputProduct;
        }
        if (name.equals("CookieBakery")) {
            for (int i = 0; i < this.getPossibleNumberOfOutputProducts(); i++)
                this.outputProduct.add(new Product(time, "cookie"));
            return outputProduct;
        }
        if (name.equals("EggPowderedPlant")) {
            for (int i = 0; i < this.getPossibleNumberOfOutputProducts(); i++)
                this.outputProduct.add(new Product(time, "powderedEgg"));
            return outputProduct;
        }
        if (name.equals("SewingFactory")) {
            for (int i = 0; i < this.getPossibleNumberOfOutputProducts(); i++)
                this.outputProduct.add(new Product(time, "sewing"));
            return outputProduct;
        }
        if (name.equals("WeavingFactory")) {
            for (int i = 0; i < this.getPossibleNumberOfOutputProducts(); i++)
                this.outputProduct.add(new Product(time, "cloth"));
            return outputProduct;
        }

        return null;
    }


    @Override
    public void move (int finalX, int finalY) {

    }

    @Override
    public  boolean upgrade() {
        if (level < 3) {
            maxNumberOfProducts++;
            moneyForUpgrading += 100;
            level++;
            return true;
        }
        return false;
    }

}
