package Model.Animal.WildAnimals;

import Model.Animal.Animal;
import Model.ElementAndBoxAndDirection.Direction;
import Model.MapAndCell.Map;
import View.Animations.SpriteAnimation;
import View.Buttons.LiveStocks.Wild.WildAnimalButton;
import View.Buttons.ProductButton;
import javafx.animation.Animation;
import javafx.event.EventHandler;
import javafx.geometry.Rectangle2D;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class WildAnimal extends Animal {
    private boolean isCaged = false;
    private int numOfClickedOnCage = 1;
    private int timeLeftFromLastClick = 0;

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
        if(!(wildAnimalView.getImage() == new Image(new FileInputStream(LION + "caged.png")))){
            wildAnimalView.setImage(new Image(new FileInputStream(LION + "caged.png")));
            wildAnimalView.setScaleX(1);
            wildAnimalView.setScaleY(1);
            wildAnimalView.setViewport(new Rectangle2D(0, 0, 132, 142));
        }
        if(!(cageView.getImage() == new Image(new FileInputStream(CAGE + "build01.png"))) && timeLeftFromLastClick > 24)
            cageView.setImage(new Image(new FileInputStream(CAGE + "build01.png")));
        if(cageView.getViewport() == null || !cageView.getViewport().equals(new Rectangle2D(260 * (numOfClickedOnCage % 3), 260 * (numOfClickedOnCage / 3), 260, 260)) && timeLeftFromLastClick > 25)
            cageView.setViewport(new Rectangle2D(260 * (numOfClickedOnCage % 3), 260 * (numOfClickedOnCage / 3), 260, 260));
        cageView.relocate(195 + (int) getX() * 9, 200 + (int) getY() * 6);
        cageView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if((numOfClickedOnCage >= 8)) {
                    map.pickUpAndPutInWareHouse(x, y);
                    mapGroup.getChildren().remove(cageView);
                    mapGroup.getChildren().remove(wildAnimalView);
                }
                else {
                    numOfClickedOnCage++;
                    if(timeLeftFromLastClick <= 24) {
                        try {
                            cageView.setImage(new Image(new FileInputStream(CAGE + "build01.png")));
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }
                    }
                    timeLeftFromLastClick = 50;
                }
            }
        });
        cageView.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                scene.setCursor(Cursor.HAND);
            }
        });
        cageView.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                scene.setCursor(Cursor.DEFAULT);
            }
        });
        if(timeLeftFromLastClick > 0)
            timeLeftFromLastClick--;
        if(timeLeftFromLastClick == 24)
            cageView.setImage(new Image(new FileInputStream(CAGE + "break01.png")));
        if(timeLeftFromLastClick < 25 && timeLeftFromLastClick > 0){
            wildAnimalView.setViewport(new Rectangle2D(132 * ((24 - timeLeftFromLastClick) % 6), 142 * ((24 - timeLeftFromLastClick) / 6), 132, 142));
            cageView.setViewport(new Rectangle2D(260 * ((24 - timeLeftFromLastClick) % 5), 260 * ((24 - timeLeftFromLastClick) / 5), 260, 260));
        }
        else if(timeLeftFromLastClick == 0){
            isCaged = false;
            cageView.setImage(new Image(new FileInputStream(CAGE + "build01.png")));
            cageView.setViewport(new Rectangle2D(0, 0, 260, 260));
            suitableSpriteAnimationForWild(mapGroup, scene, map, farmTime, Duration.millis(1000));
        }
    }

    public ImageView getWildAnimalView() {
        return wildAnimalView;
    }

    public Button getWildButton() {
        return wildButton;
    }

    private void wildAnimalNorthEastMoving(Duration duration) throws FileNotFoundException {
        if (getDirection().equals(Direction.northEast) && getName().equals("lion")) {
            wildAnimalView.setImage(new Image(new FileInputStream(LION + "northWest.png")));
            wildAnimalView.setScaleX(-1);
            wildAnimalAnimation = new SpriteAnimation(wildAnimalView, duration, 24,
                    6, 0, 0, 120, 106);
            wildAnimalAnimation.setCycleCount(5);
            wildAnimalAnimation.play();
        }

    }

    private void wildAnimalSouthEastMoving(Duration duration) throws FileNotFoundException {
        if (getDirection().equals(Direction.southEast) && getName().equals("lion")) {
            wildAnimalView.setImage(new Image(new FileInputStream(LION + "southWest.png")));
            wildAnimalView.setScaleX(-1);
            wildAnimalAnimation = new SpriteAnimation(wildAnimalView, duration, 24,
                    4, 0, 0, 118, 90);
            wildAnimalAnimation.setCycleCount(5);
            wildAnimalAnimation.play();
        }
    }

    private void wildAnimalNorthWestMoving(Duration duration) throws FileNotFoundException {
        if (getDirection().equals(Direction.northWest) && getName().equals("lion")) {
            wildAnimalView.setImage(new Image(new FileInputStream(LION + "northWest.png")));
            wildAnimalView.setScaleX(1);
            wildAnimalAnimation = new SpriteAnimation(wildAnimalView, duration, 24,
                    6, 0, 0, 120, 106);
            wildAnimalAnimation.setCycleCount(5);
            wildAnimalAnimation.play();
        }

    }

    private void wildAnimalSouthWestMoving(Duration duration) throws FileNotFoundException {
        if (getDirection().equals(Direction.southWest) && getName().equals("lion")) {
            wildAnimalView.setImage(new Image(new FileInputStream(LION + "southWest.png")));
            wildAnimalView.setScaleX(1);
            wildAnimalAnimation = new SpriteAnimation(wildAnimalView, duration, 24,
                    4, 0, 0, 118, 90);
            wildAnimalAnimation.setCycleCount(5);
            wildAnimalAnimation.play();
        }

    }

    private void wildAnimalNorthMoving(Duration duration) throws FileNotFoundException {
        if (getDirection().equals(Direction.north) && getName().equals("lion")) {
            wildAnimalView.setImage(new Image(new FileInputStream(LION + "north.png")));
            wildAnimalView.setScaleX(1);
            wildAnimalAnimation = new SpriteAnimation(wildAnimalView, duration, 24,
                    6, 0, 0, 94, 116);
            wildAnimalAnimation.setCycleCount(5);
            wildAnimalAnimation.play();
        }
    }

    private void wildAnimalSouthMoving(Duration duration) throws FileNotFoundException {
        if (getDirection().equals(Direction.south) && getName().equals("lion")) {
            wildAnimalView.setImage(new Image(new FileInputStream(LION + "south.png")));
            wildAnimalView.setScaleX(1);
            wildAnimalAnimation = new SpriteAnimation(wildAnimalView, duration, 24,
                    5, 0, 0, 96, 92);
            wildAnimalAnimation.setCycleCount(5);
            wildAnimalAnimation.play();
        }
    }

    private void wildAnimalEastMoving(Duration duration) throws FileNotFoundException {
        if (getDirection().equals(Direction.east) && getName().equals("lion")) {
            wildAnimalView.setImage(new Image(new FileInputStream(LION + "west.png")));
            wildAnimalView.setScaleX(-1);
            wildAnimalAnimation = new SpriteAnimation(wildAnimalView, duration, 24,
                    3, 0, 0, 138, 92);
            wildAnimalAnimation.setCycleCount(5);
            wildAnimalAnimation.play();
        }
    }

    private void wildAnimalWestMoving(Duration duration) throws FileNotFoundException {
        if (getDirection().equals(Direction.west) && getName().equals("lion")) {
            wildAnimalView.setImage(new Image(new FileInputStream(LION + "west.png")));
            wildAnimalView.setScaleX(1);
            wildAnimalAnimation = new SpriteAnimation(wildAnimalView, duration, 24, 3,
                    0, 0, 138, 92);
            wildAnimalAnimation.setCycleCount(5);
            wildAnimalAnimation.play();
        }
    }

    public void wildAnimalMoving(Map map, Scene scene, Group mapGroup, boolean isEntered, double farmTime)
            throws FileNotFoundException {
        Duration duration;
        wildAnimalView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                map.cageWildAnimal(getX(), getY());
            }
        });

        wildAnimalView.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                scene.setCursor(Cursor.HAND);
            }
        });

        wildAnimalView.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                scene.setCursor(Cursor.DEFAULT);
            }
        });

        wildAnimalView.relocate(250 + (int) getX() * 9, 250 + (int) getY() * 6);

        if (isEntered) {
            duration = Duration.millis(10);
        }
        else
            duration = Duration.millis(1000);
        if (removeTheLiveStockAnimation(mapGroup))
            suitableSpriteAnimationForWild(mapGroup, scene, map, farmTime, duration);

    }

    public int getTimeLeftFromLastClick() {
        return timeLeftFromLastClick;
    }

    public void setTimeLeftFromLastClick(int timeLeftFromLastClick) {
        this.timeLeftFromLastClick = timeLeftFromLastClick;
    }
}
