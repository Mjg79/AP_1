package Model.Animal;

import Model.ElementAndBoxAndDirection.Direction;
import View.Animations.SpriteAnimation;
import javafx.animation.Animation;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Dog extends Animal {

    {
           speed = 1;
            price = 0;
            volume = 0;
            level = 0;
            name = "dog";
    }

    public boolean getIsKilled() {
        return isKilled;
    }

    @Override
    public void move(int finalX, int finalY) {
       //nothing
    }


    @Override
    public boolean upgrade() {
        return false;
    }


    //////////////////////DOG_GRAPHIC////////////////////////////////////////////////////////
    private ImageView dogView = new ImageView();
    private SpriteAnimation dogAnimation;
    private static final String DOG = "C:\\Users\\Home\\Desktop\\farmFrenzySaveFiles\\Animals\\dog\\";
    private Direction previousDir = Direction.northEast;


    private boolean removeTheDogAnimation(Group mapGroup) throws FileNotFoundException {
        if (mapGroup.getChildren().contains(dogView)) {
            if (previousDir != getDirection()) {
                if (dogAnimation != null)
                    dogAnimation.stop();
                previousDir = this.getDirection();
                return  true;
            }
        } else if (!mapGroup.getChildren().contains(dogView)){
            mapGroup.getChildren().add(dogView);
            return true;
        }
        return false;
    }


    private void suitableSpriteAnimationForDog(Group group, Duration duration)
            throws FileNotFoundException {
        dogSouthEastMoving(duration);
        dogNorthEastMoving(duration);
        dogNorthWestMoving(duration);
        dogSouthWestMoving(duration);
        dogNorthMoving(duration);
        dogSouthMoving(duration);
        dogEastMoving(duration);
        dogWestMoving(duration);
    }


    private void dogNorthEastMoving(Duration duration) throws FileNotFoundException {
        if (getDirection().equals(Direction.northEast)) {
            dogView.setImage(new Image(new FileInputStream(DOG + "northWest.png")));
            dogView.setScaleX(-1);
            dogAnimation = new SpriteAnimation(dogView, duration, 25,
                    5, 0, 0, 92, 98);
            dogAnimation.setCycleCount(Animation.INDEFINITE);
            dogAnimation.play();
        }

    }

    private void dogSouthEastMoving(Duration duration) throws FileNotFoundException {
        if (getDirection().equals(Direction.southEast)) {
            dogView.setImage(new Image(new FileInputStream(DOG + "southWest.png")));
            dogView.setScaleX(-1);
            dogAnimation = new SpriteAnimation(dogView, duration, 25,
                    5, 0, 0, 92, 84);
            dogAnimation.setCycleCount(Animation.INDEFINITE);
            dogAnimation.play();
        }
    }

    private void dogNorthWestMoving(Duration duration) throws FileNotFoundException {
        if (getDirection().equals(Direction.northWest)) {
            dogView.setImage(new Image(new FileInputStream(DOG + "northWest.png")));
            dogView.setScaleX(1);
            dogAnimation = new SpriteAnimation(dogView, duration, 25,
                    5, 0, 0, 92, 98);
            dogAnimation.setCycleCount(Animation.INDEFINITE);
            dogAnimation.play();
        }

    }

    private void dogSouthWestMoving(Duration duration) throws FileNotFoundException {
        if (getDirection().equals(Direction.southWest)) {
            dogView.setImage(new Image(new FileInputStream(DOG + "southWest.png")));
            dogView.setScaleX(1);
            dogAnimation = new SpriteAnimation(dogView, duration, 25,
                    5, 0, 0, 92, 84);

            dogAnimation.setCycleCount(Animation.INDEFINITE);
            dogAnimation.play();
        }

    }

    private void dogNorthMoving(Duration duration) throws FileNotFoundException {
        if (getDirection().equals(Direction.north)) {
            dogView.setImage(new Image(new FileInputStream(DOG + "north.png")));
            dogView.setScaleX(1);
            dogAnimation = new SpriteAnimation(dogView, duration, 24,
                    6, 0, 0, 66, 100);
            dogAnimation.setCycleCount(Animation.INDEFINITE);
            dogAnimation.play();
        }
    }

    private void dogSouthMoving(Duration duration) throws FileNotFoundException {
        if (getDirection().equals(Direction.south)) {
            dogView.setImage(new Image(new FileInputStream(DOG + "south.png")));
            dogView.setScaleX(1);
            dogAnimation = new SpriteAnimation(dogView, duration, 24,
                    6, 0, 0, 66, 84);
            dogAnimation.setCycleCount(Animation.INDEFINITE);
            dogAnimation.play();
        }
    }

    private void dogEastMoving(Duration duration) throws FileNotFoundException {
        if (getDirection().equals(Direction.east)) {
            dogView.setImage(new Image(new FileInputStream(DOG + "west.png")));
            dogView.setScaleX(-1);
            dogAnimation = new SpriteAnimation(dogView, duration, 24,
                    6, 0, 0, 108, 86);
            dogAnimation.setCycleCount(Animation.INDEFINITE);
            dogAnimation.play();
        }
    }

    private void dogWestMoving(Duration duration) throws FileNotFoundException {
        if (getDirection().equals(Direction.west)) {
            dogView.setImage(new Image(new FileInputStream(DOG + "west.png")));
            dogView.setScaleX(1);
            dogAnimation = new SpriteAnimation(dogView, duration, 24, 6,
                    0, 0, 108, 86);
            dogAnimation.setCycleCount(Animation.INDEFINITE);
            dogAnimation.play();
        }
    }

    public void dogMoving(Scene scene, Group mapGroup, boolean isEntered) throws FileNotFoundException {
        Duration duration;
        dogView.relocate(250 + getX() * 12, 250 + getY() * 7);
        if (isEntered) {
            duration = Duration.millis(10);
        }
        else
            duration = Duration.millis(1000);
        if (removeTheDogAnimation(mapGroup)) {
            suitableSpriteAnimationForDog(mapGroup, duration);
        }
    }

    public ImageView getDogView() {
        return dogView;
    }

    public SpriteAnimation getDogAnimation() {
        return dogAnimation;
    }

}
