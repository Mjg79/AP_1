package Model.Animal.LiveStocks;

import Model.Animal.Animal;
import Model.ElementAndBoxAndDirection.Direction;
import Model.Products.Product;
import View.Animations.SpriteAnimation;
import javafx.animation.Animation;
import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class LiveStock extends Animal {
    private double startTimeForEatingForage = -5;//just for denying some requests from the start of game
    private double startTimeBeingInMap;
    private static final double maxHungerLevel = 8;
    private double hungerLevel = maxHungerLevel;
    private boolean mustEatForage = false;
    private AnimalType type;

    {
        speed = 1;
    }

    public AnimalType getType() {
        return type;
    }

    public double getStartTimeForEatingForage() {
        return this.startTimeForEatingForage;
    }


    public void setStartTimeForEatingForage(double time) {
        this.startTimeForEatingForage = time;
    }

    public LiveStock(double startBeingInMap, String type) {
        this.startTimeBeingInMap = startBeingInMap;
        System.out.println(this.startTimeBeingInMap + " for chicken");
        if (type.equals("chicken")) {
            this.type = AnimalType.chicken;
            price = 50;
            volume = 2;
            level = 0;
            name = "chicken";
        }

        if (type.equals("cow")) {
            this.type = AnimalType.cow;
            price = 5000;
            volume = 10;
            level = 0;
            name = "cow";
        }

        if (type.equals("ostrich")) {
            this.type = AnimalType.ostrich;
            price = 500;
            volume = 5;
            level = 0;
            name = "ostrich";
        }
    }

    public Product releaseProduct(double time) {
        if (type.equals(AnimalType.chicken))
            return new Product(time, "egg");
        if (type.equals(AnimalType.ostrich))
            return new Product(time, "feather");
        return new Product(time, "horn");
    }


    @Override
    public void move(int finalX, int finalY) {

    }


    public void mustEatForage(boolean check) {
        this.mustEatForage = check;
    }

    public boolean isMustEatForage() {
        return this.mustEatForage;
    }

    public void changeHungerLevel(double number) {
        hungerLevel += number;
    }

    public double getHungerLevel() {
        return hungerLevel;
    }

    public void checkLiveStock() {
        if(this.hungerLevel <= 3)
            this.mustEatForage(true);
        if(this.hungerLevel >= 7)
            this.mustEatForage(false);
    }


    public double getStartTimeBeingInMap() {
        return startTimeBeingInMap;
    }


    public static void main(String[] args) {
        AnimalType type = AnimalType.cow;
        System.out.println(type);
    }

    ///////////////////GERAPHIC_SECTION////////////////////////////////////////
    private ImageView liveStockView = new ImageView();
    private static final String CHICKEN = "C:\\Users\\Home\\Desktop\\farmFrenzySaveFiles\\Animals\\GuineaFowl\\";
    private static final String OSTRICH = "C:\\Users\\Home\\Desktop\\farmFrenzySaveFiles\\Animals\\Ostrich\\";
    private static final String BUFFALO = "C:\\Users\\Home\\Desktop\\farmFrenzySaveFiles\\Animals\\Buffalo\\";
    private Direction previousDir = Direction.northEast;
    public boolean graphicDeath = false;
    public int durationDeath = 0;
    private SpriteAnimation liveStockAnimation;


    private boolean removeTheLiveStockAnimation(Group mapGroup) throws FileNotFoundException {
        if (graphicDeath) {
            if (durationDeath == 0)
                liveStockAnimation.stop();
            this.liveStockDeathSituation(mapGroup);
            if (durationDeath == 1)
                liveStockAnimation.play();
            if (durationDeath == 10)
                mapGroup.getChildren().remove(liveStockView);
            return false;
        }
        if (mapGroup.getChildren().contains(liveStockView)) {
            if (previousDir != getDirection()) {
                if (liveStockAnimation != null)
                    liveStockAnimation.stop();
                previousDir = getDirection();
                return  true;
            }
        } else if (!mapGroup.getChildren().contains(liveStockView)){
            mapGroup.getChildren().add(liveStockView);
            return true;
        }
        return false;
    }
    private void suitableSpriteAnimationForChicken(Group group, Duration duration)
            throws FileNotFoundException {
        liveStockSouthEastMoving(duration);
        liveStockNorthEastMoving(duration);
        liveStockNorthWestMoving(duration);
        liveStockSouthWestMoving(duration);
        liveStockNorthMoving(duration);
        liveStockSouthMoving(duration);
        liveStockEastMoving(duration);
        liveStockWestMoving(duration);
    }

    private void liveStockEatingSituation() throws FileNotFoundException {
        liveStockView.setImage(new Image(new FileInputStream(CHICKEN + "eat.png")));
        liveStockAnimation = new SpriteAnimation(liveStockView, Duration.millis(1000), 24,
                5, 0, 0, 74, 64);

    }

    private void liveStockDeathSituation(Group group) throws FileNotFoundException {
        if (getName().equals("chicken")) {
            liveStockView.setImage(new Image(new FileInputStream(CHICKEN + "death.png")));
            liveStockAnimation = new SpriteAnimation(liveStockView, Duration.millis(1000), 24,
                    5, 0, 0, 78, 70);
            durationDeath++;
            liveStockAnimation.setCycleCount(1);
        }
    }

    private void liveStockNorthEastMoving(Duration duration) throws FileNotFoundException {
        if (getDirection().equals(Direction.northEast) && getName().equals("chicken")) {
            liveStockView.setImage(new Image(new FileInputStream(CHICKEN + "northWest.png")));
            liveStockView.setScaleX(-1);
            liveStockAnimation = new SpriteAnimation(liveStockView, duration, 24,
                    5, 0, 0, 68, 80);
            liveStockAnimation.setCycleCount(Animation.INDEFINITE);
            liveStockAnimation.play();
        }

    }

    private void liveStockSouthEastMoving(Duration duration) throws FileNotFoundException {
        if (getDirection().equals(Direction.southEast) && getName().equals("chicken")) {
            liveStockView.setImage(new Image(new FileInputStream(CHICKEN + "southWest.png")));
            liveStockView.setScaleX(-1);
            liveStockAnimation = new SpriteAnimation(liveStockView, duration, 24,
                    5, 0, 0, 70, 72);
            liveStockAnimation.setCycleCount(Animation.INDEFINITE);
            liveStockAnimation.play();
        }
    }

    private void liveStockNorthWestMoving(Duration duration) throws FileNotFoundException {
        if (getDirection().equals(Direction.northWest) && getName().equals("chicken")) {
            liveStockView.setImage(new Image(new FileInputStream(CHICKEN + "northWest.png")));
            liveStockView.setScaleX(1);
            liveStockAnimation = new SpriteAnimation(liveStockView, duration, 24,
                    5, 0, 0, 68, 80);
            liveStockAnimation.setCycleCount(Animation.INDEFINITE);
            liveStockAnimation.play();
        }

    }

    private void liveStockSouthWestMoving(Duration duration) throws FileNotFoundException {
        if (getDirection().equals(Direction.southWest) && getName().equals("chicken")) {
            liveStockView.setImage(new Image(new FileInputStream(CHICKEN + "southWest.png")));
            liveStockView.setScaleX(1);
            liveStockAnimation = new SpriteAnimation(liveStockView, duration, 24,
                    5, 0, 0, 70, 72);

            liveStockAnimation.setCycleCount(Animation.INDEFINITE);
            liveStockAnimation.play();
        }

    }

    private void liveStockNorthMoving(Duration duration) throws FileNotFoundException {
        if (getDirection().equals(Direction.north) && getName().equals("chicken")) {
            liveStockView.setImage(new Image(new FileInputStream(CHICKEN + "north.png")));
            liveStockView.setScaleX(1);
            liveStockAnimation = new SpriteAnimation(liveStockView, duration, 24,
                    5, 0, 0, 64, 84);
            liveStockAnimation.setCycleCount(Animation.INDEFINITE);
            liveStockAnimation.play();
        }
    }

    private void liveStockSouthMoving(Duration duration) throws FileNotFoundException {
        if (getDirection().equals(Direction.south) && getName().equals("chicken")) {
            liveStockView.setImage(new Image(new FileInputStream(CHICKEN + "south.png")));
            liveStockView.setScaleX(1);
            liveStockAnimation = new SpriteAnimation(liveStockView, duration, 24,
                    5, 0, 0, 66, 72);
            liveStockAnimation.setCycleCount(Animation.INDEFINITE);
            liveStockAnimation.play();
        }
    }

    private void liveStockEastMoving(Duration duration) throws FileNotFoundException {
        if (getDirection().equals(Direction.east) && getName().equals("chicken")) {
            liveStockView.setImage(new Image(new FileInputStream(CHICKEN + "west.png")));
            liveStockView.setScaleX(-1);
            liveStockAnimation = new SpriteAnimation(liveStockView, duration, 24,
                    5, 0, 0, 80, 74);
            liveStockAnimation.setCycleCount(Animation.INDEFINITE);
            liveStockAnimation.play();
        }
    }

    private void liveStockWestMoving(Duration duration) throws FileNotFoundException {
        if (getDirection().equals(Direction.west) && getName().equals("chicken")) {
            liveStockView.setImage(new Image(new FileInputStream(CHICKEN + "west.png")));
            liveStockView.setScaleX(1);
            liveStockAnimation = new SpriteAnimation(liveStockView, duration, 24, 5,
                    0, 0, 80, 74);
            liveStockAnimation.setCycleCount(Animation.INDEFINITE);
            liveStockAnimation.play();
        }
        //TODO:OSTRICH
        //TODO:BUFFALO
    }

    public void chickenMoving(Scene scene, Group mapGroup, boolean isEntered, double farmTime) throws FileNotFoundException {
        Duration duration;
        liveStockView.relocate(250 + (int)getX() * 12, 250 + (int)getY() * 7);
        if (isEntered) {
            duration = Duration.millis(10);
        }
        else
            duration = Duration.millis(1000);
        if (removeTheLiveStockAnimation(mapGroup)) {
            suitableSpriteAnimationForChicken(mapGroup, duration);
        }
    }

}
