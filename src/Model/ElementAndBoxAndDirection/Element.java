package Model.ElementAndBoxAndDirection;

public abstract class Element {
    protected int x;
    protected int y;
    protected Direction direction;
    protected int level = 0;
    protected int volume;
    protected int price;
    protected String name;
    protected double moneyForUpgrading;
    public abstract void move(int finalX, int finalY);

    public int getX () {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public Direction getDirection() {
        return direction;
    }

    public int getLevel() {
        return level;
    }
    public abstract boolean upgrade();

    public int getVolume() {
        return volume;
    }

    public int getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }

    public double getMoneyForUpgrading() {
        return moneyForUpgrading;
    }
}//it's complete
