package View.Chat;

import Controller.Controller;
import Controller.Profile;
import Model.Data.DataReader;
import Model.Data.DataWriter;
import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import Controller.*;

import javax.xml.crypto.Data;
import javax.xml.stream.FactoryConfigurationError;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class ChatRoom {
    private transient Scene mapScene;
    private transient Group chatGroup = new Group();
    private transient Scene chatScene = new Scene(chatGroup, 800, 600);
    private transient Controller controller;
    private transient Stage stage;
    private transient TextField message;
    private int numOfMes = 0;
    private DataReader dataReader;
    private DataWriter dataWriter;
    private ArrayList<DataReader> dataReaders = new ArrayList<>();
    private ArrayList<DataWriter> dataWriters = new ArrayList<>();
    private transient Label text = new Label();

    private static final String FARMFRENZYSAVEFILES = "C:\\Users\\Home\\Desktop\\farmFrenzySaveFiles\\";

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
        if (controller instanceof ClientController) {
            dataReader = new DataReader(((ClientController) controller).getClientSocket());
            dataWriter = new DataWriter(((ClientController) controller).getClientSocket());
            dataReader.start();
            dataWriter.start();
        }

        if (controller instanceof ServerController) {
            for (Profile profile : ((ServerController) controller).getClients().keySet()) {
                DataReader dataReader = new DataReader(((ServerController) controller).getClients().get(profile));
                dataReader.start();
                DataWriter dataWriter = new DataWriter(((ServerController) controller).getClients().get(profile));
                dataWriter.start();
            }
        }
    }

    private void initializeChatRoom() throws FileNotFoundException {
        ImageView backGround = new ImageView(
                new Image(new FileInputStream(FARMFRENZYSAVEFILES + "farmFrenzyScenesDesign\\back.png")));
        chatGroup.getChildren().add(backGround);
        ImageView chat = new ImageView(
                new Image(new FileInputStream(FARMFRENZYSAVEFILES + "chat\\chatBox.png")));
        chat.relocate(150, 50);
        chatGroup.getChildren().add(chat);

        ImageView type = new ImageView(
                new Image(new FileInputStream(FARMFRENZYSAVEFILES + "chat\\typeMessage.png")));

        type.relocate(155, 520);
        chatGroup.getChildren().add(type);

        message = new TextField();
        message.relocate(323, 522);
        message.setScaleX(1.8);
        message.setStyle("-fx-background-color: #02abfe; -fx-font-weight: BOLD;" +
                " -fx-font-family: 'A Spirit Of Doha Black'; -fx-font-size: 15");
        chatGroup.getChildren().add(message);
        message.setOpacity(1);


    }

    public void makeChatRoom() throws FileNotFoundException {
        initializeChatRoom();
        sendMessage();
    }


    private void  putTextInChat(String string) {
        final String[] input = {string};
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                if (!input[0].equals("")) {
                    text = new Label();
                    text.setText("you: " + input[0]);
                    text.relocate(170, 100 + numOfMes * 25);
                    text.setStyle("-fx-font-family: 'A Spirit Of Doha Black'; -fx-text-fill: #ffe700; " +
                            "-fx-font-size: 20");
                    chatGroup.getChildren().add(text);
                    numOfMes ++;
                    input[0] = "";
                }

            }
        };
        timer.start();
    }

    private void sendMessage() {
        message.addEventHandler(KeyEvent.KEY_PRESSED, (key) ->{
            if (key.getCode() == KeyCode.ENTER) {
               if (controller instanceof ClientController) {
                     dataWriter.setSentence(
                             ((ClientController) controller).getProfile().getUserName() + ": " + message.getText());
                              putTextInChat(message.getText());
                              message.clear();
               }
            }
        });
    }

    private void getMessage() {
        if (controller instanceof ServerController) {
            for (DataReader dataReader : dataReaders) {
                String input = dataReader.getInput();
                if (!input.equals("")) {
                    String[] split = input.split(":");
                    for (Profile profile: ((ServerController) controller).getProfiles()) {
                        if (profile.getUserName().equals(split[0]))
                            continue;
                    }
                }
            }
        }
    }

}
