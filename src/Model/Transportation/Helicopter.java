package Model.Transportation;
import Model.ElementAndBoxAndDirection.Box;
import Model.ElementAndBoxAndDirection.Element;

import java.util.ArrayList;

public class Helicopter extends Element {
    private double volume;
    private double current;
    private ArrayList<Box> items = new ArrayList<>();
    private int wallet = 0;


    @Override
    public void move(double finalX, double finalY) {

    }

    @Override
    public void upgrade() {

    }
}
