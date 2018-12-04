package Model.ProductsAndForage.Forage;

import Model.ElementAndBoxAndDirection.Element;

public class Forage extends Element {

    public Forage(double x, double y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public void move(double finalX, double finalY) {
        //nothing
    }

    @Override
    public void upgrade() {
        //nothing to do
    }
}

//TODO: it's complete
