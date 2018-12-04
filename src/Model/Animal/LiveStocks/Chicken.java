package Model.Animal.LiveStocks;



public class Chicken extends LiveStock {
    private static final double priceForSale = 50;// for sale price is different

    {
        price = 100;
        volume = 2;
        level = 0;
        direction = direction.getRandomDirection();
        x = (int) (Math.random() * (35 - 5 + 1) + 5);
        y = (int) (Math.random() * (35 - 5 + 1) + 5);
    }

    public Chicken(double startBeingInMap) {
        super(startBeingInMap);
    }

    public static void main(String[] args) {
        double x  = (int) (Math.random() * (35 - 5 + 1) + 5);
        System.out.println(x);
    }

}
