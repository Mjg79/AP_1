package Model.Places;

import Model.ElementAndBoxAndDirection.Element;
import View.Animations.SpriteAnimation;
import javafx.animation.Animation;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Well extends Element {
    private int current = 0;
    private boolean isInCharging = false;
    private double firstTimeForCharge;
    private double lastTimeForCharge;
    private static double timeLastingForCharging = 5;
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

    public boolean canDisChargeWell () {
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
    private static int level = 1;
    private static ImageView wellView;
    private boolean isUpgraded = false;
    static {
        try {
            wellView = new ImageView(new Image(
                    new FileInputStream(serviceFiles + "Well\\0" + level + ".png")));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }


    public void upgradeWellView(int level, Group map) throws FileNotFoundException {
        if (map.getChildren().contains(wellView))
            map.getChildren().remove(wellView);
        Image well = new Image(new FileInputStream(serviceFiles + "Well\\0" + level + ".png"));
        wellView.setImage(well);
    }



    public  Animation wellAnimation(boolean check, int level, Group map) throws FileNotFoundException {
        if (!map.getChildren().contains(wellView)) {
            wellView.relocate(414, 74);
            map.getChildren().add(wellView);
        }
        if (check)
            return new SpriteAnimation(wellView, Duration.millis(900), 16, 4,
                    0, 0, 150, 136);
        else
            return new SpriteAnimation(wellView, Duration.millis(10), 16, 4, 0, 0,
                    150, 136);
    }



}

