package Model.ElementAndBoxAndDirection;

public abstract class Element {
    protected double x;
    protected double y;
    protected Direction direction;
    protected int level = 0;
    protected int volume;
    protected int price;
    protected String name;

    public abstract void move(double finalX, double finalY);

    public double getX () {
        return x;
    }

    public double getY() {
        return y;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public Direction getDirection() {
        return direction;
    }

    public abstract void upgrade();

    public int getVolume() {
        return volume;
    }

    public int getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }


}
