package Model.Animal;

import Model.ElementAndBoxAndDirection.Direction;

public class Dog extends Animal {

    {
           speed = 2;
            price = 0;
            volume = 0;
            level = 0;
            name = "dog";
    }

    public boolean getIsKilled() {
        return isKilled;
    }

    @Override
    public void move(double finalX, double finalY) {
       //nothing
    }


    @Override
    public boolean upgrade() {
        return false;
    }
}
