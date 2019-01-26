package Model.Animal;

import Model.ElementAndBoxAndDirection.Direction;
import Model.Products.Product;
import View.Animations.SpriteAnimation;
import javafx.animation.Animation;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class Cat extends Animal {
    private static final int speedNormally = 1;

    {
      speed = speedNormally;
      moneyForUpgrading = 100;
    }

    public Cat(String name) {
        this.name = name;
    }

    @Override
    public void move(int finalX, int finalY)  {
        //nothing
    }


    public int getLevel() {
    	return level;
    }
    @Override
    public boolean upgrade() {
        if (level == 0) {
            speed += 2;
            level++;
            return true;
        }
        return false;
    }

    //////////////////////GRAPHIC_CAT////////////////////////////////////
    private transient ImageView catView = new ImageView();
    private transient SpriteAnimation catAnimation;
    private static final String CAT = "C:\\Users\\Home\\Desktop\\farmFrenzySaveFiles\\Animals\\cat\\";
    private Direction previousDir = Direction.northEast;


    public ImageView getCatView() {
        return catView;
    }

    private boolean removeTheCatAnimation(Group mapGroup) throws FileNotFoundException {
        if (mapGroup.getChildren().contains(catView)) {
            if (previousDir != getDirection()) {
                if (catAnimation != null)
                    catAnimation.stop();
                previousDir = this.getDirection();
                return  true;
            }
        } else if (!mapGroup.getChildren().contains(catView)){
            mapGroup.getChildren().add(catView);
            return true;
        }
        return false;
    }


    private void suitableSpriteAnimationForChicken(Group group, Duration duration)
            throws FileNotFoundException {
        catSouthEastMoving(duration);
        catNorthEastMoving(duration);
        catNorthWestMoving(duration);
        catSouthWestMoving(duration);
        catNorthMoving(duration);
        catSouthMoving(duration);
        catEastMoving(duration);
        catWestMoving(duration);
    }


    private void catNorthEastMoving(Duration duration) throws FileNotFoundException {
        if (getDirection().equals(Direction.northEast)) {
            catView.setImage(new Image(new FileInputStream(CAT + "northWest.png")));
            catView.setScaleX(-1);
            catAnimation = new SpriteAnimation(catView, duration, 24,
                    6, 0, 0, 72, 80);
            catAnimation.setCycleCount(Animation.INDEFINITE);
            catAnimation.play();
        }

    }

    private void catSouthEastMoving(Duration duration) throws FileNotFoundException {
        if (getDirection().equals(Direction.southEast)) {
            catView.setImage(new Image(new FileInputStream(CAT + "southWest.png")));
            catView.setScaleX(-1);
            catAnimation = new SpriteAnimation(catView, duration, 24,
                    6, 0, 0, 72, 84);
            catAnimation.setCycleCount(Animation.INDEFINITE);
            catAnimation.play();
        }
    }

    private void catNorthWestMoving(Duration duration) throws FileNotFoundException {
        if (getDirection().equals(Direction.northWest)) {
            catView.setImage(new Image(new FileInputStream(CAT + "northWest.png")));
            catView.setScaleX(1);
            catAnimation = new SpriteAnimation(catView, duration, 24,
                    6, 0, 0, 72, 80);
            catAnimation.setCycleCount(Animation.INDEFINITE);
            catAnimation.play();
        }

    }

    private void catSouthWestMoving(Duration duration) throws FileNotFoundException {
        if (getDirection().equals(Direction.southWest)) {
            catView.setImage(new Image(new FileInputStream(CAT + "southWest.png")));
            catView.setScaleX(1);
            catAnimation = new SpriteAnimation(catView, duration, 24,
                    6, 0, 0, 72, 84);

            catAnimation.setCycleCount(Animation.INDEFINITE);
            catAnimation.play();
        }

    }

    private void catNorthMoving(Duration duration) throws FileNotFoundException {
        if (getDirection().equals(Direction.north)) {
            catView.setImage(new Image(new FileInputStream(CAT + "north.png")));
            catView.setScaleX(1);
            catAnimation = new SpriteAnimation(catView, duration, 24,
                    6, 0, 0, 48, 84);
            catAnimation.setCycleCount(Animation.INDEFINITE);
            catAnimation.play();
        }
    }

    private void catSouthMoving(Duration duration) throws FileNotFoundException {
        if (getDirection().equals(Direction.south)) {
            catView.setImage(new Image(new FileInputStream(CAT + "south.png")));
            catView.setScaleX(1);
            catAnimation = new SpriteAnimation(catView, duration, 24,
                    6, 0, 0, 50, 91);
            catAnimation.setCycleCount(Animation.INDEFINITE);
            catAnimation.play();
        }
    }

    private void catEastMoving(Duration duration) throws FileNotFoundException {
        if (getDirection().equals(Direction.east)) {
            catView.setImage(new Image(new FileInputStream(CAT + "west.png")));
            catView.setScaleX(-1);
            catAnimation = new SpriteAnimation(catView, duration, 24,
                    4, 0, 0, 88, 68);
            catAnimation.setCycleCount(Animation.INDEFINITE);
            catAnimation.play();
        }
    }

    private void catWestMoving(Duration duration) throws FileNotFoundException {
        if (getDirection().equals(Direction.west)) {
            catView.setImage(new Image(new FileInputStream(CAT + "west.png")));
            catView.setScaleX(1);
            catAnimation = new SpriteAnimation(catView, duration, 24, 4,
                    0, 0, 88, 68);
            catAnimation.setCycleCount(Animation.INDEFINITE);
            catAnimation.play();
        }
    }

    public void catMoving(Scene scene, Group mapGroup, boolean isEntered) throws FileNotFoundException {
        Duration duration;
        catView.relocate(250 + getX() * 12, 250 + getY() * 7);
        if (isEntered) {
            duration = Duration.millis(10);
        }
        else
            duration = Duration.millis(1000);
        if (removeTheCatAnimation(mapGroup)) {
            suitableSpriteAnimationForChicken(mapGroup, duration);
        }
    }

}
