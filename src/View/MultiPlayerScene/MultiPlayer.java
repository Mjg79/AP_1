package View.MultiPlayerScene;

import Controller.ServerController;
import Model.ControlSystem;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.SocketException;

import static View.Menu.MenuView.buttonOpacityChanged;

public class MultiPlayer {
    private transient Scene menuScene;
    private transient Scene multScene;
    private transient Group multGroup;
    private transient Stage stage;
    private transient ServerShowList showList;
    private transient JoinHost jHost;
    private transient MakeHost mHost;
    private transient ServerController serverController = new ServerController();

    public MultiPlayer(Scene menuScene, Scene multScene, Group multGroup, Stage primaryStage) throws IOException {
        this.menuScene = menuScene;
        this.multScene = multScene;
        this.multGroup = multGroup;
        this.stage = primaryStage;
    }

    public Scene getMenuScene() {
        return menuScene;
    }

    public Scene getMultScene() {
        return multScene;
    }

    public Group getMultGroup() {
        return multGroup;
    }

    public Stage getStage() {
        return stage;
    }


    public void multiPlayerDesign() throws FileNotFoundException {
            ImageView backGround = new ImageView(new Image(new FileInputStream("C:\\Users\\Home\\Desktop\\" +
                    "farmFrenzySaveFiles\\farmFrenzyScenesDesign\\back.png")));

            multGroup.getChildren().add(backGround);

            Rectangle items = new Rectangle(234, 253, 320, 278);
            items.setStyle("-fx-stroke: #47211e; -fx-stroke-width: 5;  -fx-background-radius: 10;" +
                    "  -fx-border-width: 5; -fx-fill: #e5c06f;" +
                    " -fx-border-radius: 5px");
            multGroup.getChildren().add(items);

            Image image = new Image(new FileInputStream(
                    "C:\\Users\\Home\\Desktop\\farmFrenzySaveFiles\\Logo.png"));
            javafx.scene.image.ImageView imageView = new ImageView(image);
            imageView.relocate(180, 130);
            imageView.setFitWidth(441);
            imageView.setFitHeight(100);
            multGroup.getChildren().add(imageView);

        Button makeHost = new Button("       Make Host      ");
        makeHost.setFont(Font.font("Bauhaus 93", FontWeight.SEMI_BOLD, FontPosture.REGULAR, 18));
        makeHost.setStyle("-fx-background-color: #6993e3; -fx-border-color: #3e48cc; -fx-border-width: 3" +
                ";  -fx-text-fill: linear-gradient(from 0% 0% to 100% 250%, repeat, whitesmoke 0%, black 60%);" +
                " -fx-border-radius: 5; -fx-background-radius: 10;");
        makeHost.relocate(301, 303);
        buttonOpacityChanged(makeHost);
        multGroup.getChildren().add(makeHost);

        Button joinHost = new Button("       Join Host      ");
        joinHost.setFont(Font.font("Bauhaus 93", FontWeight.SEMI_BOLD, FontPosture.REGULAR, 18));
        joinHost.setStyle("-fx-background-color: #6993e3; -fx-border-color: #3e48cc; -fx-border-width: 3" +
                ";  -fx-text-fill: linear-gradient(from 0% 0% to 100% 250%, repeat, whitesmoke 0%, black 60%);" +
                " -fx-border-radius: 5; -fx-background-radius: 10;");
        joinHost.relocate(310, 383);
        buttonOpacityChanged(joinHost);
        multGroup.getChildren().add(joinHost);

        Button backToMenu = new Button("  backToMenu  ");
        backToMenu.relocate(381, 483);
        multGroup.getChildren().add(backToMenu);
        backToMenu.setStyle("-fx-font-family: 'Bodoni MT Black'; -fx-font-size: 16; -fx-font-style: italic;" +
                " -fx-text-fill: black; -fx-background-color: white ;" +
                "-fx-border-color: grey; -fx-border-width: 3px; -fx-border-radius: 10px;" +
                " -fx-background-radius: 15px");
        buttonOpacityChanged(backToMenu);
        backToMenu.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                stage.setScene(menuScene);
            }
        });

        makeHost.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                try {
                    mHost = new MakeHost(multScene, stage, serverController);
                    mHost.showMakeHost();
                    stage.setScene(mHost.getMakeHostScene());
                    serverController.setServer(new ServerSocket(8050));
                    ControlSystem.getControlSystem().setServerController(serverController);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        joinHost.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                try {
                    jHost = new JoinHost(multScene, stage);
                    stage.setScene(jHost.getJoinScene());
                    jHost.showJoinHost();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (SocketException e) {
                    e.printStackTrace();
                }
            }

        });

    }
}
