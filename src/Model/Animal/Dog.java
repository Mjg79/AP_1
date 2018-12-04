package Model.Animal;

import Model.ElementAndBoxAndDirection.Direction;

public class Dog extends Animal {
    private double speed = 3;//3 cell per 0.33 second
    private boolean isKilled = false;

    {
            price = 0;
            volume = 0;
            level = 0;
            direction = Direction.getRandomDirection();
            x = (int) (Math.random() * (35 - 5 + 1 ) + 5);
            y = (int) (Math.random() * (35 - 5 + 1 ) + 5);
    }

    public boolean getIsKilled() {
        return isKilled;
    }

    public void setIsKilled(boolean killed) {
        isKilled = killed;
    }

    @Override
    public void move(double finalX, double finalY) {
       //nothing
    }

    public void move(double finalX, double finalY, String condition) {
        if (condition.equals("killWildAnimal")) {
            //TODO: BFS from x,y to finalX, finalY
        }

        if (condition.equals("randomMove")) {
            //TODO:move randomly
        }
    }

    @Override
    public void upgrade() {
        //we have not any upgrade for dog
    }
}
