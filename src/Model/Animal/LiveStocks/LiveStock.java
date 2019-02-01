package Model.Animal.LiveStocks;

import Model.Animal.Animal;
import Model.ElementAndBoxAndDirection.Direction;
import Model.Products.Product;
import View.Animations.SpriteAnimation;
import javafx.animation.Animation;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class LiveStock extends Animal {
    private double startTimeForEatingForage = -5;//just for denying some requests from the start of game
    private double startTimeBeingInMap;
    private static final double maxHungerLevel = 8;
    private double hungerLevel = maxHungerLevel;
    private boolean moveWisely = false;
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

    public boolean isMoveWisely() {
        return moveWisely;
    }

    public void setMoveWisely(boolean moveWisely) {
        this.moveWisely = moveWisely;
    }

    public LiveStock(double startBeingInMap, String type) {
        this.startTimeBeingInMap = startBeingInMap;
        if (type.equals("chicken")) {
            this.type = AnimalType.chicken;
            price = 50;
            volume = 2;
            level = 0;
            name = "chicken";
        }

        if (type.equals("buffalo")) {
            this.type = AnimalType.cow;
            price = 5000;
            volume = 10;
            level = 0;
            name = "buffalo";
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
        if (type.equals(AnimalType.chicken)) {
            Product product = new Product(time, "egg");
            product.setX(this.x);
            product.setY(this.y);
            return product;
        }
        if (type.equals(AnimalType.ostrich)) {
            Product product = new Product(time, "feather");
            product.setX(this.x);
            product.setY(this.y);
            return product;
        }
        Product product = new Product(time, "horn");
        product.setX(this.x);
        product.setY(this.y);
        return product;
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
    private transient ImageView liveStockView = new ImageView();
    private static final String CHICKEN = "C:\\Users\\Home\\Desktop\\farmFrenzySaveFiles\\Animals\\GuineaFowl\\";
    private static final String OSTRICH = "C:\\Users\\Home\\Desktop\\farmFrenzySaveFiles\\Animals\\Ostrich\\";
    private static final String BUFFALO = "C:\\Users\\Home\\Desktop\\farmFrenzySaveFiles\\Animals\\Buffalo\\";
    private Direction previousDir = Direction.northEast;
    private int previousX = 50;
    private int previousY = 50;
    public boolean graphicDeath = false;
    public int durationDeath = 0;
    private transient SpriteAnimation liveStockAnimation;


    public ImageView getLiveStockView() {
        return liveStockView;
    }

    private boolean removeTheLiveStockAnimation(Group mapGroup) throws FileNotFoundException {

        if (previousX == x && previousY == y && moveWisely) {
            return true;
        }

        if (previousX != x && previousY != y) {
            previousX = x;
            previousY = y;
        }
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
    private void suitableSpriteAnimationForLiveStock(Group group, Duration duration)
            throws FileNotFoundException {
        System.out.println("\n\npreviousX: " + previousX + ", PreviousY: " + previousY);
        if (previousX == x && previousY == y && moveWisely) {
            this.liveStockEatingSituation();
        }

        else {
            liveStockSouthEastMoving(duration);
            liveStockNorthEastMoving(duration);
            liveStockNorthWestMoving(duration);
            liveStockSouthWestMoving(duration);
            liveStockNorthMoving(duration);
            liveStockSouthMoving(duration);
            liveStockEastMoving(duration);
            liveStockWestMoving(duration);

        }

    }

    private void liveStockEatingSituation() throws FileNotFoundException {
        if (getName().equals("chicken")) {
            liveStockView.setImage(new Image(new FileInputStream(CHICKEN + "eat.png")));
            liveStockView.setViewport(new Rectangle2D(0, 0, 64, 84));
            liveStockAnimation = new SpriteAnimation(liveStockView, Duration.millis(1000), 24,
                    5, 0, 0, 74, 64);
            liveStockAnimation.setCycleCount(2);
            liveStockAnimation.play();
        }

        if (getName().equals("ostrich")) {
            liveStockView.setImage(new Image(new FileInputStream(OSTRICH + "eat.png")));
            liveStockView.setViewport(new Rectangle2D(0, 0, 64, 84));
            liveStockAnimation = new SpriteAnimation(liveStockView, Duration.millis(1000), 24,
                    4, 0, 0, 114, 114);
            liveStockAnimation.setCycleCount(2);
            liveStockAnimation.play();
        }
        if (getName().equals("buffalo")) {
            liveStockView.setImage(new Image(new FileInputStream(BUFFALO + "eat.png")));
            liveStockView.setViewport(new Rectangle2D(0, 0, 64, 84));
            liveStockAnimation = new SpriteAnimation(liveStockView, Duration.millis(1000), 24,
                    6, 0, 0, 160, 122) ;
            liveStockAnimation.setCycleCount(2);
            liveStockAnimation.play();
        }

    }

    private void liveStockDeathSituation(Group group) throws FileNotFoundException {
        if (getName().equals("chicken")) {
            liveStockView.setImage(new Image(new FileInputStream(CHICKEN + "death.png")));
            liveStockView.setViewport(new Rectangle2D(0, 0, 64, 84));
            liveStockAnimation = new SpriteAnimation(liveStockView, Duration.millis(1000), 24,
                    5, 0, 0, 78, 70);
            durationDeath++;
            liveStockAnimation.setCycleCount(1);
        }
        if (getName().equals("ostrich")) {
            liveStockView.setImage(new Image(new FileInputStream(OSTRICH + "death.png")));
            liveStockView.setViewport(new Rectangle2D(0, 0, 64, 84));
            liveStockAnimation = new SpriteAnimation(liveStockView, Duration.millis(1000), 24,
                    6, 0, 0, 144, 128);
            durationDeath++;
            liveStockAnimation.setCycleCount(1);
        }
        if (getName().equals("buffalo")) {
            liveStockView.setImage(new Image(new FileInputStream(BUFFALO + "death.png")));
            liveStockView.setViewport(new Rectangle2D(0, 0, 64, 84));
            liveStockAnimation = new SpriteAnimation(liveStockView, Duration.millis(1000), 30,
                    5, 0, 0, 166, 134);
            durationDeath++;
            liveStockAnimation.setCycleCount(1);
        }

    }

    private void liveStockNorthEastMoving(Duration duration) throws FileNotFoundException {
        if (getDirection().equals(Direction.northEast) && getName().equals("chicken")) {
            liveStockView.setImage(new Image(new FileInputStream(CHICKEN + "northWest.png")));
            liveStockView.setViewport(new Rectangle2D(0, 0, 64, 84));
            liveStockView.setScaleX(-1);
            liveStockAnimation = new SpriteAnimation(liveStockView, duration, 24,
                    5, 0, 0, 68, 80);
            liveStockAnimation.setCycleCount(Animation.INDEFINITE);
            liveStockAnimation.play();
        }
        if (getDirection().equals(Direction.northEast) && getName().equals("ostrich")) {
            liveStockView.setImage(new Image(new FileInputStream(OSTRICH + "northWest.png")));
            liveStockView.setViewport(new Rectangle2D(0, 0, 64, 84));
            liveStockView.setScaleX(-1);
            liveStockAnimation = new SpriteAnimation(liveStockView, duration, 24,
                    4, 0, 0, 92, 140);
            liveStockAnimation.setCycleCount(Animation.INDEFINITE);
            liveStockAnimation.play();
        }
        if (getDirection().equals(Direction.northEast) && getName().equals("buffalo")) {
            liveStockView.setImage(new Image(new FileInputStream(BUFFALO + "northWest.png")));
            liveStockView.setViewport(new Rectangle2D(0, 0, 64, 84));
            liveStockView.setScaleX(-1);
            liveStockAnimation = new SpriteAnimation(liveStockView, duration, 24,
                    4, 0, 0, 150, 132);
            liveStockAnimation.setCycleCount(Animation.INDEFINITE);
            liveStockAnimation.play();
        }

    }

    private void liveStockSouthEastMoving(Duration duration) throws FileNotFoundException {
        if (getDirection().equals(Direction.southEast) && getName().equals("chicken")) {
            liveStockView.setImage(new Image(new FileInputStream(CHICKEN + "southWest.png")));
            liveStockView.setViewport(new Rectangle2D(0, 0, 64, 84));
            liveStockView.setScaleX(-1);
            liveStockAnimation = new SpriteAnimation(liveStockView, duration, 24,
                    5, 0, 0, 70, 72);
            liveStockAnimation.setCycleCount(Animation.INDEFINITE);
            liveStockAnimation.play();
        }
        if (getDirection().equals(Direction.southEast) && getName().equals("ostrich")) {
            liveStockView.setImage(new Image(new FileInputStream(OSTRICH + "southWest.png")));
            liveStockView.setViewport(new Rectangle2D(0, 0, 64, 84));
            liveStockView.setScaleX(-1);
            liveStockAnimation = new SpriteAnimation(liveStockView, duration, 24,
                    6, 0, 0, 92, 126);
            liveStockAnimation.setCycleCount(Animation.INDEFINITE);
            liveStockAnimation.play();
        }
        if (getDirection().equals(Direction.southEast) && getName().equals("buffalo")) {
            liveStockView.setImage(new Image(new FileInputStream(BUFFALO + "southWest.png")));
            liveStockView.setViewport(new Rectangle2D(0, 0, 64, 84));
            liveStockView.setScaleX(-1);
            liveStockAnimation = new SpriteAnimation(liveStockView, duration, 24,
                    6, 0, 0, 150, 120);
            liveStockAnimation.setCycleCount(Animation.INDEFINITE);
            liveStockAnimation.play();
        }

    }

    private void liveStockNorthWestMoving(Duration duration) throws FileNotFoundException {
        if (getDirection().equals(Direction.northWest) && getName().equals("chicken")) {
            liveStockView.setImage(new Image(new FileInputStream(CHICKEN + "northWest.png")));
            liveStockView.setViewport(new Rectangle2D(0, 0, 64, 84));
            liveStockView.setScaleX(1);
            liveStockAnimation = new SpriteAnimation(liveStockView, duration, 24,
                    5, 0, 0, 68, 80);
            liveStockAnimation.setCycleCount(Animation.INDEFINITE);
            liveStockAnimation.play();
        }
        if (getDirection().equals(Direction.northWest) && getName().equals("ostrich")) {
            liveStockView.setImage(new Image(new FileInputStream(OSTRICH + "northWest.png")));
            liveStockView.setViewport(new Rectangle2D(0, 0, 64, 84));
            liveStockView.setScaleX(1);
            liveStockAnimation = new SpriteAnimation(liveStockView, duration, 24,
                    4, 0, 0, 92, 140);
            liveStockAnimation.setCycleCount(Animation.INDEFINITE);
            liveStockAnimation.play();
        }
        if (getDirection().equals(Direction.northWest) && getName().equals("buffalo")) {
            liveStockView.setImage(new Image(new FileInputStream(BUFFALO + "northWest.png")));
            liveStockView.setViewport(new Rectangle2D(0, 0, 64, 84));
            liveStockView.setScaleX(1);
            liveStockAnimation = new SpriteAnimation(liveStockView, duration, 24,
                    4, 0, 0, 150, 132);
            liveStockAnimation.setCycleCount(Animation.INDEFINITE);
            liveStockAnimation.play();
        }

    }

    private void liveStockSouthWestMoving(Duration duration) throws FileNotFoundException {
        if (getDirection().equals(Direction.southWest) && getName().equals("chicken")) {
            liveStockView.setImage(new Image(new FileInputStream(CHICKEN + "southWest.png")));
            liveStockView.setViewport(new Rectangle2D(0, 0, 64, 84));
            liveStockView.setScaleX(1);
            liveStockAnimation = new SpriteAnimation(liveStockView, duration, 24,
                    5, 0, 0, 70, 72);

            liveStockAnimation.setCycleCount(Animation.INDEFINITE);
            liveStockAnimation.play();
        }
        if (getDirection().equals(Direction.southWest) && getName().equals("ostrich")) {
            liveStockView.setImage(new Image(new FileInputStream(OSTRICH + "southWest.png")));
            liveStockView.setViewport(new Rectangle2D(0, 0, 64, 84));
            liveStockView.setScaleX(1);
            liveStockAnimation = new SpriteAnimation(liveStockView, duration, 24,
                    6, 0, 0, 92, 126);

            liveStockAnimation.setCycleCount(Animation.INDEFINITE);
            liveStockAnimation.play();
        }
        if (getDirection().equals(Direction.southWest) && getName().equals("buffalo")) {
            liveStockView.setImage(new Image(new FileInputStream(BUFFALO + "southWest.png")));
            liveStockView.setViewport(new Rectangle2D(0, 0, 64, 84));
            liveStockView.setScaleX(1);
            liveStockAnimation = new SpriteAnimation(liveStockView, duration, 24,
                    6, 0, 0, 150, 120);

            liveStockAnimation.setCycleCount(Animation.INDEFINITE);
            liveStockAnimation.play();
        }

    }

    private void liveStockNorthMoving(Duration duration) throws FileNotFoundException {
        if (getDirection().equals(Direction.north) && getName().equals("chicken")) {
            liveStockView.setImage(new Image(new FileInputStream(CHICKEN + "north.png")));
            liveStockView.setViewport(new Rectangle2D(0, 0, 64, 84));
            liveStockView.setScaleX(1);
            liveStockAnimation = new SpriteAnimation(liveStockView, duration, 24,
                    5, 0, 0, 64, 84);
            liveStockAnimation.setCycleCount(Animation.INDEFINITE);
            liveStockAnimation.play();
        }
        if (getDirection().equals(Direction.north) && getName().equals("ostrich")) {
            liveStockView.setImage(new Image(new FileInputStream(OSTRICH + "north.png")));
            liveStockView.setViewport(new Rectangle2D(0, 0, 64, 84));
            liveStockView.setScaleX(1);
            liveStockAnimation = new SpriteAnimation(liveStockView, duration, 24,
                    4, 0, 0, 86, 142);
            liveStockAnimation.setCycleCount(Animation.INDEFINITE);
            liveStockAnimation.play();
        }
        if (getDirection().equals(Direction.north) && getName().equals("buffalo")) {
            liveStockView.setImage(new Image(new FileInputStream(BUFFALO + "north.png")));
            liveStockView.setViewport(new Rectangle2D(0, 0, 64, 84));
            liveStockView.setScaleX(1);
            liveStockAnimation = new SpriteAnimation(liveStockView, duration, 24,
                    6, 0, 0, 134, 124);
            liveStockAnimation.setCycleCount(Animation.INDEFINITE);
            liveStockAnimation.play();
        }

    }

    private void liveStockSouthMoving(Duration duration) throws FileNotFoundException {
        if (getDirection().equals(Direction.south) && getName().equals("chicken")) {
            liveStockView.setImage(new Image(new FileInputStream(CHICKEN + "south.png")));
            liveStockView.setViewport(new Rectangle2D(0, 0, 64, 84));
            liveStockView.setScaleX(1);
            liveStockAnimation = new SpriteAnimation(liveStockView, duration, 24,
                    5, 0, 0, 66, 72);
            liveStockAnimation.setCycleCount(Animation.INDEFINITE);
            liveStockAnimation.play();
        }
        if (getDirection().equals(Direction.south) && getName().equals("ostrich")) {
            liveStockView.setImage(new Image(new FileInputStream(OSTRICH + "south.png")));
            liveStockView.setViewport(new Rectangle2D(0, 0, 64, 84));
            liveStockView.setScaleX(1);
            liveStockAnimation = new SpriteAnimation(liveStockView, duration, 24,
                    6, 0, 0, 86, 126);
            liveStockAnimation.setCycleCount(Animation.INDEFINITE);
            liveStockAnimation.play();
        }
        if (getDirection().equals(Direction.south) && getName().equals("buffalo")) {
            liveStockView.setImage(new Image(new FileInputStream(BUFFALO + "south.png")));
            liveStockView.setViewport(new Rectangle2D(0, 0, 64, 84));
            liveStockView.setScaleX(1);
            liveStockAnimation = new SpriteAnimation(liveStockView, duration, 24,
                    6, 0, 0, 132, 110);
            liveStockAnimation.setCycleCount(Animation.INDEFINITE);
            liveStockAnimation.play();
        }

    }

    private void liveStockEastMoving(Duration duration) throws FileNotFoundException {
        if (getDirection().equals(Direction.east) && getName().equals("chicken")) {
            liveStockView.setImage(new Image(new FileInputStream(CHICKEN + "west.png")));
            liveStockView.setViewport(new Rectangle2D(0, 0, 64, 84));
            liveStockView.setScaleX(-1);
            liveStockAnimation = new SpriteAnimation(liveStockView, duration, 24,
                    5, 0, 0, 80, 74);
            liveStockAnimation.setCycleCount(Animation.INDEFINITE);
            liveStockAnimation.play();
        }
        if (getDirection().equals(Direction.east) && getName().equals("ostrich")) {
            liveStockView.setImage(new Image(new FileInputStream(OSTRICH + "west.png")));
            liveStockView.setViewport(new Rectangle2D(0, 0, 64, 84));
            liveStockView.setScaleX(-1);
            liveStockAnimation = new SpriteAnimation(liveStockView, duration, 24,
                    4, 0, 0, 100, 132);
            liveStockAnimation.setCycleCount(Animation.INDEFINITE);
            liveStockAnimation.play();
        }
        if (getDirection().equals(Direction.east) && getName().equals("buffalo")) {
            liveStockView.setImage(new Image(new FileInputStream(BUFFALO + "west.png")));
            liveStockView.setViewport(new Rectangle2D(0, 0, 64, 84));
            liveStockView.setScaleX(-1);
            liveStockAnimation = new SpriteAnimation(liveStockView, duration, 24,
                    4, 0, 0, 174, 128);
            liveStockAnimation.setCycleCount(Animation.INDEFINITE);
            liveStockAnimation.play();
        }

    }

    private void liveStockWestMoving(Duration duration) throws FileNotFoundException {
        if (getDirection().equals(Direction.west) && getName().equals("chicken")) {
            liveStockView.setImage(new Image(new FileInputStream(CHICKEN + "west.png")));
            liveStockView.setViewport(new Rectangle2D(0, 0, 64, 84));
            liveStockView.setScaleX(1);
            liveStockAnimation = new SpriteAnimation(liveStockView, duration, 24, 5,
                    0, 0, 80, 74);
            liveStockAnimation.setCycleCount(Animation.INDEFINITE);
            liveStockAnimation.play();
        }
        if (getDirection().equals(Direction.west) && getName().equals("ostrich")) {
            liveStockView.setImage(new Image(new FileInputStream(OSTRICH + "west.png")));
            liveStockView.setViewport(new Rectangle2D(0, 0, 64, 84));
            liveStockView.setScaleX(1);
            liveStockAnimation = new SpriteAnimation(liveStockView, duration, 24, 4,
                    0, 0, 100, 132);
            liveStockAnimation.setCycleCount(Animation.INDEFINITE);
            liveStockAnimation.play();
        }
        if (getDirection().equals(Direction.west) && getName().equals("buffalo")) {
            liveStockView.setImage(new Image(new FileInputStream(BUFFALO + "west.png")));
            liveStockView.setViewport(new Rectangle2D(0, 0, 64, 84));
            liveStockView.setScaleX(1);
            liveStockAnimation = new SpriteAnimation(liveStockView, duration, 24, 4,
                    0, 0, 174, 128);
            liveStockAnimation.setCycleCount(Animation.INDEFINITE);
            liveStockAnimation.play();
        }
    }

    public void liveStockMoving(Scene scene, Group mapGroup, boolean isEntered, double farmTime) throws FileNotFoundException {
        Duration duration;
        if (liveStockView == null){
            liveStockView = new ImageView();
            liveStockView.setViewport(new Rectangle2D(0, 0, 64, 84));
        }
        liveStockView.relocate(250 + (int)getX() * 12, 250 + (int)getY() * 7);
        if (isEntered) {
            duration = Duration.millis(10);
        }
        else
            duration = Duration.millis(1000);
        if (removeTheLiveStockAnimation(mapGroup)) {
            suitableSpriteAnimationForLiveStock(mapGroup, duration);
        }


    }

}
