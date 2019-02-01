package View.Chat;

import Model.Data.DataReader;
import Model.Data.DataWriter;
import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import Controller.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.Socket;
import java.util.Formatter;
import java.util.Scanner;

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
        makeButtonMap();
        try {
            sendMessage();
            getMessage();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void putYourTextInChat(String string) {
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
                    numOfMes++;
                    input[0] = "";
                }

            }
        };
        timer.start();
    }

    private void putOthersTextInChat(String string) {
        final String[] input = {string};
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                if (!input[0].equals("")) {
                    text = new Label();
                    text.setText(input[0]);
                    text.relocate(170, 100 + numOfMes * 25);
                    text.setStyle("-fx-font-family: 'A Spirit Of Doha Black'; -fx-text-fill: #fffdfe; " +
                            "-fx-font-size: 20");
                    chatGroup.getChildren().add(text);
                    numOfMes++;
                    input[0] = "";
                }

            }
        };
        timer.start();
    }

    private void sendMessage() {
        message.addEventHandler(KeyEvent.KEY_PRESSED, (key) -> {
            if (key.getCode() == KeyCode.ENTER) {
                if (controller instanceof ClientController) {
                    String user = controller.getProfile().getUserName();
                    makeWriterThread(((ClientController) controller).getClientSocket(), user + ": " +
                           message.getText());
                    putYourTextInChat(message.getText());
                    message.clear();
                }

                if (controller instanceof ServerController) {
                    String userName = ((ServerController) controller).getProfile().getUserName();
                    for (Profile profile : ((ServerController) controller).getClients().keySet()) {
                        System.out.println("the socket is: " + ((ServerController) controller).getClients().get(profile).toString());
                        makeWriterThread(((ServerController) controller).getClients().get(profile),
                                userName + ": " + message.getText());
                    }
                    putYourTextInChat(message.getText());
                    message.clear();
                }

            }
        });
    }

    private void getMessage() throws IOException {
        if (controller instanceof ServerController) {
            for (Profile profile: ((ServerController) controller).getClients().keySet()) {
                makeReaderThreadToSendOtherClients((ServerController) controller,
                        ((ServerController) controller).getClients().get(profile));
            }
        }


        if (controller instanceof ClientController) {
            System.out.println("in gettingMessage: " + ((ClientController) controller).getClientSocket().toString());
            makeReaderThread(((ClientController) controller).getClientSocket());
        }
    }



    private void makeButtonMap() {
        Button button = new Button("map");
        button.setScaleX(2);
        button.setScaleY(1.2);
        button.relocate(535, 567);
        button.setStyle("-fx-background-color: #b5e627; -fx-background-radius: 10px" +
                "; -fx-font-family: 'Bodoni MT Black' ; -fx-font-size: 12;-fx-border-radius: 5px;" +
                " -fx-border-color: #03ea39; -fx-border-width: 3px;");
        chatGroup.getChildren().add(button);
        button.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                stage.setScene(mapScene);
            }
        });
    }

    private void makeWriterThread(Socket socket, String string) {
        Thread threadWriter = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Formatter formatter = new Formatter(socket.getOutputStream());
                    formatter.format(string + "\n");
                    formatter.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        threadWriter.start();
    }

    private void makeReaderThread(Socket socket) {
        Thread threadReader = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Scanner scanner = new Scanner(socket.getInputStream());
                    while (true) {
                        System.out.println("I'm waiting...");
                        String string = scanner.nextLine();
                        putOthersTextInChat(string);
                        System.out.println("I got it...");
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        threadReader.start();
    }


    private void makeReaderThreadToSendOtherClients(ServerController server, Socket socket) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (true) {
                        Scanner scanner = new Scanner(socket.getInputStream());
                        String input = scanner.nextLine();
                        putOthersTextInChat(input);
                        String[] split = input.split(":");
                        for (Profile profile: server.getClients().keySet()) {
                            if (profile.getUserName().equals(split[0]))
                                continue;
                            makeWriterThread(server.getClients().get(profile), input);
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
    }
}
