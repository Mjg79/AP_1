package Model.Animal.WildAnimals;

import Model.Animal.Animal;
import Model.ElementAndBoxAndDirection.Direction;
import Model.MapAndCell.Map;
import View.Animations.SpriteAnimation;
import View.Buttons.LiveStocks.Wild.WildAnimalButton;
import View.Buttons.ProductButton;
import javafx.animation.Animation;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class WildAnimal extends Animal {
    private boolean isCaged = false;

    {
        name = "lion";
    }


    @Override
    public void move(int finalX, int finalY) {
        x = finalX;
        y = finalY;
    }

    @Override
    public boolean upgrade() {
        return true;
    }

    public void setIsCaged(boolean check) {
        isCaged = check;
    }

    public boolean isCaged() {
        return isCaged;
    }

    /////////////////////GRAPHIC_WILDANIMAL////////////////////////////////////////////////////
    private Button wildButton = new Button("lions");
    private ImageView wildAnimalView = new ImageView();
    private ImageView cageView = new ImageView();
    private static final String LION = "C:\\Users\\Home\\Desktop\\farmFrenzySaveFiles\\Animals\\Lion\\";
    private static final String CAGE = "C:\\Users\\Home\\Desktop\\farmFrenzySaveFiles\\Cages\\";
    private Direction previousDir = Direction.northEast;
    private boolean cagePlayed = false;

    private SpriteAnimation wildAnimalAnimation;
    private SpriteAnimation cageAnimation;


    private boolean removeTheLiveStockAnimation(Group mapGroup) {
        if (mapGroup.getChildren().contains(wildAnimalView)) {
            if (cagePlayed) {
                cageAnimation.stop();
                wildAnimalAnimation.stop();
                return false;
            }
            if (isCaged && mapGroup.getChildren().contains(cageView)) {
                wildAnimalAnimation.stop();
                cageAnimation.stop();
                cagePlayed = true;
                return true;
            }
            if (previousDir != getDirection() || this.isCaged()) {
                if (wildAnimalAnimation != null)
                    wildAnimalAnimation.stop();
                previousDir = getDirection();
                if (isCaged && !mapGroup.getChildren().contains(cageView))
                    mapGroup.getChildren().add(cageView);
                return  true;
            }
        } else if (!mapGroup.getChildren().contains(wildAnimalView)){
            mapGroup.getChildren().add(wildAnimalView);
            return true;
        }
        return false;
    }
    private void suitableSpriteAnimationForWild(Group mapGroup, Scene scene, Map map, double farmTime,
                                                Duration duration)
            throws FileNotFoundException {

        if (this.isCaged()) {
            wildAnimalCagedSituation(mapGroup, scene, map, farmTime);
        } else {
            wildAnimalSouthEastMoving(duration);
            wildAnimalNorthEastMoving(duration);
            wildAnimalNorthWestMoving(duration);
            wildAnimalSouthWestMoving(duration);
            wildAnimalNorthMoving(duration);
            wildAnimalSouthMoving(duration);
            wildAnimalEastMoving(duration);
            wildAnimalWestMoving(duration);
        }
    }

    private void wildAnimalCagedSituation(Group mapGroup, Scene scene, Map map, double farmTime)
            throws FileNotFoundException {
        wildAnimalView.setImage(new Image(new FileInputStream(LION + "caged.png")));
        wildAnimalAnimation = new SpriteAnimation(wildAnimalView, Duration.millis(1), 24,
                6, 0, 0, 132, 142);
        wildAnimalAnimation.play();
        cageView.setImage(new Image(new FileInputStream(CAGE + "build01.png")));
        cageView.relocate(250 + getX(), 250 + getY());
        cageAnimation = new SpriteAnimation(cageView, Duration.millis(1), 9, 3, 0,
                0, 260, 260);
        cageAnimation.play();
        if (!wildButton.getText().equals("product") && this.isCaged) {
            wildButton = WildAnimalButton.wildProductButton(wildButton, wildAnimalView, cageView, mapGroup,
                    scene, this, map, farmTime);

        }

    }

    private void wildAnimalNorthEastMoving(Duration duration) throws FileNotFoundException {
        if (getDirection().equals(Direction.northEast) && getName().equals("lion")) {
            wildAnimalView.setImage(new Image(new FileInputStream(LION + "northWest.png")));
            wildAnimalView.setScaleX(-1);
            wildAnimalAnimation = new SpriteAnimation(wildAnimalView, duration, 24,
                    6, 0, 0, 120, 106);
            wildAnimalAnimation.setCycleCount(Animation.INDEFINITE);
            wildAnimalAnimation.play();
        }

    }

    private void wildAnimalSouthEastMoving(Duration duration) throws FileNotFoundException {
        if (getDirection().equals(Direction.southEast) && getName().equals("lion")) {
            wildAnimalView.setImage(new Image(new FileInputStream(LION + "southWest.png")));
            wildAnimalView.setScaleX(-1);
            wildAnimalAnimation = new SpriteAnimation(wildAnimalView, duration, 24,
                    4, 0, 0, 118, 90);
            wildAnimalAnimation.setCycleCount(Animation.INDEFINITE);
            wildAnimalAnimation.play();
        }
    }

    private void wildAnimalNorthWestMoving(Duration duration) throws FileNotFoundException {
        if (getDirection().equals(Direction.northWest) && getName().equals("lion")) {
            wildAnimalView.setImage(new Image(new FileInputStream(LION + "northWest.png")));
            wildAnimalView.setScaleX(1);
            wildAnimalAnimation = new SpriteAnimation(wildAnimalView, duration, 24,
                    6, 0, 0, 120, 106);
            wildAnimalAnimation.setCycleCount(Animation.INDEFINITE);
            wildAnimalAnimation.play();
        }

    }

    private void wildAnimalSouthWestMoving(Duration duration) throws FileNotFoundException {
        if (getDirection().equals(Direction.southWest) && getName().equals("lion")) {
            wildAnimalView.setImage(new Image(new FileInputStream(LION + "southWest.png")));
            wildAnimalView.setScaleX(1);
            wildAnimalAnimation = new SpriteAnimation(wildAnimalView, duration, 24,
                    4, 0, 0, 118, 90);
            wildAnimalAnimation.setCycleCount(Animation.INDEFINITE);
            wildAnimalAnimation.play();
        }

    }

    private void wildAnimalNorthMoving(Duration duration) throws FileNotFoundException {
        if (getDirection().equals(Direction.north) && getName().equals("lion")) {
            wildAnimalView.setImage(new Image(new FileInputStream(LION + "north.png")));
            wildAnimalView.setScaleX(1);
            wildAnimalAnimation = new SpriteAnimation(wildAnimalView, duration, 24,
                    6, 0, 0, 94, 116);
            wildAnimalAnimation.setCycleCount(Animation.INDEFINITE);
            wildAnimalAnimation.play();
        }
    }

    private void wildAnimalSouthMoving(Duration duration) throws FileNotFoundException {
        if (getDirection().equals(Direction.south) && getName().equals("lion")) {
            wildAnimalView.setImage(new Image(new FileInputStream(LION + "south.png")));
            wildAnimalView.setScaleX(1);
            wildAnimalAnimation = new SpriteAnimation(wildAnimalView, duration, 24,
                    5, 0, 0, 96, 92);
            wildAnimalAnimation.setCycleCount(Animation.INDEFINITE);
            wildAnimalAnimation.play();
        }
    }

    private void wildAnimalEastMoving(Duration duration) throws FileNotFoundException {
        if (getDirection().equals(Direction.east) && getName().equals("lion")) {
            wildAnimalView.setImage(new Image(new FileInputStream(LION + "west.png")));
            wildAnimalView.setScaleX(-1);
            wildAnimalAnimation = new SpriteAnimation(wildAnimalView, duration, 24,
                    3, 0, 0, 138, 92);
            wildAnimalAnimation.setCycleCount(Animation.INDEFINITE);
            wildAnimalAnimation.play();
        }
    }

    private void wildAnimalWestMoving(Duration duration) throws FileNotFoundException {
        if (getDirection().equals(Direction.west) && getName().equals("lion")) {
            wildAnimalView.setImage(new Image(new FileInputStream(LION + "west.png")));
            wildAnimalView.setScaleX(1);
            wildAnimalAnimation = new SpriteAnimation(wildAnimalView, duration, 24, 3,
                    0, 0, 138, 92);
            wildAnimalAnimation.setCycleCount(Animation.INDEFINITE);
            wildAnimalAnimation.play();
        }
    }

    public void wildAnimalMoving(Map map, Scene scene, Group mapGroup, boolean isEntered, double farmTime)
            throws FileNotFoundException {
        Duration duration;

        if (!wildButton.getText().equals("product")) {
            wildButton = WildAnimalButton.wildButton(wildButton, scene, this, map);
            mapGroup.getChildren().remove(wildButton);
            wildAnimalView.relocate(250 + (int) getX() * 9, 250 + (int) getY() * 6);
        }

        wildButton.relocate(286 + getX() * 9, 284 + getY() * 6);

        if (!mapGroup.getChildren().contains(wildButton))
            mapGroup.getChildren().add(wildButton);

        if (isEntered) {
            duration = Duration.millis(10);
        }
        else
            duration = Duration.millis(1000);
        if (removeTheLiveStockAnimation(mapGroup))
            suitableSpriteAnimationForWild(mapGroup, scene, map, farmTime, duration);

    }

}
