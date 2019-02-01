package View.ScoreBoard;

import Controller.Controller;
import Controller.Profile;
import View.Buttons.GeneralButton;
import com.google.gson.Gson;
import com.sun.xml.internal.bind.v2.runtime.reflect.Lister;
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

import java.awt.color.ProfileDataException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class ScoreBoardScene {
    private transient Group scoreGroup = new Group();
    private transient Scene scoreScene = new Scene(scoreGroup, 800, 600);
    private transient Scene mapScene;
    private transient Stage stage;
    private transient ArrayList<Profile> profiles;

    public ScoreBoardScene(Scene mapScene, Stage stage) {
        this.mapScene = mapScene;
        this.stage = stage;
    }

    public Scene getScoreScene() {
        return scoreScene;
    }

    private void initializeScoreBoard() throws FileNotFoundException {
            ImageView backGround = new ImageView(new Image(new FileInputStream("C:\\Users\\Home\\Desktop\\" +
                    "farmFrenzySaveFiles\\farmFrenzyScenesDesign\\back.png")));
            scoreGroup.getChildren().add(backGround);
            Rectangle items = new Rectangle(120, 120, 500, 400);
            items.setStyle("-fx-stroke: #47211e; -fx-stroke-width: 5;  -fx-background-radius: 10;" +
                    "  -fx-border-width: 5; -fx-fill: #e5c06f;" +
                    " -fx-border-radius: 5px");
            scoreGroup.getChildren().add(items);

            Image image = new Image(new FileInputStream(
                    "C:\\Users\\Home\\Desktop\\farmFrenzySaveFiles\\Logo.png"));
            ImageView imageView = new ImageView(image);
            imageView.relocate(180, 0);
            imageView.setFitWidth(441);
            imageView.setFitHeight(100);

            scoreGroup.getChildren().add(imageView);

    }

    public void designScoreBoard() throws FileNotFoundException {
        scoreGroup.getChildren().clear();
        initializeScoreBoard();
        profiles = new ArrayList<>();

        Label showDatas = new Label("Name\t\t\t\tuserName\t\tnameOfGamePlayed\t\tbudget");
        scoreGroup.getChildren().add(showDatas);
        showDatas.setStyle("-fx-font-size: 20; -fx-opacity: 1; -fx-font-family:'A Spirit Of Doha Black';" +
                " -fx-font-weight: BOLD; -fx-text-fill: #cf5e2c");
        showDatas.relocate(140, 120);

        showPlayers();
        mapButton();
    }

    private void showPlayers() throws FileNotFoundException {
        Scanner scanner = new Scanner(new FileInputStream("C:\\Users\\Home\\Desktop\\" +
                "farmFrenzySaveFiles\\multiPlayer\\players.json"));
        while (scanner.hasNextLine()) {
            profiles.add(new Gson().fromJson(scanner.nextLine(), Profile.class));
        }
        Collections.sort(profiles);
        for (Profile profile: profiles) {
            Label label = new Label((profiles.indexOf(profile) + 1) + ". " + profile.getName() +
                    "\t\t\t\t" + profile.getUserName() + "\t\t\t\t" + profile.getNumOfGames()+ "\t\t\t\t"
                    + profile.getBudget());
            label.relocate(140, 30 * profiles.indexOf(profile) + 150);
            label.setStyle("-fx-font-size: 20; -fx-opacity: 1; -fx-font-family:'A Spirit Of Doha Black';" +
                    " -fx-font-weight: BOLD; -fx-text-fill: #6d3617");
            scoreGroup.getChildren().add(label);
        }
    }

    private void mapButton() {
        Button map  = new Button("map");
        map.setScaleX(1.5);
        map.setScaleY(1.5);
        map.relocate(550, 480);
        map.setStyle("-fx-background-color: #b5e627; -fx-background-radius: 10px" +
                "; -fx-font-family: 'Bodoni MT Black' ; -fx-font-size: 12;-fx-border-radius: 5px;" +
                " -fx-border-color: #03ea39; -fx-border-width: 3px;");
        scoreGroup.getChildren().add(map);

        GeneralButton.buttonAppearanceWithCursor(map, scoreScene);

        map.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
               stage.setScene(mapScene);
            }
        });
    }


}