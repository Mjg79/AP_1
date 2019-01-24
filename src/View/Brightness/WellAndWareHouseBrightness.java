package View.Brightness;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;

public class WellAndWareHouseBrightness {
    public static void changeBrightNess(Node node1, Node node2, Node node3, Node node4, ImageView imageView) {
        imageView.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                KeyValue infoOpacity = new KeyValue(node1.opacityProperty(), 1);
                KeyValue currentOpacity = new KeyValue(node2.opacityProperty(), 1);
                KeyValue volumeOpacity = new KeyValue(node3.opacityProperty(), 1);
                KeyValue levelOpacity = new KeyValue(node4.opacityProperty(), 1);
                KeyFrame showInfo = new KeyFrame(Duration.millis(1000), infoOpacity, currentOpacity,
                        volumeOpacity, levelOpacity);
                Timeline timeline = new Timeline(showInfo);
                timeline.getKeyFrames().add(showInfo);
                timeline.setAutoReverse(true);
                timeline.setCycleCount(1);
                timeline.play();

            }
        });

        imageView.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                KeyValue infoOpacity = new KeyValue(node1.opacityProperty(), 0);
                KeyValue currentOpacity = new KeyValue(node2.opacityProperty(), 0);
                KeyValue volumeOpacity = new KeyValue(node3.opacityProperty(), 0);
                KeyValue levelOpacity = new KeyValue(node4.opacityProperty(), 0);
                KeyFrame showInfo = new KeyFrame(Duration.millis(1000), infoOpacity, currentOpacity,
                        volumeOpacity, levelOpacity);
                Timeline timeline = new Timeline(showInfo);
                timeline.getKeyFrames().add(showInfo);
                timeline.setAutoReverse(true);
                timeline.setCycleCount(1);
                timeline.play();

            }
        });
    }
}
