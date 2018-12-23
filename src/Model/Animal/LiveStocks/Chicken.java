package Model.Animal.LiveStocks;


import Model.Products.LiveStockProducts.Egg;
import Model.Products.Product;

public class Chicken extends LiveStock {
    private static final double priceForSale = 50;// for sale price is different

    {
        price = 100;
        volume = 2;
        level = 0;
        name = "chicken";
    }

    public Chicken(double startBeingInMap) {
        super(startBeingInMap);
    }


    @Override
    public Product releaseProduct(double time) {
        return new Egg(time);
    }

}
