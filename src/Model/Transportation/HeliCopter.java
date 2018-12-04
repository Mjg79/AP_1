package Model.Transportation;
import Model.ElementAndBoxAndDirection.Element;

import java.util.ArrayList;

public class Helicopter extends Element {
    private double volume;
    private double current;
    private ArrayList<Element> items = new ArrayList<>();
    private int walletAmount;


    @Override
    public void move(double finalX, double finalY) {

    }

    @Override
    public void upgrade() {

    }
}
