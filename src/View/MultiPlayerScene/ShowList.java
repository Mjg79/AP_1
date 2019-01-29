package View.MultiPlayerScene;

import Controller.ServerController;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ShowList {
    private Group showGroup = new Group();
    private Scene showScene = new Scene(showGroup, 800, 620);
    private Stage stage;
    private ServerController serverController;

    public ShowList(Stage stage) {
        this.stage = stage;
    }

    public void ShowList() throws IOException {


    }

}
