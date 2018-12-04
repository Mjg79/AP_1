package Model.Animal.LiveStocks;

public class Ostrich extends LiveStock {
    private static final double priceForSale = 500;

    {
        price = 1000;
        volume = 5;
        level = 0;
        x = (int) (Math.random() * (35 - 5 + 1) + 5);
        y = (int) (Math.random() * (35 - 5 + 1) + 5);
    }

    public Ostrich(double startBeingInMap) {
        super(startBeingInMap);
    }


}
