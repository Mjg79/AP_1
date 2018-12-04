package Model.ElementAndBoxAndDirection;

import java.util.HashMap;

public class Box{
    private int volume;
    private int current;
    private HashMap<Element, Integer> element;

    {
     current = 0;
     volume = 10;
    }

    public void addElement(Element elementEntry, int count) {
        if(this.element.size() == 0 &&
             elementEntry.getVolume() * count <= (volume - current)) {
             this.element.put(elementEntry, count);
            current += elementEntry.getVolume() * count;// increase the occupied space (current) in Box
        }
    }

    public void removeElement() {
        current = 0;
        element.clear();
    }

    public HashMap<Element, Integer> getElement() {
        return element;
    }

    public int getCurrent() {
        return current;
    }

    public int getVolume() {
        return volume;
    }

}//TODO: it's complete
