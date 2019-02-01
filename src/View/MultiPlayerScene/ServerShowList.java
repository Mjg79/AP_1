package View.MultiPlayerScene;

import Controller.Profile;
import Controller.ServerController;
import View.Map.MapView;
import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

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
    private transient int previousNum = 0;
    private transient Scene mapScene;
    private transient Group map;

    public ServerShowList(Stage stage, ServerController controller, MapView mapView, Scene scene, Group map) {
        labels = new ArrayList<>();
        this.stage = stage;
        this.serverController = controller;
        this.mapView = mapView;
        this.map = map;
        this.mapScene = scene;
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

        Label showDatas = new Label("Name\t\t\t\tuserName\t\tnameOfGamePlayed\t\tport");
        showGroup.getChildren().add(showDatas);
        showDatas.setStyle("-fx-font-size: 20; -fx-opacity: 1; -fx-font-family:'A Spirit Of Doha Black';" +
                " -fx-font-weight: BOLD; -fx-text-fill: #cf5e2c");
        showDatas.relocate(140, 120);

        showPlayers();
        startButton();

    }

    private void joinPlayers() throws IOException, ClassNotFoundException {
        serverController.joinToServer();
    }

    private void showPlayers() {
        Profile profile = serverController.getServerProfile();
        Label label = new Label(profile.getName() + "\t\t\t\t" + profile.getUserName() + "\t\t\t\t" +
                profile.getNumOfGames() + "\t\t\t\t" + "8050");
        labels.add(label);
        label.setStyle("-fx-font-size: 20; -fx-opacity: 1; -fx-font-family:'A Spirit Of Doha Black';" +
                " -fx-font-weight: BOLD; -fx-text-fill: #6d3617");
        showGroup.getChildren().add(label);
        label.relocate(140, 150);
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                if (previousNum != serverController.getClients().size()) {
                    Profile profile = serverController.getProfiles().get(serverController.getProfiles().size() - 1);
                    Label label = new Label(profile.getName() + "\t\t\t\t" + profile.getUserName() +
                            "\t\t\t\t" + profile.getNumOfGames()+ "\t\t\t\t"
                            + serverController.getClients().get(profile).getPort());
                    showGroup.getChildren().add(label);
                    label.relocate(140, 30 * serverController.getClients().size() + 150);
                    labels.add(label);
                    label.setStyle("-fx-font-size: 20; -fx-opacity: 1; -fx-font-family:'A Spirit Of Doha Black';" +
                            " -fx-font-weight: BOLD; -fx-text-fill: #6d3617");
                    previousNum = serverController.getClients().size();
                }
            }
        };
        timer.start();
    }


    public Scene getShowScene() {
        return showScene;
    }

    public Stage getStage() {
        return stage;
    }


    private void startButton() {
        Button start  = new Button("start");
        start.setScaleX(1.5);
        start.setScaleY(1.5);
        start.relocate(550, 480);
        start.setStyle("-fx-background-color: #b5e627; -fx-background-radius: 10px" +
                "; -fx-font-family: 'Bodoni MT Black' ; -fx-font-size: 12;-fx-border-radius: 5px;" +
                " -fx-border-color: #03ea39; -fx-border-width: 3px;");
        showGroup.getChildren().add(start);


        start.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                try {
                    serverController.startGame();
                    mapView.gameMap(map, mapScene, serverController.getMap());
                    stage.setScene(mapScene);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

}
