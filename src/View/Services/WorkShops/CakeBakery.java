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

    private static Image cakeBckeryImageL1;
    private static Image cakeBckeryImageL2;
    private static Image cakeBckeryImageL3;
    private static Image cakeBckeryImageL4;
    private static Image helpBoxImage1;
    private static Image helpBoxImage2;
    private static Image helpBoxImage13;
    private static Image helpBoxImage24;

    static {
        try {
            helpBoxImage1 = new Image(new FileInputStream("C:\\Users\\Home\\Desktop\\farmFrenzySaveFiles\\helpBox\\helpBox1.png"));
            helpBoxImage2 = new Image(new FileInputStream("C:\\Users\\Home\\Desktop\\farmFrenzySaveFiles\\helpBox\\helpBox2.png"));
            helpBoxImage13 = new Image(new FileInputStream("C:\\Users\\Home\\Desktop\\farmFrenzySaveFiles\\helpBox\\helpBox13.png"));
            helpBoxImage24 = new Image(new FileInputStream("C:\\Users\\Home\\Desktop\\farmFrenzySaveFiles\\helpBox\\helpBox24.png"));
            cakeBckeryImageL1 = new Image(new FileInputStream(serviceFiles + "cakeBakery\\01.png"));
            cakeBckeryImageL2 = new Image(new FileInputStream(serviceFiles + "cakeBakery\\02.png"));
            cakeBckeryImageL3 = new Image(new FileInputStream(serviceFiles + "cakeBakery\\03.png"));
            cakeBckeryImageL4 = new Image(new FileInputStream(serviceFiles + "cakeBakery\\04.png"));
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        }
    }

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

            if (!(cakeBakeryView.getImage() == cakeBckeryImageL1) && level == 1) {
                cakeBakeryView.setImage(cakeBckeryImageL1);
                cakeAnimation = new SpriteAnimation(cakeBakeryView, Duration.millis(1), 16, 4,
                        0, 0, 184, 172);
            }
            else if (!(cakeBakeryView.getImage() == cakeBckeryImageL2) && level == 2){
                cakeBakeryView.setImage(cakeBckeryImageL2);
                cakeAnimation = new SpriteAnimation(cakeBakeryView, Duration.millis(1), 16, 4,
                        0, 0,158, 148);
            }
            else if (!(cakeBakeryView.getImage() == cakeBckeryImageL3) && level == 3){
                cakeBakeryView.setImage(cakeBckeryImageL3);
                cakeAnimation = new SpriteAnimation(cakeBakeryView, Duration.millis(1), 16, 4, 0, 0,
                        170, 168);
            }
            else if (!(cakeBakeryView.getImage() == cakeBckeryImageL4) && level == 4){
                cakeBakeryView.setImage(cakeBckeryImageL4);
                cakeAnimation =  new SpriteAnimation(cakeBakeryView, Duration.millis(1), 16, 4, 0, 0,
                        176, 170);
            }
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
        ImageView info = new ImageView(helpBoxImage13);
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


