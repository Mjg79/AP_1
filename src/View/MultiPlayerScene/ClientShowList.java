package View.MultiPlayerScene;

import Controller.ClientController;
import Controller.Profile;
import Controller.ServerController;
import View.Helicopter.HeliCopterView;
import View.Map.MapView;
import View.Map.WarehouseScene;
import javafx.animation.AnimationTimer;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;

public class ClientShowList {
    private Group showGroup = new Group();
    private Scene showScene = new Scene(showGroup, 800, 600);
    private MapView mapView;
    private Stage stage;
    private ClientController clientController;
    private transient ArrayList<Label> labels;
    private transient AnimationTimer timer;
    private transient int previousNum = 0;

    public ClientShowList(Stage stage, ClientController controller, MapView mapView) {
        labels = new ArrayList<>();
        this.stage = stage;
        this.clientController = controller;
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

    public void designClientList() throws IOException, ClassNotFoundException {
        initializeJoinScene();

        Label waiting = new Label("you added successfully please wait...");
        showGroup.getChildren().add(waiting);
        waiting.setStyle("-fx-font-size: 30; -fx-opacity: 1; -fx-font-family:'A Spirit Of Doha Black';" +
                " -fx-font-weight: BOLD; -fx-text-fill: #cf5e2c");
        waiting.relocate(190, 220);

        checkGoToGame();
    }


    public Scene getShowScene() {
        return showScene;
    }

    public Stage getStage() {
        return stage;
    }

    private void checkGoToGame() throws IOException {
        Scanner scanner = new Scanner(clientController.getClientSocket().getInputStream());
        String input = "";
        while (true) {
            input = scanner.nextLine();
            System.out.println("input is: " + input);
            if (input.equals("true")) {
                try {
                    Group map = new Group();
                    Scene mapScene = new Scene(map, 1000, 750);
                    mapView.gameMap(map, mapScene, clientController.getMap());
                    stage.setScene(mapScene);

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                break;
            }
        }

    }


}
