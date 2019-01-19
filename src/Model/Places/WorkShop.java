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
    protected double timeLastingForWorking = 12;
    protected ArrayList<Product> outputProduct;
    private double endOfTimeForWorking;


    public WorkShop(String name) {
        this.checkCakeBakery(name);
        this.checkCookieBakery(name);
        this.checkEggPowderedPlant(name);
        this.checkWeavingFactory(name);
        this.checkSewingFactory(name);
        this.checkSpinnery(name);
    }

    private void checkSpinnery(String name) {
    }

    private void checkCakeBakery(String name) {
        if (name.equals("cakeBakery")) {
            nameOfInputProducts.add("cookie");
            nameOfInputProducts.add("flour");
            nameOfOutputProduct = "cake";
            maxNumberOfProducts = 1;
            this.name = "CakeBakery";
        }
    }

    private void checkCookieBakery(String name) {
        if (name.equals("cookieBakery")) {
            this.name = "cookieBakery";
            nameOfInputProducts.add("powderedEgg");
            maxNumberOfProducts = 1;
            nameOfOutputProduct = "cookie";
        }
    }

    private void checkEggPowderedPlant(String name) {
        if (name.equals("eggPowderedPlant")) {
            this.name = "EggPowderedPlant";
            nameOfInputProducts.add("egg");
            maxNumberOfProducts = 1;
            nameOfOutputProduct = "powderedEgg";
        }
    }

    private void checkSewingFactory(String name) {
        if (name.equals("sewingFactory")) {
            nameOfInputProducts.add("wool");
            nameOfOutputProduct = "sewing";
            maxNumberOfProducts = 1;
            this.name = "sewingFactory";
        }
    }


    private void checkWeavingFactory(String name) {
        if (name.equals("weaving Factory")) {
            this.name = "weavingFactory";
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
        if (name.equals("cakeBakery")) {
           for (int i = 0; i < this.getPossibleNumberOfOutputProducts(); i++)
               this.outputProduct.add(new Product(time, "cake"));
           return outputProduct;
       }
       if (name.equals("cookieBakery")) {
           for (int i = 0; i < this.getPossibleNumberOfOutputProducts(); i++)
               this.outputProduct.add(new Product(time, "cookie"));
           return outputProduct;
       }
       if (name.equals("eggPowderedPlant")) {
           for (int i = 0; i < this.getPossibleNumberOfOutputProducts(); i++)
               this.outputProduct.add(new Product(time, "powderedEgg"));
           return outputProduct;
       }
       if (name.equals("sewingFactory")) {
           for (int i = 0; i < this.getPossibleNumberOfOutputProducts(); i++)
               this.outputProduct.add(new Product(time, "sewing"));
           return outputProduct;
       }
       if (name.equals("weavingFactory")) {
           for (int i = 0; i < this.getPossibleNumberOfOutputProducts(); i++)
               this.outputProduct.add(new Product(time, "cloth"));
           return outputProduct;
       }

       return null;
    }


    @Override
    public void move (double finalX, double finalY) {

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
