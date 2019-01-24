package Model.MapAndCell;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import Model.Animal.*;
import Model.Animal.LiveStocks.*;
import Model.Animal.WildAnimals.WildAnimal;
import Model.ElementAndBoxAndDirection.Element;
import Model.Places.WareHouse;
import Model.Places.Well;
import Model.Places.WorkShop;
import Model.Products.Product;
import Model.Products.Forage.Forage;
import Model.Transportation.Helicopter;
import Model.Transportation.Truck;
import View.Animations.SpriteAnimation;
import javafx.animation.Animation;
import javafx.animation.AnimationTimer;
import javafx.animation.PathTransition;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.util.Duration;

import java.util.HashMap;
import java.util.Iterator;

public class Map {

    private String name;
    private boolean isMissionCompleted = false;
    private double farmTime = 0;
    private int budget = 9000;
    private WareHouse wareHouse = new WareHouse();
    private Well well = new Well();
    private Truck truck = new Truck();
    private Helicopter helicopter = new Helicopter();
    private ArrayList<WorkShop> workshops = new ArrayList<>();
    private ArrayList<String> workShopName = new ArrayList<>();
    private HashMap<String, Integer> missionNeeds = new HashMap<>();
    private HashMap<String, Integer> gatherElements = new HashMap<>();
    private ArrayList<LiveStock> liveStocks = new ArrayList<>();
    private ArrayList<WildAnimal> wildAnimals = new ArrayList<>();
    private ArrayList<Cat> cats = new ArrayList<>();
    private ArrayList<Dog> dogs = new ArrayList<>();
    private ArrayList<Forage> forages = new ArrayList<>();
    private ArrayList<Product> products = new ArrayList<>();
    private Cell[][] cells = new Cell[36][36];

    {
        //TODO:clear 6 line below
        HashMap<String,Integer> hashMap = new HashMap<>();
        hashMap.put("chicken", 12);
        hashMap.put("egg", 12);
        hashMap.put("flour", 7);
        missionNeeds = hashMap;
        HashMap<String, Integer> salam = new HashMap<>();
        salam.put("chicken", 0);
        salam.put("egg", 0);
        salam.put("flour", 0);
        gatherElements = salam;
        workshops.add(new WorkShop("EggPowderedPlant", 5, 5));
        workshops.add(new WorkShop("CookieBakery", 34, 6));
        workshops.add(new WorkShop("CakeBakery", 6, 34));
        for (int i = 0; i <= 35; i++)
            for (int j = 0; j <= 35; j++)
                cells[i][j] = new Cell(i, j);

    }
//TODO: pick it
//    public Map(String name, HashMap<String, Integer> missionNeeds) {
//        this.name = name;
//        this.missionNeeds = missionNeeds;
//        for (String needs: missionNeeds.keySet())
//            gatherElements.put(needs, 0);
//    }

    /////////////////////////SETTER_AND_GETTER///////////////////////

    public HashMap<String, Integer> getMissionNeeds() {
        return missionNeeds;
    }

    public HashMap<String, Integer> getGatherElements() {
        return gatherElements;
    }

    public String getName() {
        return name;
    }

    public double getFarmTime() {
        return farmTime;
    }

    public WareHouse getWareHouse() {
        return wareHouse;
    }

    public Well getWell() {
        return well;
    }

    public Truck getTruck() {
        return truck;
    }

    public Helicopter getHelicopter() {
        return helicopter;
    }

    public ArrayList<WorkShop> getWorkshops() {
        return workshops;
    }

    public Cell[][] getCells() {
        return cells;
    }


    public int getBudget() {
        return budget;
    }

    public ArrayList<LiveStock> getLiveStocks() {
        return liveStocks;
    }

    public ArrayList<WildAnimal> getWildAnimals() {
        return wildAnimals;
    }

    public ArrayList<Cat> getCats() {
        return cats;
    }

    public ArrayList<Dog> getDogs() {
        return dogs;
    }

