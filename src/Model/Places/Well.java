package Model.Places;

import Model.ElementAndBoxAndDirection.Element;
import Model.MapAndCell.Map;
import View.Animations.SpriteAnimation;
import View.Brightness.Brightness;
import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Well extends Element {
    private int current = 0;
    private boolean isInCharging = false;
    private double firstTimeForCharge;
    private double lastTimeForCharge;
    private static double timeLastingForCharging = 7;
    private boolean haveOpportunityForExploitingFromWell = true;// make sure that we can use from well

    {
        current = 0;
        volume = 5;
        level = 1;
        name = "well";
        price = 19;
        moneyForUpgrading = 250;
    }

    @Override
    public void move(int finalX, int finalY) {

    }

    public boolean isHaveOpportunityForExploitingFromWell() {
        return haveOpportunityForExploitingFromWell;
    }

    @Override
    public boolean upgrade() {
        if (level < 3) {
            level++;
            timeLastingForCharging -= 1;
            volume += 2;
            price -= 2;
            moneyForUpgrading += 50;
            return true;
        }
        return false;
    }

    public double getFirstTimeForCharge() {
        return firstTimeForCharge;
    }

    public double getLastTimeForCharge() {
        return lastTimeForCharge;
    }

    public static double getTimeLastingForCharging() {
        return timeLastingForCharging;
    }

    public boolean isInCharging() {
        return isInCharging;
    }

    public void charge(double time) {
        if (current == 0 && !isInCharging) {
            isInCharging = true;
            firstTimeForCharge = time;
            lastTimeForCharge = time + timeLastingForCharging;
        }
    }

    public void charge() {
        current = volume;
    }

    public boolean canDisChargeWell() {
        if (current != 0 && haveOpportunityForExploitingFromWell && !isInCharging) {
            current--;
            return true;
        } else
            haveOpportunityForExploitingFromWell = false;
        return false;
    }

    public void checkWell(double time) {
        if (time > lastTimeForCharge && isInCharging) {
            current = volume;
            haveOpportunityForExploitingFromWell = true;
            isInCharging = false;
        }
    }

    public int getCurrent() {
        return current;
    }

    //////////////////GRAPHICS AND ANIMATOIN SECTION////////////////////////////////
    private static final String serviceFiles =
            "C:\\Users\\Home\\Desktop\\farmFrenzySaveFiles\\farmFrenzyPlacesAndOthers\\Service\\";
    private transient ImageView wellView = new ImageView();
    private boolean isUpgraded = false;
    private Duration duration;
    private transient SpriteAnimation wellAnimation;
    private int previousLevel = 0;

    public void setWellView(Group mapGroup) throws FileNotFoundException {
        wellView.relocate(414, 74);
        if (!mapGroup.getChildren().contains(wellView)) {
            mapGroup.getChildren().add(wellView);
            previousLevel = 0;
        }
        if (previousLevel != level) {
            wellView.setImage(new Image(new FileInputStream(serviceFiles + "Well\\0" + level + ".png")));
            if (level == 1)
                wellAnimation = new SpriteAnimation(wellView, Duration.millis(1), 16, 4,
                        0, 0, 150, 136);
            if (level == 2)
                wellAnimation = new SpriteAnimation(wellView, Duration.millis(1), 16, 4,
                        0, 0, 148, 150);
            if (level == 3)
                wellAnimation = new SpriteAnimation(wellView, Duration.millis(1), 16, 4, 0, 0,
                        144, 158);
            wellAnimation.setCycleCount(1);
            wellAnimation.play();
        }
    }
    public void showWellInWorking(Map map) {
        wellView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (!isInCharging && getCurrent() == 0) {
                    map.chargeWell();
                    if (level == 1) {
                        wellAnimation = new SpriteAnimation(wellView, Duration.millis(1000), 16, 4,
                                0, 0, 150, 136);
                    }
                    if (level == 2) {
                        wellAnimation = new SpriteAnimation(wellView, Duration.millis(1000), 16, 4,
                                0, 0, 148, 150);
                    }
                    if (level == 3) {
                        wellAnimation = new SpriteAnimation(wellView, Duration.millis(1000), 16, 4, 0, 0,
                                144, 158);
                    }
                    wellAnimation.setCycleCount((int)timeLastingForCharging);
                    wellAnimation.play();
                    System.out.println("current: " + current + " ,volume: " + volume);
                }
            }

            ;
        });

    }

    public void wellInfo(Group group) throws FileNotFoundException {
        ImageView info = new ImageView(new Image(new FileInputStream("C:\\Users\\Home\\Desktop" +
                "\\farmFrenzySaveFiles\\helpBox\\helpBox1.png")));
        info.setScaleY(0.5);
        info.setScaleX(0.5);
        info.relocate(450, 115);
        group.getChildren().add(info);
        Label current = new Label();
        current.setStyle("-fx-text-fill: #fae00e ;-fx-font-size: 16; -fx-font-family: 'A Spirit Of Doha Black'");
        Label level = new Label();
        level.setStyle("-fx-text-fill: #fae00e ;-fx-font-size: 16; -fx-font-family: 'A Spirit Of Doha Black'");
        Label volume = new Label();
        volume.setStyle("-fx-text-fill: #fae00e ;-fx-font-size: 16; -fx-font-family: 'A Spirit Of Doha Black'");
        current.relocate(533, 167);
        volume.relocate(600, 167);
        level.relocate(571, 187);
        level.setOpacity(0);
        current.setOpacity(0);
        volume.setOpacity(0);
        info.setOpacity(0);
        group.getChildren().addAll(level, volume, current);
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                current.setText("current: " + getCurrent());
                volume.setText("volume: " + getVolume());
                level.setText("level: " + getLevel());
            }
        };
        timer.start();
        Brightness.changeBrightNess4(current, volume, info, level, wellView);
    }
}



