package View.Animations;

import View.View;
import javafx.animation.Animation;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class ServiceAnimation {
    private static final String serviceFiles =
            "C:\\Users\\Home\\Desktop\\farmFrenzySaveFiles\\farmFrenzyPlacesAndOthers\\Service\\";


//
//    public Animation cakeBakery(boolean check ,Group map, int level, int x, int y)
//            throws FileNotFoundException {
//        Image workShop = new Image(new FileInputStream(serviceFiles + "cakeBakery\\0" + level + ".png"));
//        ImageView workShopView = new ImageView(workShop);
//        workShopView.setX(x);
//        workShopView.setY(y);
//        map.getChildren().add(workShopView);
//        int duration = 0;
//        if (check)
//            duration = 1000;
//        else
//            duration = 1;
//        if (level == 1)
//            return new SpriteAnimation(workShopView, Duration.millis(duration), 16, 4, 0, 0,
//                    184, 172);
//        if (level == 2)
//            return new SpriteAnimation(workShopView, Duration.millis(duration), 16, 4, 0, 0,
//                    158, 148);
//        if (level == 3)
//            return new SpriteAnimation(workShopView, Duration.millis(duration), 16, 4, 0, 0,
//                    170, 168);
//        if (level == 4)
//            return new SpriteAnimation(workShopView, Duration.millis(duration), 16, 4, 0, 0,
//                    176, 170);
//        return new SpriteAnimation(workShopView, Duration.millis(duration), 16, 4, 0, 0,
//                180, 170);
//    }
//
//    public void playAnimation(SpriteAnimation spriteAnimation) {
//        AnimationTimer timer = new AnimationTimer() {
//            @Override
//            public void handle(long now) {
//                spriteAnimation.play();
//            }
//        };
//    }

}