package View.Services.WorkShops;

import View.Animations.SpriteAnimation;
import javafx.animation.Animation;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class CakeBakery {
    private static final String serviceFiles =
            "C:\\Users\\Home\\Desktop\\farmFrenzySaveFiles\\farmFrenzyPlacesAndOthers\\Service\\";

    private static int level = 1;
    private static ImageView cakeBakeryView;
    private boolean isUpgraded = false;
    static {
        try {
            cakeBakeryView = new ImageView(new Image(
                    new FileInputStream(serviceFiles + "cakeBakery\\0" + level + ".png")));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }


    public void upgradeEggPowderPlantView(int level, Group map) throws FileNotFoundException {
        if (map.getChildren().contains(cakeBakeryView))
            map.getChildren().remove(cakeBakeryView);
        Image well = new Image(new FileInputStream(serviceFiles + "cakeBakery\\0" + level + ".png"));
        cakeBakeryView.setImage(well);
    }
    public static Animation cakeBakeryAnimation(boolean check , Group map, int level) throws FileNotFoundException {
        if (!map.getChildren().contains(cakeBakeryView)) {
            cakeBakeryView.relocate(84, 430);
            map.getChildren().add(cakeBakeryView);
        }
        int duration;
        if (check)
            duration = 1000;
        else
            duration = 1;
        if (level == 1)
            return new SpriteAnimation(cakeBakeryView, Duration.millis(duration), 16, 4, 0, 0,
                    184, 172);
        if (level == 2)
            return new SpriteAnimation(cakeBakeryView, Duration.millis(duration), 16, 4, 0, 0,
                    158, 148);
        if (level == 3)
            return new SpriteAnimation(cakeBakeryView, Duration.millis(duration), 16, 4, 0, 0,
                    170, 168);
        if (level == 4)
            return new SpriteAnimation(cakeBakeryView, Duration.millis(duration), 16, 4, 0, 0,
                    176, 170);
        return new SpriteAnimation(cakeBakeryView, Duration.millis(duration), 16, 4, 0, 0,
                180, 170);
    }

}
