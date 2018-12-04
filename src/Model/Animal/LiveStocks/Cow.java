package Model.Animal.LiveStocks;

import Model.ElementAndBoxAndDirection.Direction;

public class Cow extends LiveStock {
    private static final double moneyForSale = 5000;

    {
        price = 10000;
        volume = 10;
        level = 0;
        direction = Direction.getRandomDirection();
        x = (int) (Math.random() * (35 - 5 + 1) + 5);
        y = (int) (Math.random() * (35 - 5 + 1) + 5);
    }

    public Cow(double startBeingInMap) {
        super(startBeingInMap);
    }
}
