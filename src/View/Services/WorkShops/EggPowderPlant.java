package View.Services.WorkShops;

import View.Animations.SpriteAnimation;
import javafx.animation.Animation;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class EggPowderPlant {
    private static final String serviceFiles =
            "C:\\Users\\Home\\Desktop\\farmFrenzySaveFiles\\farmFrenzyPlacesAndOthers\\Service\\";

    private static int level = 1;
    private static ImageView eggPlantView;
    private boolean isUpgraded = false;
    static {
        try {
            eggPlantView = new ImageView(new Image(
                    new FileInputStream(serviceFiles + "eggPowderPlant\\0" + level + ".png")));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }


    public void upgradeEggPowderPlantView(int level, Group map) throws FileNotFoundException {
        if (map.getChildren().contains(eggPlantView))
            map.getChildren().remove(eggPlantView);
        Image well = new Image(new FileInputStream(serviceFiles + "eggPowderPlant\\0" + level + ".png"));
        eggPlantView.setImage(well);
    }
    public static Animation eggPowderPlantAnimation(boolean check , Group map, int level) throws FileNotFoundException {
        if (!map.getChildren().contains(eggPlantView)) {
            eggPlantView.relocate(119, 186);
            map.getChildren().add(eggPlantView);
        }
        int duration;
        if (check)
            duration = 1000;
        else
            duration = 1;
        if (level == 1)
            return new SpriteAnimation(eggPlantView, Duration.millis(duration), 16, 4, 0, 0,
                    128, 114);
        if (level == 2)
            return new SpriteAnimation(eggPlantView, Duration.millis(duration), 16, 4, 0, 0,
                    144, 132);
        if (level == 3)
            return new SpriteAnimation(eggPlantView, Duration.millis(duration), 16, 4, 0, 0,
                    164, 150);
        if (level == 4)
            return new SpriteAnimation(eggPlantView, Duration.millis(duration), 16, 4, 0, 0,
                    186, 158);
        return new SpriteAnimation(eggPlantView, Duration.millis(duration), 16, 4, 0, 0,
                171, 172);
    }

}
