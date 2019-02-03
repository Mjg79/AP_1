package View;

import Controller.Controller;
import View.Menu.MenuView;
import View.MultiPlayerScene.MultiPlayer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.FileNotFoundException;

public class View extends Application {
    private static final String backGround =
            "C:\\Users\\Home\\Desktop\\farmFrenzySaveFiles\\farmFrenzyScenesDesign\\back.png";
    private static final String accounts = "C:\\Users\\Home\\Desktop\\farmFrenzySaveFiles\\accounts";

    private static final String serviceFiles =
            "C:\\Users\\Home\\Desktop\\farmFrenzySaveFiles\\farmFrenzyPlacesAndOthers\\Service\\";
    private static final String farmFrenzyScenesDesign =
            "C:\\Users\\Home\\Desktop\\farmFrenzySaveFiles\\farmFrenzyScenesDesign\\";
    private static Media backGroundMedia;
    static {
        backGroundMedia = new Media(View.class.getResource("farmFrenzySaveFiles/soundTracks/backGroundSoundTrack.mp3").toExternalForm());
    }
    private static MediaPlayer backGroundMediaPlayer = new MediaPlayer(backGroundMedia);


    @Override
    public void start(Stage primaryStage) throws Exception{
        MenuView menuView = new MenuView();
        Controller controller = new Controller();
        backGroundMediaPlayer.setOnEndOfMedia(new Runnable() {
            public void run() {
                backGroundMediaPlayer.seek(Duration.ZERO);
            }
        });
        backGroundMediaPlayer.play();

        Group mainMenu = new Group();
        Scene menu = new Scene(mainMenu, 800, 620);

        Group nameMenu = new Group();
        Scene names = new Scene(nameMenu, 818, 557);

        Group multGroup = new Group();
        Scene multScene = new Scene(multGroup, 800, 620);

        MultiPlayer multiPlayer = new MultiPlayer(menu, multScene, multGroup, primaryStage);

        Group chooseMap = new Group();
        Scene choseMap = new Scene(chooseMap, 818, 557);
        menuView.mapChooseMenu(primaryStage, chooseMap, choseMap, menu, controller);

        menuView.mainMenu(multiPlayer, primaryStage, menu, names, choseMap,  mainMenu, nameMenu);
        menuView.nameMenu(primaryStage, menu, nameMenu, names, mainMenu, choseMap);

        primaryStage.setScene(menu);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
