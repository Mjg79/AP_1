package View;

import javafx.animation.AnimationTimer;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.awt.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class MissionNeed {
    public static void backToMenu(Stage stage, Scene menu) throws FileNotFoundException {
        Group group = new Group();
        Label label = new Label();
        label.setText("mission completed");
        label.relocate(320, 270);
        label.setStyle("-fx-text-fill: #fef448; -fx-font-family: 'A Spirit Of Doha Black';" +
                "-fx-font-size: 50px; -fx-font-weight: BOLD");
        Scene scene = new Scene(group, 800, 550);
        stage.setScene(scene);
        ImageView imageView = new ImageView(new Image(
                new FileInputStream("C:\\Users\\Home\\Desktop\\farmFrenzySaveFiles\\" +
                        "farmFrenzyScenesDesign\\back.png")));
        group.getChildren().add(imageView);
        group.getChildren().add(label);

        final int[] time = {0};
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                System.out.println("time is: " + time[0]);
                time[0]++;
                if (time[0] == 50) {
                    stage.setScene(menu);
                    this.stop();
                }
            }
        };
        timer.start();
    }
}
