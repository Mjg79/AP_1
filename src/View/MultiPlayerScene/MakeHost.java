package View.MultiPlayerScene;

import Controller.Profile;
import Controller.ServerController;
import View.Buttons.GeneralButton;
import View.Helicopter.HeliCopterView;
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
import java.net.SocketException;
import java.util.HashMap;
import java.util.Scanner;

public class MakeHost {
    private transient Group makeHostGroup = new Group();
    private transient Scene makeHostScene = new Scene(makeHostGroup, 800, 600);
    private transient Scene hostScene;
    private transient Stage stage;
    private transient TextField name;
    private transient TextField userName;
    private transient Label label = new Label("this userName is already existed.");
    private transient ServerController serverController;
    private transient ServerShowList showList;

    private static final String PLAYERS = "C:\\Users\\Home\\Desktop\\farmFrenzySaveFiles\\multiPlayer\\players.json";

    public MakeHost(Scene hostScene, Stage stage, ServerController serverController) throws SocketException {
        this.hostScene = hostScene;
        this.stage = stage;
        this.serverController = serverController;
    }

    private void initializeJoinScene() throws FileNotFoundException {
        ImageView backGround = new ImageView(new Image(new FileInputStream("C:\\Users\\Home\\Desktop\\" +
                "farmFrenzySaveFiles\\farmFrenzyScenesDesign\\back.png")));
        makeHostGroup.getChildren().add(backGround);
        Rectangle items = new Rectangle(234, 253, 320, 258);
        items.setStyle("-fx-stroke: #47211e; -fx-stroke-width: 5;  -fx-background-radius: 10;" +
                "  -fx-border-width: 5; -fx-fill: #e5c06f;" +
                " -fx-border-radius: 5px");
        makeHostGroup.getChildren().add(items);

        Image image = new Image(new FileInputStream(
                "C:\\Users\\Home\\Desktop\\farmFrenzySaveFiles\\Logo.png"));
        ImageView imageView = new ImageView(image);
        imageView.relocate(180, 130);
        imageView.setFitWidth(441);
        imageView.setFitHeight(100);
        makeHostGroup.getChildren().add(imageView);
    }


    private void makeUserNameTextField() {
        Label user = new Label("userName: ");
        user.setStyle("-fx-font-size: 25; -fx-opacity: 1; -fx-font-family:'A Spirit Of Doha Black';" +
                " -fx-font-weight: BOLD");
        user.relocate(255, 318);
        makeHostGroup.getChildren().add(user);
        userName = new TextField();
        userName.relocate(339, 328);
        userName.setStyle("-fx-background-color: #d2e0d7; -fx-border-radius: 5px;" +
                "-fx-font-weight: bold; -fx-text-fill: #4c260a;");
        makeHostGroup.getChildren().add(userName);
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
        makeHostGroup.getChildren().add(user);
        name = new TextField();
        name.relocate(339, 290);
        name.setStyle("-fx-background-color: #d2e0d7; -fx-border-radius: 5px;" +
                "-fx-font-weight: BOLD; -fx-text-fill: #4c260a;");
        makeHostGroup.getChildren().add(name);
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
        if (!makeHostGroup.getChildren().contains(label))
            makeHostGroup.getChildren().add(label);
        try {
            if (isDuplicateUserName(userName))
                label.setVisible(true);
            else
                label.setVisible(false);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }




    public void showMakeHost() throws FileNotFoundException {
        initializeJoinScene();
        makeNameTextField();
        makeUserNameTextField();

        makeOkButton(name, userName);
        makeCancelButton();
    }

    private void makeOkButton(TextField name, TextField userName) {
        Button ok = new Button("    ok   ");
        ok.relocate(265, 454);
        makeHostGroup.getChildren().add(ok);
        ok.setStyle("-fx-background-color: #51e6e1; -fx-background-radius: 10px" +
                "; -fx-font-family: 'Bodoni MT Black' ; -fx-font-size: 12;-fx-border-radius: 5px;" +
                " -fx-border-color: #192dea; -fx-border-width: 3px");
        GeneralButton.buttonAppearanceWithCursor(ok, makeHostScene);
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                userNameCheck(userName.getText());
                try {
                    if (name.getText().equals("") || isDuplicateUserName(userName.getText()))
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
                    serverController.setProfile(new Profile(name.getText(), userName.getText()));
                    Group map = new Group();
                    Scene mapScene = new Scene(map, 1000, 750);
                    showList = new ServerShowList(stage, serverController, makeMapView(map, mapScene), mapScene, map);
                    stage.setScene(showList.getShowScene());
                    showList.designList();
                    makeUserNameInPlayersFile(name.getText(), userName.getText());
                    makeBazarFile();
                }  catch (IOException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                name.clear();
                userName.clear();
            }
        });
    }

    private void makeBazarFile() throws IOException {
        HashMap<String, Integer> goods = new HashMap<>();
        goods.put("flour", 10);
        goods.put("cookie", 10);
        goods.put("cake", 10);
        goods.put("egg", 10);
        goods.put("powderedEgg", 10);
        goods.put("feather", 10);
        goods.put("horn", 10);
        Gson serializer = new Gson();
        OutputStreamWriter writer = new OutputStreamWriter(
                new FileOutputStream("C:\\Users\\Home\\Desktop\\farmFrenzySaveFiles\\multiplayer\\bazar.json"));
        serializer.toJson(goods, HashMap.class, writer);
        writer.flush();
    }

    private MapView makeMapView(Group map, Scene mapScene) throws FileNotFoundException {
        Group hGroup = new Group();
        Scene hScene = new Scene(hGroup, 1000, 750);
        WarehouseScene warehouseScene = new WarehouseScene(serverController, mapScene, serverController.getMap(), map);

        MapView mapView = new MapView(serverController,stage ,mapScene,  hScene, makeHostScene, makeHostScene, warehouseScene, "offline");

        HeliCopterView heliCopterView = new HeliCopterView(stage, mapScene,
                hScene, hGroup, map);
        heliCopterView.helicopterShow(serverController);
        return new MapView(serverController, stage ,mapScene,  hScene, hostScene, hostScene, warehouseScene ,"online");

    }

    private void makeCancelButton() {
        Button cancel = new Button("  cancel ");
        cancel.relocate(435, 453);
        makeHostGroup.getChildren().add(cancel);
        cancel.setStyle("-fx-background-color: #fd798a; -fx-background-radius: 10px" +
                "; -fx-font-family: 'Bodoni MT Black' ; -fx-font-size: 12;-fx-border-radius: 5px;" +
                " -fx-border-color: #ea0a5b; -fx-border-width: 3px");
        GeneralButton.buttonAppearanceWithCursor(cancel, makeHostScene);

        cancel.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                name.clear();
                userName.clear();
                stage.setScene(hostScene);
            }
        });
    }

    public Scene getMakeHostScene() {
        return makeHostScene;
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
}
