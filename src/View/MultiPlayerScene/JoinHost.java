package View.MultiPlayerScene;

import Controller.ClientController;
import Controller.Profile;
import View.Buttons.GeneralButton;
import View.Helicopter.HeliCopterView;
import View.Helicopter.HeliCopterViewOnline;
import View.Map.MapView;
import View.Map.WarehouseScene;
import com.google.gson.Gson;
import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.io.*;
import java.net.Socket;
import java.net.SocketException;
import java.util.Scanner;

public class JoinHost {
    private transient Group joinGroup = new Group();
    private transient Scene joinScene = new Scene(joinGroup, 800, 600);
    private transient Scene hostScene;
    private transient Stage stage;
    private transient ClientController clientController;
    private transient TextField enterIP;
    private transient TextField enterPort;
    private transient TextField name;
    private transient TextField userName;
    private transient Label label = new Label("this userName is already existed.");
    private transient ClientShowList clientShowList;

    private static final String PLAYERS = "C:\\Users\\Home\\Desktop\\farmFrenzySaveFiles\\multiPlayer\\players.json";

    public JoinHost(Scene hostScene, Stage stage) throws SocketException {
        this.hostScene = hostScene;
        this.stage = stage;
    }

    private void initializeJoinScene() throws FileNotFoundException {
        ImageView backGround = new ImageView(new Image(new FileInputStream("C:\\Users\\Home\\Desktop\\" +
                "farmFrenzySaveFiles\\farmFrenzyScenesDesign\\back.png")));
        joinGroup.getChildren().add(backGround);
        Rectangle items = new Rectangle(234, 253, 320, 258);
        items.setStyle("-fx-stroke: #47211e; -fx-stroke-width: 5;  -fx-background-radius: 10;" +
                "  -fx-border-width: 5; -fx-fill: #e5c06f;" +
                " -fx-border-radius: 5px");
        joinGroup.getChildren().add(items);

        Image image = new Image(new FileInputStream(
                "C:\\Users\\Home\\Desktop\\farmFrenzySaveFiles\\Logo.png"));
        ImageView imageView = new ImageView(image);
        imageView.relocate(180, 130);
        imageView.setFitWidth(441);
        imageView.setFitHeight(100);
        joinGroup.getChildren().add(imageView);
    }

