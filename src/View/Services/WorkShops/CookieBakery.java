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

public class CookieBakery {
    private static final String serviceFiles =
            "C:\\Users\\Home\\Desktop\\farmFrenzySaveFiles\\farmFrenzyPlacesAndOthers\\Service\\";
    private static Image cookieBakeryImageL1;
    private static Image cookieBakeryImageL2;
    private static Image cookieBakeryImageL3;
    private static Image cookieBakeryImageL4;
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
            cookieBakeryImageL1 = new Image(new FileInputStream(serviceFiles + "cookieBakery\\01.png"));
            cookieBakeryImageL2 = new Image(new FileInputStream(serviceFiles + "cookieBakery\\02.png"));
            cookieBakeryImageL3 = new Image(new FileInputStream(serviceFiles + "cookieBakery\\03.png"));
            cookieBakeryImageL4 = new Image(new FileInputStream(serviceFiles + "cookieBakery\\04.png"));
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        }
    }
    private transient static ImageView cookieBakeryView = new ImageView();
    private boolean isUpgraded = false;
    private Duration duration;
    private transient static  SpriteAnimation cookieAnimation;
    private static int previousLevel = 0;

    public static void setCookieBakeryView(Group mapGroup, Map map) throws FileNotFoundException {
        cookieBakeryView.relocate(695, 151);
        int level = map.getWorkshops().get(1).getLevel();
        if (previousLevel != level) {
            if (!mapGroup.getChildren().contains(cookieBakeryView))
                mapGroup.getChildren().add(cookieBakeryView);
        if (!(cookieBakeryView.getImage() == cookieBakeryImageL1) && level == 1){
                cookieBakeryView.setImage(cookieBakeryImageL1);
                new  SpriteAnimation(cookieBakeryView, Duration.millis(1), 16, 4,
                        0, 0, 134, 142).play();
            }
            if (!(cookieBakeryView.getImage() == cookieBakeryImageL2) && level == 2){
                cookieBakeryView.setImage(cookieBakeryImageL2);
                new SpriteAnimation(cookieBakeryView, Duration.millis(1), 16, 4,
                        0, 0,158, 150).play();
            }
            if (!(cookieBakeryView.getImage() == cookieBakeryImageL3) && level == 3){
                cookieBakeryView.setImage(cookieBakeryImageL3);
                new SpriteAnimation(cookieBakeryView, Duration.millis(1), 16, 4, 0, 0,
                        158, 166).play();
            }
            if (!(cookieBakeryView.getImage() == cookieBakeryImageL4) && level == 4){
                cookieBakeryView.setImage(cookieBakeryImageL4);
                new SpriteAnimation(cookieBakeryView, Duration.millis(1), 16, 4, 0, 0,
                        166, 170).play();
            }
            previousLevel = level;
        }
    }
    public static void showCookieBakeryInWorking(Map map) {
        cookieBakeryView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (!map.getWorkshops().get(1).isInWorking()) {
                    map.startWorkshop("CookieBakery");
                    if (map.getWorkshops().get(1).getLevel() == 1)
                        cookieAnimation = new SpriteAnimation(cookieBakeryView, Duration.millis(1000), 16, 4,
                                0, 0, 134, 142);
                    if (map.getWorkshops().get(1).getLevel() == 2)
                        cookieAnimation = new SpriteAnimation(cookieBakeryView, Duration.millis(1000), 16, 4,
                                0, 0, 158, 150);
                    if (map.getWorkshops().get(1).getLevel() == 3)
                        cookieAnimation = new SpriteAnimation(cookieBakeryView, Duration.millis(1000), 16, 4,
                                0, 0, 158, 166);
                    if (map.getWorkshops().get(1).getLevel() == 4)
                        cookieAnimation = new SpriteAnimation(cookieBakeryView, Duration.millis(1000), 16, 4,
                                0, 0, 166, 170);
                    cookieAnimation.setCycleCount((int)map.getWorkshops().get(1).getTimeLastingForWorking());
                    cookieAnimation.play();
                }
            }

            ;
        });

    }

    public static void cookieBakeryInfo(Group group, Map map) throws FileNotFoundException {
        ImageView info = new ImageView(helpBoxImage2);
        WorkShop cookieBakery = map.getWorkshops().get(1);
        info.setScaleY(0.7);
        info.setScaleX(0.7);
        info.relocate(770, 74);
        group.getChildren().add(info);
        Label input = new Label();
        input.setStyle("-fx-text-fill: #fae00e ;-fx-font-size: 18; -fx-font-family: 'A Spirit Of Doha Black'");
        Label level = new Label();
        level.setStyle("-fx-text-fill: #fae00e ;-fx-font-size: 18; -fx-font-family: 'A Spirit Of Doha Black'");
        Label output = new Label();
        output.setStyle("-fx-text-fill: #fae00e ;-fx-font-size: 18; -fx-font-family: 'A Spirit Of Doha Black'");
        Label checking = new Label();
        checking.setStyle("-fx-text-fill: #fae00e ;-fx-font-size: 18; -fx-font-family: 'A Spirit Of Doha Black'");
        input.relocate(832, 94);
        output.relocate(930, 94);
        level.relocate(832, 134);
        checking.relocate(925, 134);
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
                for (String in: cookieBakery.getNameOfInputProducts())
                    if(cookieBakery.getNameOfInputProducts().indexOf(in) != 0)
                        inputs[0] = inputs[0].concat(" ," + in);
                    else
                        inputs[0] = inputs[0].concat(cookieBakery.getMaxNumberOfProducts() + " " + in);
                input.setText("in: " + inputs[0]);
                output.setText("out: " + cookieBakery.getMaxNumberOfProducts() + " cookie");
                level.setText("level: " + cookieBakery.getLevel());
                checking.setText("canWork: " +
                        (!cookieBakery.isInWorking()));
            }
        };
        timer.start();
        Brightness.changeBrightNess5(input, checking, info, level, output, cookieBakeryView);
    }

}
