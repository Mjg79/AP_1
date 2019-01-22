package Model.ElementAndBoxAndDirection;

import Model.Products.Product;

import java.util.HashMap;

public class Box{
    private int volume;
    private int current;
    private boolean isContainAny = false;
    private HashMap<Element, Integer> element = new HashMap<>();


    private Element content = new Element() {
        @Override
        public void move(int finalX, int finalY) {

        }

        @Override
        public boolean upgrade() {
            return false;
        }
    };

    {
        content.name = "";
        current = 0;
        volume = 10;
    }

    public void addElement(Element elementEntry, int count) {
        if(elementEntry.getVolume() * count <= (volume - current)) {
            System.out.println(element.size());
            isContainAny = true;
            if (content.getName().equals(""))
                content = elementEntry;
            if (!content.getName().equals("") && content.getName().equals(elementEntry.getName())) {
                if (element.size() != 0)
                    this.element.put(content, element.get(content) + count);
                else
                    this.element.put(content, count);
                current += elementEntry.getVolume() * count;// increase the occupied space (current) in Box
            }
        }
    }


    public void removeElement() {
        current = 0;
        element.remove(content);
        content.name = "";
        isContainAny = false;
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

    public boolean isContainAny() {
        return isContainAny;
    }

    public Element getContent() {
        return content;
    }

    public void setCurrent(int count) {
        this.current = count;
    }
}