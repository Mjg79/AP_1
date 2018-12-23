package Model.Animal.WildAnimals;

import Model.Animal.Animal;

public class WildAnimal extends Animal {
    private boolean isCaged = false;

    @Override
    public void move(double finalX, double finalY) {
        x = finalX;
        y = finalY;
    }

    @Override
    public boolean upgrade() {
        return false;
    }

    public void setIsCaged(boolean check) {
        isCaged = check;
    }

    public boolean isCaged() {
        return isCaged;
    }

}
