package View.Services.WorkShops;

import Model.MapAndCell.Map;
import Model.Places.WorkShop;
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

public class EggPowderPlant {
    private static final String serviceFiles =
            "C:\\Users\\Home\\Desktop\\farmFrenzySaveFiles\\farmFrenzyPlacesAndOthers\\Service\\";
    private static ImageView eggPlantView = new ImageView();
    private boolean isUpgraded = false;
    private Duration duration;
    private static  SpriteAnimation eggAnimation;
    private static int previousLevel = 0;

    public static void setEggPlantView(Group mapGroup, Map map) throws FileNotFoundException {
        eggPlantView.relocate(144, 194);
        int level = map.getWorkshops().get(0).getLevel();
        if (previousLevel != level) {
            if (!mapGroup.getChildren().contains(eggPlantView))
                mapGroup.getChildren().add(eggPlantView);
            eggPlantView.setImage(new Image(new FileInputStream(serviceFiles + "eggPowderPlant\\0"
                        + level + ".png")));
            if (level == 1)
                eggAnimation = new  SpriteAnimation(eggPlantView, Duration.millis(1), 16, 4,
                        0, 0, 128, 114);
            if (level == 2)
                eggAnimation = new SpriteAnimation(eggPlantView, Duration.millis(1), 16, 4,
                        0, 0,144, 132);
            if (level == 3)
                eggAnimation = new SpriteAnimation(eggPlantView, Duration.millis(1), 16, 4, 0, 0,
                        164, 150);
            if (level == 4)
               eggAnimation =  new SpriteAnimation(eggPlantView, Duration.millis(1), 16, 4, 0, 0,
                        186, 158);
            previousLevel = level;
            eggAnimation.setCycleCount(1);
            eggAnimation.play();
            System.out.println("workShop level: " + level);
        }
    }
    public static void showEggPlantInWorking(Map map) {
        eggPlantView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (!map.getWorkshops().get(0).isInWorking()) {
                    map.startWorkshop("EggPowderedPlant");
                    System.out.println("workShop level in work: " + map.getWorkshops().get(0).getLevel());
                    if (map.getWorkshops().get(0).getLevel() == 1)
                        eggAnimation = new SpriteAnimation(eggPlantView, Duration.millis(1000), 16, 4,
                                0, 0, 128, 114);
                    if (map.getWorkshops().get(0).getLevel() == 2)
                        eggAnimation = new SpriteAnimation(eggPlantView, Duration.millis(1000), 16, 4,
                                0, 0, 144, 132);
                    if (map.getWorkshops().get(0).getLevel() == 3)
                        eggAnimation = new SpriteAnimation(eggPlantView, Duration.millis(1000), 16, 4,
                                0, 0, 164, 150);
                    if (map.getWorkshops().get(0).getLevel() == 4)
                        eggAnimation = new SpriteAnimation(eggPlantView, Duration.millis(1000), 16, 4,
                                0, 0, 186, 158);
                    eggAnimation.setCycleCount((int)map.getWorkshops().get(0).getTimeLastingForWorking());
                    eggAnimation.play();
                }
            }

            ;
        });

    }

    public static void eggPlantInfo(Group group, Map map) throws FileNotFoundException {
        ImageView info = new ImageView(new Image(new FileInputStream("C:\\Users\\Home\\Desktop" +
                "\\farmFrenzySaveFiles\\helpBox\\helpBox13.png")));
        WorkShop eggPlant = map.getWorkshops().get(0);
        info.setScaleY(0.7);
        info.setScaleX(0.7);
        info.relocate(-30, 124);
        group.getChildren().add(info);
        Label input = new Label();
        input.setStyle("-fx-text-fill: #fae00e ;-fx-font-size: 18; -fx-font-family: 'A Spirit Of Doha Black'");
        Label level = new Label();
        level.setStyle("-fx-text-fill: #fae00e ;-fx-font-size: 18; -fx-font-family: 'A Spirit Of Doha Black'");
        Label output = new Label();
        output.setStyle("-fx-text-fill: #fae00e ;-fx-font-size: 18; -fx-font-family: 'A Spirit Of Doha Black'");
        Label checking = new Label();
        checking.setStyle("-fx-text-fill: #fae00e ;-fx-font-size: 18; -fx-font-family: 'A Spirit Of Doha Black'");
        input.relocate(16, 144);
        output.relocate(76, 144);
        level.relocate(16, 184);
        checking.relocate(76, 184);
        level.setOpacity(0);
        input.setOpacity(0);
        output.setOpacity(0);
        info.setOpacity(0);
        checking.setOpacity(0);
        group.getChildren().addAll(level, output, input, checking);
        final String[] inputs = {""};
        final String[] outputs = {""};

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                outputs[0] = "";
                inputs[0] = "";
                for (String out: map.getWorkshops().get(1).getNameOfInputProducts())
                    if(map.getWorkshops().get(1).getNameOfInputProducts().indexOf(out) != 0)
                        outputs[0] = outputs[0].concat(" ," + out);
                    else
                        outputs[0] = outputs[0].concat(eggPlant.getMaxNumberOfProducts() + " " + out);

                for (String in: eggPlant.getNameOfInputProducts())
                    if(eggPlant.getNameOfInputProducts().indexOf(in) != 0)
                        inputs[0] = inputs[0].concat(" ," + in);
                    else
                        inputs[0] = inputs[0].concat(eggPlant.getMaxNumberOfProducts() + " " + in);
                input.setText("in: " + inputs[0]);
                output.setText("out: " + outputs[0]);
                level.setText("level: " + eggPlant.getLevel());
                checking.setText("canWork: " +
                        (!eggPlant.isInWorking()));
            }
        };
        timer.start();
        Brightness.changeBrightNess5(input, checking, info, level, output,  eggPlantView);
    }

}
