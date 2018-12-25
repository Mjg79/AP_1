package View;

import Model.Animal.LiveStocks.LiveStock;
import Model.Animal.WildAnimals.WildAnimal;
import Model.ElementAndBoxAndDirection.Box;
import Model.MapAndCell.Cell;
import Model.MapAndCell.Map;

import java.security.Key;
import java.util.Scanner;

public class View {
    private Map map;


    private void printTruck() {
        System.out.println("\nTruck: " + " ,Level: " + map.getTruck().getLevel()
                + " ,wallet: " + map.getTruck().getWallet()
                + " ,isInWorking: " + !map.getTruck().isInWareHouse() + "\n"
                + " ,BoxNumbers: " + map.getTruck().getNumOfBoxes() + " ,boxContents: "
                + " ,lastTimeForWork" + map.getTruck().getEndTimeForSellingElements());
        for (Box box : map.getTruck().getBoxes())
            System.out.println("element: " + box.getContent().getClass() + " ,numbers: " +
                    box.getElement().get(box.getContent()));
    }

    private void printHeliCopter() {
        System.out.println("\nHelicopter: " + " ,Level: " + map.getHelicopter().getLevel() + " ,Cost: "
                + map.getHelicopter().getAllCost() + " ,MapBudget: " + map.getHelicopter().getMapBudget()
                + " ,isInWorking: " + !map.getHelicopter().isInWareHouse() + "\n" + " ,BoxNumbers: "
                + map.getHelicopter().getNumOfBoxes() + " ,LastTimeForWorking: " +
                map.getHelicopter().getEndTimeForBuyingElements() + " ,boxContents: ");
        for (Box box : map.getHelicopter().getBoxes())
            System.out.println("element: " + box.getContent().getClass() + " ,numbers: " +
                    box.getElement().get(box.getContent()));
    }

    private void printWareHouse() {
        System.out.println("\nwareHouse: \n" + "Level: " + map.getWareHouse().getLevel() + " ,Current: "
                + map.getWareHouse().getCurrent() + " ,Volume: " + map.getWareHouse().getVolume()
                + " ,products: ");
        for (String name : map.getWareHouse().getGoods().keySet())
            System.out.println("productName: " + name + " ,Count: " + map.getWareHouse().getGoods().get(name));
        for (String liveStockName : map.getWareHouse().getLiveStocks().keySet())
            System.out.println("liveStockName: " + liveStockName + " ,Count: " +
                    map.getWareHouse().getLiveStocks().get(liveStockName));
    }

    private void printWell() {
        System.out.println("\nWell: \n" + "Level: " + map.getWell().getLevel() + " ,Current: "
                + map.getWell().getCurrent() + " ,Volume: " + map.getWell().getVolume()
                + " ,isCharging: " + map.getWell().isInCharging() + " ,price: " + map.getWell().getPrice()
                + "\n" + " ,moneyForUpgrading: " + map.getWell().getMoneyForUpgrading() +
                " ,startTimeForCharging: " + map.getWell().getFirstTimeForCharge() +
                " ,endTimeForCharging: " + map.getWell().getLastTimeForCharge());
    }

    private void printMap() {
        System.out.println("Map: \n" + "farmTime: " + map.getFarmTime() + " ,budget: " + map.getBudget() + "\n" +
                "\nLiveStocks: ");
        for (LiveStock liveStock : map.getLiveStocks())
            System.out.println("liveStockX: " + liveStock.getX() + " ,liveStockY: " + liveStock.getY() +
                    ",Direction: " + liveStock.getDirection() + "\nliveStockType: " + liveStock.getName() +
                    " ,timeEntered: " + liveStock.getStartTimeBeingInMap() +
                    " ,hungerLevel: " + liveStock.getHungerLevel() + " ,timeStartForEating: "
                    + liveStock.getStartTimeForEatingForage());
        System.out.println("\nWildAnimals: ");
        for (WildAnimal wildAnimal : map.getWildAnimals())
            System.out.println("wildAnimalX: " + wildAnimal.getX() + " ,wildAnimalY: " + wildAnimal.getY()
                    + " ,isCaged: " + wildAnimal.isCaged());
        this.printWell();
        this.printWareHouse();
    }

    private void printInfo() {
        System.out.println("Info: ");
        System.out.println("budget: " + map.getBudget() + " ,timeLast: " + map.getFarmTime());
        System.out.println("\nmissionNeeds: ");
        for (String string: map.getMissionNeeds().keySet())
            System.out.println("element: " + string + " ,purposeNumber: " + map.getMissionNeeds().get(string));
        System.out.println("\nelementGatheredForMissionNeedsUntilNow: ");
        for (String string: map.getGatherElements().keySet())
            System.out.println("element: " + string + " ,gatherYet: " + map.getGatherElements().get(string));
    }

    private void printWeavingFactory() {
    }

    private void printSpinnery() {
    }

    private void printSewingFactory() {
    }

    private void printCakeBakery() {
    }

    private void printCookieBakery() {
    }

    private void printEggPowderPlant() {
    }


    public void print(Map map, String string) {
        this.map = map;
        if (string.equals("truck"))
            this.printTruck();
        if (string.equals("helicopter"))
            this.printHeliCopter();
        if (string.equals("wareHouse"))
            this.printWareHouse();
        if (string.equals("well"))
            this.printWell();
        if (string.equals("eggPowderPlant"))
            this.printEggPowderPlant();
        if (string.equals("cookieBakery"))
            this.printCookieBakery();
        if (string.equals("cakeBakery"))
            this.printCakeBakery();
        if (string.equals("SewingFactory"))
            this.printSewingFactory();
        if (string.equals("Spinnery"))
            this.printSpinnery();
        if (string.equals("WeavingFactory"))
            this.printWeavingFactory();
        if (string.equals("map"))
            this.printMap();
        if (string.equals("info"))
            this.printInfo();
    }

}
