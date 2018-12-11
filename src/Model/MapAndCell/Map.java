package Model.MapAndCell;

import java.util.ArrayList;

import Model.Animal.*;
import Model.Animal.LiveStocks.*;
import Model.Animal.WildAnimals.WildAnimal;
import Model.ElementAndBoxAndDirection.Element;
import Model.Places.WareHouse;
import Model.Places.Well;
import Model.ProductsAndForage.Product;
import Model.ProductsAndForage.Forage.Forage;
import Model.Transportation.Truck;
import java.util.Date;
import java.util.Iterator;

public class Map {
    private ArrayList<LiveStock> liveStocks = new ArrayList<>();
    private ArrayList<WildAnimal> wildAnimals = new ArrayList<>();
    private ArrayList<Cat> cats = new ArrayList<>();
    private ArrayList<Dog> dogs = new ArrayList<>();
    private ArrayList<Forage> forages = new ArrayList<>();
    private ArrayList<Product> products = new ArrayList<>();
    private Cell[][] cells = new Cell[41][41];
    private WareHouse wareHouse;
    private Well well;
    private Truck truck;
    private double farmTime = 0;
    private int budget = 1000;
///////////////////////it's not complete/////////////////////////

    public void budgetDecreament(int amount) {
        budget -= amount;
    }

    //////////////////////////////BUY_ANIMAL_BY_STRING///////////////////
    public void addChicken() {
        if (budget >= 100) {
            Chicken chicken = new Chicken(this.farmTime);
            liveStocks.add(chicken);
            cells[(int) chicken.getX()][(int) chicken.getY()].getLiveStocks().add(chicken);
            budgetDecreament(100);
        }
    }

    public void addOstrich() {
        if (budget >= 1000) {
            Ostrich ostrich = new Ostrich(this.farmTime);
            liveStocks.add(ostrich);
            cells[(int) ostrich.getX()][(int) ostrich.getY()].getLiveStocks().add(ostrich);
            budgetDecreament(1000);
        }

    }

    public void addCow() {
        if (budget >= 10000) {
            Cow cow = new Cow(this.farmTime);
            liveStocks.add(cow);
            cells[(int) cow.getX()][(int) cow.getY()].getLiveStocks().add(cow);
            budgetDecreament(10000);
        }

    }

    public void buyAnimal(String stringName) {
        switch (stringName) {
            case "chicken":
                this.addChicken();
                break;
            case "oistrich":
                this.addOstrich();
                break;
            case "cow":
                this.addCow();
                break;
        }
    }

    //////////////////////////////PLANT_FORAGE//////////////////////////
    public void plantForage(int x, int y, double time) {

        if ((x > 5 && x < 35 && y > 5 && y < 35) && well.canDisChargeWell()) {
            forages.add(new Forage(farmTime));
            for (int i = -1; i < 2; i++)
                for (int j = -1; j < 2; j++)
                    cells[x + i][y + j].addElement(new Forage(farmTime));
        }
    }

    //////////////////////////////MOVE_ANIMAL///////////////////////////
    //TODO: complete BFS
    public void BFS(Element element, double i, double j) {

    }


    public void moveLiveStocks() {
        for (LiveStock liveStock : this.liveStocks) {
            if (this.farmTime - liveStock.getStartTimeForEatingForage() <= 2)//if it is eating don't move the liveStock
                continue;
            cells[(int) liveStock.getX()][(int) liveStock.getY()].removeElement(liveStock);
            liveStock.checkLiveStock();// checking for weather is hungry or not
            if (liveStock.isMustEatForage() && !forages.isEmpty()) {//liveStock should move wisely
                int closestForageX = 0;
                int closestForageY = 0;
                boolean firstCheck = false;
                for (int i = 5; i < 36; i++)
                    for (int j = 5; j < 36; j++) {
                        if (!cells[i][j].isHaveForage())
                            continue;
                        if (!firstCheck) {
                            closestForageX = i;
                            closestForageY = j;
                            firstCheck = true;
                            continue;
                        }
                        if ((Math.pow(i - liveStock.getX(), 2) +
                                Math.pow(j - liveStock.getY(), 2)) <=
                                (Math.pow(closestForageX - liveStock.getX(), 2) +
                                        Math.pow(closestForageY - liveStock.getY(), 2))) {
                            closestForageX = i;
                            closestForageY = j;
                        }
                    }
                this.BFS(liveStock, closestForageX, closestForageY);
            } else { // liveStock should move randomly
                liveStock.changeDirectionByKnowingCurrentPostition();
                liveStock.moveRandomly();
            }
            cells[(int) liveStock.getX()][(int) liveStock.getY()].getLiveStocks().add(liveStock);
        }

    }

    public void moveWildAnimals() {
        for (WildAnimal wildAnimal : wildAnimals) {
            cells[(int) wildAnimal.getX()][(int) wildAnimal.getY()].removeElement(wildAnimal);
            wildAnimal.changeDirectionByKnowingCurrentPostition();
            wildAnimal.moveRandomly();
            cells[(int) wildAnimal.getX()][(int) wildAnimal.getY()].getWildAnimals().add(wildAnimal);
        }
    }

