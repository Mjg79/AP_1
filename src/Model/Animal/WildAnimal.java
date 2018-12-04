package Model.Animal;

public class WildAnimal extends Animal {
    private double speed = 1;// 1 cell per 0.33 second
    private boolean isCaged = false;

    {
        //TODO:checkBlockInitialize
    }

    @Override
    public void move(double finalX, double finalY) {
        x = finalX;
        y = finalY;
    }

    public void setIsCaged(boolean check) {
        isCaged = check;
    }

    public boolean getIsCaged() {
        return isCaged;
    }
}
