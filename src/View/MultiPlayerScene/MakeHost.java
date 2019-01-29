package View.MultiPlayerScene;

import Controller.ServerController;
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
import java.net.ServerSocket;

public class MakeHost {
    private transient Group hostGroup = new Group();
    private transient Scene hostScene = new Scene(hostGroup, 800, 620);
    private Scene pScene;
    private Stage stage;
    private ServerController serverController = new ServerController();


    public MakeHost(Stage stage, Scene pScene) throws IOException {
        this.pScene = pScene;
        this.stage = stage;
    }

    public Group getHostGroup() {
        return hostGroup;
    }

    public Scene getHostScene() {
        return hostScene;
    }

    public Scene getpScene() {
        return pScene;
    }

    public Stage getStage() {
        return stage;
    }

    public ServerController getServerController() {
        return serverController;
    }

    public void hostDesign() throws FileNotFoundException {
        ImageView back = new ImageView(new Image(new FileInputStream("C:\\Users\\Home\\Desktop\\farmFrenzySaveFiles" +
                "\\" + "farmFrenzyScenesDesign\\back.png")));
        hostGroup.getChildren().add(back);

        javafx.scene.shape.Rectangle items = new Rectangle(234, 253, 320, 278);
        items.setStyle("-fx-stroke: #47211e; -fx-stroke-width: 5;  -fx-background-radius: 10;" +
                "  -fx-border-width: 5; -fx-fill: #e5c06f;" +
                " -fx-border-radius: 5px");
        hostGroup.getChildren().add(items);

        Image image = new Image(new FileInputStream(
                "C:\\Users\\Home\\Desktop\\farmFrenzySaveFiles\\Logo.png"));
        ImageView imageView = new ImageView(image);
        imageView.relocate(180, 130);
        imageView.setFitWidth(441);
        imageView.setFitHeight(100);
        hostGroup.getChildren().add(imageView);
        serverController.getClients().get("ali");
    }
}