    public void moveDogs() {

        for (Dog dog : dogs) {
            cells[(int) dog.getX()][(int) dog.getY()].removeElement(dog);
            if (wildAnimals.isEmpty()) {
                dog.changeDirectionByKnowingCurrentPostition();
                dog.moveRandomly();
            } else {
                int closestWildAnimalX = 0;
                int closestWildAnimalY = 0;
                boolean checkFirst = false;
                for (WildAnimal wild : wildAnimals) {
                    if (!checkFirst) {
                        closestWildAnimalX = (int) wild.getX();
                        closestWildAnimalY = (int) wild.getY();
                        continue;
                    }
                    if ((Math.pow(wild.getX() - dog.getX(), 2) +
                            Math.pow(wild.getY() - dog.getY(), 2)) <=
                            (Math.pow(closestWildAnimalX - dog.getX(), 2) +
                                    Math.pow(closestWildAnimalY - dog.getY(), 2))) {
                        closestWildAnimalX = (int) wild.getX();
                        closestWildAnimalY = (int) wild.getY();
                    }

                }
                this.BFS(dog, closestWildAnimalX, closestWildAnimalY);
            }
            cells[(int) dog.getX()][(int) dog.getY()].getDogs().add(dog);
        }
    }

    public void moveCats() {
        catLoop:
        for (Cat cat : cats) {
            cells[(int) cat.getX()][(int) cat.getY()].removeElement(cat);
            if (products.isEmpty()) {
                cat.changeDirectionByKnowingCurrentPostition();
                cat.moveRandomly();
            } else if (!products.isEmpty()) {
                int closestProductX = 0;
                int closestProductY = 0;
                boolean checkFirst = false;
                for (Product product : products)
                    if (product.getVolume() + wareHouse.getCurrent() <= wareHouse.getVolume()) {
                        this.BFS(cat, product.getX(), product.getY());
                        continue catLoop;
                    } else if (products.indexOf(product) == products.size() - 1) {
                        cat.changeDirectionByKnowingCurrentPostition();
                        cat.moveRandomly();
                    }
            }
            cells[(int) cat.getX()][(int) cat.getY()].getCats().add(cat);
        }

    }

    public void moveAnimals() {
        this.moveLiveStocks();
        this.moveWildAnimals();
        this.moveCats();
        this.moveDogs();
    }
/////////////////////////////END_MOVE_ANIMAL///////////////////////////////////

    /////////////////////////////LIVESTOCK_EAT_FORAGE//////////////////////////
    public void liveStockEatingForage() {

        for (LiveStock liveStock : liveStocks) {
            if (!liveStock.isMustEatForage() ||
                    !cells[(int) liveStock.getX()][(int) liveStock.getY()].isHaveForage())
                continue;
            cells[(int) liveStock.getX()][(int) liveStock.getY()].removeElement(liveStock);
            liveStock.setStartTimeForEatingForage(this.farmTime);
            liveStock.changeHungerLevel(1);
            cells[(int) liveStock.getX()][(int) liveStock.getY()].addElement(liveStock);
        }


    }
/////////////////////////////END_LIVESTOCK_EAT_FORAGE//////////////////////////

    /////////////////////////////CHECK_WILDANIMALS_POSITION_WITH_OTHERS////////////
    public void checkWildAnimalPositionWithOthers() {
        for (WildAnimal wildAnimal : wildAnimals)
            cells[(int) wildAnimal.getX()][(int) wildAnimal.getY()].
                    removeAllElements();
    }

    ////////////////////////////CAGE_WILD_ANIMAL///////////////////////////////////
    public void cageWildAnimal(int x, int y) {
        for (WildAnimal wildAnimal : cells[x][y].getWildAnimals())
            wildAnimal.setIsCaged(true);
    }

    ///////////////////////////PICKUP_ELEMENTS_FROM_MAP/////////////////////////////
    public void pickUpProducts(int x, int y) {
        for (Product product : cells[x][y].getProducts())
            if (wareHouse.getCurrent() + product.getVolume() <= wareHouse.getVolume()) {
                wareHouse.addGoodOrLiveStock(product, 1);
                cells[x][y].removeElement(product);
                product.setIsPickedUp(true);
            }

        Iterator iterator = products.iterator();
        while (iterator.hasNext()) {
            Product product = (Product) iterator.next();
            if (product.isPickedUp())
                iterator.remove();
        }
    }

    public void pickUpWildAnimal(int x, int y) {
        for (WildAnimal wildAnimal : cells[x][y].getWildAnimals())
            if (wareHouse.getCurrent() + wildAnimal.getVolume() <= wareHouse.getVolume() &&
                    wildAnimal.getIsCaged()) {
                wareHouse.addGoodOrLiveStock(wildAnimal, 1);
                cells[x][y].removeElement(wildAnimal);
            }
        Iterator iterator = wildAnimals.iterator();
        while (iterator.hasNext()) {
            WildAnimal wildAnimal = (WildAnimal) iterator.next();
            if (wildAnimal.getIsCaged())
                iterator.remove();
        }
    }

    public void pickUpAndPutInWareHouse(int x, int y) {
        this.pickUpProducts(x, y);
        this.pickUpWildAnimal(x, y);
    }

    ///////////////////////////PICKUP_BY_CAT///////////////////////////////////////
    public void pickUpByCatAndPutInWareHouse() {
        for (Cat cat : cats)
            this.pickUpAndPutInWareHouse((int) cat.getX(), (int) cat.getY());

    }

    public void killWildAnimalsByDogs() {
        for (Dog dog : dogs)
            if (!cells[(int) dog.getX()][(int) dog.getY()].getWildAnimals().isEmpty()) {
                cells[(int) dog.getX()][(int) dog.getY()].removeElement(dog);
                dog.setIsKilled(true);
                cells[(int) dog.getX()][(int) dog.getY()].getWildAnimals().remove(0);
            }
        Iterator iterator = dogs.iterator();
        while (iterator.hasNext()) {
            Dog dog = (Dog) iterator.next();
            if (dog.getIsKilled())
                iterator.remove();
        }

    }

    public void chargeWell() {
        well.chargeWell(farmTime);
    }
}

