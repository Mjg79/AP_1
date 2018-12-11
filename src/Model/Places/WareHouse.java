package Model.Places;

import Model.Animal.Animal;
import Model.Animal.LiveStocks.LiveStock;
import Model.ElementAndBoxAndDirection.Element;
import Model.ProductsAndForage.Product;

import java.util.ArrayList;
import java.util.HashMap;

public class WareHouse extends Element {
    private int current = 0; // current is 0 because at first we don't have anything in wareHouse
    private HashMap<String, Integer> goods = new HashMap<>();
    private HashMap<String, Integer> liveStocks = new HashMap<>();
    private static int maxKindOfAnimal = 3;
    private int numOfKindOfAnimalEnteredInYet = 0;
    {
        volume = 40;
        level = 0;
        x = 20;//truly is a constant for specifying the x of wareHouse in map
        y = 40;//truly is a constant for specifying the y of wareHouse in map
    }

    @Override
    public void move(double finalX, double finalY) {
        //nothing
    }

    @Override
    public void upgrade() {
        level++;
        volume *= 4;
    }

    public void addGoodOrLiveStock(Element element, int count) {
        if (!(element instanceof LiveStock)) {// if it is not a live stock
            if ((volume - current) >= element.getVolume() * count) {
                if (goods.containsKey(element.getName())) {
                    int previousCount = goods.get(element.getName());
                    goods.put(element.getName(), previousCount + count);
                } else {
                    goods.put(element.getName(), count);
                }
            }

        } else {// if it is a live stock
            if (liveStocks.containsKey(element.getName())) {
                int previousCount = liveStocks.get(element.getName());
                goods.put(element.getName(), previousCount + count);
            } else {
                numOfKindOfAnimalEnteredInYet++;
                if (numOfKindOfAnimalEnteredInYet <= maxKindOfAnimal)
                    goods.put(element.getName(), count);
            }
        }

    }

    public Element giveOneNumberFromAnElement(Element element)  {// for give a thing to truck
        if (element instanceof LiveStock) {// if element is a liveStock
            if (liveStocks.containsKey(element.getName()))
                if (liveStocks.get(element.getName()) == 1) {
                    liveStocks.remove(element.getName());
                    return element;
                } else if (liveStocks.get(element.getName()) > 1) {
                    int previousCount = liveStocks.get(element.getName());
                    liveStocks.put(element.getName(), --previousCount);
                    return element;
                }

        }    else {// if element is not a liveStock
            if (goods.get(element.getName()) == 1) {
                goods.remove(element.getName());
                current -= element.getVolume();
                return element;
            } else if (goods.get(element.getName()) > 1) {
                int previousCount = goods.get(element.getName());
                goods.put(element.getName(), --previousCount);
                return element;
            }
        }
        return null;
    }

    public int getCurrent() {
        return current;
    }

    public HashMap<Element, Integer> giveAllOfAnElement(Element element) {// for give all of a thing to truck
        HashMap<Element, Integer> stuffs = new HashMap<>();
        if (liveStocks.containsKey(element.getName())) {// if element is a liveStock
            int count = liveStocks.get(element.getName());
            liveStocks.remove(element.getName());
            stuffs.put(element, count);
            return stuffs;
        } else if (goods.containsKey(element.getName())) {// if element is not a liveStock
            int count = goods.get(element.getName());
            current -= element.getVolume() * count;
            goods.remove(element.getName());
            stuffs.put(element, count);
            return stuffs;
        }

        return null;
    }


}//TODO: it's complete
