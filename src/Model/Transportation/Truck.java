package Model.Transportation;

import Model.ElementAndBoxAndDirection.Box;
import Model.ElementAndBoxAndDirection.Element;

import java.util.ArrayList;

public class Truck extends Element {
    private ArrayList<Box> boxes = new ArrayList<>();
    private int wallet = 0;
    private int numOfBoxes = 2;
    private double startTimeForSellingElements;
    private double endTimeForSellingElements;

    {
        //TODO:checkBlockInitialize
        for (int i = 0; i < numOfBoxes; i++)
            boxes.add(new Box());
    }

    @Override
    public void move(double finalX, double finalY) {
        //TODO: nothing to do ;
    }

    @Override
    public void upgrade() {
        numOfBoxes++;
    }

    public void putElementInBox(Element element, int count) {
        for (Box box: boxes)
            box.addElement(element, count);

    }

    public void sellBoxes() {
        for (Box box: boxes)
            if (box.getElement() != null)
               ;// wallet +=
    }

}
