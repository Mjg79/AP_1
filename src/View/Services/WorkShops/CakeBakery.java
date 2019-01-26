package View.Services.WorkShops;

import View.Animations.SpriteAnimation;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

import java.io.FileInputStream;
import java.io.FileNotFoundException;


import Model.MapAndCell.Map;
import Model.Places.WorkShop;
import View.Brightness.Brightness;
import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

public class CakeBakery {
    private static final String serviceFiles =
            "C:\\Users\\Home\\Desktop\\farmFrenzySaveFiles\\farmFrenzyPlacesAndOthers\\Service\\";
    private transient static ImageView cakeBakeryView = new ImageView();
    private boolean isUpgraded = false;
    private Duration duration;
    private transient static  SpriteAnimation cakeAnimation;
    private static int previousLevel = 0;

    public static void setCakeBakeryView(Group mapGroup, Map map) throws FileNotFoundException {
        cakeBakeryView.relocate(84, 430);
        int level = map.getWorkshops().get(2).getLevel();
        if (previousLevel != level) {
            if (!mapGroup.getChildren().contains(cakeBakeryView))
                mapGroup.getChildren().add(cakeBakeryView);
            cakeBakeryView.setImage(new Image(new FileInputStream(serviceFiles + "cakeBakery\\0"
                    + level + ".png")));

            if (level == 1)
                cakeAnimation = new  SpriteAnimation(cakeBakeryView, Duration.millis(1), 16, 4,
                        0, 0, 184, 172);
            if (level == 2)
                cakeAnimation = new SpriteAnimation(cakeBakeryView, Duration.millis(1), 16, 4,
                        0, 0,158, 148);
            if (level == 3)
                cakeAnimation = new SpriteAnimation(cakeBakeryView, Duration.millis(1), 16, 4, 0, 0,
                        170, 168);
            if (level == 4)
                cakeAnimation =  new SpriteAnimation(cakeBakeryView, Duration.millis(1), 16, 4, 0, 0,
                        176, 170);
            previousLevel = level;
            cakeAnimation.setCycleCount(1);
            cakeAnimation.play();
        }
    }
    public static void showCookieBakeryInWorking(Map map) {
        cakeBakeryView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (!map.getWorkshops().get(2).isInWorking()) {
                    map.startWorkshop("CakeBakery");

                    if (map.getWorkshops().get(2).getLevel() == 1)
                        cakeAnimation = new SpriteAnimation(cakeBakeryView, Duration.millis(1000), 16, 4,
                                0, 0, 184, 172);
                    if (map.getWorkshops().get(2).getLevel() == 2)
                        cakeAnimation = new SpriteAnimation(cakeBakeryView, Duration.millis(1000), 16, 4,
                                0, 0, 158, 148);
                    if (map.getWorkshops().get(2).getLevel() == 3)
                        cakeAnimation = new SpriteAnimation(cakeBakeryView, Duration.millis(1000), 16, 4,
                                0, 0, 170, 168);
                    if (map.getWorkshops().get(2).getLevel() == 4)
                        cakeAnimation = new SpriteAnimation(cakeBakeryView, Duration.millis(1000), 16, 4,
                                0, 0, 176, 170);
                    cakeAnimation.setCycleCount((int)map.getWorkshops().get(1).getTimeLastingForWorking());
                    cakeAnimation.play();
                }
            }

            ;
        });

    }

    public static void cakeBakeryInfo(Group group, Map map) throws FileNotFoundException {
        ImageView info = new ImageView(new Image(new FileInputStream("C:\\Users\\Home\\Desktop" +
                "\\farmFrenzySaveFiles\\helpBox\\helpBox13.png")));
        WorkShop cakeBakery = map.getWorkshops().get(2);
        info.setScaleY(0.7);
        info.setScaleX(0.7);
        info.relocate(-30, 380);
        group.getChildren().add(info);
        Label input = new Label();
        input.setStyle("-fx-text-fill: #fae00e ;-fx-font-size: 18; -fx-font-family: 'A Spirit Of Doha Black'");
        Label level = new Label();
        level.setStyle("-fx-text-fill: #fae00e ;-fx-font-size: 18; -fx-font-family: 'A Spirit Of Doha Black'");
        Label output = new Label();
        output.setStyle("-fx-text-fill: #fae00e ;-fx-font-size: 18; -fx-font-family: 'A Spirit Of Doha Black'");
        Label checking = new Label();
        checking.setStyle("-fx-text-fill: #fae00e ;-fx-font-size: 18; -fx-font-family: 'A Spirit Of Doha Black'");
        input.relocate(27, 404);
        output.relocate(127, 404);
        level.relocate(27, 430);
        checking.relocate(115, 430);
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
                for (String in: cakeBakery.getNameOfInputProducts())
                    if(cakeBakery.getNameOfInputProducts().indexOf(in) != 0)
                        inputs[0] = inputs[0].concat(" ," + in);
                    else
                        inputs[0] = inputs[0].concat(cakeBakery.getMaxNumberOfProducts() + " " + in);
                input.setText("in: " + inputs[0]);
                output.setText("out: " + cakeBakery.getMaxNumberOfProducts() + " cake");
                level.setText("level: " + cakeBakery.getLevel());
                checking.setText("canWork: " +
                        (!cakeBakery.isInWorking()));
            }
        };
        timer.start();
        Brightness.changeBrightNess5(input, checking, info, level, output, cakeBakeryView);
    }

}