    private void makeServerIpTextField() {
        Label ip = new Label("Server IP: ");
        ip.setStyle("-fx-font-size: 25; -fx-opacity: 1; -fx-font-family:'A Spirit Of Doha Black';" +
                " -fx-font-weight: BOLD");
        ip.relocate(260, 370);
        joinGroup.getChildren().add(ip);
        enterIP = new TextField();
        enterIP.relocate(339, 380);
        enterIP.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                enterIP.clear();
            }
        });
        enterIP.setStyle("-fx-background-color: #d2e0d7; -fx-border-radius: 5px;" +
                "-fx-font-weight: bold; -fx-text-fill: #4c260a;");
        joinGroup.getChildren().add(enterIP);

    }

    private void makeServerPortTextField() {
        Label port = new Label("Server port: ");
        port.setStyle("-fx-font-size: 25; -fx-opacity: 1; -fx-font-family:'A Spirit Of Doha Black';" +
                " -fx-font-weight: BOLD");
        port.relocate(255, 410);
        joinGroup.getChildren().add(port);
        enterPort = new TextField();
        enterPort.relocate(339, 420);
        enterPort.setStyle("-fx-background-color: #d2e0d7; -fx-border-radius: 5px;" +
                "-fx-font-weight: BOLD; -fx-text-fill: #4c260a;");
        joinGroup.getChildren().addAll(enterPort);
        enterPort.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                enterPort.clear();
            }
        });
    }

    private void makeUserNameTextField() {
        Label user = new Label("userName: ");
        user.setStyle("-fx-font-size: 25; -fx-opacity: 1; -fx-font-family:'A Spirit Of Doha Black';" +
                " -fx-font-weight: BOLD");
        user.relocate(255, 318);
        joinGroup.getChildren().add(user);
        userName = new TextField();
        userName.relocate(339, 328);
        userName.setStyle("-fx-background-color: #d2e0d7; -fx-border-radius: 5px;" +
                "-fx-font-weight: bold; -fx-text-fill: #4c260a;");
        joinGroup.getChildren().add(userName);
        userName.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                userName.clear();
            }
        });
    }

    private void makeNameTextField() {
        Label user = new Label("Name: ");
        user.setStyle("-fx-font-size: 25; -fx-opacity: 1; -fx-font-family:'A Spirit Of Doha Black';" +
                " -fx-font-weight: BOLD");
        user.relocate(255, 280);
        joinGroup.getChildren().add(user);
        name = new TextField();
        name.relocate(339, 290);
        name.setStyle("-fx-background-color: #d2e0d7; -fx-border-radius: 5px;" +
                "-fx-font-weight: BOLD; -fx-text-fill: #4c260a;");
        joinGroup.getChildren().add(name);
        name.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                name.clear();
            }
        });
    }

    private void userNameCheck(String userName) {
        label.setStyle("-fx-font-size: 20; -fx-opacity: 1; -fx-font-family:'A Spirit Of Doha Black';" +
                " -fx-font-weight: BOLD; -fx-text-fill: #ff1524");
        label.relocate(335, 350);
        if (!joinGroup.getChildren().contains(label))
            joinGroup.getChildren().add(label);
        try {
            if (isDuplicateUserName(userName))
                label.setVisible(true);
            else
                label.setVisible(false);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }




    public void showJoinHost() throws FileNotFoundException {
        initializeJoinScene();
        makeNameTextField();
        makeUserNameTextField();
        makeServerIpTextField();
        makeServerPortTextField();

        makeOkButton(enterIP, enterPort);
        makeCancelButton();
    }

    private void makeOkButton(TextField serverIp, TextField serverPort) {
        Button ok = new Button("    ok   ");
        ok.relocate(265, 453);
        joinGroup.getChildren().add(ok);
        ok.setStyle("-fx-background-color: #51e6e1; -fx-background-radius: 10px" +
                "; -fx-font-family: 'Bodoni MT Black' ; -fx-font-size: 12;-fx-border-radius: 5px;" +
                " -fx-border-color: #192dea; -fx-border-width: 3px");
        GeneralButton.buttonAppearanceWithCursor(ok, joinScene);
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                userNameCheck(userName.getText());
                try {
                    if (serverIp.getText().equals("") || isDuplicateUserName(userName.getText()))
                        ok.setVisible(false);
                    else
                        ok.setVisible(true);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        };
        timer.start();
        ok.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                try {
                    Socket client = new Socket(serverIp.getText(), Integer.parseInt(serverPort.getText()));

                    ObjectOutputStream outputStream = new ObjectOutputStream(client.getOutputStream());
                    Profile profile = new Profile(name.getText(), userName.getText());
                    outputStream.writeObject(profile);//client give its data to server


                    makeUserNameInPlayersFile(name.getText(), userName.getText());//make new userName in players.json
                    clientController = new ClientController(client, profile);
                    //make client controller for player who joined in host
                    Group map = new Group();
                    Scene mapScene = new Scene(map, 1000, 750);
                    clientShowList = new ClientShowList(stage, clientController, makeMapView(map, mapScene), mapScene, map);
                    stage.setScene(clientShowList.getShowScene());
                    clientShowList.designClientList();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                userName.clear();
                name.clear();
                enterIP.clear();
                enterPort.clear();
            }
        });
    }

    private void makeCancelButton() {
        Button cancel = new Button("  cancel ");
        cancel.relocate(435, 453);
        joinGroup.getChildren().add(cancel);
        cancel.setStyle("-fx-background-color: #fd798a; -fx-background-radius: 10px" +
                "; -fx-font-family: 'Bodoni MT Black' ; -fx-font-size: 12;-fx-border-radius: 5px;" +
                " -fx-border-color: #ea0a5b; -fx-border-width: 3px");
        GeneralButton.buttonAppearanceWithCursor(cancel, joinScene);

        cancel.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                name.clear();
                userName.clear();
                enterPort.clear();
                enterIP.clear();
                stage.setScene(hostScene);
            }
        });
    }

    public Scene getJoinScene() {
        return joinScene;
    }

    private boolean isDuplicateUserName(String userName) throws FileNotFoundException {
        InputStream inputStream = new FileInputStream(PLAYERS);
        Scanner scanner = new Scanner(inputStream);
        int i = 0;
        Profile profile;
        while (scanner.hasNextLine()) {
            Gson deserializer = new Gson();
            String sentence = scanner.nextLine();
            profile = deserializer.fromJson(sentence, Profile.class);
            if (profile.getUserName().equals(userName))
                return true;
        }

        return false;
    }

    private void makeUserNameInPlayersFile(String name, String userName) throws IOException {
        OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream(PLAYERS, true));
        Profile profile = new Profile(name, userName);
        Gson serializer = new Gson();
        serializer.toJson(profile, Profile.class, writer);
        writer.write("\n");
        writer.flush();
    }

    private MapView makeMapView(Group map, Scene mapScene) throws FileNotFoundException {
        Group hGroup = new Group();
        Scene hScene = new Scene(hGroup, 1000, 750);
        WarehouseScene warehouseScene = new WarehouseScene(clientController, mapScene, clientController.getMap(), map);

        MapView mapView = new MapView(clientController,stage ,mapScene,  hScene, hostScene, hostScene, warehouseScene, "offline");

        HeliCopterViewOnline heliCopterView = new HeliCopterViewOnline(stage, mapScene,
                hScene, hGroup, map);
        heliCopterView.helicopterShow(clientController);
        return new MapView(clientController, stage ,mapScene,  hScene, hostScene, hostScene, warehouseScene ,"online");

    }

}
