package Model.Products;

import Model.ElementAndBoxAndDirection.Element;

public class Product extends Element {
    private double firstTime;
    private double secondTime;
    private boolean isPickedUp = false;
    private ProductTypes type;

    public Product(double firstTime, String type) {
        this.firstTime = firstTime;
        this.secondTime = firstTime + 10;

        this.checkEgg(type);
        this.checkFeather(type);
        this.checkHorn(type);

        this.checkCake(type);
        this.checkPowderedEgg(type);
        this.checkCookie(type);
        this.checkCloth(type);
        this.checkFlour(type);
        this.checkSewing(type);
        this.checkWool(type);
    }

    private void checkEgg(String type) {
        if (type.equals("egg")) {
            price = 10;
            volume = 1;
            name = "egg";
            this.type = ProductTypes.egg;
        }
    }

    private void checkFeather(String type) {
        if (type.equals("feather")) {
            price = 100;
            volume = 1;
            name = "feather";
            this.type = ProductTypes.feather;
        }
    }

    private void checkHorn(String type) {
        if (type.equals("horn")) {
            price = 1000;
            volume = 2;
            name = "horn";
            this.type = ProductTypes.horn;
        }
    }

    private void checkCake(String type) {
        if (type.equals("cake")) {
            volume = 2;
            price = 100;
            name = "cake";
            this.type = ProductTypes.cake;
        }

    }

    private void checkCookie(String type) {
        if (type.equals("cookie")) {
            price = 100;
            volume = 2;
            name = "cookie";
            this.type = ProductTypes.cookie;
        }
    }


    private void checkCloth(String type) {
        if (type.equals("cloth")) {
            name = "cloth";
            volume = 1;
            price = 300;
        }
    }

    private void checkPowderedEgg(String type) {
        if (type.equals("powderedEgg")) {
            price = 50;
            volume = 2;
            name = "powderedEgg";
            this.type = ProductTypes.powderedEgg;
        }

    }

    private void checkFlour(String type) {
        if (type.equals("flour")) {
            name = "flour";
            volume = 1;
            price = 10;
            this.type = ProductTypes.flour;
        }
    }

    private void checkSewing(String type) {
        if (type.equals("sewing")) {
            name = "sewing";
            volume = 1;
            price = 150;
            this.type = ProductTypes.sewing;
        }

    }

    private void checkWool(String type) {
        if (type.equals("wool")) {
            name = "wool";
            price = 100;
            volume = 1;
            this.type = ProductTypes.wool;
        }
    }


    public double getFirstTime() {
        return firstTime;
    }

    public double getSecondTime() {
        return secondTime;
    }

    public void setIsPickedUp(boolean check) {
        isPickedUp = check;
    }

    public boolean isPickedUp() {
        return isPickedUp;
    }

    @Override
    public void move(double finalX, double finalY) {
        //s
    }

    @Override
    public boolean upgrade() {
        return false;
    }
}
