package View.MultiPlayerScene;

import Controller.ServerController;
import View.Map.MapView;
import javafx.animation.AnimationTimer;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.awt.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class ServerShowList {
    private Group showGroup = new Group();
    private Scene showScene = new Scene(showGroup, 800, 600);
    private MapView mapView;
    private Stage stage;
    private  ServerController serverController;
    private transient ArrayList <Label> labels;
    private transient AnimationTimer timer;

    public ServerShowList(Stage stage, ServerController controller, MapView mapView) {
        labels = new ArrayList<>();
        this.stage = stage;
        this.serverController = controller;
        this.mapView = mapView;
    }

    private void initializeJoinScene() throws FileNotFoundException {
        ImageView backGround = new ImageView(new Image(new FileInputStream("C:\\Users\\Home\\Desktop\\" +
                "farmFrenzySaveFiles\\farmFrenzyScenesDesign\\back.png")));
        showGroup.getChildren().add(backGround);
        Rectangle items = new Rectangle(120, 120, 500, 400);
        items.setStyle("-fx-stroke: #47211e; -fx-stroke-width: 5;  -fx-background-radius: 10;" +
                "  -fx-border-width: 5; -fx-fill: #e5c06f;" +
                " -fx-border-radius: 5px");
        showGroup.getChildren().add(items);

        Image image = new Image(new FileInputStream(
                "C:\\Users\\Home\\Desktop\\farmFrenzySaveFiles\\Logo.png"));
        ImageView imageView = new ImageView(image);
        imageView.relocate(180, 0);
        imageView.setFitWidth(441);
        imageView.setFitHeight(100);
        showGroup.getChildren().add(imageView);
    }

    public void designList() throws IOException, ClassNotFoundException {
        initializeJoinScene();

        joinPlayers();
        final int[] i = {0};
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                System.out.println("the i is : " + i[0]);
                i[0]++;
                if (i[0] == 500) {
                    System.out.println("finishadding");
                    serverController.setStop(false);
                }
            }
        };
        timer.stop();
    }

    public void joinPlayers() throws IOException, ClassNotFoundException {
        serverController.joinToServer();
    }


    public Scene getShowScene() {
        return showScene;
    }

    public Stage getStage() {
        return stage;
    }
}
