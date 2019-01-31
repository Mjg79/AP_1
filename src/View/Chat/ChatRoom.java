package View.Chat;

import Controller.Controller;
import Controller.Profile;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ChatRoom {
    private transient Scene mapScene;
    private transient Group chatGroup = new Group();
    private transient Scene chatScene = new Scene(chatGroup, 800, 600);
    private transient Controller controller;
    private transient Stage stage;

    public Scene getMapScene() {
        return mapScene;
    }

    public Scene getChatScene() {
        return chatScene;
    }

    public ChatRoom(Scene mapScene, Controller controller, Stage stage) {
        this.mapScene = mapScene;
        this.controller = controller;
        this.stage = stage;
    }

}