    public ArrayList<Forage> getForages() {
        return forages;
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public ArrayList<String> getWorkShopName() {
        return workShopName;
    }

    //////////////////////////GENERATE_RANDOM_NUMBER_FOR_RANDOM_PLACE_AND_JOBS_RELATED_TO_BUDGET///////
    private int makeRandomNumbers() {
        return (int) (Math.random() * (35 - 5) + 5);
    }

    private void budgetDecreament(int amount) {
        budget -= amount;
    }

    private boolean isBudgetEnough(int amount) {
        if (budget >= amount) {
            budget -= amount;
            return true;
        }
        return false;
    }

    private boolean suitableTimeForRealeasingProduct(double time) {
        for (int i = 1; i < 900; i++)
            if (Math.abs(farmTime - time - 10 * i) < 0.01)
                return true;
        return false;
    }

    //////////////////////////////BUY_ANIMAL_BY_STRING///////////////////
    private void addChicken() {
        if (budget >= 100) {
            System.out.println(budget);
            LiveStock chicken = new LiveStock(this.farmTime, "chicken");
            liveStocks.add(chicken);
            wareHouse.addGoodOrLiveStock(chicken, 1);
            cells[(int) chicken.getX()][(int) chicken.getY()].getLiveStocks().add(chicken);
            budgetDecreament(100);
        }
    }

    private void addOstrich() {
        if (budget >= 1000) {
            LiveStock ostrich = new LiveStock(this.farmTime, "ostrich");
            liveStocks.add(ostrich);
            wareHouse.addGoodOrLiveStock(ostrich, 1);
            cells[(int) ostrich.getX()][(int) ostrich.getY()].getLiveStocks().add(ostrich);
            budgetDecreament(1000);
        }

    }

    private void addCow() {
        if (budget >= 10000) {
            LiveStock cow = new LiveStock(this.farmTime, "cow");
            liveStocks.add(cow);
            wareHouse.addGoodOrLiveStock(cow, 1);
            cells[(int) cow.getX()][(int) cow.getY()].getLiveStocks().add(cow);
            budgetDecreament(10000);
        }

    }

    private void addCat() {
        if (budget >= 2500) {
            Cat cat = new Cat("cat" + Integer.toString(cats.size()));
            cats.add(cat);
            cells[(int) cat.getX()][(int) cat.getY()].getCats().add(cat);
            budgetDecreament(2500);
        }

    }

    private void addDog() {
        if (budget >= 2600) {
            Dog dog = new Dog();
            dogs.add(dog);
            cells[(int) dog.getX()][(int) dog.getY()].getDogs().add(dog);
            budgetDecreament(2500);
        }
    }

    public void addWildAnimal() {
        wildAnimals.add(new WildAnimal());
    }

    //////////////////////////CHECK_MISSION_NEEDS/////////////////////////
    private void gatherForMissionNeeds(String purpose) {
        for (String needs: missionNeeds.keySet())
            if (needs.equals(purpose)) {
                if (gatherElements.get(purpose) < missionNeeds.get(purpose))
                gatherElements.put(purpose, gatherElements.get(purpose) + 1);
                break;
            }
        if (gatherElements.equals(missionNeeds)) {
            isMissionCompleted = true;
            System.out.println("mission completed.");
            System.exit(0);
        }
    }


    public boolean isMissionCompleted() {
        return isMissionCompleted;
    }


    public void buyAnimal(String stringName) {
        switch (stringName) {
            case "chicken":
                this.addChicken();
                break;
            case "ostrich":
                this.addOstrich();
                break;
            case "cow":
                this.addCow();
                break;
            case "cat":
                this.addCat();
                break;
            case "dog":
                this.addDog();
                break;
        }
        this.gatherForMissionNeeds(stringName);
    }

    //////////////////////////////PLANT_FORAGE//////////////////////////
    public void plantForage(int x, int y, double time) {
        if ((x > 5 && x < 35 && y > 5 && y < 35) && well.canDisChargeWell()) {
            forages.add(new Forage(farmTime));
            for (int i = -1; i < 2; i++)
                for (int j = -1; j < 2; j++) {
                    cells[x + i][y + j].addElement(new Forage(farmTime));
                }
        }
    }

    //////////////////////////////MOVE_ANIMAL///////////////////////////
    private void BFS(Animal animal, double i, double j) {
        animal.moveWisely(i, j);
    }

    //////////////////MOVE_LIVE_STOCKS//////////////////////////////////
    private void moveLiveStocks() {
        for (LiveStock liveStock : this.getLiveStocks()) {
            if (this.farmTime - liveStock.getStartTimeForEatingForage() < 2)
                //if it is eating don't move the liveStock
                continue;
            cells[ liveStock.getX()][liveStock.getY()].removeElement(liveStock);
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
                    } //for finding closest forage to liveStock
                liveStock.changeHungerLevel(-0.05);
                this.BFS(liveStock, closestForageX, closestForageY);
                System.out.println("move wisely");
            } else { // liveStock should move randomly
                liveStock.changeHungerLevel(-0.05);
                liveStock.changeDirectionByKnowingCurrentPostition();
                liveStock.moveRandomly(1);
                System.out.println("Move randomly");
            }
            cells[liveStock.getX()][liveStock.getY()].getLiveStocks().add(liveStock);
        }

    }
    //////////////////////////MOVE_WILD_ANIMALS//////////////////
    private void moveWildAnimals() {
        for (WildAnimal wildAnimal : wildAnimals) {
            if (wildAnimal.isCaged())
                continue;
            cells[(int) wildAnimal.getX()][(int) wildAnimal.getY()].removeElement(wildAnimal);
            wildAnimal.changeDirectionByKnowingCurrentPostition();
            wildAnimal.moveRandomly(1);
            cells[(int) wildAnimal.getX()][(int) wildAnimal.getY()].getWildAnimals().add(wildAnimal);
        }
    }
    ////////////////////////MOVE_DOG//////////////////////////
    private void moveDogs() {

        for (Dog dog : dogs) {
            cells[(int) dog.getX()][(int) dog.getY()].removeElement(dog);
            if (wildAnimals.isEmpty()) {
                dog.changeDirectionByKnowingCurrentPostition();
                dog.moveRandomly(1);
            } else {
                int closestWildAnimalX = 0;
                int closestWildAnimalY = 0;
                boolean checkFirst = false;
                for (WildAnimal wild : wildAnimals) {
                    if (!checkFirst) {
                        closestWildAnimalX = (int) wild.getX();
                        closestWildAnimalY = (int) wild.getY();
                        checkFirst = true;
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
    /////////////////////MOVE_CAT//////////////////////////////
    private void moveCats() {
        catLoop:
        for (Cat cat : cats) {
            cells[(int) cat.getX()][(int) cat.getY()].removeElement(cat);
            if (products.isEmpty()) {
                cat.changeDirectionByKnowingCurrentPostition();
                cat.moveRandomly(1);
            } else {
                for (Product product : products) {
                    if (product.getVolume() + wareHouse.getCurrent() <= wareHouse.getVolume()
                            && (product.getFollowedByNameCat().equals("") || product.getFollowedByNameCat().equals(cat.getName())) ) {
                        product.setFollowedByNameCat(cat.getName());
                        this.BFS(cat, product.getX(), product.getY());
                        continue catLoop;
                    } else if (products.indexOf(product) == products.size() - 1) {
                        cat.changeDirectionByKnowingCurrentPostition();
                        cat.moveRandomly(1);
                    }
                }
            }
            cells[(int) cat.getX()][(int) cat.getY()].getCats().add(cat);
        }

    }
    ///////////////MOVE_ALL_ANIMALS/////////////////////////////////////////
    public void moveAnimals() {
        this.moveLiveStocks();
        this.moveWildAnimals();
        this.moveCats();
        this.moveDogs();
    }

    /////////////////////////////LIVESTOCK_EAT_FORAGE//////////////////////////
    private void liveStockEatingForage() {

        for (LiveStock liveStock : liveStocks) {
            liveStock.checkLiveStock();
            if (!liveStock.isMustEatForage() ||
                    !cells[(int) liveStock.getX()][(int) liveStock.getY()].isHaveForage() ||
                    farmTime - liveStock.getStartTimeForEatingForage() < 2)
                continue;
            cells[liveStock.getX()][liveStock.getY()].removeElement(liveStock);
            liveStock.setStartTimeForEatingForage(this.farmTime);
            liveStock.changeHungerLevel(1);//by eating forage it increases two level of its hunger
            System.out.println("eating");
            cells[(int) liveStock.getX()][(int) liveStock.getY()].addElement(liveStock);
            cells[(int) liveStock.getX()][(int) liveStock.getY()].removeOneForage();
        }


    }

    private void checkLiveStocksForReleasingProduct() {
        for (LiveStock liveStock : liveStocks)
            if (suitableTimeForRealeasingProduct(liveStock.getStartTimeBeingInMap())) {
                cells[liveStock.getX()][liveStock.getY()].addElement(liveStock.releaseProduct(farmTime));
                System.out.println("add product in : " + farmTime);
                products.add(liveStock.releaseProduct(farmTime));
            }
    }

    /////////////////////////////CHECK_WILDANIMALS_POSITION_WITH_OTHERS////////////
    public void checkWildAnimalPositionWithOthers(Group mapGroup) {
        for (WildAnimal wildAnimal : wildAnimals)
            cells[(int) wildAnimal.getX()][(int) wildAnimal.getY()].
                    removeAllElements();
        Iterator iterator = liveStocks.iterator();
        Iterator iterator1 = cats.iterator();
        for (WildAnimal wildAnimal: wildAnimals) {
            while (iterator.hasNext()) {
                LiveStock liveStock = (LiveStock) iterator.next();
                if (liveStock.getX() == wildAnimal.getX() && liveStock.getY() == wildAnimal.getY()) {
                    mapGroup.getChildren().remove(liveStock.getLiveStockView());
                    iterator.remove();
                }
            }
            while (iterator1.hasNext()) {
                Cat cat = (Cat) iterator1.next();
                if (cat.getX() == wildAnimal.getX() && cat.getY() == wildAnimal.getY()) {
                    mapGroup.getChildren().remove(cat.getCatView());
                    iterator1.remove();
                }
            }
        }
    }
    ////////////////////////////CAGE_WILD_ANIMAL///////////////////////////////////
    public void cageWildAnimal(int x, int y) {
        for (WildAnimal wildAnimal : cells[x][y].getWildAnimals())
            wildAnimal.setIsCaged(true);
    }

    ///////////////////////////PICKUP_ELEMENTS_FROM_MAP/////////////////////////////
    private void pickUpProducts(int x, int y) {
        Iterator iterator = cells[x][y].getProducts().iterator();
        while (iterator.hasNext()) {
            Product product = (Product)iterator.next();
            if (wareHouse.getCurrent() + product.getVolume() <= wareHouse.getVolume()) {
                System.out.println("asd;flkjsalkfkdjs;lkfjas;fkljsa");
                wareHouse.addGoodOrLiveStock(product, 1);
                removeOneProduct(product);
                iterator.remove();
                this.gatherForMissionNeeds(product.getName());
                product.setIsPickedUp(true);
            }
        }
    }

    private void removeOneProduct(Product product) {
        Iterator iterator = products.iterator();
        while (iterator.hasNext()) {
            Product product1 = (Product) iterator.next();
            if (product.getName().equals(product1.getName()) && product.getX() == product1.getX() &&
            product.getY() == product1.getY())
                iterator.remove();
        }
    }

    private void pickUpWildAnimal(int x, int y) {
        for (WildAnimal wildAnimal : cells[x][y].getWildAnimals())
            if (wildAnimal.isCaged()) {
                wareHouse.addGoodOrLiveStock(wildAnimal, 1);
            }
        Iterator iterator = wildAnimals.iterator();
        while (iterator.hasNext()) {
            WildAnimal wildAnimal = (WildAnimal) iterator.next();
            if (wildAnimal.isCaged() && wildAnimal.getX() == x && wildAnimal.getY() == y)
                iterator.remove();
        }
    }

    public void pickUpAndPutInWareHouse(int x, int y) {
        this.pickUpProducts(x, y);
        this.pickUpWildAnimal(x, y);
    }

    ///////////////////////////PICKUP_BY_CAT///////////////////////////////////////
    private void pickUpByCatAndPutInWareHouse() {
        for (Cat cat : cats)
            this.pickUpProducts((int) cat.getX(), (int) cat.getY());

    }

    ///////////////////////////KILLED_WILD_ANIMALS_BY_DOGS/////////////////////////
    private void killedWildAnimalsByDogs(Group mapGroup) throws FileNotFoundException {
        for (Dog dog : dogs)
            if (!cells[(int) dog.getX()][(int) dog.getY()].getWildAnimals().isEmpty()) {
                battleAnimation(mapGroup, dog.getX(), dog.getY());
                cells[(int) dog.getX()][(int) dog.getY()].removeDog(dog, mapGroup);
                dog.setIsKilled(true);
                System.out.println();
                mapGroup.getChildren().remove(cells[(int) dog.getX()][(int) dog.getY()].getWildAnimals().get(0)
                        .getWildAnimalView());
                wildAnimals.remove(cells[(int) dog.getX()][(int) dog.getY()].getWildAnimals().get(0));
                cells[(int) dog.getX()][(int) dog.getY()].getWildAnimals().remove(0);
            }
        Iterator iterator = dogs.iterator();
        while (iterator.hasNext()) {
            Dog dog = (Dog) iterator.next();
            if (dog.getIsKilled())
                iterator.remove();
        }

    }


    public void battleAnimation(Group mapGroup, int x, int y) throws FileNotFoundException {
        ImageView battle = new ImageView(new Image(new FileInputStream("" +
                "C:\\Users\\Home\\Desktop\\farmFrenzySaveFiles\\Cages\\battle_1.png")));
        mapGroup.getChildren().add(battle);
        SpriteAnimation battleAnimation = new SpriteAnimation(battle, Duration.millis(1000), 20,
                5, 0, 0, 250, 250);
        battleAnimation.setCycleCount(5);
        battleAnimation.play();

        Path path = new Path(new MoveTo(250 + x * 9, 250 + y * 9), new LineTo(1200, 250 + y * 9));
        path.setVisible(false);
        mapGroup.getChildren().add(path);
        PathTransition pathTransition = new PathTransition(Duration.seconds(3), path, battle);
        pathTransition.setCycleCount(1);
        pathTransition.play();

    }



    ///////////////////////////CHARGE_WELL/////////////////////////////////////////
    public void chargeWell() {
        if (!well.isInCharging() && well.getCurrent() == 0 && isBudgetEnough(well.getPrice())) {
            well.charge(farmTime);
        }

    }

    //////////////////////////START_WORKSHOP//////////////////////////////////////
    public void startWorkshop(String workShopName) {
        for (WorkShop workShop : workshops) {
            System.out.println("workshops: " + workShop.getName());
            if (!workShop.getName().equals(workShopName))
                continue;
            System.out.println("workShop name: " + workShop.getName());
            if (!workShop.isInWorking() && wareHouse.isItPossibleForStartingWorkshop(workShop)) {
                System.out.println("start");
                workShop.startWorking(this.farmTime);
            }
        }
    }



    //////////////////////////CHECKING_WORKSHOP_FOR_GETTING_OUTPUT/////////////////
    private void checkWorkshopForGettingOutput(WorkShop workShop) {
        if (workShop.checkWorkShopForDistributingOutputs(farmTime)) {
            this.addProductProducedByWorkshops(workShop, workShop.distributeOutputs(this.farmTime));
        }
    }

    private void addProductProducedByWorkshops(WorkShop workShop, ArrayList<Product> goods) {
        for (Product product : goods) {
            cells[workShop.getX()][workShop.getY()].addElement(product);
            products.add(product);
        }
    }

    /////////////////////////ADD_ELEMENT_TO_TRUCK//////////////////////////////////
    private void addOneElementToTruck(Element element) {
        this.truck.putElementInTrunk(this.wareHouse.giveOneNumberFromAnElement(element), 1);
    }

    private void addAllOfAnElementToTruck(Element element) {
        HashMap<Element, Integer> reference = this.wareHouse.giveAllOfAnElement(element);
        this.truck.putElementInTrunk(element, reference.get(element));
    }

    public void addElementToTruck(Element element, int count) {
        if (wareHouse.isHaveThisElement(element) && this.truck.isInWareHouse()) {
            if (count == 1)
                this.addAllOfAnElementToTruck(element);
            else
                this.addOneElementToTruck(element);
            this.wareHouse.addGoodOrLiveStock(element, this.truck.getCountReturnToWareHouse());
        }
    }

    ////////////////////////GO_TRUCK////////////////////////////////////////////////
    public void goTruck() {
        budget += this.truck.startWorking(this.farmTime);
    }

    ///////////////////////CHECK_TRUCK_IS_IN_wareHouse/////////////////////////////
    private void checkIsTruckInWareHouse() {
        this.truck.checkWasTruckCameBackFromBazar(farmTime);
    }

    //////////////////////CLEAR_TRUCK//////////////////////////////////////////////
    public void clearTruck() {
        this.truck.clear();
    }

    ////////////////////////ADD_ELEMENT_IN_HELICOPTER//////////////////////////////
    public void addElementToHelicopter(Element element) {
        this.helicopter.putOneCountOfAnElementInHelicopter(element, budget);
    }

    ////////////////////////GO_HELICOPTER/////////////////////////////////
    public void goHelicopter() {
        budget = this.helicopter.startWorking(farmTime);
    }

    ///////////////////////CHECK_HELICOPTER_COME_BACK_FROM_BAZAR/////////////////////
    private void checkHelicopterCameBackFromBazar() {
        if (this.helicopter.checkWasHelicopterCameBackFromBazar(farmTime)) {
            for (Element element : helicopter.getSalesGoods()) {
                int x = makeRandomNumbers();
                int y = makeRandomNumbers();
                element.setX(x);
                element.setY(y);
                cells[x][y].addElement(element);
                products.add((Product)element);
            }
        }
    }

    ////////////////////////CLEAR_HELICOPTER///////////////////////////
    public void clearHelicopter() {
        this.helicopter.clear();
    }

    ///////////////////////LIVE_STOCK_TURN//////////////////////////////
    private void liveStockTurn() {
        this.checkLiveStocksForReleasingProduct();
        this.liveStockEatingForage();
        this.moveLiveStocks();
        Iterator iterator = liveStocks.iterator();
        while (iterator.hasNext()) {
            LiveStock liveStock = (LiveStock)iterator.next();
            if (liveStock.isKilled()) {
                wareHouse.eliminateLiveStock(liveStock);
                cells[(int)liveStock.getX()][(int)liveStock.getY()].removeElement(liveStock);
                iterator.remove();
            }
        }

    }

    /////////////////////PRODUCT_TURN///////////////////////////////////////
    private void productTurn() {
        Iterator iterator = products.iterator();
        while (iterator.hasNext()) {
            Product product = (Product) iterator.next();
            if (product.getSecondTime() <= farmTime) {
                cells[(int)product.getX()][(int)product.getY()].removeElement(product);
            }
        }
    }

    //////////////////////WILD_ANIMAL_TURN//////////////////////////////////
    private void wildAnimalTurn(Group mapGroup) {
        this.moveWildAnimals();
        this.checkWildAnimalPositionWithOthers(mapGroup);
    }

    /////////////////////DOG_TURN////////////////////////////////////////////
    private void dogTurn(Group mapGroup) throws FileNotFoundException {
        this.moveDogs();
        this.killedWildAnimalsByDogs(mapGroup);
    }

    ////////////////////CAT_TURN//////////////////////////////////////////////
    private void catTurn() {
        this.moveCats();
        this.pickUpByCatAndPutInWareHouse();
    }

    //////////////////WELL_TURN//////////////////////////////////////////////
    private void wellTurn() {
        this.well.checkWell(farmTime);
    }

    /////////////////TURN////////////////////////////////////////////////////
    public void turnMap(double increase, Group mapGroup) throws FileNotFoundException {
        farmTime += increase;
        this.productTurn();
        this.liveStockTurn();
        this.wildAnimalTurn(mapGroup);
        this.dogTurn(mapGroup);
        this.catTurn();
        this.wellTurn();
        for (WorkShop workShop : workshops) {
            this.checkWorkshopForGettingOutput(workShop);
        }

        this.checkHelicopterCameBackFromBazar();
        this.checkIsTruckInWareHouse();
    }

    ///////////////////////////UPGRADE_ELEMENT////////////////////
    private void upgradeWareHouse() {
        if (wareHouse.upgrade())
            budgetDecreament((int) wareHouse.getMoneyForUpgrading());
    }

    private void upgradeWorkshop(String workShopName) {
        for (WorkShop workShop: workshops)
            if (workShop.getName().equals(workShopName))
                if (workShop.upgrade())
                    budgetDecreament((int) workShop.getMoneyForUpgrading());
    }

    private void upgradeCat() {
        for (Cat cat: cats)
            if (cat.upgrade())
                budgetDecreament((int) cat.getMoneyForUpgrading());
    }

    private void upgradeWell() {
        if (well.upgrade())
            budgetDecreament((int) well.getMoneyForUpgrading());
    }

    private void upgradeTruck() {
        if (truck.upgrade())
            budgetDecreament((int) truck.getMoneyForUpgrading());
    }

    private void upgradeHelicopter() {
        if (helicopter.upgrade())
            budgetDecreament((int) helicopter.getMoneyForUpgrading());
    }

    public void upgrade(String name) {
        switch (name) {
            case "truck":
                if (budget >= this.getTruck().getMoneyForUpgrading())
                    this.upgradeTruck();
                break;
            case "helicopter":
                if (budget >= this.getTruck().getMoneyForUpgrading())
                    this.upgradeHelicopter();
                break;
            case "wareHouse":
                if (budget >= this.getTruck().getMoneyForUpgrading())
                    this.upgradeWareHouse();
                break;
            case "well":
                if (budget >= this.getWell().getMoneyForUpgrading())
                    this.upgradeWell();
                break;
            case "cat":
                if (budget >= this.getTruck().getMoneyForUpgrading())
                    this.upgradeCat();
                break;
            default:
                if (budget >= this.getWorkshops().get(0).getMoneyForUpgrading())
                    upgradeWorkshop(name);
                break;

        }
    }
}