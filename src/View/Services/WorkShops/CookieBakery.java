package View.Services.WorkShops;

import View.Animations.SpriteAnimation;
import javafx.animation.Animation;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class CookieBakery {
    private static final String serviceFiles =
            "C:\\Users\\Home\\Desktop\\farmFrenzySaveFiles\\farmFrenzyPlacesAndOthers\\Service\\";

    private static int level = 1;
    private static ImageView cookieBakeryView;
    private boolean isUpgraded = false;
    static {
        try {
            cookieBakeryView = new ImageView(new Image(
                    new FileInputStream(serviceFiles + "cookieBakery\\0" + level + ".png")));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }


    public void upgradeEggPowderPlantView(int level, Group map) throws FileNotFoundException {
        if (map.getChildren().contains(cookieBakeryView))
            map.getChildren().remove(cookieBakeryView);
        Image well = new Image(new FileInputStream(serviceFiles + "cookieBakery\\0" + level + ".png"));
        cookieBakeryView.setImage(well);
    }
    public static Animation cookieBakeryAnimation(boolean check , Group map, int level) throws FileNotFoundException {
        if (!map.getChildren().contains(cookieBakeryView)) {
            cookieBakeryView.relocate(695, 151);
            map.getChildren().add(cookieBakeryView);
        }
        int duration;
        if (check)
            duration = 1000;
        else
            duration = 1;
        if (level == 1)
            return new SpriteAnimation(cookieBakeryView, Duration.millis(duration), 16, 4, 0, 0,
                    134, 142);
        if (level == 2)
            return new SpriteAnimation(cookieBakeryView, Duration.millis(duration), 16, 4, 0, 0,
                    158, 150);
        if (level == 3)
            return new SpriteAnimation(cookieBakeryView, Duration.millis(duration), 16, 4, 0, 0,
                    158, 166);
        if (level == 4)
            return new SpriteAnimation(cookieBakeryView, Duration.millis(duration), 16, 4, 0, 0,
                    166, 170);
        return new SpriteAnimation(cookieBakeryView, Duration.millis(duration), 16, 4, 0, 0,
                186, 184);
    }


}
