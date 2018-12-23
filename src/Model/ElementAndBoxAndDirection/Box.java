package Model.ElementAndBoxAndDirection;

import java.util.ArrayList;
import java.util.HashMap;

public class Box{
    private int volume = 40;
    private int current = 0;
    private ArrayList<Element> elements = new ArrayList<>();

    public void addElement(Element elementEntry) {
        if(this.elements.size() == 0 || (elementEntry.getClass() == elements.get(0).getClass() &&
                elementEntry.getVolume() <= (volume - current))) {
            this.elements.add(elementEntry);
            current += elementEntry.getVolume();// increase the occupied space (current) in Box
        }
    }

    public void removeElement() {
        current = 0;
        elements.clear();
    }

    public int getCost() {
        int cost = 0;
        for(Element element : elements)
            cost += element.getPrice();
        return cost;
    }

    public int getVolume() {
        return volume;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }

    public int getCurrent() {
        return current;
    }

    public void setCurrent(int current) {
        this.current = current;
    }

    public ArrayList<Element> getElements() {
        return elements;
    }

    public void setElements(ArrayList<Element> elements) {
        this.elements = elements;
    }
}
