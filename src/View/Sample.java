package View;

import Model.MapAndCell.Map;
import View.Animations.SpriteAnimation;
import com.google.gson.Gson;
import javafx.animation.Animation;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Sample extends Application {


    @Override
    public void start(Stage primaryStage) throws Exception {
    Group group = new Group();
        Scene mapScene = new Scene(group, 700, 700);
        mapScene.setFill(Color.YELLOW);
        primaryStage.setScene(mapScene);
        primaryStage.show();
        Image image = new Image(new FileInputStream("C:\\Users\\Home\\Desktop\\farmFrenzySaveFiles\\Animals" +
                "\\GuineaFowl\\west.png"));
        ImageView imageView = new ImageView(image);
        group.getChildren().add(imageView);
        imageView.relocate(350, 350);
        final SpriteAnimation[] spriteAnimation = {new SpriteAnimation(imageView, Duration.millis(1000), 24,
                5, 0, 0, 80, 74)};
         spriteAnimation[0].setCycleCount(3);
         spriteAnimation[0].play();
        AnimationTimer animationTimer = new AnimationTimer() {
            int i = 0;
            @Override
            public void handle(long now) {
                spriteAnimation[0].setCycleCount(Animation.INDEFINITE);
                spriteAnimation[0].play();
                imageView.relocate((350 +  i) % 700, (350 +  i) % 700);
                if ((250 + i) % 700 == 200)
                    imageView.setScaleX(1);
                if ((350 + i) % 700 == 50) {
                    try {
                        imageView.setImage(new Image(new FileInputStream("C:\\Users\\Home\\Desktop\\farmFrenzySaveFiles\\Animals" +
                                "\\GuineaFowl\\west.png")));
                        spriteAnimation[0].stop();
                        spriteAnimation[0] = new SpriteAnimation(imageView, Duration.millis(1000), 24,
                                5, 0, 0, 80, 74);
                        imageView.setScaleX(-1);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                }
                i++;
            }
        };
        animationTimer.start();
    }


}
